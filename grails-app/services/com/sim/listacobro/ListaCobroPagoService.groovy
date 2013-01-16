package com.sim.listacobro

class ListaCobroPagoServiceException extends RuntimeException {
	String mensaje
}

class ListaCobroPagoService {

	static transactional = true

    def guardarPago(String idListaCobroDetalle,
    	String idPrestamo,
    	Double pago,
    	Date   fechaPago){

			//SE OBTIENE EL DETALLE DE LA LISTA DE COBRO
			ListaCobroDetalle listaCobroDetalleInstance = ListaCobroDetalle.get(999)
	        if (!listaCobroDetalleInstance) {
				throw new ListaCobroPagoServiceException(mensaje: "No se encontro el detalle de la lista de cobro")
	        }

    		log.info "Servicio"
			log.info listaCobroDetalleInstance
			log.info idPrestamo
			log.info pago
			log.info fechaPago

    }
}
