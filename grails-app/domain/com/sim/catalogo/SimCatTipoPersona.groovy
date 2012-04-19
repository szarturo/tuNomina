package com.sim.catalogo

import com.rs.gral.RsPersona

class SimCatTipoPersona {

  	String  claveTipoPersona
	String  nombreTipoPersona
	String  descripcionTipoPersona
	
	//RELACION DE MUCHOS A MUCHOS ENTRE TIPO DE PERSONA Y PERSONA
	static hasMany = RsPersona 
	static belongsTo = RsPersona

    static constraints = {
		claveTipoPersona(size:3..15, unique: true, nullable: false, blank: false)
		nombreTipoPersona(size:3..50, unique: true, nullable: false, blank: false)
		descripcionTipoPersona(size:3..150, nullable: true)
    }

	String toString() {
		"${nombreTipoPersona}"
	}
}
