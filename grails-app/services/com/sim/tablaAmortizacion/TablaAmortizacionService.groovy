package com.sim.tablaAmortizacion

import com.sim.credito.Prestamo

class TablaAmortizacionService {

	static transactional = true

	Boolean miMetodo() {
		return true
	}

	Boolean generaTablaAmortizacion(Prestamo prestamoInstance) {

		log.info ("Servicio Prestamo: ${prestamoInstance}")
		def NumeroPagos = 1

		BigDecimal saldoInsoluto         = prestamoInstance.montoSolicitado
		//Integer iIdCliente               = tablaAmortizacionInstance.idCliente
		String sMetodoCalculo 			 = prestamoInstance.promocion.metodoCalculo.claveMetodoCalculo
		BigDecimal Capital				 = prestamoInstance.montoSolicitado
		String sDiasPeriodicidadTasa 	 = prestamoInstance.promocion.periodicidadTasa.clavePeriodicidad
		String sDiasPeriodicidadPago 	 = prestamoInstance.promocion.periodicidadPagos.clavePeriodicidad
		Integer iDiasPeriodicidadTasa 	 = prestamoInstance.promocion.periodicidadTasa.numeroDias
		Integer iDiasPeriodicidadPago 	 = prestamoInstance.promocion.periodicidadPagos.numeroDias

		println "Metodo Calculo = ${sMetodoCalculo}, Periodicidad Tasa = ${sDiasPeriodicidadTasa}, PeriodicidadPago = ${sDiasPeriodicidadPago}"

		(1..prestamoInstance.promocion.numeroDePagos).each{

			//FORMULA PARA EL CALCULO DE LA TASA
			BigDecimal Tasa = (prestamoInstance.promocion.tasaDeInteres/100) * (iDiasPeriodicidadPago / iDiasPeriodicidadTasa)

			//Variables a calcular.
			BigDecimal Amortizacion       = 0
			BigDecimal PagoIntereses      = 0
			BigDecimal CuotaTotal         = 0
			BigDecimal bPagoTotalInteres  = 0
			BigDecimal bPagoTotalPrestamo = 0


			if (sMetodoCalculo.equals("METODO01")){
				//FORMULA PARA EL CALCULO DE LA AMORTIZACION
				Amortizacion = Capital / prestamoInstance.promocion.numeroDePagos
				//FORMULA PARA EL PAGO DE INTERES MENSUAL
				PagoIntereses = Capital * Tasa
				//FORMULA PARA VER EL PAGO TOTAL DEL MES
				CuotaTotal = Amortizacion + PagoIntereses
				//CALCULA EL SALDO RESTANTE DEL CAPITAL
				saldoInsoluto = saldoInsoluto - Amortizacion
				//VARIABLES QUE GENERAN LOS PAGOS TOTALES
				bPagoTotalInteres  = PagoIntereses * prestamoInstance.promocion.numeroDePagos
				bPagoTotalPrestamo = Capital + bPagoTotalInteres
				println "*************************"
				println"""
					NumeroPago: 			 ${NumeroPagos}
					Interes: 				 ${PagoIntereses}
					Amortizacion Capital:    ${Amortizacion}
					Pago Total: 			 ${CuotaTotal}
					Saldo Insoluto: 		 ${saldoInsoluto}
					Pago total de interes:   ${bPagoTotalInteres}
					Pago total del prestamo: ${bPagoTotalPrestamo}
				"""

			}

			//INTRODUCE LOS REGISTROS A LA TABLA AMORTIZACION
			prestamoInstance.addToTablaAmortizacion(new TablaAmortizacion(numeroDePago:NumeroPagos,interes:PagoIntereses,amortizacionCapital:Amortizacion,pagoTotal:CuotaTotal,saldoInsoluto:saldoInsoluto,interesTotal:bPagoTotalInteres, pagoTotalPrestamo:bPagoTotalPrestamo)).save()
			NumeroPagos++
		}

		return true
	}
}