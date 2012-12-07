package com.sim.producto

import  com.sim.catalogo.SimCatAccesorio
import com.sim.catalogo.SimCatFormaAplicacion

class ProPromocionAccesorio {
	
	SimCatAccesorio accesorio
	Integer         orden
	SimCatFormaAplicacion	formaAplicacion
	
	static belongsTo = [proPromocion : ProPromocion]

    static constraints = {
    	proPromocion()
    	accesorio (unique: false, nullable: false)
		orden (range: 1..20, unique: false, nullable: false)
		formaAplicacion()
    } 
    
	String toString() {
		"${proPromocion} - ${accesorio}"
	}
}
