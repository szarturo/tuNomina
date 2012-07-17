package com.sim.procesos.credito

class SolicitudPrestamoController {

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
        [solicitudPrestamoInstanceList: SolicitudPrestamo.list(params), 
			   solicitudPrestamoInstanceTotal: SolicitudPrestamo.count(),
			   myTasksCount: assignedTasksCount]
    }

    def create = {
        def solicitudPrestamoInstance = new SolicitudPrestamo()
        solicitudPrestamoInstance.properties = params
        return [solicitudPrestamoInstance: solicitudPrestamoInstance,
			          myTasksCount: assignedTasksCount]
    }

    def save = {
        def solicitudPrestamoInstance = new SolicitudPrestamo(params)
        if (solicitudPrestamoInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'solicitudPrestamo.label', default: 'SolicitudPrestamo'), solicitudPrestamoInstance.id])}"
			      params.id = solicitudPrestamoInstance.id
						if (params.complete) {
							completeTask(params)
						} else {
							params.action="show"
							saveTask(params)
						}
            redirect(action: "show", params: params)
        }
        else {
            render(view: "create", model: [solicitudPrestamoInstance: solicitudPrestamoInstance, myTasksCount: assignedTasksCount])
        }
    }

    def show = {
        def solicitudPrestamoInstance = SolicitudPrestamo.get(params.id)
        if (!solicitudPrestamoInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'solicitudPrestamo.label', default: 'SolicitudPrestamo'), params.id])}"
            redirect(controller: "task", action: "myTaskList")
        }
        else {
            [solicitudPrestamoInstance: solicitudPrestamoInstance, myTasksCount: assignedTasksCount]
        }
    }

    def edit = {
        def solicitudPrestamoInstance = SolicitudPrestamo.get(params.id)
        if (!solicitudPrestamoInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'solicitudPrestamo.label', default: 'SolicitudPrestamo'), params.id])}"
            redirect(controller: "task", action: "myTaskList")
        }
        else {
            [solicitudPrestamoInstance: solicitudPrestamoInstance, myTasksCount: assignedTasksCount]
        }
    }

    def update = {
        def solicitudPrestamoInstance = SolicitudPrestamo.get(params.id)
        if (solicitudPrestamoInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (solicitudPrestamoInstance.version > version) {
                    
                    solicitudPrestamoInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'solicitudPrestamo.label', default: 'SolicitudPrestamo')] as Object[], "Another user has updated this SolicitudPrestamo while you were editing")
                    render(view: "edit", model: [solicitudPrestamoInstance: solicitudPrestamoInstance, myTasksCount: assignedTasksCount])
                    return
                }
            }
            solicitudPrestamoInstance.properties = params
            if (!solicitudPrestamoInstance.hasErrors() && solicitudPrestamoInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'solicitudPrestamo.label', default: 'SolicitudPrestamo'), solicitudPrestamoInstance.id])}"
								Boolean isComplete = params["_action_update"].equals(message(code: 'default.button.complete.label', default: 'Complete'))
								if (isComplete) {
										completeTask(params)
								} else {
										params.action="show"
										saveTask(params)
								}				
                redirect(action: "show", id: solicitudPrestamoInstance.id, params: [taskId:params.taskId, complete:isComplete?:null])
            }
            else {
                render(view: "edit", model: [solicitudPrestamoInstance: solicitudPrestamoInstance, myTasksCount: assignedTasksCount])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'solicitudPrestamo.label', default: 'SolicitudPrestamo'), params.id])}"
            redirect(controller: "task", action: "myTaskList")
        }
    }

    def delete = {
        def solicitudPrestamoInstance = SolicitudPrestamo.get(params.id)
        if (solicitudPrestamoInstance) {
            try {
                solicitudPrestamoInstance.delete(flush: true)
                deleteTask(params.taskId)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'solicitudPrestamo.label', default: 'SolicitudPrestamo'), params.id])}"
                redirect(controller: "task", action: "myTaskList")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'solicitudPrestamo.label', default: 'SolicitudPrestamo'), params.id])}"
                redirect(action: "show", id: params.id, params: params)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'solicitudPrestamo.label', default: 'SolicitudPrestamo'), params.id])}"
            redirect(controller: "task", action: "myTaskList")
        }
    }
}
