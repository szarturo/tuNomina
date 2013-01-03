package com.rs.gral

import org.springframework.dao.DataIntegrityViolationException
import com.sim.entidad.EntSucursal

class RsGralDomicilioController {

	static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

	def index() {
		redirect(action: "list", params: params)
	}

	def list() {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		[rsGralDomicilioInstanceList: RsGralDomicilio.list(params), rsGralDomicilioInstanceTotal: RsGralDomicilio.count()]
	}

	/*
	 def create() {
	 [rsGralDomicilioInstance: new RsGralDomicilio(params)]
	 }
	 */
	def create = {
		def rsGralDomicilioInstance = new RsGralDomicilio()
		rsGralDomicilioInstance.properties = params
		/*
		 // VERIFICA SI EL DOMICILIO SE ASIGNA A UNA REGIONAL
		 if (params.simRegional){
		 def simRegionalInstance = new SimRegional()
		 simRegionalInstance = SimRegional.get(params.simRegional.id)
		 rsGralDomicilioInstance.regional = simRegionalInstance
		 }
		 // VERIFICA SI EL DOMICILIO SE ASIGNA A UNA SUCURSAL
		 if (params.simSucursal){
		 def simSucursalInstance = new SimSucursal()
		 simSucursalInstance = SimSucursal.get(params.simSucursal.id)
		 rsGralDomicilioInstance.sucursal = simSucursalInstance
		 }
		 // VERIFICA SI EL DOMICILIO SE ASIGNA A UN NEGOCIO
		 if (params.simClienteNegocio){
		 def simClienteNegocioInstance = new SimClienteNegocio()
		 simClienteNegocioInstance = SimClienteNegocio.get(params.simClienteNegocio.id)
		 rsGralDomicilioInstance.negocio = simClienteNegocioInstance
		 }
		 */		
		// VERIFICA SI EL DOMICILIO SE ASIGNA A UNA PERSONA
		if (params.rsPersona){
			def rsPersonaInstance = new RsPersona()
			rsPersonaInstance = RsPersona.get(params.rsPersona.id)
			rsGralDomicilioInstance.persona = rsPersonaInstance
		} else if (params.entSucursal) {
			def entSucursalInstance = new EntSucursal()
			entSucursalInstance = EntSucursal.get(params.entSucursal.id)
			rsGralDomicilioInstance.sucursal = entSucursalInstance
		}

		return [rsGralDomicilioInstance: rsGralDomicilioInstance]
	}

	def save() {
		def rsGralDomicilioInstance = new RsGralDomicilio(params)
		if (!rsGralDomicilioInstance.save(flush: true)) {
			render(view: "create", model: [rsGralDomicilioInstance: rsGralDomicilioInstance])
			return
		}

		flash.message = message(code: 'default.created.message', args: [
			message(code: 'rsGralDomicilio.label', default: 'RsGralDomicilio'),
			rsGralDomicilioInstance.id
		])
		redirect(action: "show", id: rsGralDomicilioInstance.id)
	}

	def show() {
		def rsGralDomicilioInstance = RsGralDomicilio.get(params.id)
		if (!rsGralDomicilioInstance) {
			flash.message = message(code: 'default.not.found.message', args: [
				message(code: 'rsGralDomicilio.label', default: 'RsGralDomicilio'),
				params.id
			])
			redirect(action: "list")
			return
		}

		[rsGralDomicilioInstance: rsGralDomicilioInstance]
	}

	def edit() {
		def rsGralDomicilioInstance = RsGralDomicilio.get(params.id)
		if (!rsGralDomicilioInstance) {
			flash.message = message(code: 'default.not.found.message', args: [
				message(code: 'rsGralDomicilio.label', default: 'RsGralDomicilio'),
				params.id
			])
			redirect(action: "list")
			return
		}

		[rsGralDomicilioInstance: rsGralDomicilioInstance]
	}

	def update() {
		def rsGralDomicilioInstance = RsGralDomicilio.get(params.id)
		if (!rsGralDomicilioInstance) {
			flash.message = message(code: 'default.not.found.message', args: [
				message(code: 'rsGralDomicilio.label', default: 'RsGralDomicilio'),
				params.id
			])
			redirect(action: "list")
			return
		}

		if (params.version) {
			def version = params.version.toLong()
			if (rsGralDomicilioInstance.version > version) {
				rsGralDomicilioInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
						[
							message(code: 'rsGralDomicilio.label', default: 'RsGralDomicilio')]
						as Object[],
						"Another user has updated this RsGralDomicilio while you were editing")
				render(view: "edit", model: [rsGralDomicilioInstance: rsGralDomicilioInstance])
				return
			}
		}

		rsGralDomicilioInstance.properties = params

		if (!rsGralDomicilioInstance.save(flush: true)) {
			render(view: "edit", model: [rsGralDomicilioInstance: rsGralDomicilioInstance])
			return
		}

		flash.message = message(code: 'default.updated.message', args: [
			message(code: 'rsGralDomicilio.label', default: 'RsGralDomicilio'),
			rsGralDomicilioInstance.id
		])
		redirect(action: "show", id: rsGralDomicilioInstance.id)
	}

	def delete() {
		def rsGralDomicilioInstance = RsGralDomicilio.get(params.id)
		if (!rsGralDomicilioInstance) {
			flash.message = message(code: 'default.not.found.message', args: [
				message(code: 'rsGralDomicilio.label', default: 'RsGralDomicilio'),
				params.id
			])
			redirect(action: "list")
			return
		}

		try {
			rsGralDomicilioInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [
				message(code: 'rsGralDomicilio.label', default: 'RsGralDomicilio'),
				params.id
			])
			redirect(action: "list")
		}
		catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [
				message(code: 'rsGralDomicilio.label', default: 'RsGralDomicilio'),
				params.id
			])
			redirect(action: "show", id: params.id)
		}
	}
}
