package com.sim.listacobro

class ListaCobroPagoService {

	static transactional = true

    def guardarPago(String idListaCobroDetalle,
    	String idPrestamo,
    	Double pago,
    	Date   fechaPago){
    		log.info "Servicio"
			log.info idListaCobroDetalle
			log.info idPrestamo
			log.info pago
			log.info fechaPago

    }
}
