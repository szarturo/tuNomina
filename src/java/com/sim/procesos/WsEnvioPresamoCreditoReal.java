package com.sim.procesos;

import mx.com.creditoreal.ws.client.Client;
import mx.com.creditoreal.ws.dto.Adicional;
import mx.com.creditoreal.ws.dto.Solicitud;
import mx.com.creditoreal.ws.exception.ClientException;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

public class WsEnvioPresamoCreditoReal implements JavaDelegate{
	
	public void execute(DelegateExecution execution) {
		
		String comentarios = (String)execution.getVariable("comentarios");
		comentarios = comentarios + " ,Envio de prestamo a Credito Real";
		System.out.println("Credito Real Envio Credito. "+comentarios);
		execution.setVariable("comentarios", comentarios);
		
		Client client = null;
		try {
			client = new Client(true);
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Solicitud solicitud = new Solicitud();
		solicitud.setReferencia("3");
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
		System.out.println("consecutivo: "+ consecutivo);
		
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
		System.out.println("Respuesta: "+ respuesta);		
		
		
	}	

}

