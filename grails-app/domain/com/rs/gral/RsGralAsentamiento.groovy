package com.rs.gral

import com.sim.catalogo.SimCatTipoAsentamiento
import java.util.SortedSet

class RsGralAsentamiento implements Comparable{

 	String  nombreAsentamiento
	String  codigoPostal
	
	static constraints = {
		nombreAsentamiento(size:3..50, unique: false,nullable: false, blank: false)
		codigoPostal(size:5..5, unique: false,nullable: false, blank: false)
	}
	
	static belongsTo = [ delegacionMunicipio : RsGralDelegacionMunicipio, tipoAsentamiento : SimCatTipoAsentamiento ]
	
	String toString() {
		"""ESTADO:${delegacionMunicipio.ciudad.estado},
		CIUDAD:${delegacionMunicipio.ciudad},
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
