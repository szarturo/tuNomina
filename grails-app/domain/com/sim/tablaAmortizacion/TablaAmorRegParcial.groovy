package com.sim.tablaAmortizacion

import com.sim.listacobro.ListaCobroParcial

class TablaAmorRegParcial {

    Integer numeroPagoParcial

    static belongsTo = [listaCobroParcial: ListaCobroParcial,
                        tablaAmortizacionRegistro: TablaAmortizacionRegistro]

    static constraints = {
    }
}
