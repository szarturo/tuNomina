package com.sim.entidad

import grails.converters.*

class EntSucursalController {

    static scaffold = true

	def ajaxGetDelegacion = {
		EntSucursal sucursal = EntSucursal.get(params.id)
		render sucursal?.delegaciones as JSON
	}

}
