package com.sim.entidad

class EntOficina {

    String  claveOficina
    String  nombreOficina
    String  descripcionOficina

    static belongsTo = [sucursal: EntSucursal]

    static constraints = {
        claveOficina(size:3..15, unique: true, nullable: false, blank: false)
        nombreOficina(size:3..50, unique: true, nullable: false, blank: false)
        descripcionOficina(size:5..150, nullable: true)
        sucursal nullable: true
    }

    String toString() {
        "${nombreOficina}"
    }
}
