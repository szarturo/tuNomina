package com.sim.pfin

import java.util.Date;

class Saldo {
	
	Date       fechaFoto
	//Divisa divisa
	BigDecimal saldo

	static belongsTo = [cuenta:Cuenta]
	
    static constraints = {
		fechaFoto(nullable:false)
		cuenta(nullable:true)
		//divisa(nullable:false)
		saldo(nullable:false)
    }
	
	String toString() {
		"${cuenta.id} - ${fechaFoto}"
	}
}
