package com.rs.gral

import grails.converters.*

class RsGralEstadoController {
	def scaffold = true

	def ajaxGetDelegacionMunicipio = {
		def estado = RsGralEstado.get(params.id)
		render estado?.delegacionMunicipio as JSON
	}

}
