package com.sim.pfin

class PfinCatConcepto {
	
	String claveConcepto
	String descripcionCorta
	String descripcionLarga
	String situacion

    static constraints = {
		claveConcepto(size:3..20, unique: true, nullable: false, blank: false)
		descripcionCorta(size:1..50, nullable: false, blank: false)
		descripcionLarga(size:1..150, nullable: false, blank: false)
		situacion(nullable: false, inList:["ACTIVO", "INACTIVO"]) 
    }
	
	String toString() {
		"${descripcionCorta}"
	}

}