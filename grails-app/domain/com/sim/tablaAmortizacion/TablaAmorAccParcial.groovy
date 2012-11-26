package com.sim.tablaAmortizacion

class TablaAmorAccParcial {

	BigDecimal  	importeAccesorioPagado
	BigDecimal  	importeIvaAccesorioPagado

	static belongsTo = [tablaAmorAccesorio: TablaAmortizacionAccesorio,
						tablaAmorRegParcial: TablaAmorRegParcial]

    static constraints = {
    	importeAccesorioPagado()
    	importeIvaAccesorioPagado()
    	tablaAmorRegParcial()
    	tablaAmorAccesorio()
    }

	String toString() {
		"${tablaAmorRegParcial} - ${tablaAmorAccesorio}"
	}    
}
