package com.sim.empresa

import com.rs.gral.RsPersona
import com.sim.entidad.EntSucursal

class EmpEmpleado {

    RsPersona    persona
    String       clavePromotor
    Date         fechaIngreso
    String       numeroNomina
    Boolean      esVigente = false
    String       tipoEmpleado
    EmpPuesto    puesto
    EntSucursal  sucursal

    static constraints = {
        persona(unique: true , validator: { personaReferencia, RsReferenciaCliente ->
            personaReferencia?.tiposPersona?.claveTipoPersona?.contains('EMPLEADO') })        
        numeroNomina nullable:false, blank: false
        clavePromotor(size:0..25, nullable: true, unique: true)
        puesto(nullable: false)
        fechaIngreso()
        tipoEmpleado(nullable: false, inList:["INTERNO", "EXTERNO"] )
        esVigente()
        sucursal()
    }

    String toString() {
        "${puesto.nombrePuesto}: ${persona.apellidoPaterno} ${persona.apellidoMaterno ?: ""} ${persona.primerNombre} ${persona.segundoNombre ?: ""}"
    }
}
