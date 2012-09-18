package com.sim.catalogo

class SimCatEtapaPrestamo {

	String  claveEtapaPrestamo
	String  nombreEtapaPrestamo
	String  descripcionEtapaPrestamo

    static constraints = {
		claveEtapaPrestamo(size:3..15, unique: true, nullable: false, blank: false)
		nombreEtapaPrestamo(size:5..50, unique: true, nullable: false, blank: false)
		descripcionEtapaPrestamo(size:5..250, unique: true, nullable: false, blank: false)
    }

	String toString() {
		"${nombreEtapaPrestamo}"
	}
}
