package com.sim.publicacion

class PublicacionLoteController {

	def publicacionLoteService

    def scaffold = true

    def publicarLote() {
        def resultado = publicacionLoteService.publicar()
    }    
}
