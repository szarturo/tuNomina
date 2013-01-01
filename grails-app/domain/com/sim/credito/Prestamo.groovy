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
import com.sim.catalogo.SimCatTipoEmp
import com.sim.tablaAmortizacion.TablaAmortizacionRegistro
import com.sim.call.CallCenter

class Prestamo {

    Integer        folioSolicitud
    EntDependencia dependencia
    SimCatTipoEmp  tipoEmpleadoDep
    //SOLO SE DEBEN MOSTRAR LAS PROMOCIONES DE LA DEPENDENCIA SELECCIONADA
    ProPromocion   promocion
    //SOLO SE DEBEN MOSTRAR LAS SUCURSALES DE LA DEPENDENCIA SELECCIONADA
    EntSucursal    sucursal
    EntDelegacion  delegacion
    EmpEmpleado    vendedor
	String         correoSolicitante
    Date    	   fechaSolicitud
    BigDecimal     percepcionesMensuales
    BigDecimal     deduccionesMensuales
    BigDecimal     montoSolicitado
    SimCatEtapaPrestamo estatusSolicitud
    SimCatFormaEntrega formaDeDispercion
    Boolean        documentosCorrectos
	Boolean        aprobado
	String         comentarios
	Boolean        reenviarSolicitud
	String         explicacionDevolucion
    String         usuarioMesaControl
    String         consecutivoCr
	ApprovalStatus approvalStatus = ApprovalStatus.PENDING
	
	//LOS SIGUIENTES ATRIBUTOS NO SE PUEDEN CAMBIAR DE NOMBRE
	Date dateCreated
	Date lastUpdated
	
	static hasMany = [ tablaAmortizacion : TablaAmortizacionRegistro ,  prestamoAccesorio : PrestamoAccesorio,
                       callCenter: CallCenter, documentos: PrestamoDocumento]
	
	static mapping = {
		tablaAmortizacion cascade: "all-delete-orphan"
        documentos  cascade: "all-delete-orphan"
     }
	 

    static belongsTo = [cliente : RsCliente]
	
    static constraints = {
        cliente(nullable: false)
		correoSolicitante email:true, nullable: false
        folioSolicitud(nullable: false, unique: true)
        promocion(nullable: false)
        dependencia(nullable: false)
        tipoEmpleadoDep(nullable : true)
        sucursal(nullable: true)
        delegacion(nullable: true)
        vendedor(nullable: false)
        fechaSolicitud()
        montoSolicitado(nullable: false)
        percepcionesMensuales(nullable: true)
        deduccionesMensuales(nullable: true)
        estatusSolicitud( nullable: false)
        formaDeDispercion(nullable: false)
        documentosCorrectos(nullable: false)
		aprobado(nullable: false)
		reenviarSolicitud(nullable: false)
		comentarios nullable:true, size:5..255
		explicacionDevolucion blank:true, nullable:true, size:5..255
		approvalStatus nullable:false
        usuarioMesaControl nullable:true
        consecutivoCr nullable:true
        documentos nullable:true
		dateCreated ()
		lastUpdated nullable:true
    }
	
	String toString() {
		"${cliente} - ${folioSolicitud}"
	}
}
