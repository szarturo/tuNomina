package com.sim.catalogo

class SimCatTipoAccesorio {
	String claveTipoAccesorio
	String nombreTipoAccesorio

    static constraints = {
		claveTipoAccesorio (size:3..15, unique: true, nullable: false, blank: false)
		nombreTipoAccesorio (size:3..80, unique: true, nullable: false, blank: false)
    }
	
	String toString() {
		"${nombreTipoAccesorio}"
	}
}
