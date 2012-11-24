package com.sim.credito

import com.sim.catalogo.SimCatAccesorio
import com.sim.catalogo.SimCatUnidad
import com.sim.catalogo.SimCatPeriodicidad


class PrestamoAccesorio {
	
	SimCatAccesorio 		accesorio
	BigDecimal				valor
	SimCatUnidad    		unidad
	SimCatPeriodicidad		periodicidad
	
	static belongsTo = [prestamo:Prestamo]

    static constraints = {
		accesorio()
		valor(nullable:false)
		unidad()
		periodicidad()
    }
	
	String toString() {
		"${accesorio}"
	}
}