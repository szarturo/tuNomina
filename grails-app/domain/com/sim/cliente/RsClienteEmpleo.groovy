package com.sim.cliente

import com.rs.gral.RsGralDomicilio

class RsClienteEmpleo {

    Date    	fechaIngreso
    String  	area
    BigDecimal  ingresoMensual
    RsGralDomicilio domicilio

    static belongsTo = [cliente : RsCliente]

    static constraints = {
        cliente(nullable: false)
    	fechaIngreso()
    	area(size:1..50, unique: true, nullable: false, blank: false)
    	ingresoMensual(nullable: false)
    	domicilio(nullable: true)
    }

    String toString() {
        "${area}"
    }    
}
