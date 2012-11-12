package com.sim.pfin

import java.util.Date;

class PfinSaldo {
	
	Date       fechaFoto
	PfinDivisa divisa
	BigDecimal saldo

	static belongsTo = [cuenta:PfinCuenta]
	
    static constraints = {
		fechaFoto(nullable:false)
		cuenta(nullable:true)
		divisa(nullable:false)
		saldo(nullable:false)
    }
	
	String toString() {
		"${cuenta.id} - ${fechaFoto}"
	}
}
