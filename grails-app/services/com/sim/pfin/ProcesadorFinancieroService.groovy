package com.sim.pfin

import com.sim.pfin.pruebas.PfinPagoCredito;

class ProcesadorFinancieroServiceException extends RuntimeException {
	String mensaje
}

class ProcesadorFinancieroService {
    
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
}