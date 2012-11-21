package com.sim.listacobro

import com.sim.entidad.EntDependencia

class ListaCobro {

    Integer anio
    Integer numeroPago
    Date    fechaInicio
    Date    fechaFin
    Boolean parcialidades = false

    static belongsTo = [dependencia:EntDependencia]

    static constraints = {
        dependencia()
        anio range: 2012..2020
        numeroPago range: 1..56
        fechaInicio nullable: true
        fechaFin nullable: true

    }
}
