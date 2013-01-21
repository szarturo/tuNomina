package com.sim.listacobro

import org.springframework.dao.DataIntegrityViolationException

class ListaCobroDetalleController {

    def filterPaneService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [listaCobroDetalleInstanceList: ListaCobroDetalle.list(params), listaCobroDetalleInstanceTotal: ListaCobroDetalle.count()]
    }

    def create() {
        [listaCobroDetalleInstance: new ListaCobroDetalle(params)]
    }

    def save() {
        def listaCobroDetalleInstance = new ListaCobroDetalle(params)
        if (!listaCobroDetalleInstance.save(flush: true)) {
            render(view: "create", model: [listaCobroDetalleInstance: listaCobroDetalleInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'listaCobroDetalle.label', default: 'ListaCobroDetalle'), listaCobroDetalleInstance.id])
        redirect(action: "show", id: listaCobroDetalleInstance.id)
    }

    def show(Long id) {
        def listaCobroDetalleInstance = ListaCobroDetalle.get(id)
        if (!listaCobroDetalleInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'listaCobroDetalle.label', default: 'ListaCobroDetalle'), id])
            redirect(action: "list")
            return
        }

        [listaCobroDetalleInstance: listaCobroDetalleInstance]
    }

    def edit(Long id) {
        def listaCobroDetalleInstance = ListaCobroDetalle.get(id)
        if (!listaCobroDetalleInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'listaCobroDetalle.label', default: 'ListaCobroDetalle'), id])
            redirect(action: "list")
            return
        }

        [listaCobroDetalleInstance: listaCobroDetalleInstance]
    }

    def update(Long id, Long version) {
        def listaCobroDetalleInstance = ListaCobroDetalle.get(id)
        if (!listaCobroDetalleInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'listaCobroDetalle.label', default: 'ListaCobroDetalle'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (listaCobroDetalleInstance.version > version) {
                listaCobroDetalleInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'listaCobroDetalle.label', default: 'ListaCobroDetalle')] as Object[],
                          "Another user has updated this ListaCobroDetalle while you were editing")
                render(view: "edit", model: [listaCobroDetalleInstance: listaCobroDetalleInstance])
                return
            }
        }

        listaCobroDetalleInstance.properties = params

        if (!listaCobroDetalleInstance.save(flush: true)) {
            render(view: "edit", model: [listaCobroDetalleInstance: listaCobroDetalleInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'listaCobroDetalle.label', default: 'ListaCobroDetalle'), listaCobroDetalleInstance.id])
        redirect(action: "show", id: listaCobroDetalleInstance.id)
    }

    def delete(Long id) {
        def listaCobroDetalleInstance = ListaCobroDetalle.get(id)
        if (!listaCobroDetalleInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'listaCobroDetalle.label', default: 'ListaCobroDetalle'), id])
            redirect(action: "list")
            return
        }

        try {
            listaCobroDetalleInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'listaCobroDetalle.label', default: 'ListaCobroDetalle'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'listaCobroDetalle.label', default: 'ListaCobroDetalle'), id])
            redirect(action: "show", id: id)
        }
    }

    def filter = {
        if(!params.max) params.max = 10
        render( view:'list', 
            model:[ listaCobroDetalleInstanceList: filterPaneService.filter( params, ListaCobroDetalle ), 
                listaCobroDetalleCount: filterPaneService.count( params, ListaCobroDetalle ), 
                filterParams: org.grails.plugin.filterpane.FilterPaneUtils.extractFilterParams(params), 
                params:params ] )
    }    
}
