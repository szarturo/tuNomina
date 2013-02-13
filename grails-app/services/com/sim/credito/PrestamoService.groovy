package com.sim.credito

import mx.com.creditoreal.ws.client.Client
import mx.com.creditoreal.ws.dto.Adicional
import mx.com.creditoreal.ws.dto.Solicitud
import mx.com.creditoreal.ws.dto.SolicitudDecididasDia
import mx.com.creditoreal.ws.dto.ComprasDia
import mx.com.creditoreal.ws.dto.CarteraGeneradaDia
import mx.com.creditoreal.ws.exception.ClientException
import com.sim.producto.ProPromocion
import com.sim.pfin.PfinCatParametro
import com.sim.catalogo.SimCatCrMotivo

//Clases importadas para el metodo: altaPrestamos
import com.sim.catalogo.*
import com.sim.entidad.*
import com.sim.empresa.EmpEmpleado
import com.sim.cliente.RsCliente
import com.sim.pfin.PfinCatConcepto

class PrestamoServiceException extends RuntimeException {
	String mensaje
}

class PrestamoService {

	//Servicio que genera las Tablas de Amortizacion
	def tablaAmortizacionRegistroService 

    String envioSolicitudCreditoReal(Prestamo prestamo) {

    	def documentos = prestamo.documentos
    	File doctoSolicitud
    	File doctoPagare
    	File doctoIdentificacion
    	File doctoAdicionalA
    	File doctoAdicionalB
    	File doctoAdicionalC
    	File doctoAdicional

		File path = new File("${System.getProperty('user.home')}/Documents/tuNomina/documentosCredito/${prestamo.cliente.id}/${prestamo.folioSolicitud}")

		List<PrestamoDocumento> documentosListaCr = []
		
		documentos.each{documentoPrestamo ->

			switch ( documentoPrestamo.documento ) {
			    case SimCatDocumento.findByClaveDocumento('SOLICITUD_PRESTAMO'):
			    	doctoSolicitud = new File(path, documentoPrestamo.nombreArchivo)
			    	log.info "Solicitud: "+doctoSolicitud
			    break
			    case SimCatDocumento.findByClaveDocumento('PAGARE_PRESTAMO'):
			    	doctoPagare = new File(path, documentoPrestamo.nombreArchivo)
			    	log.info "Pagare: "+doctoPagare
			    break
			    case SimCatDocumento.findByClaveDocumento('IDENTIFICACION_PRESTAMO'):
			    	doctoIdentificacion = new File(path, documentoPrestamo.nombreArchivo)
			    	log.info "Identificacion: "+doctoIdentificacion
			    break
			    default:
			    	if (!doctoAdicionalA){
			    		doctoAdicionalA = new File(path, documentoPrestamo.nombreArchivo)
			    		log.info "doctoAdicionalA: "+doctoAdicionalA
			    	}else if(!doctoAdicionalB){
			    		doctoAdicionalB = new File(path, documentoPrestamo.nombreArchivo)
			    		log.info "doctoAdicionalB: "+doctoAdicionalB
			    	}else if(!doctoAdicionalC){
			    		doctoAdicionalC = new File(path, documentoPrestamo.nombreArchivo)
			    		log.info "doctoAdicionalC: "+doctoAdicionalC
			    	}else{
			    		//SE ASIGNA EL DOCUMENTO A UNA LISTA PARA ENVIAR
			    		//A CREDITO REAL
			    		documentosListaCr.add(documentoPrestamo)
			    		log.info "No se asigno el documento: "+documentoPrestamo.nombreArchivo
			    	}
			}
    	}
    	
		Client client = null
		Boolean pruebasCr = PfinCatParametro.findByClaveMedio("SistemaMtn").pruebasClienteWsCr
		log.info ("Pruebas CR: ${pruebasCr}")
		
		try {
			//TRUE SIGNIFICA QUE ENVIA A UN WEBSERVICE DE CREDITO REAL EN UN AMBIENTE DE PRUEBAS
			client = new Client(pruebasCr)
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace()
		}

		Solicitud solicitud = new Solicitud()
		solicitud.setReferencia("${prestamo.folioSolicitud}") //Numero de Referencia o Folio propia del distribuidor o dejar en blanco
        log.info "Referencia: " + solicitud.getReferencia()
		solicitud.setDistribuidor("${prestamo.dependencia.distribuidor}") //9999  Se asignara por CR
		log.info "Distribuidor: " + solicitud.getDistribuidor()
		solicitud.setSucursal("${prestamo.promocion.clavePromocion}") //Se asignara por CR
		log.info "Sucursal: " + solicitud.getSucursal()
		solicitud.setVendedor("${prestamo.vendedor.clavePromotor}") //Clave de Vendedor asignada. Dejar en blanco para pruebas
        log.info "Vendedor: " + solicitud.getVendedor()
		solicitud.setDap("0") //Enviar un 0
		solicitud.setPercepciones(prestamo.percepcionesMensuales) //Percepciones mensuales del cliente
        log.info "Percepciones Mensuales: "+ solicitud.getPercepciones()
		solicitud.setDeducciones(prestamo.deduccionesMensuales) //Deducciones mensuales del cliente
        log.info "Deducciones Mensuales: "+ solicitud.getDeducciones()
		solicitud.setSolicitud(doctoSolicitud?.getBytes()) //Imagen binarizada de la Solictud de credito
		solicitud.setPagare(doctoPagare?.getBytes()) //Imagen binarizada del pagare
		solicitud.setIdentificacion(doctoIdentificacion?.getBytes()) //Imagen binarizada de la identificacion
		solicitud.setDocadA(doctoAdicionalA?.getBytes()) //Imagen binarizada de un documento adicional
		solicitud.setDocadB(doctoAdicionalB?.getBytes()) //Imagen binarizada de un documento adicional
		solicitud.setDocadC(doctoAdicionalC?.getBytes()) //Imagen binarizada de un documento adicional
		
		String consecutivo = "NO SE GENERO"
			
		try {
			consecutivo = client.solicitudZell(solicitud)
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace()
		}
		log.info("Respuesta Credito Real Consecutivo: ${consecutivo}")
		
		prestamo.consecutivoCr = consecutivo

		if(documentosListaCr){
			log.info ("Existen mas de 6 documentos para enviar a CR")

			documentosListaCr.each{ documentoPrestamo ->
				
			    doctoAdicional = new File(path, documentoPrestamo.nombreArchivo)				

				Adicional adicional = new Adicional()
				adicional.setConsecutivo(consecutivo)
				adicional.setDocAd(doctoAdicional.getBytes())
				
				String respuesta = "No se genero respuesta"
				try {
					respuesta = client.documentoAdicional(adicional)
				} catch (ClientException e) {
					// TODO Auto-generated catch block
					e.printStackTrace()
				}
				log.info("Respuesta para el documento ${doctoAdicional}: ${respuesta}")
			}
		}
        return consecutivo
    }

    String envioDocumentoCreditoReal(String idDocumento) {

		PrestamoDocumento documento = 
			PrestamoDocumento.get(idDocumento)	
		log.info "Documento: "+documento

		String respuesta = "No se genero respuesta"

		File path = new File("${System.getProperty('user.home')}/Documents/tuNomina/documentosCredito/${documento.prestamo.cliente.id}/${documento.prestamo.folioSolicitud}")
		log.info ("Path: "+path)

		String consecutivo = documento.prestamo.consecutivoCr
		log.info "Prestamo: "+documento.prestamo
		log.info "Consecutivo CR: "+consecutivo

		if(consecutivo){
			File doctoAdicional = new File(path, documento.nombreArchivo)
			log.info "Documento Adicional: "+doctoAdicional

			Adicional adicional = new Adicional()
			adicional.setConsecutivo(consecutivo)
			adicional.setDocAd(doctoAdicional.getBytes())		
			
			try {
				Client client = new Client(PfinCatParametro.findByClaveMedio("SistemaMtn").pruebasClienteWsCr)
				respuesta = client.documentoAdicional(adicional)
			} catch (ClientException e) {
				throw new PrestamoServiceException(mensaje: "Se genero un error al enviar el documento a Crédito Real.")
			}
			log.info("Respuesta para el documento ${doctoAdicional}: ${respuesta}")
		}else{
			throw new PrestamoServiceException(mensaje:"No existe consecutivo, debe enviarse primero la solicitud a Credito Real")
		}

		return "${documento.nombreArchivo} : ${respuesta}"
    }

    Boolean solicitudesDecididasDia(
    	String distribuidor,
    	String dia,
    	String mes,
    	String anio){

		if (dia.size()!=2){
			dia = "0${dia}"
		} 

		if (mes.size()!=2){
			mes = "0${mes}"
		} 
		String fecha = "$anio$mes$dia"
		log.info("Fecha: ${fecha}")

		String usuario = 'pruebaws'
		String password = 'ws1866_cr'

		try {
			Client cliente = new Client(PfinCatParametro.findByClaveMedio("SistemaMtn").pruebasClienteWsCr)

			List<SolicitudDecididasDia> solicitudes = 
				cliente.getSolicitudesDecididasDia(distribuidor,fecha,usuario,password)

			//SE OBTIENE SI ESTAMOS TRABAJANDO EN UN AMBIENTE DE PRUEBAS
			Boolean pruebasWsCr = PfinCatParametro.findByClaveMedio("SistemaMtn").pruebasClienteWsCr
			//LINEA TEMPORAL PARA PRUEBAS WS DE CR
			Integer x = 1

			for(SolicitudDecididasDia solicitud : solicitudes){
				log.info "Nombre Solicitud: ${solicitud.nombre}"
				Integer folioSolicitud = solicitud.folio.toInteger()

				//VALIDA SI ESTAMOS EN EL AMBIENTE DE PRUEBAS DE LOS WS
				if (pruebasWsCr){
					//LINEAS TEMPORALES
					if (x==1){
						folioSolicitud = 34534	
					}else{
						//ASEGURARSE TENER EL FOLIO SOLICITUD CON VALOR 
						//IGUAL A 1
						folioSolicitud = 1	
					}
				}
				
				Prestamo prestamo = Prestamo.findByFolioSolicitud(folioSolicitud)
				log.info "Prestamo: ${prestamo}"

				new PrestamoCrRespuesta(
					 consecutivo : 		solicitud.consecutivo,
					 folio : 			solicitud.folio,
					 referencia : 		solicitud.referencia,
					 claveDistribuidor: solicitud.clavedistribuidor,
					 claveTienda : 		solicitud.clavetienda,	
					 nombreSucursal : 	solicitud.nombreSucursal,
					 fechaRecepcion : 	solicitud.fechaFinCaptura,
					 fechaRespuesta : 	solicitud.fechaRespuesta,
					 montoSolicitado : 	solicitud.montoSolicitado,
					 montoAutorizado : 	solicitud.montoAutorizado,
					 estatus : 			solicitud.status,
					 motivo	: 			solicitud.motivo,
					 nombre : 			solicitud.nombre,
					 vendedor : 		solicitud.vendedor,
					 promocion : 		solicitud.promocion,
					 observaciones : 	solicitud.observaciones,
					 numeroCliente : 	solicitud.numeroCliente,
					 prestamo : 		prestamo,
				).save(failOnError:true)

				//LINEA TEMPORAL PARA PRUEBAS WS DE CR
				x++

				ProPromocion promocion = ProPromocion.findByClavePromocion(solicitud.promocion)
				if (!promocion.equals(prestamo.promocion)){
					log.info ("La promocion no es igual a la asignada originalmente")
					log.info ("Promocion Prestamo: "+prestamo.promocion)
					log.info ("Promocion Respuesta CR: "+promocion)
					//SE SOBREESCRIBE LA PROMOCION
					//prestamo.promocion = promocion
				}

	            switch ( solicitud.status ) {
	                case "TRA":
						prestamo.estatusSolicitud = PrestamoEstatus.TRANSMISION_CR
	                break
	                case "RET":
						prestamo.estatusSolicitud = PrestamoEstatus.RETENSION_CR
	                break
	                case "PRO":
						prestamo.estatusSolicitud = PrestamoEstatus.PROCESADA
	                break
	                case "AUT":
						prestamo.estatusSolicitud = PrestamoEstatus.AUTORIZADA
	                break
	               	case "REC":
						prestamo.estatusSolicitud = PrestamoEstatus.RECHAZADA_CR
	                break
	                case "PEN":
						prestamo.estatusSolicitud = PrestamoEstatus.PENDIENTE_CR
	                break
	                case "CAN":
						prestamo.estatusSolicitud = PrestamoEstatus.CANCELADA_CR
	                break
	                case "VAL":
						prestamo.estatusSolicitud = PrestamoEstatus.VALIDACION_CR
	                break
	                case "REV":
						prestamo.estatusSolicitud = PrestamoEstatus.REVALORACION_CR
	                break
	                case "CAP":
						prestamo.estatusSolicitud = PrestamoEstatus.CAPTURA_CR
	                break
	               	case "DEV":
						prestamo.estatusSolicitud = PrestamoEstatus.DEVOLUCION_CR
	                break

	                default:
	                    log.info ("No se encontro el estatus asignado: "+prestamo.estatusSolicitud)
	            }	
	            //ASIGNA LA RESPUESTA DE CREDITO REAL
	            prestamo.movitoRespuestaCr = SimCatCrMotivo.findByCodigo(solicitud.motivo)
	            log.info "Catalogo Motivo: "+SimCatCrMotivo.findByCodigo(solicitud.motivo)
			}		

		} catch (ClientException e) {
			throw new PrestamoServiceException(mensaje: "Se genero un plobema de comunicación con Crédito Real.")
		}
		return true
    }

    Boolean comprasDia(
    	String distribuidor,
    	String dia,
    	String mes,
    	String anio){

		if (dia.size()!=2){
			dia = "0${dia}"
		} 

		if (mes.size()!=2){
			mes = "0${mes}"
		} 
		String fecha = "$anio$mes$dia"
		log.info ("Fecha: "+fecha)

		String usuario = 'pruebaws'
		String password = 'ws1866_cr'

		try {
			Client cliente = new Client(PfinCatParametro.findByClaveMedio("SistemaMtn").pruebasClienteWsCr)

			//SE OBTIENE SI ESTAMOS TRABAJANDO EN UN AMBIENTE DE PRUEBAS
			Boolean pruebasWsCr = PfinCatParametro.findByClaveMedio("SistemaMtn").pruebasClienteWsCr
			//LINEA TEMPORAL PARA PRUEBAS WS DE CR
			Integer x = 1

			List<ComprasDia> comprasDia = 
				cliente.getComprasDia(distribuidor,fecha,usuario,password)
			for(ComprasDia compra : comprasDia){
				log.info "Nombre Solicitud: ${compra.nombre}"
				Integer folioSolicitud 
				//VALIDA SI ESTAMOS EN EL AMBIENTE DE PRUEBAS DE LOS WS
				if (pruebasWsCr){
					//LINEAS TEMPORALES
					if (x==1){
						folioSolicitud = 34534	
					}else if(x==2){
						//ASEGURARSE TENER EL FOLIO SOLICITUD CON VALOR 
						//IGUAL A 1
						folioSolicitud = 1	
					}else{
						folioSolicitud = 3
					}
				}else{
					//AMBIENTE PRODUCTIVO CR
					//CLASIFICADOR EQUIVALE A CONSECUTIVO EN solicitudesDecididasDia
					String consecutivo = compra.clasificador
					PrestamoCrRespuesta solicitudDecididaDia = PrestamoCrRespuesta.findByConsecutivo(consecutivo)
					log.info ("Solicitud Decidida:"+solicitudDecididaDia)	
					if (!solicitudDecididaDia){
						log.info ("No encontro el registro de la solicitud decidida")
						throw new PrestamoServiceException(mensaje: "No se encontro el registro de la solicitud decidida")
					}else{
						folioSolicitud = solicitudDecididaDia.folio
					}
				}
				
				Prestamo prestamo = Prestamo.findByFolioSolicitud(folioSolicitud)
				log.info "Prestamo: ${prestamo}"

				new PrestamoCrComprada(
					 numeroSolicitud : 	compra.numeroSolicitud,
					 numeroOperacion : 	compra.numeroOperacion,
					 claveCia : 		compra.claveCia,
					 claveSucursal: 	compra.claveSucursal,
					 nombre : 			compra.nombre,	
					 fechaCompra : 		compra.fechaCompra,
					 tipoPromocion : 	compra.tipoPromocion,
					 clasificador : 	compra.clasificador,
					 fechaProxPago : 	compra.fechaProxPago,
					 primerCredito : 	compra.primerCredito,
					 status : 			compra.status,
					 importeCedido : 	compra.importeCedido,
					 ivaCapital	: 		compra.ivaCapital,
					 ivaDiferido : 		compra.ivaDiferido,
					 ivaIntereses : 	compra.ivaIntereses,
					 importeDescuento : compra.importeDescuento,
					 pagoTienda : 		compra.pagoTienda,
					 reserva : 			compra.reserva,
					 netoPagado : 		compra.netoPagado,
					 importeIntereses : compra.importeIntereses,
					 cesion : 			compra.cesion,
					 prestamo : prestamo,
				).save(failOnError:true)
				prestamo.estatusSolicitud = PrestamoEstatus.COMPRADA
				//LINEA TEMPORAL PARA PRUEBAS WS DE CR
				x++
			}		

		} catch (ClientException e) {
			throw new PrestamoServiceException(mensaje: "Se genero un plobema de comunicación con Crédito Real.")
		}
		return true
    }


    Boolean carteraGeneradaDia(
    	String distribuidor,
    	String dia,
    	String mes,
    	String anio){

		if (dia.size()!=2){
			dia = "0${dia}"
		} 

		if (mes.size()!=2){
			mes = "0${mes}"
		} 
		String fecha = "$anio$mes$dia"
		log.info ("Fecha: "+fecha)

		String usuario = 'pruebaws'
		String password = 'ws1866_cr'

		try {
			Client cliente = new Client(PfinCatParametro.findByClaveMedio("SistemaMtn").pruebasClienteWsCr)

			//SE OBTIENE SI ESTAMOS TRABAJANDO EN UN AMBIENTE DE PRUEBAS
			Boolean pruebasWsCr = PfinCatParametro.findByClaveMedio("SistemaMtn").pruebasClienteWsCr
			//LINEA TEMPORAL PARA PRUEBAS WS DE CR
			Integer x = 1

			List<CarteraGeneradaDia> carteras=
				cliente.getCarteraGeneradaDia(distribuidor,fecha,usuario,password)

			for(CarteraGeneradaDia cartera: carteras){
				log.info "Nombre Solicitud: ${cartera.nombre}"
				Integer folioSolicitud 
				//VALIDA SI ESTAMOS EN EL AMBIENTE DE PRUEBAS DE LOS WS
				if (pruebasWsCr){
					//LINEAS TEMPORALES
					if (x==1){
						folioSolicitud = 34534	
					}else if(x==2){
						//ASEGURARSE TENER EL FOLIO SOLICITUD CON VALOR 
						//IGUAL A 1
						folioSolicitud = 1	
					}else{
						folioSolicitud = 3
					}
				}else{
					//AMBIENTE PRODUCTIVO CR
					//CLASIFICADOR EQUIVALE A CONSECUTIVO EN solicitudesDecididasDia
					String consecutivo = cartera.consecutivo
					PrestamoCrRespuesta solicitudDecididaDia = PrestamoCrRespuesta.findByConsecutivo(consecutivo)
					log.info ("Solicitud Decidida:"+solicitudDecididaDia)	
					if (!solicitudDecididaDia){
						log.info ("No encontro el registro de la solicitud decidida")
						throw new PrestamoServiceException(mensaje: "No se encontro el registro de la solicitud decidida")
					}else{
						folioSolicitud = solicitudDecididaDia.folio
					}
				}
				
				Prestamo prestamo = Prestamo.findByFolioSolicitud(folioSolicitud)
				log.info "Prestamo: ${prestamo}"
				
				new PrestamoCrCartera(
				     consecutivo : cartera.consecutivo,
					 claveCliente : cartera.claveCliente,
					 numeroOperacion : cartera.numeroOperacion, 
					 nombre : cartera.nombre, 
					 apePat : cartera.apePat, 
					 apeMat : cartera.apeMat, 
					 fechaNac : cartera.fechaNac, 
					 rfcCliente : cartera.rfcCliente, 
					 calleDom : cartera.calleDom, 
					 numIntDom : cartera.numIntDom, 
					 numExtDom : cartera.numExtDom, 
					 coloniaDom : cartera.coloniaDom, 
					 localidadDom : cartera.localidadDom, 
					 cveMunicipioDom : cartera.cveMunicipioDom, 
					 cveEstadoDom : cartera.cveEstadoDom, 
					 codPostalDom : cartera.codPostalDom, 
					 tipPropiedad : cartera.tipPropiedad, 
					 atgDomicilio : cartera.atgDomicilio, 
					 telDomicilio : cartera.telDomicilio, 
					 edoCivil : cartera.edoCivil, 
					 nomConyuge : cartera.nomConyuge, 
					 idePresupuestal : cartera.idePresupuestal, 
					 numPersonal : cartera.numPersonal, 
					 cenTrabajo : cartera.cenTrabajo, 
					 telTrabajo : cartera.telTrabajo, 
					 atgTrabajo : cartera.atgTrabajo, 
					 calleTrab : cartera.calleTrab, 
					 numExtTra : cartera.numExtTra, 
					 numIntTra : cartera.numIntTra, 
					 coloniaTra : cartera.coloniaTra, 
					 localidadTra : cartera.localidadTra, 
					 cveMunicipioTra : cartera.cveMunicipioTra, 
					 cveEstadoTra : cartera.cveEstadoTra, 
					 codPostalTra : cartera.codPostalTra, 
					 ingresoBruto : cartera.ingresoBruto, 
					 deducciones : cartera.deducciones, 
					 ingresoNeto : cartera.ingresoNeto, 
					 fecRegistro : cartera.fecRegistro, 
					 promocion : cartera.promocion, 
					 impCreditoSol : cartera.impCreditoSol, 
					 numParcialidades : cartera.numParcialidades, 
					 impDescuento : cartera.impDescuento, 
					 intDescuento : cartera.intDescuento, 
					 claveSucursal : cartera.claveSucursal, 
					 claveCia : cartera.claveCia, 
					 estatusDap : cartera.estatusDap, 
					 estatusSolicitud : cartera.estatusSolicitud, 
					 ref1Nombre : cartera.ref1Nombre, 
					 ref1ApePat : cartera.ref1ApePat, 
					 ref1ApeMat : cartera.ref1ApeMat, 
					 ref1Domicilio : cartera.ref1Domicilio, 
					 ref1Telefono : cartera.ref1Telefono, 
					 ref2Nombre : cartera.ref2Nombre, 
					 ref2ApePat : cartera.ref2ApePat, 
					 ref2ApeMat : cartera.ref2ApeMat, 
					 ref2Domicilio : cartera.ref2Domicilio, 
					 ref2Telefono : cartera.ref2Telefono, 
					 ref3Nombre : cartera.ref3Nombre, 
					 ref3ApePat : cartera.ref3ApePat, 
					 ref3ApeMat : cartera.ref3ApeMat, 
					 ref3Domicilio : cartera.ref3Domicilio, 
					 ref3Telefono : cartera.ref3Telefono, 
					 numAgente : cartera.numAgente, 
					 cveSupervisor : cartera.cveSupervisor, 
					 claveCesion : cartera.claveCesion, 
					 importeCedido : cartera.importeCedido, 
					 fechaDeCompra : cartera.fechaDeCompra, 
					 fechaDispersion : cartera.fechaDispersion,
					 prestamo : 		prestamo,
				).save(failOnError:true)

				//LINEA TEMPORAL PARA PRUEBAS WS DE CR
				x++
			}		

		} catch (ClientException e) {
			throw new PrestamoServiceException(mensaje: "Se genero un plobema de comunicación con Crédito Real.")
		}
		return true
    }

}