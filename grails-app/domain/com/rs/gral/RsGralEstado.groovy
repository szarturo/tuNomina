package com.rs.gral

import com.sim.entidad.EntSucursal
import com.sim.entidad.EntRegion

class RsGralEstado {

	String  cveEstado
 	String  nombreEstado
	String  aliasEstado
	
	SortedSet ciudad
	static hasMany = [ ciudad : RsGralCiudad, sucursal: EntSucursal ]
	static belongsTo = [region: EntRegion]

	static constraints = {
		cveEstado(size:2..20, unique: true,nullable: false, blank: false)
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
