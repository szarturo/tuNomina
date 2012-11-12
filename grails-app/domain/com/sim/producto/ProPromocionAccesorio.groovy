package com.sim.producto

import  com.sim.catalogo.SimCatAccesorio

class ProPromocionAccesorio {
	
	SimCatAccesorio accesorio
	Integer         orden
	
	static belongsTo = [proPromocion : ProPromocion]

    static constraints = {
		accesorio (unique: true, nullable: false, blank: false)
		orden (range: 1..20, unique: true, nullable: false, blank: false)
		proPromocion()
    } 
	String toString() {
		"${proPromocion} - ${accesorio}"
	}
}
