package com.sim.empresa

import com.rs.gral.RsPersona
import com.sim.entidad.EntSucursal

class EmpEmpleado {

    String  claveEmpleado
    Date    fechaIngreso
    String  numeroNomina
    Boolean esVigente = false
    String  tipoEmpleado
    EmpPuesto puesto
    RsPersona    persona
    EntSucursal  sucursal

    static constraints = {
        claveEmpleado unique: true, nullable: false, size:0..25
        persona(unique: true , validator: { personaReferencia, RsReferenciaCliente ->
        personaReferencia?.tiposPersona?.claveTipoPersona?.contains('EMPLEADO') })
        puesto(nullable: false)
        fechaIngreso()
        tipoEmpleado(nullable: false, inList:["INTERNO", "EXTERNO"] )
        numeroNomina nullable:false, blank: false
        esVigente()
        sucursal()
    }

    String toString() {
        "${puesto.nombrePuesto}: ${persona.apellidoPaterno} ${persona.apellidoMaterno ?: ""} ${persona.primerNombre} ${persona.segundoNombre ?: ""}"
    }
}
