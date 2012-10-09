package abltutorial

import org.springframework.dao.DataIntegrityViolationException
import abltutorial.Transactional


class ProductController {

	static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

	def index() {
		redirect(action: "list", params: params)
	}

	def list() {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		[productInstanceList: Product.list(params), productInstanceTotal: Product.count()]
	}

	def create() {
		[productInstance: new Product(params)]
	}


	def show() {
		def productInstance = Product.get(params.id)
		if (!productInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'product.label', default: 'Product'), params.id])
			redirect(action: "list")
			return
		}

		[productInstance: productInstance]
	}

	def edit() {
		def productInstance = Product.get(params.id)
		if (!productInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'product.label', default: 'Product'), params.id])
			redirect(action: "list")
			return
		}

		[productInstance: productInstance]
	}


	//////////// following code is modified for abl transaction integration

	def save() {
		Transactional.save(Product, this)
	}

	def update() {
		Transactional.update(Product, this)
	}

	def delete() {
		Transactional.delete(Product, this)
	}

}
