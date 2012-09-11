package com.sim.entidad

class EntSucursal {

    String  claveSucursal
    String  nombreSucursal
    String  descripcionSucursal

    static hasMany = [oficinas: EntOficina, delegaciones: EntDelegacion]
    static belongsTo = [regional: EntRegion]

    static constraints = {
        claveSucursal(size:3..15, unique: true, nullable: false, blank: false)
        nombreSucursal(size:3..50, unique: true, nullable: false, blank: false)
        descripcionSucursal(size:5..150, nullable: true)
        oficinas nullable : true
        delegaciones nullable : true
    }

    String toString() {
        "${nombreSucursal}"
    }

}
