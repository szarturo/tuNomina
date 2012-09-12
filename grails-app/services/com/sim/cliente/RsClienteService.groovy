package com.sim.cliente

class RsClienteService {

    static transactional = true

    def findClientes(params) {
        def sortIndex = params.sidx ?: 'numeroDeNomina'
        def sortOrder  = params.sord ?: 'asc'
        def maxRows = Integer.valueOf(params.rows)
        def currentPage = Integer.valueOf(params.page) ?: 1
        def rowOffset = currentPage == 1 ? 0 : (currentPage - 1) * maxRows

        def clientes = RsCliente.createCriteria().list(max: maxRows, offset: rowOffset) {
            /*
            if (params.firstName)
                ilike('firstName', "%${params.firstName}%")

            if (params.lastName)
                ilike('lastName', "%${params.lastName}%")

            if (params.email)
                ilike('email', "%${params.email}%")

            if (params.phone) {
                ilike('phone', "%${params.phone}%")
            }
            */
            order(sortIndex, sortOrder)
        }

        def totalRows = clientes.totalCount
        def numberOfPages = Math.ceil(totalRows / maxRows)

        def results = clientes?.collect {
            [
                    cell: [
                            it.persona.primerNombre,
                            it.persona.apellidoPaterno,
                            it.persona.apellidoMaterno,
                            it.numeroDeNomina
                    ],
                    id: it.id
            ]
        }

        [rows: results, page: currentPage, records: totalRows, total: numberOfPages]
    }
}