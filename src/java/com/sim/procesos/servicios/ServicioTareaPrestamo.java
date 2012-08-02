package com.sim.procesos.servicios;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

public class ServicioTareaPrestamo implements JavaDelegate {
	
	public void execute(DelegateExecution execution) {
		
		Boolean bCreditoViable = (Boolean)execution.getVariable("creditoViable");
		System.out.println("Servicio Credito Viable: "+bCreditoViable);
		execution.setVariable("prestamoAutorizado", bCreditoViable);
		
	}	

}
