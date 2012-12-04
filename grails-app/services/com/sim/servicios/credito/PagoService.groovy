package com.sim.servicios.credito

import com.sim.pfin.*
import com.sim.credito.*
import com.sim.tablaAmortizacion.*
import com.sim.catalogo.SimCatTipoAccesorio
import com.sim.catalogo.SimCatAccesorio
import com.sim.catalogo.SimCatFormaAplicacion
import com.sim.producto.ProPromocionAccesorio
import com.sim.usuario.Usuario

import org.hibernate.collection.PersistentSet

class PagoServiceException extends RuntimeException {
	String mensaje
	PrestamoPago prestamoPagoInstance
}

class PagoService {

	static transactional = true

	//SERVICIO PARA RECUPERAR EL USUARIO
	def springSecurityService
	//SERVICIO DEL CORE FINANCIERO
	def procesadorFinancieroService

	PfinMovimiento guardarPago (PrestamoPago prestamoPagoInstance){
		//VALIDA SI EL PRESTAMO TIENE PAGOS GUARDADOS PREVIOS
		def criteriaNumeroPreMovimientos = PfinMovimiento.createCriteria()
		Integer numeroPreMovimientos = criteriaNumeroPreMovimientos.count() {
			and {
				eq("prestamo",prestamoPagoInstance.prestamo)
				eq("situacionMovimiento", SituacionPremovimiento.PROCESADO_VIRTUAL)
				eq("operacion", PfinCatOperacion.findByClaveOperacion('TEDEPEFE'))
			}
		}

		if (numeroPreMovimientos>0){
			//EXISTEN PAGOS GUARDADOS
			throw new PagoServiceException(mensaje: "Existen pagos guardados, debe de cancelar o aplicar los pagos previos guardados", prestamoPagoInstance:prestamoPagoInstance )
		}

		//ALMACENA EL REGISTRO DE PAGO
        if (!prestamoPagoInstance.save(flush: true)) {
            throw new PagoServiceException(mensaje: "No se guardo el registro de Pago", prestamoPagoInstance:prestamoPagoInstance )
        }

		//OBTIENE EL REGISTRO ACTUAL
		Usuario usuario = springSecurityService.getCurrentUser()
		if (!usuario){
			throw new PagoServiceException(mensaje: "No se encontro usuario registrado", prestamoPagoInstance:prestamoPagoInstance )
		}

		//SE OBTIENE LA FECHA DEL MEDIO
		//FECHA_MEDIO = FECHA_SISTEMA = FECHA_LIQUIDACION
		PfinCatParametro parametros = PfinCatParametro.findByClaveMedio("SistemaMtn")
		Date fechaMedio = parametros?.fechaMedio
		if (!fechaMedio){
			throw new PagoServiceException(mensaje: "No existe la fecha del medio", prestamoPagoInstance:prestamoPagoInstance )
		}

		//FECHA DE APLICACION
		Date fechaAplicacion = prestamoPagoInstance.fechaPago

		//OBTIENE LA CUENTA EJE DEL CLIENTE
		PfinCuenta cuentaCliente = PfinCuenta.findByTipoCuentaAndCliente("EJE",prestamoPagoInstance.prestamo.cliente)

		//VERIFICA SI EXISTE LA CUENTA EJE DEL CLIENTE
		if (!cuentaCliente){
			throw new PagoServiceException(mensaje: "No existe la cuenta del Cliente", prestamoPagoInstance:prestamoPagoInstance )
		}

		//ASIGNA VALORES AL PREMOVIMIENTO
		PfinPreMovimiento preMovimientoInsertado = new PfinPreMovimiento(
				cuenta:  					cuentaCliente,
				divisa: 					PfinDivisa.findByClaveDivisa('MXP'),
				fechaOperacion: 			fechaMedio, //FECHA DEL MEDIO
				fechaLiquidacion: 			fechaMedio, //FECHA DEL MEDIO
				importeNeto: 				prestamoPagoInstance.importePago,
				//referencia NO SE DEFINE AL CREAR EL PREMOVIMIENTO
				prestamo : 					prestamoPagoInstance.prestamo,
				nota : 						"Deposito de efectivo",
				situacionPreMovimiento : 	SituacionPremovimiento.NO_PROCESADO,
				fechaRegistro: 				new Date(),
				logIpDireccion: 			'127.0.0.1',
				logUsuario: 				'127.0.0.1',
				logHost: 					'127.0.0.1',
				usuario : 					usuario,
				fechaAplicacion: 			prestamoPagoInstance.fechaPago,
				numeroPagoAmortizacion:  	0,
				operacion: 					PfinCatOperacion.findByClaveOperacion('TEDEPEFE'))
				//TEDEPEFE: DEPOSITO DE EFECTIVO A LA CUENTA DEL CLIENTE
		try{
			// GENERA EL PREMOVIMIENTO
			preMovimientoInsertado = procesadorFinancieroService.generaPreMovimiento(preMovimientoInsertado)

		}catch(ProcesadorFinancieroServiceException errorProcesadorFinanciero){
			throw errorProcesadorFinanciero
		}

		PfinMovimiento movimiento
		try{
			// GENERA EL MOVIMIENTO
			movimiento = procesadorFinancieroService.procesaMovimiento(preMovimientoInsertado,
					SituacionPremovimiento.PROCESADO_VIRTUAL, usuario, fechaAplicacion)
		}catch(ProcesadorFinancieroServiceException errorProcesadorFinanciero){
			throw errorProcesadorFinanciero
		}catch(Exception errorGenerarMovimiento){
			log.error(errorGenerarMovimiento)
			throw new PagoServiceException(mensaje: "No se genero el movimiento", prestamoPagoInstance:prestamoPagoInstance )
		}
	}

	Boolean cancelaPagoGuardado (PrestamoPago prestamoPagoInstance){

		def criteriaPreMovimientoGuardado = PfinPreMovimiento.createCriteria()
		PfinPreMovimiento preMovimientoGuardado  = criteriaPreMovimientoGuardado.get() {
			and {
				eq("prestamo",prestamoPagoInstance.prestamo)
				eq("situacionPreMovimiento", SituacionPremovimiento.PROCESADO_VIRTUAL)
				//TEDEPEFE = DEPOSITO DE EFECTIVO
				eq("operacion", PfinCatOperacion.findByClaveOperacion('TEDEPEFE'))
			}
		}

		if(!preMovimientoGuardado){
			throw new PagoServiceException(mensaje: "No existe el Premovimiento a Cancelar", prestamoPagoInstance:prestamoPagoInstance )
		}
		//FECHA DE APLICACION
		Date fechaAplicacion = prestamoPagoInstance.fechaPago
		//SE OBTIENE EL USUARIO ACTUAL
		Usuario usuario = springSecurityService.getCurrentUser()
		if (!usuario){
			throw new PagoServiceException(mensaje: "No se encontro usuario registrado", prestamoPagoInstance:prestamoPagoInstance )
		}
		try{
			// CANCELA EL PREMOVIMIENTO
			procesadorFinancieroService.procesaMovimiento(
				preMovimientoGuardado,
				SituacionPremovimiento.CANCELADO,
				usuario,
				fechaAplicacion)

		}catch(ProcesadorFinancieroServiceException errorProcesadorFinanciero){
			throw errorProcesadorFinanciero
		}		
	}	

	Boolean aplicarPago(PrestamoPago prestamoPagoInstance){

		//Valida que la fecha valor del pago no sea menor a un pago previo
		def criteriaPfinMovimiento = PfinMovimiento.createCriteria()
		Integer cuentaMovimientos = criteriaPfinMovimiento.count(){
			and {
				eq("prestamo",prestamoPagoInstance.prestamo)
		        ne("situacionMovimiento", SituacionPremovimiento.CANCELADO)
		        eq("cancelaTransaccion",0)
		        gt("fechaAplicacion", prestamoPagoInstance.fechaPago)
		    }
		}		
		if (cuentaMovimientos > 0){
			throw new PagoServiceException(mensaje: "Existen movimientos con fecha valor posterior a este movimiento", prestamoPagoInstance:prestamoPagoInstance )		
		}

		//Se obtiene la Fecha Valor
		Date fechaValor = prestamoPagoInstance.fechaPago

		//Se obtiene la Fecha del Medio
		PfinCatParametro parametros = PfinCatParametro.findByClaveMedio("SistemaMtn")
		Date fechaSistema = parametros?.fechaMedio
		if (!fechaSistema){
			throw new PagoServiceException(mensaje: "No se encuentra la fecha del medio del sistema", prestamoPagoInstance:prestamoPagoInstance )
		}

		//Valida que la fecha valor no sea mayor a la fecha del Medio
		if (fechaValor > fechaSistema) {
			throw new PagoServiceException(mensaje: "Operación no realizada, la fecha de Aplicación es mayor a la fecha del medio del sistema", prestamoPagoInstance:prestamoPagoInstance )
		}

		//Se obtiene la cuenta Eje del Cliente
		PfinCuenta cuentaEje = PfinCuenta.findWhere(tipoCuenta: "EJE", cliente: prestamoPagoInstance.prestamo.cliente)
		if (!cuentaEje){
			throw new PagoServiceException(mensaje: "No se encontro la cuenta eje del Cliente", prestamoPagoInstance:prestamoPagoInstance )
		}

		//Recuperar o almacenar el pago Guardado
		def criteriaMovimientoGuardado = PfinMovimiento.createCriteria()
		PfinMovimiento movimientoGuardado  = criteriaMovimientoGuardado.get() {
			and {
				eq("prestamo",prestamoPagoInstance.prestamo)
				eq("situacionMovimiento", SituacionPremovimiento.PROCESADO_VIRTUAL)
				//TEDEPEFE = DEPOSITO DE EFECTIVO
				eq("operacion", PfinCatOperacion.findByClaveOperacion('TEDEPEFE'))
			}
		}

		if(!movimientoGuardado){
			log.info ("No Existe el movimiento PROCESADO_VIRTUAL")
			movimientoGuardado = guardarPago(prestamoPagoInstance)
		}		
		//EL MOVIMIENTO CAMBIARA A PROCESADO REAL PARA INDICAR QUE YA NO ES UN PAGO GUARDADO
		//Y QUE HA SIDO APLICADO 
		movimientoGuardado.situacionMovimiento = SituacionPremovimiento.PROCESADO_REAL
		movimientoGuardado.save(flush:true)

		//Se obtienen las amortizaciones pendientes de pago
		def criteriaTablaAmortizacionRegistro = TablaAmortizacionRegistro.createCriteria()
		ArrayList listaAmortizacionPendiente  = criteriaTablaAmortizacionRegistro.list() {
			and {
				eq("prestamo",prestamoPagoInstance.prestamo)
				eq("pagado", false)
				order("numeroPago")
			}
		}

		//Ejecuta el pago de cada amortizacion pendiente siempre y 
		//cuando el cliente tenga saldo en su cuenta
		listaAmortizacionPendiente.each(){
			//Obtiene el importe de la cuenta eje del cliente
			PfinSaldo obtieneSaldo = PfinSaldo.findWhere(fechaFoto: fechaSistema, cuenta: cuentaEje)
			BigDecimal importeSaldo  = obtieneSaldo.saldo
			//SI EL CLIENTE TIENE SALDO EN SU CUENTA EJE APLICA PAGOS POR AMORTIZACION.
			if (importeSaldo > 0){
				AplicaPagoCreditoPorAmort(prestamoPagoInstance,it,importeSaldo,cuentaEje,fechaSistema)
			}
		}
	}

	Boolean AplicaPagoCreditoPorAmort (PrestamoPago prestamoPago,
		TablaAmortizacionRegistro tablaAmortizacionRegistro,
		BigDecimal importeSaldo,
		PfinCuenta cuentaCliente,
		Date fechaMedio) {

		Prestamo prestamoInstance = prestamoPago.prestamo
		Integer amortizacionPago = tablaAmortizacionRegistro.numeroPago
		BigDecimal importeNeto = 0
		
		//IMPLEMENTACION VISTA DE PRELACION DE PAGOS

		//SE OBTIENEN TODOS LOS ACCESORIOS DE LA PROMOCION 
		//QUE NO SEAN CARGOS INICIALES
		ArrayList listaAccesoriosPromocion = ProPromocionAccesorio.findAllByProPromocionAndFormaAplicacionNotEqual(
		 prestamoInstance.promocion,
		 SimCatFormaAplicacion.findByClaveFormaAplicacion('CARGO_INICIAL'))

		//ARREGLO PARA GUARDAR LOS OBJETOS PrelacionPagoConcepto
		//PrelacionPagoConcepto ES UNA CLASE GROOVY CREADA EN EL PROYECTO
		ArrayList listaPrelacionPagoConcepto = []

		//SE OBTIENEN LOS ACCESORIOS DE LA AMORTIZACION CORRESPONDIENTE
		PersistentSet listaAccesoriosAmortizacion = tablaAmortizacionRegistro.tablaAmortizacionAccesorio

		listaAccesoriosPromocion.each(){ 
			//SE ITERAN LOS ACCESORIOS DE LA PROMOCION
			//SE INSTANCEA LA CLASE prelacionPago
			PrelacionPagoConcepto prelacionPago = new PrelacionPagoConcepto()
			prelacionPago.numeroAmortizacion = tablaAmortizacionRegistro.numeroPago
			prelacionPago.ordenPago = it.orden
			prelacionPago.concepto = it.accesorio.concepto

			SimCatTipoAccesorio tipoAccesorio = it.accesorio.tipoAccesorio
			PfinCatConcepto     conceptoPrestamo = it.accesorio.concepto
			SimCatAccesorio     accesorio = it.accesorio

			//SE VALIDA SI EL TIPO DE ACCESORIO ES FIJO
			if (tipoAccesorio.equals(SimCatTipoAccesorio.findByClaveTipoAccesorio('FIJO'))){
				if (conceptoPrestamo.equals(PfinCatConcepto.findByClaveConcepto('INTERES'))) {
					//CONCEPTO ES IGUAL A INTERES
			        BigDecimal importeInteres = tablaAmortizacionRegistro.impInteres - tablaAmortizacionRegistro.impInteresPagado
			        prelacionPago.cantidadPagar = importeInteres
				}else{
					//CONCEPTO ES IGUAL A IVA DE INTERES
			        BigDecimal importeIvaInteres = tablaAmortizacionRegistro.impIvaInteres - tablaAmortizacionRegistro.impIvaInteresPagado
			        prelacionPago.cantidadPagar = importeIvaInteres
				}
			}else{
				//EL TIPO DE ACCESORIO NO ES FIJO
				listaAccesoriosAmortizacion.each(){ tablaAmortizacionAccesorio ->
					if (tablaAmortizacionAccesorio.accesorio.equals(accesorio)){
						BigDecimal importeAccesorio = tablaAmortizacionAccesorio.importeAccesorio - tablaAmortizacionAccesorio.importeAccesorioPagado
						prelacionPago.cantidadPagar = importeAccesorio
					}
				}
			}
			listaPrelacionPagoConcepto.add(prelacionPago)
		}

		//SE INSERTA EL CAPITAL
		PrelacionPagoConcepto prelacionPagoCapital = new PrelacionPagoConcepto(
			tablaAmortizacionRegistro.numeroPago,
			99,
			tablaAmortizacionRegistro.impCapital - tablaAmortizacionRegistro.impCapitalPagado,
			PfinCatConcepto.findByClaveConcepto('CAPITAL'))

		listaPrelacionPagoConcepto.add(prelacionPagoCapital)

		//OBTIENE EL REGISTRO ACTUAL
		Usuario usuario = springSecurityService.getCurrentUser()
		if (!usuario){
			throw new PagoServiceException(mensaje: "No se encontro usuario registrado", prestamoPagoInstance:prestamoPago)
		}

		//SE GENERAL EL PREMOVIMIENTO
		PfinPreMovimiento preMovimientoInsertado = new PfinPreMovimiento(
				cuenta:  					cuentaCliente,
				divisa: 					PfinDivisa.findByClaveDivisa('MXP'),
				fechaOperacion: 			fechaMedio, //FECHA DEL MEDIO
				fechaLiquidacion: 			fechaMedio, //FECHA DEL MEDIO
				importeNeto: 				0,
				//referencia NO SE DEFINE AL CREAR EL PREMOVIMIENTO
				prestamo : 					prestamoInstance,
				nota : 						"PagoDePrestamo",
				situacionPreMovimiento : 	SituacionPremovimiento.NO_PROCESADO,
				fechaRegistro: 				new Date(),
				logIpDireccion: 			'127.0.0.1',
				logUsuario: 				'127.0.0.1',
				logHost: 					'127.0.0.1',
				usuario : 					usuario,
				fechaAplicacion: 			prestamoPago.fechaPago,
				numeroPagoAmortizacion:  	amortizacionPago,
				operacion: 					PfinCatOperacion.findByClaveOperacion('CRPAGOPRES'))
				//CRPAGOPRES: PAGO DE PRESTAMO
		try{
			// GENERA EL PREMOVIMIENTO
			preMovimientoInsertado = procesadorFinancieroService.generaPreMovimiento(preMovimientoInsertado)

		}catch(ProcesadorFinancieroServiceException errorProcesadorFinanciero){
			throw errorProcesadorFinanciero
		}

		//ARREGLO PARA ALMACENAR LOS MOVIMIENTOS DETALLE
		ArrayList listaMovimientoDet = []
		//ITERA TODOS LOS CONCEPTOS A PAGAR DEL PRESTAMO
		listaPrelacionPagoConcepto.each(){
			log.info "******************"
			log.info "Numero Amortizacion: "+it.numeroAmortizacion
			log.info "Orden Pago: "+it.ordenPago
			log.info "Concepto: " +it.concepto
			log.info "Cantidad:"+it.cantidadPagar

			BigDecimal importeConcepto

			//VERIFICA SI EXISTE SALDO PARA PAGAR EL CONCEPTO CORRESPONDIENTE
			if (importeSaldo>0){
				//Si el saldo es mayor al importe del concepto, cubre todo el concepto, de lo contrario solo lo que le alcanza
				if (it.cantidadPagar > importeSaldo){
					//Si ya no tiene saldo para el pago del concepto no paga completo
					importeConcepto = importeSaldo
				}else{
					importeConcepto = it.cantidadPagar
				}

				//SE DEFINE EL PREMOVIMIENTO DETALLE
				PfinPreMovimientoDet preMovimientoDetInsertado
				try{
					// GENERA EL PREMOVIMIENTO DETALLE
					preMovimientoDetInsertado = procesadorFinancieroService.generaPreMovimientoDet(
						preMovimientoInsertado,
						it.concepto,
						importeConcepto,
						it.concepto.descripcionCorta)
				}catch(ProcesadorFinancieroServiceException errorProcesadorFinanciero){
					throw errorProcesadorFinanciero
				}

				listaMovimientoDet.add(preMovimientoDetInsertado)
              	//Se actualiza el importe neto y el saldo del cliente
               	importeNeto = importeNeto  + importeConcepto
               	importeSaldo = importeSaldo - importeConcepto
			}
		}

		preMovimientoInsertado.importeNeto = importeNeto
		preMovimientoInsertado.save(flush:true)	

		PfinMovimiento movimiento
		try{
			// GENERA EL MOVIMIENTO
			movimiento = procesadorFinancieroService.procesaMovimiento(preMovimientoInsertado,
					SituacionPremovimiento.PROCESADO_VIRTUAL, usuario, prestamoPago.fechaPago)
		}catch(ProcesadorFinancieroServiceException errorProcesadorFinanciero){
			throw errorProcesadorFinanciero
		}catch(Exception errorGenerarMovimiento){
			log.error(errorGenerarMovimiento)
			throw new PagoServiceException(mensaje: "No se genero el movimiento", prestamoPagoInstance:prestamoPagoInstance )
		}

		//Se actualiza el identificador del movimiento en el dominio preMovimiento
		preMovimientoInsertado.pfinMovimiento = movimiento
		preMovimientoInsertado.save(flush:true)	

		actualizaTablaAmortizacion(movimiento,listaMovimientoDet)
	}

	Boolean actualizaTablaAmortizacion (PfinMovimiento movimiento, ArrayList listaMovimientoDet ){

		TablaAmortizacionRegistro tablaAmortizacionActual = TablaAmortizacionRegistro.findByPrestamoAndNumeroPago(movimiento.prestamo, movimiento.numeroPagoAmortizacion) 

		listaMovimientoDet.each(){ listMovDet ->

			switch ( listMovDet.concepto ) {
			    case PfinCatConcepto.findByClaveConcepto('CAPITAL'):
			        tablaAmortizacionActual.impCapitalPagado = tablaAmortizacionActual.impCapitalPagado	+ listMovDet.importeConcepto
			        //EL CAPITAL ES EL ULTIMO CONCEPTO A PAGAR
			        //VALIDA SI PAGO TODO EL CAPITAL
			        if (tablaAmortizacionActual.impCapitalPagado>=tablaAmortizacionActual.impCapital){
			        	//SE CUBRIO TODA LA AMORTIZACION
			        	tablaAmortizacionActual.pagado = true
			        }
			        break
			    case PfinCatConcepto.findByClaveConcepto('INTERES'):
			        tablaAmortizacionActual.impInteresPagado = tablaAmortizacionActual.impInteresPagado + listMovDet.importeConcepto
			        break
			    case PfinCatConcepto.findByClaveConcepto('IVAINT'):
			        tablaAmortizacionActual.impIvaInteresPagado = tablaAmortizacionActual.impIvaInteresPagado + listMovDet.importeConcepto
			        break
			    default:
			    	//SE TIENE QUE BUSCAR POR CADA MOVIMIENTO DETALLE LA TABLA
			    	//AMORTIZACION ACCESORIO QUE PAGO
			    	tablaAmortizacionActual.tablaAmortizacionAccesorio.each{ tabAmorAcc ->
			    		if (listMovDet.concepto.equals(tabAmorAcc.accesorio.concepto)){
			    			tabAmorAcc.importeAccesorioPagado = tabAmorAcc.importeAccesorioPagado + listMovDet.importeConcepto
			    			tabAmorAcc.save(flush:true)
			    		}
			    	}
			}
			//INCREMETA LO PAGADO EN LA AMORTIZACION
			tablaAmortizacionActual.impPagoPagado = tablaAmortizacionActual.impPagoPagado + listMovDet.importeConcepto
			tablaAmortizacionActual.save(flush:true)
		}
	}

	//METODO DE EJEMPLO TOMADO DEL SIM CREDICONFIA
	//EJEMPLO QUE NOS SIRVIO PARA DESARROLLAR EL CORE FINANCIERO
	Boolean aplicaPagoIndividual(PrestamoPago prestamoPagoInstance) {

		//Se obtiene el usuario actual
		Usuario usuario = springSecurityService.getCurrentUser()

		//SE OBTIENE LA FECHA DEL MEDIO
		//FECHA_MEDIO = FECHA_SISTEMA = FECHA_LIQUIDACION
		PfinCatParametro parametros = PfinCatParametro.findByClaveMedio("SistemaMtn")
		Date fechaMedio = parametros?.fechaMedio
		if (!fechaMedio){
			throw new PagoServiceException(mensaje: "No existe la fecha del medio", prestamoPagoInstance:prestamoPagoInstance )
		}

		//FECHA DE APLICACION
		Date fechaAplicacion = prestamoPagoInstance.fechaPago

		//OBTIENE LA CUENTA DEL CLIENTE
		PfinCuenta cuentaCliente = PfinCuenta.findByTipoCuentaAndCliente("EJE",prestamoPagoInstance.prestamo.cliente)

		//VERIFICA SI EXISTE LA CUENTA DEL CLIENTE
		if (!cuentaCliente){
			throw new PagoServiceException(mensaje: "No existe la cuenta del Cliente", prestamoPagoInstance:prestamoPagoInstance )
		}

		//ASIGNA VALORES AL PREMOVIMIENTO
		PfinPreMovimiento preMovimientoInsertado = new PfinPreMovimiento(cuenta:  cuentaCliente,
				divisa: PfinDivisa.findByClaveDivisa('MXP'),
				fechaOperacion:fechaMedio, //FECHA DEL MEDIO
				fechaLiquidacion:fechaMedio, //FECHA DEL MEDIO
				importeNeto: prestamoPagoInstance.importePago,
				//referencia NO SE DEFINE AL CREAR EL PREMOVIMIENTO
				prestamo : prestamoPagoInstance.prestamo,
				nota : "Deposito de efectivo",
				//pfinMovimiento()
				situacionPreMovimiento : SituacionPremovimiento.NO_PROCESADO,
				fechaRegistro:new Date(),
				logIpDireccion: 'xxxxxxxxx',
				logUsuario:'xxxxxxxxxx',
				logHost:'xxxxxxxxxx',
				usuario : usuario,
				fechaAplicacion:prestamoPagoInstance.fechaPago,
				numeroPagoAmortizacion: 0,
				operacion: PfinCatOperacion.findByClaveOperacion('TEDEPEFE'))
		try{
			// GENERA EL PREMOVIMIENTO
			preMovimientoInsertado = procesadorFinancieroService.generaPreMovimiento(preMovimientoInsertado)

			//GENERA LOS DETALLES DEL PREMOVIMIENTO
			PfinCatOperacion operacion = preMovimientoInsertado.operacion

			def listaConceptos = PfinCatOperacionConcepto.findAllByOperacion(operacion)

			listaConceptos.each() {
				PfinPreMovimientoDet preMovimientoDet = procesadorFinancieroService.generaPreMovimientoDet(preMovimientoInsertado, it.concepto, 100, "Si pasa!")
				preMovimientoInsertado.addToPfinPreMovimientoDet(preMovimientoDet)
			}
			
		}catch(ProcesadorFinancieroServiceException errorProcesadorFinanciero){
			throw errorProcesadorFinanciero
		}

		PfinMovimiento movimiento
		try{
			// GENERA EL MOVIMIENTO
			movimiento = procesadorFinancieroService.procesaMovimiento(preMovimientoInsertado,
					SituacionPremovimiento.PROCESADO_VIRTUAL, usuario, fechaAplicacion)
		}catch(ProcesadorFinancieroServiceException errorProcesadorFinanciero){
			throw errorProcesadorFinanciero
		}catch(Exception errorGenerarMovimiento){
			log.error(errorGenerarMovimiento)
			throw new PagoServiceException(mensaje: "No se genero el movimiento", prestamoPagoInstance:prestamoPagoInstance )
		}
		return true
	}
}