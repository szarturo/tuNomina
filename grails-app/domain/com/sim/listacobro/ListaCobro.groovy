package com.sim.listacobro

import com.sim.entidad.EntDependencia
import com.sim.tablaAmortizacion.TablaAmortizacionRegistro

class ListaCobro implements Comparable {

    Integer anio
    Integer numeroPago
    Date    fechaInicio
    Date    fechaFin
    Boolean parcialidades = false

    static hasMany = [registroTablaAmor:TablaAmortizacionRegistro]
    static belongsTo = [dependencia:EntDependencia]

    static constraints = {
        dependencia()
        anio range: 2012..2020
        numeroPago range: 1..56
        fechaInicio nullable: true
        fechaFin nullable: true
    }

   String toString() {
        "Lista de Cobro: ${numeroPago} - ${anio}"
    }    

    int compareTo(obj) {
        numeroPago.compareTo(obj.numeroPago)
    }

}
