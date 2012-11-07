package com.sim.pfin

class CatOperacionConcepto {

	CatOperacion operacion
	CatConcepto  concepto
	String claveAfecta
	String situacion

    static constraints = {
		operacion(nullable: false, blank: false)
		concepto(nullable: false, blank: false)
		claveAfecta(nullable: false, inList:["INCREMENTA", "DECREMENTA"])
		situacion(nullable: false, inList:["ACTIVO", "INACTIVO"]) 
    }
	
	String toString() {
		"${operacion.descripcionCorta} - ${concepto.descripcionCorta}"
	}
}
