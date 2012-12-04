package com.sim.cliente

import com.sim.catalogo.SimCatDescTelefono

class RsClienteReferencia {

	String             nombreCompleto
	String             telefono
	SimCatDescTelefono descripcionTelefono

	static belongsTo = [cliente : RsCliente]
	
    static constraints = {
    	cliente(nullable: false)
    	nombreCompleto nullable: false, size:5..50
  		telefono(size:5..15, unique: false, nullable: false, blank: false)
		descripcionTelefono(nullable: false)
    }

    String toString() {
        "${nombreCompleto} - ${telefono}"
    }
}
