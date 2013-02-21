package com.sim.tablaAmortizacion

import com.sim.credito.Prestamo
import com.sim.listacobro.ListaCobro

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
	//LISTA DE COBRO BASADA A PARTIR DE LA FECHA DE COBRO, AL GENERAR LAS LISTAS DE COBRO ESTE ATRIBUTO NOS AYUDA
	//A TOMARLO COMO REFERENCIA PARA OBTENER LAS AMORTIZACIONES CON NUMERO DE PAGO 1 Y ASIGNARLAS A LOS DETALLES
	//DE LA LISTA DE COBRO
	ListaCobro  listaCobroFechaCobro 
	//LISTA DE COBRO COMO DEBERIA PAGARSE EL CREDITO A PARTIR DE LA FECHA DE INSTALACION
	ListaCobro  listaCobroPrimerPago
	//POR EL MOMENTO NO SE ESPECIFICA CUAL ES LA LISTA DE COBRO EN LA QUE SE DEBERIA PAGAR ESTA AMORTIZACION
	ListaCobro  listaCobroReal 

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
		listaCobroFechaCobro()
		listaCobroPrimerPago        nullable:true
		listaCobroReal              nullable:true
	}

	String toString() {
		"${prestamo} :Amort. ${numeroPago}"
	}
}
