package com.rs.gral

import com.sim.cliente.RsClienteEmpleo
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
	
	static belongsTo = [persona : RsPersona, sucursal : EntSucursal, empleo: RsClienteEmpleo]
	
    static constraints = {
		calle(size:5..100, nullable: false, blank: false)
		numeroInterior(nullable: true)
		numeroExterior(nullable: true)
		tipoVivienda inList:["PROPIA","RENTADA","FAMILIARES","HIPOTECADA","OTRO"], nullable :true, blank :true
		rsGralAsentamiento(nullable: false)
		esFiscal()
		comentarios(nullable: true,size:0..300)
		entreCalles size:0..300, nullable:true
		sucursal(nullable: true)
		persona (nullable: true)
        empleo  (nullable: true)
		aniosResidencia nullable: true
    }
	
	String toString() {
		"${calle} ${numeroInterior} ${numeroExterior} - CP:${rsGralAsentamiento.codigoPostal}"
	}
}
