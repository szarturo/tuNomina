package com.sim.tablaAmortizacion

import java.util.Date

import com.sim.credito.Prestamo
import com.sim.producto.ProPromocion
import com.sim.producto.ProPromocionAccesorio
import com.sim.listacobro.ListaCobro
import com.sim.listacobro.ListaCobroDetalle
import com.sim.entidad.EntDependencia

import com.sim.pfin.PfinMovimiento
import com.sim.pfin.PfinCatOperacion
import com.sim.pfin.SituacionPremovimiento
import com.sim.pfin.PfinCatParametro
import com.sim.pfin.PfinCatConcepto
import com.sim.pfin.PrelacionPagoConcepto
import com.sim.pfin.PfinCuenta

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

		if (!fechaCobro){
			log.info("No se especifico la fecha de Cobro")
			throw new TablaAmortizacionServiceException(mensaje: "No se especifico la Fecha de Cobro")
		}

		//INCREMENTA LA FECHA DE COBRO EN LOS DIAS DE LA PERIODICIDAD
		fechaCobro = fechaCobro + prestamoInstance.promocion.periodicidadPagos.numeroDias

		//SE OBTIENE LA LISTA DE COBRO QUE SE ASIGNA A LA AMORTIZACION UNO
		ListaCobro primerPago = obtenerListaCobroPrimerPago(fechaCobro,prestamoInstance.dependencia)

		if (!primerPago){
			log.info("No se especifico la lista de cobro para la amortizacion uno")
			throw new TablaAmortizacionServiceException(mensaje: "No se especifico la lista de cobro para la amortizacion uno")
		}

		//SE OBTIENTEN LAS LISTAS DE COBRO QUE PERTENECEN A LA DEPENDENCIA
		//MAYORES E IGUAL A LA QUE SE ASIGNO PARA EL PRIMER PAGO
		ArrayList listasDeCobro = 
			ListaCobro.findAllByIdGreaterThanEqualsAndDependencia(primerPago.id,prestamoInstance.dependencia,[sort: "id", order: "asc"])

		//SE CREA OBJETO ITERATOR PARA MANEJAR LAS LISTAS DE COBRO
 		Iterator iteratorListasCobro = listasDeCobro.iterator()

		//VALIDA SI EL PRESTAMO TIENE PAGOS GUARDADOS PREVIOS
		def criteriaNumeroMovimientos = PfinMovimiento.createCriteria()
		Integer numeroMovimientos = criteriaNumeroMovimientos.count() {
			and {
				eq("prestamo",prestamoInstance)
				eq("situacionMovimiento", SituacionPremovimiento.PROCESADO_REAL)
				isNull("cancelaTransaccion")
			}
		}

		log.info ("Movimientos Encontrados: "+numeroMovimientos)

		if (numeroMovimientos>0){
			log.info("No se genera la tabla ya que existen pagos al credito.")
			throw new TablaAmortizacionServiceException(mensaje: "No se genero la Tabla de Amortizacion ya que existen pagos al credito")
		}

		//CUENTA LOS DETALLES DE LAS LISTAS DE COBRO QUE PERTENEZCAN AL PRESTAMO
		def criteriaListasCobroDetalle = ListaCobroDetalle.createCriteria()
		Integer numeroCobroDetalles  = criteriaListasCobroDetalle.count() {
			amortizacion{
				eq("prestamo",prestamoInstance)
			}
		}

		if (numeroCobroDetalles>0){
			log.info("El prestamo ha sido asignado a una Lista de Cobro Detalle: ${numeroCobroDetalles}")
			throw new TablaAmortizacionServiceException(mensaje: "El prestamo ha sido asignado a una Lista de Cobro Detalle")
		}

		//VALIDA SI EXISTE LA CUENTA EJE DEL CLIENTE
		PfinCuenta cuentaCliente = PfinCuenta.findByTipoCuentaAndCliente("EJE",prestamoInstance.cliente)
		if (!cuentaCliente){
			//SE CREA LA CUENTA EJE DEL CLIENTE
			cuentaCliente = new PfinCuenta(tipoCuenta:  'EJE',
				situacion: 'ACTIVO',
				cliente: prestamoInstance.cliente,
			).save()
		}
		if (!cuentaCliente){
			throw new TablaAmortizacionServiceException(mensaje: "No se pudo generar la cuenta Eje del Cliente")	
		}

		//OBTIENE TODOS LOS REGISTROS DE LA TABLA DE AMORTIZACION QUE PERTENECEN AL PRESTAMO PARA ELIMINARLOS
		//org.hibernate.collection.PersistentSet registrosTabla = prestamoInstance.tablaAmortizacion NO FUNCIONO
		ArrayList registrosTabla = TablaAmortizacionRegistro.findAllByPrestamo(prestamoInstance)
		
		registrosTabla.each() {
			prestamoInstance.removeFromTablaAmortizacion(it)
		}
		prestamoInstance.save()

		//SE OBTIENEN LOS DATOS DEL PRESTAMO
		ProPromocion promocion = prestamoInstance.promocion
		SimCatMetodoCalculo metodoCalculo 	 = promocion.metodoCalculo
		SimCatPeriodicidad  periodicidadTasa = promocion.periodicidadTasa
		SimCatPeriodicidad  periodicidadPago = promocion.periodicidadPagos
		
		Integer diasPeriodicidadTasa 	 = periodicidadTasa.numeroDias
		Integer diasPeriodicidadPago 	 = periodicidadPago.numeroDias
		Integer iva 	 				 = promocion.iva
					
		BigDecimal importeIvaInteres
		BigDecimal cargoInicial			 = prestamoInstance.montoAutorizado
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

		Integer accesoriosPromocion = 0
		Integer accesoriosPrestamo = 0

		//SE RESTAN 2 ACCESORIOS A LA PROMOCION YA QUE NO SE CONTEMPLA EL IVA Y SU INTERES
		accesoriosPromocion = prestamoInstance?.promocion?.proPromocionAccesorio?.size() - 2
		accesoriosPrestamo = prestamoInstance?.prestamoAccesorio?.size()

		log.info ("PrestamoAccesorios: ${accesoriosPrestamo}")
		log.info ("PromocionAccesorio: ${accesoriosPromocion}")

		//VALIDA QUE LA LISTA DE ACCESORIOS SEA IGUAL A LA LISTA DE ACCESORIOS DE LA PROMOCION
		if (accesoriosPromocion!=accesoriosPrestamo){
			throw new TablaAmortizacionServiceException(mensaje: "No se han definido los accesorios del prestamo que se indicaron en la promoción")	
		}

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

			//SE VALIDA QUE EL ACCESORIO DEL PRESTAMO TENGA IMPORTE
			if (valorAccesorio <= 0){
				throw new TablaAmortizacionServiceException(mensaje: "No se definio importe para el accesorio: ${it.accesorio} ")	
			}

		}
		
		//ASIGNA EL CARGO INICIAL CALCULADO A LA VARIALBLE capitalTotal PARA LOS CALCULOS POSTERIORES
		capitalTotal = cargoInicial
		
		BigDecimal saldoInicial			 = capitalTotal
		BigDecimal saldoInsoluto         = capitalTotal

		//EN LAS SIGUIENTES VARIABLES NO IMPORTA EL METODO DE CALCULO
			
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
			ListaCobro listaCobroConsecutivo = (ListaCobro)iteratorListasCobro.next()

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
					importeAccesorio = (((prestamoInstance.montoAutorizado * valor) / valorUnidad) / diasPeriodicidadAccesorio) * diasPeriodicidadPago
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

		return true
	}

	ListaCobro obtenerListaCobroPrimerPago(Date fechaCobro, EntDependencia dependencia){

		//SE OBTIENE LA LISTA DE COBRO QUE CORRESPONDE A LA FECHA DE COBRO
		def criteriaListaCobro = ListaCobro.createCriteria()
		ListaCobro listaCobroPrimerPago  = criteriaListaCobro.get() {
			and {
				eq("dependencia",dependencia)
				le("fechaInicio", fechaCobro)
				ge("fechaFin",fechaCobro )
			}
		}
		
		//VALIDA SI LA LISTA DE COBRO NO HA SIDO GENERADA
		if (listaCobroPrimerPago.estatus.equals(
			SimCatListaCobroEstatus.findByClaveListaEstatus("NO_GENERADA"))){
			//SE ASIGNA LA LISTA DE COBRO QUE SE OBTUVO EN EL CRITERIA
		}else{
			//LA LISTA DE COBRO HA SIDO GENERADA
			//SE OBTIENEN LAS LISTAS DE COBRO POSTERIORES
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

		log.info ("ListaCobro para el primer pago: ${listaCobroPrimerPago}")

		return listaCobroPrimerPago
	}

}