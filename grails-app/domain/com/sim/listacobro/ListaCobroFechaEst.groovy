package com.sim.listacobro

import com.sim.catalogo.SimCatListaCobroEstatus

class ListaCobroFechaEst {

	Date fecha
	SimCatListaCobroEstatus estatusListaCobro

	static belongsTo = [listaCobro :ListaCobro]

    static constraints = {
    	fecha()
    	estatusListaCobro()
        listaCobro(nullable : true)
    }

   	String toString() {
        "${fecha}  ${listaCobro ?: listaCobroParcial}"
    }    
}
