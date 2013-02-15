package demo

import grails.converters.JSON;

class ContactController {
	static scaffold = true
	def defaultAction = 'list'

	def contactService
	
	def listJSON = {
		println 'Parametros: '+params
		render contactService.findContacts(params) as JSON
	}
	
	// This logic should go in a service, but this is just demo app
	def editJSON = {
		def result
		def message = ""
		def state = "FAIL"
		def id

		// determine our action. Grid will pass a param called "oper"
		switch (params.oper) {
			case 'del':
				
				def items = params.id.split(',')
				items.each{ println "item: $it" }

				items.each{
					result = Contact.get(it)
				
					if (result) {
						result.delete()
						message = "Contact '${result.firstName} ${result.lastName}' Deleted"
						state = "OK"
					}

				}
				break;
			case 'add':
				result = new Contact(params)
				break;
			case 'edit':
				// add or edit instruction sent
				result = Contact.get(params.id)
				result.properties = params
				break;
		}

		// If we aren't deleting the object then we need to validate and save.
		// Capture any validation messages to display on the client side
		if (result && params.oper != "del") {
			if (!result.hasErrors() && result.save(flush: true)) {
				message = "Contact '${result.firstName} ${result.lastName}' " + (params.oper == 'add') ? "Added" : "Updated"
				id = result.id
				state = "OK"
			} else {
				message = "Validation Errors"
			}
		}

		def jsonData = [messsage: message, state: state, id: id]
		render jsonData as JSON
	}

    def subgridJSON = {
      def contact = Contact.get(params.id)
      def results = contact?.addresses?.collect {
          [
             cell: [
                it.line1,
                it.city,
                it.state,
                it.zip
              ]
          ]
      }

      def jsonData = [rows: results]
      render jsonData as JSON
    }
}