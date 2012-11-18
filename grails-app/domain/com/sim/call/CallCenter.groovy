package com.sim.call

import com.sim.credito.Prestamo

class CallCenter {

    Date     fechaLlamada
    Boolean  seContacto = false
    Boolean  prestamoCobrado = false
    String   comentarios
    Boolean  prestamoCancelado = false

    static belongsTo = [prestamo:Prestamo]

    static constraints = {
        prestamo(nullable :false)
        fechaLlamada(nullable: false)
        prestamoCobrado()
        comentarios(nullable:true, size:5..255)
        prestamoCancelado()

    }
}
