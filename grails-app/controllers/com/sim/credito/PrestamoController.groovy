package com.sim.credito

import com.sim.catalogo.SimCatEtapaPrestamo
import org.grails.activiti.ApprovalStatus

class PrestamoController {

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
			prestamoInstance.estatusSolicitud = SimCatEtapaPrestamo.findByClaveEtapaPrestamo('CAPMC')
		}else{
			prestamoInstance.estatusSolicitud = SimCatEtapaPrestamo.findByClaveEtapaPrestamo('INIMC')
		}
		prestamoInstance.approvalStatus = ApprovalStatus.PENDING
		prestamoInstance.documentosCorrectos = false
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
			
			Boolean isComplete = params["_action_update"].equals(message(code: 'default.button.complete.label', default: 'Complete'))
			if (isComplete) {
				prestamoInstance.estatusSolicitud = SimCatEtapaPrestamo.findByClaveEtapaPrestamo('CAPMC')
			}else{
				prestamoInstance.estatusSolicitud = SimCatEtapaPrestamo.findByClaveEtapaPrestamo('INIMC')
			}
            if (!prestamoInstance.hasErrors() && prestamoInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'prestamo.label', default: 'Prestamo'), prestamoInstance.id])}"
								
								if (isComplete) {
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
}
