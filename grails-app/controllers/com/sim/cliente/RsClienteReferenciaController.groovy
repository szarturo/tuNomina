package com.sim.cliente

import org.springframework.dao.DataIntegrityViolationException

class RsClienteReferenciaController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [rsClienteReferenciaInstanceList: RsClienteReferencia.list(params), rsClienteReferenciaInstanceTotal: RsClienteReferencia.count()]
    }

    def create() {
        def rsClienteReferenciaInstance = new RsClienteReferencia()
        rsClienteReferenciaInstance.properties = params

        if (params.rsCliente){
            def rsClienteInstance = new RsCliente()
            rsClienteInstance = RsCliente.get(params.rsCliente.id)
            rsClienteReferenciaInstance.cliente = rsClienteInstance
        }

        return [rsClienteReferenciaInstance: rsClienteReferenciaInstance]
    }

    def save() {
        def rsClienteReferenciaInstance = new RsClienteReferencia(params)
        if (!rsClienteReferenciaInstance.save(flush: true)) {
            render(view: "create", model: [rsClienteReferenciaInstance: rsClienteReferenciaInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'rsClienteReferencia.label', default: 'RsClienteReferencia'), rsClienteReferenciaInstance.id])
        redirect(action: "show", id: rsClienteReferenciaInstance.id)
    }

    def show(Long id) {
        def rsClienteReferenciaInstance = RsClienteReferencia.get(id)
        if (!rsClienteReferenciaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'rsClienteReferencia.label', default: 'RsClienteReferencia'), id])
            redirect(action: "list")
            return
        }

        [rsClienteReferenciaInstance: rsClienteReferenciaInstance]
    }

    def edit(Long id) {
        def rsClienteReferenciaInstance = RsClienteReferencia.get(id)
        if (!rsClienteReferenciaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'rsClienteReferencia.label', default: 'RsClienteReferencia'), id])
            redirect(action: "list")
            return
        }

        [rsClienteReferenciaInstance: rsClienteReferenciaInstance]
    }

    def update(Long id, Long version) {
        def rsClienteReferenciaInstance = RsClienteReferencia.get(id)
        if (!rsClienteReferenciaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'rsClienteReferencia.label', default: 'RsClienteReferencia'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (rsClienteReferenciaInstance.version > version) {
                rsClienteReferenciaInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'rsClienteReferencia.label', default: 'RsClienteReferencia')] as Object[],
                          "Another user has updated this RsClienteReferencia while you were editing")
                render(view: "edit", model: [rsClienteReferenciaInstance: rsClienteReferenciaInstance])
                return
            }
        }

        rsClienteReferenciaInstance.properties = params

        if (!rsClienteReferenciaInstance.save(flush: true)) {
            render(view: "edit", model: [rsClienteReferenciaInstance: rsClienteReferenciaInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'rsClienteReferencia.label', default: 'RsClienteReferencia'), rsClienteReferenciaInstance.id])
        redirect(action: "show", id: rsClienteReferenciaInstance.id)
    }

    def delete(Long id) {
        def rsClienteReferenciaInstance = RsClienteReferencia.get(id)
        if (!rsClienteReferenciaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'rsClienteReferencia.label', default: 'RsClienteReferencia'), id])
            redirect(action: "list")
            return
        }

        try {
            rsClienteReferenciaInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'rsClienteReferencia.label', default: 'RsClienteReferencia'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'rsClienteReferencia.label', default: 'RsClienteReferencia'), id])
            redirect(action: "show", id: id)
        }
    }
}
