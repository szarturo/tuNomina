package com.sim.listacobro

import com.sim.tablaAmortizacion.TablaAmortizacionRegistro
import com.sim.credito.PrestamoPago
import com.sim.catalogo.SimCatTipoEmp
import com.sim.usuario.Usuario

class ListaCobroDetalle {

	ListaCobroDetalleEstatus  estatus
	TablaAmortizacionRegistro amortizacion
	PrestamoPago pago
    SimCatTipoEmp  tipoEmpleadoDep
    Usuario usuario

	static belongsTo = [listaCobro: ListaCobro]

    static constraints = {
    	estatus nullable:false
    	listaCobro nullable:false
    	amortizacion nullable:false
    	pago nullable:true
        tipoEmpleadoDep nullable:true
        usuario nullable:false
    }

    String toString() {
        "${amortizacion} - ${estatus}"
    }       
}
