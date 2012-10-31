package com.sim.alfresco.procesos.task.test;

import java.io.ByteArrayOutputStream;
import java.net.URL;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import com.sim.alfresco.AlfrescoService;

public class CreaSolicitudTask implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		System.out.println("CreaSolicitudTask");
		
		com.itextpdf.text.Document pdf = new com.itextpdf.text.Document();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(1024);
		PdfWriter writer = PdfWriter.getInstance(pdf, outputStream);
		writer.setBoxSize("art", new Rectangle(36, 54, 559, 788));
    HeaderFooter event = new HeaderFooter();
    writer.setPageEvent(event);
    pdf.addAuthor("Loan Sharks");
    pdf.addTitle("Subject: loan request");
    pdf.addSubject("Reference number 100898");
    pdf.open();
    
    
    AlfrescoService service = new AlfrescoService();
    
     
    org.apache.chemistry.opencmis.client.api.Document document=(org.apache.chemistry.opencmis.client.api.Document) 
    service.getDocumentByWorkspaceId("workspace://SpacesStore/291f4ec0-e203-475d-9cc8-bbec2624ac37");
    
 // Verificar que la imagen exista
    if(document!=null && document.getContentStream()!=null && document.getContentStream().getLength()>0){
    	 byte[] bytes= new byte[(int)document.getContentStream().getLength()];
    	    document.getContentStream().getStream().read(bytes);
    	    Image image = Image.getInstance(bytes);
    	    pdf.add(image);
    }
		
		pdf.add(new Paragraph(" "));
		pdf.add(new Paragraph(" "));
		pdf.add(new Paragraph(" "));
		pdf.add(new Paragraph("Dear Mr/Mrs " + execution.getVariable("nombre") + ","));
		pdf.add(new Paragraph(" "));
		
		String status=(String)execution.getVariable("evaluateStatus");
		System.out.println("Status : " +status);
		if("approved".equalsIgnoreCase(status) ||
				"needs manager approval".equalsIgnoreCase(status)) {
			
			pdf.add(new Paragraph("After analysis regarding your loan request we are happy to inform you that your loan request for $"
					+ execution.getVariable("montoPrestamo") + " is approved. Enclosed, you'll find all the details regarding the next steps in the process of your loan request."));
		} else {
			pdf.add(new Paragraph("After analysis regarding your loan request we regret to inform you that your loan request for $"
					+ execution.getVariable("montoPrestamo")  + " is denied."));
		}
		pdf.add(new Paragraph(" "));
		pdf.add(new Paragraph(" "));
		pdf.add(new Paragraph(" "));
		pdf.add(new Paragraph("With regards,"));
		pdf.add(new Paragraph(" "));
		pdf.add(new Paragraph(" "));
		pdf.add(new Paragraph("John Shark"));
		pdf.add(new Paragraph("Manager Loan Sharks"));
		pdf.close();
		
		
		service.saveFile("solicitud.pdf", outputStream.toByteArray(), "application/pdf", (String)execution.getVariable("idCliente"), (String)execution.getVariable("idCredito"));
		
		System.out.println("Creado !");
	}
	
	/** Inner class to add a header and a footer. */
	  static class HeaderFooter extends PdfPageEventHelper {

	    public void onEndPage (PdfWriter writer, Document document) {
	      Rectangle rect = writer.getBoxSize("art");
	      ColumnText.showTextAligned(writer.getDirectContent(),
	              Element.ALIGN_RIGHT, new Phrase("Loan Sharks"),
	              rect.getRight(), rect.getTop(), 0);
	      
	      ColumnText.showTextAligned(writer.getDirectContent(),
	              Element.ALIGN_RIGHT, new Phrase("4543 1st Street"),
	              rect.getRight(), rect.getTop() - 15, 0);
	      
	      ColumnText.showTextAligned(writer.getDirectContent(),
	              Element.ALIGN_RIGHT, new Phrase("Bay City, 38989 "),
	              rect.getRight(), rect.getTop() - 30, 0);
	      
	      ColumnText.showTextAligned(writer.getDirectContent(),
	              Element.ALIGN_RIGHT, new Phrase("E-mail: info@loansharks.com"),
	              rect.getRight(), rect.getTop() - 60, 0);
	    }
	  }
}
	
