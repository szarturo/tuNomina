package com.sim.calendario
import com.sim.catalogo.SimCatEvento;
import com.sim.entidad.EntDependencia

class FechaEvento {

	SimCatEvento evento;
	
	EntDependencia dependencia;
	
	Date fecha;
	
    static constraints = {
		evento(nullable:false)
		dependencia(nullable:false)
		fecha(nullable:false)
    }
}
