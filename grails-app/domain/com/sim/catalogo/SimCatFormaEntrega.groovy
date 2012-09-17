package com.sim.catalogo

class SimCatFormaEntrega {

    String  claveFormaEntrega
    String  nombreFormaEntrega

    static constraints = {
        claveFormaEntrega(size:3..15, unique: true, nullable: false, blank: false)
        nombreFormaEntrega(size:5..50, unique: true, nullable: false, blank: false)
    }
    String toString() {
        "${nombreFormaEntrega}"
    }
}
