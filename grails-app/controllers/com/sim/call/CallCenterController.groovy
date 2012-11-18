package com.sim.call

class CallCenterController {

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
        [callCenterInstanceList: CallCenter.list(params), 
			   callCenterInstanceTotal: CallCenter.count(),
			   myTasksCount: assignedTasksCount]
    }

    def create = {
        log.info "Parametros Call Center: ${params}"
        def callCenterInstance = new CallCenter()
        callCenterInstance.properties = params
        return [callCenterInstance: callCenterInstance,
			          myTasksCount: assignedTasksCount]
    }

    def save = {
        def callCenterInstance = new CallCenter(params)
        if (callCenterInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'callCenter.label', default: 'CallCenter'), callCenterInstance.id])}"
			      params.id = callCenterInstance.id
						if (params.complete) {
							completeTask(params)
						} else {
							params.action="show"
							saveTask(params)
						}
            redirect(action: "show", params: params)
        }
        else {
            render(view: "create", model: [callCenterInstance: callCenterInstance, myTasksCount: assignedTasksCount])
        }
    }

    def show = {
        def callCenterInstance = CallCenter.get(params.id)
        if (!callCenterInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'callCenter.label', default: 'CallCenter'), params.id])}"
            redirect(controller: "task", action: "myTaskList")
        }
        else {
            [callCenterInstance: callCenterInstance, myTasksCount: assignedTasksCount]
        }
    }

    def edit = {
        def callCenterInstance = CallCenter.get(params.id)
        if (!callCenterInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'callCenter.label', default: 'CallCenter'), params.id])}"
            redirect(controller: "task", action: "myTaskList")
        }
        else {
            [callCenterInstance: callCenterInstance, myTasksCount: assignedTasksCount]
        }
    }

    def update = {
        def callCenterInstance = CallCenter.get(params.id)
        if (callCenterInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (callCenterInstance.version > version) {
                    
                    callCenterInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'callCenter.label', default: 'CallCenter')] as Object[], "Another user has updated this CallCenter while you were editing")
                    render(view: "edit", model: [callCenterInstance: callCenterInstance, myTasksCount: assignedTasksCount])
                    return
                }
            }
            callCenterInstance.properties = params
            if (!callCenterInstance.hasErrors() && callCenterInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'callCenter.label', default: 'CallCenter'), callCenterInstance.id])}"
								Boolean isComplete = params["_action_update"].equals(message(code: 'default.button.complete.label', default: 'Complete'))
								if (isComplete) {
										completeTask(params)
								} else {
										params.action="show"
										saveTask(params)
								}				
                redirect(action: "show", id: callCenterInstance.id, params: [taskId:params.taskId, complete:isComplete?:null])
            }
            else {
                render(view: "edit", model: [callCenterInstance: callCenterInstance, myTasksCount: assignedTasksCount])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'callCenter.label', default: 'CallCenter'), params.id])}"
            redirect(controller: "task", action: "myTaskList")
        }
    }

    def delete = {
        def callCenterInstance = CallCenter.get(params.id)
        if (callCenterInstance) {
            try {
                callCenterInstance.delete(flush: true)
                deleteTask(params.taskId)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'callCenter.label', default: 'CallCenter'), params.id])}"
                redirect(controller: "task", action: "myTaskList")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'callCenter.label', default: 'CallCenter'), params.id])}"
                redirect(action: "show", id: params.id, params: params)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'callCenter.label', default: 'CallCenter'), params.id])}"
            redirect(controller: "task", action: "myTaskList")
        }
    }
}
