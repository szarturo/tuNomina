package com.sim.credito

class PrestamoDocumentosController {

     def listaDocumentos() {
		
		 def path = new File("${System.getProperty('user.home')}/Documents/tuNomina/imagenes/${params.id}")
		 def imagenes = path.listFiles()
		 
        render(view: "documentos", model: [clavePrestamo:params.id, path:path, imagenes:imagenes])
    }
	 
	def obtenerImagen(){
		
		def fileName = params.fileName
		def savePath = params.ruta
		
		println fileName
		println savePath
	
		def archivo = new File(savePath, fileName).readBytes()
		String longitud = archivo.length
		
		response.setHeader('Content-length', longitud)
		response.contentType = 'image/jpg' // or the appropriate image content type
		response.outputStream << archivo
		response.outputStream.flush()

		
	}
}
