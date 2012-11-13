package com.sim.catalogo

class SimCatEvento {

	String evento;
	
    static constraints = {
		evento(nullable:false)
    }
	
	public String toString(){
		return evento;
	}
}
