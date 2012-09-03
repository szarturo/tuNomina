package com.sim.entidad

class Delegacion {

    String  claveDelegacion
    String  nombreDelegacion
    String  descripcionDelegacion

    static constraints = {
        claveDelegacion(size:3..15, unique: true, nullable: false, blank: false)
        nombreDelegacion(size:3..50, unique: true, nullable: false, blank: false)
        descripcionDelegacion(size:5..150, nullable: true)
    }

    String toString() {
        "${nombreDelegacion}"
    }}
