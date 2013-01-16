package com.sim.listacobro

import org.springframework.dao.DataIntegrityViolationException
import java.text.SimpleDateFormat

import com.sim.catalogo.SimCatListaCobroEstatus
import com.sim.servicios.credito.PagoServiceException
import com.sim.servicios.credito.PagoServiceAplicaPagoException
import com.sim.pfin.ProcesadorFinancieroServiceException

class ListaCobroController {

    def listaCobroService
    def listaCobroPagoService
    def filterPaneService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [listaCobroInstanceList: ListaCobro.list(params), listaCobroInstanceTotal: ListaCobro.count()]
    }

    def create() {
        [listaCobroInstance: new ListaCobro(params)]
    }

    def save() {
        def listaCobroInstance = new ListaCobro(params)
        if (!listaCobroInstance.save(flush: true)) {
            render(view: "create", model: [listaCobroInstance: listaCobroInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'listaCobro.label', default: 'ListaCobro'), listaCobroInstance.id])
        redirect(action: "show", id: listaCobroInstance.id)
    }

    def show(Long id) {
        def listaCobroInstance = ListaCobro.get(id)
        if (!listaCobroInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'listaCobro.label', default: 'ListaCobro'), id])
            redirect(action: "list")
            return
        }

        [listaCobroInstance: listaCobroInstance]
    }

    def edit(Long id) {
        def listaCobroInstance = ListaCobro.get(id)
        if (!listaCobroInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'listaCobro.label', default: 'ListaCobro'), id])
            redirect(action: "list")
            return
        }

        [listaCobroInstance: listaCobroInstance]
    }

    def update(Long id, Long version) {
        def listaCobroInstance = ListaCobro.get(id)
        if (!listaCobroInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'listaCobro.label', default: 'ListaCobro'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (listaCobroInstance.version > version) {
                listaCobroInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'listaCobro.label', default: 'ListaCobro')] as Object[],
                          "Another user has updated this ListaCobro while you were editing")
                render(view: "edit", model: [listaCobroInstance: listaCobroInstance])
                return
            }
        }

        listaCobroInstance.properties = params

        if (!listaCobroInstance.save(flush: true)) {
            render(view: "edit", model: [listaCobroInstance: listaCobroInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'listaCobro.label', default: 'ListaCobro'), listaCobroInstance.id])
        redirect(action: "show", id: listaCobroInstance.id)
    }

    def delete(Long id) {
        def listaCobroInstance = ListaCobro.get(id)
        if (!listaCobroInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'listaCobro.label', default: 'ListaCobro'), id])
            redirect(action: "list")
            return
        }

        try {
            listaCobroInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'listaCobro.label', default: 'ListaCobro'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'listaCobro.label', default: 'ListaCobro'), id])
            redirect(action: "show", id: id)
        }
    }

    def filter = {
        if(!params.max) params.max = 10
        render( view:'list', 
            model:[ listaCobroInstanceList: filterPaneService.filter( params, ListaCobro ), 
                listaCobroCount: filterPaneService.count( params, ListaCobro ), 
                filterParams: org.grails.plugin.filterpane.FilterPaneUtils.extractFilterParams(params), 
                params:params ] )
    }

    def generar(Long id){
        ListaCobro listaCobro = ListaCobro.get(id)
        String estatusListaCobro = listaCobro.estatus.claveListaEstatus
        if ( estatusListaCobro in ["REGISTRO_PAGOS",
             "APLICADA_PARCIALMENTE", 
             "APLICADA_COMPLETAMENTE", 
             "PUBLICADA"] == true){
            flash.message = message(code: "No se pueden generar listas de cobro con estatus: ${listaCobro.estatus}", args: [])
        }else{
            listaCobroService.generar(listaCobro) 
            flash.message = message(code: "Se ha generado la lista de cobro", args: [])
        }

        redirect(action: "show", id: id)
    }

    def mostrarDetalles(Long id) {
        def listaCobroInstance = ListaCobro.get(id)
        if (!listaCobroInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'listaCobro.label', default: 'ListaCobro'), id])
            redirect(action: "list")
            return
        }

        //SE APLICAN LOS FILTROS PARA LA BUSQUEDA
        //AUNQUE SE APLICAN A TODAS LAS LISTAS DE COBRO
        def listaCobroDetalleListFiltros = filterPaneService.filter( params, ListaCobroDetalle )

        //SE OBTIENE TODOS LOS DETALLES DE COBRO QUE NO PERTENECEN A LA LISTA DE COBRO
        def listaCobroDetalleNoPertenecen = ListaCobroDetalle.findAllByListaCobroNotEqual(listaCobroInstance)

        //SE ELIMINAN DE LA BUSQUEDA ORIGINAL TODOS LO QUE NO PERTENECEN A LA LISTA DE COBRO
        def listaCobroDetalleInstanceList = listaCobroDetalleListFiltros.minus(listaCobroDetalleNoPertenecen)

        [listaCobroInstance: listaCobroInstance,
         listaCobroDetalleInstanceList: listaCobroDetalleInstanceList,
         filterParams: org.grails.plugin.filterpane.FilterPaneUtils.extractFilterParams(params),
         params: params]
    }

    def guardarPagoLc(){
       
        Integer numeroFila= params.numeroFila.toInteger()
       
        String id = params.get("id${numeroFila}")
        String idListaCobro = params.get("idListaCobro")        
        String idListaCobroDetalle = request.getParameter("idListaCobroDetalle${numeroFila}")
        String idPrestamo = request.getParameter("idPrestamo${numeroFila}")        
        String sPago = request.getParameter("pago${numeroFila}")
        String sfechaPago = request.getParameter("fecha${numeroFila}")

        if (!sPago){
            log.info ("El importe es incorrecto")
            flash.message = message(code: "El importe es incorrecto", args: [])
            redirect(action: "mostrarDetalles", id: idListaCobro)
            return                        
        }else if(!sfechaPago){
            log.info ("Indique la fecha de Aplicacion")
            flash.message = message(code: "Indique la fecha de Aplicacion", args: [])
            redirect(action: "mostrarDetalles", id: idListaCobro)
            return                        
        }else{
            Double pago = request.getParameter("pago${numeroFila}")?.toDouble()            
            Date   fechaPago = getFecha(request.getParameter("fecha${numeroFila}_value"))
            try{
                listaCobroPagoService.guardarPago(idListaCobroDetalle,
                idPrestamo,
                pago,
                fechaPago) 
            //VERIFICAR SI SE GENERO ALGUN ERROR
            }catch(ListaCobroPagoServiceException errorPagoListaCobro){
                //EL ERROR SE PROPAGO DESDE EL SERVICIO ListaCobroPagoService
                log.error "Failed:", errorPagoListaCobro
                flash.message = message(code: errorPagoListaCobro.mensaje, args: [])
                redirect(action: "mostrarDetalles", id: idListaCobro)
                return        
            }catch(PagoServiceException errorPago){
                //EL ERROR SE PROPAGO DESDE EL SERVICIO PagoService
                log.error "Failed:", errorPago
                flash.message = message(code: errorPago.mensaje, args: [])
                redirect(action: "mostrarDetalles", id: idListaCobro)
                return
            }catch(ProcesadorFinancieroServiceException errorProcesadorFinanciero){
                //EL ERROR SE PROPAGO DESDE EL SERVICIO ProcesadorFinancieroService
                log.error "Failed:", errorProcesadorFinanciero
                flash.message = message(code: errorProcesadorFinanciero.mensaje, args: [])
                redirect(action: "mostrarDetalles", id: idListaCobro)
                return                        
            }catch(Exception errorGuardaPago){
                log.error "Failed:", errorGuardaPago
                flash.message = message(code: "No se Guardo el Pago. Contacte al Administrador", args: [])
                redirect(action: "mostrarDetalles", id: idListaCobro)
                return                        
            }                   
        }
        flash.message = message(code: "El pago ha sido Guardado", args: [])
        redirect(action: "mostrarDetalles", id: idListaCobro)
    }

    private Date getFecha(String value){
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        return format.parse(value);
    }    

}
