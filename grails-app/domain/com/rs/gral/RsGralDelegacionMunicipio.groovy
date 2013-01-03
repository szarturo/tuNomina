package com.rs.gral

import java.util.SortedSet;

class RsGralDelegacionMunicipio implements Comparable {

	String  nombreDelegacionMunicipio
	String  nombreCiudad

	SortedSet asentamiento
	static hasMany   = [ asentamiento : RsGralAsentamiento ]
	static belongsTo = [ estado : RsGralEstado ]

	static constraints = {
		nombreDelegacionMunicipio(size:3..50, unique: false,nullable: false, blank: false)
		nombreCiudad(size:3..50, unique: false, nullable: true, blank: true)
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
