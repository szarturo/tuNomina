package com.sim.catalogo

class SimCatDocumento {

	String  	  claveDocumento
	String  	  nombreDocumento
	String  	  descripcion

	static belongsTo = [ tipoDocumento : SimCatTipoDocumento ]

    static constraints = {
		claveDocumento(size:3..40, unique: true, nullable: false, blank: false)
		nombreDocumento(size:5..80, nullable: false, blank: false)
		descripcion(size:10..300, nullable: true, blank: true)
		tipoDocumento(nullable:true)
    }

	String toString() {
		"${nombreDocumento}"
	}
}
