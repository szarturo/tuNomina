package com.sim.procesos;

import mx.com.creditoreal.ws.client.Client;
import mx.com.creditoreal.ws.dto.Adicional;
import mx.com.creditoreal.ws.dto.Solicitud;
import mx.com.creditoreal.ws.exception.ClientException;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

import com.sim.credito.Prestamo;

public class WsEnvioPresamoCreditoReal implements JavaDelegate{
	
	//SERVICIO QUE ENVIA LA SOLICITUD DE UN PRESTAMO A CREDITO REAL
	public void execute(DelegateExecution execution) {
		
		
		String comentarios = (String)execution.getVariable("comentarios");
		comentarios = comentarios + " ,Envio de prestamo a Credito Real";
		System.out.println("Credito Real Envio Credito. "+comentarios);
		execution.setVariable("comentarios", comentarios);
		
		/* CODIGO QUE ENVIA SOLICITUD Y DOCUMENTOS A CREDITO REAL
		String referencia = (String)execution.getVariable("folioSolicitud");
		System.out.println("REFERENCIA Envio CR: "+referencia);
		
		Client client = null;
		try {
			//TRUE SIGNIFICA QUE ENVIA A UN WEBSERVICE DE CREDITO REAL EN UN AMBIENTE DE PRUEBAS
			client = new Client(true);
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Solicitud solicitud = new Solicitud();
		solicitud.setReferencia(referencia); //Numero de Referencia o Folio propia del distribuidor o dejar en blanco
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
		
		//EN CASO DE EXISTIR MAS DE 6 DOCUMENTOS DEBEN SER ENVIADOS EN UN CICLO
		//DEPENDIENDO DEL NUMERO DE DOCUMENTOS
		
		Adicional adicional = new Adicional();
		adicional.setConsecutivo(consecutivo);
		adicional.setDocAd("3".getBytes());
		
		boolean respuesta = false;
		try {
			respuesta = client.documentoAdicional(adicional);
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("**CRE** Respuesta envio documento adicional: "+ respuesta);		
		*/
	}	

}

