package com.rs.gral

import org.springframework.dao.DataIntegrityViolationException

class RsGralTelefonoController {

	static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

	def index() {
		redirect(action: "list", params: params)
	}

	def list() {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		[rsGralTelefonoInstanceList: RsGralTelefono.list(params), rsGralTelefonoInstanceTotal: RsGralTelefono.count()]
	}
	/*
	def create() {
		[rsGralTelefonoInstance: new RsGralTelefono(params)]
	}*/

	def create = {
		def rsGralTelefonoInstance = new RsGralTelefono()
		rsGralTelefonoInstance.properties = params
		/*
		// VERIFICA SI EL TELEFONO SE ASIGNA A UNA REGIONAL
		if (params.simRegional){
			def simRegionalInstance = new SimRegional()
			simRegionalInstance = SimRegional.get(params.simRegional.id)
			rsGralTelefonoInstance.regional = simRegionalInstance
		}
		// VERIFICA SI EL TELEFONO SE ASIGNA A UNA SUCURSAL
		if (params.simSucursal){
			def simSucursalInstance = new SimSucursal()
			simSucursalInstance = SimSucursal.get(params.simSucursal.id)
			rsGralTelefonoInstance.sucursal = simSucursalInstance
		}
		// VERIFICA SI EL TELEFONO SE ASIGNA A UN NEGOCIO
		if (params.simClienteNegocio){
			def simClienteNegocioInstance = new SimClienteNegocio()
			simClienteNegocioInstance = SimClienteNegocio.get(params.simClienteNegocio.id)
			rsGralTelefonoInstance.negocio = simClienteNegocioInstance
		}*/
		// VERIFICA SI EL TELEFONO SE ASIGNA A UNA PERSONA
		if (params.rsPersona){
			def rsPersonaInstance = new RsPersona()
			rsPersonaInstance = RsPersona.get(params.rsPersona.id)
			rsGralTelefonoInstance.persona = rsPersonaInstance
		}

		return [rsGralTelefonoInstance: rsGralTelefonoInstance]
	}

	def save() {
		def rsGralTelefonoInstance = new RsGralTelefono(params)
		if (!rsGralTelefonoInstance.save(flush: true)) {
			render(view: "create", model: [rsGralTelefonoInstance: rsGralTelefonoInstance])
			return
		}

		flash.message = message(code: 'default.created.message', args: [
			message(code: 'rsGralTelefono.label', default: 'RsGralTelefono'),
			rsGralTelefonoInstance.id
		])
		redirect(action: "show", id: rsGralTelefonoInstance.id)
	}

	def show() {
		def rsGralTelefonoInstance = RsGralTelefono.get(params.id)
		if (!rsGralTelefonoInstance) {
			flash.message = message(code: 'default.not.found.message', args: [
				message(code: 'rsGralTelefono.label', default: 'RsGralTelefono'),
				params.id
			])
			redirect(action: "list")
			return
		}

		[rsGralTelefonoInstance: rsGralTelefonoInstance]
	}

	def edit() {
		def rsGralTelefonoInstance = RsGralTelefono.get(params.id)
		if (!rsGralTelefonoInstance) {
			flash.message = message(code: 'default.not.found.message', args: [
				message(code: 'rsGralTelefono.label', default: 'RsGralTelefono'),
				params.id
			])
			redirect(action: "list")
			return
		}

		[rsGralTelefonoInstance: rsGralTelefonoInstance]
	}

	def update() {
		def rsGralTelefonoInstance = RsGralTelefono.get(params.id)
		if (!rsGralTelefonoInstance) {
			flash.message = message(code: 'default.not.found.message', args: [
				message(code: 'rsGralTelefono.label', default: 'RsGralTelefono'),
				params.id
			])
			redirect(action: "list")
			return
		}

		if (params.version) {
			def version = params.version.toLong()
			if (rsGralTelefonoInstance.version > version) {
				rsGralTelefonoInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
						[
							message(code: 'rsGralTelefono.label', default: 'RsGralTelefono')]
						as Object[],
						"Another user has updated this RsGralTelefono while you were editing")
				render(view: "edit", model: [rsGralTelefonoInstance: rsGralTelefonoInstance])
				return
			}
		}

		rsGralTelefonoInstance.properties = params

		if (!rsGralTelefonoInstance.save(flush: true)) {
			render(view: "edit", model: [rsGralTelefonoInstance: rsGralTelefonoInstance])
			return
		}

		flash.message = message(code: 'default.updated.message', args: [
			message(code: 'rsGralTelefono.label', default: 'RsGralTelefono'),
			rsGralTelefonoInstance.id
		])
		redirect(action: "show", id: rsGralTelefonoInstance.id)
	}

	def delete() {
		def rsGralTelefonoInstance = RsGralTelefono.get(params.id)
		if (!rsGralTelefonoInstance) {
			flash.message = message(code: 'default.not.found.message', args: [
				message(code: 'rsGralTelefono.label', default: 'RsGralTelefono'),
				params.id
			])
			redirect(action: "list")
			return
		}

		try {
			rsGralTelefonoInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [
				message(code: 'rsGralTelefono.label', default: 'RsGralTelefono'),
				params.id
			])
			redirect(action: "list")
		}
		catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [
				message(code: 'rsGralTelefono.label', default: 'RsGralTelefono'),
				params.id
			])
			redirect(action: "show", id: params.id)
		}
	}
}
