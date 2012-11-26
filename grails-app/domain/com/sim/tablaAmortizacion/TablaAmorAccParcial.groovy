package com.sim.tablaAmortizacion

class TablaAmorAccParcial {

	BigDecimal  	importeAccesorioPagado
	BigDecimal  	importeIvaAccesorioPagado

	static belongsTo = [tablaAmortizacionAccesorio: TablaAmortizacionAccesorio,
						tablaAmorRegParcial: TablaAmorRegParcial]

    static constraints = {
    	importeAccesorioPagado()
    	importeIvaAccesorioPagado()
    	tablaAmorRegParcial()
    	tablaAmortizacionAccesorio()
    }

	String toString() {
		"${tablaAmorRegParcial} - ${tablaAmortizacionAccesorio}"
	}    
}
