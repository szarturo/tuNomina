package com.sim.publicacion

import com.sim.usuario.Usuario
import com.sim.listacobro.ListaCobroDetalle
import com.sim.listacobro.ListaCobroDetalleEstatus
import com.sim.pfin.PfinCatParametro

class PublicacionLoteException extends RuntimeException {
	String mensaje
}

class PublicacionLoteService {

	static transactional = true
    //SERVICIO PARA RECUPERAR EL USUARIO
    def springSecurityService    	

    def publicar() {
    	Usuario usuario = springSecurityService.getCurrentUser()
    	log.info ("Usuario: ${usuario}")

    	//SE OBTIENE LA FECHA DEL MEDIO
		PfinCatParametro parametros = PfinCatParametro.findByClaveMedio("SistemaMtn")
		Date fechaMedio = parametros?.fechaMedio
		if (!fechaMedio){
			throw new PublicacionLoteException(mensaje: "No existe la fecha del medio")
		}

    	//SE CREA EL REGISTRO DE LA PUBLICACION LOTE
		PublicacionLote publicacionLote =
				new PublicacionLote(fechaMedio:  fechaMedio,
					fechaRegistro: new Date(),
					importeLote: 0,
					usuario: usuario,
					).save()

		def criteriaDetallesListaCobro = 
			ListaCobroDetalle.createCriteria()
		ArrayList detalleRegistros = 
			criteriaDetallesListaCobro.list() {
			and {
				eq("usuario",usuario)
				le("estatus",ListaCobroDetalleEstatus.APLICADO)
			}
		}

		Double totalPublicacion = 0

		String usuarioWs = 'Usuario'
		String passwordWs = 'Password'
		//IMPLEMENTAR QUE SEA INCREMENTABLE E IRREPETIBLE
		Integer idWs = 1
		//NUMERO DE CLIENTE QUE SE ASIGNO AL CREDITO POR PARTE DE CR
		String numeroClienteWs
		//NUMERO DE OPERACION QUE SE ASIGNO AL CREDITO POR PARTE DE CR
		String numeroOperacionWs		
		String claveCiaWs = '999'
		//SE REFIERE A LA RESPUESTA DE CR: ClaveTienda	
		String claveSucursalWs = '001'
		//EN QUE CASOS SE ENVIA 'CAN' PARA CANCELACIONES
		String tipoPagoWs = 'NOR'		
		String conceptoWs = '51'
		BigDecimal importePagoWs 
		BigDecimal importeMoratoriosWs = 0
		//SE REFIERE A LA RESPUESTA DE CR: Referencia
		String referenciaWs		
		String fechaPagoMesWs	
		String fechaPagoDiaWs	
		String fechaPagoAnioWs			

		detalleRegistros.each{
			log.info ("Detalle: ${it}")
			//SE INDICA EL IMPORTE TOTAL DE LA PUBLICACION
			totalPublicacion = totalPublicacion + it.pago.importePago

			//SE OBTIENE EL NUMERO DE CLIENTE
			//FALTA DAR FUNCIONALIDAD AL DOMINIO DatosCrRespuesta
			numeroClienteWs = '11' //it.pago.prestamo.datosCrRespuesta.numeroCliente

			//SE OBTIENE EL NUMERO DE OPERACION
			//FALTA DAR FUNCIONALIDAD AL DOMINIO DatosCrRespuesta
			numeroOperacionWs = '22' //it.pago.prestamo.datosCrRespuesta.numeroOperacion

			importePagoWs = it.pago.importePago

			referenciaWs = '0000099990-1' // it.pago.prestamo.datosCrRespuesta.referencia

			Date fechaPago = it.pago.fechaPago
			Calendar calFechaPago = fechaPago.toCalendar()

			fechaPagoAnioWs = calFechaPago.get(Calendar.YEAR)
			fechaPagoMesWs = calFechaPago.get(Calendar.MONTH) + 1
			fechaPagoDiaWs = calFechaPago.get(Calendar.DATE)

			//SE CREA EL DETALLE DE LA PUBLICACION
			PublicacionDet publicacionDet = new PublicacionDet(
				usuario:  usuarioWs,
				password: passwordWs,
				idCr: idWs,
				numeroCliente: numeroClienteWs,
				numeroOperacion: numeroOperacionWs,
				claveCia: claveCiaWs,
				claveSucursal: claveSucursalWs,
				tipoPago: tipoPagoWs,
				concepto:conceptoWs,
				importePago: importePagoWs,
				importeMoratorios: importeMoratoriosWs,
				referencia: referenciaWs,
				fechaPagoMes: fechaPagoMesWs,
				fechaPagoDia: fechaPagoDiaWs,
				fechaPagoAnio: fechaPagoAnioWs,
				publicacionLote: publicacionLote,
				listaCobroDetalle: it,				
				).save(failOnError: true)

			log.info ("OKKKK")

		}
    }
}
