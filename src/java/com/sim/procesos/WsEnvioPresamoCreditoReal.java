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
		
		/*
		String comentarios = (String)execution.getVariable("comentarios");
		comentarios = comentarios + " ,Envio de prestamo a Credito Real";
		System.out.println("Credito Real Envio Credito. "+comentarios);
		execution.setVariable("comentarios", comentarios);
		*/
		
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
		solicitud.setReferencia(referencia);
		solicitud.setDistribuidor("9999");
		solicitud.setSucursal("1111");
		solicitud.setVendedor("");
		solicitud.setDap("0");
		solicitud.setPercepciones(10D);
		solicitud.setDeducciones(1D);
		solicitud.setSolicitud("2".getBytes());
		solicitud.setPagare("1".getBytes());
		solicitud.setIdentificacion("1".getBytes());
		solicitud.setDocadA("1".getBytes());
		solicitud.setDocadB("1".getBytes());
		solicitud.setDocadC("1".getBytes());
		
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

