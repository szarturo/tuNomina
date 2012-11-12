package com.sim.tablaAmortizacion

import com.sim.credito.Prestamo

class TablaAmortizacion {
	
	Integer     numeroPagoAmortizacion //numeroDePago
	Date		fechaAmortizacion
	BigDecimal	importeSaldoInicial
	BigDecimal  tasaInteres
	BigDecimal  importeInteres //interes
	BigDecimal  importeIvaInteres
	BigDecimal  importeCapitalAmortizacion //amortizacionCapital
	BigDecimal  importePago //pagoTotal
	BigDecimal  importeSaldoFinal //saldoInsoluto
	Boolean     pagoPuntual = false
	BigDecimal  importeInteresPagado
	BigDecimal  importeIvaInteresPagado
	BigDecimal  importeCapitalAmortizacionPagado
	BigDecimal  importePagoPagado
	Boolean     pagado = false
	Date		fechaAmortizacionPagoUltimo
	Date		fechaValorCalculado

	static belongsTo = [prestamo:Prestamo]

	static hasMany =   [tablaAmortizacionAccesorio : TablaAmortizacionAccesorio]

	static constraints = {
		numeroPagoAmortizacion()
		fechaAmortizacion()
		importeSaldoInicial 			nullable:false
		tasaInteres						nullable:false
		importeInteres					nullable:false
		importeIvaInteres				nullable:false
		importeCapitalAmortizacion		nullable:false
		importePago						nullable:false
		importeSaldoFinal				nullable:false
		pagoPuntual()
		importeInteresPagado			 nullable:true
		importeIvaInteresPagado			 nullable:true
		importeCapitalAmortizacionPagado nullable:true
		importePagoPagado				 nullable:true
		pagado()
		fechaAmortizacionPagoUltimo()
		fechaValorCalculado()
	}

	String toString() {
		"Pago no: ${numeroPagoAmortizacion}"
	}
}
