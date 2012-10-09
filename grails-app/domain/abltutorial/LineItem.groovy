package abltutorial

class LineItem implements Comparable {

    static constraints = {
    }
	
	Integer qtyOrdered
	BigDecimal productPrice
	BigDecimal amount
	
	static belongsTo = [purchaseOrder:PurchaseOrder, product:Product]

		int compareTo(obj) {
		return id.compareTo(obj.id)
	}
}
