package com.sim.entidad

import com.rs.gral.*

class EntSucursal {

    String  claveSucursal
    String  nombreSucursal
    String  descripcionSucursal
    String  tipoSucursal

    static hasMany = [delegaciones: EntDelegacion, domicilios : RsGralDomicilio]
    static belongsTo = [estado: RsGralEstado]
    
    static constraints = {
        claveSucursal(size:3..15, unique: true, nullable: false, blank: false)
        nombreSucursal(size:3..50, unique: true, nullable: false, blank: false)
        descripcionSucursal(size:5..150, nullable: true)
		domicilios()
        delegaciones nullable : true
        tipoSucursal inList:["CORPORATIVA","SUCURSAL FISICA","HOME OFFICE"], nullable :false, blank :false
    }

    String toString() {
        "${nombreSucursal}"
    }

}
