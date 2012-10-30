package com.sim.alfresco;

import java.util.List;

import org.apache.chemistry.opencmis.client.api.Document;

public class AlfrescoApp {


	static String idCliente="clientex1";
	static	String idCredito="credito1";
	
	//609cc263-09e9-4d54-b2ea-f42d93f95899
	public static void main(String[] args) {
		
		AlfrescoService service = new AlfrescoService();

//		service.saveFile("archivo1.txt", "XDDDDDDD los bytes".getBytes(),"text/plain", idCliente, idCredito);
//		service.saveFile("archivo2.txt", "xD los bytes".getBytes(), "text/plain", idCliente, idCredito);
		
		int i=1;
		for(i=1; i<=10; i++){
			service.saveFile("archivo1.txt", ("archivo "+i ).getBytes(),"text/plain", idCliente, idCredito);
			service.saveFile("archivo2.txt", "xD los bytes".getBytes(), "text/plain", idCliente, idCredito);
		}
		System.out.println(" I: "+i);
		
		List<Document> documentos=service.getDocumentosByCredito(idCliente, idCredito);
		System.out.println("\n\n Todos los documentos ");
		for(Document documento: documentos){
			System.out.println(documento.getName() +"  " + documento.getVersionLabel() +" " + documento.getCheckinComment() +" -> " +documento.getId());
		}
		
		System.out.println("\n\n ALL Versions ");
		documentos=service.getAllVersionsFromDocumento(idCliente, idCredito, "archivo1.txt");
		for(Document documento: documentos){
			System.out.println(documento.getName() +"  " + documento.getVersionLabel() +" " + documento.getCheckinComment());
		}

	}

}
