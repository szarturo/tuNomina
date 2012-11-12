package com.sim.tablaAmortizacion

import com.sim.credito.Prestamo
import com.sim.catalogo.SimCatAccesorio
import com.sim.catalogo.SimCatFormaAplicacion


class TablaAmortizacionAccesorio {

	Integer     	numeroPagoAmortizacion
	SimCatAccesorio accesorio
	SimCatFormaAplicacion	formaAplicacion
	BigDecimal		importeAccesorio
	BigDecimal		importeIvaAccesorio
	BigDecimal  	importeAccesorioPagado
	BigDecimal  	importeIvaAccesorioPagado

	static belongsTo = [tablaAmortizacion:TablaAmortizacion]

	static constraints = {
		numeroPagoAmortizacion() 		
		accesorio()
		formaAplicacion() 			
		importeAccesorio			nullable:false
		importeIvaAccesorio			nullable:false
		importeAccesorioPagado		nullable:true
		importeIvaAccesorioPagado	nullable:true
	}

	String toString() {
		"Pago no: ${numeroPagoAmortizacion} - ${accesorio}"
	}
}
