package com.sim.tablaAmortizacion

import com.sim.listacobro.ListaCobroParcial

class TablaAmorRegParcial {

	BigDecimal  impInteresPagado
	BigDecimal  impIvaInteresPagado
	BigDecimal  impCapitalPagado
	BigDecimal  impPagoPagado

    static belongsTo = [listaCobroParcial: ListaCobroParcial,
                        tablaAmortizacionRegistro: TablaAmortizacionRegistro]

    static hasMany = [tablaAmorAccParcial : TablaAmorAccParcial]

    static constraints = {
    	tablaAmortizacionRegistro()
    	listaCobroParcial()
		impInteresPagado()
		impIvaInteresPagado()
		impCapitalPagado()
		impPagoPagado()
        tablaAmorAccParcial()
    }

	String toString() {
		"${listaCobroParcial}, ${tablaAmortizacionRegistro}"
	}    
}
