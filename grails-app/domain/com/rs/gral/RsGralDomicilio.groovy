package com.rs.gral

//import com.sim.regional.SimRegional
//import com.sim.regional.SimSucursal
//import com.sim.cliente.SimClienteNegocio

class RsGralDomicilio {

	String  calle
	String  numeroInterior
	String  numeroExterior
	String  tipoVivienda
	Boolean esFiscal = false
	String  entreCalles
	Integer aniosResidencia
	String  comentarios
	 
	
	//RsGralAsentamiento rsGralAsentamiento
	
	/*
	static belongsTo = [ regional : SimRegional, sucursal : SimSucursal, persona : RsPersona,
						 negocio : SimClienteNegocio]
	*/

	static belongsTo = [persona : RsPersona]
	
    static constraints = {
		calle(size:5..100, nullable: false, blank: false)
		numeroInterior()
		numeroExterior()
		tipoVivienda inList:["PROPIA","RENTADA","FAMILIARES","HIPOTECADA","OTRO"], nullable :true, blank :true
		//rsGralAsentamiento(nullable: false)
		esFiscal()
		comentarios(size:0..300)
		entreCalles size:0..300, nullable:true
		//regional(nullable: true)
		//sucursal(nullable: true)
		persona(nullable: true)
		aniosResidencia nullable: true
		//negocio(nullable: true)
    }
	
	String toString() {
		//"${calle} ${numeroInterior} ${numeroExterior} - CP:${rsGralAsentamiento.codigoPostal}"
		"${calle} ${numeroInterior} ${numeroExterior}"
	}
}
