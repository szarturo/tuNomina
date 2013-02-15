package com.rs.gral

import grails.converters.*

class RsGralDelegacionMunicipioController {

    static scaffold = true
	
	def ajaxGetAsentamiento = {
		def delegacionAsentamiento = RsGralDelegacionMunicipio.get(params.id)
		render delegacionAsentamiento?.asentamiento as JSON
	}
	
}
