package com.sim.pfin

class PfinCatPais {

	String clavePais
	String nombrePais

	static constraints = {
		clavePais (size:3..10, unique: true, nullable: false, blank: false)
		nombrePais (size:3..30, unique: true, nullable: false, blank: false)
	}

	String toString() {
		"${nombrePais}"
	}
}