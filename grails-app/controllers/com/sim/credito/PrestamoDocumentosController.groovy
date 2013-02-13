package com.sim.credito

import com.sim.catalogo.SimCatDocumento
import com.sim.alfresco.AlfrescoService
import org.apache.chemistry.opencmis.client.api.Folder
import org.apache.chemistry.opencmis.client.api.CmisObject
import org.apache.chemistry.opencmis.client.api.Document
import org.grails.activiti.ApprovalStatus

class PrestamoDocumentosController {
	
	def springSecurityService
	def prestamoService

	static activiti = true

    def listaDocumentos(){

		String nombreCliente = params.cliente
		String claveCliente = params.claveCliente
		String folioSolicitud = params.folioSolicitud

		log.info "${System.getProperty('user.home')}/Documents/tuNomina/documentosCredito/${claveCliente}/${folioSolicitud}"
		
        def path = new File("${System.getProperty('user.home')}/Documents/tuNomina/documentosCredito/${claveCliente}/${folioSolicitud}")
		def imagenes = path.listFiles()

		try{
			AlfrescoService alfrescoService = new AlfrescoService();
			//EN CASO DE NO EXISTIR EL FOLDER DEL CREDITO SE CREA EN ALFRESCO
			Folder creditos= alfrescoService.getRootFolder();
			Folder folderCliente=alfrescoService.getFolderCliente(creditos, claveCliente);
			alfrescoService.getFolderCredito(folderCliente, folioSolicitud)
		}catch(Exception e){
				e.printStackTrace();
		}
		
        render(view: "documentos", model: [folioSolicitud:folioSolicitud, path:path, imagenes:imagenes, claveCliente:claveCliente])
    }
	 
	def obtenerImagen(){
		
		def fileName = params.fileName

        def path = new File("${System.getProperty('user.home')}/Documents/tuNomina/documentosCredito/${params.claveCliente}/${params.folioSolicitud}")
		
		def archivo = new File(path, fileName).readBytes()
		String longitud = archivo.length
		
		response.setHeader('Content-length', longitud)
		response.contentType = 'image/jpg' // or the appropriate image content type
		response.outputStream << archivo
		response.outputStream.flush()
	}

    def compararDocumentos(){
        def fileName1 = params.fileName0
        def fileName2 = params.fileName1
        def folioSolicitud = params.folioSolicitud
		def claveCliente = params.claveCliente

        render text: """
                <FRAMESET rows="50%, 50%">
                      <FRAME src="${createLink(action:'obtenerImagen')}?fileName=${fileName1}&claveCliente=${claveCliente}&folioSolicitud=${folioSolicitud}">
                      <FRAME src="${createLink(action:'obtenerImagen')}?fileName=${fileName2}&claveCliente=${claveCliente}&folioSolicitud=${folioSolicitud}">
                </FRAMESET>
        """,contentType: 'text/html'
    }
	
	def salvarAlfresco(){
		String nombreArchivo = params.nombreArchivo
		String folioSolicitud = params.folioSolicitud
		String claveCliente = params.claveCliente
		def path = new File("${System.getProperty('user.home')}/Documents/tuNomina/documentosCredito/${claveCliente}/${folioSolicitud}")
		def archivo = new File(path, nombreArchivo).readBytes()

		def user = springSecurityService.getCurrentUser()
		String username = user.username;
		log.info ("Usuario actual: ${username}")
		if (!username) username = "RUBEN"
		
		AlfrescoService alfrescoService = new AlfrescoService();
		alfrescoService.saveFile(nombreArchivo, archivo,"image/jpg", claveCliente, folioSolicitud, username);
	}

	def asignaNombre(){
		//SE OBTIENE LOS DOCUMENTOS QUE SE GUARDARON EN EL SERVIDOR
		File path = new File("${System.getProperty('user.home')}/Documents/tuNomina/documentosCredito/${params.idCliente}/${params.folioSolicitud}")
		List<File> documentos = path.listFiles()		

		//SE OBTIENEN LOS DOCUMENTOS QUE SE ASIGNARON AL PRESTAMO
		Prestamo prestamo = Prestamo.findByFolioSolicitud(params.folioSolicitud)	
		ArrayList documentosPrestamo = PrestamoDocumento.findAllByPrestamo(prestamo)
		//ARREGLO PARA PRESENTAR LOS DOCUMENTOS EN LA PANTALLA
		//NECESARIO PARA NO ENVIAR AQUELLOS DOCUMENTOS QUE HAYAN SIDO ELIMINADOS
		List documentosPrestamoActualizado = []

		//SE ITERAN LOS DOCUMENTOS DEL SERVIDOR
		documentos.each{ doctoServidor ->
			
			Boolean archivoEncontrado = false
			//SE ITERAN LOS DOCUMENTOS DEL PRESTAMO
			documentosPrestamo.each{ doctoPrestamo ->
				//VALIDAR SI EL DOCUMENTO GUARDADO EN EL SERVIDOR
				//EXISTE ASIGNADO AL PRESTAMO
				if (doctoServidor.name.equals(doctoPrestamo.nombreArchivo)){
					archivoEncontrado = true
					documentosPrestamoActualizado.add(doctoPrestamo)
				}
			}

			//SI EL ARCHIVO NO FUE ENCONTRADO EN LOS DOCUMENTOS DEL PRESTAMO
			//HAY QUE CREARLO
			if(!archivoEncontrado){
				PrestamoDocumento prestamoDocumentoCreado = new PrestamoDocumento(nombreArchivo: doctoServidor.name,
					documento: SimCatDocumento.findByClaveDocumento('NO_DEFINIDO'),
					prestamo: prestamo).save()
				documentosPrestamoActualizado.add(prestamoDocumentoCreado)

			}
		}

		model: [documentosPrestamo: documentosPrestamoActualizado,
			folioSolicitud:params.folioSolicitud, 
			idCliente:params.idCliente]
	}

	def guardaNombre(){

		File path = new File("${System.getProperty('user.home')}/Documents/tuNomina/documentosCredito/${params.idCliente}/${params.folioSolicitud}")
		List<File> imagenes = path.listFiles()	

		Prestamo prestamo = Prestamo.findByFolioSolicitud(params.folioSolicitud)	

		//SE OBTIENEN LOS DOCUMENTOS DEL PRESTAMO PARA SER ELIMINADOS
		ArrayList documentosPrestamo = PrestamoDocumento.findAllByPrestamo(prestamo)
		if(documentosPrestamo){
			documentosPrestamo.each() {
				prestamo.removeFromDocumentos(it)
			}
			prestamo.save()
		}

		imagenes.each{
			String idDocumento = request.getParameter("${it.name}")
			SimCatDocumento documento = SimCatDocumento.findById(idDocumento)
			new PrestamoDocumento(nombreArchivo: it.name,
				documento: documento,
				prestamo: prestamo).save()
		}
		redirect (action: "listaDocumentos" , params :[folioSolicitud:params.folioSolicitud, claveCliente:params.idCliente])
	}

	def enviaDocumento(){
        String respuesta = "No se genero respuesta"
        //SE RECUPERA EL PRESTAMO
        Prestamo prestamoInstance = Prestamo.get(params.idPrestamo)

        try{
        	respuesta = prestamoService.envioDocumentoCreditoReal(params.idDocumento)
	        //CAMBIA EL ESTATUS DEL PRESTAMO A PROCESADA
	        prestamoInstance.estatusSolicitud = PrestamoEstatus.PROCESADA
	        prestamoInstance.approvalStatus = ApprovalStatus.PENDING        
	        //COMPLETA LA TAREA
	        completeTask(params)
        }catch(PrestamoServiceException errorEnvioDocumentoCr){
        	flash.message = message(code: errorEnvioDocumentoCr.mensaje, args: [])
        	redirect (action: "asignaNombre" , params :[folioSolicitud:params.folioSolicitud, idCliente:params.idCliente])		
        	return
        }catch(Exception errorEnvioDocumento){
        	flash.message = message(code: "No se envio el Documento. Contacte al Administrador", args: [])
        	redirect (action: "asignaNombre" , params :[folioSolicitud:params.folioSolicitud, idCliente:params.idCliente])		
        	return
        }

        flash.message = message(code: "Respuesta de Credito Real para el documento ${respuesta}", args: [])
		redirect (action: "asignaNombre" , params :[folioSolicitud:params.folioSolicitud, idCliente:params.idCliente])		
	}

}