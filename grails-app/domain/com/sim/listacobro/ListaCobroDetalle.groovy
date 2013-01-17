package com.sim.listacobro

import com.sim.tablaAmortizacion.TablaAmortizacionRegistro
import com.sim.credito.PrestamoPago
import com.sim.catalogo.SimCatTipoEmp
import com.sim.usuario.Usuario
import com.sim.publicacion.PublicacionDet
import com.sim.publicacion.PublicacionLote

class ListaCobroDetalle {

	ListaCobroDetalleEstatus  estatus
	TablaAmortizacionRegistro amortizacion
	PrestamoPago pago
    SimCatTipoEmp  tipoEmpleadoDep
    Usuario usuario

	static belongsTo = [listaCobro: ListaCobro,
        publicacionLote: PublicacionLote]
    static hasOne = [publicacionDet: PublicacionDet]

    static constraints = {
    	estatus nullable:false
    	listaCobro nullable:false
        publicacionLote nullable:true
    	amortizacion nullable:false
    	pago nullable:true
        tipoEmpleadoDep nullable:true
        usuario nullable:false
        publicacionDet nullable:true
    }

    String toString() {
        "${amortizacion} - ${estatus}"
    }       
}
