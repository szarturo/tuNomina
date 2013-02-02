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
		comentarios = comentarios + " Envio de prestamo a Credito Real";
		System.out.println("Credito Real Envio Credito. "+comentarios);
		execution.setVariable("comentarios", comentarios);

	}	

}

