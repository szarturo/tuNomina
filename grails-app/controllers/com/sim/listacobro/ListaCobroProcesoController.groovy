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
        Boolean existeListaCobro = false
        if (params.id){
            //YA ESTA DEFINIDA LA LISTA DE COBRO, YA FUE INICIADO EL PROCESO
            log.info ("Se recupera la lista de cobro")
            listaCobroProcesoInstance.listaCobro = listaCobroProcesoInstance.get(params.id).listaCobro
            existeListaCobro = true
            //SE OBTIENE EL ESTATUS ACTUAL
            SimCatListaCobroEstatus estatus = listaCobroProcesoInstance.get(params.id).estatusListaCobro
            log.info ("Estatus actual: ${estatus}")

            switch ( estatus ) {
                case SimCatListaCobroEstatus.findByClaveListaEstatus('GENERAR'):
                    listaCobroProcesoInstance.estatusListaCobro = SimCatListaCobroEstatus.findByClaveListaEstatus('ENVIAR_DEPENDENCIA')
                    log.info ("Cambio a Estatus: ${listaCobroProcesoInstance.estatusListaCobro}")
                break
                case SimCatListaCobroEstatus.findByClaveListaEstatus('ENVIAR_DEPENDENCIA'):
                    listaCobroProcesoInstance.estatusListaCobro = SimCatListaCobroEstatus.findByClaveListaEstatus('INSTALAR_DEPENDENCIA')
                    log.info ("Cambio a Estatus: ${listaCobroProcesoInstance.estatusListaCobro}")
                break
                case SimCatListaCobroEstatus.findByClaveListaEstatus('INSTALAR_DEPENDENCIA'):
                    listaCobroProcesoInstance.estatusListaCobro = SimCatListaCobroEstatus.findByClaveListaEstatus('DEVOLVER_DEPENDENCIA')
                    log.info ("Cambio a Estatus: ${listaCobroProcesoInstance.estatusListaCobro}")
                break
                default:
                    log.info ("NO se definio el estatus para la lista de Cobro")
            }

        }else{
            //INICIO DEL PROCESO, NO ESTA DEFINIDA LA LISTA DE COBRO
            //SE INDICA EL ESTATUS DE GENERAR
            SimCatListaCobroEstatus estatus = SimCatListaCobroEstatus.findByClaveListaEstatus('GENERAR')
            listaCobroProcesoInstance.estatusListaCobro = estatus   
        }
        return [listaCobroProcesoInstance: listaCobroProcesoInstance,
                    existeListaCobro :existeListaCobro,
			        myTasksCount: assignedTasksCount]
    }

    def save = {
          def listaCobroProcesoInstance = new ListaCobroProceso(params)

          //SE RECUPERAN PARAMETROS QUE NO SON DEFINIDOS POR EL USUARIO
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
