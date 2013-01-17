package com.sim.publicacion

class PublicacionLoteController {

    def scaffold = true

    def publicarLote() {
        log.info "Paso publicarLote"
        redirect(action: "list", params: params)
    }    
}
