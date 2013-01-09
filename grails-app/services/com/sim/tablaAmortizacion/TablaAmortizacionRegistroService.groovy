package com.sim.tablaAmortizacion

import java.util.Date

import com.sim.credito.Prestamo
import com.sim.producto.ProPromocion
import com.sim.producto.ProPromocionAccesorio
import com.sim.listacobro.ListaCobro
import com.sim.entidad.EntDependencia

import com.sim.pfin.PfinMovimiento
import com.sim.pfin.PfinCatOperacion
import com.sim.pfin.SituacionPremovimiento
import com.sim.pfin.PfinCatParametro
import com.sim.pfin.PfinCatConcepto
import com.sim.pfin.PrelacionPagoConcepto

import com.sim.catalogo.SimCatAccesorio
import com.sim.catalogo.SimCatFormaAplicacion
import com.sim.catalogo.SimCatMetodoCalculo
import com.sim.catalogo.SimCatPeriodicidad
import com.sim.catalogo.SimCatUnidad
import com.sim.catalogo.SimCatTipoAccesorio
import com.sim.catalogo.SimCatListaCobroEstatus


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

		//SE RECUPERA LA FECHA EN QUE EL CLIENTE COBRO EL CREDITO
		Date fechaCobro = prestamoInstance.fechaCobro
		log.info ("Fecha de Cobro: "+fechaCobro)

		if (!fechaCobro){
			log.info("No se especifico la fecha de Cobro")
			throw new TablaAmortizacionServiceException(mensaje: "No se especifico la Fecha de Cobro")
		}

		//SE OBTIENE LA LISTA DE COBRO QUE SE ASIGNA A LA AMORTIZACION UNO
		ListaCobro primerPago = obtenerListaCobroPrimerPago(fechaCobro,prestamoInstance.dependencia)

		if (!primerPago){
			log.info("No se especifico la lista de cobro para la amortizacion uno")
			throw new TablaAmortizacionServiceException(mensaje: "No se especifico la lista de cobro para la amortizacion uno")
		}

		//SE OBTIENTEN LAS LISTAS DE COBRO QUE PERTENECEN A LA DEPENDENCIA
		ArrayList listasDeCobro = 
			ListaCobro.findAllByIdGreaterThanEqualsAndDependencia(primerPago.id,prestamoInstance.dependencia,[sort: "id", order: "asc"])

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

			//OBTIENE TODOS LOS REGISTROS DE LA TABLA DE AMORTIZACION QUE PERTENECEN AL PRESTAMO PARA ELIMINARLOS
			
			//org.hibernate.collection.PersistentSet registrosTabla = prestamoInstance.tablaAmortizacion
			ArrayList registrosTabla = TablaAmortizacionRegistro.findAllByPrestamo(prestamoInstance)
			
			if(registrosTabla){
				registrosTabla.each() {
					prestamoInstance.removeFromTablaAmortizacion(it)
				}
				prestamoInstance.save()
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
			
			//AHORA ASIGNAMOS LA FECHA DE COBRO COMO LA FECHA PARA LA AMORTIZACION UNO
			fechaPago = fechaCobro
			
			//SE VA A OBTENER LOS ACCESORIOS QUE APLIQUEN COMO CARGO INICIAL
			//RECUPERA LOS ACCESORIOS DEL PRESTAMO
			ArrayList listaAccesorios	= prestamoInstance.prestamoAccesorio
			
			//VERFICA SI EXISTEN ACCESORIOS QUE DEBEN SER CARGOS INICIALES
			listaAccesorios.each() {

				ProPromocionAccesorio promocionAccesorio = ProPromocionAccesorio.findByAccesorioAndProPromocion(it.accesorio,promocion)
				SimCatFormaAplicacion formaAplicacion = promocionAccesorio.formaAplicacion
				BigDecimal valorAccesorio  = it.valor

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
					//log.info("Pago de Interes: " + pagoIntereses)
					//CALCULA EL IVA DEL INTERES
					importeIvaInteres = pagoIntereses * (iva/100)
					//FORMULA PARA VER EL PAGO TOTAL
					cuotaTotal = amortizacion + pagoIntereses + importeIvaInteres
					//log.info("Cuota Total: " + cuotaTotal)
					//VARIABLES QUE GENERAN LOS PAGOS TOTALES
					pagoTotalInteres  = pagoIntereses * promocion.numeroDePagos
					pagoTotalPrestamo = capitalTotal + pagoTotalInteres
					saldoInsoluto = saldoInsoluto - amortizacion
					//CALCULA LA FECHA DE PAGO
					fechaPago = fechaPago + diasPeriodicidadPago
				} else if (promocion.metodoCalculo.equals(SimCatMetodoCalculo.findByClaveMetodoCalculo('METODO02'))) {
					//FORMULA PARA EL CALCULO DE LA AMORTIZACION
					amortizacion = capitalTotal / promocion.numeroDePagos
					//FORMULA PARA EL PAGO DE INTERES
					pagoIntereses = saldoInsoluto * tasa
					//log.info("Pago de Interes: " + pagoIntereses)
					//CALCULA EL IVA DEL INTERES
					importeIvaInteres = pagoIntereses * (iva/100)
					//FORMULA PARA VER EL PAGO TOTAL
					cuotaTotal = amortizacion + pagoIntereses + importeIvaInteres
					//log.info("Cuota Total: " + cuotaTotal)
					//VARIABLES QUE GENERAN LOS PAGOS TOTALES
					pagoTotalInteres  = pagoIntereses * promocion.numeroDePagos
					pagoTotalPrestamo = capitalTotal + pagoTotalInteres
					saldoInsoluto = saldoInsoluto - amortizacion
					//CALCULA LA FECHA DE PAGO
					fechaPago = fechaPago + diasPeriodicidadPago
				} else if (promocion.metodoCalculo.equals(SimCatMetodoCalculo.findByClaveMetodoCalculo('METODO05'))) {
					BigDecimal p = capitalTotal / ((1 - (1 / ((1 + tasa)**promocion.numeroDePagos))) / tasa)
					//FORMULA PARA EL PAGO DE INTERES
					pagoIntereses = saldoInsoluto * tasa
					//log.info("Pago de Interes: " + pagoIntereses)
					//FORMULA PARA EL CALCULO DE LA AMORTIZACION
					amortizacion = p - pagoIntereses
					//CALCULA EL IVA DEL INTERES
					importeIvaInteres = pagoIntereses * (iva/100)
					//FORMULA PARA VER EL PAGO TOTAL
					cuotaTotal = p + importeIvaInteres
					//log.info("Cuota Total: " + cuotaTotal)
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
						).save()

				//CALCULA EL SALDO RESTANTE DEL CAPITAL
				saldoInicial  = saldoInicial  - amortizacion
				
				//POR CADA ACCESORIO DEL PRESTAMO, SE INTRODUCIRAN LOS REGISTROS CORRESPONDIENTE A LA TABLA DE AMORTIZACION DE ACCESORIO 
				listaAccesorios.each() {
					
					//RECUPERA LOS DATOS DEL ACCESORIO 
					SimCatPeriodicidad periodicidadAccesorio = it.periodicidad
					ProPromocionAccesorio promocionAccesorio = ProPromocionAccesorio.findByAccesorioAndProPromocion(it.accesorio,promocion)
					SimCatFormaAplicacion formaAplicacion = promocionAccesorio.formaAplicacion

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
								//ACTUALMENTE NO SE CALCULA EL IVA DEL ACCESORIO EN EL NEGOCIO DEL SIM
								importeIvaAccesorio : 		 0,//importeIvaAccesorio,
								importeAccesorioPagado:	 	 0,
								importeIvaAccesorioPagado :  0,
								tablaAmortizacion:			 tablaAmortizacionInsertada
								).save()
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
								importeIvaAccesorio : 		 0,//importeIvaAccesorio,
								importeAccesorioPagado:	 	 0,
								importeIvaAccesorioPagado :  0,
								tablaAmortizacion:			 tablaAmortizacionInsertada
								).save()
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
								importeIvaAccesorio : 		 0,//importeIvaAccesorio,
								importeAccesorioPagado:	 	 0,
								importeIvaAccesorioPagado :  0,
								tablaAmortizacion:			 tablaAmortizacionInsertada
								).save()
					}
					//POR CADA ACCESORIO SE MODIFICA EL TOTAL DE LO QUE HAY QUE PAGAR
					//POR AMORTIZACION
					tablaAmortizacionInsertada.impPago =  tablaAmortizacionInsertada.impPago + (importeAccesorio ?: 0)
					tablaAmortizacionInsertada.save()
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

	ListaCobro obtenerListaCobroPrimerPago(Date fechaCobro, EntDependencia dependencia){

		def criteriaListaCobro = ListaCobro.createCriteria()
		ListaCobro listaCobroPrimerPago  = criteriaListaCobro.get() {
			and {
				eq("dependencia",dependencia)
				le("fechaInicio", fechaCobro)
				ge("fechaFin",fechaCobro )
			}
		}
		
		//VALIDA SI LA LISTA DE COBRO HA SIDO GENERADA
		if (listaCobroPrimerPago.estatus.equals(
			SimCatListaCobroEstatus.findByClaveListaEstatus("NO_GENERADA"))){
			log.info ("La lista de cobro se encuentra con estatus: No generada")
			//SE ASIGNA A LA LISTA DE COBRO QUE SE OBTUVO
		}
		
		else{
			//LA LISTA DE COBRO YA NO SE ENCUENTRA SIN GENERAR
			//SE OBTIENEN LAS LISTAS DE COBRO MAYORES A LA LISTA
			//DE COBRO QUE DEBERIA DE CORRESPONDER
			def criteriaListasDeCobro = ListaCobro.createCriteria()
			ArrayList listasDeCobro  = criteriaListasDeCobro.list() {
				and {
					eq("dependencia",dependencia)
					gt("id", listaCobroPrimerPago.id)
					order("id", "desc")
				}
			}

			//BUSCA LA PRIMERA LISTA DE COBRO QUE SE ENCUENTRE CON
			//ESTATUS DE NO GENERADA
			listasDeCobro.each{
				Boolean listaEncontrada = false
				SimCatListaCobroEstatus estatusListaCiclo = it.estatus
				if (estatusListaCiclo.equals(
					SimCatListaCobroEstatus.findByClaveListaEstatus("NO_GENERADA"))&&
					listaEncontrada == false){
					listaCobroPrimerPago = it
					listaEncontrada = true
				}
			}
		}

		log.info ("ListaCobro: ${listaCobroPrimerPago}")

		return listaCobroPrimerPago
	}

}