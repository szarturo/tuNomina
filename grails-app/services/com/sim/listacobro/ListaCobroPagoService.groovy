package com.sim.listacobro

import com.sim.catalogo.SimCatListaCobroEstatus
import com.sim.credito.Prestamo
import com.sim.credito.PrestamoPago
import com.sim.servicios.credito.PagoService
import com.sim.servicios.credito.PagoServiceException
import com.sim.pfin.ProcesadorFinancieroServiceException
import com.sim.pfin.PfinMovimiento

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
    	Date   fechaPago,
    	String idListaCobro){

		//SE OBTIENE LA LISTA DE COBRO
		ListaCobro listaCobroInstance = ListaCobro.get(idListaCobro)
        if (!listaCobroInstance) {
			throw new ListaCobroPagoServiceException(mensaje: "No se encontro la lista de Cobro")
        }

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
			fechaPago: fechaPago).save()

		if (!prestamoPagoInstance){
            throw new ListaCobroPagoServiceException(mensaje: "No se puedo crear el objeto PrestamoPago")
            return
    	}

    	PfinMovimiento pfinMovimientoGuardado

        try{
            pfinMovimientoGuardado = pagoService.guardarPago(prestamoPagoInstance)
        //VERIFICAR SI SE GENERO ALGUN ERROR
        }catch(PagoServiceException errorPago){
            //EL ERROR SE PROPAGO DESDE EL SERVICIO PagoService
            throw errorPago
        }catch(ProcesadorFinancieroServiceException errorProcesadorFinanciero){
            //EL ERROR SE PROPAGO DESDE EL SERVICIO ProcesadorFinancieroService
            throw errorProcesadorFinanciero
        }

        if (pfinMovimientoGuardado){
        	listaCobroDetalleInstance.estatus = ListaCobroDetalleEstatus.GUARDADO
        	listaCobroDetalleInstance.pago = prestamoPagoInstance
        	//SE CAMBIA EL ESTATUS A LA LISTA DE COBRO
        	if (listaCobroInstance.estatus.equals(SimCatListaCobroEstatus.findByClaveListaEstatus("GENERADA"))){
        		listaCobroInstance.estatus = SimCatListaCobroEstatus.findByClaveListaEstatus("REGISTRO_PAGOS")
        	}
        }
    }

    def cancelarPagoGuardadoLc(String idListaCobroDetalle){

		//SE OBTIENE EL DETALLE DE LA LISTA DE COBRO
		ListaCobroDetalle listaCobroDetalleInstance = ListaCobroDetalle.get(idListaCobroDetalle)
        if (!listaCobroDetalleInstance) {
			throw new ListaCobroPagoServiceException(mensaje: "No se encontro el detalle de la lista de cobro")
        }

    	Boolean canceloPagoGuardado

        try{
            canceloPagoGuardado = pagoService.cancelaPagoGuardado(
            	listaCobroDetalleInstance.pago)
        //VERIFICAR SI SE GENERO ALGUN ERROR
        }catch(PagoServiceException errorPago){
            //EL ERROR SE PROPAGO DESDE EL SERVICIO PagoService
            throw errorPago
        }catch(ProcesadorFinancieroServiceException errorProcesadorFinanciero){
            //EL ERROR SE PROPAGO DESDE EL SERVICIO ProcesadorFinancieroService
            throw errorProcesadorFinanciero
        }

        if (canceloPagoGuardado){
        	listaCobroDetalleInstance.estatus = ListaCobroDetalleEstatus.INICIO
        	listaCobroDetalleInstance.pago = null
        }        
    }

    def aplicarPago(String idListaCobroDetalle,
        String idPrestamo,
        Double pago,
        Date   fechaPago,
        String idListaCobro){

        //SE OBTIENE LA LISTA DE COBRO
        ListaCobro listaCobroInstance = ListaCobro.get(idListaCobro)
        if (!listaCobroInstance) {
            throw new ListaCobroPagoServiceException(mensaje: "No se encontro la lista de Cobro")
        }

        //SE OBTIENE EL DETALLE DE LA LISTA DE COBRO
        ListaCobroDetalle listaCobroDetalleInstance = ListaCobroDetalle.get(idListaCobroDetalle)
        if (!listaCobroDetalleInstance) {
            throw new ListaCobroPagoServiceException(mensaje: "No se encontro el detalle de la lista de cobro")
        }

        Boolean existePrestamoPago = false

        PrestamoPago prestamoPagoExiste = listaCobroDetalleInstance.pago
        Prestamo prestamoInstance
        PrestamoPago prestamoPagoInstance
        Boolean resultadoAplicarPago = false
        if (prestamoPagoExiste){
            //EXISTE EL OBJETO PRESTAMO PAGO
            existePrestamoPago = true
            log.info("Existe el pago guardado")
            prestamoPagoInstance = prestamoPagoExiste
        }else{
            //NO EXISTE EL OBJETO PRESTAMO PAGO
            log.info("No Existe pago guardado")

            prestamoInstance = Prestamo.get(idPrestamo)
            if (!prestamoInstance) {
                throw new ListaCobroPagoServiceException(mensaje: "No se encontro el Prestamo para guardar el pago")
            }    

            prestamoPagoInstance = new PrestamoPago(
                importePago:  pago,
                prestamo: prestamoInstance,
                fechaPago: fechaPago).save()

            if (!prestamoPagoInstance){
                throw new ListaCobroPagoServiceException(mensaje: "No se puedo crear el objeto PrestamoPago")
                return
            }
        }

        try{
            resultadoAplicarPago = pagoService.aplicarPago(prestamoPagoInstance,existePrestamoPago)
        //VERIFICAR SI SE GENERO ALGUN ERROR
        }catch(PagoServiceException errorPago){
            //EL ERROR SE PROPAGO DESDE EL SERVICIO PagoService
            throw errorPago
        }catch(ProcesadorFinancieroServiceException errorProcesadorFinanciero){
            //EL ERROR SE PROPAGO DESDE EL SERVICIO ProcesadorFinancieroService
            throw errorProcesadorFinanciero
        }

        log.info ("Resultado Aplicar Pago: ${resultadoAplicarPago}")

        if (resultadoAplicarPago){
            listaCobroDetalleInstance.estatus = ListaCobroDetalleEstatus.APLICADO
            listaCobroDetalleInstance.pago = prestamoPagoInstance
            //SE CAMBIA EL ESTATUS A LA LISTA DE COBRO
            listaCobroInstance.estatus = SimCatListaCobroEstatus.findByClaveListaEstatus("APLICADA_PARCIALMENTE")
        }

    }

}
