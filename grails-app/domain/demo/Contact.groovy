package demo

class Contact {

	String firstName
	String lastName
	String email
	String phone
	String estatus="PENDIENTE"
	
	static hasMany = [addresses: Address]

    static constraints = {
		firstName(blank: false)
		lastName(blank: false)
		email(blank: false, email: true)
		phone(nullable: true)
		estatus()
    }
}

