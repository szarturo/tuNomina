package com.sim.credito

import mx.com.creditoreal.ws.client.Client;
import mx.com.creditoreal.ws.dto.Adicional;
import mx.com.creditoreal.ws.dto.Solicitud;
import mx.com.creditoreal.ws.exception.ClientException;

class PrestamoService {

    def envioSolicitudCreditoReal() {
    	log.info ("Envio Service Solicitud a Credito Real")

		Client client = null;
		try {
			//TRUE SIGNIFICA QUE ENVIA A UN WEBSERVICE DE CREDITO REAL EN UN AMBIENTE DE PRUEBAS
			client = new Client(true);
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Solicitud solicitud = new Solicitud();
		solicitud.setReferencia("123"); //Numero de Referencia o Folio propia del distribuidor o dejar en blanco
		solicitud.setDistribuidor("9999"); //9999  Se asignara por CR
		solicitud.setSucursal("1111"); //1111 Se asignara por CR
		//EL VENDEDOR SE REFIEREN A MTN O EL VENDEDOR DE MTN
		solicitud.setVendedor(""); //Clave de Vendedor asignada. Dejar en blanco para pruebas 
		solicitud.setDap("0"); //Enviar un 0
		solicitud.setPercepciones(10D); //Percepciones mensuales del cliente
		solicitud.setDeducciones(1D); //Deducciones mensuales del cliente
		solicitud.setSolicitud("2".getBytes()); //Imagen binarizada de la Solictud de credito
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
		System.out.println("***CR*** Respuesta Credito Real Consecutivo: "+ consecutivo);

    }
}
