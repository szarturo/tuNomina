package abltutorial

import org.springframework.dao.DataIntegrityViolationException
import abltutorial.Transactional


class PurchaseOrderController {

	static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

	def index() {
		redirect(action: "list", params: params)
	}

	def list() {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		[purchaseOrderInstanceList: PurchaseOrder.list(params), purchaseOrderInstanceTotal: PurchaseOrder.count()]
	}

	def create() {
		[purchaseOrderInstance: new PurchaseOrder(params)]
	}

	def show() {
		def purchaseOrderInstance = PurchaseOrder.get(params.id)
		if (!purchaseOrderInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'purchaseOrder.label', default: 'PurchaseOrder'), params.id])
			redirect(action: "list")
			return
		}

		[purchaseOrderInstance: purchaseOrderInstance]
	}

	def edit() {
		def purchaseOrderInstance = PurchaseOrder.get(params.id)
		if (!purchaseOrderInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'purchaseOrder.label', default: 'PurchaseOrder'), params.id])
			redirect(action: "list")
			return
		}

		[purchaseOrderInstance: purchaseOrderInstance]
	}


	//////////// following code is modified for abl transaction integration

	def save() {
		Transactional.save(PurchaseOrder, this)
	}

	def update() {
		Transactional.update(PurchaseOrder, this)
	}

	def delete() {
		Transactional.delete(PurchaseOrder, this)
	}
}
