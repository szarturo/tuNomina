package com.sim.alfresco

import org.apache.chemistry.opencmis.client.api.Document

class ViewImageCompareController {
	
	private static final String CUSTOM_HEADER="showMenu=false";

    def index() { 
		//String imagen = params.get("imagen");
		String info= params.get("info");
		
		if(info.contains("?")){
			info=request.getContextPath()+getInfo(info)+"&"+CUSTOM_HEADER;
		}else{
			info=request.getContextPath()+getInfo(info)+"?"+CUSTOM_HEADER;
		}
		
		request.putAt("info", info);
	}
	
	private String getInfo(String info){
		if(info.startsWith("/")){
			return info;
		}
		return "/"+info;
		
	}
	
	def loadImagen() {
		String imagen = params.get("imagen");
		AlfrescoService service = new AlfrescoService();
		
		Document document=service.getDocumentByWorkspaceId(imagen);
		
		response.setHeader('Content-length', document.getContentStream().getStream().getBytes().size() +"");
		response.contentType = getMimeType(document.getName());
		response.outputStream << document.getContentStream().getStream().getBytes();
		response.outputStream.flush();
		
	}
	
	private String getMimeType(String name){
		if(name.endsWith(".gif")){
			return "image/gif";
		}
		if(name.endsWith(".jpg") || name.endsWith(".jpeg")|| name.endsWith(".jpe")){
			return "image/jpeg";
		}
		if(name.endsWith(".png")){
			return "image/png";
		}
	}
}

