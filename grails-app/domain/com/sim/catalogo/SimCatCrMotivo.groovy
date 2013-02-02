package com.sim.catalogo

class SimCatCrMotivo {

	String codigo
	String proceso
	String descripcion

    static constraints = {
    	codigo()
    	proceso()
    	descripcion size:2..80
    }

    String toString() {
		"${codigo} - ${descripcion}"
	}
}
