package test

class DummyCobranza {

	double field1;
	
	double field2;
	
	String field3;
	
	String field4;
	
	Date field5;
	
	String field6;
	
    static constraints = {
		field1 ()
		field2 ()
		field3 nullable:false, blank:false
		field4 nullable:false, blank:false
		field5 ()
		field6 nullable:false, blank:false
    }
}
