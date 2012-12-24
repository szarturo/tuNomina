package com.sim.catalogo

class SimCatTipoEmp {

    String	claveTipoEmpleadoDep
	String	nombreTipoEmpleadoDep

    static constraints = {
    	claveTipoEmpleadoDep(size:2..20, unique: true, nullable: false, blank: false)
		nombreTipoEmpleadoDep(size:5..50, unique: true, nullable: false, blank: false)
    }

    String toString() {
        "${nombreTipoEmpleadoDep}"
    }
}
