package com.sim.usuario

import com.sim.entidad.EntRegion
import com.sim.entidad.EntSucursal

class UsuarioAcceso {

	Usuario usuario
	Boolean accesoTodo

	static hasMany = [regiones: EntRegion,
					sucursales: EntSucursal]

    static constraints = {
    	usuario nullable:false, unique:true
    	accesoTodo()
    	regiones nullable:true
    	sucursales nullable:true
    }

    String toString() {
        "${usuario}"
    }    
}
