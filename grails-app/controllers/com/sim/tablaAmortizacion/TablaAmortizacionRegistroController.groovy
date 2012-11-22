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

        [tablaAmortizacionInstanceList: listaTablaAmortizacion, tablaAmortizacionInstanceTotal: listaTablaAmortizacion.size() ]
    }    

    def create() {
        [tablaAmortizacionInstance: new TablaAmortizacionRegistro(params)]
    }

    def save() {
        def tablaAmortizacionInstance = new TablaAmortizacionRegistro(params)
        if (!tablaAmortizacionInstance.save(flush: true)) {
            render(view: "create", model: [tablaAmortizacionInstance: tablaAmortizacionInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'tablaAmortizacion.label', default: 'TablaAmortizacion'), tablaAmortizacionInstance.id])
        redirect(action: "show", id: tablaAmortizacionInstance.id)
    }

    def show(Long id) {
        def tablaAmortizacionInstance = TablaAmortizacionRegistro.get(id)
        if (!tablaAmortizacionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tablaAmortizacion.label', default: 'TablaAmortizacion'), id])
            redirect(action: "list")
            return
        }

        [tablaAmortizacionInstance: tablaAmortizacionInstance]
    }

    def edit(Long id) {
        def tablaAmortizacionInstance = TablaAmortizacionRegistro.get(id)
        if (!tablaAmortizacionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tablaAmortizacion.label', default: 'TablaAmortizacion'), id])
            redirect(action: "list")
            return
        }

        [tablaAmortizacionInstance: tablaAmortizacionInstance]
    }

    def update(Long id, Long version) {
        def tablaAmortizacionInstance = TablaAmortizacionRegistro.get(id)
        if (!tablaAmortizacionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tablaAmortizacion.label', default: 'TablaAmortizacion'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (tablaAmortizacionInstance.version > version) {
                tablaAmortizacionInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'tablaAmortizacion.label', default: 'TablaAmortizacion')] as Object[],
                          "Another user has updated this TablaAmortizacion while you were editing")
                render(view: "edit", model: [tablaAmortizacionInstance: tablaAmortizacionInstance])
                return
            }
        }

        tablaAmortizacionInstance.properties = params

        if (!tablaAmortizacionInstance.save(flush: true)) {
            render(view: "edit", model: [tablaAmortizacionInstance: tablaAmortizacionInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'tablaAmortizacion.label', default: 'TablaAmortizacion'), tablaAmortizacionInstance.id])
        redirect(action: "show", id: tablaAmortizacionInstance.id)
    }

    def delete(Long id) {
        def tablaAmortizacionInstance = TablaAmortizacionRegistro.get(id)
        if (!tablaAmortizacionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tablaAmortizacion.label', default: 'TablaAmortizacion'), id])
            redirect(action: "list")
            return
        }

        try {
            tablaAmortizacionInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'tablaAmortizacion.label', default: 'TablaAmortizacion'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'tablaAmortizacion.label', default: 'TablaAmortizacion'), id])
            redirect(action: "show", id: id)
        }
    }
}
