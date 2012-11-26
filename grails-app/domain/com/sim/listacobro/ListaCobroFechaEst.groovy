package com.sim.listacobro

import com.sim.catalogo.SimCatListaCobroEstatus

class ListaCobroFechaEst {

	Date fecha
	SimCatListaCobroEstatus estatusListaCobro

	static belongsTo = [listaCobro :ListaCobro,
                        listaCobroParcial: ListaCobroParcial]

    static constraints = {
    	fecha()
    	estatusListaCobro()
        listaCobro(nullable : true)
    	listaCobroParcial(nullable : true)
    }

   	String toString() {
        "${fecha}  ${listaCobro ?: listaCobroParcial}"
    }    
}
