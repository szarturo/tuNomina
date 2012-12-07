package com.sim.entidad

import com.sim.listacobro.ListaCobro
import com.sim.catalogo.SimCatPeriodicidad
import com.sim.producto.ProPromocion

class EntDependencia {

    String  claveDependencia
    String  nombreDependencia
    String  descripcionDependencia
    SimCatPeriodicidad periodicidadPagoNomina

    SortedSet listaCobro

    static hasMany = [listaCobro: ListaCobro, promocion: ProPromocion]

    static constraints = {
        claveDependencia(size:3..15, unique: true, nullable: false, blank: false)
        nombreDependencia(size:3..100, unique: false, nullable: false, blank: false)
        descripcionDependencia(size:5..150, nullable: true)
        periodicidadPagoNomina()        
        listaCobro(nullable: true)
        promocion(nullable: true)
    }

    String toString() {
        "${nombreDependencia}"
    }
}
