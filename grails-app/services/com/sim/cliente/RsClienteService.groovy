package com.sim.cliente

import com.rs.gral.RsPersona

class RsClienteService {

    static transactional = true

    def findClientes(params) {

        def sortIndex = params.sidx ?: 'apellidoPaterno'
        def sortOrder  = params.sord ?: 'asc'
        def maxRows = Integer.valueOf(params.rows)
        def currentPage = Integer.valueOf(params.page) ?: 1
        def rowOffset = currentPage == 1 ? 0 : (currentPage - 1) * maxRows

        def clientes = RsPersona.createCriteria().list(max: maxRows, offset: rowOffset) {

            if (params.primerNombre)
                ilike('primerNombre', "%${params.primerNombre}%")

            if (params.apellidoPaterno)
                ilike('apellidoPaterno', "%${params.apellidoPaterno}%")

            if (params.apellidoMaterno)
                ilike('apellidoMaterno', "%${params.apellidoMaterno}%")

            if (params.rfc) {
                ilike('rfc', "%${params.rfc}%")
            }

            order(sortIndex, sortOrder)
        }

        def totalRows = clientes.totalCount
        def numberOfPages = Math.ceil(totalRows / maxRows)

        def results = clientes?.collect {
            [
                    cell: [
                            it.primerNombre,
                            it.apellidoPaterno,
                            it.apellidoMaterno,
                            it.rfc
                    ],
                    id: it.id
            ]
        }

        [rows: results, page: currentPage, records: totalRows, total: numberOfPages]
    }
}