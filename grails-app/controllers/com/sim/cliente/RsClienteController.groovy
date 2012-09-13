package com.sim.cliente

import grails.converters.JSON
import com.rs.gral.RsPersona

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

    def subgridJSON = {
        def persona = RsPersona.get(params.id)
        def cliente = persona?.datosCliente


        def results = cliente?.creditos?.collect {
            [
                    cell: [
                            it.clavePrestamo,
                            it.clavePrestamo,
                            it.clavePrestamo
                    ]
            ]
        }

        def jsonData = [rows: results]
        render jsonData as JSON
    }

}