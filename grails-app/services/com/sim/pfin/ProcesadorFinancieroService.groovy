package com.sim.pfin

import com.sim.pfin.pruebas.PfinPagoCredito;

class ProcesadorFinancieroServiceException extends RuntimeException {
	String mensaje
}

class ProcesadorFinancieroService {
	//SERVICIO PARA RECUPERAR EL USUARIO
	def springSecurityService
    
	//METODO PARA INSERTAR EL PREMOVIMIENTO
	Boolean generaPreMovimiento(PfinPagoCredito pagoCreditoInstance) {
		try{
			PfinPreMovimiento preMovimiento = new PfinPreMovimiento(cuenta:  cuenta1,
				divisa: PfinDivisa.findByClaveDivisa('MXP'),
				fechaOperacion:new Date('09/30/2012'),
				fechaLiquidacion:new Date('09/30/2012'),
				importeNeto: 1000.50,
				referencia: 34,
				prestamo : Prestamo.findByClavePrestamo('KLP987'),
				nota : 'Pago de Prestamo',
				listaCobro : 3453,
				//pfinMovimiento()
				situacionPreMovimiento : 'PV',
				fechaRegistro:new Date('09/30/2012'),
				logIpDireccion: 'xxxxxxxxx',
				logUsuario:'xxxxxxxxxx',
				logHost:'xxxxxxxxxx',
				usuario : usuarioKermit,
				fechaAplicacion:new Date('09/30/2012'),
				numeroPagoAmortizacion: 1,
				operacion: PfinCatOperacion.findByClaveOperacion('TEDEPEFE'),
			).save(flush: true,failOnError: true)
		
		}catch(Exception errorInsertarPreMovimiento){
			throw new ProcesadorFinancieroServiceException(mensaje: "No inserto el PreMovimiento")
		}
    }
}