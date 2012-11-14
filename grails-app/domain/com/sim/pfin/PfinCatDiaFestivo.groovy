package com.sim.pfin

import com.sim.catalogo.SimCatPais

class PfinCatDiaFestivo {

	SimCatPais   pais
	Date         fechaDiaFestivo
	String       descripcionDiaFestivo


	static constraints = {
		pais(nullable:false)
		fechaDiaFestivo(nullable:false)
		descripcionDiaFestivo (size:5..50, unique: true, nullable: false, blank: false)
	}

	String toString() {
		"${fechaDiaFestivo} - ${descripcionDiaFestivo}"
	}
}
