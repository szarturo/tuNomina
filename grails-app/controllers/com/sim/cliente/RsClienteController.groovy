package com.sim.cliente

import org.springframework.dao.DataIntegrityViolationException

class RsClienteController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [rsClienteInstanceList: RsCliente.list(params), rsClienteInstanceTotal: RsCliente.count()]
    }

    def create() {
        [rsClienteInstance: new RsCliente(params)]
    }

    def save() {
        def rsClienteInstance = new RsCliente(params)
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
}
