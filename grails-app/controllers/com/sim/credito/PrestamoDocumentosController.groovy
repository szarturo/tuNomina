package com.sim.credito

import com.sim.alfresco.AlfrescoService
import org.apache.chemistry.opencmis.client.api.Folder

class PrestamoDocumentosController {
	
	 def springSecurityService

     def listaDocumentos() {
		 
		log.info "folioSolicitud: ${params.folioSolicitud}"
		log.info "ClaveCliente ${params.claveCliente}"
		
		String nombreCliente = params.cliente
		String claveCliente = params.claveCliente
		String folioSolicitud = params.folioSolicitud

		log.info "${System.getProperty('user.home')}/Documents/tuNomina/documentosCredito/${claveCliente}/${folioSolicitud}"
		
        def path = new File("${System.getProperty('user.home')}/Documents/tuNomina/documentosCredito/${claveCliente}/${folioSolicitud}")
		def imagenes = path.listFiles()
		 
		AlfrescoService alfrescoService = new AlfrescoService();
		//EN CASO DE NO EXISTIR EL FOLDER DEL CREDITO SE CREA EN ALFRESCO
		Folder creditos= alfrescoService.getRootFolder();
		Folder folderCliente=alfrescoService.getFolderCliente(creditos, claveCliente);
		alfrescoService.getFolderCredito(folderCliente, folioSolicitud)
		
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

}