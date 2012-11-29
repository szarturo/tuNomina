package com.sim.cliente

import com.sim.catalogo.SimCatBanco

class RsClienteCtaBancaria {

    String numeroDeCuenta
    String clabe
    Boolean depositoPrestamo = false
    SimCatBanco banco

    static belongsTo = [cliente : RsCliente]

    static constraints = {
        cliente()
        banco()
        depositoPrestamo()
        numeroDeCuenta size:5..25, blank: false, unique: false
        clabe          size:5..25, blank: false, unique: true
    }

    String toString() {
        "${banco.nombreBanco}: ${numeroDeCuenta}"
    }
}
