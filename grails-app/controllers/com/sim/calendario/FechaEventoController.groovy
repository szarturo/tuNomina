package com.sim.calendario

import java.text.SimpleDateFormat

import com.sim.catalogo.SimCatEvento;
import com.sim.entidad.EntDependencia

import org.springframework.dao.DataIntegrityViolationException

class FechaEventoController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
		render {
			div(id:"exito", params.get("content") + " Almacenado. ")
		}
    }
	

	def saveEvento(){
		System.out.println("dependenciaid: "+params.get("dependenciaid"));
		System.out.println("dependencia: "+params.get("dependencia"));
		System.out.println("eventoSave:" + params.get("eventoSave"));
		System.out.println("fechaSave:" + params.get("fechaSave"));
		System.out.println("idRemove:"+ params.get("idRemove"));
		System.out.println("nsave:"+ params.get("nsave"));
		System.out.println("nremove:"+ params.get("nremove"));
		
		String dependenciaid=params.get("dependenciaid");
		String[] eventoSave=params.get("eventoSave");
		String[] fechaSave=(String[])params.get("fechaSave");
		String[] idRemove=(String[])params.get("idRemove");
		
		if(params.get("nsave").equals("1")){
			System.out.println("NSAVE IS 1");
			eventoSave=new String[1];
			fechaSave=new String[1];
			eventoSave[0]=params.get("eventoSave");
			fechaSave[0]=params.get("fechaSave");
		}
		if(params.get("nremove").equals("1")){
			System.out.println("NRemove IS 1");
			idRemove=new String[1];
			idRemove[0]=params.get("idRemove");
		}
		
		
		def dependenciaInstance = EntDependencia.get(dependenciaid);
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		
		if(eventoSave!=null){
			for(int i=0; i<eventoSave.length; i++){
				try{
					def fechaEventoInstance = FechaEvento.create();
					fechaEventoInstance.setDependencia(dependenciaInstance);
					
					fechaEventoInstance.setFecha(format.parse(fechaSave[i]))
					
					def query = SimCatEvento.where {
						evento in eventoSave[i]
					 }
					def eventoInstance = query.find();
					
					fechaEventoInstance.setEvento(eventoInstance);
					
					System.out.println("save "+i);
					
					fechaEventoInstance.save(flush: true);
				
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		
		if(idRemove!=null){
			for(int i=0; i<idRemove.length; i++){
				def fechaEventoInstance= FechaEvento.get(idRemove[i]);
				
				try{
					
					System.out.println("delete "+i);
					fechaEventoInstance.delete(flush: true);
				}catch(Exception e){
					e.printStackTrace();
				}
				
			}
		}
		
		
		redirect(action: "list", params: params)
	}
	
    def list(Integer max) {
		
		params.max = 100;
		request.putAt("eventos", SimCatEvento.list(params));
		
		EntDependencia selectDependencia=null;
		def dependencias= EntDependencia.list(params);
		for(EntDependencia dependencia:dependencias){
			selectDependencia=dependencia;
			if(dependencia.getNombreDependencia().equalsIgnoreCase(params.get("dependencia"))){
				selectDependencia=dependencia;
				break ;
			}
		}
		
		request.putAt("dependencia", selectDependencia);
		request.putAt("dependencias", dependencias);
		
		params.max = Math.min(max ?: 10, 1000)
		
		def query = FechaEvento.where {
			dependencia.nombreDependencia in selectDependencia.getNombreDependencia()
		 }
		
		[fechaEventoInstanceList:  query.findAll(), fechaEventoInstanceTotal: FechaEvento.count()]
		
    }

    def create() {
        [fechaEventoInstance: new FechaEvento(params)]
    }

    def save() {
        def fechaEventoInstance = new FechaEvento(params)
        if (!fechaEventoInstance.save(flush: true)) {
            render(view: "create", model: [fechaEventoInstance: fechaEventoInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'fechaEvento.label', default: 'FechaEvento'), fechaEventoInstance.id])
        redirect(action: "show", id: fechaEventoInstance.id)
    }

    def show(Long id) {
        def fechaEventoInstance = FechaEvento.get(id)
        if (!fechaEventoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'fechaEvento.label', default: 'FechaEvento'), id])
            redirect(action: "list")
            return
        }

        [fechaEventoInstance: fechaEventoInstance]
    }

    def edit(Long id) {
        def fechaEventoInstance = FechaEvento.get(id)
        if (!fechaEventoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'fechaEvento.label', default: 'FechaEvento'), id])
            redirect(action: "list")
            return
        }

        [fechaEventoInstance: fechaEventoInstance]
    }

    def update(Long id, Long version) {
        def fechaEventoInstance = FechaEvento.get(id)
        if (!fechaEventoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'fechaEvento.label', default: 'FechaEvento'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (fechaEventoInstance.version > version) {
                fechaEventoInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'fechaEvento.label', default: 'FechaEvento')] as Object[],
                          "Another user has updated this FechaEvento while you were editing")
                render(view: "edit", model: [fechaEventoInstance: fechaEventoInstance])
                return
            }
        }

        fechaEventoInstance.properties = params

        if (!fechaEventoInstance.save(flush: true)) {
            render(view: "edit", model: [fechaEventoInstance: fechaEventoInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'fechaEvento.label', default: 'FechaEvento'), fechaEventoInstance.id])
        redirect(action: "show", id: fechaEventoInstance.id)
    }

    def delete(Long id) {
        def fechaEventoInstance = FechaEvento.get(id)
        if (!fechaEventoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'fechaEvento.label', default: 'FechaEvento'), id])
            redirect(action: "list")
            return
        }

        try {
            fechaEventoInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'fechaEvento.label', default: 'FechaEvento'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'fechaEvento.label', default: 'FechaEvento'), id])
            redirect(action: "show", id: id)
        }
    }
}
