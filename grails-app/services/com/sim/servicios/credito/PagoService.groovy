package com.sim.servicios.credito

import com.sim.credito.Prestamo;
import com.sim.pfin.pruebas.PfinPagoCredito
import com.sim.pfin.PfinCuenta
//import com.sim.pfin.PfinParametros

class PagoService {

 	static transactional = true
	 
	//SERVICIO PARA RECUPERAR EL USUARIO
	def springSecurityService

	Boolean aplicaPagoIndividual(PfinPagoCredito pagoCreditoInstance) {
		
		String username = springSecurityService.getCurrentUser().username;
		log.info ("Usuario Service Pago: ${username}")
		
		//SE OBTIENE LA FECHA DEL MEDIO
		//FECHA_MEDIO = FECHA_SISTEMA = FECHA_LIQUIDACION
		//PfinParametros parametros = PfinParametros.findByClaveMedio("System")
		//Date fechaMedio = parametros.fechaMedio
		
		//FECHA DE APLICACION
		Date fechaAplicacion = pagoCreditoInstance.fechaPago
		
		//OBTIENE LA CUENTA DEL CLIENTE
		PfinCuenta cuentaCliente = PfinCuenta.findByTipoCuentaAndCliente("CREDITO",pagoCreditoInstance.prestamo.cliente)
		log.info("Cuenta Cliente: ${cuentaCliente}")
		

		
		return true
	}

}
