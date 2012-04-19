package com.rs.gral

import java.util.SortedSet;

class RsGralDelegacionMunicipio implements Comparable {

	String  nombreDelegacionMunicipio
	
	SortedSet asentamiento
	static hasMany   = [ asentamiento : RsGralAsentamiento ]
	static belongsTo = [ ciudad : RsGralCiudad ]

	static constraints = {
		nombreDelegacionMunicipio(size:3..50, unique: false,nullable: false, blank: false)
	}

	String toString() {
		"${nombreDelegacionMunicipio}"
	}
	
	int compareTo(obj) {
		nombreDelegacionMunicipio.compareTo(obj.nombreDelegacionMunicipio)
	}
	static mapping = {
		sort "nombreDelegacionMunicipio"
	}

	
}
