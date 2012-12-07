package com.sim.credito

import org.springframework.dao.DataIntegrityViolationException
import com.sim.servicios.credito.PagoServiceException
import com.sim.servicios.credito.PagoServiceAplicaPagoException
import com.sim.pfin.ProcesadorFinancieroServiceException

class PrestamoPagoController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
	
	def pagoService

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [prestamoPagoInstanceList: PrestamoPago.list(params), prestamoPagoInstanceTotal: PrestamoPago.count()]
    }

    def create() {
        [prestamoPagoInstance: new PrestamoPago(params)]
    }

    def save() {
        def prestamoPagoInstance = new PrestamoPago(params)
        if (!prestamoPagoInstance.save(flush: true)) {
            render(view: "create", model: [prestamoPagoInstance: prestamoPagoInstance])
            return
        }
		
        /*
        //EJEMPLO DE COMO GENERAR EL PREMOVIMIENTO Y EL MOVIMIENTO
		try{
			pagoService.aplicaPagoIndividual(prestamoPagoInstance)
		//VERIFICAR SI SE GENERO ALGUN ERROR
		}catch(PagoServiceException errorPago){
			//EL ERROR SE PROPAGO DESDE EL SERVICIO PagoService
			prestamoPagoInstance.errors.reject("ErrorPagoCredito",errorPago.mensaje)
			log.error "Failed:", errorPago
			render(view: "create", model: [prestamoPagoInstance: prestamoPagoInstance])
			return
		}catch(ProcesadorFinancieroServiceException errorProcesadorFinanciero){
			//EL ERROR SE PROPAGO DESDE EL SERVICIO ProcesadorFinancieroService
			prestamoPagoInstance.errors.reject("ErrorProcesadorFinanciero",errorProcesadorFinanciero.mensaje)
			log.error "Failed:", errorProcesadorFinanciero
			render(view: "create", model: [prestamoPagoInstance: prestamoPagoInstance])
			return
		}*/

        flash.message = message(code: 'default.created.message', args: [message(code: 'prestamoPago.label', default: 'PrestamoPago'), prestamoPagoInstance.id])
        redirect(action: "show", id: prestamoPagoInstance.id)
    }

    def show(Long id) {
        def prestamoPagoInstance = PrestamoPago.get(id)
        if (!prestamoPagoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'prestamoPago.label', default: 'PrestamoPago'), id])
            redirect(action: "list")
            return
        }

        [prestamoPagoInstance: prestamoPagoInstance]
    }

    def edit(Long id) {
        def prestamoPagoInstance = PrestamoPago.get(id)
        if (!prestamoPagoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'prestamoPago.label', default: 'PrestamoPago'), id])
            redirect(action: "list")
            return
        }

        [prestamoPagoInstance: prestamoPagoInstance]
    }

    def update(Long id, Long version) {
        def prestamoPagoInstance = PrestamoPago.get(id)
        if (!prestamoPagoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'prestamoPago.label', default: 'PrestamoPago'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (prestamoPagoInstance.version > version) {
                prestamoPagoInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'prestamoPago.label', default: 'PrestamoPago')] as Object[],
                          "Another user has updated this PrestamoPago while you were editing")
                render(view: "edit", model: [prestamoPagoInstance: prestamoPagoInstance])
                return
            }
        }

        prestamoPagoInstance.properties = params

        if (!prestamoPagoInstance.save(flush: true)) {
            render(view: "edit", model: [prestamoPagoInstance: prestamoPagoInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'prestamoPago.label', default: 'PrestamoPago'), prestamoPagoInstance.id])
        redirect(action: "show", id: prestamoPagoInstance.id)
    }

    def delete(Long id) {
        def prestamoPagoInstance = PrestamoPago.get(id)
        if (!prestamoPagoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'prestamoPago.label', default: 'PrestamoPago'), id])
            redirect(action: "list")
            return
        }

        try {
            prestamoPagoInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'prestamoPago.label', default: 'PrestamoPago'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'prestamoPago.label', default: 'PrestamoPago'), id])
            redirect(action: "show", id: id)
        }
    }

    def guardarPago(){
        def prestamoPagoInstance = new PrestamoPago(params)

        if (!prestamoPagoInstance.validate()) {
            render(view: "create", model: [prestamoPagoInstance: prestamoPagoInstance])
            return
        }

        try{
            pagoService.guardarPago(prestamoPagoInstance)
        //VERIFICAR SI SE GENERO ALGUN ERROR
        }catch(PagoServiceException errorPago){
            //EL ERROR SE PROPAGO DESDE EL SERVICIO PagoService
            prestamoPagoInstance.errors.reject("ErrorPagoCredito",errorPago.mensaje)
            log.error "Failed:", errorPago
            render(view: "create", model: [prestamoPagoInstance: prestamoPagoInstance])
            return
        }catch(ProcesadorFinancieroServiceException errorProcesadorFinanciero){
            //EL ERROR SE PROPAGO DESDE EL SERVICIO ProcesadorFinancieroService
            prestamoPagoInstance.errors.reject("ErrorProcesadorFinanciero",errorProcesadorFinanciero.mensaje)
            log.error "Failed:", errorProcesadorFinanciero
            render(view: "create", model: [prestamoPagoInstance: prestamoPagoInstance])
            return
        }catch(Exception errorGuardaPago){
            prestamoPagoInstance.errors.reject("ErrorGuardaPago","No se guardo el Pago. Contacte al Administrador")
            log.error "Failed:", errorGuardaPago
            render(view: "create", model: [prestamoPagoInstance: prestamoPagoInstance])
            return
        }
        redirect(action: "list")
    }

    def aplicaPago(Long id){

        def prestamoPagoInstance
        Boolean existePagoInstance = false
        //SI EL ID DEL PRESTAMO PAGO NO EXISTE VALIDA QUE EL PRESTAMO PAGO NO
        //VAYAN A EXISTIR ERRORES CUANDO MAS ADELANTE SE GUARDE
        if (!id){
            log.info "*****EL PRESTAMO PAGO NO EXISTE"
            prestamoPagoInstance = new PrestamoPago(params)

            if (!prestamoPagoInstance.validate()) {
                render(view: "create", model: [prestamoPagoInstance: prestamoPagoInstance])
                return
            }
        }else{
            //EN CASO DE EXISTIR RECUPERA EL REGISTRO
            log.info "*****EL PRESTAMO PAGO EXISTE"
            prestamoPagoInstance = PrestamoPago.get(id)    
            existePagoInstance = true
        }

        try{
            pagoService.aplicarPago(prestamoPagoInstance,existePagoInstance)
        //VERIFICAR SI SE GENERO ALGUN ERROR
        }catch(PagoServiceAplicaPagoException errorPagoAplicaPago){
            //EL ERROR SE PROPAGO DESDE EL SERVICIO PagoService
            prestamoPagoInstance.errors.reject("ErrorPagoCredito",errorPagoAplicaPago.mensaje)
            log.error "Failed:", errorPagoAplicaPago
            flash.message = message(code: errorPagoAplicaPago.mensaje, args: [])
            redirect(action: "list")
            return
        }catch(PagoServiceException errorPago){
            //EL ERROR SE PROPAGO DESDE EL SERVICIO PagoService
            prestamoPagoInstance.errors.reject("ErrorPagoCredito",errorPago.mensaje)
            log.error "Failed:", errorPago
            flash.message = message(code: errorPago.mensaje, args: [])
            redirect(action: "show", id: prestamoPagoInstance.id)            
            return
        }catch(ProcesadorFinancieroServiceException errorProcesadorFinanciero){
            //EL ERROR SE PROPAGO DESDE EL SERVICIO ProcesadorFinancieroService
            prestamoPagoInstance.errors.reject("ErrorProcesadorFinanciero",errorProcesadorFinanciero.mensaje)
            log.error "Failed:", errorProcesadorFinanciero
            flash.message = message(code: errorProcesadorFinanciero.mensaje, args: [])
            redirect(action: "show", id: prestamoPagoInstance.id)            
            return            
        }catch(Exception errorAplicaPago){
            prestamoPagoInstance.errors.reject("errorAplicaPago","No se aplico el Pago. Contacte al Administrador")
            log.error "Failed:", errorAplicaPago
            flash.message = message(code: "No se cancelo el Pago Guardado. Contacte al Administrador", args: [])
            redirect(action: "show", id: prestamoPagoInstance.id)            
            return            
        }
        flash.message = message(code: "El pago ha sido aplicado", args: [])
        redirect(action: "show", id: prestamoPagoInstance.id)            
    }

    def cancelaPagoGuardado(Long id) {

        //SE OBTIENE EL PRESTAMO PAGO        
        def prestamoPagoInstance = PrestamoPago.get(id)

        try{
            pagoService.cancelaPagoGuardado(prestamoPagoInstance)
        //VERIFICAR SI SE GENERO ALGUN ERROR
        }catch(PagoServiceException errorPago){
            //EL ERROR SE PROPAGO DESDE EL SERVICIO PagoService
            prestamoPagoInstance.errors.reject("ErrorPagoCredito",errorPago.mensaje)
            log.error "Failed:", errorPago
            flash.message = message(code: errorPago.mensaje, args: [])
            redirect(action: "show", id: prestamoPagoInstance.id)            
            return
        }catch(ProcesadorFinancieroServiceException errorProcesadorFinanciero){
            //EL ERROR SE PROPAGO DESDE EL SERVICIO ProcesadorFinancieroService
            prestamoPagoInstance.errors.reject("ErrorProcesadorFinanciero",errorProcesadorFinanciero.mensaje)
            log.error "Failed:", errorProcesadorFinanciero
            flash.message = message(code: errorProcesadorFinanciero.mensaje, args: [])
            redirect(action: "show", id: prestamoPagoInstance.id)            
            return            
        }catch(Exception errorGuardaPago){
            prestamoPagoInstance.errors.reject("ErrorGuardaPago","No se cancelo el Pago Guardado. Contacte al Administrador")
            log.error "Failed:", errorGuardaPago
            flash.message = message(code: "No se cancelo el Pago Guardado. Contacte al Administrador", args: [])
            redirect(action: "show", id: prestamoPagoInstance.id)            
            return            
        }
        flash.message = message(code: "El pago guardado ha sido cancelado", args: [])
        redirect(action: "show", id: prestamoPagoInstance.id)            
    }

    def cancelaPagoAplicado(Long id, Long version){
        log.info("Cancela Pago Aplicado")
        
        def prestamoPagoInstance = PrestamoPago.get(id)

        try{
            pagoService.cancelaPagoAplicado(prestamoPagoInstance)
        //VERIFICAR SI SE GENERO ALGUN ERROR
        }catch(PagoServiceException errorPago){
            //EL ERROR SE PROPAGO DESDE EL SERVICIO PagoService
            prestamoPagoInstance.errors.reject("ErrorPagoCredito",errorPago.mensaje)
            log.error "Failed:", errorPago
        }catch(ProcesadorFinancieroServiceException errorProcesadorFinanciero){
            //EL ERROR SE PROPAGO DESDE EL SERVICIO ProcesadorFinancieroService
            prestamoPagoInstance.errors.reject("ErrorProcesadorFinanciero",errorProcesadorFinanciero.mensaje)
            log.error "Failed:", errorProcesadorFinanciero
        }catch(Exception errorGuardaPago){
            prestamoPagoInstance.errors.reject("ErrorGuardaPago","No se cancelo el Pago Aplicado. Contacte al Administrador")
            log.error "Failed:", errorGuardaPago
        }
        redirect(action: "list")
    }

}
