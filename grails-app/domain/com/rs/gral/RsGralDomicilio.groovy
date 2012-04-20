package com.rs.gral

//import com.sim.regional.SimRegional
//import com.sim.regional.SimSucursal
//import com.sim.cliente.SimClienteNegocio

class RsGralDomicilio {

	String  calle
	String  numeroInterior
	String  numeroExterior
	Boolean esFiscal = false
	String  comentarios
	
	RsGralAsentamiento rsGralAsentamiento
	
	/*
	static belongsTo = [ regional : SimRegional, sucursal : SimSucursal, persona : RsPersona,
						 negocio : SimClienteNegocio]
	*/

	static belongsTo = [persona : RsPersona]

	
    static constraints = {
		calle(size:5..100, nullable: false, blank: false)
		numeroInterior()
		numeroExterior()
		rsGralAsentamiento(nullable: false)
		esFiscal()
		comentarios(size:0..300)
		//regional(nullable: true)
		//sucursal(nullable: true)
		persona(nullable: true)
		//negocio(nullable: true)
    }
	
	String toString() {
		"${calle} ${numeroInterior} ${numeroExterior} - CP:${rsGralAsentamiento.codigoPostal}"
	}
}
