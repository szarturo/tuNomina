package com.sim.cliente

import com.rs.gral.RsGralDomicilio

class RsClienteEmpleo {

    Date    	fechaIngreso
    String  	area
    BigDecimal  ingresoMensual

    static belongsTo = [cliente : RsCliente]

    static hasMany = [domicilios: RsGralDomicilio]

    static constraints = {
        cliente(nullable: false)
    	fechaIngreso()
    	area(size:1..50, nullable: false, blank: false)
    	ingresoMensual(nullable: false)
    	domicilios(nullable: true)
    }

    String toString() {
        "${cliente} - ${area}"
    }    
}
