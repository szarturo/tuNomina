package test

class DummyCobranza {

	double field1;
	
	double field2
	
	String field3;
	
	String field4;
	
	Date field5;
	
	String field6;
	
    static constraints = {
		field1 nullable:false, blank:false
		field2 nullable:false, blank:false
		field3 nullable:false, blank:false
		field4 nullable:false, blank:false
		field5( nullable:false, blank:false)
		field6 nullable:false, blank:false
    }
}
