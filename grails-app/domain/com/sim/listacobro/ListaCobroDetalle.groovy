package com.sim.listacobro

import com.sim.tablaAmortizacion.TablaAmortizacionRegistro
import com.sim.credito.PrestamoPago
import com.sim.catalogo.SimCatTipoEmp
import com.sim.usuario.Usuario
import com.sim.publicacion.PublicacionDet

class ListaCobroDetalle {

	ListaCobroDetalleEstatus  estatus
	TablaAmortizacionRegistro amortizacionReal
    TablaAmortizacionRegistro amortizacionPrimerPago
	PrestamoPago pago
    SimCatTipoEmp  tipoEmpleadoDep
    Usuario usuario

	static belongsTo = [listaCobro: ListaCobro]
    static hasOne = [publicacionDet: PublicacionDet]

    static constraints = {
    	estatus nullable:false
    	listaCobro nullable:false
    	amortizacionReal nullable:false
        amortizacionPrimerPago nullable:true
    	pago nullable:true
        tipoEmpleadoDep nullable:true
        usuario nullable:false
        publicacionDet nullable:true
    }

    String toString() {
        "${amortizacionReal} - ${estatus}"
    }       
}
