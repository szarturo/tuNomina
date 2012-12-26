package com.sim.credito

import com.sim.alfresco.AlfrescoService
import com.sim.catalogo.SimCatEtapaPrestamo
import com.sim.catalogo.SimCatFormaEntrega
import com.sim.cliente.RsCliente
import com.sim.tablaAmortizacion.TablaAmortizacionServiceException
import org.apache.chemistry.opencmis.client.api.CmisObject
import org.apache.chemistry.opencmis.client.api.Document
import org.apache.chemistry.opencmis.client.api.Folder
import org.grails.activiti.ApprovalStatus

class PrestamoController {
	
	def tablaAmortizacionRegistroService
    def springSecurityService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
    static activiti = true

    def index = {
        redirect(action: "list", params: params)
    }

    def start = {
        start(params)
    }
	
    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [prestamoInstanceList: Prestamo.list(params), 
			   prestamoInstanceTotal: Prestamo.count(),
			   myTasksCount: assignedTasksCount]
    }

    def create = {
        def prestamoInstance = new Prestamo()
        prestamoInstance.properties = params
        return [prestamoInstance: prestamoInstance,
			          myTasksCount: assignedTasksCount]
    }

    def save = {
        def prestamoInstance = new Prestamo(params)
		if (params.complete) {
			prestamoInstance.estatusSolicitud = SimCatEtapaPrestamo.findByClaveEtapaPrestamo('CAPTURADA_MESA')
		}else{
			prestamoInstance.estatusSolicitud = SimCatEtapaPrestamo.findByClaveEtapaPrestamo('INICIO_MESA')
		}
		prestamoInstance.approvalStatus = ApprovalStatus.PENDING
		prestamoInstance.documentosCorrectos = false
		prestamoInstance.aprobado = false
		prestamoInstance.reenviarSolicitud = false
        //SE OBTIENE EL NOMBRE DEL USUARIO DE MESA DE CONTROL
        def user = springSecurityService.getCurrentUser()
        prestamoInstance.usuarioMesaControl = user.username
		if (!params.correoSolicitante){
			log.info("No se asigno correo al solicitante")
			prestamoInstance.correoSolicitante = "sincorreo@gmail.com"
		}
        if (prestamoInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'prestamo.label', default: 'Prestamo'), prestamoInstance.id])}"
			      params.id = prestamoInstance.id
						if (params.complete) {
							//LOS SIGUIENTES PARAMETROS CAUSABAN PROBLEMAS CON ACTIVITI
							//SIN EMBARGO SI PASA CORRECTAMENTE LOS ID DE CADA PARAMETRO ELIMINADO
							params.remove("dependencia")
							params.remove("promocion")
							params.remove("sucursal")
							params.remove("delegacion")
							params.remove("vendedor")
							params.remove("estatusSolicitud")
							params.remove("formaDeDispercion")
							params.remove("cliente")
                            params.remove("tipoEmpleadoDep")
							completeTask(params)
						} else {
							params.action="show"
							saveTask(params)
						}
            redirect(action: "show", params: params)
        }
        else {
            render(view: "create", model: [prestamoInstance: prestamoInstance, myTasksCount: assignedTasksCount])
        }
    }

    def show = {
        def prestamoInstance = Prestamo.get(params.id)
        if (!prestamoInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'prestamo.label', default: 'Prestamo'), params.id])}"
            redirect(controller: "task", action: "myTaskList")
        }
        else {

			try{
				List<Document> documentos = new ArrayList<Document>();
				AlfrescoService service = new AlfrescoService();
				Object o = null
				
				//try{
					o=service.getByPath("/Sites/tuNomina/creditos/${prestamoInstance.cliente.id}/${prestamoInstance.folioSolicitud}");
				//}catch(Exception e){
					//e.printStackTrace();
				//}
				
				if(o!=null){
					Folder folder = (Folder)o;
					
					for(CmisObject cmisObject: folder.getChildren()){
						if(cmisObject instanceof Document){
							documentos.add((Document) cmisObject);
						}
					}
					
					request.putAt("documentos", documentos);
				}
			}catch(Exception e){
					e.printStackTrace();
			}
            [prestamoInstance: prestamoInstance, myTasksCount: assignedTasksCount]
        }
    }

    def edit = {
        def prestamoInstance = Prestamo.get(params.id)
        if (!prestamoInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'prestamo.label', default: 'Prestamo'), params.id])}"
            redirect(controller: "task", action: "myTaskList")
        }
        else {
            [prestamoInstance: prestamoInstance, myTasksCount: assignedTasksCount]
        }
    }

    def update = {
        def prestamoInstance = Prestamo.get(params.id)
        if (prestamoInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (prestamoInstance.version > version) {
                    
                    prestamoInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'prestamo.label', default: 'Prestamo')] as Object[], "Another user has updated this Prestamo while you were editing")
                    render(view: "edit", model: [prestamoInstance: prestamoInstance, myTasksCount: assignedTasksCount])
                    return
                }
            }
            prestamoInstance.properties = params
			
			def estatusSolicitud = SimCatEtapaPrestamo.get(params.estatusSolicitud.id)
			
			log.info("Estatus de la solicitud: "+estatusSolicitud)

			Boolean isComplete = params["_action_update"].equals(message(code: 'default.button.complete.label', default: 'Complete'))
			if (isComplete) {

				if (estatusSolicitud.claveEtapaPrestamo.equals("INICIO_MESA")){
					prestamoInstance.estatusSolicitud = SimCatEtapaPrestamo.findByClaveEtapaPrestamo('CAPTURADA_MESA')
					prestamoInstance.approvalStatus = ApprovalStatus.PENDING
				}else if(estatusSolicitud.claveEtapaPrestamo.equals("CAPTURADA_MESA") && params.aprobado.equals("on")){
					prestamoInstance.estatusSolicitud = SimCatEtapaPrestamo.findByClaveEtapaPrestamo('PROCESADA')
					prestamoInstance.approvalStatus = ApprovalStatus.PENDING
				}else if(estatusSolicitud.claveEtapaPrestamo.equals("CAPTURADA_MESA") && !params.aprobado.equals("on")){
					prestamoInstance.estatusSolicitud = SimCatEtapaPrestamo.findByClaveEtapaPrestamo('DEVOLUCION_AMESA')
					prestamoInstance.approvalStatus = ApprovalStatus.REJECTED
				}else if(estatusSolicitud.claveEtapaPrestamo.equals("DEVOLUCION_AMESA") && params.reenviarSolicitud.equals("on")){
					prestamoInstance.estatusSolicitud = SimCatEtapaPrestamo.findByClaveEtapaPrestamo('CAPTURADA_MESA')
					prestamoInstance.approvalStatus = ApprovalStatus.PENDING
				}else if(estatusSolicitud.claveEtapaPrestamo.equals("DEVOLUCION_CR") && params.aprobado.equals("on")){
                    prestamoInstance.estatusSolicitud = SimCatEtapaPrestamo.findByClaveEtapaPrestamo('PROCESADA')
                    prestamoInstance.approvalStatus = ApprovalStatus.PENDING
                }else if(estatusSolicitud.claveEtapaPrestamo.equals("DEVOLUCION_CR") && !params.aprobado.equals("on")){
                    prestamoInstance.estatusSolicitud = SimCatEtapaPrestamo.findByClaveEtapaPrestamo('DEVOLUCION_AMESA')
                    prestamoInstance.approvalStatus = ApprovalStatus.REJECTED
                }else if(estatusSolicitud.claveEtapaPrestamo.equals("CANCELADA_CR") && params.aprobado.equals("on")){
                    prestamoInstance.estatusSolicitud = SimCatEtapaPrestamo.findByClaveEtapaPrestamo('PROCESADA')
                    prestamoInstance.approvalStatus = ApprovalStatus.PENDING
                }else if(estatusSolicitud.claveEtapaPrestamo.equals("CANCELADA_CR") && !params.aprobado.equals("on")){
                    prestamoInstance.estatusSolicitud = SimCatEtapaPrestamo.findByClaveEtapaPrestamo('DEVOLUCION_AMESA')
                    prestamoInstance.approvalStatus = ApprovalStatus.REJECTED
                }                

            }
			
            if (!prestamoInstance.hasErrors() && prestamoInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'prestamo.label', default: 'Prestamo'), prestamoInstance.id])}"
								
								if (isComplete) {
                                        log.info "LA TAREA ES COMPLETADA"
										params.aprobado = params.aprobado.equals("on")
										params.reenviarSolicitud = prestamoInstance.reenviarSolicitud
                                        //EL SIGUIENTE PARAMETRO SE UTILIZA CUANDO SE REALIZA UNA DEVOLUCION A MESA DE CONTROL
                                        params.usuarioMesaControl = prestamoInstance.usuarioMesaControl
										//LOS SIGUIENTES PARAMETROS CAUSABAN PROBLEMAS CON ACTIVITI
										//SIN EMBARGO SI PASA CORRECTAMENTE LOS ID DE CADA PARAMETRO ELIMINADO
										params.remove("dependencia")
										params.remove("promocion")
										params.remove("sucursal")
										params.remove("delegacion")
										params.remove("vendedor")
										params.remove("estatusSolicitud")
										params.remove("formaDeDispercion")
										params.remove("cliente")
                                        params.remove("tipoEmpleadoDep")
										completeTask(params)
								} else {
										params.action="show"
										saveTask(params)
								}				
                redirect(action: "show", id: prestamoInstance.id, params: [taskId:params.taskId, complete:isComplete?:null])
            }
            else {
                render(view: "edit", model: [prestamoInstance: prestamoInstance, myTasksCount: assignedTasksCount])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'prestamo.label', default: 'Prestamo'), params.id])}"
            redirect(controller: "task", action: "myTaskList")
        }
    }

    def delete = {
        def prestamoInstance = Prestamo.get(params.id)
        if (prestamoInstance) {
            try {
                prestamoInstance.delete(flush: true)
                deleteTask(params.taskId)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'prestamo.label', default: 'Prestamo'), params.id])}"
                redirect(controller: "task", action: "myTaskList")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'prestamo.label', default: 'Prestamo'), params.id])}"
                redirect(action: "show", id: params.id, params: params)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'prestamo.label', default: 'Prestamo'), params.id])}"
            redirect(controller: "task", action: "myTaskList")
        }
    }
	
	def generaTablaAmortizacion = {
		Prestamo prestamoInstance = Prestamo.get(params.idPrestamo)
	
		try{
			tablaAmortizacionRegistroService.generaTablaAmortizacion(prestamoInstance)
		//VERIFICAR SI SE GENERO ALGUN ERROR
		}catch(TablaAmortizacionServiceException errorGeneraTablaAmor){
			prestamoInstance.errors.reject("ErrorGeneraTablaAmor",errorGeneraTablaAmor.mensaje)
			log.error "Failed:", errorGeneraTablaAmor
			//AL APLICAR LA FUNCIONALIDAD REAL HAY QUE MOSTRAR EL MENSAJE
			redirect(action: "show", params: [id: params.idPrestamo])
			return
		}
		
		redirect(controller: "tablaAmortizacionRegistro", action: "list", params: [idPrestamo: params.idPrestamo])
		
	}

    def validaRespuestaCr = {

        def prestamoInstance = Prestamo.get(params.id)

        log.info "Estatus Solicitud: ${prestamoInstance.estatusSolicitud}"

        if (prestamoInstance.estatusSolicitud.equals(SimCatEtapaPrestamo.findByClaveEtapaPrestamo("PROCESADA"))){
            log.info "La solicitud continua en ${prestamoInstance.folioSolicitud}"
            flash.message = " La solicitud ${prestamoInstance.folioSolicitud} continua en ${prestamoInstance.estatusSolicitud}"
        }else if(prestamoInstance.estatusSolicitud.equals(SimCatEtapaPrestamo.findByClaveEtapaPrestamo("AUTORIZADA"))){
            log.info "La solicitud se encuentra en ${prestamoInstance.folioSolicitud}"
            flash.message = " La solicitud ${prestamoInstance.folioSolicitud} se encuentra en ${prestamoInstance.estatusSolicitud}"
        }else{

            //RECUPERA EL ESTATUS DE LA SOLICITUD ACTUAL
            params.estatusSolicitudActual =  prestamoInstance.estatusSolicitud.claveEtapaPrestamo
            completeTask(params)
            flash.message = " Credito Real cambio el estatus de la solicitud ${prestamoInstance.folioSolicitud} a ${prestamoInstance.estatusSolicitud}"
        }
        redirect(controller: "task", action: "myTaskList")
    }


   def dispersarPrestamo = {

        def prestamoInstance = Prestamo.get(params.id)

        if (prestamoInstance.estatusSolicitud.equals(SimCatEtapaPrestamo.findByClaveEtapaPrestamo("COMPRADA"))){
            log.info "La solicitud continua en ${prestamoInstance.folioSolicitud}, necesita ser DISPERSADA"
            flash.message = " La solicitud ${prestamoInstance.folioSolicitud} continua en ${prestamoInstance.estatusSolicitud}, necesita ser DISPERSADA"
        }else if(prestamoInstance.estatusSolicitud.equals(SimCatEtapaPrestamo.findByClaveEtapaPrestamo("DISPERSADA"))){
            //CONSULTA SI EL PAGO AL CLIENTE SE REALIZA A TRAVES DE TRANSFERENCIA ELECTRONICA O
            //POR VENTANILLA BANCARIA
            SimCatFormaEntrega formaDeEntrega = prestamoInstance.formaDeDispercion
            log.info "Forma Dispercion: ${formaDeEntrega}"
            params.formaEntrega = formaDeEntrega.claveFormaEntrega

            //PARAMETROS PARA ENVIAR EL CORREO
            params.from = grailsApplication.config.activiti.mailServerDefaultFrom
            params.emailTo = prestamoInstance.correoSolicitante
            log.info("ID CLIENTE: "+ prestamoInstance.cliente.id)
            def clientePrestamo = RsCliente.get(prestamoInstance.cliente.id)
            String nombreCliente = clientePrestamo.persona.primerNombre + " " + clientePrestamo.persona.apellidoPaterno
            log.info("NOMBRE CLIENTE: "+nombreCliente)
            params.nombreCliente = nombreCliente

            if (formaDeEntrega.equals(SimCatFormaEntrega.findByClaveFormaEntrega('VENBANCO'))){
                //LA SIGUIENTE DEFINICION SE USA COMO EJEMPLO DE COMO PASAR UNA VARIABLE A LA SIGUIENTE TAREA
                params.idPrestamo = params.id
                prestamoInstance.estatusSolicitud = SimCatEtapaPrestamo.findByClaveEtapaPrestamo('PENDIENTE_COBRO')
            }else{
                prestamoInstance.estatusSolicitud = SimCatEtapaPrestamo.findByClaveEtapaPrestamo('ACTIVO')
            }

            flash.message = "La solicitud ${prestamoInstance.folioSolicitud} cambio del estatus POR DISPERSAR al estatus ${prestamoInstance.estatusSolicitud}"
            prestamoInstance.save(flush: true)
            completeTask(params)
            
        }else{
            flash.message = " La solicitud ${prestamoInstance.folioSolicitud} debe cambiar al estatus  ${SimCatEtapaPrestamo.findByClaveEtapaPrestamo("DISPERSADA")}"
        }

        redirect(controller: "task", action: "myTaskList")

    }

    //TEMPORAL PARA EL CAMBIO DE ESTATUS A UN CREDITO. MOSTRAR EDIT
    def editCambioEstatus = {
        def prestamoInstance = Prestamo.get(params.id)
        [prestamoInstance: prestamoInstance]
    }

    //TEMPORAL PARA EL CAMBIO DE ESTATUS A UN CREDITO. EJECUTAR UPDATE
    def updateEstatus = {
        def prestamoInstance = Prestamo.get(params.id)
        if (prestamoInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (prestamoInstance.version > version) {
                    
                    prestamoInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'prestamo.label', default: 'Prestamo')] as Object[], "Another user has updated this Prestamo while you were editing")
                    render(view: "edit", model: [prestamoInstance: prestamoInstance])
                    return
                }
            }
            prestamoInstance.properties = params
            
            
            if (!prestamoInstance.hasErrors() && prestamoInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'prestamo.label', default: 'Prestamo'), prestamoInstance.id])}"
                                
                redirect(action: "show", id: prestamoInstance.id)
            }
            else {
                render(view: "edit", model: [prestamoInstance: prestamoInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'prestamo.label', default: 'Prestamo'), params.id])}"
            redirect(controller: "task", action: "myTaskList")
        }
    }

	
}