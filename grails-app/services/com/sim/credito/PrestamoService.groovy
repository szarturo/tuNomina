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

		File path = new File("${System.getProperty('user.home')}/Documents/tuNomina/documentosCredito/${prestamo.cliente.id}/${prestamo.folioSolicitud}")
		
		documentos.each{documentoPrestamo ->

			switch ( documentoPrestamo.documento ) {
			    case SimCatDocumento.findByClaveDocumento('SOLICITUD_PRESTAMO'):
			    	doctoSolicitud = new File(path, documentoPrestamo.nombreArchivo)
			    	log.info ("***: "+doctoSolicitud)
			    break
			    case SimCatDocumento.findByClaveDocumento('PAGARE_PRESTAMO'):
			    	log.info "Pagare: "+documentoPrestamo.nombreArchivo
			    break
			    case SimCatDocumento.findByClaveDocumento('IDENTIFICACION_PRESTAMO'):
			    	log.info "Identificacion: "+documentoPrestamo.nombreArchivo
			    break
			    default:
			    	log.info "Otro: "+documentoPrestamo.nombreArchivo
			}
    	}

    	
		Client client = null;
		try {
			//TRUE SIGNIFICA QUE ENVIA A UN WEBSERVICE DE CREDITO REAL EN UN AMBIENTE DE PRUEBAS
			client = new Client(true);
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
		solicitud.setSolicitud(doctoSolicitud.getBytes()); //Imagen binarizada de la Solictud de credito
		solicitud.setPagare("1".getBytes()); //Imagen binarizada del pagare
		solicitud.setIdentificacion("1".getBytes()); //Imagen binarizada de la identificacion
		solicitud.setDocadA("1".getBytes()); //Imagen binarizada de un documento adicional
		solicitud.setDocadB("1".getBytes()); //Imagen binarizada de un documento adicional
		solicitud.setDocadC("1".getBytes()); //Imagen binarizada de un documento adicional
		
		String consecutivo = null;
		
		try {
			consecutivo = client.solicitudZell(solicitud);
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Respuesta Credito Real Consecutivo: "+ consecutivo);
		
        return consecutivo

    }
}
