package com.rs.gral

import grails.converters.*

class RsGralEstadoController {
	def scaffold = true
	
	def ajaxGetCiudades = {
		def estado = RsGralEstado.get(params.id)
		render estado?.ciudad as JSON
	}
	
}
