package com.sim.cliente

import org.springframework.dao.DataIntegrityViolationException

class RsClienteEmpleoController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [rsClienteEmpleoInstanceList: RsClienteEmpleo.list(params), rsClienteEmpleoInstanceTotal: RsClienteEmpleo.count()]
    }

    def create() {
        def rsClienteEmpleoInstance = new RsClienteEmpleo()
        rsClienteEmpleoInstance.properties = params

        if (params.rsCliente){
            def rsClienteInstance = new RsCliente()
            rsClienteInstance = RsCliente.get(params.rsCliente.id)
            rsClienteEmpleoInstance.cliente = rsClienteInstance
        }

        return [rsClienteEmpleoInstance: rsClienteEmpleoInstance]
    }

    def save() {
        def rsClienteEmpleoInstance = new RsClienteEmpleo(params)
        if (!rsClienteEmpleoInstance.save(flush: true)) {
            render(view: "create", model: [rsClienteEmpleoInstance: rsClienteEmpleoInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'rsClienteEmpleo.label', default: 'RsClienteEmpleo'), rsClienteEmpleoInstance.id])
        redirect(action: "show", id: rsClienteEmpleoInstance.id)
    }

    def show(Long id) {
        def rsClienteEmpleoInstance = RsClienteEmpleo.get(id)
        if (!rsClienteEmpleoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'rsClienteEmpleo.label', default: 'RsClienteEmpleo'), id])
            redirect(action: "list")
            return
        }

        [rsClienteEmpleoInstance: rsClienteEmpleoInstance]
    }

    def edit(Long id) {
        def rsClienteEmpleoInstance = RsClienteEmpleo.get(id)
        if (!rsClienteEmpleoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'rsClienteEmpleo.label', default: 'RsClienteEmpleo'), id])
            redirect(action: "list")
            return
        }

        [rsClienteEmpleoInstance: rsClienteEmpleoInstance]
    }

    def update(Long id, Long version) {
        def rsClienteEmpleoInstance = RsClienteEmpleo.get(id)
        if (!rsClienteEmpleoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'rsClienteEmpleo.label', default: 'RsClienteEmpleo'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (rsClienteEmpleoInstance.version > version) {
                rsClienteEmpleoInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'rsClienteEmpleo.label', default: 'RsClienteEmpleo')] as Object[],
                          "Another user has updated this RsClienteEmpleo while you were editing")
                render(view: "edit", model: [rsClienteEmpleoInstance: rsClienteEmpleoInstance])
                return
            }
        }

        rsClienteEmpleoInstance.properties = params

        if (!rsClienteEmpleoInstance.save(flush: true)) {
            render(view: "edit", model: [rsClienteEmpleoInstance: rsClienteEmpleoInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'rsClienteEmpleo.label', default: 'RsClienteEmpleo'), rsClienteEmpleoInstance.id])
        redirect(action: "show", id: rsClienteEmpleoInstance.id)
    }

    def delete(Long id) {
        def rsClienteEmpleoInstance = RsClienteEmpleo.get(id)
        if (!rsClienteEmpleoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'rsClienteEmpleo.label', default: 'RsClienteEmpleo'), id])
            redirect(action: "list")
            return
        }

        try {
            rsClienteEmpleoInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'rsClienteEmpleo.label', default: 'RsClienteEmpleo'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'rsClienteEmpleo.label', default: 'RsClienteEmpleo'), id])
            redirect(action: "show", id: id)
        }
    }
}
