package com.sim.catalogo

class SimCatAccesorio {
	String  claveAccesorio
	String  nombreAccesorio
	SimCatTipoAccesorio  tipoAccesorio
	

    static constraints = {
		claveAccesorio  (size:3..80, unique: true, nullable: false, blank: false)
		nombreAccesorio (size:3..80, unique: true, nullable: false, blank: false)
		tipoAccesorio   (nullable: false, blank: false)
		
    }
	String toString() {
		"${nombreAccesorio}"
	}
}
