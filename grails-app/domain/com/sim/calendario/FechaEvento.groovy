package com.sim.calendario
import com.sim.entidad.EntDependencia

class FechaEvento {

	Evento evento;
	
	EntDependencia dependencia;
	
	Date fecha;
	
    static constraints = {
		evento(nullable:false)
		dependencia(nullable:false)
		fecha(nullable:false)
    }
}
