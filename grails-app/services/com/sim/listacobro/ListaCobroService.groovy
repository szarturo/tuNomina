package com.sim.listacobro

import com.sim.entidad.EntDependencia
import com.sim.catalogo.SimCatPeriodicidad

class ListaCobroService {

    //METODO UTILIZADO EN EL BOOTSTRAP

    def generarListasCobro(EntDependencia dependencia,
            Integer anio,
            SimCatPeriodicidad periodicidad){

        Integer pagoNomina = 1

        (1..periodicidad.cantidadPagos).each{

            new ListaCobro(anio:  anio,
                    numeroPago: pagoNomina,
                    parcialidades: false,
                    dependencia: dependencia,
            ).save()

            pagoNomina ++
        }

    }
}
