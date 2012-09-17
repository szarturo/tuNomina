package com.sim.producto

import com.sim.entidad.EntDependencia

class ProPromocion {

    String         clavePromocion
    String         nombrePromocion
    EntDependencia dependencia
    BigDecimal     tasa
    Integer        plazo
    Date    	   fechaInicioVigencia
    Date    	   fechaFinVigencia
    Integer        iva

    static constraints = {
        clavePromocion(size:1..20, unique: true, nullable: false, blank: false)
        nombrePromocion(size:5..50, unique: true, nullable: false, blank: false)
        dependencia nullable: false
        fechaInicioVigencia(nullable: false)
        fechaFinVigencia(nullable: false)
        tasa scale:2, nullable:false
        plazo range:1..40
        iva  inList:[15, 16]
    }

    String toString() {
        "${clavePromocion}"
    }
}
