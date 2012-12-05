package com.sim.pfin

import com.sim.credito.Prestamo
import com.sim.credito.PrestamoPago
import com.sim.usuario.Usuario

class PfinMovimiento {

	PfinCuenta     cuenta
	PfinDivisa     divisa
	Date           fechaOperacion
	Date           fechaLiquidacion
	BigDecimal     importeNeto
	Integer        referencia
	Prestamo       prestamo
	String         nota
	Date           fechaRegistro
	String         logIpDireccion
	String         logUsuario
	String         logHost
	Usuario        usuario
	Date           fechaAplicacion
	Integer        numeroPagoAmortizacion
	Integer        cancelaTransaccion
	PfinCatOperacion       operacion
	SituacionPremovimiento situacionMovimiento
	PfinPreMovimiento      pfinPreMovimiento


	static hasMany = [pfinMovimientoDet : PfinMovimientoDet]
	static belongsTo = [prestamoPago : PrestamoPago]

	static mapping = {
		pfinMovimientoDet joinTable: [name:'REL_MOVIMIENTOS', key:'ID_MOVIMIENTO', column:'ID_MOVIMIENTO_DET']
	}

	static constraints = {
		cuenta()
		divisa()
		fechaOperacion()
		fechaLiquidacion()
		importeNeto()
		referencia(nullable:true)
		prestamo()
		nota()
		situacionMovimiento()
		fechaRegistro()
		logIpDireccion(nullable:true)
		logUsuario(nullable:true)
		logHost(nullable:true)
		usuario()
		fechaAplicacion()
		numeroPagoAmortizacion()
		operacion()
		cancelaTransaccion(nullable:true)
		pfinPreMovimiento()
		prestamoPago nullable:true
	}

	String toString() {
		"${nota} - ${importeNeto}"
	}
}
