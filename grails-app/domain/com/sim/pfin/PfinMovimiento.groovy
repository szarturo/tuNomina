package com.sim.pfin

import com.sim.credito.Prestamo
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
	//IMPLEMENTACION TEMPORAL QUE HAY QUE CAMBIAR AL DEFINIR EL DOMINIO DE LISTAS DE COBRO
	//ListaCobro   listaCobro
	Integer        listaCobro
	String         situacionMovimiento
	Date           fechaRegistro
	String         logIpDireccion
	String         logUsuario
	String         logHost
	Usuario        usuario
	Date           fechaAplicacion
	Integer        numeroPagoAmortizacion
	Integer        cancelaTransaccion
	PfinCatOperacion   operacion


	static hasMany = [pfinMovimientoDet : PfinMovimientoDet]

	static mapping = {
		pfinMovimientoDet joinTable: [name:'REL_MOVIMIENTOS', key:'ID_MOVIMIENTO', column:'ID_MOVIMIENTO_DET']
	}

	static constraints = {
		cuenta()
		divisa()
		fechaOperacion()
		fechaLiquidacion()
		importeNeto()
		referencia()
		prestamo()
		nota()
		listaCobro(nullable:true)
		situacionMovimiento()
		fechaRegistro()
		logIpDireccion(nullable:true)
		logUsuario(nullable:true)
		logHost(nullable:true)
		usuario()
		fechaAplicacion()
		numeroPagoAmortizacion()
		operacion()
		cancelaTransaccion()
	}

	String toString() {
		"${nota} - ${importeNeto}"
	}
}
