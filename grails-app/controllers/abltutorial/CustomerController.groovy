package abltutorial

import org.springframework.dao.DataIntegrityViolationException
import abltutorial.Transactional


class CustomerController {

	static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

	def index() {
		redirect(action: "list", params: params)
	}

	def list() {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		[customerInstanceList: Customer.list(params), customerInstanceTotal: Customer.count()]
	}

	def create() {
		[customerInstance: new Customer(params)]
	}

	def show() {
		def customerInstance = Customer.get(params.id)
		if (!customerInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'customer.label', default: 'Customer'), params.id])
			redirect(action: "list")
			return
		}

		[customerInstance: customerInstance]
	}

	def edit() {
		def customerInstance = Customer.get(params.id)
		if (!customerInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'customer.label', default: 'Customer'), params.id])
			redirect(action: "list")
			return
		}

		[customerInstance: customerInstance]
	}


	//////////// following code is modified for abl transaction integration

	def save() {
		Transactional.save(Customer, this)
	}

	def update() {
		Transactional.update(Customer, this)
	}

	def delete() {
		Transactional.delete(Customer, this)
	}
}
