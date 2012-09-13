package com.sim.credito

import com.sim.cliente.RsCliente

class Prestamo {

	String clavePrestamo

    static belongsTo = [cliente : RsCliente]
	
    static constraints = {
		clavePrestamo(size:1..20, unique: true, nullable: false, blank: false)

    }
}
