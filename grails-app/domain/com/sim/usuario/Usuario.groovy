package com.sim.usuario

import com.rs.gral.RsPersona;

class Usuario {

	transient springSecurityService

	static belongsTo =   [ persona : RsPersona ]

	String username
	String password
	boolean enabled
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired
	
	boolean isPasswordEncoded = false

	static constraints = {
		username blank: false, unique: true
		password blank: false
		persona  nullable:true
	}

	static mapping = {
		password column: '`password`'
		//Here we configure GORM to load the associated persona instance (through the persona property) whenever an Usuario is loaded.
		persona lazy:false
	}

	Set<Rol> getAuthorities() {
		UsuarioRol.findAllByUsuario(this).collect { it.rol } as Set
	}

	//CAMBIO DEBIDO A LA UTILIZACION DE MAS DE UN DATASOURCE
	def beforeInsert() {
		if ( !isPasswordEncoded )
		{
			isPasswordEncoded = true
			encodePassword ()
		}
	}

	/*
	def beforeInsert() {
		encodePassword()
	}*/

	def beforeUpdate() {
		if (isDirty('password')) {
			isPasswordEncoded = false
			encodePassword()
		}
	}
	
	/*
	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
	}*/

	protected void encodePassword() {
		password = springSecurityService.encodePassword(password)
	}


	String toString() {
		"${username}"
	}
}
