package com.sim.pfin

import com.sim.pfin.pruebas.PfinPagoCredito;

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
	
	//METODO PARA INSERTAR EL MOVIMIENTO
	PfinMovimiento generaMovimiento(PfinMovimiento movimiento) {
		
		if (movimiento.situacionMovimiento!='CA'){
			//ASIGNA VALORES AL MOVIMIENTO
			movimiento.cuenta = movimiento.pfinPreMovimiento.cuenta
			movimiento.divisa = movimiento.pfinPreMovimiento.divisa
			try{
				movimiento.fechaOperacion = obtenerFechaMedio()
				movimiento.fechaLiquidacion = obtenerFechaMedio()
			}catch(ProcesadorFinancieroServiceException errorFechaMedio){
				log.error(errorFechaMedio)
				throw errorFechaMedio
			}
			movimiento.operacion = movimiento.pfinPreMovimiento.operacion
			movimiento.importeNeto = movimiento.pfinPreMovimiento.importeNeto
			movimiento.referencia = movimiento.pfinPreMovimiento.referencia
			movimiento.prestamo = movimiento.pfinPreMovimiento.prestamo
			movimiento.nota = movimiento.pfinPreMovimiento.nota
			movimiento.listaCobro = movimiento.pfinPreMovimiento.listaCobro
			movimiento.fechaRegistro = new Date()
			movimiento.logIpDireccion = movimiento.pfinPreMovimiento.logIpDireccion
			movimiento.logUsuario = movimiento.pfinPreMovimiento.logUsuario
			movimiento.logHost = movimiento.pfinPreMovimiento.logHost
			movimiento.numeroPagoAmortizacion = movimiento.pfinPreMovimiento.numeroPagoAmortizacion
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