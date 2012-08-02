package vacationRequest

import org.activiti.engine.delegate.JavaDelegate
import org.activiti.engine.delegate.DelegateExecution

 

class MailSender implements JavaDelegate {
  private static String TO = "emailTo";
  
  public void execute(DelegateExecution execution) throws Exception {
    String to = (String) execution.getVariable(TO)
    	println "*** Email message sent to ${to}"
  }
}
