package com.sim.usuario

import java.util.Set;
import com.rs.gral.RsPersona;

class Usuario implements org.activiti.engine.identity.User {

	transient springSecurityService

	static belongsTo =   [ persona : RsPersona ]

	String username
	String password
	boolean enabled
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired
	
	//ACTIVITI SOLICITA LOS SIGUIENTES ATRIBUTOS
	String id
	String email
	String firstName
	String lastName
		
	boolean isPasswordEncoded = false

	static constraints = {
		username blank: false, unique: true
		password blank: false
		persona  nullable:true
		
		email email: true, blank: true, nullable : true
		firstName blank: true, nullable : true
		lastName blank: true, nullable : true
	}

	static mapping = {
		password column: '`password`'
		id generator: 'uuid'
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
