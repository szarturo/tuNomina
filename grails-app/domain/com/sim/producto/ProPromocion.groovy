package com.sim.producto

import com.sim.catalogo.SimCatMetodoCalculo
import com.sim.catalogo.SimCatPeriodicidad
import com.sim.entidad.EntDependencia

class ProPromocion {

    String         		clavePromocion
    String         		nombrePromocion
    Date    	   		fechaInicioVigencia
    Date    	   		fechaFinVigencia
    Boolean             vigenciaIndefinida = false
	BigDecimal  		tasaDeInteres
	SimCatPeriodicidad  periodicidadTasa
	Long        		numeroDePagos
	SimCatPeriodicidad  periodicidadPagos
    Integer        		iva
	SimCatMetodoCalculo metodoCalculo
	
    static belongsTo = EntDependencia
	static hasMany = [proPromocionAccesorio : ProPromocionAccesorio, 
                      dependencia: EntDependencia]

    static constraints = {
        clavePromocion(size:1..20, unique: true, nullable: false, blank: false)
        nombrePromocion(size:5..100, unique: false, nullable: false, blank: false)
        fechaInicioVigencia(nullable: false)
        fechaFinVigencia(nullable: false)
        vigenciaIndefinida()
		metodoCalculo()
        tasaDeInteres scale:2, nullable:false
		periodicidadTasa()
        numeroDePagos range:1..50
		periodicidadPagos()
        iva  inList:[11, 16]
        dependencia(nullable: true)
    }

    String toString() {
        "${clavePromocion}"
    }
}
