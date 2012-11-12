package com.sim.catalogo

class SimCatAccesorio {
	SimCatTipoAccesorio  tipoAccesorio
	String         nombreAccesorio

    static constraints = {
		tipoAccesorio (nullable: false, blank: false)
		nombreAccesorio (size:3..80, unique: true, nullable: false, blank: false)
    }
	String toString() {
		"${nombreAccesorio}"
	}
}
