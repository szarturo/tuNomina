package com.sim.credito

import com.sim.pfin.PfinMovimiento

class PrestamoPago {

	BigDecimal importePago
	Prestamo   prestamo
	Date       fechaPago

	static hasMany = [pfinMovimiento: PfinMovimiento]

	String toString() {
		"${prestamo} - ${fechaPago} - ${importePago}"
	}
}
