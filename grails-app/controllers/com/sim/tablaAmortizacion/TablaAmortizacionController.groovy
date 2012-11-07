package com.sim.tablaAmortizacion

import org.springframework.dao.DataIntegrityViolationException
import com.sim.credito.Prestamo

class TablaAmortizacionController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
		Prestamo prestamo = Prestamo.get(params.idPrestamo)
		
		def listaTablaAmortizacion = TablaAmortizacion.findAllByPrestamo(prestamo)
		
		listaTablaAmortizacion.sort{ it.numeroDePago } 
		
		[tablaAmortizacionInstanceList: listaTablaAmortizacion, tablaAmortizacionInstanceTotal: listaTablaAmortizacion.size() ]
	}

}