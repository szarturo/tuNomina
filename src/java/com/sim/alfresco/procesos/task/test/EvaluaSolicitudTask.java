package com.sim.alfresco.procesos.task.test;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.apache.chemistry.opencmis.client.api.Document;
import org.apache.poi.ss.usermodel.Cell;

import com.sim.alfresco.AlfrescoService;
import com.sim.alfresco.PoiHelper;

public class EvaluaSolicitudTask  implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		System.out.println(this.getClass().getName());
		
		//long ingresos= Long.parseLong((String) execution.getVariable("ingresos"));
		long loanAmount= Long.parseLong((String) execution.getVariable("montoPrestamo"));
		boolean creditCheck=(Boolean)execution.getVariable("creditCheck");
		
		AlfrescoService service = new AlfrescoService();
		//Document evaluacion=service.getDocument("9d081a36-420f-48ad-9297-a5fcafd700e4");
		Document evaluacion=service.getDocumentByPath("/Sites/tuNomina/creditos/evaluation.xlsx");
		PoiHelper helper = new PoiHelper();
		helper.openWorkbook(evaluacion);
    
		boolean foundMatch = false;
		boolean reachedEndOfRules = false;
		int rowCounter = 1;
		
		System.out.println("In EvaluaSolicitudTast  creditCheck:"+creditCheck +" row: "+rowCounter +"  helper: "+helper);
		while(foundMatch == false && reachedEndOfRules == false) {
			System.out.println(" row: "+rowCounter);
			
			Cell cell = helper.getCell(rowCounter, 0);
			if(cell == null) {
				reachedEndOfRules = true;
				
			} else if(creditCheck == helper.getBooleanCellValue(rowCounter, 0)) {
				
				System.out.println("Credit check es igual  row: "+ rowCounter);
				
				String loanAmountRule = helper.getStringCellValue(rowCounter, 1);
				if("N/A".equalsIgnoreCase(loanAmountRule)) {
					foundMatch = true;
				} else {
					int spaceIndex = loanAmountRule.indexOf(" ");
					String loanAmountRuleCompare = loanAmountRule.substring(0, spaceIndex);
					String loanAmountRuleValue = loanAmountRule.substring(spaceIndex + 1, loanAmountRule.length());
					
					System.out.println("loanAmountRuleCompare: "+ loanAmountRuleCompare + "   -> loanAmountRuleValue:"+ loanAmountRuleValue);
					
					if("<".equals(loanAmountRuleCompare)) {
						if(loanAmount < Long.valueOf(loanAmountRuleValue)) {
							foundMatch = true;
						}
					} else if("<=".equals(loanAmountRuleCompare)) {
						if(loanAmount <= Long.valueOf(loanAmountRuleValue)) {
							foundMatch = true;
						}
					} else if("=".equals(loanAmountRuleCompare)) {
						if(loanAmount == Long.valueOf(loanAmountRuleValue).longValue()) {
							foundMatch = true;
						}
					} else if(">".equals(loanAmountRuleCompare)) {
						if(loanAmount > Long.valueOf(loanAmountRuleValue)) {
							foundMatch = true;
						}
					} else if(">=".equals(loanAmountRuleCompare)) {
						if(loanAmount >= Long.valueOf(loanAmountRuleValue)) {
							foundMatch = true;
						}
					}
				}
			}
			
			if(foundMatch == false) {
				rowCounter++;
			}
		}
		
		if(foundMatch == false) {
			throw new ActivitiException("No match found in decision table");
		}

		System.out.println("evaluateStatus: "+ helper.getStringCellValue(rowCounter, 2));
		execution.setVariable("evaluateStatus", helper.getStringCellValue(rowCounter, 2));
    
	}

}
