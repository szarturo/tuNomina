package com.sim.listacobro

import com.sim.tablaAmortizacion.TablaAmorRegParcial

class ListaCobroParcial {

    Integer numeroParcialidad

    static belongsTo = [listaCobro:ListaCobro]

    static hasMany = [tablaAmorRegParcial: TablaAmorRegParcial]

    static constraints = {
    }
}
