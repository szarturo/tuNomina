package com.sim.publicacion

import com.sim.listacobro.ListaCobroDetalle

class PublicacionLote {

	BigDecimal importeLote

	static hasMany = [listaCobroDet: ListaCobroDetalle]

    static constraints = {
    	importeLote()
    	listaCobroDet()
    }
}
