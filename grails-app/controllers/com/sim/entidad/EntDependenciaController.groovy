package com.sim.entidad

import com.sim.listacobro.ListaCobro

class EntDependenciaController {

    def scaffold = true

    def generarListasCobro = {

    	EntDependencia dependencia = EntDependencia.findByClaveDependencia(params.claveDependencia)
    	Integer anio = params.anio.toInteger()


    	Integer pagoNomina = 1

    	(1..dependencia.periodicidadPagoNomina.cantidadPagos).each{

			new ListaCobro(anio:  anio,
				numeroPago: pagoNomina,
				parcialidades: false,
				dependencia: dependencia,
				).save(flush: true)

    		pagoNomina ++
    	}


    }
}
