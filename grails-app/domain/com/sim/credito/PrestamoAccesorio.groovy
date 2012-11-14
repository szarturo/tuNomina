package com.sim.credito

import com.sim.catalogo.SimCatAccesorio
import com.sim.catalogo.SimCatFormaAplicacion
import com.sim.catalogo.SimCatUnidad
import com.sim.catalogo.SimCatPeriodicidad


class PrestamoAccesorio {
	
	SimCatAccesorio 		accesorio
	SimCatFormaAplicacion	formaAplicacion
	BigDecimal				valor
	SimCatUnidad    		unidad
	SimCatPeriodicidad		periodicidad
	
	static belongsTo = [prestamo:Prestamo]

    static constraints = {
		accesorio()
		formaAplicacion()
		valor(nullable:false)
		unidad()
		periodicidad()
    }
	
	String toString() {
		"${accesorio}"
	}
}