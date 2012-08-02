package vacationRequest

import org.grails.activiti.ApprovalStatus
import org.codehaus.groovy.grails.commons.ConfigurationHolder as CH
import org.grails.activiti.ActivitiConstants
import com.sim.usuario.Usuario

class VacationRequestController {

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
        [vacationRequestInstanceList: VacationRequest.list(params), 
				vacationRequestInstanceTotal: VacationRequest.count(),
				myTasksCount: assignedTasksCount]				
    }

    def create = {
        def vacationRequestInstance = new VacationRequest()
        String sessionUsernameKey = CH.config.activiti.sessionUsernameKey?:ActivitiConstants.DEFAULT_SESSION_USERNAME_KEY
				vacationRequestInstance.employeeName = session[sessionUsernameKey]
        vacationRequestInstance.properties = params
        return [vacationRequestInstance: vacationRequestInstance,
                myTasksCount: assignedTasksCount]
    }

    def save = {		
        def vacationRequestInstance = new VacationRequest(params)
        if (vacationRequestInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'vacationRequest.label', default: 'VacationRequest'), vacationRequestInstance.id])}"
            params.id = vacationRequestInstance.id
						if (params.complete) {
							completeTask(params)
						} else {
							params.action="show"
							saveTask(params)
						}
			      redirect(action: "show", params: params)
        }
        else {
            render(view: "create", model: [vacationRequestInstance: vacationRequestInstance, 
                                           myTasksCount: assignedTasksCount])
        }
    }

    def show = {
        def vacationRequestInstance = VacationRequest.get(params.id)
        if (!vacationRequestInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'vacationRequest.label', default: 'VacationRequest'), params.id])}"
            redirect(controller: "task", action: "myTaskList")
        }
        else {
            [vacationRequestInstance: vacationRequestInstance,
             myTasksCount: assignedTasksCount]				
        }
    }
	
   def approval = {
        show()
      }	

	    
   def performApproval = {
        def vacationRequestInstance = VacationRequest.get(params.id)
        if (vacationRequestInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (vacationRequestInstance.version > version) {
                    
                    vacationRequestInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'vacationRequest.label', default: 'VacationRequest')] as Object[], "Another user has updated this VacationRequest while you were editing")
                    render(view: "approval", model: [vacationRequestInstance: vacationRequestInstance,
                                                     myTasksCount: assignedTasksCount])
                    return
                }
            }
            vacationRequestInstance.properties = params
            if (!vacationRequestInstance.hasErrors() && vacationRequestInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'vacationRequest.label', default: 'VacationRequest'), vacationRequestInstance.id])}"
                if (params.complete) {
										//RECUPERA EL USUARIO PARA ENVIAR EL CORREO
										def Usuario = Usuario.findByUsername(vacationRequestInstance.properties.employeeName)
										println ("Usuario Correo: " +Usuario.persona.email)
										params.id = vacationRequestInstance.id
										params.vacationApproved = vacationRequestInstance.approvalStatus == ApprovalStatus.APPROVED
										params.from = grailsApplication.config.activiti.mailServerDefaultFrom
										params.emailTo = Usuario.persona.email
										params.approvalRemark = params.approvalRemark && params.approvalRemark != "" ? params.approvalRemark : "No Approval Remark."
		                completeTask(params)
								} else {
										params.action="approval"
										saveTask(params)
								}				
								params.isApproval = true
                redirect(action: "show", id: vacationRequestInstance.id, params: params)
								
            }
            else {
                render(view: "approval", model: [vacationRequestInstance: vacationRequestInstance,
                                                 myTasksCount: assignedTasksCount])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'vacationRequest.label', default: 'VacationRequest'), params.id])}"
            redirect(controller: "task", action: "myTaskList")
        }
      }	

    def edit = {
        def vacationRequestInstance = VacationRequest.get(params.id)
        if (!vacationRequestInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'vacationRequest.label', default: 'VacationRequest'), params.id])}"
            redirect(controller: "task", action: "myTaskList")
        }
        else {
            return [vacationRequestInstance: vacationRequestInstance,
                    myTasksCount: assignedTasksCount]
        }
    }

    def update = {			
        def vacationRequestInstance = VacationRequest.get(params.id)
        if (vacationRequestInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (vacationRequestInstance.version > version) {
                    
                    vacationRequestInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'vacationRequest.label', default: 'VacationRequest')] as Object[], "Another user has updated this VacationRequest while you were editing")
                    render(view: "edit", model: [vacationRequestInstance: vacationRequestInstance,
                                                 myTasksCount: assignedTasksCount])
                    return
                }
            }
            vacationRequestInstance.properties = params
            if (!vacationRequestInstance.hasErrors() && vacationRequestInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'vacationRequest.label', default: 'VacationRequest'), vacationRequestInstance.id])}"
				        Boolean isComplete = params["_action_update"].equals(message(code: 'default.button.complete.label', default: 'Complete'))
								if (isComplete) {
										params.resendRequest = vacationRequestInstance.resendRequest
										completeTask(params)
								} else {
										params.action="show"
										saveTask(params)
								}				
                redirect(action: "show", id: vacationRequestInstance.id, params: [taskId:params.taskId, complete:isComplete?:null])
            }
            else {
                render(view: "edit", model: [vacationRequestInstance: vacationRequestInstance,
                                             myTasksCount: assignedTasksCount])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'vacationRequest.label', default: 'VacationRequest'), params.id])}"
            redirect(controller: "task", action: "myTaskList")
        }
    }

    def delete = {
        def vacationRequestInstance = VacationRequest.get(params.id)
        if (vacationRequestInstance) {
            try {
                vacationRequestInstance.delete(flush: true)
								deleteTask(params.taskId)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'vacationRequest.label', default: 'VacationRequest'), params.id])}"
                redirect(controller: "task", action: "myTaskList")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'vacationRequest.label', default: 'VacationRequest'), params.id])}"
                redirect(action: "show", id: params.id, params: params)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'vacationRequest.label', default: 'VacationRequest'), params.id])}"
            redirect(controller: "task", action: "myTaskList")
        }
    }
}
