package com.sim.pfin

import com.sim.credito.Prestamo
import com.sim.usuario.Usuario

class PfinPreMovimiento {

	Cuenta     cuenta
	Divisa     divisa
	Date       fechaOperacion
	Date       fechaLiquidacion
	BigDecimal importeNeto
	Integer    referencia
	Prestamo   prestamo
	String     nota
	//IMPLEMENTACION TEMPORAL QUE HAY QUE CAMBIAR AL DEFINIR EL DOMINIO DE LISTAS DE COBRO
	//ListaCobro listaCobro
	Integer    listaCobro
	String     situacionPreMovimiento
	Date       fechaRegistro
	String     logIpDireccion
	String     logUsuario
	String     logHost
	Usuario    usuario
	Date       fechaAplicacion
	Integer    numeroPagoAmortizacion
	CatOperacion  operacion

	//static hasOne = [pfinMovimiento : PfinMovimiento]
	static hasMany = [pfinPreMovimientoDet : PfinPreMovimientoDet]
	
    static constraints = {
		cuenta()
		divisa()
		fechaOperacion()
		fechaLiquidacion()
		importeNeto()
		referencia()
		prestamo()
		nota()
		listaCobro(null:true)
		//pfinMovimiento()
		situacionPreMovimiento()
		fechaRegistro()
		logIpDireccion(null:true)
		logUsuario(null:true)
		logHost(null:true)
		usuario()
		fechaAplicacion()
		numeroPagoAmortizacion()
		operacion()
    }
	
	String toString() {
		"${nota} - ${importeNeto}"
	}
}
