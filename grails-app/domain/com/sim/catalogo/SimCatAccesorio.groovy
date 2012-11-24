package com.sim.catalogo

import com.sim.pfin.PfinCatConcepto

class SimCatAccesorio {

	SimCatTipoAccesorio  tipoAccesorio
	
    static belongsTo = [concepto: PfinCatConcepto]

    static constraints = {
		tipoAccesorio   (nullable: false, blank: false)
		
    }
	String toString() {
		"${concepto.descripcionCorta}"
	}
}
