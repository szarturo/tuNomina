package com.sim.catalogo

class SimCatListaCobroEstatus {

    String  claveListaEstatus
    String  nombreListaEstatus
    String  descripcionListaEstatus

    static constraints = {
        claveListaEstatus(size:3..15, unique: true, nullable: false, blank: false)
        nombreListaEstatus(size:5..50, unique: true, nullable: false, blank: false)
        descripcionListaEstatus(nullable: true, size:5..150)
    }

    String toString() {
        "${nombreListaEstatus}"
    }


}
