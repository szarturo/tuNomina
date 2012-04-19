package com.rs.gral

class RsGralEstado {

	String  cveEstado
 	String  nombreEstado
	String  aliasEstado
	
	SortedSet ciudad
	static hasMany = [ ciudad : RsGralCiudad ]
	
	static constraints = {
		cveEstado(size:2..8, unique: true,nullable: false, blank: false)
		nombreEstado(size:3..50, unique: true,nullable: false, blank: false)
		aliasEstado(nullable: true)
	}
	
	String toString() {
		"${nombreEstado}"
	}
	
	static mapping = {
		sort "nombreEstado"
	}
}
