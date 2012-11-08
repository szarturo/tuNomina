package com.sim.pfin.pruebas

import org.springframework.dao.DataIntegrityViolationException

class PfinPagoCreditoController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
	
	def pagoService

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [pfinPagoCreditoInstanceList: PfinPagoCredito.list(params), pfinPagoCreditoInstanceTotal: PfinPagoCredito.count()]
    }

    def create() {
        [pfinPagoCreditoInstance: new PfinPagoCredito(params)]
    }

    def save() {
        def pfinPagoCreditoInstance = new PfinPagoCredito(params)
        if (!pfinPagoCreditoInstance.save(flush: true)) {
            render(view: "create", model: [pfinPagoCreditoInstance: pfinPagoCreditoInstance])
            return
        }
		
		log.info("Respuesta servicio Pago:"+pagoService.miMetodoPago())
		
		pagoService.aplicaPagoIndividual(pfinPagoCreditoInstance)

        flash.message = message(code: 'default.created.message', args: [message(code: 'pfinPagoCredito.label', default: 'PfinPagoCredito'), pfinPagoCreditoInstance.id])
        redirect(action: "show", id: pfinPagoCreditoInstance.id)
    }

    def show(Long id) {
        def pfinPagoCreditoInstance = PfinPagoCredito.get(id)
        if (!pfinPagoCreditoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'pfinPagoCredito.label', default: 'PfinPagoCredito'), id])
            redirect(action: "list")
            return
        }

        [pfinPagoCreditoInstance: pfinPagoCreditoInstance]
    }

    def edit(Long id) {
        def pfinPagoCreditoInstance = PfinPagoCredito.get(id)
        if (!pfinPagoCreditoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'pfinPagoCredito.label', default: 'PfinPagoCredito'), id])
            redirect(action: "list")
            return
        }

        [pfinPagoCreditoInstance: pfinPagoCreditoInstance]
    }

    def update(Long id, Long version) {
        def pfinPagoCreditoInstance = PfinPagoCredito.get(id)
        if (!pfinPagoCreditoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'pfinPagoCredito.label', default: 'PfinPagoCredito'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (pfinPagoCreditoInstance.version > version) {
                pfinPagoCreditoInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'pfinPagoCredito.label', default: 'PfinPagoCredito')] as Object[],
                          "Another user has updated this PfinPagoCredito while you were editing")
                render(view: "edit", model: [pfinPagoCreditoInstance: pfinPagoCreditoInstance])
                return
            }
        }

        pfinPagoCreditoInstance.properties = params

        if (!pfinPagoCreditoInstance.save(flush: true)) {
            render(view: "edit", model: [pfinPagoCreditoInstance: pfinPagoCreditoInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'pfinPagoCredito.label', default: 'PfinPagoCredito'), pfinPagoCreditoInstance.id])
        redirect(action: "show", id: pfinPagoCreditoInstance.id)
    }

    def delete(Long id) {
        def pfinPagoCreditoInstance = PfinPagoCredito.get(id)
        if (!pfinPagoCreditoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'pfinPagoCredito.label', default: 'PfinPagoCredito'), id])
            redirect(action: "list")
            return
        }

        try {
            pfinPagoCreditoInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'pfinPagoCredito.label', default: 'PfinPagoCredito'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'pfinPagoCredito.label', default: 'PfinPagoCredito'), id])
            redirect(action: "show", id: id)
        }
    }
}
