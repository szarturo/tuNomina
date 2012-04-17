package com.sim.catalogo

class SimCatBanco {

	String  claveBanco
	String  nombreBanco
	
	//static hasMany = [cuentasBancarias :RsCuentaBancaria] 
	//IMPLEMENTAR hasMany = RsCuentaBancaria, VER SimCatTipoAsentamiento

    static constraints = {
		claveBanco(size:3..15, unique: true, nullable: false, blank: false)
		nombreBanco(size:5..50, unique: true, nullable: false, blank: false)
    }

	String toString() {
		"${nombreBanco}"
	}
	
}
