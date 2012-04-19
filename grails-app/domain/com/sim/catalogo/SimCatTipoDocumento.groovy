package com.sim.catalogo

class SimCatTipoDocumento {
	String  claveTipoDocumento
	String  nombreTipoDocumento

	static hasMany = [ documentos : SimCatDocumento ]

	static constraints = {
		claveTipoDocumento(size:3..15, unique: true, nullable: false, blank: false)
		nombreTipoDocumento(size:3..50, unique: true, nullable: false, blank: false)
	}

	String toString() {
		"${nombreTipoDocumento}"
	}
}
