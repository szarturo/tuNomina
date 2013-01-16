package com.sim.listacobro

import com.sim.credito.Prestamo
import com.sim.credito.PrestamoPago
import com.sim.servicios.credito.PagoServiceException
import com.sim.pfin.ProcesadorFinancieroServiceException
import com.sim.servicios.credito.PagoService

class ListaCobroPagoServiceException extends RuntimeException {
	String mensaje
}

class ListaCobroPagoService {

	static transactional = true
	//SERVICIO PARA APLICAR PAGOS
	def pagoService

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

	        try{
	            pagoService.guardarPago(prestamoPagoInstance)
	        //VERIFICAR SI SE GENERO ALGUN ERROR
	        }catch(PagoServiceException errorPago){
	            //EL ERROR SE PROPAGO DESDE EL SERVICIO PagoService
	            throw errorPago
	        }catch(ProcesadorFinancieroServiceException errorProcesadorFinanciero){
	            //EL ERROR SE PROPAGO DESDE EL SERVICIO ProcesadorFinancieroService
	            throw errorProcesadorFinanciero
	        }

    		log.info "Servicio"
			log.info listaCobroDetalleInstance
			log.info prestamoInstance
			log.info prestamoPagoInstance
			log.info pago
			log.info fechaPago

    }
}
