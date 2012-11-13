package com.sim.calendario

import org.springframework.dao.DataIntegrityViolationException

class SimCatEventoController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [eventoInstanceList: SimCatEvento.list(params), eventoInstanceTotal: SimCatEvento.count()]
    }

    def create() {
        [eventoInstance: new SimCatEvento(params)]
    }

    def save() {
        def eventoInstance = new SimCatEvento(params)
        if (!eventoInstance.save(flush: true)) {
            render(view: "create", model: [eventoInstance: eventoInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'evento.label', default: 'Evento'), eventoInstance.id])
        redirect(action: "show", id: eventoInstance.id)
    }

    def show(Long id) {
        def eventoInstance = SimCatEvento.get(id)
        if (!eventoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'evento.label', default: 'Evento'), id])
            redirect(action: "list")
            return
        }

        [eventoInstance: eventoInstance]
    }

    def edit(Long id) {
        def eventoInstance = SimCatEvento.get(id)
        if (!eventoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'evento.label', default: 'Evento'), id])
            redirect(action: "list")
            return
        }

        [eventoInstance: eventoInstance]
    }

    def update(Long id, Long version) {
        def eventoInstance = SimCatEvento.get(id)
        if (!eventoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'evento.label', default: 'Evento'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (eventoInstance.version > version) {
                eventoInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'evento.label', default: 'Evento')] as Object[],
                          "Another user has updated this Evento while you were editing")
                render(view: "edit", model: [eventoInstance: eventoInstance])
                return
            }
        }

        eventoInstance.properties = params

        if (!eventoInstance.save(flush: true)) {
            render(view: "edit", model: [eventoInstance: eventoInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'evento.label', default: 'Evento'), eventoInstance.id])
        redirect(action: "show", id: eventoInstance.id)
    }

    def delete(Long id) {
        def eventoInstance = SimCatEvento.get(id)
        if (!eventoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'evento.label', default: 'Evento'), id])
            redirect(action: "list")
            return
        }

        try {
            eventoInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'evento.label', default: 'Evento'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'evento.label', default: 'Evento'), id])
            redirect(action: "show", id: id)
        }
    }
}
