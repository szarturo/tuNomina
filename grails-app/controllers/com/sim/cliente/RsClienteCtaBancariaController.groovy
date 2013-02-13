package com.sim.cliente

import org.springframework.dao.DataIntegrityViolationException

class RsClienteCtaBancariaController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [rsClienteCtaBancariaInstanceList: RsClienteCtaBancaria.list(params), rsClienteCtaBancariaInstanceTotal: RsClienteCtaBancaria.count()]
    }

    def create() {
        def rsClienteCtaBancariaInstance = new RsClienteCtaBancaria()
        rsClienteCtaBancariaInstance.properties = params

        if (params.rsCliente){
            def rsClienteInstance = new RsCliente()
            rsClienteInstance = RsCliente.get(params.rsCliente.id)
            rsClienteCtaBancariaInstance.cliente = rsClienteInstance
        }

        return [rsClienteCtaBancariaInstance: rsClienteCtaBancariaInstance]
    }

    def save() {
        def rsClienteCtaBancariaInstance = new RsClienteCtaBancaria(params)
        if (!rsClienteCtaBancariaInstance.save(flush: true)) {
            render(view: "create", model: [rsClienteCtaBancariaInstance: rsClienteCtaBancariaInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'rsClienteCtaBancaria.label', default: 'RsClienteCtaBancaria'), rsClienteCtaBancariaInstance.id])
        redirect(action: "show", id: rsClienteCtaBancariaInstance.id)
    }

    def show(Long id) {
        def rsClienteCtaBancariaInstance = RsClienteCtaBancaria.get(id)
        if (!rsClienteCtaBancariaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'rsClienteCtaBancaria.label', default: 'RsClienteCtaBancaria'), id])
            redirect(action: "list")
            return
        }

        [rsClienteCtaBancariaInstance: rsClienteCtaBancariaInstance]
    }

    def edit(Long id) {
        def rsClienteCtaBancariaInstance = RsClienteCtaBancaria.get(id)
        if (!rsClienteCtaBancariaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'rsClienteCtaBancaria.label', default: 'RsClienteCtaBancaria'), id])
            redirect(action: "list")
            return
        }

        [rsClienteCtaBancariaInstance: rsClienteCtaBancariaInstance]
    }

    def update(Long id, Long version) {
        def rsClienteCtaBancariaInstance = RsClienteCtaBancaria.get(id)
        if (!rsClienteCtaBancariaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'rsClienteCtaBancaria.label', default: 'RsClienteCtaBancaria'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (rsClienteCtaBancariaInstance.version > version) {
                rsClienteCtaBancariaInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'rsClienteCtaBancaria.label', default: 'RsClienteCtaBancaria')] as Object[],
                          "Another user has updated this RsClienteCtaBancaria while you were editing")
                render(view: "edit", model: [rsClienteCtaBancariaInstance: rsClienteCtaBancariaInstance])
                return
            }
        }

        rsClienteCtaBancariaInstance.properties = params

        if (!rsClienteCtaBancariaInstance.save(flush: true)) {
            render(view: "edit", model: [rsClienteCtaBancariaInstance: rsClienteCtaBancariaInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'rsClienteCtaBancaria.label', default: 'RsClienteCtaBancaria'), rsClienteCtaBancariaInstance.id])
        redirect(action: "show", id: rsClienteCtaBancariaInstance.id)
    }

    def delete(Long id) {
        def rsClienteCtaBancariaInstance = RsClienteCtaBancaria.get(id)
        if (!rsClienteCtaBancariaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'rsClienteCtaBancaria.label', default: 'RsClienteCtaBancaria'), id])
            redirect(action: "list")
            return
        }

        try {
            rsClienteCtaBancariaInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'rsClienteCtaBancaria.label', default: 'RsClienteCtaBancaria'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'rsClienteCtaBancaria.label', default: 'RsClienteCtaBancaria'), id])
            redirect(action: "show", id: id)
        }
    }
}
