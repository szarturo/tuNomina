package demo

class Contact {

	String firstName
	String lastName
	String email
	String phone

    static constraints = {
		firstName(blank: false)
		lastName(blank: false)
		email(blank: false, email: true)
		phone(nullable: true)
    }
}

