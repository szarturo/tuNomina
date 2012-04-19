package com.rs.gral

import java.util.SortedSet;

class RsGralCiudad implements Comparable {

 	String  nombreCiudad 
	
	SortedSet delegacionMunicipio
	static belongsTo = [ estado : RsGralEstado ]
	static hasMany = [ delegacionMunicipio : RsGralDelegacionMunicipio ]

	static constraints = {
		nombreCiudad(size:3..50, unique: false,nullable: false, blank: false)
	}

	String toString() {
		"${nombreCiudad}"
	}
	
	int compareTo(obj) {
		nombreCiudad.compareTo(obj.nombreCiudad)
	}
	
	static mapping = {
		sort "nombreCiudad"
	}
}
