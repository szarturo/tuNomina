package com.sim.tablaAmortizacion

import com.sim.catalogo.SimCatAccesorio

class TablaAmortizacionAccesorio {

	SimCatAccesorio accesorio
	BigDecimal		importeAccesorio
	BigDecimal		importeIvaAccesorio
	BigDecimal  	importeAccesorioPagado
	BigDecimal  	importeIvaAccesorioPagado

	static belongsTo = [tablaAmortizacion   : TablaAmortizacionRegistro]

	static constraints = {
		accesorio()			
		importeAccesorio			nullable:false
		importeIvaAccesorio			nullable:false
		importeAccesorioPagado		nullable:true
		importeIvaAccesorioPagado	nullable:true
	}

	String toString() {
		"${accesorio}. Pago: ${importeAccesorio}. Pagado: ${importeAccesorioPagado}"
	}
}
