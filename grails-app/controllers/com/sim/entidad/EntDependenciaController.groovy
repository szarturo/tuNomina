package com.sim.entidad

class EntDependenciaController {

    def scaffold = true

    def generarListasCobro = {

    	log.info params

    	EntDependencia dependencia = EntDependencia.findByClaveDependencia(params.claveDependencia)
    	Integer anio = params.anio.toInteger()

    	log.info dependencia
    	log.info anio



    }
}
