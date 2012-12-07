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

class PagoServiceAplicaPagoException extends RuntimeException {
	String mensaje
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
			throw new PagoServiceException(mensaje: "Existen pagos guardados, debe de cancelar o aplicar los pagos previos guardados al Prestamo", prestamoPagoInstance:prestamoPagoInstance )
		}

		//ALMACENA EL REGISTRO DE PAGO
        if (!prestamoPagoInstance.save(flush: true)) {
            throw new PagoServiceException(mensaje: "No se guardo el registro de Pago", prestamoPagoInstance:prestamoPagoInstance )
        }

		//OBTIENE EL USUARIO ACTUAL
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
			throw new PagoServiceException(mensaje: "No existe la cuenta Eje del Cliente", prestamoPagoInstance:prestamoPagoInstance )
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
				nota : 						"Deposito de efectivo a la Cuenta",
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
			// SE ASIGNA LA RELACION PRESTAMO PAGO Y MOVIMIENTO
			movimiento.prestamoPago = prestamoPagoInstance
			movimiento.save(flush:true)			
		}catch(ProcesadorFinancieroServiceException errorProcesadorFinanciero){
			throw errorProcesadorFinanciero
		}catch(Exception errorGenerarMovimiento){
			log.error(errorGenerarMovimiento)
			throw new PagoServiceException(mensaje: "No se genero el movimiento", prestamoPagoInstance:prestamoPagoInstance )
		}
	}

	Boolean cancelaPagoGuardado (PrestamoPago prestamoPagoInstance){
		//VALIDA SI EXISTE EL PREMOVIMIENTO PARA CANCELAR DE prestamoPagoInstance
		PfinPreMovimiento preMovimientoGuardado
		//ITERA LOS PfinMovimiento DEL PrestamoPago
		prestamoPagoInstance.pfinMovimiento.each{
			//VALIDA SI EXISTE EL PfinMovimiento CON OPERACION DEPOSITO DE EFECTIVO
			//Y PROCESADO VIRTUALMENTE
			if (it.operacion.equals(PfinCatOperacion.findByClaveOperacion('TEDEPEFE'))
				&& it.situacionMovimiento.equals(SituacionPremovimiento.PROCESADO_VIRTUAL)){
					//SI ENCUENTRA EL PfinMovimiento RECUPERA SU PfinPreMovimiento
					preMovimientoGuardado =  it.pfinPreMovimiento
			}
		}

		if(!preMovimientoGuardado){
			throw new PagoServiceException(mensaje: "No existe el Pago a Cancelar", prestamoPagoInstance:prestamoPagoInstance )
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

	Boolean aplicarPago(PrestamoPago prestamoPagoInstance, Boolean existePagoInstance){

		//VALIDA SI prestamoPagoInstance NO EXISTE
		//SI EXISTE NO HAY POSIBILIDAD QUE EXISTAN MAS PAGOS GUARDADOS, YA QUE SOLO DEBERIA
		//EXISTIR EL QUE QUE SE VA APLICAR
		if (!existePagoInstance){
			//prestamoPagoInstance NO EXISTE
			//HAY QUE VALIDAR SI EXISTEN PAGOS GUARDADOS DE OTROS PAGOS
			def criteriaNumeroMovimientos = PfinMovimiento.createCriteria()
			Integer numeroMovimientos = criteriaNumeroMovimientos.count() {
				and {
					eq("prestamo",prestamoPagoInstance.prestamo)
					eq("situacionMovimiento", SituacionPremovimiento.PROCESADO_VIRTUAL)
					eq("operacion", PfinCatOperacion.findByClaveOperacion('TEDEPEFE'))
				}
			}

			if (numeroMovimientos>0){
				//EXISTEN PAGOS GUARDADOS
				throw new PagoServiceAplicaPagoException(mensaje: "Existen pagos guardados, debe de cancelar o aplicar los pagos previos guardados")
			}			
		}

		//VALIDA SI EL PRESTAMO HA SIDO CANCELADO O APLICADO
		PfinMovimiento movimiento
		//ITERA LOS PfinMovimiento DEL PrestamoPago
		prestamoPagoInstance.pfinMovimiento.each{
			//VALIDA SI EXISTE UN PfinMovimiento APLICADO O CANCELADO
			if (it.cancelaTransaccion 
				|| it.situacionMovimiento.equals(SituacionPremovimiento.PROCESADO_REAL)
				|| it.situacionMovimiento.equals(SituacionPremovimiento.CANCELADO)){
				log.info "ENCONTRO EL MOVIMIENTO APLICADO O CANCELADO"
				movimiento =  it
			}
		}

		if (movimiento){
			throw new PagoServiceException(mensaje: "El pago no puede ser aplicado, ya esta aplicado o cancelado", prestamoPagoInstance:prestamoPagoInstance )	
		}


		//Valida que la fecha valor del pago no sea menor a un pago aplicado previo
		def criteriaPfinMovimiento = PfinMovimiento.createCriteria()
		Integer cuentaMovimientos = criteriaPfinMovimiento.count(){
			and {
				eq("prestamo",prestamoPagoInstance.prestamo)
		        eq("situacionMovimiento", SituacionPremovimiento.PROCESADO_REAL)
		        isNull("cancelaTransaccion")
		        gt("fechaAplicacion", prestamoPagoInstance.fechaPago)
		    }
		}		
		if (cuentaMovimientos > 0){
			throw new PagoServiceAplicaPagoException(mensaje: "Existen movimientos con fecha valor posterior a este movimiento")		
		}

		//Se obtiene la Fecha Valor
		Date fechaValor = prestamoPagoInstance.fechaPago

		//Se obtiene la Fecha del Medio
		PfinCatParametro parametros = PfinCatParametro.findByClaveMedio("SistemaMtn")
		Date fechaSistema = parametros?.fechaMedio
		if (!fechaSistema){
			throw new PagoServiceAplicaPagoException(mensaje: "No se encuentra la fecha del medio del sistema", prestamoPagoInstance:prestamoPagoInstance )
		}

		//Valida que la fecha valor no sea mayor a la fecha del Medio
		if (fechaValor > fechaSistema) {
			throw new PagoServiceAplicaPagoException(mensaje: "Operación no realizada, la fecha de Aplicación es mayor a la fecha del medio del sistema")
		}

		//Se obtiene la cuenta Eje del Cliente
		PfinCuenta cuentaEje = PfinCuenta.findWhere(tipoCuenta: "EJE", cliente: prestamoPagoInstance.prestamo.cliente)
		if (!cuentaEje){
			throw new PagoServiceAplicaPagoException(mensaje: "No se encontro la cuenta eje del Cliente")
		}

		//Recuperar o almacenar el pago Guardado
		def criteriaMovimientoGuardado = PfinMovimiento.createCriteria()
		PfinMovimiento movimientoGuardado  = criteriaMovimientoGuardado.get() {
			and {
				eq("prestamo",prestamoPagoInstance.prestamo)
				eq("situacionMovimiento", SituacionPremovimiento.PROCESADO_VIRTUAL)
				//TEDEPEFE = DEPOSITO DE EFECTIVO
				eq("operacion", PfinCatOperacion.findByClaveOperacion('TEDEPEFE'))
				isNull("cancelaTransaccion")
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
		//ORDENADAS POR NUMERO DE PAGO
		def criteriaTablaAmortizacionRegistro = TablaAmortizacionRegistro.createCriteria()
		ArrayList listaAmortizacionPendiente  = criteriaTablaAmortizacionRegistro.list() {
			and {
				eq("prestamo",prestamoPagoInstance.prestamo)
				eq("pagado", false)
				order("numeroPago")
			}
		}

		if (!listaAmortizacionPendiente){
			throw new PagoServiceException(mensaje: "No se encontraron amortizaciones para aplicar el Pago", prestamoPagoInstance:prestamoPagoInstance )			
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
		BigDecimal importeSaldo, //IMPORTE QUE EL CLIENTE TIENE EN SU CUENTA
		PfinCuenta cuentaCliente, //CUENTA EJE DEL CLIENTE
		Date fechaMedio) {

		Prestamo prestamoInstance = prestamoPago.prestamo
		Integer amortizacionPago = tablaAmortizacionRegistro.numeroPago
		BigDecimal importeNeto = 0 //IMPORTE TOTAL QUE SE VA A REALIZAR A LA AMORTIZACION
		
		//IMPLEMENTACION QUE SUSTITUYE A LA VISTA DE PRELACION DE PAGOS 

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

		//OBTIENE EL USUARIO ACTUAL
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
				nota : 						"Pago de Prestamo",
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
			/*
			log.info "******************"
			log.info "Numero Amortizacion: "+it.numeroAmortizacion
			log.info "Orden Pago: "+it.ordenPago
			log.info "Concepto: " +it.concepto
			log.info "Cantidad:"+it.cantidadPagar
			*/
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
			// SE INDICA A QUE PAGO PRESTAMO PERTENECE EL MOVIMIENTO
			movimiento.prestamoPago = prestamoPago
			movimiento.save(flush:true)

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

		//SE OBTIENE COMO SE AFECTA EL SALDO DE LA CUENTA EJE 
		//DESDE LA OPERACION DEL MOVIMIENTO
		String afectaSaldo = movimiento.operacion.claveAfectaSaldo
		Integer afectaCredito = 1

		if (afectaSaldo.equals("INCREMENTA")){
			//SI EL SALDO DE LA CUENTA EJE INCREMENTA ENTOCES 
			//EL CREDITO DEBE DECREMENTAR
			//ESTE CASO SE PUEDE DAR EN CANCELA PAGO
			afectaCredito = -1
		}

		listaMovimientoDet.each(){ listMovDet ->

			switch ( listMovDet.concepto ) {
			    case PfinCatConcepto.findByClaveConcepto('CAPITAL'):
			        tablaAmortizacionActual.impCapitalPagado = tablaAmortizacionActual.impCapitalPagado	+ (listMovDet.importeConcepto * afectaCredito)
			        //EL CAPITAL ES EL ULTIMO CONCEPTO A PAGAR
			        //VALIDA SI PAGO TODO EL CAPITAL
			        if (tablaAmortizacionActual.impCapitalPagado>=tablaAmortizacionActual.impCapital){
			        	//SE CUBRIO TODA LA AMORTIZACION
			        	tablaAmortizacionActual.pagado = true
			        }else{
			        	tablaAmortizacionActual.pagado = false
			        }
			        break
			    case PfinCatConcepto.findByClaveConcepto('INTERES'):
			        tablaAmortizacionActual.impInteresPagado = tablaAmortizacionActual.impInteresPagado + (listMovDet.importeConcepto * afectaCredito)
			        break
			    case PfinCatConcepto.findByClaveConcepto('IVAINT'):
			        tablaAmortizacionActual.impIvaInteresPagado = tablaAmortizacionActual.impIvaInteresPagado + (listMovDet.importeConcepto * afectaCredito)
			        break
			    default:
			    	//SE TIENE QUE BUSCAR POR CADA MOVIMIENTO DETALLE LA TABLA
			    	//AMORTIZACION ACCESORIO QUE PAGO
			    	tablaAmortizacionActual.tablaAmortizacionAccesorio.each{ tabAmorAcc ->
			    		if (listMovDet.concepto.equals(tabAmorAcc.accesorio.concepto)){
			    			tabAmorAcc.importeAccesorioPagado = tabAmorAcc.importeAccesorioPagado + (listMovDet.importeConcepto * afectaCredito)
			    			tabAmorAcc.save(flush:true)
			    		}
			    	}
			}
			//INCREMETA O DECREMENTA LO PAGADO EN LA AMORTIZACION
			tablaAmortizacionActual.impPagoPagado = tablaAmortizacionActual.impPagoPagado + (listMovDet.importeConcepto * afectaCredito)
 			tablaAmortizacionActual.save(flush:true)
		}
	}

	Boolean cancelaPagoAplicado (PrestamoPago prestamoPagoInstance){
		log.info("Cancela Pago Aplicado")

		//ARREGLO PARA ALMACENAR TODOS MOVIMIENTOS QUE SON PAGOS AL CREDITO
		ArrayList listaMovimientosPagoPrestamo = []

		//VALIDA SI EXISTE EL MOVIMIENTO APLICADO PARA CANCELAR DE prestamoPagoInstance
		PfinMovimiento movimientoAplicado
		//ITERA LOS PfinMovimiento DEL PrestamoPago
		prestamoPagoInstance.pfinMovimiento.each{
			//VALIDA SI EXISTE EL PfinMovimiento CON OPERACION DEPOSITO DE EFECTIVO,
			//PROCESADO REAL Y QUE NO HAYA SIDO CANCELADO
			if (it.operacion.equals(PfinCatOperacion.findByClaveOperacion('TEDEPEFE'))
				&& it.situacionMovimiento.equals(SituacionPremovimiento.PROCESADO_REAL)
				&& !it.cancelaTransaccion){
					//SI ENCUENTRA EL PfinMovimiento 
					movimientoAplicado =  it
			}
			if (it.operacion.equals(PfinCatOperacion.findByClaveOperacion('CRPAGOPRES'))
				&& it.situacionMovimiento.equals(SituacionPremovimiento.PROCESADO_VIRTUAL)){
					//ALMACENA EN EL ARREGLO TODOS LOS MOVIMIENTOS QUE SON PAGO AL PRESTAMO
					listaMovimientosPagoPrestamo.add(it)
			}

		}



		if(!movimientoAplicado){
			throw new PagoServiceException(mensaje: "No se encontro la transacción para cancelar el pago aplicado", prestamoPagoInstance:prestamoPagoInstance )			
		}

		// Valida que la fecha valor no sea menor a un pago previo
		def criteriaPfinMovimiento = PfinMovimiento.createCriteria()
		Integer cuentaMovimientos = criteriaPfinMovimiento.count(){
			and {
				eq("prestamo",prestamoPagoInstance.prestamo)
		        ne("situacionMovimiento", SituacionPremovimiento.CANCELADO)
		        eq("cancelaTransaccion",null)
		        gt("fechaAplicacion", prestamoPagoInstance.fechaPago)
		    }
		}		
		if (cuentaMovimientos > 0){
			throw new PagoServiceException(mensaje: "Existen movimientos con fecha valor posterior a este movimiento", prestamoPagoInstance:prestamoPagoInstance )		
		}

		//SE OBTIENE LA FECHA DEL MEDIO
		//FECHA_MEDIO = FECHA_SISTEMA = FECHA_LIQUIDACION
		PfinCatParametro parametros = PfinCatParametro.findByClaveMedio("SistemaMtn")
		Date fechaMedio = parametros?.fechaMedio
		if (!fechaMedio){
			throw new PagoServiceException(mensaje: "No existe la fecha del medio", prestamoPagoInstance:prestamoPagoInstance )
		}

		//OBTIENE EL USUARIO ACTUAL
		Usuario usuario = springSecurityService.getCurrentUser()
		if (!usuario){
			throw new PagoServiceException(mensaje: "No se encontro usuario registrado", prestamoPagoInstance:prestamoPagoInstance )
		}

		//ASIGNA VALORES AL PREMOVIMIENTO
		PfinPreMovimiento preMovimientoInsertado = new PfinPreMovimiento(
				fechaOperacion: 			fechaMedio, //FECHA DEL MEDIO
				fechaLiquidacion: 			fechaMedio, //FECHA DEL MEDIO
				cuenta:  					movimientoAplicado.cuenta,
				prestamo : 					movimientoAplicado.prestamo,
				divisa: 					PfinDivisa.findByClaveDivisa('MXP'),
				operacion: 					PfinCatOperacion.findByClaveOperacion('AJUSTE_CARGO'),
				importeNeto: 				movimientoAplicado.importeNeto,
				nota : 						"Ajuste Extraordinario Cargo",
				usuario : 					usuario,
				fechaAplicacion: 			movimientoAplicado.fechaAplicacion,
				situacionPreMovimiento : 	SituacionPremovimiento.NO_PROCESADO,
				fechaRegistro: 				new Date(),
				numeroPagoAmortizacion:  	0)

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
					SituacionPremovimiento.PROCESADO_VIRTUAL, usuario, movimientoAplicado.fechaAplicacion)
			// SE ASIGNA LA RELACION PRESTAMO PAGO Y MOVIMIENTO
			movimiento.prestamoPago = prestamoPagoInstance
			movimiento.save(flush:true)			
		}catch(ProcesadorFinancieroServiceException errorProcesadorFinanciero){
			throw errorProcesadorFinanciero
		}catch(Exception errorGenerarMovimiento){
			log.error(errorGenerarMovimiento)
			throw new PagoServiceException(mensaje: "No se genero el movimiento", prestamoPagoInstance:prestamoPagoInstance )
		}		

		//AL MOVIMIENTO DE DEPOSITO DE EFECTIVO A LA CUENTA LE ASIGNA EL MOVIMIENTO QUE LO CANCELA
		movimientoAplicado.cancelaTransaccion = movimiento

		listaMovimientosPagoPrestamo.each{ movimientoPago ->
			//PARA CADA MOVIMIENTO (No.AMORTIZACION) CREA UN AJUSTE EXTRAORDINARIO 
			//POR CANCELACION DE PAGO
			preMovimientoInsertado = new PfinPreMovimiento(
					fechaOperacion: 			fechaMedio, //FECHA DEL MEDIO
					fechaLiquidacion: 			fechaMedio, //FECHA DEL MEDIO
					cuenta:  					movimientoPago.cuenta,
					prestamo : 					movimientoPago.prestamo,
					divisa: 					PfinDivisa.findByClaveDivisa('MXP'),
					operacion: 					PfinCatOperacion.findByClaveOperacion('CANCELA_PAGO'),
					importeNeto: 				movimientoPago.importeNeto,
					nota : 						"Cancela Pago",
					usuario : 					usuario,
					fechaAplicacion: 			movimientoPago.fechaAplicacion,
					situacionPreMovimiento : 	SituacionPremovimiento.NO_PROCESADO,
					fechaRegistro: 				new Date(),
					numeroPagoAmortizacion:  	movimientoPago.numeroPagoAmortizacion)

			try{
				// GENERA EL PREMOVIMIENTO
				preMovimientoInsertado = procesadorFinancieroService.generaPreMovimiento(preMovimientoInsertado)

			}catch(ProcesadorFinancieroServiceException errorProcesadorFinanciero){
				throw errorProcesadorFinanciero
			}

			//ARREGLO PARA ALMACENAR LOS MOVIMIENTOS DETALLE
			ArrayList listaMovimientoDet = []

			//ITERA LOS MOVIMIENTOS DETALLE DEL MOVIMIENTO PARA CREAR EL DETALLE DEL AJUSTE
			movimientoPago.pfinMovimientoDet.each{ movientoDetPago ->

				//SE DEFINE EL PREMOVIMIENTO DETALLE
				PfinPreMovimientoDet preMovimientoDetInsertado
				try{
					// GENERA EL PREMOVIMIENTO DETALLE
					preMovimientoDetInsertado = procesadorFinancieroService.generaPreMovimientoDet(
						preMovimientoInsertado,
						movientoDetPago.concepto,
						movientoDetPago.importeConcepto,
						movientoDetPago.concepto.descripcionCorta)
				}catch(ProcesadorFinancieroServiceException errorProcesadorFinanciero){
					throw errorProcesadorFinanciero
				}
				listaMovimientoDet.add(preMovimientoDetInsertado)

			}	

			//SE GENERA EL MOVIMIENTO
			try{
				movimiento = procesadorFinancieroService.procesaMovimiento(preMovimientoInsertado,
						SituacionPremovimiento.PROCESADO_VIRTUAL, usuario, movimientoPago.fechaAplicacion)
				// SE ASIGNA LA RELACION PRESTAMO PAGO Y MOVIMIENTO
				movimiento.prestamoPago = prestamoPagoInstance
				movimiento.save(flush:true)			
			}catch(ProcesadorFinancieroServiceException errorProcesadorFinanciero){
				throw errorProcesadorFinanciero
			}catch(Exception errorGenerarMovimiento){
				log.error(errorGenerarMovimiento)
				throw new PagoServiceException(mensaje: "No se genero el movimiento", prestamoPagoInstance:prestamoPagoInstance )
			}		
			movimientoPago.cancelaTransaccion = movimiento
			movimientoPago.save(flush:true)

			actualizaTablaAmortizacion(movimiento,listaMovimientoDet)
		}
	}
}