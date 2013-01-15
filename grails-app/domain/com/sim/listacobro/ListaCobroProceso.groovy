package com.sim.listacobro

import com.sim.catalogo.SimCatListaCobroEstatus
import com.sim.usuario.Usuario

class ListaCobroProceso {

	Usuario usuario
	Date    fechaAplicacion
	SimCatListaCobroEstatus estatusListaCobro
	String comentarios
	Date fechaMedio
	Date dateCreated
	Date lastUpdated

	static belongsTo = [listaCobro :ListaCobro]

    static constraints = {
    	//DATOS SOLICITADOS AL USUARIO
    	listaCobro()
    	fechaAplicacion()
    	comentarios nullable:true
		//DATOS NO SOLICITADOS AL USUARIO
    	estatusListaCobro()
    	usuario()
    	fechaMedio()
    	dateCreated ()
		lastUpdated nullable:true
    }

    String toString() {
        "${fechaAplicacion} ${estatusListaCobro}"
    }     
}
