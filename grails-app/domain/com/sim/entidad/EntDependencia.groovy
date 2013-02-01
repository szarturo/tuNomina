package com.sim.entidad

import com.sim.listacobro.ListaCobro
import com.sim.producto.ProPromocion
import com.sim.catalogo.SimCatTipoEmp

class EntDependencia {

    String  claveDependencia
    String  nombreDependencia
    String  descripcionDependencia
    //1866 Empresas Publicas
    //1833 Empresas Privadas (Bio Servicios Corporativos)
    String  distribuidor

    SortedSet listaCobro

    static hasMany = [listaCobro: ListaCobro, 
                    promocion: ProPromocion, 
                    tiposEmpleado: SimCatTipoEmp]

    static mapping = {
        tiposEmpleado joinTable: [name:'REL_TIPOS_EMPLEADOS', key:'ID_DEPENDENCIA', column:'ID_TIPO_EMPLEADO']
    }

    static constraints = {
        claveDependencia(size:3..15, unique: true, nullable: false, blank: false)
        nombreDependencia(size:3..100, unique: false, nullable: false, blank: false)
        descripcionDependencia(size:5..150, nullable: true)
        listaCobro(nullable: true)
        promocion(nullable: true)
        tiposEmpleado(nullable: true)
        distribuidor nullable:false, size:4..4
    }

    String toString() {
        "${nombreDependencia}"
    }
}
