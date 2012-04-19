package com.sim.catalogo

class SimCatEscolaridad {

	String  claveEscolaridad
	String  nombreEscolaridad
	
    static constraints = {
		claveEscolaridad(size:3..20, unique: true, nullable: false, blank: false)
		nombreEscolaridad(size:5..50, unique: true, nullable: false, blank: false)
    }

	String toString() {
		"${nombreEscolaridad}"
	}
}
