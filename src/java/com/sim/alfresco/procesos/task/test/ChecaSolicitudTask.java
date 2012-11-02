package com.sim.alfresco.procesos.task.test;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.apache.chemistry.opencmis.client.api.Document;

import com.sim.alfresco.AlfrescoService;
import com.sim.alfresco.PoiHelper;


public class ChecaSolicitudTask  implements JavaDelegate {

	private static final String EXCEL_MIMETYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		System.out.println(this.getClass().getName());
		
		AlfrescoService service = new AlfrescoService();
		
		//Document template=service.getDocument("bf9fc57e-72a9-437a-a4e3-86b94b2d519d");
		Document template=service.getDocumentByPath("/Sites/tuNomina/creditos/template.xlsx");
		PoiHelper helper = new PoiHelper();
		helper.openWorkbook(template);
		
	    // Set customer name
			helper.setCellValue((String)execution.getVariable("nombre"), 0, 1, true);
	    
	    // Set email address
			helper.setCellValue("n/a", 1, 1, true);
	    
	    // Set income cell
			helper.setCellValue((String)execution.getVariable("ingresos"), 4, 0, false);
	    
	    // Set loan amount cell
	    helper.setCellValue((String)execution.getVariable("montoPrestamo"), 4, 1, false);
	    
	    // Evaluate loan amount double formula cell
	    helper.evaluateFormulaCell(4, 2);
	    
	    // Get credit check formula cell
	    helper.evaluateFormulaCell(6, 1);
	    

	    // se cambia la implementacion de la formula para que no devuelva un Boolean.
	    boolean creditCheck=helper.getCell(6, 1).getNumericCellValue()>0;
	    
	    System.out.println("Evaluacion de la formula: "+ creditCheck);
	    execution.setVariable("creditCheck", creditCheck);

	    helper.recalculateSheetAfterOpening();

	    String usuario= (String)execution.getVariable("username");
	    
	    service.saveFile("tempexcel.xls", helper.getBytes(), EXCEL_MIMETYPE, (String)execution.getVariable("idCliente"), (String)execution.getVariable("idCredito"),usuario);
	    
		
//		for(String s:execution.getVariableNames()){
//			System.out.println("VN : "+s);
//		}
		
		
	}


}
