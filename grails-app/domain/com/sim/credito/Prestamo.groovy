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
import com.sim.catalogo.SimCatTipoEmp
import com.sim.catalogo.SimCatCrMotivo
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
    BigDecimal     montoAutorizado
    PrestamoEstatus estatusSolicitud
    SimCatFormaEntrega formaDeDispercion
    Boolean        documentosCorrectos
	Boolean        aprobado
	String         comentarios
	Boolean        reenviarSolicitud
	String         explicacionDevolucion
    String         usuarioMesaControl
    String         consecutivoCr
    Boolean        incluirEnListasCobro
    SimCatCrMotivo movitoRespuestaCr
    //FECHA PARA INDICAR CUANDO SE REALIZO LA TRANSFERENCIA ELECTRONICA O
    //FECHA EN QUE EL USUARIO COBRO SU CREDITO
    Date           fechaCobro
    Date           fechaInstalacion
	ApprovalStatus approvalStatus = ApprovalStatus.PENDING
	
	//LOS SIGUIENTES ATRIBUTOS NO SE PUEDEN CAMBIAR DE NOMBRE
	Date dateCreated
	Date lastUpdated

    static belongsTo = [cliente : RsCliente]
	
	static hasMany = [tablaAmortizacion : TablaAmortizacionRegistro, 
                        prestamoAccesorio : PrestamoAccesorio,
                        callCenter : CallCenter, 
                        documentos : PrestamoDocumento,
                        datosCrRespuesta : PrestamoCrRespuesta,
                        datosCrCartera : PrestamoCrCartera]
	
	static mapping = {
		tablaAmortizacion cascade: "all-delete-orphan"
        documentos  cascade: "all-delete-orphan"
     }
	 
    static hasOne = [datosCrComprada : PrestamoCrComprada ]
	
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
        montoAutorizado(nullable: true)
        percepcionesMensuales(nullable: false)
        deduccionesMensuales(nullable: false)
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
        datosCrRespuesta nullable:true
        datosCrComprada nullable:true
        datosCrCartera nullable:true
        movitoRespuestaCr nullable:true
        fechaCobro nullable:true
        fechaInstalacion nullable:true
        incluirEnListasCobro ()
 		dateCreated ()
		lastUpdated nullable:true
    }
	
	String toString() {
		"${cliente} - ${folioSolicitud}"
	}
}
