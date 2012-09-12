package com.sim.cliente

import grails.converters.JSON

class RsClienteController {

    def scaffold = true

    def defaultAction = 'list'

    def rsClienteService

    def busquedaCliente = {
        println 'Busqueda CLiente'
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [rsClienteInstanceList: RsCliente.list(params), rsClienteInstanceTotal: RsCliente.count()]
    }

    def listJSON = {
        println 'Parametros listJSON: '+params
        render rsClienteService.findClientes(params) as JSON
    }

}
