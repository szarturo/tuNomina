package com.sim.listacobro

class ListaCobroDetalle {

	static belongsTo = [listaCobro :ListaCobro]

    static constraints = {
    	listaCobro()
    }
}
