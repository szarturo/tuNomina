package com.sim.listacobro

import com.sim.entidad.EntDependencia
import com.sim.catalogo.SimCatPeriodicidad
import com.sim.catalogo.SimCatListaCobroEstatus
import com.sim.catalogo.SimCatEtapaPrestamo
import com.sim.tablaAmortizacion.TablaAmortizacionRegistro
import com.sim.credito.Prestamo

class ListaCobroService {

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
        println ("Service Lista Cobro: ${listaCobro}")
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

        //SE CONTINUAN CONTEMPLANDO LAS AMORTIZACIONES CON NUMERO DE PAGO IGUAL A UNO
        //QUE ACTUALMENTE SE ENCUENTRAN EN LA LISTA DE COBRO
        listaCobro.registros.each{ amortizacion ->
            if (amortizacion.numeroPago == 1){
                log.info "NumeroPago igual a 1: ${amortizacion}"
                listaAmortizaciones.add(amortizacion)
            }
        }

        //ITERACION DE LAS AMORTIZACIONES DE LA LISTA DE COBRO ANTERIOR
        listaCobroAnterior.registros.each{ amortizacion ->
            log.info("Si encontro amortizaciones de la lista anterior")
            //POR CADA AMORTIZACION SE RECUPERA EL PRESTAMO 
            //QUE LE CORRESPONDE
            Prestamo prestamo = amortizacion.prestamo
            //SE VALIDA SI EL PRESTAMO SE INCLUYE EN LISTAS DE COBRO
            //Y QUE ESTE ACTIVO
            if (prestamo.incluirEnListasCobro==true &&
                    prestamo.estatusSolicitud.equals(SimCatEtapaPrestamo.findByClaveEtapaPrestamo("ACTIVO"))){
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

        //SE ELIMINAN LAS AMORTIZACIONES DE LA LISTA DE COBRO ACTUAL
        listaCobro.registros.each() {
            listaCobro.removeFromRegistros(it)
        }

        //SE ASIGNAN LAS AMORTIZACIONES GENERADAS A LA LISTA DE COBRO
        listaAmortizaciones.each{
            log.info("Lista de amortizaciones generadas para la Lista")
            log.info it
            listaCobro.addToRegistros(it)
        }
        listaCobro.save()
    }
}
