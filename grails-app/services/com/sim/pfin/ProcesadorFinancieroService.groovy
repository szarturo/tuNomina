package com.sim.pfin

import com.sim.usuario.Usuario

class ProcesadorFinancieroServiceException extends RuntimeException {
	String mensaje
}

class ProcesadorFinancieroService {

	//RECUPERA LA FECHA DEL MEDIO
	Date obtenerFechaMedio(){
		//FECHA_MEDIO = FECHA_SISTEMA = FECHA_LIQUIDACION
		PfinCatParametro parametros = PfinCatParametro.findByClaveMedio("SistemaMtn")
		Date fechaMedio = parametros?.fechaMedio
		if (!fechaMedio){
			throw new ProcesadorFinancieroServiceException(mensaje: "No existe la fecha del medio")
		}
		return fechaMedio
	}

	//METODO PARA INSERTAR EL PREMOVIMIENTO
	PfinPreMovimiento generaPreMovimiento(PfinPreMovimiento preMovimiento) {
		try{
			preMovimiento.save(flush: true,failOnError: true)

		}catch(Exception errorInsertarPreMovimiento){
			log.error(errorInsertarPreMovimiento)
			throw new ProcesadorFinancieroServiceException(mensaje: "No inserto el PreMovimiento")
		}
	}

	//METODO PARA INSERTAR EL PREMOVIMIENTO DETALLE
	PfinPreMovimientoDet generaPreMovimientoDet(PfinPreMovimiento preMovimiento, PfinCatConcepto catConcepto, BigDecimal importeConcepto, String nota) {
		try {
			new PfinPreMovimientoDet(concepto:  catConcepto,
					importeConcepto: importeConcepto,
					nota : nota,
					preMovimiento : preMovimiento).save(flush: true,failOnError: true)
			
		}catch(Exception errorInsertarPreMovimientoDet) {
			log.error(errorInsertarPreMovimientoDet)
			throw new ProcesadorFinancieroServiceException(mensaje: "No inserto el PreMovimientoDet")
		}
	}

	//METODO PARA PROCESAR EL MOVIMIENTO
	PfinMovimiento procesaMovimiento(PfinPreMovimiento pfinPreMovimiento,
		SituacionPremovimiento situacionMovimiento, Usuario usuario, Date fechaAplicacion) {
		
		//SE VALIDA QUE LA ACTUALIZACION SEA CORRECTA
		if  (!(
				(pfinPreMovimiento.situacionPreMovimiento == SituacionPremovimiento.NO_PROCESADO &&
				(situacionMovimiento ==  SituacionPremovimiento.PROCESADO_VIRTUAL || situacionMovimiento ==  SituacionPremovimiento.PROCESADO_REAL))
				|| 
				(pfinPreMovimiento.situacionPreMovimiento == SituacionPremovimiento.PROCESADO_VIRTUAL &&
				(situacionMovimiento == SituacionPremovimiento.CANCELADO || situacionMovimiento == SituacionPremovimiento.PROCESADO_REAL))
			)){
			throw new ProcesadorFinancieroServiceException(mensaje: 
				"Tipo de actualización ilegal de ${pfinPreMovimiento.situacionPreMovimiento} a ${situacionMovimiento}")
		}
		
		//ACTUALIZACIONES LEGALES
		//NO_PROCESADO      -> (PROCESADO_VIRTUAL,PROCESADO_REAL)
		//PROCESADO_VIRTUAL -> (CANCELADO,PROCESADO_REAL)
		
		PfinMovimiento movimiento = new PfinMovimiento()
		if (situacionMovimiento != SituacionPremovimiento.CANCELADO){
			//ASIGNA VALORES AL MOVIMIENTO
			movimiento.situacionMovimiento = situacionMovimiento
			movimiento.pfinPreMovimiento = pfinPreMovimiento
			movimiento.fechaAplicacion = fechaAplicacion
			movimiento.usuario =   usuario
			movimiento.cuenta = pfinPreMovimiento.cuenta
			movimiento.divisa = pfinPreMovimiento.divisa
			try{
				movimiento.fechaOperacion = obtenerFechaMedio()
				movimiento.fechaLiquidacion = obtenerFechaMedio()
			}catch(ProcesadorFinancieroServiceException errorFechaMedio){
				log.error(errorFechaMedio)
				throw errorFechaMedio
			}
			movimiento.operacion = pfinPreMovimiento.operacion
			movimiento.importeNeto = pfinPreMovimiento.importeNeto
			movimiento.referencia = pfinPreMovimiento.referencia
			movimiento.prestamo = pfinPreMovimiento.prestamo
			movimiento.nota = pfinPreMovimiento.nota
			movimiento.fechaRegistro = new Date()
			movimiento.logIpDireccion = pfinPreMovimiento.logIpDireccion
			movimiento.logUsuario = pfinPreMovimiento.logUsuario
			movimiento.logHost = pfinPreMovimiento.logHost
			movimiento.numeroPagoAmortizacion = pfinPreMovimiento.numeroPagoAmortizacion
			movimiento.cancelaTransaccion = 0
		
			try{
				movimiento.save(flush: true,failOnError: true)
	
			}catch(Exception errorInsertarMovimiento){
				log.error(errorInsertarMovimiento)
				throw new ProcesadorFinancieroServiceException(mensaje: "No inserto el Movimiento")
			}
			
			//CORRECTA LA SIGUIENTE LINEA PERO SE AFECTA EL PERFORMACE DE LA BASE DE DATOS
			def listaPreMovimientosDetalle = PfinPreMovimientoDet.findAllByPreMovimiento(pfinPreMovimiento)
			//def listaPreMovimientosDetalle = pfinPreMovimiento.pfinPreMovimientoDet
			
			//CREA LOS MOVIMIENTOS DETALLE DEL MOVIMIENTO
			PfinMovimientoDet movimientoDetalle
			listaPreMovimientosDetalle.each() {
				movimientoDetalle = new PfinMovimientoDet()
				//ASIGNA VALORES AL MOVIMIENTO DETALLE
				movimientoDetalle.movimiento = movimiento
				movimientoDetalle.concepto = it.concepto
				movimientoDetalle.importeConcepto = it.importeConcepto
				movimientoDetalle.nota =   it.nota
				movimientoDetalle.save(flush: true,failOnError: true)
			}
			//ACTUALIZA PARAMETROS DEL PREMOVIMIENTO
			pfinPreMovimiento.situacionPreMovimiento = SituacionPremovimiento.PROCESADO_VIRTUAL
			pfinPreMovimiento.pfinMovimiento = movimiento
			pfinPreMovimiento.save(flush:true)
		}else{
			//EN CASO DE SER UNA CANCELACION SOLO SE ACTUALIZA LA SITUACION DEL MOVIMIENTO Y PREMOVIMIENTO
			//SE MODIFICA LA SITUACION DEL PREMOVIMIENTO
			pfinPreMovimiento.situacionPreMovimiento = SituacionPremovimiento.CANCELADO
			pfinPreMovimiento.usuario = usuario
			try{
				pfinPreMovimiento.save(flush:true)
			}catch(Exception errorCancelarPremovimiento){
				log.error(errorCancelarPremovimiento)
				throw new ProcesadorFinancieroServiceException(mensaje: "Ocurrio un problema al cancelar el premovimiento")
			}
			//SE MODIFICA LA SITUACION DEL MOVIMIENTO
			movimiento = pfinPreMovimiento.pfinMovimiento
			movimiento.situacionMovimiento = SituacionPremovimiento.CANCELADO
			movimiento.usuario = usuario
			try{
				movimiento.save(flush:true)
			}catch(Exception errorCancelarMovimiento){
				log.error(errorCancelarMovimiento)
				throw new ProcesadorFinancieroServiceException(mensaje: "Ocurrio un problema al cancelar el movimiento")
			}
		}
		
		//VERIFICA DE QUE FORMA VA A AFECTAR EL SALDO DEL CLIENTE
		String comoAfectaSaldo = pfinPreMovimiento.operacion.claveAfectaSaldo
		
		if (comoAfectaSaldo =='INCREMENTA' || comoAfectaSaldo=='DECREMENTA'){
			
			Integer afectaSaldo = 0
			
			if (situacionMovimiento != SituacionPremovimiento.CANCELADO){
				afectaSaldo = comoAfectaSaldo == 'INCREMENTA' ? 1 : -1
			}else{
				afectaSaldo = comoAfectaSaldo == 'INCREMENTA' ? -1 : 1
			}
			
			PfinSaldo saldoCliente = PfinSaldo.findWhere(
				fechaFoto: obtenerFechaMedio(), 
				cuenta: pfinPreMovimiento.cuenta,
				divisa: pfinPreMovimiento.divisa)
			
			if (saldoCliente){
				//YA EXISTE LA CUENTA
				//ACTUALIZA EL SALDO DE LA CUENTA CON LA FECHA FOTO
				saldoCliente.saldo = saldoCliente.saldo + (pfinPreMovimiento.importeNeto * afectaSaldo)
				try{
					saldoCliente.save(flush:true)
				}catch(Exception errorActualizarSaldoCuenta){
					log.error(errorActualizarSaldoCuenta)
					throw new ProcesadorFinancieroServiceException(mensaje: "Ocurrio un problema al actualizar el saldo de la cuenta")
				}
			}else{
				//NO EXISTE EL SALDO PARA LA CUENTA
				//INSERTA EL SALDO DE LA CUENTA CON LA FOTO
				try{
					new PfinSaldo(
						fechaFoto: obtenerFechaMedio(),
						   divisa: pfinPreMovimiento.divisa,
						    saldo: pfinPreMovimiento.importeNeto * afectaSaldo,  
						   cuenta: pfinPreMovimiento.cuenta,
					).save(flush: true,failOnError: true)
				}catch(Exception errorInsertarSaldoCuenta){
					log.error(errorInsertarSaldoCuenta)
					throw new ProcesadorFinancieroServiceException(mensaje: "Ocurrio un problema al insertar el saldo de la cuenta")
				}
			}
		}
		
		return movimiento
	}
}