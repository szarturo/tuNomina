package com.sim.listacobro

import com.sim.credito.Prestamo

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
			ListaCobroDetalle listaCobroDetalleInstance = ListaCobroDetalle.get(idListaCobroDetalle)
	        if (!listaCobroDetalleInstance) {
				throw new ListaCobroPagoServiceException(mensaje: "No se encontro el detalle de la lista de cobro")
	        }

			Prestamo prestamoInstance = Prestamo.get(idPrestamo)
	        if (!prestamoInstance) {
				throw new ListaCobroPagoServiceException(mensaje: "No se encontro el Prestamo para guardar el pago")	        
			}	        

    		log.info "Servicio"
			log.info listaCobroDetalleInstance
			log.info idPrestamo
			log.info pago
			log.info fechaPago

    }
}
