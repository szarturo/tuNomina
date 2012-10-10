package businesslogic

import abltutorial.Customer
import abltutorial.CustomerAudit
import com.autobizlogic.abl.annotations.*
import com.autobizlogic.abl.logic.LogicContext
import com.autobizlogic.abl.businesslogic.InsertIntoFrom
import com.autobizlogic.abl.logic.Verb


public class CustomerLogic {

	@CurrentBean
	Customer customer; // injected by BusLogicEngine
	
	@OldBean
	Customer customerOld;
	
	@Sum("purchaseOrders.amountTotal where paid = false and ready = true")
    public void deriveBalance() { }
	
	@Constraint(value="balance <= creditLimit",
		errorMessage="Customer {name} exceeds credit limit")
	public void checkBalance() { }
	
	@LogicContextObject
	public LogicContext logicContext
	
	/**
	 * Insert row into child EmployeeAudit on salary change.
	 */
	 @Action
	 public void actionAuditCustomer() {
		if (logicContext.getInitialVerb() == Verb.UPDATE && customer.creditLimit != customerOld.creditLimit)  {
			println "CustomerLogic#actionAuditCustomer creating audit record"
				 CustomerAudit audit = InsertIntoFrom.insertChildFrom({new CustomerAudit()}, logicContext)
		}
	 
	 }
	
}
