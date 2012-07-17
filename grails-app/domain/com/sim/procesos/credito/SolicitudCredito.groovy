package com.sim.procesos.credito

import org.grails.activiti.ApprovalStatus;

class SolicitudCredito {

 	String nombreSolicitante
	BigDecimal cantidadSolicitada
	String motivo
	ApprovalStatus approvalStatus = ApprovalStatus.PENDING
	String approvalRemark
	Boolean reenviarSolicitud
	
	//LOS SIGUIENTES ATRIBUTOS NO SE PUEDEN CAMBIAR DE NOMBRE
	Date dateCreated
	Date lastUpdated

	static constraints = {
		nombreSolicitante blank:false, size:5..50
		cantidadSolicitada nullable:false
		motivo blank:false, size:5..255
		approvalStatus nullable:false
		approvalRemark nullable:true
		reenviarSolicitud nullable:true
		dateCreated blank:false
		lastUpdated nullable:true
	}

}
