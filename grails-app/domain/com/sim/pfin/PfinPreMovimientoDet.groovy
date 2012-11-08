package com.sim.pfin

class PfinPreMovimientoDet {
	
	PfinCatConcepto concepto
	BigDecimal  importeConcepto
	String      nota

	static belongsTo = [preMovimiento:PfinPreMovimiento]

	static constraints = {
		concepto(nullable: false)
		importeConcepto(nullable: false)
		nota(size:3..80,nullable: false, blank: false)
		preMovimiento(nullable: false)
	}
	
	String toString() {
		"${nota} - ${importeConcepto}"
	}

}
