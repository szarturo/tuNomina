package test

class DummyPersona {

	String nombre;
	String apellido;
	String calle;
	String numero;
	String codigoPostal;
	
	
    static constraints = {
		nombre(nullable:false)
		apellido(nullable:false)
		calle(nullable:false)
		numero(nullable:false)
		codigoPostal(nullable:false)
    }
}
