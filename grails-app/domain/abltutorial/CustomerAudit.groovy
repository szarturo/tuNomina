package abltutorial

class CustomerAudit implements Comparable {

    static constraints = {
		createdOn nullable: true
    }
		
	String name
	BigDecimal balance
	BigDecimal creditLimit
	Date createdOn

	static belongsTo = [customer:Customer]
	
	int compareTo(obj) {
		if (obj == null || obj.id == null || id == null)
			return -1
		else
			return id?.compareTo(obj?.id)
	}
	
	}
