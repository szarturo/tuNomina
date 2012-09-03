package com.sim.entidad

class Sucursal {

    String  claveSucursal
    String  nombreSucursal
    String  descripcionSucursal

    static hasMany = [oficinas: Oficina, delegaciones: Delegacion]

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
