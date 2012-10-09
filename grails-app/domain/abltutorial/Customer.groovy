package abltutorial

class Customer implements Comparable {

	static constraints = {
		name(notEqual:"Bad Name")
	}
	
	SortedSet purchaseOrders
	SortedSet customerAudits
	static hasMany = [purchaseOrders : PurchaseOrder, customerAudits: CustomerAudit]
	
	String name
	BigDecimal balance
	BigDecimal creditLimit
	
	int compareTo(obj) {
		return id.compareTo(obj.id)
	}
}
