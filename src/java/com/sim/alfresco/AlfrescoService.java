package com.sim.alfresco;

import java.io.ByteArrayInputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.chemistry.opencmis.client.api.CmisObject;
import org.apache.chemistry.opencmis.client.api.Document;
import org.apache.chemistry.opencmis.client.api.DocumentType;
import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.ItemIterable;
import org.apache.chemistry.opencmis.client.api.ObjectId;
import org.apache.chemistry.opencmis.client.api.ObjectType;
import org.apache.chemistry.opencmis.client.api.Policy;
import org.apache.chemistry.opencmis.client.api.QueryResult;
import org.apache.chemistry.opencmis.client.api.Repository;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.client.api.SessionFactory;
import org.apache.chemistry.opencmis.client.runtime.SessionFactoryImpl;
import org.apache.chemistry.opencmis.commons.PropertyIds;
import org.apache.chemistry.opencmis.commons.SessionParameter;
import org.apache.chemistry.opencmis.commons.data.Ace;
import org.apache.chemistry.opencmis.commons.data.ContentStream;
import org.apache.chemistry.opencmis.commons.definitions.PropertyDefinition;
import org.apache.chemistry.opencmis.commons.enums.BaseTypeId;
import org.apache.chemistry.opencmis.commons.enums.BindingType;
import org.apache.chemistry.opencmis.commons.enums.VersioningState;
import org.apache.chemistry.opencmis.commons.impl.dataobjects.ContentStreamImpl;

/**
 * 
 * Clase para facilitar el uso del cliente que se conecta a alfresco
 * 
 * @author cmendoza
 *
 */
public class AlfrescoService {
	
	//session.getObjectByPath("/Sites/tuNomina/creditos/"+idCliente+"/"+idCredito+"/"+fileName)
	
	private final String ALFRESCO_SERVER;
	private final String USERNAME;
	private final String PASSWORD;
	private static final String USERNAME_DEFAULT="admin";
	private static final String PASS_DEFAULT="admin";
	private static final String ALFRESCO_SERVER_DEFAULT="http://localhost:9090/alfresco/service/cmis";
	
	private Session session;
	
	public AlfrescoService(){
		this(USERNAME_DEFAULT, PASS_DEFAULT, ALFRESCO_SERVER_DEFAULT);
	}
	public AlfrescoService(String userName, String pass){
		this(userName, pass, ALFRESCO_SERVER_DEFAULT);
	}
	
	/**
	 * 
	 * @param userName Usuario de alfresco
	 * @param pass Password de alfresco
	 * @param server Servidor de alfresco
	 */
	public AlfrescoService(String userName, String pass, String server){
		USERNAME=userName;
		PASSWORD=pass;
		ALFRESCO_SERVER=server;
		session=createCmisSession();
	}
	
	
	/**
	 * Devuelve un documento o un Folder. Null si no encuentra nada.
	 * EJemplo /Sites/tuNomina/creditos/template.xls  o EJemplo /Sitios/tuNomina/imagenes
	 * @param path
	 * @return
	 */
	public CmisObject getByPath(String path){
		CmisObject o =(CmisObject)session.getObjectByPath(path);
		if(o==null){
			return null;
		}
		
		if(o instanceof Folder){
			return (Folder)o;
		}
		
		if(o instanceof Document){
			return (Document)o;
		}
		
		return null;
	}
	
	/**
	 * Obtiene un documento
	 * @param path  ruta del documento. EJemplo /Sites/tuNomina/creditos/template.xls 
	 * @return
	 */
	public Document getDocumentByPath(String path){
		try{
			return (Document) session.getObjectByPath(path);
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Obtiene un documento por ID -> workspace://SpacesStore/ +  Id
	 * @param id
	 * @return
	 */
	public Document getDocument(String id){
		return (Document) session.getObject("workspace://SpacesStore/"+ id);
	}
	
	/**
	 * Obtiene un documento por ID -> workspace://SpacesStore/ +  Id
	 * @param workspace
	 * @return
	 */
	public Document getDocumentByWorkspaceId(String workspace){
		return (Document) session.getObject(workspace);
	}
	
	
	/**
	 * Obtiene todas las versiones de un documento
	 * @param idCliente
	 * @param idCredito
	 * @param fileName
	 * @return
	 */
	public List<Document> getAllVersionsFromDocumento(String idCliente, String idCredito, String fileName){
		Folder creditos= getRootFolder();
		Folder folderCliente=getFolderCliente(creditos, idCliente);
		Folder folderCredito =getFolderCredito(folderCliente, idCredito);
		
		Document document=getIdFile(folderCredito,fileName);
		if(document==null){
			return new ArrayList<Document>();
		}
		
		return document.getAllVersions();
	}
	
	/**
	 * Obtiene lista de documentos del credito de un cliente
	 * @param idCliente
	 * @param idCredito
	 * @return
	 */
	public List<Document> getDocumentosByCredito(String idCliente, String idCredito){
		Folder creditos= getRootFolder();
		Folder folderCliente=getFolderCliente(creditos, idCliente);
		Folder folderCredito =getFolderCredito(folderCliente, idCredito);
		
		List<Document> documentos=new ArrayList<Document>();
		
		for(CmisObject cmisObject: folderCredito.getChildren()){
			if(cmisObject instanceof Document){
				documentos.add((Document) cmisObject);
			}
		}
		
		return documentos;
	}
	
	public Object updateFile(String id, ContentStream stream){
		Document doc = (Document) session.getObject("workspace://SpacesStore/"+ id);
		Document pwc = (Document) session.getObject(doc.checkOut());

		try {
			pwc.setContentStream(stream, true);
			return pwc.checkIn(true, null, pwc.getContentStream(),"Nueva Version desde AlfrescoService");
			
		} catch (Exception e) {
			e.printStackTrace();
			pwc.cancelCheckOut();
		}
		return null;
	}
	
	
	/**
	 * Almacena o actualiza documento.
	 * @param fileName Si no es nuevo el nombre debe coincidir con el almacenado en alfresco
	 * @param bytesFile bytes del documento
	 * @param idCliente 
	 * @param idCredito
	 * @param usuario
	 * @return
	 */
	public boolean saveFile(String fileName, byte[] bytesFile, String mimeType,  String idCliente, String idCredito, String usuario){
		Folder creditos= getRootFolder();
		Folder folderCliente=getFolderCliente(creditos, idCliente);
		Folder folderCredito =getFolderCredito(folderCliente, idCredito);
		
		Document oldFile=getIdFile(folderCredito, fileName);
		Object result=null;
		
		Map<String, Object> properties = new HashMap<String, Object>();
		properties.put(PropertyIds.OBJECT_TYPE_ID,BaseTypeId.CMIS_DOCUMENT.value());
		//properties.put(PropertyIds.NAME, fileName);
		properties.put(PropertyIds.CONTENT_STREAM_MIME_TYPE, mimeType);
		// no funciona
		//properties.put(PropertyIds.CHECKIN_COMMENT, "Nueva version de "+ fileName);
		
		try{
			if(oldFile!=null){
				Document checkFile=(Document) session.getObject(oldFile.checkOut());
				
//				checkFile.updateProperties(properties,true);
				checkFile.setContentStream(getStream(fileName,bytesFile,mimeType), true);
				
				result=checkFile.checkIn(true, null, checkFile.getContentStream(), "Modifica ["+usuario+"]");
				//result=oldFile.checkIn(true, null, getStream(fileName, bytesFile, mimeType)  ,"n/a");
			}else{
				Map<String, Object> docProps = new HashMap<String, Object>();
			    docProps.put(PropertyIds.NAME, fileName);
			    docProps.put(PropertyIds.OBJECT_TYPE_ID, DocumentType.DOCUMENT_BASETYPE_ID);
			    
			    ByteArrayInputStream in = new ByteArrayInputStream(bytesFile);
			    ContentStream contentStream = session.getObjectFactory().createContentStream(fileName, bytesFile.length, mimeType, in);
			    
			    //result = session.createDocument(docProps, folderCredito, contentStream, null, null, null, null);
			    result = session.createDocument(docProps, folderCredito, null, VersioningState.CHECKEDOUT, null, null, null);
			    
			    Document ch=(Document) session.getObject((ObjectId) result);
			    
			    ch.checkIn(true, properties, contentStream, "Crea documento ["+usuario+"]");
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			oldFile.cancelCheckOut();
		}
		
		
		
		return result!=null;
	}
	
	public ContentStream getStream(String filename, byte[] bytesFile, String mimeType){
		return new ContentStreamImpl(filename,BigInteger.valueOf(bytesFile.length),  mimeType , new ByteArrayInputStream(bytesFile));
	}
	
	private Document getIdFile(Folder folder,String fileName){
		if(folder==null){
			return null;
		}
		
		for(CmisObject cmisObject: folder.getChildren()){
			if(cmisObject instanceof Document){
				Document document = (Document) cmisObject;
				if(document.getName()!=null && document.getName().trim().equalsIgnoreCase(fileName)){
					return document;
				}
			}
		}
		return null;
	}
	
	private Folder getFolderCredito(Folder folderCliente, String credito){
		if(folderCliente==null){
			return null;
		}
		
		for(CmisObject cmisObject: folderCliente.getChildren()){
			if(! (cmisObject instanceof Document)){
				Folder f = (Folder)cmisObject;
				if(f.getName()!=null && f.getName().equals(credito)){
					return f;
				}
			}
		}
		
		return createFolderCredito(folderCliente, credito);
	}
	
	private Folder createFolderCredito(Folder folderCliente, String idCredito){
		Folder folderCredito=getFolder(folderCliente, idCredito);
		
		if(folderCredito==null){
			Map<String, Object> properties = new HashMap<String, Object>();
			properties.put(PropertyIds.OBJECT_TYPE_ID,BaseTypeId.CMIS_FOLDER.value());
			properties.put(PropertyIds.NAME, idCredito);
			
			
			folderCredito=folderCliente.createFolder(properties);
			
			
		}
		return folderCredito;
	}
	
	private Folder getFolderCliente(Folder credito, String idCliente){
		
		Folder folderCliente=getFolder(credito, idCliente);
		
		if(folderCliente==null){
			Map<String, Object> properties = new HashMap<String, Object>();
			properties.put(PropertyIds.OBJECT_TYPE_ID,BaseTypeId.CMIS_FOLDER.value());
			properties.put(PropertyIds.NAME, idCliente);
			folderCliente=credito.createFolder(properties);
			
		}
		return folderCliente;
	}
	
	
	private Folder getRootFolder(){
		return getFolderAnyWhere("creditos");
	}
	
	
	private Session createCmisSession() {
		SessionFactory sessionFactory = SessionFactoryImpl.newInstance();
		Map<String, String> parameter = new HashMap<String, String>();
		parameter.put(SessionParameter.USER, USERNAME);
		parameter.put(SessionParameter.PASSWORD, PASSWORD);
		parameter.put(SessionParameter.ATOMPUB_URL, ALFRESCO_SERVER);
		parameter.put(SessionParameter.BINDING_TYPE,BindingType.ATOMPUB.value());
		
		Repository repository = sessionFactory.getRepositories(parameter).get(0);
		return repository.createSession();
	}
	
	public Folder getFolder(Folder folder, String folderName) {
		for(CmisObject o:folder.getChildren()){
			if(o instanceof Folder){
				Folder f = (Folder)o;
				if(f.getName()!=null && f.getName().equalsIgnoreCase(folderName)){
					return f;
				}
			}
		}
		return null;
	}
	
	
	public Folder getFolderAnyWhere(String folderName) {
		
		ObjectType type = session.getTypeDefinition("cmis:folder");
		PropertyDefinition<?> objectIdPropDef = type.getPropertyDefinitions()
				.get(PropertyIds.OBJECT_ID);
		String objectIdQueryName = objectIdPropDef.getQueryName();
		ItemIterable<QueryResult> results = session.query("SELECT * FROM cmis:folder WHERE cmis:name='" + folderName+ "'", false);
		
		if(results==null){
			return null;
		}
		
		for (QueryResult qResult : results) {
			String objectId = qResult
					.getPropertyValueByQueryName(objectIdQueryName);
			return (Folder) session.getObject(session.createObjectId(objectId));
		}
		return null;
	}
	

	public ObjectId createFolder(ObjectId parentId, String rootFolder) {
		
		ObjectId mainFolderID = null;

		Map<String, Object> properties = new HashMap<String, Object>();
		properties.put(PropertyIds.OBJECT_TYPE_ID,BaseTypeId.CMIS_FOLDER.value());
		properties.put(PropertyIds.NAME, rootFolder);

		List<Ace> addAces = new LinkedList<Ace>();
		List<Ace> removeAces = new LinkedList<Ace>();
		List<Policy> policies = new LinkedList<Policy>();

		try {

			mainFolderID = session.createFolder(properties, parentId, policies,addAces, removeAces);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return mainFolderID;
	}
	
}
