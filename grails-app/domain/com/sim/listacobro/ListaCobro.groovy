package com.sim.listacobro

import com.sim.catalogo.SimCatListaCobroEstatus
import com.sim.catalogo.SimCatPeriodicidad
import com.sim.entidad.EntDependencia
import com.sim.tablaAmortizacion.TablaAmortizacionRegistro

class ListaCobro implements Comparable {

    Integer anio
    Integer numeroPago
    Date    fechaInicio
    Date    fechaFin
    Boolean parcialidades = false
    SimCatListaCobroEstatus estatus
    SimCatPeriodicidad periodicidad

    static hasMany = [amortizaciones: TablaAmortizacionRegistro,
        listaCobroProceso:ListaCobroProceso]

    static belongsTo = [dependencia:EntDependencia]

    static mapping = {
        amortizaciones cascade: "all-delete-orphan"
     }    

    static constraints = {
        dependencia()
        anio range: 2012..2020
        periodicidad ()
        numeroPago ()
        fechaInicio nullable: true
        fechaFin nullable: true
        estatus()
        listaCobroProceso()
        amortizaciones(nullable:true)
    }

    String toString() {
        "${dependencia}: ${numeroPago} - ${anio}"
    }    

    int compareTo(obj) {
        numeroPago.compareTo(obj.numeroPago)
    }
}