package com.sim.listacobro

import com.sim.entidad.EntDependencia
import com.sim.tablaAmortizacion.TablaAmortizacionRegistro

class ListaCobro implements Comparable {

    Integer anio
    Integer numeroPago
    Date    fechaInicio
    Date    fechaFin
    Boolean parcialidades = false

    static hasMany = [registroTablaAmor:TablaAmortizacionRegistro,
                      listaCobroParcial:ListaCobroParcial,
                      fechaEstatus:ListaCobroFechaEst]

    static belongsTo = [dependencia:EntDependencia]

    static constraints = {
        dependencia()
        anio range: 2012..2020
        numeroPago ()
        fechaInicio nullable: true
        fechaFin nullable: true
        fechaEstatus()
        registroTablaAmor()
        listaCobroParcial()
    }

   String toString() {
        "${dependencia.periodicidadPagoNomina.clavePeriodicidad}: ${numeroPago} - ${anio}"
    }    

    int compareTo(obj) {
        numeroPago.compareTo(obj.numeroPago)
    }

}
