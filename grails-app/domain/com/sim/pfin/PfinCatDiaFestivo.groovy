package com.sim.pfin

class PfinCatDiaFestivo {

	PfinCatPais  pais
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
