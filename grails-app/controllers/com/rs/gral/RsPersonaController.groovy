package com.rs.gral

import org.springframework.dao.DataIntegrityViolationException

class RsPersonaController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [rsPersonaInstanceList: RsPersona.list(params), rsPersonaInstanceTotal: RsPersona.count()]
    }

    def create() {
        [rsPersonaInstance: new RsPersona(params)]
    }

    def save() {
        def rsPersonaInstance = new RsPersona(params)
        if (!rsPersonaInstance.save(flush: true)) {
            render(view: "create", model: [rsPersonaInstance: rsPersonaInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'rsPersona.label', default: 'RsPersona'), rsPersonaInstance.id])
        redirect(action: "show", id: rsPersonaInstance.id)
    }

    def show(Long id) {
        def rsPersonaInstance = RsPersona.get(id)
        if (!rsPersonaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'rsPersona.label', default: 'RsPersona'), id])
            redirect(action: "list")
            return
        }

        [rsPersonaInstance: rsPersonaInstance]
    }

    def edit(Long id) {
        def rsPersonaInstance = RsPersona.get(id)
        if (!rsPersonaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'rsPersona.label', default: 'RsPersona'), id])
            redirect(action: "list")
            return
        }

        [rsPersonaInstance: rsPersonaInstance]
    }

    def update(Long id, Long version) {
        def rsPersonaInstance = RsPersona.get(id)
        if (!rsPersonaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'rsPersona.label', default: 'RsPersona'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (rsPersonaInstance.version > version) {
                rsPersonaInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'rsPersona.label', default: 'RsPersona')] as Object[],
                          "Another user has updated this RsPersona while you were editing")
                render(view: "edit", model: [rsPersonaInstance: rsPersonaInstance])
                return
            }
        }

        rsPersonaInstance.properties = params

        if (!rsPersonaInstance.save(flush: true)) {
            render(view: "edit", model: [rsPersonaInstance: rsPersonaInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'rsPersona.label', default: 'RsPersona'), rsPersonaInstance.id])
        redirect(action: "show", id: rsPersonaInstance.id)
    }

    def delete(Long id) {
        def rsPersonaInstance = RsPersona.get(id)
        if (!rsPersonaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'rsPersona.label', default: 'RsPersona'), id])
            redirect(action: "list")
            return
        }

        try {
            rsPersonaInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'rsPersona.label', default: 'RsPersona'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'rsPersona.label', default: 'RsPersona'), id])
            redirect(action: "show", id: id)
        }
    }
}
