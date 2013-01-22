package com.sim.credito

class PrestamoCrRespuesta {

	//DOMINIO QUE ALMACENA LAS RESPUESTAS DE CREDITO REAL
	//ES DECIR CON ESTATUS DE ACTIVO

	String consecutivo	
	Integer folio	
	String referencia	
	String claveDistribuidor	
	String claveTienda	
	String nombreSucursal	
	Date fechaRecepcion
	Date fechaRespuesta
	BigDecimal montoSolicitado
	BigDecimal montoAutorizado
	String estatus	
	String motivo	
	String nombre	
	String vendedor	
	String promocion	
	String observaciones	
	String numeroCliente	
	Prestamo prestamo

    static constraints = {
		 consecutivo nullable:true
		 folio ()	
		 referencia nullable:true
		 claveDistribuidor nullable:true
		 claveTienda nullable:true
		 nombreSucursal nullable:true
		 fechaRecepcion nullable:true
		 fechaRespuesta nullable:true
		 montoSolicitado nullable:true
		 montoAutorizado nullable:true
		 estatus nullable:true	
		 motivo nullable:true	
		 nombre nullable:true	
		 vendedor nullable:true
		 promocion nullable:true	
		 observaciones nullable:true
		 numeroCliente nullable:true	
		 prestamo()    	
    }

	String toString() {
		"${prestamo} - ${referencia}"
	}    
}
