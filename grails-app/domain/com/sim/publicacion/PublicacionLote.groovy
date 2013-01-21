package com.sim.publicacion

import com.sim.listacobro.ListaCobroDetalle
import com.sim.usuario.Usuario

class PublicacionLote {

	Date fechaAplicaion
	Date fechaMedio
	Date fechaRegistro
	BigDecimal importeLote
	Usuario usuario

	static hasMany = [listaCobroDet: ListaCobroDetalle]

    static constraints = {
    	importeLote()
    	fechaAplicaion()
    	fechaMedio()
    	fechaRegistro()
    	listaCobroDet()
    }
}
