package com.sim.credito

import com.sim.alfresco.AlfrescoService
import com.sim.catalogo.SimCatFormaEntrega
import com.sim.cliente.RsCliente
import com.sim.usuario.Usuario
import com.sim.usuario.UsuarioAcceso
import com.sim.tablaAmortizacion.TablaAmortizacionServiceException
import com.sim.entidad.EntSucursal
import org.apache.chemistry.opencmis.client.api.CmisObject
import org.apache.chemistry.opencmis.client.api.Document
import org.apache.chemistry.opencmis.client.api.Folder
import org.grails.activiti.ApprovalStatus

class PrestamoController {
	
	def tablaAmortizacionRegistroService
    def springSecurityService
    def prestamoService
    def filterPaneService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
    static activiti = true

    def index = {
        redirect(action: "list", params: params)
    }

    def start = {
        flash.idCliente = params.idCliente
        start(params)
    }
	
    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)

        ArrayList prestamoInstanceList = Prestamo.list(params)
        //SE RESTRINGEN LOS PRESTAMOS A LOS QUE SE TIENEN ACCESO
        prestamoInstanceList = accesoPrestamos(prestamoInstanceList)

        [prestamoInstanceList: prestamoInstanceList, 
			   prestamoInstanceTotal: Prestamo.count(),
			   myTasksCount: assignedTasksCount]
    }

    def create = {
        RsCliente cliente = RsCliente.read(flash.idCliente)
        def prestamoInstance = new Prestamo()
        prestamoInstance.properties = params
        prestamoInstance.cliente = cliente        
        return [prestamoInstance: prestamoInstance,
			          myTasksCount: assignedTasksCount]
    }

    def save = {
        def prestamoInstance = new Prestamo(params)
		if (params.complete) {
			prestamoInstance.estatusSolicitud = PrestamoEstatus.CAPTURADA_MESA
		}else{
			prestamoInstance.estatusSolicitud = PrestamoEstatus.INICIO_MESA
		}
		prestamoInstance.approvalStatus = ApprovalStatus.PENDING
		prestamoInstance.documentosCorrectos = false
		prestamoInstance.aprobado = false
		prestamoInstance.reenviarSolicitud = false
        prestamoInstance.incluirEnListasCobro = true
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
            //SE GENERAN LOS ACCESORIOS A PARTIR DE LA PROMOCION DEFINIDA
            Boolean accesoriosCreados = prestamoService.generarAccesoriosPrestamo(prestamoInstance)
            log.info ("Resultado accesorios creados: ${accesoriosCreados}")
			if (params.complete) {
				//LOS SIGUIENTES PARAMETROS CAUSABAN PROBLEMAS CON ACTIVITI
				//SIN EMBARGO SI PASA CORRECTAMENTE LOS ID DE CADA PARAMETRO ELIMINADO
				params.remove("dependencia")
				params.remove("promocion")
				params.remove("sucursal")
				params.remove("delegacion")
				params.remove("vendedor")
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
            render(view: "create", model: [prestamoInstance: prestamoInstance, myTasksCount: assignedTasksCount, parametroNombreVendedor:params.nombreVendedor])
        }
    }

    def show = {
        def prestamoInstance = Prestamo.get(params.id)
        if (!prestamoInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'prestamo.label', default: 'Prestamo'), params.id])}"
            redirect(controller: "task", action: "myTaskList")
        }
        else {

            /*
			try{
				List<Document> documentos = new ArrayList<Document>();
				AlfrescoService service = new AlfrescoService();
				Object o = null
				
				o=service.getByPath("/Sites/tuNomina/creditos/${prestamoInstance.cliente.id}/${prestamoInstance.folioSolicitud}");
				
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
            */
            //SE OBTIENE LOS DOCUMENTOS QUE SE GUARDARON CON LA SOLICITUD
            def path = new File("${System.getProperty('user.home')}/Documents/tuNomina/documentosCredito/${prestamoInstance.cliente.id}/${prestamoInstance.folioSolicitud}")
            def documentos = path.listFiles()

            [prestamoInstance: prestamoInstance, 
                myTasksCount: assignedTasksCount,
                documentos: documentos,
                path:path]
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
			
			PrestamoEstatus estatusSolicitud = prestamoInstance.estatusSolicitud
			log.info("Estatus de la solicitud: "+estatusSolicitud)

            String consecutivo 

			Boolean isComplete = params["_action_update"].equals(message(code: 'default.button.complete.label', default: 'Complete'))
			if (isComplete) {

				if (estatusSolicitud.equals(PrestamoEstatus.INICIO_MESA)){
					prestamoInstance.estatusSolicitud = PrestamoEstatus.CAPTURADA_MESA
					prestamoInstance.approvalStatus = ApprovalStatus.PENDING
				}else if(estatusSolicitud.equals(PrestamoEstatus.CAPTURADA_MESA) && params.aprobado.equals("on")){
                    //EL PRESTAMO ES ENVIADO A CREDITO REAL
					prestamoInstance.estatusSolicitud = PrestamoEstatus.PROCESADA
					prestamoInstance.approvalStatus = ApprovalStatus.PENDING
                    //consecutivo = prestamoService.envioSolicitudCreditoReal(prestamoInstance)
                    //EN EL AMBIENTE DE DESARROLLO HAY QUE COMENTAR LA LINEA DE ARRIBA
                    //Y DESCOMENTAR LA LINEA DE ABAJO
                    consecutivo = 'Prueba'
				}else if(estatusSolicitud.equals(PrestamoEstatus.CAPTURADA_MESA) && !params.aprobado.equals("on")){
					prestamoInstance.estatusSolicitud = PrestamoEstatus.DEVOLUCION_AMESA
					prestamoInstance.approvalStatus = ApprovalStatus.REJECTED
				}else if(estatusSolicitud.equals(PrestamoEstatus.DEVOLUCION_AMESA) && params.reenviarSolicitud.equals("on")){
					prestamoInstance.estatusSolicitud = PrestamoEstatus.CAPTURADA_MESA
					prestamoInstance.approvalStatus = ApprovalStatus.PENDING
				}else if(estatusSolicitud.equals(PrestamoEstatus.DEVOLUCION_CR) && params.aprobado.equals("on")){
                    log.info("El prestamo es envido otra vez a CR")
                    log.info("Estatus actual: DEVOLUCION_CR")
                    log.info("Valor Solo enviar docto: "+params.soloEnviarDocumento)
                    //EL PRESTAMO ES OTRA VEZ ENVIADO A CREDITO REAL
                    prestamoInstance.estatusSolicitud = PrestamoEstatus.PROCESADA
                    prestamoInstance.approvalStatus = ApprovalStatus.PENDING
                    //VALIDAR SI SOLO FUE ENVIADO UNO O MAS DOCUMENTOS
                    if(!params.soloEnviarDocumento.equals("on")){
                        log.info("Se vuelve a enviar los datos del prestamo")
                        //SE ENVIA DE NUEVO TODO LOS DATOS DEL PRESTAMO A CREDITO REAL
                        //consecutivo = prestamoService.envioSolicitudCreditoReal(prestamoInstance)
                        //EN EL AMBIENTE DE DESARROLLO HAY QUE COMENTAR LA LINEA DE ARRIBA
                        //Y DESCOMENTAR LA LINEA DE ABAJO
                        consecutivo = 'Prueba'
                    }
                }else if(estatusSolicitud.equals(PrestamoEstatus.DEVOLUCION_CR) && !params.aprobado.equals("on")){
                    prestamoInstance.estatusSolicitud = PrestamoEstatus.DEVOLUCION_AMESA
                    prestamoInstance.approvalStatus = ApprovalStatus.REJECTED
                }else if(estatusSolicitud.equals(PrestamoEstatus.CANCELADA_CR) && params.aprobado.equals("on")){
                    log.info("El prestamo es envido otra vez a CR")
                    log.info("Estatus actual: CANCELADA_CR")
                    log.info("Valor Solo enviar docto: "+params.soloEnviarDocumento)
                    //EL PRESTAMO ES OTRA VEZ ENVIADO A CREDITO REAL
                    prestamoInstance.estatusSolicitud = PrestamoEstatus.PROCESADA
                    prestamoInstance.approvalStatus = ApprovalStatus.PENDING
                    //VALIDAR SI SOLO FUE ENVIADO UNO O MAS DOCUMENTOS
                    if(!params.soloEnviarDocumento.equals("on")){
                        log.info("Se vuelve a enviar los datos del prestamo")
                        //SE ENVIA DE NUEVO TODO LOS DATOS DEL PRESTAMO A CREDITO REAL
                        //consecutivo = prestamoService.envioSolicitudCreditoReal(prestamoInstance)
                        //EN EL AMBIENTE DE DESARROLLO HAY QUE COMENTAR LA LINEA DE ARRIBA
                        //Y DESCOMENTAR LA LINEA DE ABAJO
                        consecutivo = 'Prueba'

                    }
                }else if(estatusSolicitud.equals(PrestamoEstatus.CANCELADA_CR) && !params.aprobado.equals("on")){
                    prestamoInstance.estatusSolicitud = PrestamoEstatus.DEVOLUCION_AMESA
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
			log.error "Failed:", errorGeneraTablaAmor
            flash.message = message(code: errorGeneraTablaAmor.mensaje, args: [])
			redirect(action: "show", params: [id: params.idPrestamo])
			return
		}catch(Exception errorTablaAmortizacion){
            log.error "Failed:", errorTablaAmortizacion
            flash.message = message(code: "No se genero la tabla de Amortizacion. Contacte al Administrador", args: [])
            redirect(action: "show", params: [id: params.idPrestamo])
            return            
        }
		redirect(controller: "tablaAmortizacionRegistro", action: "list", params: [idPrestamo: params.idPrestamo])
	}

    def validaRespuestaCr = {

        def prestamoInstance = Prestamo.get(params.id)

        log.info "Estatus Solicitud: ${prestamoInstance.estatusSolicitud}"

        if (prestamoInstance.estatusSolicitud.equals(PrestamoEstatus.PROCESADA)){
            log.info "La solicitud continua en ${prestamoInstance.folioSolicitud}"
            flash.message = " La solicitud ${prestamoInstance.folioSolicitud} continua en ${prestamoInstance.estatusSolicitud}"
        }else if(prestamoInstance.estatusSolicitud.equals(PrestamoEstatus.AUTORIZADA)){
            log.info "La solicitud se encuentra en ${prestamoInstance.folioSolicitud}"
            flash.message = " La solicitud ${prestamoInstance.folioSolicitud} se encuentra en ${prestamoInstance.estatusSolicitud}"
        }else{

            //RECUPERA EL ESTATUS DE LA SOLICITUD ACTUAL
            params.estatusSolicitudActual =  (String)prestamoInstance.estatusSolicitud
            completeTask(params)
            flash.message = " Credito Real cambio el estatus de la solicitud ${prestamoInstance.folioSolicitud} a ${prestamoInstance.estatusSolicitud}"
        }
        redirect(controller: "task", action: "myTaskList")
    }


   def dispersarPrestamo = {

        def prestamoInstance = Prestamo.get(params.id)

        if (prestamoInstance.estatusSolicitud.equals(PrestamoEstatus.COMPRADA)){
            log.info "La solicitud continua en ${prestamoInstance.folioSolicitud}, necesita ser DISPERSADA"
            flash.message = " La solicitud ${prestamoInstance.folioSolicitud} continua en ${prestamoInstance.estatusSolicitud}, necesita ser DISPERSADA"
        }else if(prestamoInstance.estatusSolicitud.equals(PrestamoEstatus.DISPERSADA)){
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
                prestamoInstance.estatusSolicitud = PrestamoEstatus.PENDIENTE_COBRO
            }else{
                prestamoInstance.estatusSolicitud = PrestamosEstatus.ACTIVO
            }

            flash.message = "La solicitud ${prestamoInstance.folioSolicitud} cambio del estatus POR DISPERSAR al estatus ${prestamoInstance.estatusSolicitud}"
            prestamoInstance.save(flush: true)
            completeTask(params)
            
        }else{
            flash.message = " La solicitud ${prestamoInstance.folioSolicitud} debe cambiar al estatus  ${PrestamoEstatus.DISPERSADA}"
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

    def showSolicitudesDecididasDia = {
        //METODO PARA MOSTRAR LA GSP showSolicitudesDecididasDia
    }

    def solicitudesDecididasDia = {
        log.info("Distribuidor:"+params.distribuidor)
        try{
            def respuesta = prestamoService.solicitudesDecididasDia(
                    params.distribuidor,
                    params.fecha_day,
                    params.fecha_month,
                    params.fecha_year)    
        }catch(PrestamoServiceException errorSolicitudesDecididas){
            log.error "Failed:", errorSolicitudesDecididas
            flash.message = message(code: errorSolicitudesDecididas.mensaje, args: [])
            redirect(action: "list")
            return            
        }catch(Exception error){
            log.error "Failed:", error
            flash.message = message(code: "Se genero un problema de comunicación. Contacte al Administrador.", args: [])
            redirect(action: "list")
            return            
        }
        flash.message = message(code: "Se han actualizado los estatus de los prestamos.", args: [])
        redirect(action: "list")
    }

    def showComprasDia = {
        //METODO PARA MOSTRAR LA GSP showSolicitudesDecididasDia
    }    

    def comprasDia = {
        log.info("Distribuidor:"+params.distribuidor)        
        try{
            def respuesta = prestamoService.comprasDia(
                    params.distribuidor,
                    params.fecha_day,
                    params.fecha_month,
                    params.fecha_year)   
        }catch(PrestamoServiceException errorComprasDia){
            log.error "Failed:", errorComprasDia
            flash.message = message(code: errorComprasDia.mensaje, args: [])
            redirect(action: "list")
            return            
        }catch(Exception error){
            log.error "Failed:", error
            flash.message = message(code: "Se genero un problema de comunicación. Contacte al Administrador.", args: [])
            redirect(action: "list")
            return            
        }
        flash.message = message(code: "Se han actualizado las compras del día.", args: [])
        redirect(action: "list")
    }

    def showCarteraGenerada = {
        //METODO PARA MOSTRAR LA GSP showCarteraGenerada
    }    

    def carteraGeneradaDia = {
        log.info("Distribuidor:"+params.distribuidor)        
        try{
            def respuesta = prestamoService.carteraGeneradaDia(
                    params.distribuidor,
                    params.fecha_day,
                    params.fecha_month,
                    params.fecha_year)   
        }catch(PrestamoServiceException errorComprasDia){
            log.error "Failed:", errorComprasDia
            flash.message = message(code: errorComprasDia.mensaje, args: [])
            redirect(action: "list")
            return            
        }catch(Exception error){
            log.error "Failed:", error
            flash.message = message(code: "Se genero un problema de comunicación. Contacte al Administrador.", args: [])
            redirect(action: "list")
            return            
        }
        flash.message = message(code: "Se ha actualizado la Cartera Generada.", args: [])
        redirect(action: "list")
    }

    def altaPrestamo = {
        def datosIntroducidos
        datosIntroducidos = prestamoService.altaPrestamos()
        log.info("Los prestamos se dieron de alta correctamente: " + datosIntroducidos)
        prestamoService.altaAccesorios()
        redirect(action: "list")
    }

    def generaTablaAmortizacionVarias = {
        ArrayList prestamosExistentes = Prestamo.findAll()
        prestamosExistentes.each() {
            def idPrestamo = it.id
            Prestamo prestamoInstance = Prestamo.get(params.idPrestamo)
            try{
                tablaAmortizacionRegistroService.generaTablaAmortizacion(it)
                //VERIFICAR SI SE GENERO ALGUN ERROR
            }catch(TablaAmortizacionServiceException errorGeneraTablaAmor){
                //prestamoInstance.errors.reject("ErrorGeneraTablaAmor",errorGeneraTablaAmor.mensaje)
                log.error "Failed:", errorGeneraTablaAmor
                flash.message = message(code: errorGeneraTablaAmor.mensaje, args: [])
                //AL APLICAR LA FUNCIONALIDAD REAL HAY QUE MOSTRAR EL MENSAJE
                redirect(action: "show", params: [id: params.idPrestamo])
                return
            }catch(Exception errorTablaAmortizacion){
                //prestamoInstance.errors.reject("errorAplicaPago","No se aplico el Pago. Contacte al Administrador")
                log.error "Failed:", errorTablaAmortizacion
                flash.message = message(code: "No se genero la tabla de Amortizacion. Contacte al Administrador", args: [])
                redirect(action: "show", params: [id: params.idPrestamo])
                return
            }

        }
        redirect(action: "list")
    }

    def filter = {
        if(!params.max) params.max = 10
        ArrayList prestamoInstanceList = filterPaneService.filter( params, Prestamo )
        //SE RESTRINGEN LOS PRESTAMOS A LOS QUE SE TIENEN ACCESO
        prestamoInstanceList = accesoPrestamos(prestamoInstanceList)

        render( view:'list', 
            model:[ prestamoInstanceList: prestamoInstanceList, 
                prestamoCount: filterPaneService.count( params, Prestamo ), 
                filterParams: org.grails.plugin.filterpane.FilterPaneUtils.extractFilterParams(params), 
                myTasksCount: assignedTasksCount,
                params:params ] )
    }

    ArrayList accesoPrestamos(ArrayList prestamoInstanceList){

        //ARREGLO PARA GUARDAR LOS PRESTAMOS PERMITIDOS
        ArrayList prestamosPermitidos = []

        //ARREGLO PARA ALMACENAR LAS SUCURSALES PERMITIDAS
        ArrayList sucursalesPermitidas = []

        //SE OBTIENE EL USUARIO
        Usuario usuario = springSecurityService.getCurrentUser()
        //SE OBTIENE LOS ACCESOS DEL USUARIO
        UsuarioAcceso usuarioAcceso = UsuarioAcceso.findByUsuario(usuario)
        if (!usuarioAcceso){
            //NO SE HAN DEFINIDO LOS ACCESOS AL USUARIO
            log.info "No se han definido los accesos al usuario, tiene acceso a todos los prestamos"
        }else{
            if (usuarioAcceso.accesoTodo){
                log.info "Tiene acceso a todos los prestamos"
            }else{
                if (usuarioAcceso.regiones){
                    log.info "El usuario tiene definido regionales"

                    //ITERA LAS REGIONALES PARA OBTENER LAS SUCURSALES
                    usuarioAcceso.regiones.each{region->
                        region.estados.each{estado->
                            estado.sucursal.each{sucursal->
                                sucursalesPermitidas.add(sucursal.claveSucursal)    
                            }
                        }
                    }

                    log.info "Sucursales Permitidas: ${sucursalesPermitidas}"
                    //ITERA LOS PRESTAMOS OBTENIDOS
                    prestamoInstanceList.each{prestamo->
                        //VALIDA SI LA SUCURSAL PERMITIDA ESTA EN EL ARREGLO DE SUCURSALES PERMITIDAS
                        if (sucursalesPermitidas.contains(prestamo.sucursal.claveSucursal)){
                            log.info "El prestamos si se encuentra en la sucursal"
                            prestamosPermitidos.add(prestamo)
                        }else{
                            log.info "El prestamos no se encuentra en la sucursal"
                        }
                    }
                    prestamoInstanceList = prestamosPermitidos

                }else{
                    //DEBE TENER ASIGNADO LAS SUCURSALES A LAS QUE TIENE ACESO
                    if (usuarioAcceso.sucursales){
                        log.info "El usuario tiene definido sucursales"
                        usuarioAcceso.sucursales.each{
                            //GUARDA LAS CLAVES DE SUCURSALES EN EL ARREGLO
                            sucursalesPermitidas.add(it.claveSucursal)
                        }

                        //ITERA LOS PRESTAMOS OBTENIDOS
                        prestamoInstanceList.each{prestamo->
                            //VALIDA SI LA SUCURSAL PERMITIDA ESTA EN EL ARREGLO DE SUCURSALES PERMITIDAS
                            if (sucursalesPermitidas.contains(prestamo.sucursal.claveSucursal)){
                                log.info "El prestamos si se encuentra en la sucursal"
                                prestamosPermitidos.add(prestamo)
                            }else{
                                log.info "El prestamos no se encuentra en la sucursal"
                            }
                        }
                        prestamoInstanceList = prestamosPermitidos

                    }else{
                        //NO SE LE HA DEFINIDO SUCURSALES
                        log.info "No se le han definido al usuario ni sucursales ni regionales"
                        prestamoInstanceList=[]
                    }

                }
            }
        }
        return prestamoInstanceList
    }

}