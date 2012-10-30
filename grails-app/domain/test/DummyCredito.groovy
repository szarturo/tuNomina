package test

class DummyCredito implements Serializable{

	private static final long serialVersionUID = 123423426754654654L;
	
	String nombre; 
	long ingresos;
	long montoPrestamo;
	String idCliente;
	String idCredito;
	
	boolean creditoOk;
	String status;
	
    static constraints = {
		nombre(nullable: false)
		ingresos(nullable: false)
		montoPrestamo(nullable: false)
		status(nullable: false)
    }
}
