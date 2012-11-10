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
		String situacionMovimiento, Usuario usuario, Date fechaAplicacion) {

		PfinMovimiento movimiento = new PfinMovimiento()
		if (situacionMovimiento!='CA'){
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
		}

		try{
			movimiento.save(flush: true,failOnError: true)

		}catch(Exception errorInsertarMovimiento){
			log.error(errorInsertarMovimiento)
			throw new ProcesadorFinancieroServiceException(mensaje: "No inserto el Movimiento")
		}





		return movimiento
	}

}