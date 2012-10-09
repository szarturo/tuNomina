package abltutorial

import org.springframework.dao.DataIntegrityViolationException
import abltutorial.Transactional


class LineItemController {

	static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

	def index() {
		redirect(action: "list", params: params)
	}

	def list() {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		[lineItemInstanceList: LineItem.list(params), lineItemInstanceTotal: LineItem.count()]
	}

	def create() {
		[lineItemInstance: new LineItem(params)]
	}

	def show() {
		def lineItemInstance = LineItem.get(params.id)
		if (!lineItemInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'lineItem.label', default: 'LineItem'), params.id])
			redirect(action: "list")
			return
		}

		[lineItemInstance: lineItemInstance]
	}

	def edit() {
		def lineItemInstance = LineItem.get(params.id)
		if (!lineItemInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'lineItem.label', default: 'LineItem'), params.id])
			redirect(action: "list")
			return
		}

		[lineItemInstance: lineItemInstance]
	}


	//////////// following code is modified for abl transaction integration

	def save() {
		Transactional.save(LineItem, this)
	}

	def update() {
		Transactional.update(LineItem, this)
	}

	def delete() {
		Transactional.delete(LineItem, this)
	}

}
