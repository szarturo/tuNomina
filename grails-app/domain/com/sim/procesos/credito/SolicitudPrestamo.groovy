package com.sim.procesos.credito

import java.math.BigDecimal;
import java.util.Date;
import org.grails.activiti.ApprovalStatus;

class SolicitudPrestamo {

 	String nombreSolicitante
	BigDecimal sueldoMensual
	BigDecimal prestamo
	Boolean prestamoAutorizado
	String explicacionSolicitud
	String explicacionCredito
	ApprovalStatus approvalStatus = ApprovalStatus.PENDING
	
	//LOS SIGUIENTES ATRIBUTOS NO SE PUEDEN CAMBIAR DE NOMBRE
	Date dateCreated
	Date lastUpdated

	static constraints = {
		nombreSolicitante blank:false, size:5..50
		sueldoMensual scale:2, nullable:false
		prestamo scale:2, nullable:false
		prestamoAutorizado nullable:true
		explicacionSolicitud blank:false, size:5..255
		explicacionCredito blank:false, size:5..255
		approvalStatus nullable:false
		dateCreated blank:false
		lastUpdated nullable:true
	}
}
