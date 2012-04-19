package com.rs.gral

import com.sim.usuario.Usuario
//import com.sim.empresa.RsEmpleado
import com.sim.catalogo.SimCatDocumento
import com.sim.catalogo.SimCatEscolaridad
import com.sim.catalogo.SimCatTipoPersona
//import com.sim.cliente.RsCliente;
//import com.sim.cliente.RsReferenciaCliente;

class RsPersona {

	String email
	String apellidoPaterno
	String apellidoMaterno
	String primerNombre
	String segundoNombre
	String nombreAlterno
	String sexo
	String estadoCivil
	Date   fechaNacimiento
	String numeroIdentificacionOficial
	String rfc
	String curp

	SimCatDocumento   identificacionOficial
	SimCatEscolaridad escolaridad
	Usuario           usuario
	
	//static hasOne  = [ datosEmpleado : RsEmpleado, datosCliente : RsCliente, referenciaCliente : RsReferenciaCliente]
	
	//RELACION DE MUCHOS A MUCHOS ENTRE TIPO DE PERSONA Y PERSONA
	//static hasMany = [ telefonos : RsGralTelefono, domicilios : RsGralDomicilio, tiposPersona : SimCatTipoPersona]
	static hasMany = [tiposPersona : SimCatTipoPersona]
	
	static constraints = {
		email email:true, nullable: true,  validator: { correo, rsPersona ->
			if (rsPersona.tiposPersona?.claveTipoPersona?.contains('USUARIO')) correo != null }
		apellidoPaterno size:3..25, blank: false, unique: false
		apellidoMaterno nullable: true, size:0..25
		primerNombre size:3..25, blank: false, unique: false
		segundoNombre nullable: true, size:0..25
		sexo(nullable: true, inList:["MASCULINO", "FEMENINO"] )
		estadoCivil nullable: true, inList:["CASADO - BIENES MANCOMUNADOS",
			"CASADO - BIENES SEPARADOS",
			"DIVORCIADO",
			"SOLTERO",
			"UNION LIBRE",
			"VIUDO"]
		fechaNacimiento(nullable:true)
		//telefonos()
		//domicilios()
		nombreAlterno nullable: true, size:0..50
		identificacionOficial nullable: true
		numeroIdentificacionOficial nullable: true
		rfc nullable: true
		curp nullable: true
		escolaridad  nullable: true
		//AL MODIFICAR UNA PERSONA SE VALIDA QUE SI TIENE ASIGNADO UN USUARIO LA PERSONA DEBE TENER TIPO DE USUARIO IGUAL A USUARIO
		//EN LA ALTA DE USUARIO SE ASIGNA EN LA CLASE CONTROLADORA UserController
		tiposPersona nullable: true, validator: { tipoPersona, rsPersona ->
			if (rsPersona.usuario) tipoPersona?.claveTipoPersona?.contains('USUARIO') }
		usuario nullable:true, unique: true
		//datosEmpleado nullable: true
		//datosCliente nullable: true
		//referenciaCliente nullable: true
	}
	
	String toString() {
		"${apellidoPaterno} ${apellidoMaterno ?: ""} ${primerNombre} ${segundoNombre ?: ""}"
	}
	
	
}
