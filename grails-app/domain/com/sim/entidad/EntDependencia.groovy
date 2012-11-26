package com.sim.entidad

import com.sim.listacobro.ListaCobro
import com.sim.catalogo.SimCatPeriodicidad

class EntDependencia {

    String  claveDependencia
    String  nombreDependencia
    String  descripcionDependencia
    SimCatPeriodicidad periodicidadPagoNomina

    SortedSet listaCobro

    static hasMany = [listaCobro: ListaCobro]

    static constraints = {
        claveDependencia(size:3..15, unique: true, nullable: false, blank: false)
        nombreDependencia(size:3..50, unique: true, nullable: false, blank: false)
        descripcionDependencia(size:5..150, nullable: true)
        periodicidadPagoNomina()        
        listaCobro(nullable: true)
    }

    String toString() {
        "${nombreDependencia}"
    }
}
