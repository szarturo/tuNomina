package com.rs.gral

import com.sim.catalogo.SimCatTipoAsentamiento
import java.util.SortedSet

class RsGralAsentamiento implements Comparable{

	String  nombreAsentamiento
	String  codigoPostal 
	String 	tipoAsentamiento

	static constraints = {
		nombreAsentamiento(size:3..100, unique: false,nullable: false, blank: false)
		codigoPostal(size:5..5, unique: false,nullable: false, blank: false)
		tipoAsentamiento(size:3..100, unique: false,nullable: false, blank: false)
	}

	static belongsTo = [ delegacionMunicipio : RsGralDelegacionMunicipio]

	String toString() {
		"""ESTADO:${delegacionMunicipio.estado},
		DELEGACION:${delegacionMunicipio},
		${nombreAsentamiento} ${codigoPostal}"""
	}

	int compareTo(obj) {
		nombreAsentamiento.compareTo(obj.nombreAsentamiento)
	}

	static mapping = {
		sort "nombreAsentamiento"
	}

}
