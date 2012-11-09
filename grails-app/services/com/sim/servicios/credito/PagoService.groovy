package com.sim.servicios.credito

import com.sim.credito.Prestamo;
import com.sim.usuario.Usuario
import com.sim.pfin.pruebas.PfinPagoCredito
import com.sim.pfin.PfinCuenta
import com.sim.pfin.PfinPreMovimiento
import com.sim.pfin.PfinCatParametro
import com.sim.pfin.PfinDivisa
import com.sim.pfin.ProcesadorFinancieroServiceException
import com.sim.pfin.PfinCatOperacion


class PagoServiceException extends RuntimeException {
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
		
		//String username = springSecurityService.getCurrentUser().username;
		
		Usuario usuario = springSecurityService.getCurrentUser()
		log.info ("Usuario Service Pago: ${usuario}")
		
		//SE OBTIENE LA FECHA DEL MEDIO
		//FECHA_MEDIO = FECHA_SISTEMA = FECHA_LIQUIDACION
		PfinCatParametro parametros = PfinCatParametro.findByClaveMedio("SistemaMtn")
		Date fechaMedio = parametros?.fechaMedio
		if (!fechaMedio){
			throw new PagoServiceException(mensaje: "No existe la fecha del medio", pagoCreditoInstance:pagoCreditoInstance )
		}
		
		//FECHA DE APLICACION
		Date fechaAplicacion = pagoCreditoInstance.fechaPago
		
		//OBTIENE LA CUENTA DEL CLIENTE
		PfinCuenta cuentaCliente = PfinCuenta.findByTipoCuentaAndCliente("EJE",pagoCreditoInstance.prestamo.cliente)
		log.info("Cuenta Cliente: ${cuentaCliente}")
		//VERIFICA SI EXISTE LA CUENTA DEL CLIENTE
		if (!cuentaCliente){
			throw new PagoServiceException(mensaje: "No existe la cuenta del Cliente", pagoCreditoInstance:pagoCreditoInstance )
		}
		
		//ASIGNA VALORES AL PREMOVIMIENTO
		PfinPreMovimiento preMovimientoInsertado = new PfinPreMovimiento(cuenta:  cuentaCliente,
			divisa: PfinDivisa.findByClaveDivisa('MXP'),
			fechaOperacion:fechaMedio, //FECHA DEL MEDIO
			fechaLiquidacion:fechaMedio, //FECHA DEL MEDIO
			importeNeto: pagoCreditoInstance.importePago,
			//referencia NO SE DEFINE AL CREAR EL PREMOVIMIENTO
			prestamo : pagoCreditoInstance.prestamo,
			nota : "Deposito de efectivo",
			listaCobro : 1,
			//pfinMovimiento()
			situacionPreMovimiento : 'NP',
			fechaRegistro:new Date(),
			logIpDireccion: 'xxxxxxxxx',
			logUsuario:'xxxxxxxxxx',
			logHost:'xxxxxxxxxx',
			usuario : usuario,
			fechaAplicacion:pagoCreditoInstance.fechaPago,
			numeroPagoAmortizacion: 0,
			operacion: PfinCatOperacion.findByClaveOperacion('TEDEPEFE'))
		try{
			// GENERA EL PREMOVIMIENTO
			preMovimientoInsertado = procesadorFinancieroService.generaPreMovimiento(preMovimientoInsertado)
		}catch(ProcesadorFinancieroServiceException errorProcesadorFinanciero){
			throw errorProcesadorFinanciero
		}
		
		return true
	}

}
