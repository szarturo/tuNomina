package com.sim.credito

import mx.com.creditoreal.ws.client.Client;
import mx.com.creditoreal.ws.dto.Adicional;
import mx.com.creditoreal.ws.dto.Solicitud;
import mx.com.creditoreal.ws.exception.ClientException;

import com.sim.catalogo.SimCatDocumento

class PrestamoService {

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

    	
		Client client = null;
		/*
		try {
			//TRUE SIGNIFICA QUE ENVIA A UN WEBSERVICE DE CREDITO REAL EN UN AMBIENTE DE PRUEBAS
			client = new Client(true);
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

		Solicitud solicitud = new Solicitud();
		solicitud.setReferencia("${prestamo.folioSolicitud}"); //Numero de Referencia o Folio propia del distribuidor o dejar en blanco
        log.info "Referencia: " + solicitud.getReferencia()
		solicitud.setDistribuidor("9999"); //9999  Se asignara por CR
		solicitud.setSucursal("1111"); //1111 Se asignara por CR
		//EL VENDEDOR SE REFIEREN A MTN O EL VENDEDOR DE MTN
		solicitud.setVendedor(""); //Clave de Vendedor asignada. Dejar en blanco para pruebas
        log.info "¿Que enviar en Vendedor?"
		solicitud.setDap("0"); //Enviar un 0
		solicitud.setPercepciones(prestamo.percepcionesMensuales); //Percepciones mensuales del cliente
        log.info "Percepciones Mensuales: "+ solicitud.getPercepciones()
		solicitud.setDeducciones(prestamo.deduccionesMensuales); //Deducciones mensuales del cliente
        log.info "Deducciones Mensuales: "+ solicitud.getDeducciones()
		solicitud.setSolicitud(doctoSolicitud?.getBytes()); //Imagen binarizada de la Solictud de credito
		solicitud.setPagare(doctoPagare?.getBytes()); //Imagen binarizada del pagare
		solicitud.setIdentificacion(doctoIdentificacion?.getBytes()); //Imagen binarizada de la identificacion
		solicitud.setDocadA(doctoAdicionalA?.getBytes()); //Imagen binarizada de un documento adicional
		solicitud.setDocadB(doctoAdicionalB?.getBytes()); //Imagen binarizada de un documento adicional
		solicitud.setDocadC(doctoAdicionalC?.getBytes()); //Imagen binarizada de un documento adicional
		
		String consecutivo = "NO SE GENERO";
			
		/*	
		try {
			consecutivo = client.solicitudZell(solicitud);
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		System.out.println("Respuesta Credito Real Consecutivo: "+ consecutivo);
		
		prestamo.consecutivoCr = consecutivo

		if(documentosListaCr){
			log.info ("Existen mas de 6 documentos para enviar a CR")

			documentosListaCr.each{ documentoPrestamo ->
				
			    doctoAdicional = new File(path, documentoPrestamo.nombreArchivo)				

				Adicional adicional = new Adicional()
				adicional.setConsecutivo(consecutivo)
				adicional.setDocAd(doctoAdicional.getBytes())
				
				String respuesta = "No se genero respuesta"
				/*	
				try {
					respuesta = client.documentoAdicional(adicional);
				} catch (ClientException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
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
			
			/*
			try {
				Client client = new Client(true);
				respuesta = client.documentoAdicional(adicional);
			} catch (ClientException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			log.info("Respuesta para el documento ${doctoAdicional}: ${respuesta}")
		}else{
			respuesta = """
			No existe consecutivo, debe enviarse primero la solicitud a Credito Real
			"""
		}

		return "${documento.nombreArchivo} : ${respuesta}"
    }
}
