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
        listaCobro()
    	listaCobroParcial()
    }

   	String toString() {
        "${fecha}  ${listaCobro ?: listaCobroParcial}"
    }    
}
