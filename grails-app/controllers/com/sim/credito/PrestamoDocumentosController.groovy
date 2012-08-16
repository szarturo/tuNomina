package com.sim.credito

class PrestamoDocumentosController {

     def listaDocumentos() {
        render(view: "documentos", model: [clavePrestamo: params.id])
    }
}
