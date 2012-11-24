package com.sim.pfin

class PrelacionPagoConcepto {

    Integer numeroAmortizacion
    Integer ordenPago
    BigDecimal cantidadPagar
    PfinCatConcepto concepto

    PrelacionPagoConcepto(){}

    PrelacionPagoConcepto (Integer numeroAmortizacion, Integer ordenPago, BigDecimal cantidadPagar, PfinCatConcepto concepto){
        this.numeroAmortizacion = numeroAmortizacion
        this.ordenPago = ordenPago
        this.cantidadPagar = cantidadPagar
        this.concepto =  concepto
    }

}
