package com.rs.gral

import grails.converters.*

class RsGralCiudadController {

    def scaffold = true
	
	
	def ajaxGetDelegacionMunicipio = {
		def ciudad = RsGralCiudad.get(params.id)
		render ciudad?.delegacionMunicipio as JSON
	}
	
}
