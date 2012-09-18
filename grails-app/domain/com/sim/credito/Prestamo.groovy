package com.sim.credito

import com.sim.cliente.RsCliente
import com.sim.entidad.EntDependencia
import com.sim.entidad.EntSucursal
import com.sim.empresa.EmpEmpleado
import com.sim.producto.ProPromocion
import com.sim.entidad.EntDelegacion
import com.sim.catalogo.SimCatFormaEntrega

class Prestamo {

	String         clavePrestamo
    Integer        folioSolicitud
    EntDependencia dependencia
    //SOLO SE DEBEN MOSTRAR LAS PROMOCIONES DE LA DEPENDENCIA SELECCIONADA
    ProPromocion   promocion
    //SOLO SE DEBEN MOSTRAR LAS SUCURSALES DE LA DEPENDENCIA SELECCIONADA
    EntSucursal    sucursal
    EntDelegacion  delegacion
    EmpEmpleado    vendedor
    Date    	   fechaSolicitud
    BigDecimal     montoSolicitado
    String         estatusSolicitud
    SimCatFormaEntrega formaDeDispercion
    Boolean        documentosCorrectos

    static belongsTo = [cliente : RsCliente]
	
    static constraints = {
        cliente(nullable: false)
		clavePrestamo(size:1..20, unique: true, nullable: false, blank: false)
        folioSolicitud(nullable: false, unique: true)
        promocion(nullable: false)
        dependencia(nullable: false)
        sucursal(nullable: true)
        delegacion(nullable: true)
        vendedor(nullable: false)
        fechaSolicitud()
        montoSolicitado(nullable: false)
        estatusSolicitud( inList:["INICIO MESA CONTROL",
                "REVISADO MESA DE CONTROL",
                "REVISADO CONTROL DE CALIDAD",
                "PROCESADO",
                "DEVOLUCION",
                "COMPRADO"])
        formaDeDispercion(nullable: false)
        documentosCorrectos(nullable: false)
    }
}
