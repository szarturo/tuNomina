package abltutorial

class Product implements Comparable {

    static constraints = {
    }
	String name
	BigDecimal price
	
	static hasMany = [ lineitems : LineItem ]
	
	int compareTo(obj) {
		return id.compareTo(obj.id)
	}
}
