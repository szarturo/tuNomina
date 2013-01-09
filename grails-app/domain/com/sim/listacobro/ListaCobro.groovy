package com.sim.listacobro

import com.sim.catalogo.SimCatListaCobroEstatus
import com.sim.entidad.EntDependencia
import com.sim.tablaAmortizacion.TablaAmortizacionRegistro

class ListaCobro implements Comparable {

    Integer anio
    Integer numeroPago
    Date    fechaInicio
    Date    fechaFin
    Boolean parcialidades = false
    SimCatListaCobroEstatus estatus

    static hasMany = [registros: TablaAmortizacionRegistro,
        listaCobroProceso:ListaCobroProceso]

    static belongsTo = [dependencia:EntDependencia]

    static constraints = {
        dependencia()
        anio range: 2012..2020
        numeroPago ()
        fechaInicio nullable: true
        fechaFin nullable: true
        estatus()
        listaCobroProceso()
        registros(nullable:true)
    }

    String toString() {
        "${dependencia}: ${numeroPago} - ${anio}"
    }    

    int compareTo(obj) {
        numeroPago.compareTo(obj.numeroPago)
    }

}
