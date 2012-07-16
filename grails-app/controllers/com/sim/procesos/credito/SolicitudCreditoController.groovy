package com.sim.procesos.credito

class SolicitudCreditoController {

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
        [solicitudCreditoInstanceList: SolicitudCredito.list(params), 
			   solicitudCreditoInstanceTotal: SolicitudCredito.count(),
			   myTasksCount: assignedTasksCount]
    }

    def create = {
        def solicitudCreditoInstance = new SolicitudCredito()
        solicitudCreditoInstance.properties = params
        return [solicitudCreditoInstance: solicitudCreditoInstance,
			          myTasksCount: assignedTasksCount]
    }

    def save = {
        def solicitudCreditoInstance = new SolicitudCredito(params)
        if (solicitudCreditoInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'solicitudCredito.label', default: 'SolicitudCredito'), solicitudCreditoInstance.id])}"
			      params.id = solicitudCreditoInstance.id
						if (params.complete) {
							completeTask(params)
						} else {
							params.action="show"
							saveTask(params)
						}
            redirect(action: "show", params: params)
        }
        else {
            render(view: "create", model: [solicitudCreditoInstance: solicitudCreditoInstance, myTasksCount: assignedTasksCount])
        }
    }

    def show = {
        def solicitudCreditoInstance = SolicitudCredito.get(params.id)
        if (!solicitudCreditoInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'solicitudCredito.label', default: 'SolicitudCredito'), params.id])}"
            redirect(controller: "task", action: "myTaskList")
        }
        else {
            [solicitudCreditoInstance: solicitudCreditoInstance, myTasksCount: assignedTasksCount]
        }
    }

    def edit = {
        def solicitudCreditoInstance = SolicitudCredito.get(params.id)
        if (!solicitudCreditoInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'solicitudCredito.label', default: 'SolicitudCredito'), params.id])}"
            redirect(controller: "task", action: "myTaskList")
        }
        else {
            [solicitudCreditoInstance: solicitudCreditoInstance, myTasksCount: assignedTasksCount]
        }
    }

    def update = {
        def solicitudCreditoInstance = SolicitudCredito.get(params.id)
        if (solicitudCreditoInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (solicitudCreditoInstance.version > version) {
                    
                    solicitudCreditoInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'solicitudCredito.label', default: 'SolicitudCredito')] as Object[], "Another user has updated this SolicitudCredito while you were editing")
                    render(view: "edit", model: [solicitudCreditoInstance: solicitudCreditoInstance, myTasksCount: assignedTasksCount])
                    return
                }
            }
            solicitudCreditoInstance.properties = params
            if (!solicitudCreditoInstance.hasErrors() && solicitudCreditoInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'solicitudCredito.label', default: 'SolicitudCredito'), solicitudCreditoInstance.id])}"
								Boolean isComplete = params["_action_update"].equals(message(code: 'default.button.complete.label', default: 'Complete'))
								if (isComplete) {
										completeTask(params)
								} else {
										params.action="show"
										saveTask(params)
								}				
                redirect(action: "show", id: solicitudCreditoInstance.id, params: [taskId:params.taskId, complete:isComplete?:null])
            }
            else {
                render(view: "edit", model: [solicitudCreditoInstance: solicitudCreditoInstance, myTasksCount: assignedTasksCount])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'solicitudCredito.label', default: 'SolicitudCredito'), params.id])}"
            redirect(controller: "task", action: "myTaskList")
        }
    }

    def delete = {
        def solicitudCreditoInstance = SolicitudCredito.get(params.id)
        if (solicitudCreditoInstance) {
            try {
                solicitudCreditoInstance.delete(flush: true)
                deleteTask(params.taskId)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'solicitudCredito.label', default: 'SolicitudCredito'), params.id])}"
                redirect(controller: "task", action: "myTaskList")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'solicitudCredito.label', default: 'SolicitudCredito'), params.id])}"
                redirect(action: "show", id: params.id, params: params)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'solicitudCredito.label', default: 'SolicitudCredito'), params.id])}"
            redirect(controller: "task", action: "myTaskList")
        }
    }
}
