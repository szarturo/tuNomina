package com.sim.listacobro

class ListaCobroController {

    def scaffold = true

    def listaCobroService

    def generar(){
     	ListaCobro listaCobro = ListaCobro.get(params.id)
    	log.info "Lista Cobro: ${listaCobro}"
    	listaCobroService.generar(listaCobro)
    }


}
