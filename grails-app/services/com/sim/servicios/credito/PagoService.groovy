package com.sim.servicios.credito

import com.sim.usuario.Usuario

import com.sim.pfin.*
import com.sim.credito.*
import com.sim.tablaAmortizacion.TablaAmortizacionRegistro

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
		log.info "Servicio Guarda Pago"

		//VALIDA SI EL PRESTAMO TIENE PAGOS GUARDADOS PREVIOS
		Integer numeroPreMovimientos = PfinPreMovimiento.countByPrestamoAndSituacionPreMovimiento(prestamoPagoInstance.prestamo,SituacionPremovimiento.PROCESADO_VIRTUAL)
		log.info ("Numero de Premovimientos: ${numeroPreMovimientos}")
		if (numeroPreMovimientos>0){
			throw new PagoServiceException(mensaje: "Existen pagos guardados, debe de cancelar o aplicar los pagos previos guardados", prestamoPagoInstance:prestamoPagoInstance )
		}

		//ALMACENA EL REGISTRO DE PAGO
        if (!prestamoPagoInstance.save(flush: true)) {
            throw new PagoServiceException(mensaje: "No se guardo el registro de Pago", prestamoPagoInstance:prestamoPagoInstance )
        }

		//OBTIENE EL REGISTRO ACTUAL
		Usuario usuario = springSecurityService.getCurrentUser()
		log.info ("Usuario Service Pago: ${usuario}")
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

		//OBTIENE LA CUENTA DEL CLIENTE
		PfinCuenta cuentaCliente = PfinCuenta.findByTipoCuentaAndCliente("EJE",prestamoPagoInstance.prestamo.cliente)
		log.info("Cuenta Cliente: ${cuentaCliente}")
		//VERIFICA SI EXISTE LA CUENTA DEL CLIENTE
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
				listaCobro : 				1,
				//pfinMovimiento()
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
		log.info "Servicio Cancela Pago Guardado"
		PfinPreMovimiento preMovimientoGuardado = PfinPreMovimiento.findByPrestamoAndSituacionPreMovimiento(prestamoPagoInstance.prestamo,SituacionPremovimiento.PROCESADO_VIRTUAL)
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
		log.info "Aplicar Pago Service"

		//Valida que la fecha valor no sea menor a un pago previo
		def criteriaPfinMovimiento = PfinMovimiento.createCriteria()
		Integer cuentaMovimientos = criteriaPfinMovimiento.count(){
			and {
				eq("prestamo",prestamoPagoInstance.prestamo)
		        ne("situacionMovimiento", SituacionPremovimiento.CANCELADO)
		        eq("cancelaTransaccion",0)
		        gt("fechaAplicacion", prestamoPagoInstance.fechaPago)
		    }
		}		
		log.info "Resultado: ${cuentaMovimientos}"

		if (cuentaMovimientos > 0){
			throw new PagoServiceException(mensaje: "Existen movimientos con fecha valor posterior a este movimiento", prestamoPagoInstance:prestamoPagoInstance )		
		}

		//Se obtiene la Fecha Valor
		Date fechaValor = prestamoPagoInstance.fechaPago
		log.info ("Fecha Valor: ${fechaValor}")

		//Se obtiene la Fecha del Medio
		PfinCatParametro parametros = PfinCatParametro.findByClaveMedio("SistemaMtn")
		Date fechaSistema = parametros?.fechaMedio
		if (!fechaSistema){
			throw new PagoServiceException(mensaje: "No se encuentra la fecha del medio del sistema", prestamoPagoInstance:prestamoPagoInstance )
		}
		log.info "Fecha Medio: ${fechaSistema}"

		//Valida que la fecha valor no sea mayor a la fecha del Medio
		if (fechaValor > fechaSistema) {
			throw new PagoServiceException(mensaje: "Operación no realizada, la fecha de Aplicación es mayor a la fecha del medio del sistema", prestamoPagoInstance:prestamoPagoInstance )
		}

		//Se obtiene la cuenta Eje del Cliente
		PfinCuenta cuentaEje = PfinCuenta.findWhere(tipoCuenta: "EJE", cliente: prestamoPagoInstance.prestamo.cliente)
		log.info("La cuenta es: ${cuentaEje}.")
		if (!cuentaEje){
			throw new PagoServiceException(mensaje: "No se encontro la cuenta eje del Cliente", prestamoPagoInstance:prestamoPagoInstance )
		}

		//Guardar o recuperar el pago Guardado
		PfinMovimiento movimientoGuardado = PfinMovimiento.findByPrestamoAndSituacionMovimiento(prestamoPagoInstance.prestamo,SituacionPremovimiento.PROCESADO_VIRTUAL)

		if(!movimientoGuardado){
			log.info ("No Existe el premoviento y movimiento PROCESADO_VIRTUAL")
			movimientoGuardado = guardarPago(prestamoPagoInstance)
		}		


		//Se obtienen las amortizaciones pendientes de pago
		def criteriaTablaAmortizacionRegistro = TablaAmortizacionRegistro.createCriteria()
		ArrayList listaAmortizacionPendiente  = criteriaTablaAmortizacionRegistro.list() {
			and {
				eq("prestamo",prestamoPagoInstance.prestamo)
				eq("pagado", false)
				order("numeroPago")
			}
		}
		log.info("Las amortizaciones pendientes son: ${listaAmortizacionPendiente}")

		//Ejecuta el pago de cada amortizacion pendiente siempre y cuando el cliente tenga saldo en su cuenta
		listaAmortizacionPendiente.each(){
			log.info "Existen amortizacion pendiente"

			//Obtiene el importe de la cuenta eje del cliente
			PfinSaldo obtieneSaldo = PfinSaldo.findWhere(fechaFoto: fechaSistema, cuenta: cuentaEje)
			BigDecimal importeSaldo  = obtieneSaldo.saldo
			log.info "El saldo del cliente es: ${importeSaldo}."
			if (importeSaldo > 0){
				log.info "Empieza la funcion: AplicaPagoCreditoPorAmort"
				AplicaPagoCreditoPorAmort(prestamoPagoInstance,it,importeSaldo,cuentaEje)

			}else{

			}
		}






	}

	Boolean AplicaPagoCreditoPorAmort (PrestamoPago prestamoPago,
		TablaAmortizacionRegistro tablaAmortizacionRegistro,
		BigDecimal importeSaldo,
		PfinCuenta) {

		//Se inician las variables de los importes
		//BigDecimal importePago = prestamo.importePago
		//BigDecimal importeNeto = 0


		log.info ("Inicio AplicaPagoCreditoPorAmort")

	}



	//METODO DE EJEMPLO TOMADO DEL SIM CREDICONFIA
	//EJEMPLO QUE NOS SIRVIO PARA DESARROLLAR EL CORE FINANCIERO
	Boolean aplicaPagoIndividual(PrestamoPago prestamoPagoInstance) {

		//String username = springSecurityService.getCurrentUser().username;

		//Se obtiene el usuario actual
		Usuario usuario = springSecurityService.getCurrentUser()
		log.info ("Usuario Service Pago: ${usuario}")

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
		log.info("Cuenta Cliente: ${cuentaCliente}")
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
				listaCobro : 1,
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
			log.info("Operacion: ${operacion}")

			def listaConceptos = PfinCatOperacionConcepto.findAllByOperacion(operacion)
			log.info(listaConceptos)

			listaConceptos.each() {
				log.info("Conceptos: ${it.concepto}")
				PfinPreMovimientoDet preMovimientoDet = procesadorFinancieroService.generaPreMovimientoDet(preMovimientoInsertado, it.concepto, 100, "Si pasa!")
				preMovimientoInsertado.addToPfinPreMovimientoDet(preMovimientoDet)
			}
			
			log.info"Detalles del PreMovimiento: ${preMovimientoInsertado.pfinPreMovimientoDet}"
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
