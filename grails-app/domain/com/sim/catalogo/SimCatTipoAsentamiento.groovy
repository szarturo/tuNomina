package com.sim.catalogo

import com.rs.gral.RsGralAsentamiento

class SimCatTipoAsentamiento {

	String  claveTipoAsentamiento
	String  nombreTipoAsentamiento

    static constraints = {
		claveTipoAsentamiento(size:2..15, unique: true, nullable: false, blank: false)
		nombreTipoAsentamiento(size:5..50, unique: true, nullable: false, blank: false)
    }

	String toString() {
		"${nombreTipoAsentamiento}"
	}
	
	static hasMany =  RsGralAsentamiento 
	//EN LA CLASE CONTROLADORA SE IMPLEMENTO QUE NO ELIMINE
	//UN TIPO DE ASENTAMIENTO SI ESTA DADO DE ALTA UN UN ASENTAMIENTO

	//IMPLEMENTAR LA FUNCIONALIDAD MENCIONADA, QUEDA COMO PENDIENTE
}
