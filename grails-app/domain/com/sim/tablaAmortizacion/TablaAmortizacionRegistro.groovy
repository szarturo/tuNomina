package com.sim.tablaAmortizacion

import com.sim.credito.Prestamo

class TablaAmortizacionRegistro {
	
	Integer     numeroPago 
	Date		fecha
	BigDecimal	impSaldoInicial
	BigDecimal  tasaInteres
	BigDecimal  impInteres 
	BigDecimal  impIvaInteres
	BigDecimal  impCapital 
	BigDecimal  impPago 
	BigDecimal  impSaldoFinal 
	Boolean     pagoPuntual = false
	BigDecimal  impInteresPagado
	BigDecimal  impIvaInteresPagado
	BigDecimal  impCapitalPagado
	BigDecimal  impPagoPagado
	Boolean     pagado = false
	Date		fechaPagoUltimo
	Date		fechaValorCalculado

	static belongsTo = [prestamo:Prestamo]
	
	static hasMany =   [tablaAmortizacionAccesorio : TablaAmortizacionAccesorio]
	
	static constraints = {
		numeroPago()
		fecha()
		impSaldoInicial 			nullable:false
		tasaInteres					nullable:false
		impInteres					nullable:false
		impIvaInteres				nullable:false
		impCapital					nullable:false
		impPago						nullable:false
		impSaldoFinal				nullable:false
		pagoPuntual()
		impInteresPagado			nullable:true
		impIvaInteresPagado			nullable:true
		impCapitalPagado 			nullable:true
		impPagoPagado				nullable:true
		pagado()
		fechaPagoUltimo             nullable:true
		fechaValorCalculado 		nullable:true
	}

	String toString() {
		"${prestamo} :Pago ${numeroPago}"
	}
}
