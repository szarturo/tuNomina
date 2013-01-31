package com.sim.publicacion

import com.sim.usuario.Usuario
import com.sim.listacobro.ListaCobroDetalle
import com.sim.listacobro.ListaCobroDetalleEstatus
import com.sim.pfin.PfinCatParametro
import com.sim.credito.PrestamoCrComprada

import mx.com.creditoreal.ws.client.Client
import mx.com.creditoreal.ws.exception.ClientException

class PublicacionLoteServiceException extends RuntimeException {
	String mensaje
}

class PublicacionLoteService {

	static transactional = true
    //SERVICIO PARA RECUPERAR EL USUARIO
    def springSecurityService    	

    Boolean publicar() {
    	Usuario usuario = springSecurityService.getCurrentUser()
    	log.info ("Usuario: ${usuario}")

    	//SE OBTIENE LA FECHA DEL MEDIO
		PfinCatParametro parametros = PfinCatParametro.findByClaveMedio("SistemaMtn")
		Date fechaMedio = parametros?.fechaMedio
		if (!fechaMedio){
			throw new PublicacionLoteServiceException(mensaje: "No existe la fecha del medio")
		}

    	//SE CREA EL REGISTRO DE LA PUBLICACION LOTE
		PublicacionLote publicacionLote =
				new PublicacionLote(fechaMedio:  fechaMedio,
					fechaRegistro: new Date(),
					importeLote: 0,
					usuario: usuario,
					).save()

		//SE OBTIENEN LOS DETALLES DE LAS LISTAS DE COBRO QUE SE 
		//HAYAN APLICADO DEL USUARIO
		def criteriaDetallesListaCobro = 
			ListaCobroDetalle.createCriteria()
		ArrayList detalleRegistros = 
			criteriaDetallesListaCobro.list() {
			and {
				eq("usuario",usuario)
				le("estatus",ListaCobroDetalleEstatus.APLICADO)
			}
		}

		Double importeTotalPublicacion = 0

		//ATRIBUTOS PARA ENVIAR LA PUBLICACION A CREDITO REAL
		String usuarioWs = 'Usuario'
		String passwordWs = 'Password'
		//SE OBTIENE EL ID
		//!!!VERIFICAR QUE FUNCIONE CON ALTA CONCURRENCIA!!!
		parametros.consecutivoPublicacion =
			parametros.consecutivoPublicacion + 1
		parametros.save(flush:true,failOnError:true)

		Integer idWs = parametros.consecutivoPublicacion
		//NUMERO DE CLIENTE QUE SE ASIGNO AL CREDITO POR PARTE DE CR
		String numeroClienteWs
		//NUMERO DE OPERACION QUE SE ASIGNO AL CREDITO POR PARTE DE CR
		String numeroOperacionWs		
		String claveCiaWs
		//SE REFIERE A LA RESPUESTA DE CR: ClaveTienda	
		String claveSucursalWs 
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

		String respuesta
		detalleRegistros.each{
			log.info "Detalle: ${it}"
			//SE INDICA EL IMPORTE TOTAL DE LA PUBLICACION
			importeTotalPublicacion = importeTotalPublicacion + it.pago.importePago

			//SE OBTIENE LOS DATOS DEL PRESTAMO COMPRADO
			PrestamoCrComprada prestamoComprado = it.pago.prestamo.datosCrComprada
			log.info "Solicitud Comprada: ${prestamoComprado}"

			//SE OBTIENE EL NUMERO DE CLIENTE
			numeroClienteWs = prestamoComprado.numeroSolicitud
			log.info "Numero de Cliente: ${numeroClienteWs}"

			//SE OBTIENE EL NUMERO DE OPERACION
			numeroOperacionWs = prestamoComprado.numeroOperacion
			log.info "Numero de Operacion: ${numeroOperacionWs}"

			//SE OBTIENE LA CLAVE DE CIA			
			claveCiaWs = prestamoComprado.claveCia
			log.info "Clave Cia: ${claveCiaWs}"

			//SE OBTIENE LA CLAVE DE LA SUCURSAL
			claveSucursalWs = prestamoComprado.claveSucursal
			log.info "Clave Sucursal: ${claveSucursalWs}"			

			importePagoWs = it.pago.importePago
			log.info "Importe Pago: ${importePagoWs}"

			//CONVIERTE A STRING
			String sId = idWs
			String sImportePago = importePagoWs 
			String sImporteMoratorios = importeMoratoriosWs					

			referenciaWs = it.pago.prestamo.datosCrRespuesta.referencia
			log.info "Referencia: ${referenciaWs}"

			Date fechaPago = it.pago.fechaPago
			Calendar calFechaPago = fechaPago.toCalendar()

			fechaPagoAnioWs = calFechaPago.get(Calendar.YEAR)
			fechaPagoMesWs = calFechaPago.get(Calendar.MONTH) + 1
			fechaPagoDiaWs = calFechaPago.get(Calendar.DATE)

			try {
				//TRUE SIGNIFICA QUE ENVIA A UN WEBSERVICE DE CREDITO REAL EN UN AMBIENTE DE PRUEBAS
				Client cliente = new Client(PfinCatParametro.findByClaveMedio("SistemaMtn").pruebasClienteWsCr)
				respuesta = cliente.recepcionPago(
					usuarioWs,
					passwordWs,
					sId,
					numeroClienteWs,
					numeroOperacionWs,
					claveCiaWs,
					claveSucursalWs,
					tipoPagoWs,
					conceptoWs,
					sImportePago,
					sImporteMoratorios,
					referenciaWs,
					fechaPagoMesWs,
					fechaPagoDiaWs,
					fechaPagoAnioWs)

			} catch (ClientException e) {
				// TODO Auto-generated catch block
				throw new PublicacionLoteServiceException(mensaje: "Se genero un plobema de comunicación con Crédito Real.")
			}

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
				respuestaCr: respuesta,			
			).save(failOnError: true)
			//ACTUALIZA EL ESTATUS DEL DETALLE DE LISTA DE COBRO
			it.estatus = ListaCobroDetalleEstatus.PUBLICADO
		}
		//SE INDICA LA CANTIDAD TOTAL DEL LOTE		
		publicacionLote.importeLote = importeTotalPublicacion

		return true
    }
}
