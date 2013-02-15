package com.sim.publicacion

class PublicacionLoteController {

	def publicacionLoteService

    static scaffold = true

    def publicarLote() {

    	try{
    		Boolean resultado = publicacionLoteService.publicar()	
        }catch(PublicacionLoteServiceException errorPublicacionLote){
            log.error "Failed:", errorPublicacionLote
            flash.message = message(code: errorPublicacionLote.mensaje, args: [])
            redirect(action: "list")
            return            
        }catch(Exception error){
            log.error "Failed:", error
            flash.message = message(code: "Se genero un problema de comunicación. Contacte al Administrador.", args: [])
            redirect(action: "list")
            return            
        }

        flash.message = message(code: "Se ha generado la publicación.", args: [])
        redirect(action: "list")
    }
         
}
