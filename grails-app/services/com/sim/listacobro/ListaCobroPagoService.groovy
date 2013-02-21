package com.sim.listacobro

import com.sim.catalogo.SimCatListaCobroEstatus
import com.sim.credito.Prestamo
import com.sim.credito.PrestamoPago
import com.sim.servicios.credito.PagoService
import com.sim.servicios.credito.PagoServiceException
import com.sim.pfin.ProcesadorFinancieroServiceException
import com.sim.pfin.PfinMovimiento
import com.sim.pfin.SituacionPremovimiento
import com.sim.pfin.PfinCatOperacion

class ListaCobroPagoServiceException extends RuntimeException {
	String mensaje
}

class ListaCobroPagoService {

	static transactional = true
    //SERVICIO PARA RECUPERAR EL USUARIO
    def springSecurityService    
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
            
            //VALIDA SI YA EXISTEN MOVIMIENTOS DE DEPOSITOS A LA CUENTA
            def criteriaNumeroMovimientos = PfinMovimiento.createCriteria()
            Integer numeroMovimientos = criteriaNumeroMovimientos.count() {
                and {
                    eq("prestamo",prestamoInstance)
                    eq("situacionMovimiento", SituacionPremovimiento.PROCESADO_REAL)
                    eq("operacion", PfinCatOperacion.findByClaveOperacion('TEDEPEFE'))
                    isNull("cancelaTransaccion")
                }
            }
            if (numeroMovimientos==1){
                //SE REALIZO EL PRIMER PAGO AL PRESTAMO
                log.info ("***Es el primer pago al prestamo***")
                //SE INDICA AL PRESTAMO LA FECHA DE INSTALACION POR PARTE DE LA DEPENDENCIA
                prestamoInstance.fechaInstalacion = fechaPago
                prestamoInstance.save()

                //SE OBTIENEN LAS LISTAS DE COBRO A PARTIR DE LA ACTUAL
                def criteriaListasDeCobro = ListaCobro.createCriteria()
                def listasDeCobroScroll  = criteriaListasDeCobro.scroll() {
                    and {
                        eq("dependencia",listaCobroInstance.dependencia)
                        ge("id", listaCobroInstance.id)
                        order("id", "asc")
                    }
                }                
                //SE OBTIENE EL PRIMER ELEMENTO DEL SCROLL DE LAS LISTAS DE COBRO
                listasDeCobroScroll.next()

                prestamoInstance.tablaAmortizacion.each{
                    log.info it
                    //SE ASIGNAN LAS LISTAS DE COBRO QUE DEBERIAN APLICARSE A PARTIR DEL PRIMER PAGO
                    it.listaCobroPrimerPago = listasDeCobroScroll.get(0)
                    listasDeCobroScroll.next()
                }
            }

        //VERIFICAR SI SE GENERO ALGUN ERROR
        }catch(PagoServiceException errorPago){
            //EL ERROR SE PROPAGO DESDE EL SERVICIO PagoService
            throw errorPago
        }catch(ProcesadorFinancieroServiceException errorProcesadorFinanciero){
            //EL ERROR SE PROPAGO DESDE EL SERVICIO ProcesadorFinancieroService
            throw errorProcesadorFinanciero
        }catch(Exception errorAplicarPago){
            throw new ListaCobroPagoServiceException(mensaje: "Error al aplicar el pago y asignar listas de cobro a partir de la fecha de instalación")
        }

        log.info ("Resultado Aplicar Pago: ${resultadoAplicarPago}")

        if (resultadoAplicarPago){
            listaCobroDetalleInstance.estatus = ListaCobroDetalleEstatus.APLICADO
            listaCobroDetalleInstance.pago = prestamoPagoInstance
            listaCobroDetalleInstance.usuario = springSecurityService.getCurrentUser()
            //SE CAMBIA EL ESTATUS A LA LISTA DE COBRO
            listaCobroInstance.estatus = SimCatListaCobroEstatus.findByClaveListaEstatus("APLICADA_PARCIALMENTE")
        }

    }

    def cancelarPagoAplicadoLc(String idListaCobroDetalle){

        //SE OBTIENE EL DETALLE DE LA LISTA DE COBRO
        ListaCobroDetalle listaCobroDetalleInstance = ListaCobroDetalle.get(idListaCobroDetalle)
        if (!listaCobroDetalleInstance) {
            throw new ListaCobroPagoServiceException(mensaje: "No se encontro el detalle de la lista de cobro")
        }

        Boolean canceloPagoAplicado

        try{
            canceloPagoAplicado = pagoService.cancelaPagoAplicado(
                listaCobroDetalleInstance.pago)
        //VERIFICAR SI SE GENERO ALGUN ERROR
        }catch(PagoServiceException errorPago){
            //EL ERROR SE PROPAGO DESDE EL SERVICIO PagoService
            throw errorPago
        }catch(ProcesadorFinancieroServiceException errorProcesadorFinanciero){
            //EL ERROR SE PROPAGO DESDE EL SERVICIO ProcesadorFinancieroService
            throw errorProcesadorFinanciero
        }

        if (canceloPagoAplicado){
            listaCobroDetalleInstance.estatus = ListaCobroDetalleEstatus.INICIO
            listaCobroDetalleInstance.pago = null
        }        
    }

}
