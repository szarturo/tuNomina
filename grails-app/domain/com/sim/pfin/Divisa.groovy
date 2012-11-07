package com.sim.pfin

class Divisa {

	String claveDivisa
	String descripcionDivisa
	
    static constraints = {
		claveDivisa(size:3..10, unique: true, nullable: false, blank: false)
		descripcionDivisa(size:10..20, unique: true, nullable: false, blank: false)
    }
	
	String toString() {
		"${descripcionDivisa} "
	}
}
