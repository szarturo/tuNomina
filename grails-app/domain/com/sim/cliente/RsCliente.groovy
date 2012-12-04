package com.sim.cliente

import com.rs.gral.RsPersona;
import com.sim.entidad.EntDependencia
import com.sim.credito.Prestamo
import com.sim.pfin.PfinCuenta

class RsCliente {

    RsPersona      persona
    EntDependencia dependencia
    String         numeroDeNomina

    /*
    BigDecimal ingresoSemanal
    Integer    dependientesEconomicos
    String     destinoDelCredito
    String     rolEnElHogar
    Boolean    listaNegra = false
    */

    /*
    static hasMany = [ cuentasBancarias : RsCuentaBancaria, documentacion : RsClienteDocumentacion, referenciasClientes : RsReferenciaCliente,
            negocios : SimClienteNegocio, integranteUef : SimClienteIntegranteUef, adeudos : SimClienteAdeudos, garantias : SimClienteGarantia ]
     */

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

        /*
        ingresoSemanal scale:2, nullable:true
        dependientesEconomicos range:0..30
        destinoDelCredito inList:[
                "ADQUIRIR O COMPRAR MERCANCIA",
                "COMPRAR MAQUINARIA, EQUIPO O HERRAMIENTAS",
                "AMPLIAR, ADECUAR O REPARAR EL LOCAL O VEHICULO",
                "COMPRAR LOCAL O VEHICULO",
                "PAGAR DEUDAS DEL NEGOCIO",
                "OTRO FIN RELACIONADO",
                "FINES AJENOS AL NEGOCIO"]
        rolEnElHogar inList:[
                "JEFE(A)",
                "ESPOSO(A)",
                "HIJO(A)",
                "OTRO"]
        listaNegra()
        */
        //cuentasBancarias nullable : true
        //documentacion nullable : true
        //referenciasClientes nullable : true
        //negocios ()
        //integranteUef nullable : true
        //adeudos nullable : true
        //garantias nullable : true
    }

    String toString() {
        "${persona.apellidoPaterno} ${persona.apellidoMaterno ?: ""} ${persona.primerNombre} ${persona.segundoNombre ?: ""}"
    }

}
