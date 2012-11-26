package com.sim.credito

import java.util.Date;

import org.grails.activiti.ApprovalStatus;

import com.sim.cliente.RsCliente
import com.sim.entidad.EntDependencia
import com.sim.entidad.EntSucursal
import com.sim.empresa.EmpEmpleado
import com.sim.producto.ProPromocion
import com.sim.entidad.EntDelegacion
import com.sim.catalogo.SimCatFormaEntrega
import com.sim.catalogo.SimCatEtapaPrestamo
import com.sim.tablaAmortizacion.TablaAmortizacionRegistro
import com.sim.call.CallCenter
import com.sim.listacobro.ListaCobro

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
	String         correoSolicitante
    Date    	   fechaSolicitud
    BigDecimal     montoSolicitado
    SimCatEtapaPrestamo estatusSolicitud
    SimCatFormaEntrega formaDeDispercion
    Boolean        documentosCorrectos
	Boolean        aprobado
	String         comentarios
	Boolean        reenviarSolicitud
	String         explicacionDevolucion
	ListaCobro     primerPagoDependcia
	ApprovalStatus approvalStatus = ApprovalStatus.PENDING
	
	//LOS SIGUIENTES ATRIBUTOS NO SE PUEDEN CAMBIAR DE NOMBRE
	Date dateCreated
	Date lastUpdated
	
	static hasMany = [ tablaAmortizacion : TablaAmortizacionRegistro ,  prestamoAccesorio : PrestamoAccesorio,
                       callCenter: CallCenter]
	
	static mapping = {
		tablaAmortizacion cascade: "all-delete-orphan"
	 }

    static belongsTo = [cliente : RsCliente]
	
    static constraints = {
        cliente(nullable: false)
		correoSolicitante email:true, nullable: false
		clavePrestamo(size:1..20, unique: true, nullable: false, blank: false)
        folioSolicitud(nullable: false, unique: true)
        promocion(nullable: false)
        dependencia(nullable: false)
        sucursal(nullable: true)
        delegacion(nullable: true)
        vendedor(nullable: false)
        fechaSolicitud()
        montoSolicitado(nullable: false)
        estatusSolicitud( nullable: false)
        formaDeDispercion(nullable: false)
        documentosCorrectos(nullable: false)
		aprobado(nullable: false)
		reenviarSolicitud(nullable: false)
		comentarios nullable:true, size:5..255
		explicacionDevolucion blank:true, nullable:true, size:5..255
		approvalStatus nullable:false
		dateCreated ()
		lastUpdated nullable:true
		primerPagoDependcia nullable : true
    }
	
	String toString() {
		"${cliente} - ${clavePrestamo}"
	}
}
