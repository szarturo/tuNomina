package com.sim.cliente

import grails.converters.JSON

class RsClienteController {

    def rsClienteService

    def scaffold = true

    def defaultAction = 'list'

    def busquedaCliente = {
        log.info('Búsqueda Cliente')
    }

    def listJSON = {
        render rsClienteService.findClientes(params) as JSON
    }

}
