package com.sim.publicacion

import com.sim.listacobro.ListaCobroDetalle

class PublicacionDet {

	String usuario		
	String password		
	Integer idCr	
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

	static belongsTo = [publicacionLote: PublicacionLote]

    static constraints = {
    	usuario size:1..10
		password size:1..10
		idCr()
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

	String toString() {
		"Numero Cliente: ${numeroCliente} - Pago ${importePago}"
	}    

}
