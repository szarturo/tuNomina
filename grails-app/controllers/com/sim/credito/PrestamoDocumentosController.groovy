package com.sim.credito

import com.sim.alfresco.AlfrescoService
import org.apache.chemistry.opencmis.client.api.Folder

class PrestamoDocumentosController {

     def listaDocumentos() {
		
        def path = new File("${System.getProperty('user.home')}/Documents/tuNomina/imagenes/${params.id}")
		def imagenes = path.listFiles()
		 
		AlfrescoService alfrescoService = new AlfrescoService();
		//EN CASO DE NO EXISTIR EL FOLDER DEL CREDITO SE CREA EN ALFRESCO
		Folder creditos= alfrescoService.getRootFolder();
		Folder folderCliente=alfrescoService.getFolderCliente(creditos, "clientex1");
		alfrescoService.getFolderCredito(folderCliente, params.id)
		
        render(view: "documentos", model: [clavePrestamo:params.id, path:path, imagenes:imagenes])
    }
	 
	def obtenerImagen(){
		
		def fileName = params.fileName

        def path = new File("${System.getProperty('user.home')}/Documents/tuNomina/imagenes/${params.clavePrestamo}")
		
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
        def clavePrestamo = params.clavePrestamo

        render text: """
                <FRAMESET rows="50%, 50%">
                      <FRAME src="${createLink(action:'obtenerImagen')}?fileName=${fileName1}&clavePrestamo=${clavePrestamo}">
                      <FRAME src="${createLink(action:'obtenerImagen')}?fileName=${fileName2}&clavePrestamo=${clavePrestamo}">
                </FRAMESET>
        """,contentType: 'text/html'
    }
	
	def salvarAlfresco(){
		String nombreArchivo = params.nombreArchivo
		String clavePrestamo = params.clavePrestamo
		def path = new File("${System.getProperty('user.home')}/Documents/tuNomina/imagenes/${clavePrestamo}")
		def archivo = new File(path, nombreArchivo).readBytes()
		
		AlfrescoService alfrescoService = new AlfrescoService();
		alfrescoService.saveFile(nombreArchivo, archivo,"image/jpg", "clientex1", clavePrestamo);
	}

}