package com.sim.listacobro

import com.sim.credito.Prestamo
import com.sim.credito.PrestamoPago

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

			PrestamoPago prestamoPagoInstance = new PrestamoPago(
				importePago:  pago,
				prestamo: prestamoInstance,
				fechaPago: fechaPago)

			if (!prestamoPagoInstance.save(flush: true)) {
	            throw new ListaCobroPagoServiceException(mensaje: "No se puedo crear el objeto PrestamoPago")
	            return
        	}


     

    		log.info "Servicio"
			log.info listaCobroDetalleInstance
			log.info prestamoInstance
			log.info prestamoPagoInstance
			log.info pago
			log.info fechaPago

    }
}
