package com.sim.pfin

class ProcesadorFinancieroService {

    def serviceMethod() {
		
		new PfinPreMovimiento(cuenta:  cuenta1,
			divisa: PfinDivisa.findByClaveDivisa('MXP'),
			fechaOperacion:new Date('09/30/2012'),
			fechaLiquidacion:new Date('09/30/2012'),
			importeNeto: 1000.50,
			referencia: 34,
			prestamo : Prestamo.findByClavePrestamo('KLP987'),
			nota : 'Pago de Prestamo',
			listaCobro : 3453,
			//pfinMovimiento()
			situacionPreMovimiento : 'PV',
			fechaRegistro:new Date('09/30/2012'),
			logIpDireccion: 'xxxxxxxxx',
			logUsuario:'xxxxxxxxxx',
			logHost:'xxxxxxxxxx',
			usuario : usuarioKermit,
			fechaAplicacion:new Date('09/30/2012'),
			numeroPagoAmortizacion: 1,
			operacion: PfinCatOperacion.findByClaveOperacion('TEDEPEFE'),
		).save(flush: true,failOnError: true)

    }
}
