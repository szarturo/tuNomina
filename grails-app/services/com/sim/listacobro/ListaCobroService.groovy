package com.sim.listacobro

import com.sim.entidad.EntDependencia
import com.sim.catalogo.SimCatPeriodicidad
import com.sim.catalogo.SimCatListaCobroEstatus
import com.sim.tablaAmortizacion.TablaAmortizacionRegistro
import com.sim.credito.Prestamo
import com.sim.credito.PrestamoEstatus

class ListaCobroService {

    static transactional = true
    //SERVICIO PARA RECUPERAR EL USUARIO
    def springSecurityService

    //METODO UTILIZADO EN EL BOOTSTRAP
    def crearListasCobro(EntDependencia dependencia,
            Integer anio,
            SimCatPeriodicidad periodicidad){

        Integer numeroPago = 1
        Integer mes = 1
        Integer mesSegundaQuincena = 0
        Date    fechaSegundaQuincena

        (1..periodicidad.cantidadPagos).each{

            ListaCobro listaCobro = new ListaCobro(anio:  anio,
                    periodicidad : periodicidad,
                    numeroPago: numeroPago,
                    parcialidades: false,
                    dependencia: dependencia,
                    estatus: SimCatListaCobroEstatus.findByClaveListaEstatus("NO_GENERADA"),
            )

            //SE OBTIENE SI VA A CORRESPONDER A UNA QUINCENA DE INICIO DEL MES
            Integer resultadoPar = numeroPago.mod(2)
            if (resultadoPar==1){
                //CORRESPONDE A LA PRIMERA QUINCENA DEL MES
                listaCobro.fechaInicio = new Date("${mes}/01/${anio}")
                listaCobro.fechaFin = listaCobro.fechaInicio + 14
                mes++
                //SE GUARDA LA FECHA DE INICIO PARA LA SEGUNDA QUINCENA
                fechaSegundaQuincena = listaCobro.fechaFin + 1
            }else{
                //CORRESPONDE A LA SEGUNDA QUINCENA DEL MES
                listaCobro.fechaInicio = fechaSegundaQuincena
                //SE RECUPERA EL ULTIMO DIA DEL MES
                //Note: Months are from 0 to 11
                Calendar calendar = GregorianCalendar.instance
                calendar.set(anio, mesSegundaQuincena, 20) //20 NO AFECTA CALCULO
                Integer ultimoDiaMes = calendar.getActualMaximum(GregorianCalendar.DAY_OF_MONTH)
                listaCobro.fechaFin = new Date("${mesSegundaQuincena+1}/${ultimoDiaMes}/${anio}")
                mesSegundaQuincena ++
            }
            numeroPago ++
            listaCobro.save()
        }
    }

    def generar(ListaCobro listaCobro){
        Integer numeroLista = listaCobro.numeroPago
        ListaCobro listaCobroAnterior
        
        //VALIDA SI ES EL PRIMER PAGO DEL AÑO
        if(numeroLista == 1){
            //SE OBTIENE EL AÑO ANTERIOR
            Integer anioAnterior = listaCobro.anio - 1
            //SE OBTIENE LA CANTIDAD DE PAGOS DE LA PERIODICIDAD
            SimCatPeriodicidad periodicidad = listaCobro.periodicidad
            Integer cantidadPeriodicidad = periodicidad.cantidadPagos
            //SE OBTIENE EL ULTIMO PAGO DEL AÑO ANTERIOR
            def criteriaListaCobro = ListaCobro.createCriteria()
            listaCobroAnterior  = criteriaListaCobro.get() {
                and {
                    eq("dependencia",listaCobro.dependencia)
                    eq("numeroPago", cantidadPeriodicidad)
                    eq("anio",anioAnterior )
                }
            }

        }else{
            //SE RECUPERA LA ANTERIOR LISTA DE COBRO
            Integer numeroListaAnterior = numeroLista -1
            def criteriaListaCobro = ListaCobro.createCriteria()
            listaCobroAnterior  = criteriaListaCobro.get() {
                and {
                    eq("dependencia",listaCobro.dependencia)
                    eq("numeroPago", numeroListaAnterior)
                    eq("anio",listaCobro.anio )
                }
            }
        }

        if (!listaCobroAnterior){
            //EN CASO DE NO EXISTIR TOMA LA LISTA DE COBRO ORIGINAL
            log.info ("No existe la lista de cobro anterior, se toma la actual")
            listaCobroAnterior = listaCobro
        }

        log.info "Numero de Lista Anterior: ${listaCobroAnterior}"

        //LISTA PARA GUARDAR LAS AMORTIZACIONES QUE SE VAN A GENERAR PARA LA LISTA
        List<TablaAmortizacionRegistro> listaAmortizaciones = []

        //SE OBTIENEN LAS AMORTIZACIONES CON NUMERO DE PAGO IGUAL A UNO
        //QUE ESTAN ASIGNADAS A LA LISTA DE COBRO
        def criteriaAmortizacionListaCobro = TablaAmortizacionRegistro.createCriteria()
        ArrayList listaAmortizacionListaCobro  = criteriaAmortizacionListaCobro.list() {
            and {
                eq("listaCobroFechaCobro",listaCobro)
                eq("numeroPago", 1)
                //VALIDAR SI SE TIENE QUE COLOCAR UNA CONDICION MAS PARA QUE NO SE REPITA
                //CON LA ITERACION DE LA LISTA DE COBRO ANTERIOR
            }
        }
        //SE ASIGNAN A LA LISTA A PARTIR DE LA CUAL SE CREARAN LOS DETALLES DE LA LISTA DE COBRO        
        listaAmortizacionListaCobro.each{ amortizacion ->
                listaAmortizaciones.add(amortizacion)
        }

        //ITERACION DE LOS DETALLES DE LA LISTA DE COBRO ANTERIOR
        listaCobroAnterior.detalles.each{ detalle ->
            log.info("Si encontro amortizaciones de la lista anterior")
            //POR CADA DETALLE SE RECUPERA EL PRESTAMO 
            //QUE LE CORRESPONDE, PASANDO POR LA AMORTIZACION
            Prestamo prestamo = detalle.amortizacionReal.prestamo
            //SE VALIDA SI EL PRESTAMO SE INCLUYE EN LISTAS DE COBRO
            //Y QUE ESTE ACTIVO
            if (prestamo.incluirEnListasCobro==true &&
                    prestamo.estatusSolicitud.equals(PrestamoEstatus.ACTIVO)){
                log.info ("Prestamo de la lista de cobro anterior: ${prestamo}")
                //SE RECUPERA LA PRIMERA AMORTIZACION NO PAGADA DEL CREDITO
                def criteriaAmortizacionPendiente = TablaAmortizacionRegistro.createCriteria()
                Integer numeroAmortizacionPendiente  = criteriaAmortizacionPendiente.get() {
                    projections {
                       min("numeroPago")
                    }
                    and {
                        eq("prestamo",prestamo)
                        eq("pagado", false)
                    }
                }
                log.info "Numero amortizacion no pagada: ${numeroAmortizacionPendiente}"
                def criteriaAmortizacionPendienteRegistro = TablaAmortizacionRegistro.createCriteria()
                TablaAmortizacionRegistro amortizacionPendiente  = criteriaAmortizacionPendienteRegistro.get() {
                    and {
                        eq("prestamo",prestamo)
                        eq("pagado", false)
                        eq("numeroPago",numeroAmortizacionPendiente)
                    }
                }                
                log.info "Amortizacion no pagada: ${amortizacionPendiente}"
                listaAmortizaciones.add(amortizacionPendiente)   
            }
        }

        /*
        //YA NO ES NECESARIO ELIMINAR LAS AMORTIZACION DE LA LISTA DE COBRO ACTUAL
        //YA QUE QUEDAN COMO UN REGISTRO DE COMO SE DEBIERON HABER PAGADOS LAS AMORTIZACIONES
        //A PARTIR DE LA FECHA DE COBRO
        ArrayList amortizacionesListaCobro = listaCobro.amortizaciones
        amortizacionesListaCobro.each() {
            it.listaCobro = null
        }*/

        ArrayList detallesListaCobro = listaCobro.detalles
        //SE ELIMINAN LOS DETALLES DE LA LISTA DE COBRO ACTUAL
        detallesListaCobro.each() {
            listaCobro.removeFromDetalles(it)   
        }

        //SE GENERAN LOS DETALLES DE LA LISTA DE COBRO
        log.info("Lista de amortizaciones generadas para la Lista")
        Prestamo prestamo
        listaAmortizaciones.each{
            log.info it
            prestamo = it.prestamo
            //AL YA NO HABERSE ELIMINADO LAS AMORTIZACIONES DE LA LISTA DE COBRO ACTUAL
            //YA NO ES NECESARIO AGREGAR LA AMORTIZACION A LA LISTA
            //listaCobro.addToAmortizaciones(it)

            //SE OBTIENE LA AMORTIZACION QUE DEBERIA CORRESPONDER CON LA FECHA DE COBRO
            def criteriaAmortizacionFechaCobro = TablaAmortizacionRegistro.createCriteria()
            TablaAmortizacionRegistro amortizacionFechaCobro  = criteriaAmortizacionFechaCobro.get() {
                and {
                    eq("listaCobroFechaCobro",listaCobro)
                    eq("prestamo", prestamo)
                }
            }            

            //SE OBTIENE LA AMORTIZACION QUE DEBERIA CORRESPONDER CON LA FECHA DEL PRIMER PAGO (INSTALACION)
            def criteriaAmortizacionPrimerPago = TablaAmortizacionRegistro.createCriteria()
            TablaAmortizacionRegistro amortizacionPrimerPago  = criteriaAmortizacionPrimerPago.get() {
                and {
                    eq("listaCobroPrimerPago",listaCobro)
                    eq("prestamo", prestamo)
                }
            }            

            //SE CREAN LOS DETALLES PARA LA LISTA DE COBRO
            ListaCobroDetalle detalle = new ListaCobroDetalle(
                estatus:  ListaCobroDetalleEstatus.INICIO,
                amortizacionReal: it,
                amortizacionFechaCobro: amortizacionFechaCobro,
                amortizacionPrimerPago: amortizacionPrimerPago,
                listaCobro: listaCobro,
                tipoEmpleadoDep: it.prestamo.tipoEmpleadoDep,
                usuario: springSecurityService.getCurrentUser(),
            ).save()
        }
        listaCobro.estatus = SimCatListaCobroEstatus.findByClaveListaEstatus("GENERADA")
        listaCobro.save()
    }
}
