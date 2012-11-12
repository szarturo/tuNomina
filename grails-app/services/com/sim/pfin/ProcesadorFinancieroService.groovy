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


		return preMovimiento
	}

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
			
			log.info("Procesador Service: ${listaPreMovimientosDetalle}")
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
		return movimiento
	}

}