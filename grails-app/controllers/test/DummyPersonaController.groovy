package test

import org.apache.chemistry.opencmis.client.api.CmisObject;
import org.apache.chemistry.opencmis.client.api.Document;
import org.apache.chemistry.opencmis.client.api.Folder;
import org.springframework.dao.DataIntegrityViolationException

import com.sim.alfresco.AlfrescoService;

class DummyPersonaController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [dummyPersonaInstanceList: DummyPersona.list(params), dummyPersonaInstanceTotal: DummyPersona.count()]
    }

    def create() {
        [dummyPersonaInstance: new DummyPersona(params)]
    }

    def save() {
        def dummyPersonaInstance = new DummyPersona(params)
        if (!dummyPersonaInstance.save(flush: true)) {
            render(view: "create", model: [dummyPersonaInstance: dummyPersonaInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'dummyPersona.label', default: 'DummyPersona'), dummyPersonaInstance.id])
        redirect(action: "show", id: dummyPersonaInstance.id)
    }

    def show(Long id) {
        def dummyPersonaInstance = DummyPersona.get(id)
        if (!dummyPersonaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'dummyPersona.label', default: 'DummyPersona'), id])
            redirect(action: "list")
            return
        }

        [dummyPersonaInstance: dummyPersonaInstance]
    }

    def edit(Long id) {
        def dummyPersonaInstance = DummyPersona.get(id)
        if (!dummyPersonaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'dummyPersona.label', default: 'DummyPersona'), id])
            redirect(action: "list")
            return
        }
		
		List<Document> documentos = new ArrayList<Document>();
		AlfrescoService service = new AlfrescoService();
		Object o=service.getByPath("/Sites/tuNomina/imagenes");
		if(o!=null){
			Folder folder = (Folder)o;
			
			for(CmisObject cmisObject: folder.getChildren()){
				if(cmisObject instanceof Document){
					documentos.add((Document) cmisObject);
				}
			}
			
			request.putAt("documentos", documentos);
		}

        [dummyPersonaInstance: dummyPersonaInstance]
    }

    def update(Long id, Long version) {
        def dummyPersonaInstance = DummyPersona.get(id)
        if (!dummyPersonaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'dummyPersona.label', default: 'DummyPersona'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (dummyPersonaInstance.version > version) {
                dummyPersonaInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'dummyPersona.label', default: 'DummyPersona')] as Object[],
                          "Another user has updated this DummyPersona while you were editing")
                render(view: "edit", model: [dummyPersonaInstance: dummyPersonaInstance])
                return
            }
        }

        dummyPersonaInstance.properties = params

        if (!dummyPersonaInstance.save(flush: true)) {
            render(view: "edit", model: [dummyPersonaInstance: dummyPersonaInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'dummyPersona.label', default: 'DummyPersona'), dummyPersonaInstance.id])
        redirect(action: "show", id: dummyPersonaInstance.id)
    }

    def delete(Long id) {
        def dummyPersonaInstance = DummyPersona.get(id)
        if (!dummyPersonaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'dummyPersona.label', default: 'DummyPersona'), id])
            redirect(action: "list")
            return
        }

        try {
            dummyPersonaInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'dummyPersona.label', default: 'DummyPersona'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'dummyPersona.label', default: 'DummyPersona'), id])
            redirect(action: "show", id: id)
        }
    }
}
