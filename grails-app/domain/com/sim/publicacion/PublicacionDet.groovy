package com.sim.publicacion
import com.sim.listacobro.ListaCobroDetalle

class PublicacionDet {

	String usuario		
	String password		
	Integer id	
	String numeroCliente		
	String numeroOperacion		
	String claveCia		
	String claveSucursal
	String tipoPago		
	String concepto		
	BigDecimal importePago	
	BigDecimal importeMoratorios
	String referencia		
	String fechaPagoMes		
	String fechaPagoDia		
	String fechaPagoAnio	
	ListaCobroDetalle listaCobroDetalle	

    static constraints = {
    	usuario size:1..10
		password size:1..10
		id( validator: {
  			return it > 0 &&  (it.toString.length) <= 4
		})
		numeroCliente size:1..15
		numeroOperacion size:1..26
		claveCia size:1..5
		claveSucursal size:1..5
		tipoPago size:1..3
		concepto size:1..3
		importePago(nullable: false, min: 1.0, max: 9999999.99, scale: 2)
		importeMoratorios()
		referencia	size:1..26
		fechaPagoMes size:1..2
		fechaPagoDia size:1..2
		fechaPagoAnio size:1..4
		listaCobroDetalle unique:true
    }
}
