package test

class DummyCreditoController {

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
        [dummyCreditoInstanceList: DummyCredito.list(params), 
			   dummyCreditoInstanceTotal: DummyCredito.count(),
			   myTasksCount: assignedTasksCount]
    }

    def create = {
        def dummyCreditoInstance = new DummyCredito()
        dummyCreditoInstance.properties = params
        return [dummyCreditoInstance: dummyCreditoInstance,
			          myTasksCount: assignedTasksCount]
    }

    def save = {
        def dummyCreditoInstance = new DummyCredito(params)
        if (dummyCreditoInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'dummyCredito.label', default: 'DummyCredito'), dummyCreditoInstance.id])}"
			      params.id = dummyCreditoInstance.id
						if (params.complete) {
							completeTask(params)
						} else {
							params.action="show"
							saveTask(params)
						}
            redirect(action: "show", params: params)
        }
        else {
            render(view: "create", model: [dummyCreditoInstance: dummyCreditoInstance, myTasksCount: assignedTasksCount])
        }
    }

    def show = {
        def dummyCreditoInstance = DummyCredito.get(params.id)
        if (!dummyCreditoInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'dummyCredito.label', default: 'DummyCredito'), params.id])}"
            redirect(controller: "task", action: "myTaskList")
        }
        else {
            [dummyCreditoInstance: dummyCreditoInstance, myTasksCount: assignedTasksCount]
        }
    }

    def edit = {
        def dummyCreditoInstance = DummyCredito.get(params.id)
        if (!dummyCreditoInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'dummyCredito.label', default: 'DummyCredito'), params.id])}"
            redirect(controller: "task", action: "myTaskList")
        }
        else {
            [dummyCreditoInstance: dummyCreditoInstance, myTasksCount: assignedTasksCount]
        }
    }

    def update = {
        def dummyCreditoInstance = DummyCredito.get(params.id)
        if (dummyCreditoInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (dummyCreditoInstance.version > version) {
                    
                    dummyCreditoInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'dummyCredito.label', default: 'DummyCredito')] as Object[], "Another user has updated this DummyCredito while you were editing")
                    render(view: "edit", model: [dummyCreditoInstance: dummyCreditoInstance, myTasksCount: assignedTasksCount])
                    return
                }
            }
            dummyCreditoInstance.properties = params
            if (!dummyCreditoInstance.hasErrors() && dummyCreditoInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'dummyCredito.label', default: 'DummyCredito'), dummyCreditoInstance.id])}"
								Boolean isComplete = params["_action_update"].equals(message(code: 'default.button.complete.label', default: 'Complete'))
								if (isComplete) {
										completeTask(params)
								} else {
										params.action="show"
										saveTask(params)
								}				
                redirect(action: "show", id: dummyCreditoInstance.id, params: [taskId:params.taskId, complete:isComplete?:null])
            }
            else {
                render(view: "edit", model: [dummyCreditoInstance: dummyCreditoInstance, myTasksCount: assignedTasksCount])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'dummyCredito.label', default: 'DummyCredito'), params.id])}"
            redirect(controller: "task", action: "myTaskList")
        }
    }

    def delete = {
        def dummyCreditoInstance = DummyCredito.get(params.id)
        if (dummyCreditoInstance) {
            try {
                dummyCreditoInstance.delete(flush: true)
                deleteTask(params.taskId)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'dummyCredito.label', default: 'DummyCredito'), params.id])}"
                redirect(controller: "task", action: "myTaskList")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'dummyCredito.label', default: 'DummyCredito'), params.id])}"
                redirect(action: "show", id: params.id, params: params)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'dummyCredito.label', default: 'DummyCredito'), params.id])}"
            redirect(controller: "task", action: "myTaskList")
        }
    }
}
