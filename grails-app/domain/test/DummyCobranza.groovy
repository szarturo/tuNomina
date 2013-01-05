package test

import com.sim.tablaAmortizacion.TablaAmortizacionRegistro

class DummyCobranza {

	TablaAmortizacionRegistro detalleRegistro

	Double field1;
	
	Double field2;
	
	String field3;
	
	String field4;
	
	Date field5;
	
	String field6;
	
    static constraints = {
    	detalleRegistro()
		field1 ()
		field2 ()
		field3 nullable:true, blank:true
		field4 nullable:true, blank:true
		field5 ()
		field6 nullable:true, blank:true
    }
}
