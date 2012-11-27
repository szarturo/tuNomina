package com.sim.pfin.pruebas

import org.springframework.dao.DataIntegrityViolationException
import com.sim.servicios.credito.PagoServiceException
import com.sim.pfin.ProcesadorFinancieroServiceException

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
		
        /*
        //EJEMPLO DE COMO GENERAR EL PREMOVIMIENTO Y EL MOVIMIENTO
		try{
			pagoService.aplicaPagoIndividual(pfinPagoCreditoInstance)
		//VERIFICAR SI SE GENERO ALGUN ERROR
		}catch(PagoServiceException errorPago){
			//EL ERROR SE PROPAGO DESDE EL SERVICIO PagoService
			pfinPagoCreditoInstance.errors.reject("ErrorPagoCredito",errorPago.mensaje)
			log.error "Failed:", errorPago
			render(view: "create", model: [pfinPagoCreditoInstance: pfinPagoCreditoInstance])
			return
		}catch(ProcesadorFinancieroServiceException errorProcesadorFinanciero){
			//EL ERROR SE PROPAGO DESDE EL SERVICIO ProcesadorFinancieroService
			pfinPagoCreditoInstance.errors.reject("ErrorProcesadorFinanciero",errorProcesadorFinanciero.mensaje)
			log.error "Failed:", errorProcesadorFinanciero
			render(view: "create", model: [pfinPagoCreditoInstance: pfinPagoCreditoInstance])
			return
		}*/

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

    def guardarPago(){
        log.info("Guarda Pago")
        def pfinPagoCreditoInstance = new PfinPagoCredito(params)

        if (!pfinPagoCreditoInstance.validate()) {
            render(view: "create", model: [pfinPagoCreditoInstance: pfinPagoCreditoInstance])
            return
        }

        try{
            pagoService.guardarPago(pfinPagoCreditoInstance)
        //VERIFICAR SI SE GENERO ALGUN ERROR
        }catch(PagoServiceException errorPago){
            //EL ERROR SE PROPAGO DESDE EL SERVICIO PagoService
            pfinPagoCreditoInstance.errors.reject("ErrorPagoCredito",errorPago.mensaje)
            log.error "Failed:", errorPago
            render(view: "create", model: [pfinPagoCreditoInstance: pfinPagoCreditoInstance])
            return
        }catch(ProcesadorFinancieroServiceException errorProcesadorFinanciero){
            //EL ERROR SE PROPAGO DESDE EL SERVICIO ProcesadorFinancieroService
            pfinPagoCreditoInstance.errors.reject("ErrorProcesadorFinanciero",errorProcesadorFinanciero.mensaje)
            log.error "Failed:", errorProcesadorFinanciero
            render(view: "create", model: [pfinPagoCreditoInstance: pfinPagoCreditoInstance])
            return
        }catch(Exception errorGuardaPago){
            pfinPagoCreditoInstance.errors.reject("ErrorGuardaPago","No se guardo el Pago")
            log.error "Failed:", errorGuardaPago
            render(view: "create", model: [pfinPagoCreditoInstance: pfinPagoCreditoInstance])
            return
        }

        redirect(action: "list")

    }

    def aplicaPago(){
        log.info("Aplica Pago")
        redirect(action: "list")
    }

    def cancelaPagoGuardado(){
        log.info("Cancela Pago Guardado")
        redirect(action: "list")
    }
    def cancelaPagoAplicado(){
        log.info("Cancela Pago Aplicado")
        redirect(action: "list")
    }



}
