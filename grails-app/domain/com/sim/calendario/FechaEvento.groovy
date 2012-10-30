package com.sim.calendario

class FechaEvento {

	Evento evento;
	
	Dependencia dependencia;
	
	Date fecha;
	
    static constraints = {
		evento(nullable:false)
		dependencia(nullable:false)
		fecha(nullable:false)
    }
}
