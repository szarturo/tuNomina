package com.sim.entidad

import com.rs.gral.RsGralEstado

class EntRegion {

    String  claveRegion
    String  nombreRegion
    String  descripcionRegion

    static hasMany = [estados: RsGralEstado]

    static constraints = {
        claveRegion(size:3..15, unique: true, nullable: false, blank: false)
        nombreRegion(size:3..50, unique: true, nullable: false, blank: false)
        descripcionRegion(size:5..150, nullable: true)
        estados()
    }

    String toString() {
        "${nombreRegion}"
    }

}
