package com.sim.cliente

import com.rs.gral.RsPersona
import com.sim.catalogo.SimCatDocumento
import com.sim.catalogo.SimCatEscolaridad
import com.sim.catalogo.SimCatTipoPersona
import com.sim.entidad.EntDependencia

import grails.converters.JSON
import org.springframework.dao.DataIntegrityViolationException

class RsClienteController {

	def rsClienteService
	def filterPaneService
	def defaultAction = 'list'

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [rsClienteInstanceList: RsCliente.list(params), rsClienteInstanceTotal: RsCliente.count()]
    }

    def create() {
    	RsCliente rsClienteInstance = new RsCliente(params)
        RsPersona persona = RsPersona.read(params.idPersona) 
        rsClienteInstance.persona = persona
        [rsClienteInstance: rsClienteInstance]
    }

    def save() {
        def rsClienteInstance = new RsCliente(params)
        //SE OBTIENE LA PERSONA ASIGNADA
        //SE ASIGNAN LOS ATRIBUTOS DE LA PERSONA AL CLIENTE
        //ESTO SE TUVO QUE HACER POR PROBLEMAS CON EL PLUGIN FILTERPANE
        RsPersona persona = RsPersona.read(rsClienteInstance.persona.id)
        rsClienteInstance.apellidoPaterno = persona.apellidoPaterno
        rsClienteInstance.apellidoMaterno = persona.apellidoMaterno
        rsClienteInstance.primerNombre = persona.primerNombre
        rsClienteInstance.segundoNombre = persona.segundoNombre
        rsClienteInstance.rfc = persona.rfc

        if (!rsClienteInstance.save(flush: true)) {
            render(view: "create", model: [rsClienteInstance: rsClienteInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'rsCliente.label', default: 'RsCliente'), rsClienteInstance.id])
        redirect(action: "show", id: rsClienteInstance.id)
    }

    def show(Long id) {
        def rsClienteInstance = RsCliente.get(id)
        if (!rsClienteInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'rsCliente.label', default: 'RsCliente'), id])
            redirect(action: "list")
            return
        }

        [rsClienteInstance: rsClienteInstance]
    }

    def edit(Long id) {
        def rsClienteInstance = RsCliente.get(id)
        if (!rsClienteInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'rsCliente.label', default: 'RsCliente'), id])
            redirect(action: "list")
            return
        }

        [rsClienteInstance: rsClienteInstance]
    }

    def update(Long id, Long version) {
        def rsClienteInstance = RsCliente.get(id)
        if (!rsClienteInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'rsCliente.label', default: 'RsCliente'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (rsClienteInstance.version > version) {
                rsClienteInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'rsCliente.label', default: 'RsCliente')] as Object[],
                          "Another user has updated this RsCliente while you were editing")
                render(view: "edit", model: [rsClienteInstance: rsClienteInstance])
                return
            }
        }

        rsClienteInstance.properties = params

        if (!rsClienteInstance.save(flush: true)) {
            render(view: "edit", model: [rsClienteInstance: rsClienteInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'rsCliente.label', default: 'RsCliente'), rsClienteInstance.id])
        redirect(action: "show", id: rsClienteInstance.id)
    }

    def delete(Long id) {
        def rsClienteInstance = RsCliente.get(id)
        if (!rsClienteInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'rsCliente.label', default: 'RsCliente'), id])
            redirect(action: "list")
            return
        }

        try {
            rsClienteInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'rsCliente.label', default: 'RsCliente'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'rsCliente.label', default: 'RsCliente'), id])
            redirect(action: "show", id: id)
        }
    }


    def busquedaCliente = {
    }

    def listJSON = {
        render rsClienteService.findClientes(params) as JSON
    }

    def subgridJSON = {
        def persona = RsPersona.get(params.id)
        def cliente = persona?.datosCliente

        def results = cliente?.creditos?.collect {
            [
                    cell: [
                            it.folioSolicitud,
                            it.promocion.nombrePromocion,
                            (String)it.estatusSolicitud
                    ]
            ]
        }

        def jsonData = [rows: results]
        render jsonData as JSON
    }    

    def filter = {
        if(!params.max) params.max = 10
        render( view:'list', 
            model:[ rsClienteInstanceList: filterPaneService.filter( params, RsCliente ), 
                rsClienteCount: filterPaneService.count( params, RsCliente ), 
                filterParams: org.grails.plugin.filterpane.FilterPaneUtils.extractFilterParams(params), 
                params:params ] )
    }    


}
