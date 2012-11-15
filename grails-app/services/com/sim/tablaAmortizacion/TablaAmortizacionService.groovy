package com.sim.tablaAmortizacion

import java.util.Date;

import com.sim.credito.Prestamo
import com.sim.pfin.PfinMovimiento
import com.sim.producto.ProPromocion
import com.sim.catalogo.SimCatAccesorio
import com.sim.catalogo.SimCatFormaAplicacion
import com.sim.catalogo.SimCatMetodoCalculo
import com.sim.catalogo.SimCatPeriodicidad
import com.sim.catalogo.SimCatUnidad

class TablaAmortizacionServiceException extends RuntimeException {
	String	mensaje
}

class TablaAmortizacionService {

	static transactional = true

	Boolean generaTablaAmortizacion(Prestamo prestamoInstance) {

		Integer numeroPago = 1

		//VALIDA QUE NO EXISTE NINGUN MOVIMIENTO DE PAGO PARA EL PRESTAMO
		def listaMovimiento = PfinMovimiento.findAllByPrestamo(prestamoInstance)

		if (!listaMovimiento) {
			log.info("Se genera la tabla de amortizacion")

			//OBTIENE TODOS LOS REGISTROS DE LA TABLA DE AMORTIZACION QUE PERTENECEN AL PRESTAMO PARA ELIMINARLOS
			
			//org.hibernate.collection.PersistentSet registrosTabla = prestamoInstance.tablaAmortizacion
			ArrayList registrosTabla = TablaAmortizacion.findAllByPrestamo(prestamoInstance)
			//log.info prestamoInstance.tablaAmortizacion.class
			//log.info registrosTabla.class
			
			if(registrosTabla){
				log.info("Elimina los registros de la tabla de amortizacion del credito")
				registrosTabla.each() {
					prestamoInstance.removeFromTablaAmortizacion(it)
				}
				prestamoInstance.save(flush: true)
			}

			//SE OBTIENEN LOS DATOS DEL PRESTAMO
			ProPromocion promocion = prestamoInstance.promocion
			SimCatMetodoCalculo metodoCalculo 	 = promocion.metodoCalculo
			SimCatPeriodicidad  periodicidadTasa = promocion.periodicidadTasa
			SimCatPeriodicidad  periodicidadPago = promocion.periodicidadPagos
			
			Integer diasPeriodicidadTasa 	 = periodicidadTasa.numeroDias
			Integer diasPeriodicidadPago 	 = periodicidadPago.numeroDias
			Integer iva 	 				 = promocion.iva
						
			BigDecimal importeIvaInteres
			BigDecimal saldoInsoluto         = prestamoInstance.montoSolicitado
			BigDecimal capital				 = prestamoInstance.montoSolicitado
			BigDecimal saldoInicial			 = prestamoInstance.montoSolicitado
			
			log.info("Numero de pagos: ${promocion.numeroDePagos}")
			
			//RECUPERA LOS ACCESORIOS DEL PRESTAMO
			ArrayList listaAccesorios	= prestamoInstance.prestamoAccesorio
			log.info listaAccesorios.class
			
			(1..promocion.numeroDePagos).each{
				
				//FORMULA PARA EL CALCULO DE LA TASA
				BigDecimal tasa = (promocion.tasaDeInteres/100) * (diasPeriodicidadPago / diasPeriodicidadTasa)
				//VARIABLES A CALCULAR
				BigDecimal amortizacion      = 0
				BigDecimal pagoIntereses     = 0
				BigDecimal cuotaTotal        = 0
				BigDecimal pagoTotalInteres  = 0
				BigDecimal pagoTotalPrestamo = 0
				
				log.info("Tasa: ${tasa}")
				
				if (promocion.metodoCalculo.equals(SimCatMetodoCalculo.findByClaveMetodoCalculo('METODO01'))){
					//FORMULA PARA EL CALCULO DE LA AMORTIZACION
					amortizacion = capital / promocion.numeroDePagos
					//FORMULA PARA EL PAGO DE INTERES
					pagoIntereses = capital * tasa
					//CALCULA EL IVA DEL INTERES
					importeIvaInteres = pagoIntereses * (iva/100)
					//FORMULA PARA VER EL PAGO TOTAL
					cuotaTotal = amortizacion + pagoIntereses + importeIvaInteres
					//VARIABLES QUE GENERAN LOS PAGOS TOTALES
					pagoTotalInteres  = pagoIntereses * promocion.numeroDePagos
					pagoTotalPrestamo = capital + pagoTotalInteres
					saldoInsoluto = saldoInsoluto - amortizacion
				}
				
				//INTRODUCE LOS REGISTROS A LA TABLA AMORTIZACION
				TablaAmortizacion tablaAmortizacionInsertada = new TablaAmortizacion(
						numeroPago:				numeroPago,
						fecha : 				new Date(), //CORREGIR FECHA DE PAGO
						impSaldoInicial: 		saldoInicial,
						tasaInteres: 			tasa,
						impInteres: 			pagoIntereses,
						impIvaInteres: 			importeIvaInteres,
						impCapital: 			amortizacion,
						impPago: 				cuotaTotal,
						impSaldoFinal: 			saldoInsoluto,
						pagoPuntual: 			false,
						impInteresPagado: 		0,
						impIvaInteresPagado: 	0,
						impCapitalPagado: 		0,
						impPagoPagado: 			0,
						pagado: 				false,
						//fechaPagoUltimo: 		new Date(),
						fechaValorCalculado: 	new Date(),
						prestamo:               prestamoInstance).save()

				
				//CALCULA EL SALDO RESTANTE DEL CAPITAL
				saldoInicial  = saldoInicial  - amortizacion
				

				def each = listaAccesorios.each() {
					
					//RECUPERA LOS DATOS DEL ACCESORIO 
					SimCatPeriodicidad periodicidadAccesorio = it.periodicidad
					SimCatFormaAplicacion formaAplicacion    = it.formaAplicacion
					SimCatAccesorio accesorio = it.accesorio
					SimCatUnidad    unidad    = it.unidad
					
					BigDecimal valor  = it.valor
					BigDecimal importeAccesorio
					BigDecimal importeIvaAccesorio
					//String claveFormaAplicacion = oFormaAplicacion.claveFormaAplicacion
					
					//INTRODUCE LOS REGISTROS A LA TABLA AMORTIZACION ACCESORIO
					if (formaAplicacion.equals(SimCatFormaAplicacion.findByClaveFormaAplicacion('MONTO_PRESTADO'))) {
						if (numeroPago == 1) {
							log.info("TABLA AMORTIZACION ACCESORIO PAGO: ${numeroPago}")
							importeAccesorio = valor
							TablaAmortizacionAccesorio tablaAmortizacionAccesoriosInsertado = new TablaAmortizacionAccesorio(
									accesorio:                   accesorio,
									formaAplicacion : 			 formaAplicacion,
									numPago:					 numeroPago,
									importeAccesorio :			 importeAccesorio,
									importeIvaAccesorio : 		 0,
									importeAccesorioPagado:	 	 0,
									importeIvaAccesorioPagado :  0,
									tablaAmortizacion:			 tablaAmortizacionInsertada
									).save(flush: true,failOnError: true)
						}
						else {
							log.info("TABLA AMORTIZACION ACCESORIO PAGO: ${numeroPago}")
							log.info("No debe de llenar los otros pagos.")
						}

					}/* else

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
					}*/
				}
				numeroPago++
			}
				

		}
		else {
			log.info("No se genera la tabla ya que existen pagos al credito.")
			throw new TablaAmortizacionServiceException(mensaje: "No se genero la Tabla de Amortizacion ya existen pagos al credito")
		}

		return true
	}
}