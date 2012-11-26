package com.sim.pfin

import com.sim.credito.Prestamo
import com.sim.usuario.Usuario
import com.sim.pfin.SituacionPremovimiento

class PfinPreMovimiento {

	PfinCuenta     cuenta
	PfinDivisa     divisa
	Date       fechaOperacion
	Date       fechaLiquidacion
	BigDecimal importeNeto
	Integer    referencia
	Prestamo   prestamo
	String     nota
	//IMPLEMENTACION TEMPORAL QUE HAY QUE CAMBIAR AL DEFINIR EL DOMINIO DE LISTAS DE COBRO
	//ListaCobro listaCobro
	Integer    listaCobro
	Date       fechaRegistro
	String     logIpDireccion
	String     logUsuario
	String     logHost
	Usuario    usuario
	Date       fechaAplicacion
	Integer    numeroPagoAmortizacion
	PfinCatOperacion       operacion
	SituacionPremovimiento situacionPreMovimiento
	
	static hasMany = [pfinPreMovimientoDet : PfinPreMovimientoDet]
	static hasOne = [pfinMovimiento : PfinMovimiento]
	
    static constraints = {
		cuenta()
		divisa()
		fechaOperacion()
		fechaLiquidacion()
		importeNeto()
		referencia(nullable:true)
		prestamo()
		nota()
		listaCobro(nullable:true)
		//pfinMovimiento()
		situacionPreMovimiento()
		fechaRegistro()
		logIpDireccion(nullable:true)
		logUsuario(nullable:true)
		logHost(nullable:true)
		usuario()
		fechaAplicacion()
		numeroPagoAmortizacion()
		operacion()
		pfinMovimiento(nullable:true)
    }
	
	String toString() {
		"${nota} - ${importeNeto}"
	}
}
