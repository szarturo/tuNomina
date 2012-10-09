package abltutorial

class PurchaseOrder implements Comparable {

    static constraints = {
    }
	
	BigDecimal amountTotal
	Boolean paid
	Boolean ready
	String notes
	
	SortedSet lineitems
	
	static belongsTo = [customer:Customer]
	static hasMany = [ lineItems : LineItem ]
	
	int compareTo(obj) {
		return id.compareTo(obj.id)
	}
}
