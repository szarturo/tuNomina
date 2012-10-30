package com.sim.calendario

class Evento {

	String evento;
	
    static constraints = {
		evento(nullable:false)
    }
	
	public String toString(){
		return evento;
	}
}
