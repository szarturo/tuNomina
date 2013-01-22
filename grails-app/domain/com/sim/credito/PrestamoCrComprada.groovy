package com.sim.credito

class PrestamoCrComprada {

	//DOMINIO QUE ALMACENA LA INFORMACION DE LOS CREDITOS COMPRADOS

	String numeroSolicitud
	String numeroOperacion
	String claveCia
	String claveSucursal
	String nombre
	String fechaCompra
	String tipoPromocion
	String clasificador
	String primerCredito
	String status
	BigDecimal importeCedido
	BigDecimal ivaCapital
	BigDecimal ivaDiferido
	BigDecimal ivaIntereses
	BigDecimal importeDescuento
	BigDecimal pagoTienda
	BigDecimal reserva
	BigDecimal netoPagado
	BigDecimal importe 
	Integer cesion
	Prestamo prestamo

    static constraints = {
		numeroSolicitud()
		numeroOperacion()
		claveCia()
		claveSucursal()
		nombre()
		fechaCompra()
		tipoPromocion()
		clasificador()
		primerCredito()
		status()
		importeCedido()
		ivaCapital()
		ivaDiferido()
		ivaIntereses()
		importeDescuento()
		pagoTienda()
		reserva()
		netoPagado()
		importe()
		cesion()
		prestamo()
    }

	String toString() {
		"${prestamo} - Importe Cedido: ${importeCedido}"
	}    

}
