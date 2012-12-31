package com.sim.credito

import com.sim.catalogo.SimCatDocumento

class PrestamoDocumento {

    String          nombreArchivo
    SimCatDocumento documento

    static belongsTo = [prestamo : Prestamo]

    static constraints = {
        nombreArchivo size:5..100
        documento()
        prestamo()
    }

    String toString(){
        nombreArchivo
    }
}
