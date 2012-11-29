package com.sim.entidad
import com.rs.gral.RsGralEstado

class EntSucursal {

    String  claveSucursal
    String  nombreSucursal
    String  descripcionSucursal
    String  tipoSucursal

    static hasMany = [delegaciones: EntDelegacion]
    static belongsTo = [estado: RsGralEstado]
    
    static constraints = {
        claveSucursal(size:3..15, unique: true, nullable: false, blank: false)
        nombreSucursal(size:3..50, unique: true, nullable: false, blank: false)
        descripcionSucursal(size:5..150, nullable: true)
        delegaciones nullable : true
        tipoSucursal inList:["Corporativa","Sucursal Fisica","Home Office"]
    }

    String toString() {
        "${nombreSucursal}"
    }

}
