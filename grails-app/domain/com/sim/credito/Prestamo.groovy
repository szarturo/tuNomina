package com.sim.credito

class Prestamo {

	String clavePrestamo
	
    static constraints = {
		clavePrestamo(size:1..20, unique: true, nullable: false, blank: false)
    }
}
