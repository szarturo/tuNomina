package com.sim.listacobro

import com.sim.entidad.EntDependencia
import com.sim.catalogo.SimCatPeriodicidad
import com.sim.catalogo.SimCatListaCobroEstatus

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
        if(numeroPago == 1){
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
                    ge("anio",anioAnterior )
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
                    ge("anio",listaCobro.anio )
                }
            }
        }

        printnl "Numero de Lista Anterior: ${listaCobroAnterior}"
    }
}
