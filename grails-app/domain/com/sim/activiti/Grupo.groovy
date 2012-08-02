package com.sim.activiti

class Grupo {
	
	//NO SE PUEDE DAR DE ALTA YA QUE NO APARECE EL ID
	//EN LAS PANTALLAS
	String id
	String nombreGrupo
	String tipo
	

	static mapping = { 
		datasource 'activiti'
		table 'ACT_ID_GROUP'
		version false
        autoTimestamp false
		id column: 'ID_'
		nombreGrupo column: 'NAME_' 
		tipo column: 'TYPE_' }

	static constraints = {
	}
}
