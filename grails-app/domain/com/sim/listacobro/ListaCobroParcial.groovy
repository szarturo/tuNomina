package com.sim.listacobro

import com.sim.tablaAmortizacion.TablaAmorRegParcial

class ListaCobroParcial implements Comparable {

    Integer numeroParcialidad

    static belongsTo = [listaCobro:ListaCobro]

    static hasMany = [tablaAmorRegParcial: TablaAmorRegParcial]

    static constraints = {
    	numeroParcialidad()
    	listaCobro()
    	tablaAmorRegParcial()
    }

   	String toString() {
        "${listaCobro}. Parcialidad: ${numeroParcialidad}"
    }   

   	int compareTo(obj) {
        numeroParcialidad.compareTo(obj.numeroParcialidad)
    }
}
