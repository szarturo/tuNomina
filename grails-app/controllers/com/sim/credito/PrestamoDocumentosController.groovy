package com.sim.credito

class PrestamoDocumentosController {

     def listaDocumentos() {
		
        def path = new File("${System.getProperty('user.home')}/Documents/tuNomina/imagenes/${params.id}")
		def imagenes = path.listFiles()
		 
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

}