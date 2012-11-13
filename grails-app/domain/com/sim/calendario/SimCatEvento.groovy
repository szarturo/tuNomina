package com.sim.calendario

class SimCatEvento {

	String evento;
	
    static constraints = {
		evento(nullable:false)
    }
	
	public String toString(){
		return evento;
	}
}
