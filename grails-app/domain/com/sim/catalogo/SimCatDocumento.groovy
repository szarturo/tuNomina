package com.sim.catalogo

class SimCatDocumento {

	String  	  claveDocumento
	String  	  nombreDocumento
	String  	  descripcion
	Boolean 	  esReporte = false
	//SimCatReporte simCatReporte

	static belongsTo = [ tipoDocumento : SimCatTipoDocumento ]

    static constraints = {
		claveDocumento(size:3..30, unique: true, nullable: false, blank: false)
		nombreDocumento(size:5..50, nullable: false, blank: false)
		descripcion(size:10..300, nullable: true, blank: true)
		tipoDocumento(nullable:true)
		esReporte()
		//simCatReporte nullable: true, unique: true //RELACION UNO A UNO Y CON OPCION A SER NULO
    }

	String toString() {
		"${nombreDocumento}"
	}
}
