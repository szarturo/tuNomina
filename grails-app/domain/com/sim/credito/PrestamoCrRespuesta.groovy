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
	String fechaRecepcion
	String fechaRespuesta
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
		 consecutivo()
		 folio()	
		 referencia()
		 claveDistribuidor()
		 claveTienda()
		 nombreSucursal()
		 fechaRecepcion()
		 fechaRespuesta()
		 montoSolicitado()
		 montoAutorizado()
		 estatus()	
		 motivo()	
		 nombre()	
		 vendedor()
		 promocion()	
		 observaciones()
		 numeroCliente()	
		 prestamo()    	
    }

	String toString() {
		"${prestamo} - ${referencia}"
	}    
}
