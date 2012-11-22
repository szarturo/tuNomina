package com.sim.tablaAmortizacion

import org.springframework.dao.DataIntegrityViolationException
import com.sim.credito.Prestamo

class TablaAmortizacionRegistroController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        Prestamo prestamo = Prestamo.get(params.idPrestamo)

        def listaTablaAmortizacion = TablaAmortizacionRegistro.findAllByPrestamo(prestamo)

        listaTablaAmortizacion.sort{ it.numeroPago } 

        [tablaAmortizacionRegistroInstanceList: listaTablaAmortizacion, 
         tablaAmortizacionRegistroInstanceTotal: listaTablaAmortizacion.size() ]
    }    

    def create() {
        [tablaAmortizacionRegistroInstance: new TablaAmortizacionRegistro(params)]
    }

    def save() {
        def tablaAmortizacionRegistroInstance = new TablaAmortizacionRegistro(params)
        if (!tablaAmortizacionRegistroInstance.save(flush: true)) {
            render(view: "create", model: [tablaAmortizacionRegistroInstance: tablaAmortizacionRegistroInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'tablaAmortizacionRegistro.label', default: 'TablaAmortizacionRegistro'), tablaAmortizacionRegistroInstance.id])
        redirect(action: "show", id: tablaAmortizacionRegistroInstance.id)
    }

    def show(Long id) {
        def tablaAmortizacionRegistroInstance = TablaAmortizacionRegistro.get(id)
        if (!tablaAmortizacionRegistroInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tablaAmortizacionRegistro.label', default: 'TablaAmortizacionRegistro'), id])
            redirect(action: "list")
            return
        }

        [tablaAmortizacionRegistroInstance: tablaAmortizacionRegistroInstance]
    }

    def edit(Long id) {
        def tablaAmortizacionRegistroInstance = TablaAmortizacionRegistro.get(id)
        if (!tablaAmortizacionRegistroInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tablaAmortizacionRegistro.label', default: 'TablaAmortizacionRegistro'), id])
            redirect(action: "list")
            return
        }

        [tablaAmortizacionRegistroInstance: tablaAmortizacionRegistroInstance]
    }

    def update(Long id, Long version) {
        def tablaAmortizacionRegistroInstance = TablaAmortizacionRegistro.get(id)
        if (!tablaAmortizacionRegistroInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tablaAmortizacionRegistro.label', default: 'TablaAmortizacionRegistro'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (tablaAmortizacionRegistroInstance.version > version) {
                tablaAmortizacionRegistroInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'tablaAmortizacionRegistro.label', default: 'TablaAmortizacionRegistro')] as Object[],
                          "Another user has updated this TablaAmortizacionRegistro while you were editing")
                render(view: "edit", model: [tablaAmortizacionRegistroInstance: tablaAmortizacionRegistroInstance])
                return
            }
        }

        tablaAmortizacionRegistroInstance.properties = params

        if (!tablaAmortizacionRegistroInstance.save(flush: true)) {
            render(view: "edit", model: [tablaAmortizacionRegistroInstance: tablaAmortizacionRegistroInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'tablaAmortizacionRegistro.label', default: 'TablaAmortizacionRegistro'), tablaAmortizacionRegistroInstance.id])
        redirect(action: "show", id: tablaAmortizacionRegistroInstance.id)
    }

    def delete(Long id) {
        def tablaAmortizacionRegistroInstance = TablaAmortizacionRegistro.get(id)
        if (!tablaAmortizacionRegistroInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tablaAmortizacionRegistro.label', default: 'TablaAmortizacionRegistro'), id])
            redirect(action: "list")
            return
        }

        try {
            tablaAmortizacionRegistroInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'tablaAmortizacionRegistro.label', default: 'TablaAmortizacionRegistro'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'tablaAmortizacionRegistro.label', default: 'TablaAmortizacionRegistro'), id])
            redirect(action: "show", id: id)
        }
    }
}
