package com.sim.entidad

class Region {

    String  claveRegion
    String  nombreRegion
    String  descripcionRegion

    static hasMany = [sucursales: Sucursal]

    static constraints = {
        claveRegion(size:3..15, unique: true, nullable: false, blank: false)
        nombreRegion(size:3..50, unique: true, nullable: false, blank: false)
        descripcionRegion(size:5..150, nullable: true)
    }

    String toString() {
        "${nombreRegion}"
    }

}
