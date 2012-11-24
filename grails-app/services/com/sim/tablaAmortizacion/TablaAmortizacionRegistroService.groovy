package com.sim.tablaAmortizacion

import java.util.Date

import com.sim.credito.Prestamo
import com.sim.producto.ProPromocion
import com.sim.listacobro.ListaCobro

import com.sim.pfin.PfinMovimiento
import com.sim.pfin.PfinCatOperacion
import com.sim.pfin.SituacionPremovimiento
import com.sim.pfin.PfinCatParametro

import com.sim.catalogo.SimCatAccesorio
import com.sim.catalogo.SimCatFormaAplicacion
import com.sim.catalogo.SimCatMetodoCalculo
import com.sim.catalogo.SimCatPeriodicidad
import com.sim.catalogo.SimCatUnidad

class TablaAmortizacionServiceException extends RuntimeException {
	String	mensaje
}

class TablaAmortizacionRegistroService {

	static transactional = true

	Boolean generaTablaAmortizacion(Prestamo prestamoInstance) {

		//DEFINIMOS VARIABLE PARA DEFINIR EL PLAZO
		Integer numeroPago  = 1

		//VARIABLE PARA SABER CUANTOS PAGOS TIENE EL CREDITO
		Integer pagoCredito = 0

		//SE OBTIENE LA LISTA DE COBRO QUE SE ASIGNA A LA AMORTIZACION UNO
		ListaCobro primerPago = prestamoInstance.primerPagoDependcia

		if (!primerPago){
			log.info("No se especifico la lista de cobro para la amortizacion uno")
			throw new TablaAmortizacionServiceException(mensaje: "No se especifico el primer pago de la Dependencia")
		}

		//SE OBTIENTEN LAS LISTAS DE COBRO QUE PERTENECEN A LA DEPENDENCIA
		ArrayList listasDeCobro = ListaCobro.findAllByDependencia(prestamoInstance.dependencia,
                  [sort: "id", order: "asc"])

		//ARRAY PARA ALMACENAR LAS LISTAS DE COBRA QUE SE ELIMINARAN
		ArrayList periodicidadPagos = []

		//VALIDA QUE LISTAS DE COBRO VAN A SER ELIMINADAS
		listasDeCobro.each(){
			if (primerPago>it){
				periodicidadPagos.add(it)
			}
		}

		//REMUEVE LAS LISTAS DE COBRO QUE NO SE UTILIZAN
		periodicidadPagos.each(){
			listasDeCobro.remove(it)
		}

		//SE CREA OBJETO ITERATOR PARA MANEJAR LAS LISTAS DE COBRO
 		Iterator iteratorListasCobro = listasDeCobro.iterator();

		//OBTIENE TODOS LOS MOVIMIENTOS DEL PRESTAMO
		ArrayList listaMovimiento = PfinMovimiento.findAllByPrestamo(prestamoInstance)
		
		//VALIDA QUE NO EXISTE NINGUN MOVIMIENTO DE PAGO PARA EL PRESTAMO
		listaMovimiento.each(){
			PfinCatOperacion 		operacion = it.operacion
			SituacionPremovimiento  situacion = it.situacionMovimiento
			//VALIDA SI EL MOVIMIENTO TIENE CLAVE DE OPERACION IGUAL A CRPAGOPRES
			if (operacion.equals(PfinCatOperacion.findByClaveOperacion('CRPAGOPRES'))) {
				//VALIDA SI EL MOVIMIENTO TIENE SITUACION DIFERENTE DE CANCELADO
				if(situacion != SituacionPremovimiento.CANCELADO) {
					//CUENTA LOS MOVIMIENTOS QUE TIENEN CLAVE DE OPERACION IGUAL A CRPAGOPRES Y NO ESTAN CACELADOS
					pagoCredito++
				}
			}
		}
		
		//SI NO EXISTEN MOVIMIENTOS CON CLAVE OPERACION IGUAL A CRPAGOPRES Y QUE NO ESTEN CANCELADOS, SE CREA LA TABLA DE AMORTIZACION
		if (pagoCredito == 0) {
			log.info("Se genera la tabla de amortizacion")

			//OBTIENE TODOS LOS REGISTROS DE LA TABLA DE AMORTIZACION QUE PERTENECEN AL PRESTAMO PARA ELIMINARLOS
			
			//org.hibernate.collection.PersistentSet registrosTabla = prestamoInstance.tablaAmortizacion
			ArrayList registrosTabla = TablaAmortizacionRegistro.findAllByPrestamo(prestamoInstance)
			//log.info prestamoInstance.tablaAmortizacion.class
			//log.info registrosTabla.class
			
			if(registrosTabla){
				log.info("Elimina los registros de la tabla de amortizacion del credito")
				registrosTabla.each() {
					prestamoInstance.removeFromTablaAmortizacion(it)
				}
				prestamoInstance.save(flush: true)
			}

			//SE OBTIENEN LOS DATOS DEL PRESTAMO
			ProPromocion promocion = prestamoInstance.promocion
			SimCatMetodoCalculo metodoCalculo 	 = promocion.metodoCalculo
			SimCatPeriodicidad  periodicidadTasa = promocion.periodicidadTasa
			SimCatPeriodicidad  periodicidadPago = promocion.periodicidadPagos
			
			Integer diasPeriodicidadTasa 	 = periodicidadTasa.numeroDias
			Integer diasPeriodicidadPago 	 = periodicidadPago.numeroDias
			Integer iva 	 				 = promocion.iva
						
			BigDecimal importeIvaInteres
			BigDecimal cargoInicial			 = prestamoInstance.montoSolicitado
			BigDecimal capitalTotal          = 0
			
			Date fechaPago
			
			//SE OBTIENE LA FECHA DEL MEDIO
			PfinCatParametro parametros = PfinCatParametro.findByClaveMedio("SistemaMtn")

			if (!parametros){
				log.info("No se encontro la fecha del medio")
				throw new TablaAmortizacionServiceException(mensaje: "No se encontro la fecha del medio")
			}

			Date fechaMedio = parametros.fechaMedio
			
			//PARA OBTENER LA PRIMERA FECHA DE AMORTIZACION SE SUMAN 60 DIAS
			//IMPLEMENTACION TEMPORAL PARA ASIGNAR LAS FECHAS DE AMORTIZACION
			fechaPago = fechaMedio + 60
			
			//RECUPERA LOS ACCESORIOS DEL PRESTAMO
			ArrayList listaAccesorios	= prestamoInstance.prestamoAccesorio
			
			//VERFICA SI EXISTEN ACCESORIOS QUE DEBEN SER CARGOS INICIALES
			listaAccesorios.each() {
				SimCatFormaAplicacion formaAplicacion    = it.formaAplicacion
				BigDecimal valorAccesorio  				 = it.valor
				if (formaAplicacion.equals(SimCatFormaAplicacion.findByClaveFormaAplicacion('CARGO_INICIAL'))) {
					cargoInicial = cargoInicial + valorAccesorio
				}
			}
			
			//ASIGNA EL CARGO INICIAL CALCULADO A LA VARIALBLE capitalTotal PARA LOS CALCULOS POSTERIORES
			capitalTotal = cargoInicial
			
			BigDecimal saldoInicial			 = capitalTotal
			BigDecimal saldoInsoluto         = capitalTotal

			//*****En las siguientes variables no importa el método de calculo*****
				
			//VARIABLES A CALCULAR
			BigDecimal amortizacion      = 0
			BigDecimal pagoIntereses     = 0
			BigDecimal cuotaTotal        = 0
			BigDecimal pagoTotalInteres  = 0
			BigDecimal pagoTotalPrestamo = 0

			//FORMULA PARA EL CALCULO DE LA TASA
			BigDecimal tasa = (promocion.tasaDeInteres / 100) * (diasPeriodicidadPago / diasPeriodicidadTasa)
			
			(1..promocion.numeroDePagos).each{

				//SE OBTIENE LA LISTA DE COBRO QUE LE CORRESPONDE A LA AMORTIZACION
				ListaCobro listaCobroConsecutivo = (ListaCobro)iteratorListasCobro.next();

				//OBTIENE LOS CALCULOS CORRESPONDIENTE AL METODO DE CALCULO CON CLAVE METODO01
				if (promocion.metodoCalculo.equals(SimCatMetodoCalculo.findByClaveMetodoCalculo('METODO01'))) {
					
					//FORMULA PARA EL CALCULO DE LA AMORTIZACION
					amortizacion = capitalTotal / promocion.numeroDePagos
					//FORMULA PARA EL PAGO DE INTERES
					pagoIntereses = capitalTotal * tasa
					//CALCULA EL IVA DEL INTERES
					importeIvaInteres = pagoIntereses * (iva/100)
					//FORMULA PARA VER EL PAGO TOTAL
					cuotaTotal = amortizacion + pagoIntereses + importeIvaInteres
					//VARIABLES QUE GENERAN LOS PAGOS TOTALES
					pagoTotalInteres  = pagoIntereses * promocion.numeroDePagos
					pagoTotalPrestamo = capitalTotal + pagoTotalInteres
					saldoInsoluto = saldoInsoluto - amortizacion
					//CALCULA LA FECHA DE PAGO
					fechaPago = fechaPago + diasPeriodicidadPago
				}
				
				//INTRODUCE LOS REGISTROS A LA TABLA AMORTIZACION
				TablaAmortizacionRegistro tablaAmortizacionInsertada = new TablaAmortizacionRegistro(
						numeroPago:				numeroPago,
						fecha : 				fechaPago,
						impSaldoInicial: 		saldoInicial,
						tasaInteres: 			tasa,
						impInteres: 			pagoIntereses,
						impIvaInteres: 			importeIvaInteres,
						impCapital: 			amortizacion,
						impPago: 				cuotaTotal,
						impSaldoFinal: 			saldoInsoluto,
						pagoPuntual: 			false,
						impInteresPagado: 		0,
						impIvaInteresPagado: 	0,
						impCapitalPagado: 		0,
						impPagoPagado: 			0,
						pagado: 				false,
						listaCobro:    			listaCobroConsecutivo,
						prestamo:               prestamoInstance
						).save(flush: true,failOnError: true)

				//CALCULA EL SALDO RESTANTE DEL CAPITAL
				saldoInicial  = saldoInicial  - amortizacion
				
				//POR CADA ACCESORIO DEL PRESTAMO, SE INTRODUCIRAN LOS REGISTROS CORRESPONDIENTE A LA TABLA DE AMORTIZACION DE ACCESORIO 
				listaAccesorios.each() {
					
					//RECUPERA LOS DATOS DEL ACCESORIO 
					SimCatPeriodicidad periodicidadAccesorio = it.periodicidad
					SimCatFormaAplicacion formaAplicacion    = it.formaAplicacion
					SimCatAccesorio accesorio = it.accesorio
					SimCatUnidad    unidad    = it.unidad
					
					Integer    valorUnidad = unidad.valor
					
					BigDecimal valor  = it.valor
					BigDecimal importeAccesorio
					BigDecimal importeIvaAccesorio
					Integer	diasPeriodicidadAccesorio = periodicidadAccesorio.numeroDias
					
					//INTRODUCE LOS REGISTROS A LA TABLA AMORTIZACION ACCESORIO, SI LA FORMA DE APLICACION TIENE CLAVE DE MONTO_PRESTADO
					if (formaAplicacion.equals(SimCatFormaAplicacion.findByClaveFormaAplicacion('MONTO_PRESTADO'))) {
						importeAccesorio = (((prestamoInstance.montoSolicitado * valor) / valorUnidad) / diasPeriodicidadAccesorio) * diasPeriodicidadPago
						importeIvaAccesorio	= importeAccesorio * (iva/100)
						TablaAmortizacionAccesorio tablaAmortizacionAccesoriosInsertado = new TablaAmortizacionAccesorio(
								accesorio:                   accesorio,
								formaAplicacion : 			 formaAplicacion,
								numPago:					 numeroPago,
								importeAccesorio :			 importeAccesorio,
								importeIvaAccesorio : 		 importeIvaAccesorio,
								importeAccesorioPagado:	 	 0,
								importeIvaAccesorioPagado :  0,
								tablaAmortizacion:			 tablaAmortizacionInsertada
								).save(flush: true,failOnError: true)
					} else
					//INTRODUCE LOS REGISTROS A LA TABLA AMORTIZACION ACCESORIO, SI LA FORMA DE APLICACION TIENE CLAVE DE SALDOA_LAFECHA
					if (formaAplicacion.equals(SimCatFormaAplicacion.findByClaveFormaAplicacion('SALDOA_LAFECHA'))) {
						importeAccesorio = (((saldoInicial * valor) / valorUnidad) / diasPeriodicidadAccesorio) * diasPeriodicidadPago
						importeIvaAccesorio	= importeAccesorio * (iva/100)
						TablaAmortizacionAccesorio tablaAmortizacionAccesoriosInsertado = new TablaAmortizacionAccesorio(
								accesorio:                   accesorio,
								formaAplicacion : 			 formaAplicacion,
								numPago:					 numeroPago,
								importeAccesorio :			 importeAccesorio,
								importeIvaAccesorio : 		 importeIvaAccesorio,
								importeAccesorioPagado:	 	 0,
								importeIvaAccesorioPagado :  0,
								tablaAmortizacion:			 tablaAmortizacionInsertada
								).save(flush: true,failOnError: true)
					} else
					//INTRODUCE LOS REGISTROS A LA TABLA AMORTIZACION ACCESORIO, SI LA FORMA DE APLICACION TIENE CLAVE DE CARGO_FIJO
					if (formaAplicacion.equals(SimCatFormaAplicacion.findByClaveFormaAplicacion('CARGO_FIJO'))) {
						importeAccesorio = (((1 * valor) / 1) / diasPeriodicidadAccesorio) * diasPeriodicidadPago
						importeIvaAccesorio	= importeAccesorio * (iva/100)
						TablaAmortizacionAccesorio tablaAmortizacionAccesoriosInsertado = new TablaAmortizacionAccesorio(
								accesorio:                   accesorio,
								formaAplicacion : 			 formaAplicacion,
								numPago:					 numeroPago,
								importeAccesorio :			 importeAccesorio,
								importeIvaAccesorio : 		 importeIvaAccesorio,
								importeAccesorioPagado:	 	 0,
								importeIvaAccesorioPagado :  0,
								tablaAmortizacion:			 tablaAmortizacionInsertada
								).save(flush: true,failOnError: true)
					}
				}
				numeroPago++
			}
		}
		else {
			log.info("No se genera la tabla ya que existen pagos al credito.")
			throw new TablaAmortizacionServiceException(mensaje: "No se genero la Tabla de Amortizacion ya que existen pagos al credito")
		}

		return true
	}
}