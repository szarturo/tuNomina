package abltutorial

import org.springframework.dao.DataIntegrityViolationException
import abltutorial.Transactional


class CustomerAuditController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [customerAuditInstanceList: CustomerAudit.list(params), customerAuditInstanceTotal: CustomerAudit.count()]
    }

    def create() {
        [customerAuditInstance: new CustomerAudit(params)]
    }

    def show() {
        def customerAuditInstance = CustomerAudit.get(params.id)
        if (!customerAuditInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'customerAudit.label', default: 'CustomerAudit'), params.id])
            redirect(action: "list")
            return
        }

        [customerAuditInstance: customerAuditInstance]
    }

    def edit() {
        def customerAuditInstance = CustomerAudit.get(params.id)
        if (!customerAuditInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'customerAudit.label', default: 'CustomerAudit'), params.id])
            redirect(action: "list")
            return
        }

        [customerAuditInstance: customerAuditInstance]
    }


	//////////// following code is modified for abl transaction integration

	def save() {
		Transactional.save(CustomerAudit, this)
	}

	def update() {
		Transactional.update(CustomerAudit, this)
	}

	def delete() {
		Transactional.delete(CustomerAudit, this)
	}
}
