package com.rs.gral

import com.sim.entidad.EntSucursal

class RsGralDomicilio {

	String  calle
	String  numeroInterior
	String  numeroExterior
	String  tipoVivienda
	Boolean esFiscal = false
	String  entreCalles
	Integer aniosResidencia
	String  comentarios
	
	RsGralAsentamiento rsGralAsentamiento
	
	static belongsTo = [persona : RsPersona, sucursal : EntSucursal]
	
    static constraints = {
		calle(size:5..100, nullable: false, blank: false)
		numeroInterior()
		numeroExterior()
		tipoVivienda inList:["PROPIA","RENTADA","FAMILIARES","HIPOTECADA","OTRO"], nullable :true, blank :true
		rsGralAsentamiento(nullable: false)
		esFiscal()
		comentarios(nullable: true,size:0..300)
		entreCalles size:0..300, nullable:true
		sucursal(nullable: true)
		persona (nullable: true)
		aniosResidencia nullable: true
    }
	
	String toString() {
		"${calle} ${numeroInterior} ${numeroExterior} - CP:${rsGralAsentamiento.codigoPostal}"
	}
}
