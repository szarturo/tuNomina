package com.sim.cliente

import com.rs.gral.RsPersona;
import com.sim.entidad.EntDependencia
import com.sim.credito.Prestamo
import com.sim.pfin.PfinCuenta

class RsCliente {

    RsPersona      persona
    EntDependencia dependencia
    String         numeroDeNomina

    static hasMany = [ creditos : Prestamo, 
                       cuentas : PfinCuenta, 
                       cuentaBancaria : RsClienteCtaBancaria,
                       clienteEmpleo: RsClienteEmpleo,
                       referenciasClientes : RsClienteReferencia]

    static constraints = {
        persona unique: true
        dependencia nullable: false
        numeroDeNomina nullable: false
        creditos nullable: true
		cuentas nullable:true
        cuentaBancaria nullable: true
        clienteEmpleo nullable: true
        referenciasClientes nullable : true
    }

    String toString() {
        "${persona.apellidoPaterno} ${persona.apellidoMaterno ?: ""} ${persona.primerNombre} ${persona.segundoNombre ?: ""}"
    }

}
