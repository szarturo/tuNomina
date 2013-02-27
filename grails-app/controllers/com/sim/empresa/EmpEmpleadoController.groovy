package com.sim.empresa


import grails.converters.*

class EmpEmpleadoController {

    static scaffold = true

    def ajaxGetVendedor = {
        def clavePromotor = params.clave
        EmpEmpleado vendedor = EmpEmpleado.findByClavePromotor(clavePromotor)

        String nombreVendedor
        String idVendedor

        if (vendedor){
            nombreVendedor  = "${vendedor?.persona?.apellidoPaterno} ${vendedor?.persona?.apellidoMaterno ?: ""} ${vendedor?.persona?.primerNombre} ${vendedor?.persona?.segundoNombre ?: ""}"
            idVendedor = vendedor?.id            
            
        }else{
            nombreVendedor = "NO DEFINIDO"
            idVendedor = null
        }

		def respuesta = JSON.parse("[]")
		respuesta = JSON.parse("[ ${nombreVendedor}, ${idVendedor} ]")
		render respuesta as JSON
    }
}
