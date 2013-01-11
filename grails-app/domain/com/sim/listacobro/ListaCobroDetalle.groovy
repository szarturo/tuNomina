package com.sim.listacobro

import com.sim.tablaAmortizacion.TablaAmortizacionRegistro
import com.sim.credito.PrestamoPago

class ListaCobroDetalle {

	ListaCobroDetalleEstatus  estatus
	TablaAmortizacionRegistro amortizacion
	PrestamoPago pago

	static belongsTo = [listaCobro: ListaCobro]

    static constraints = {
    	estatus nullable:false
    	listaCobro nullable:false
    	amortizacion nullable:false
    	pago nullable:true
    }
}
