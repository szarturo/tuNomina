package com.sim.pfin

class PfinCatOperacion {

	String claveOperacion
	String claveAfectaSaldo
	String descripcionCorta
	String descripcionLarga
	String situacion
    PfinCatAfectaOperacion afecta
	

    static constraints = {
		claveOperacion(size:3..20, unique: true, nullable: false, blank: false)
		claveAfectaSaldo(nullable: false, inList:["INCREMENTA", "DECREMENTA"])
		descripcionCorta(size:1..50, nullable: false, blank: false)
		descripcionLarga(size:1..150, nullable: false, blank: false)
		situacion(nullable: false, inList:["ACTIVO", "INACTIVO"]) 
		afecta(nullable: true, blank: true)
    }
	
	String toString() {
		"${descripcionCorta}"
	}
}
