package com.sim.servicios.credito

import com.sim.credito.Prestamo;
import com.sim.pfin.pruebas.PfinPagoCredito
import com.sim.pfin.PfinCuenta
import com.sim.pfin.PfinPreMovimiento
import com.sim.pfin.PfinCatParametro
import com.sim.pfin.ProcesadorFinancieroServiceException


class AplicaPagoIndividualException extends RuntimeException {
	String mensaje
	PfinPagoCredito pagoCreditoInstance
}

class PagoService {

 	static transactional = true
	 
	//SERVICIO PARA RECUPERAR EL USUARIO
	def springSecurityService
	//SERVICIO DEL CORE FINANCIERO
	def procesadorFinancieroService

	Boolean aplicaPagoIndividual(PfinPagoCredito pagoCreditoInstance) {
		
		String username = springSecurityService.getCurrentUser().username;
		log.info ("Usuario Service Pago: ${username}")
		
		
		//SE OBTIENE LA FECHA DEL MEDIO
		//FECHA_MEDIO = FECHA_SISTEMA = FECHA_LIQUIDACION
		PfinCatParametro parametros = PfinCatParametro.findByClaveMedio("SistemaMtn")
		Date fechaMedio = parametros?.fechaMedio
		if (!fechaMedio){
			throw new AplicaPagoIndividualException(mensaje: "No existe la fecha del medio", pagoCreditoInstance:pagoCreditoInstance )
		}
		
		//FECHA DE APLICACION
		Date fechaAplicacion = pagoCreditoInstance.fechaPago
		
		//OBTIENE LA CUENTA DEL CLIENTE
		PfinCuenta cuentaCliente = PfinCuenta.findByTipoCuentaAndCliente("CREDITO",pagoCreditoInstance.prestamo.cliente)
		log.info("Cuenta Cliente: ${cuentaCliente}")
		//VERIFICA SI EXISTE LA CUENTA DEL CLIENTE
		if (!cuentaCliente){
			throw new AplicaPagoIndividualException(mensaje: "No existe la cuenta del Cliente", pagoCreditoInstance:pagoCreditoInstance )
		}
		
		//INSERTA EL PREMOVIMIENTO
		PfinPreMovimiento preMovimientoInsertado
		try{
			preMovimientoInsertado = procesadorFinancieroService.generaPreMovimiento(pagoCreditoInstance,cuentaCliente)
		}catch(ProcesadorFinancieroServiceException errorProcesadorFinanciero){
			throw errorProcesadorFinanciero
		}
		
		return true
	}

}
