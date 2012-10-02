package com.sim.procesos;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

public class ServicioWsCreditoRealAutoriza implements JavaDelegate{
	
	public void execute(DelegateExecution execution) {
		
		String comentarios = (String)execution.getVariable("comentarios");
		comentarios = comentarios + " ,Credito Real Autorizo Proceso";
		System.out.println("Credito Real WS Proceso");
		execution.setVariable("comentarios", comentarios);
		
	}	

}
