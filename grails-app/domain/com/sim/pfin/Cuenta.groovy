package com.sim.pfin

import com.sim.cliente.RsCliente

class Cuenta {


	String tipoCuenta
	String situacion
	String comentario
	
	static hasMany = [ saldos : Saldo]
	static belongsTo = [cliente:RsCliente]

    static constraints = {
		tipoCuenta(nullable: false, inList:["EJE", "CREDITO"])
		situacion(nullable: false, inList:["ACTIVO", "INACTIVO"]) 
		comentario(nullable: true, blank: true)
		cliente(nullable: false, blank: false)
		saldos(nullable:true)
    }
	
	String toString() {
		"${cliente} - ${tipoCuenta}"
	}
}
