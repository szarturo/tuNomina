package com.sim.producto

import com.sim.catalogo.SimCatMetodoCalculo
import com.sim.catalogo.SimCatPeriodicidad
import com.sim.entidad.EntDependencia

class ProPromocion {

    String         		clavePromocion
    String         		nombrePromocion
    Date    	   		fechaInicioVigencia
    Date    	   		fechaFinVigencia
	BigDecimal  		tasaDeInteres
	SimCatPeriodicidad  periodicidadTasa
	Long        		numeroDePagos
	SimCatPeriodicidad  periodicidadPagos
    Integer        		iva
	SimCatMetodoCalculo metodoCalculo
	
	static hasMany = [proPromocionAccesorio : ProPromocionAccesorio]

    static constraints = {
        clavePromocion(size:1..20, unique: true, nullable: false, blank: false)
        nombrePromocion(size:5..100, unique: false, nullable: false, blank: false)
        fechaInicioVigencia(nullable: false)
        fechaFinVigencia(nullable: false)
		metodoCalculo()
        tasaDeInteres scale:2, nullable:false
		periodicidadTasa()
        numeroDePagos range:1..50
		periodicidadPagos()
        iva  inList:[15, 16]
    }

    String toString() {
        "${clavePromocion}"
    }
}
