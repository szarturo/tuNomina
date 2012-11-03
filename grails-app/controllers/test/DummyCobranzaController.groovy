package test

import java.text.SimpleDateFormat;

import org.springframework.dao.DataIntegrityViolationException
import com.sim.calendario.Dependencia;

class DummyCobranzaController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

	def deleteAll(){
		System.out.println("saveAll");
		Object o= params.get("id");
		String[] ids;
		
		// Es un Array
		if(o.getProperties().empty==null){
			ids= ((String[])params.get("id"));
		}else{
			ids= new String[1];
			ids[0]=params.get("id");
		}
		
		for(int i=0; i<ids.length; i++){
			String rowcheck=params.get("rowcheck"+i);
			
			System.out.println("rowcheck: "+rowcheck);
			
			if(rowcheck ==null){
				continue;
			}
			
			def dummyCobranzaInstance = DummyCobranza.get(ids[i])
			if (!dummyCobranzaInstance) {
				continue;
			}
			System.out.println("field1 to delete: "+dummyCobranzaInstance.getField1());
	
			try {
				dummyCobranzaInstance.delete(flush: true);
			}catch (DataIntegrityViolationException e) {
			}
		}
		
		redirect(action: "list", params: params)
	}
	
	def deleteRow(){
		System.out.println("deleteRow");
		
		int idrow= java.lang.Integer.parseInt(params.get("numeroFila"));
		Object o= params.get("id");
		
		String id;
		// Es un Array
		if(o.getProperties().empty==null){
			id= ((String[])params.get("id"))[idrow];
		}else{
			id= params.get("id");
		}
		

		System.out.println("numeroFila:"+idrow);
		System.out.println("id:"+id);
		
		def dummyCobranzaInstance = DummyCobranza.get(id)
		if (!dummyCobranzaInstance) {
			redirect(action: "list")
			return
		}
		System.out.println("df1: "+dummyCobranzaInstance.getField1());

		try {
			dummyCobranzaInstance.delete(flush: true);
		}catch (DataIntegrityViolationException e) {
		}
		
		redirect(action: "list", params: params)
	}
	
	def saveAll(){
		System.out.println("saveAll");
		
		Object o= params.get("id");
		String[] ids;
		double[] fields2;
		String[] fields3;
		String[] fields6;
		Date[] fields5;
		
		// Es un Array
		if(o.getProperties().empty==null){
			ids= ((String[])params.get("id"));
			//fields2= ((double[])params.get("field2"));
			fields2= parseDoubleArray(((String[])params.get("field2")));
			fields3= ((String[])params.get("field3"));
			fields6= ((String[])params.get("field6"));
			fields5= getFechas(params, ids.length);
		}else{
			ids= new String[1];
			fields2= new double[1];
			fields3= new String[1];
			fields6= new String[1];
			ids[0]=params.get("id");
			fields2[0]= Double.parseDouble( params.get("field2") );
			fields3[0]= params.get("field3");
			fields6[0]= params.get("field6");
			fields5= getFechas(params, ids.length);
		}
		
		
		for(int i=0; i<ids.length; i++){
			String rowcheck=params.get("rowcheck"+i);
			
			System.out.println("rowcheck: "+rowcheck);
			
			if(rowcheck ==null){
				continue;
			}
			
			def dummyCobranzaInstance = DummyCobranza.get(ids[i])
			if (!dummyCobranzaInstance) {
				continue;
			}
			System.out.println("field1 to save: "+dummyCobranzaInstance.getField1());
	
			dummyCobranzaInstance.setField2(fields2[i]);
			dummyCobranzaInstance.setField3(fields3[i]);
			dummyCobranzaInstance.setField6(fields6[i]);
			dummyCobranzaInstance.setField5(fields5[i]);
	
			dummyCobranzaInstance.save(flush: true);
		}
		
		redirect(action: "list", params: params)
		
	}
	
	
	def actionr1(){
		System.out.println("actionr1");
		
		int idrow= java.lang.Integer.parseInt(params.get("numeroFila"));
		
		Object o= params.get("id");
		String id;
		double field2;
		String field3;
		String field6;
		Date field5;
		
		// Es un Array
		if(o.getProperties().empty==null){
			id= ((String[])params.get("id"))[idrow];
			//field2= ((String[])params.get("field2"))[idrow];
			field2=Double.parseDouble(  ((String[])params.get("field2"))[idrow] );
			field3= ((String[])params.get("field3"))[idrow];
			field6= ((String[])params.get("field6"))[idrow];
			field5=getFecha(params.get("field50_value"));
			
		}else{
			id= params.get("id");
			field2=Double.parseDouble(params.get("field2"));
			field3=params.get("field3");
			field6=params.get("field6");
			field5=getFecha(params.get("field50_value"));
		}
		
		
		System.out.println("True ID with 1 row: "+ params.get("id"));
		

		System.out.println("numeroFila:"+idrow);
		System.out.println("id:"+id);
		System.out.println("f2:"+field2);
		System.out.println("f3:"+field3);
		System.out.println("f6:"+field6);
		System.out.println("f5:"+field5);
		
		def dummyCobranzaInstance = DummyCobranza.get(id)
		if (!dummyCobranzaInstance) {
			redirect(action: "list")
			return
		}
		System.out.println("df1: "+dummyCobranzaInstance.getField1());

		dummyCobranzaInstance.setField2(field2);
		dummyCobranzaInstance.setField3(field3);
		dummyCobranzaInstance.setField6(field6);
		dummyCobranzaInstance.setField5(field5);

		dummyCobranzaInstance.save(flush: true);
		
		redirect(action: "list", params: params)
	}
	
	private Date[] getFechas(Map<Object,Object> params, int number){
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Date[] dates= new Date[number];
		
		for(int i=0; i<number; i++){
			try{
				dates[i]=format.parse(params.get("field5"+i+"_value"));
			}catch(Exception e){
			}
		}
		
		return dates;
		
	}
	
	private Date getFecha(String value){
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		return format.parse(value);
	}
	
	private double[] parseDoubleArray(String[] array){
		double[] arrayDouble =null;
		if(array==null || array.length<=0){
			return null;
		}
		arrayDouble = new double[array.length+1];
		
		int i=0;
		for(String v:array){
			arrayDouble[i++]=Double.parseDouble(v);
		}
		
		return arrayDouble;
	}
	
	def clean(){
		
	}
	
    def index() {
		System.out.println("Index");
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
		
		try{
		request.putAt("dependencias", Dependencia.list());
		System.out.println("d: "+ request.getAt("dependencias").size());
		}catch(Exception e){
			e.printStackTrace();
		}
		
        [dummyCobranzaInstanceList: DummyCobranza.list(params), dummyCobranzaInstanceTotal: DummyCobranza.count()]
		
		
    }

    def create() {
        [dummyCobranzaInstance: new DummyCobranza(params)]
    }

    def save() {
        def dummyCobranzaInstance = new DummyCobranza(params)
        if (!dummyCobranzaInstance.save(flush: true)) {
            render(view: "create", model: [dummyCobranzaInstance: dummyCobranzaInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'dummyCobranza.label', default: 'DummyCobranza'), dummyCobranzaInstance.id])
        redirect(action: "show", id: dummyCobranzaInstance.id)
    }

    def show(Long id) {
        def dummyCobranzaInstance = DummyCobranza.get(id)
        if (!dummyCobranzaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'dummyCobranza.label', default: 'DummyCobranza'), id])
            redirect(action: "list")
            return
        }

        [dummyCobranzaInstance: dummyCobranzaInstance]
    }

    def edit(Long id) {
        def dummyCobranzaInstance = DummyCobranza.get(id)
        if (!dummyCobranzaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'dummyCobranza.label', default: 'DummyCobranza'), id])
            redirect(action: "list")
            return
        }

        [dummyCobranzaInstance: dummyCobranzaInstance]
    }

    def update(Long id, Long version) {
        def dummyCobranzaInstance = DummyCobranza.get(id)
        if (!dummyCobranzaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'dummyCobranza.label', default: 'DummyCobranza'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (dummyCobranzaInstance.version > version) {
                dummyCobranzaInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'dummyCobranza.label', default: 'DummyCobranza')] as Object[],
                          "Another user has updated this DummyCobranza while you were editing")
                render(view: "edit", model: [dummyCobranzaInstance: dummyCobranzaInstance])
                return
            }
        }

        dummyCobranzaInstance.properties = params

        if (!dummyCobranzaInstance.save(flush: true)) {
            render(view: "edit", model: [dummyCobranzaInstance: dummyCobranzaInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'dummyCobranza.label', default: 'DummyCobranza'), dummyCobranzaInstance.id])
        redirect(action: "show", id: dummyCobranzaInstance.id)
    }

    def delete(Long id) {
        def dummyCobranzaInstance = DummyCobranza.get(id)
        if (!dummyCobranzaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'dummyCobranza.label', default: 'DummyCobranza'), id])
            redirect(action: "list")
            return
        }

        try {
            dummyCobranzaInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'dummyCobranza.label', default: 'DummyCobranza'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'dummyCobranza.label', default: 'DummyCobranza'), id])
            redirect(action: "show", id: id)
        }
    }
}
