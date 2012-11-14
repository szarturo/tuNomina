package com.sim.tablaAmortizacion

import java.util.Date;

import com.sim.credito.Prestamo
import com.sim.pfin.PfinMovimiento
import com.sim.catalogo.SimCatAccesorio
import com.sim.catalogo.SimCatFormaAplicacion

class TablaAmortizacionServiceException extends RuntimeException {
	String		mensaje
}

class TablaAmortizacionService {

	static transactional = true

	Boolean generaTablaAmortizacion(Prestamo prestamoInstance) {

		def NumeroPagos = 1

		//VALIDA QUE NO EXISTE NINGUN MOVIMIENTO DE PAGO PARA EL PRESTAMO
		def listaMovimiento = PfinMovimiento.findAllByPrestamo(prestamoInstance)

		if (!listaMovimiento) {
			log.info("Se genera la tabla de amortizacion")

			/*
			//OBTIENE TODOS LOS REGISTROS DE LA TABLA DE AMORTIZACION QUE PERTENECEN AL PRESTAMO
			def registrosTabla = TablaAmortizacion.findAllByPrestamo(prestamoInstance)

			registrosTabla.each() {
				prestamoInstance.removeFromTablaAmortizacion(it)
			}
			prestamoInstance.save(flush: true)

			//Se obtienen todos los datos del prestamo
			BigDecimal ImporteIvaInteres
			BigDecimal saldoInsoluto         = prestamoInstance.montoSolicitado
			String sMetodoCalculo 			 = prestamoInstance.promocion.metodoCalculo.claveMetodoCalculo
			BigDecimal Capital				 = prestamoInstance.montoSolicitado
			BigDecimal SaldoInicial			 = prestamoInstance.montoSolicitado
			String sDiasPeriodicidadTasa 	 = prestamoInstance.promocion.periodicidadTasa.clavePeriodicidad
			String sDiasPeriodicidadPago 	 = prestamoInstance.promocion.periodicidadPagos.clavePeriodicidad
			Integer iDiasPeriodicidadTasa 	 = prestamoInstance.promocion.periodicidadTasa.numeroDias
			Integer iDiasPeriodicidadPago 	 = prestamoInstance.promocion.periodicidadPagos.numeroDias
			Integer iva 	 				 = prestamoInstance.promocion.iva
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
					//FORMULA PARA EL PAGO DE INTERES
					PagoIntereses = Capital * Tasa
					//Calcula el Iva del interés
					ImporteIvaInteres = PagoIntereses * (iva/100)
					//FORMULA PARA VER EL PAGO TOTAL
					CuotaTotal = Amortizacion + PagoIntereses + ImporteIvaInteres
					//VARIABLES QUE GENERAN LOS PAGOS TOTALES
					bPagoTotalInteres  = PagoIntereses * prestamoInstance.promocion.numeroDePagos
					bPagoTotalPrestamo = Capital + bPagoTotalInteres
					saldoInsoluto = saldoInsoluto - Amortizacion
				}
				//INTRODUCE LOS REGISTROS A LA TABLA AMORTIZACION
				TablaAmortizacion tablaAmortizacionInsertada = new TablaAmortizacion(
						numeroPago:				NumeroPagos,
						fecha : 				new Date(),
						impSaldoInicial: 		SaldoInicial,
						tasaInteres: 			Tasa,
						impInteres: 			PagoIntereses,
						impIvaInteres: 			ImporteIvaInteres,
						impCapital: 			Amortizacion,
						impPago: 				CuotaTotal,
						impSaldoFinal: 			saldoInsoluto,
						pagoPuntual: 			false,
						impInteresPagado: 		0,
						impIvaInteresPagado: 	0,
						impCapitalPagado: 		0,
						impPagoPagado: 			0,
						pagado: 				false,
						fechaPagoUltimo: 		new Date(),
						fechaValorCalculado: 	new Date(),
						prestamo:               prestamoInstance).save()

				//CALCULA EL SALDO RESTANTE DEL CAPITAL
				SaldoInicial  = SaldoInicial  - Amortizacion

				//Recupera los accesorios del prestamo
				def listaAccesorios	= prestamoInstance.prestamoAccesorio

				def each = listaAccesorios.each() {
					
					//Recupera los datos de los accesorios 
					SimCatAccesorio pAccesorio 			   = it.accesorio
					SimCatFormaAplicacion oFormaAplicacion = it.formaAplicacion
					String claveFormaAplicacion            = oFormaAplicacion.claveFormaAplicacion
					BigDecimal pValor 					   = it.valor
					Integer pValorUnidad 				   = it.unidad.valor
					Integer pDiasPeriodicidad			   = it.periodicidad.numeroDias
					BigDecimal bImporteAccesorio
					BigDecimal bImporteIvaAccesorio

					//INTRODUCE LOS REGISTROS A LA TABLA AMORTIZACION ACCESORIO
					if (claveFormaAplicacion.equals("1")) {
						if (NumeroPagos == 1) {
							bImporteAccesorio = pValor
							TablaAmortizacionAccesorio tablaAmortizacionAccesoriosInsertado = new TablaAmortizacionAccesorio(
									accesorio:                   pAccesorio,
									formaAplicacion : 			 oFormaAplicacion,
									numPago:					 NumeroPagos,
									importeAccesorio :			 bImporteAccesorio,
									importeIvaAccesorio : 		 0,
									importeAccesorioPagado:	 	 0,
									importeIvaAccesorioPagado :  0,
									tablaAmortizacion:			 tablaAmortizacionInsertada
									).save(flush: true,failOnError: true)
						}
						else log.info("No debe de llenar los otros pagos.")

					} else

					if (claveFormaAplicacion.equals("3")) {
						bImporteAccesorio = (((prestamoInstance.montoSolicitado * pValor) / pValorUnidad) / pDiasPeriodicidad) * iDiasPeriodicidadPago
						bImporteIvaAccesorio	= bImporteAccesorio * (iva/100)
						TablaAmortizacionAccesorio tablaAmortizacionAccesoriosInsertado = new TablaAmortizacionAccesorio(
								accesorio:                   pAccesorio,
								formaAplicacion : 			 oFormaAplicacion,
								numPago:					 NumeroPagos,
								importeAccesorio :			 bImporteAccesorio,
								importeIvaAccesorio : 		 bImporteIvaAccesorio,
								importeAccesorioPagado:	 	 0,
								importeIvaAccesorioPagado :  0,
								tablaAmortizacion:			 tablaAmortizacionInsertada
								).save(flush: true,failOnError: true)
					} else

					if (claveFormaAplicacion.equals("4")) {
						bImporteAccesorio = (((SaldoInicial * pValor) / pValorUnidad) / pDiasPeriodicidad) * iDiasPeriodicidadPago
						bImporteIvaAccesorio	= bImporteAccesorio * (iva/100)
						TablaAmortizacionAccesorio tablaAmortizacionAccesoriosInsertado = new TablaAmortizacionAccesorio(
								accesorio:                   pAccesorio,
								formaAplicacion : 			 oFormaAplicacion,
								numPago:					 NumeroPagos,
								importeAccesorio :			 bImporteAccesorio,
								importeIvaAccesorio : 		 bImporteIvaAccesorio,
								importeAccesorioPagado:	 	 0,
								importeIvaAccesorioPagado :  0,
								tablaAmortizacion:			 tablaAmortizacionInsertada
								).save(flush: true,failOnError: true)
					} else
					if (claveFormaAplicacion.equals("5")) {
						bImporteAccesorio = (((1 * pValor) / 1) / pDiasPeriodicidad) * iDiasPeriodicidadPago
						bImporteIvaAccesorio	= bImporteAccesorio * (iva/100)
						TablaAmortizacionAccesorio tablaAmortizacionAccesoriosInsertado = new TablaAmortizacionAccesorio(
								accesorio:                   pAccesorio,
								formaAplicacion : 			 oFormaAplicacion,
								numPago:					 NumeroPagos,
								importeAccesorio :			 bImporteAccesorio,
								importeIvaAccesorio : 		 bImporteIvaAccesorio,
								importeAccesorioPagado:	 	 0,
								importeIvaAccesorioPagado :  0,
								tablaAmortizacion:			 tablaAmortizacionInsertada
								).save(flush: true,failOnError: true)
					}
				}
				NumeroPagos++
			}*/

		}
		else {
			log.info("No se genera la tabla ya que existen pagos al credito.")
			throw new TablaAmortizacionServiceException(mensaje: "No se genero la Tabla de Amortizacion ya existen pagos al credito")
		}

		return true
	}
}