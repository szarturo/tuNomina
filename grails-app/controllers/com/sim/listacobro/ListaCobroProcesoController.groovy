package com.sim.listacobro
import com.sim.catalogo.SimCatListaCobroEstatus
import com.sim.pfin.PfinCatParametro
import com.sim.usuario.Usuario

class ListaCobroProcesoController {

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
        [listaCobroProcesoInstanceList: ListaCobroProceso.list(params), 
			   listaCobroProcesoInstanceTotal: ListaCobroProceso.count(),
			   myTasksCount: assignedTasksCount]
    }

    def create = {
        def listaCobroProcesoInstance = new ListaCobroProceso()
        listaCobroProcesoInstance.properties = params
        return [listaCobroProcesoInstance: listaCobroProcesoInstance,
			          myTasksCount: assignedTasksCount]
    }

    def save = {
          def listaCobroProcesoInstance = new ListaCobroProceso(params)

          //SE RECUPERAN PARAMETROS QUE NO SON DEFINIDOS POR EL USUARIO
          SimCatListaCobroEstatus estatus = SimCatListaCobroEstatus.findByClaveListaEstatus('GENERAR')
          listaCobroProcesoInstance.estatusListaCobro = estatus
          PfinCatParametro parametros = PfinCatParametro.findByClaveMedio("SistemaMtn")
          Date fechaMedio = parametros?.fechaMedio  
          listaCobroProcesoInstance.fechaMedio = fechaMedio
          Usuario user = springSecurityService.getCurrentUser()
          listaCobroProcesoInstance.usuario = user


        if (listaCobroProcesoInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'listaCobroProceso.label', default: 'ListaCobroProceso'), listaCobroProcesoInstance.id])}"
			      params.id = listaCobroProcesoInstance.id

						if (params.complete) {
                            //CORRECCION A LOS BUGS DE ACTIVITI
                            params.remove("listaCobro")
                            params.remove("usuario")
                            params.remove("estatusListaCobro")
							completeTask(params)
						} else {
							params.action="show"
							saveTask(params)
						}
            redirect(action: "show", params: params)
        }
        else {
            render(view: "create", model: [listaCobroProcesoInstance: listaCobroProcesoInstance, myTasksCount: assignedTasksCount])
        }
    }

    def show = {
        def listaCobroProcesoInstance = ListaCobroProceso.get(params.id)
        if (!listaCobroProcesoInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'listaCobroProceso.label', default: 'ListaCobroProceso'), params.id])}"
            redirect(controller: "task", action: "myTaskList")
        }
        else {
            [listaCobroProcesoInstance: listaCobroProcesoInstance, myTasksCount: assignedTasksCount]
        }
    }

    def edit = {
        def listaCobroProcesoInstance = ListaCobroProceso.get(params.id)
        if (!listaCobroProcesoInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'listaCobroProceso.label', default: 'ListaCobroProceso'), params.id])}"
            redirect(controller: "task", action: "myTaskList")
        }
        else {
            [listaCobroProcesoInstance: listaCobroProcesoInstance, myTasksCount: assignedTasksCount]
        }
    }

    def update = {
        def listaCobroProcesoInstance = ListaCobroProceso.get(params.id)
        if (listaCobroProcesoInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (listaCobroProcesoInstance.version > version) {
                    
                    listaCobroProcesoInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'listaCobroProceso.label', default: 'ListaCobroProceso')] as Object[], "Another user has updated this ListaCobroProceso while you were editing")
                    render(view: "edit", model: [listaCobroProcesoInstance: listaCobroProcesoInstance, myTasksCount: assignedTasksCount])
                    return
                }
            }
            listaCobroProcesoInstance.properties = params

              //SE RECUPERAN PARAMETROS QUE NO SON DEFINIDOS POR EL USUARIO
              SimCatListaCobroEstatus estatus = SimCatListaCobroEstatus.findByClaveListaEstatus('ENVIAR_DEPENDENCIA')
              listaCobroProcesoInstance.estatusListaCobro = estatus
              PfinCatParametro parametros = PfinCatParametro.findByClaveMedio("SistemaMtn")
              Date fechaMedio = parametros?.fechaMedio  
              listaCobroProcesoInstance.fechaMedio = fechaMedio
              Usuario user = springSecurityService.getCurrentUser()
              listaCobroProcesoInstance.usuario = user
              
            if (!listaCobroProcesoInstance.hasErrors() && listaCobroProcesoInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'listaCobroProceso.label', default: 'ListaCobroProceso'), listaCobroProcesoInstance.id])}"
								Boolean isComplete = params["_action_update"].equals(message(code: 'default.button.complete.label', default: 'Complete'))
								if (isComplete) {
                                    //CORRECCION A LOS BUGS DE ACTIVITI
                                        params.remove("listaCobro")
                                        params.remove("usuario")
                                        params.remove("estatusListaCobro")
										completeTask(params)
								} else {
										params.action="show"
										saveTask(params)
								}				
                redirect(action: "show", id: listaCobroProcesoInstance.id, params: [taskId:params.taskId, complete:isComplete?:null])
            }
            else {
                render(view: "edit", model: [listaCobroProcesoInstance: listaCobroProcesoInstance, myTasksCount: assignedTasksCount])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'listaCobroProceso.label', default: 'ListaCobroProceso'), params.id])}"
            redirect(controller: "task", action: "myTaskList")
        }
    }

    def delete = {
        def listaCobroProcesoInstance = ListaCobroProceso.get(params.id)
        if (listaCobroProcesoInstance) {
            try {
                listaCobroProcesoInstance.delete(flush: true)
                deleteTask(params.taskId)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'listaCobroProceso.label', default: 'ListaCobroProceso'), params.id])}"
                redirect(controller: "task", action: "myTaskList")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'listaCobroProceso.label', default: 'ListaCobroProceso'), params.id])}"
                redirect(action: "show", id: params.id, params: params)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'listaCobroProceso.label', default: 'ListaCobroProceso'), params.id])}"
            redirect(controller: "task", action: "myTaskList")
        }
    }
}
