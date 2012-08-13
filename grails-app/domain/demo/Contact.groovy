package demo

class Contact {

	String firstName
	String lastName
	String email
	String phone
	
	static hasMany = [addresses: Address]

    static constraints = {
		firstName(blank: false)
		lastName(blank: false)
		email(blank: false, email: true)
		phone(nullable: true)
    }
}

