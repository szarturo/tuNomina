package com.sim.pfin

import com.sim.pfin.pruebas.PfinPagoCredito
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
		
		log.info("Tipo de actualización de ${pfinPreMovimiento.situacionPreMovimiento} a ${situacionMovimiento}")
		//SE VALIDA QUE LA ACTUALIZACION SEA CORRECTA
		if (! (pfinPreMovimiento.situacionPreMovimiento == SituacionPremovimiento.NO_PROCESADO &&
			(situacionMovimiento ==  SituacionPremovimiento.PROCESADO_VIRTUAL || situacionMovimiento ==  SituacionPremovimiento.PROCESADO_REAL))
			|| (pfinPreMovimiento.situacionPreMovimiento == SituacionPremovimiento.PROCESADO_VIRTUAL &&
			(situacionMovimiento == SituacionPremovimiento.CANCELADO || situacionMovimiento == SituacionPremovimiento.PROCESADO_REAL))){

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
			movimiento.listaCobro = pfinPreMovimiento.listaCobro
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
			
			//def listapreMovimientosDetalle = PfinPreMovimientoDet.findAllByPreMovimiento(pfinPreMovimiento)
			def listaPreMovimientosDetalle = pfinPreMovimiento.pfinPreMovimientoDet
			
			//CREA LOS MOVIMIENTOS DETALLE DEL MOVIMIENTO
			PfinMovimientoDet movimientoDetalle
			listaPreMovimientosDetalle.each() {
				log.info("PreMovimientosDetalle Service PF: ${it.id}")
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
			//ACTUALIZA PARAMETROS DEL PREMOVIMIENTO
			pfinPreMovimiento.situacionPreMovimiento = SituacionPremovimiento.CANCELADO
			pfinPreMovimiento.pfinMovimiento = null
			pfinPreMovimiento.usuario = usuario
			pfinPreMovimiento.save(flush:true)
		}
		
		//VERIFICA DE QUE FORMA VA A AFECTAR EL SALDO DEL CLIENTE
		String comoAfectaSaldo = pfinPreMovimiento.operacion.claveAfectaSaldo
		log.info("Afecta Saldo: ${comoAfectaSaldo}")
		
		if (comoAfectaSaldo =='INCREMENTA' || comoAfectaSaldo=='DECREMENTA'){
			
			Integer afectaSaldo = 0
			
			if (situacionMovimiento != SituacionPremovimiento.CANCELADO){
				afectaSaldo = comoAfectaSaldo == 'INCREMENTA' ? 1 : -1
			}else{
				afectaSaldo = comoAfectaSaldo == 'DECREMENTA' ? -1 : 1
			}
			
			PfinSaldo saldoCliente = PfinSaldo.findWhere(fechaFoto: fechaAplicacion, cuenta: pfinPreMovimiento.cuenta,
															   divisa: pfinPreMovimiento.divisa)
			log.info("Saldo Cliente: ${saldoCliente}")
			
			if (saldoCliente){
				//YA EXISTE LA CUENTA
				//ACTUALIZA EL SALDO DE LA CUENTA CON LA FOTO
				//¿PORQUE EL SALDO LO LLEVA POR FECHA?
				log.info("Actualiza el saldo de la cuenta")
				saldoCliente.saldo = saldoCliente.saldo + (pfinPreMovimiento.importeNeto * afectaSaldo)
				saldoCliente.save(flush:true)
			}else{
				//NO EXISTE EL SALDO PARA LA CUENTA
				//INSERTA EL SALDO DE LA CUENTA CON LA FOTO
				log.info("Inserta el saldo de la cuenta")
				new PfinSaldo(
					fechaFoto: fechaAplicacion,
					   divisa: pfinPreMovimiento.divisa,
					    saldo: pfinPreMovimiento.importeNeto * afectaSaldo, //UTIL PARA CUANDO DECREMENTA EL SALDO 
					   cuenta: pfinPreMovimiento.cuenta,
				).save(flush: true,failOnError: true)
			}
		}
		
		return movimiento
	}

}