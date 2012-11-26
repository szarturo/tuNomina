package com.sim.pfin

import com.sim.catalogo.SimCatAccesorio

class PfinCatConcepto {
	
	String claveConcepto
	String descripcionCorta
	String descripcionLarga
	String situacion

    static hasOne = [accesorio: SimCatAccesorio]

    static constraints = {
		claveConcepto(size:3..20, unique: true, nullable: false, blank: false)
		descripcionCorta(size:1..50, nullable: false, blank: false)
		descripcionLarga(size:1..150, nullable: false, blank: false)
		situacion(nullable: false, inList:["ACTIVO", "INACTIVO"]) 
		accesorio nullable : true
    }
	
	String toString() {
		"${descripcionCorta}"
	}

}
