package com.sim.publicacion

import com.sim.usuario.Usuario

class PublicacionLote {

	Date fechaMedio
	Date fechaRegistro
	BigDecimal importeLote
	Usuario usuario

	static hasMany = [publicacionDet: PublicacionDet]

    static constraints = {
    	usuario()
    	importeLote()
    	fechaMedio()
    	fechaRegistro()
    	publicacionDet()
    }
}
