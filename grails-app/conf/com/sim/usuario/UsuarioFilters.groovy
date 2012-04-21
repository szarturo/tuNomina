package com.sim.usuario

class UsuarioFilters {

    def springSecurityService

    def filters = {
        all(uri: '/**') {
            after = { model ->
                if (model && springSecurityService.isLoggedIn()) {
					// SE MODIFICO EN NOMBRE DE user A usuario YA QUE INTERFERIA
					// CON LAS PANTALLAS DE CRUD DE USUARIO.
					// EL MODELO usuario ES VISIBLE EN LAS JSP'S
                    model['usuario'] = Usuario.get(springSecurityService.principal.id)
                }
            }
        }
    }
}
