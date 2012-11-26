package com.sim.listacobro

import com.sim.catalogo.SimCatListaCobroEstatus

class ListaCobroFechaEst {

	Date fecha
	SimCatListaCobroEstatus estatusListaCobro

	static belongsTo = [listaCobroParcial: ListaCobroParcial]

    static constraints = {
    	fecha()
    	estatusListaCobro()
    	listaCobroParcial()
    }

   	String toString() {
        "${fecha} ${estatusListaCobro}"
    }    
}
