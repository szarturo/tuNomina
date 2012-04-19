package com.rs.gral

import grails.converters.*

class RsGralAsentamientoController {

	def scaffold = true

	def ajaxGetCodigoPostal = {
		def asentamiento = RsGralAsentamiento.get(params.id)
		render asentamiento as JSON
	}

	def ajaxGetCombos = {
		def codigoPostal = params.cp
		def tamanoCp  = params.cp.size()
		def a = JSON.parse("[]")
		// VALIDA QUE EL CODIGO POSTAL SEA DE LONGITUD 5
		if (tamanoCp == 5){
			def asentamiento = RsGralAsentamiento.findByCodigoPostal(codigoPostal)
			if (asentamiento){
				def idEstado = asentamiento.delegacionMunicipio.ciudad.estado.id
				def idCiudad = asentamiento.delegacionMunicipio.ciudad.id
				def idDelegacionMunicipio = asentamiento.delegacionMunicipio.id
				def idAsentamiento = asentamiento.id
				a = JSON.parse("[ ${idEstado}, ${idCiudad}, ${idDelegacionMunicipio}, ${idAsentamiento}]")
			}
		}
		render a as JSON
	}
}
