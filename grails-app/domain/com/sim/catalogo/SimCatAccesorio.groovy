package com.sim.catalogo

import com.sim.pfin.PfinCatConcepto

class SimCatAccesorio {

	SimCatTipoAccesorio  tipoAccesorio
	
    static belongsTo = [concepto : PfinCatConcepto]

    static constraints = {
		tipoAccesorio   (nullable: false)
		
    }
	String toString() {
		"${concepto.descripcionCorta}"
	}
}
