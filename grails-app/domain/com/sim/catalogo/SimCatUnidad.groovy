package com.sim.catalogo

class SimCatUnidad {
	
	String      claveUnidad
	String		nombreUnidad
	Integer		valor

    static constraints = {
		claveUnidad(size: 5..20, nullable: false, blank: false)
		nombreUnidad(size: 5..20, nullable: false, blank: false)
		valor()
    }
	
	String toString() {
		"${nombreUnidad}"
	}
}
