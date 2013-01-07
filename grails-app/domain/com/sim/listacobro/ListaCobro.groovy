package com.sim.listacobro

import com.sim.entidad.EntDependencia

class ListaCobro implements Comparable {

    Integer anio
    Integer numeroPago
    Date    fechaInicio
    Date    fechaFin
    Boolean parcialidades = false

    static hasMany = [listaCobroProceso:ListaCobroProceso,
                      detalle: ListaCobroDetalle]

    static belongsTo = [dependencia:EntDependencia]

    static constraints = {
        dependencia()
        anio range: 2012..2020
        numeroPago ()
        fechaInicio nullable: true
        fechaFin nullable: true
        listaCobroProceso()
        detalle()
    }

   String toString() {
        "${dependencia}: ${numeroPago} - ${anio}"
    }    

    int compareTo(obj) {
        numeroPago.compareTo(obj.numeroPago)
    }

}
