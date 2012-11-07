package com.sim.tablaAmortizacion

import com.sim.credito.Prestamo

class TablaAmortizacion {

	Integer     numeroDePago
	BigDecimal  interes
	BigDecimal  amortizacionCapital
	BigDecimal  pagoTotal
	BigDecimal  saldoInsoluto

	static belongsTo = [prestamo:Prestamo]

	static constraints = {
		numeroDePago range:1..360
		interes scale:2, nullable:true
		amortizacionCapital scale:2, nullable:true
		pagoTotal scale:2, nullable:true
		saldoInsoluto scale:2, nullable:true
	}

	String toString() {
		"Pago no: ${numeroDePago}"
	}
}
