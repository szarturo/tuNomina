package com.sim.tablaAmortizacion

import com.sim.listacobro.ListaCobroParcial

class TablaAmorRegParcial {

    Integer 	numeroPagoParcial
	BigDecimal  impInteresPagado
	BigDecimal  impIvaInteresPagado
	BigDecimal  impCapitalPagado
	BigDecimal  impPagoPagado

    static belongsTo = [listaCobroParcial: ListaCobroParcial,
                        tablaAmortizacionRegistro: TablaAmortizacionRegistro]

    static constraints = {
    	numeroPagoParcial()
    	tablaAmortizacionRegistro()
    	listaCobroParcial()
		impInteresPagado()
		impIvaInteresPagado()
		impCapitalPagado()
		impPagoPagado()
    }

	String toString() {
		"${tablaAmortizacionRegistro}. Pago Parcial: ${numeroPagoParcial}"
	}    
}
