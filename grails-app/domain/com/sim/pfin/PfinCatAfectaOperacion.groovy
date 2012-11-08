package com.sim.pfin

class PfinCatAfectaOperacion {

	String claveAfecta
	String descripcionAfecta
	String comentarios

    static constraints = {
		claveAfecta(size:3..20, unique: true, nullable: false, blank: false)
		descripcionAfecta(size:1..50, nullable: false, blank: false)
		comentarios(size:1..150, nullable: true, blank: true)
    }
	
	String toString() {
		"${descripcionAfecta}"
	}
}
