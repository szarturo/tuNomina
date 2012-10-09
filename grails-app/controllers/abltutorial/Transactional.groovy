package abltutorial

import com.autobizlogic.abl.engine.ConstraintException

/**
 * Transactional save, update and delete,
 *  to ensure bus logic execution and error handling.
 *
 */
class Transactional {

	/**
	 * get row from page, save, process constraint errors.
	 * 
	 * @param aDomainClass
	 * @param aController
	 */
	static void  save(Object aDomainClass, Object aController) {
		performOperation(aDomainClass, aController, "save")
	}	

	/**
	 * get row from page, opt lock check, update, process constraint errors.
	 * 
	 * @param aDomainClass
	 * @param aController
	 */
	static void  update(Class aDomainClass, Object aController) {
		performOperation(aDomainClass, aController, "update")
	}

	/**
	 * get row from page, opt lock check, delete, process constraint errors.
	 * 
	 * @param aDomainClass
	 * @param aController
	 */
	static void  delete(Object aDomainClass, Object aController) {
		performOperation(aDomainClass, aController, "delete")
	}


	/**
	 * get row from page, [opt lock check], save/update/delete, process constraint errors.
	 * 
	 * @param aDomainInstance
	 * @param aController
	 * @param aVerb save, update or delete
	 */
	private static void  performOperation(Class aDomainClass, Object aController, String aVerb) {

		String domainClassSimpleName = aDomainClass.simpleName // e.g., "PurchaseOrder"
		String domainClassSimpleNameFirstLower = domainClassSimpleName.substring(0,1).toLowerCase() + domainClassSimpleName.substring(1)

		String msgCodeLabel = domainClassSimpleNameFirstLower + ".label"   // e.g., "purchaseOrder.label"
		String domainInstanceLabel = domainClassSimpleNameFirstLower + "Instance"   // e.g., purchaseOrderInstance

		String msgCode = "default.updated.message"
		if (aVerb == "save")
			msgCode = "default.created.message"
		else if (aVerb == "delete")
			msgCode = "default.deleted.message"

		def aDomainInstance
		if (aVerb == "save") {
			aDomainInstance = aDomainClass.newInstance()
		} else {
			aDomainInstance = aDomainClass.get(aController.params.id)
			if (!aDomainInstance) {
				aController.flash.message = message(code: 'default.not.found.message',
						args: [aController.message(code: msgCodeLabel, default: domainClassSimpleName), aController.params.id])
				aController.redirect(action: "list")
				return
			}
		}

		if (aController.params.version) {
			def version = aController.params.version.toLong()
			if (aDomainInstance.version > version) {
				aDomainInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
						[aController.message(code: msgCodeLabel, default: domainClassSimpleName)] as Object[],
						"Another user has updated this ${domainClassSimpleName} while you were editing")
				aController.render(view: "edit", model: [domainInstanceLabel: aDomainInstance])
				return
			}
		}

		aDomainInstance.properties = aController.params
		try {
			if (aVerb == "delete")
				aDomainClass.withTransaction { status ->
					aDomainInstance.delete()  }
			else
				aDomainClass.withTransaction { status ->
					aDomainInstance.save()  }
		} catch (org.springframework.orm.hibernate3.HibernateSystemException ex) {
			// If there was a constraint exception, extract the message and the problem attribute from it
			 if (ex.cause && ex.cause instanceof ConstraintException) {
			 	aDomainInstance.errors.rejectValue(ex.cause.constraintFailures[0].problemAttributes?.getAt(0), "",
			 			"Sorry - constraint violated : " + ex.cause.constraintFailures[0].constraintMessage)
			 } else
				throw ex
		}
		if ( aDomainInstance.hasErrors() ) {
			// Error; transaction is over now, so we need to re-fetch the object to display it
			if (aVerb == "update") {
				def refreshedDomainInstance = aDomainClass.get(aDomainInstance.id)
				refreshedDomainInstance.errors = aDomainInstance.errors  
				def setRefreshedDomainInstanceInView = new LinkedHashMap()
				setRefreshedDomainInstanceInView.put(domainInstanceLabel, refreshedDomainInstance)
				aController.render(view: "edit", model: setRefreshedDomainInstanceInView)
			} else if (aVerb == "save") {
				def setRefreshedDomainInstanceInView = new LinkedHashMap()
				setRefreshedDomainInstanceInView.put(domainInstanceLabel, aDomainInstance)
				aController.render(view: "create", model: setRefreshedDomainInstanceInView)
				System.out.print("save failed")
			}
		} else {  // Success
			aController.flash.message = aController.message(code: msgCode, args: [aController.message(code: msgCodeLabel, default: domainClassSimpleName), aDomainInstance.id])
			aController.redirect(action: "show", id: aDomainInstance.id)
		}
	}
}
