package com.sim.entidad

import com.sim.listacobro.ListaCobro

class EntDependenciaController {

    def scaffold = true

    def showListaCobro = {
        log.info("showListaCobro Inicio")
        EntDependencia dependencia = EntDependencia.findByClaveDependencia(params.claveDependencia)
    }

}
