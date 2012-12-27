package com.sim.entidad

import com.sim.listacobro.ListaCobro
import grails.converters.*

class EntDependenciaController {

    def scaffold = true

    def showListaCobro = {
        log.info("showListaCobro Inicio")
        EntDependencia dependencia = EntDependencia.findByClaveDependencia(params.claveDependencia)
    }

    def ajaxGetTipoEmpleado = {
		EntDependencia dependencia = EntDependencia.get(params.id)
    	render dependencia?.tiposEmpleado as JSON
    }    

    def ajaxGetPromocion = {
		EntDependencia dependencia = EntDependencia.get(params.id)
    	render dependencia?.promocion as JSON
    }    

}
