package com.sim.tablaAmortizacion

import com.sim.catalogo.SimCatAccesorio
import com.sim.catalogo.SimCatFormaAplicacion

class TablaAmortizacionAccesorio {

	SimCatAccesorio accesorio
	SimCatFormaAplicacion	formaAplicacion
	BigDecimal		importeAccesorio
	BigDecimal		importeIvaAccesorio
	BigDecimal  	importeAccesorioPagado
	BigDecimal  	importeIvaAccesorioPagado

	static belongsTo = [tablaAmortizacion:TablaAmortizacion]

	static constraints = {
		accesorio()
		formaAplicacion() 			
		importeAccesorio			nullable:false
		importeIvaAccesorio			nullable:false
		importeAccesorioPagado		nullable:true
		importeIvaAccesorioPagado	nullable:true
	}

	String toString() {
		"${tablaAmortizacion} - ${accesorio}"
	}
}
