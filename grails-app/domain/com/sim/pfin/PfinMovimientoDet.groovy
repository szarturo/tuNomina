package com.sim.pfin

class PfinMovimientoDet {

	PfinCatConcepto   concepto
	BigDecimal        importeConcepto
	String            nota

	static belongsTo = [movimiento:PfinMovimiento]

	static constraints = {
		concepto(nullable: false)
		importeConcepto(nullable: false)
		nota(size:3..80,nullable: false, blank: false)
		movimiento(nullable: false)
	}

	String toString() {
		"${nota} - ${importeConcepto}"
	}
}
