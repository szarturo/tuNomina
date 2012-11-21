package com.sim.entidad

import com.sim.listacobro.ListaCobro

class EntDependencia {

    String  claveDependencia
    String  nombreDependencia
    String  descripcionDependencia

    static hasMany = [listaCobro: ListaCobro]


    static constraints = {
        claveDependencia(size:3..15, unique: true, nullable: false, blank: false)
        nombreDependencia(size:3..50, unique: true, nullable: false, blank: false)
        descripcionDependencia(size:5..150, nullable: true)
        listaCobro(nullable: true)
    }

    String toString() {
        "${nombreDependencia}"
    }
}
