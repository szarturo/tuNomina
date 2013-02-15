package com.sim.entidad

import grails.converters.*

class EntDependenciaController {

    static scaffold = true

    def ajaxGetTipoEmpleado = {
		EntDependencia dependencia = EntDependencia.get(params.id)
    	render dependencia?.tiposEmpleado as JSON
    }    

    def ajaxGetPromocion = {
		EntDependencia dependencia = EntDependencia.get(params.id)
    	render dependencia?.promocion as JSON
    }    

}
