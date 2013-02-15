package com.rs.gral

import grails.converters.*

class RsGralEstadoController {
	static scaffold = true

	def ajaxGetDelegacionMunicipio = {
		def estado = RsGralEstado.get(params.id)
		render estado?.delegacionMunicipio as JSON
	}

}
