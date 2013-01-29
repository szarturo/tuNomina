package com.sim.cliente

import com.rs.gral.RsPersona
import com.sim.catalogo.SimCatDocumento
import com.sim.catalogo.SimCatEscolaridad
import com.sim.catalogo.SimCatTipoPersona
import com.sim.entidad.EntDependencia

import grails.converters.JSON
import org.springframework.dao.DataIntegrityViolationException

class RsClienteController {

	def rsClienteService
	def filterPaneService
	def defaultAction = 'list'

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [rsClienteInstanceList: RsCliente.list(params), rsClienteInstanceTotal: RsCliente.count()]
    }

    def create() {
        [rsClienteInstance: new RsCliente(params)]
    }

    def save() {
        def rsClienteInstance = new RsCliente(params)
        //SE OBTIENE LA PERSONA ASIGNADA
        //SE ASIGNAN LOS ATRIBUTOS DE LA PERSONA AL CLIENTE
        //ESTO SE TUVO QUE HACER POR PROBLEMAS CON EL PLUGIN FILTERPANE
        RsPersona persona = RsPersona.read(rsClienteInstance.persona.id)
        rsClienteInstance.apellidoPaterno = persona.apellidoPaterno
        rsClienteInstance.apellidoMaterno = persona.apellidoMaterno
        rsClienteInstance.primerNombre = persona.primerNombre
        rsClienteInstance.segundoNombre = persona.segundoNombre
        rsClienteInstance.rfc = persona.rfc

        if (!rsClienteInstance.save(flush: true)) {
            render(view: "create", model: [rsClienteInstance: rsClienteInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'rsCliente.label', default: 'RsCliente'), rsClienteInstance.id])
        redirect(action: "show", id: rsClienteInstance.id)
    }

    def show(Long id) {
        def rsClienteInstance = RsCliente.get(id)
        if (!rsClienteInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'rsCliente.label', default: 'RsCliente'), id])
            redirect(action: "list")
            return
        }

        [rsClienteInstance: rsClienteInstance]
    }

    def edit(Long id) {
        def rsClienteInstance = RsCliente.get(id)
        if (!rsClienteInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'rsCliente.label', default: 'RsCliente'), id])
            redirect(action: "list")
            return
        }

        [rsClienteInstance: rsClienteInstance]
    }

    def update(Long id, Long version) {
        def rsClienteInstance = RsCliente.get(id)
        if (!rsClienteInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'rsCliente.label', default: 'RsCliente'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (rsClienteInstance.version > version) {
                rsClienteInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'rsCliente.label', default: 'RsCliente')] as Object[],
                          "Another user has updated this RsCliente while you were editing")
                render(view: "edit", model: [rsClienteInstance: rsClienteInstance])
                return
            }
        }

        rsClienteInstance.properties = params

        if (!rsClienteInstance.save(flush: true)) {
            render(view: "edit", model: [rsClienteInstance: rsClienteInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'rsCliente.label', default: 'RsCliente'), rsClienteInstance.id])
        redirect(action: "show", id: rsClienteInstance.id)
    }

    def delete(Long id) {
        def rsClienteInstance = RsCliente.get(id)
        if (!rsClienteInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'rsCliente.label', default: 'RsCliente'), id])
            redirect(action: "list")
            return
        }

        try {
            rsClienteInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'rsCliente.label', default: 'RsCliente'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'rsCliente.label', default: 'RsCliente'), id])
            redirect(action: "show", id: id)
        }
    }


    def busquedaCliente = {
    }

    def listJSON = {
        render rsClienteService.findClientes(params) as JSON
    }

    def subgridJSON = {
        def persona = RsPersona.get(params.id)
        def cliente = persona?.datosCliente

        def results = cliente?.creditos?.collect {
            [
                    cell: [
                            it.folioSolicitud,
                            it.promocion.nombrePromocion,
                            it.estatusSolicitud.nombreEtapaPrestamo
                    ]
            ]
        }

        def jsonData = [rows: results]
        render jsonData as JSON
    }    

    def filter = {
        if(!params.max) params.max = 10
        render( view:'list', 
            model:[ rsClienteInstanceList: filterPaneService.filter( params, RsCliente ), 
                rsClienteCount: filterPaneService.count( params, RsCliente ), 
                filterParams: org.grails.plugin.filterpane.FilterPaneUtils.extractFilterParams(params), 
                params:params ] )
    }    

    //METODO TEMPORAL PARA DAR DE ALTA CLIENTES
	def altaClientes = {
		// CLIENTE 1

		def javierHernandez = new RsPersona(
				email : "javierhernandez@gmail.com",
				apellidoPaterno: "HERNANDEZ",
				apellidoMaterno: "CHIHARITO",
				primerNombre: "JAVIER",
				segundoNombre: "JAVI",
				sexo: "MASCULINO",
				estadoCivil : "CASADO - BIENES MANCOMUNADOS",
				fechaNacimiento : new Date('09/30/1974'),
				identificacionOficial : SimCatDocumento.findByClaveDocumento('LICENCIA_CONDUCIR'),
				numeroIdentificacionOficial : "JCHI727328328",
				rfc : "JCHI89778",
				curp : "JCHI76878968",
				escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('PREPA'),
				tiposPersona : [
					SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
				],
				).save(flush: true,failOnError: true)

		def clienteJavier = new RsCliente(persona: javierHernandez,
				dependencia: EntDependencia.findByClaveDependencia('CFE'),
				numeroDeNomina: 'JCHSDFYUYUI',
				).save(flush: true,failOnError: true)


		// CLIENTE 2

		def carlossalcido = new RsPersona(
				email : "carsalcido@gmail.com",
				apellidoPaterno: "SALCIDO",
				apellidoMaterno: "CAMPOS",
				primerNombre: "CARLOS",
				segundoNombre: "CHARLY",
				sexo: "MASCULINO",
				estadoCivil : "CASADO - BIENES MANCOMUNADOS",
				fechaNacimiento : new Date('09/30/1970'),
				identificacionOficial : SimCatDocumento.findByClaveDocumento('LICENCIA_CONDUCIR'),
				numeroIdentificacionOficial : "CSAL727328328",
				rfc : "CSAL89778",
				curp : "CSAL76878968",
				escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('QUINDER'),
				tiposPersona : [
					SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
				],
				).save(flush: true,failOnError: true)

		def clienteCarlos = new RsCliente(persona: carlossalcido,
				dependencia: EntDependencia.findByClaveDependencia('CFE'),
				numeroDeNomina: 'CSALSDFYUYUI',
				).save(flush: true,failOnError: true)

		// CLIENTE 3

		def franciscorodriguez = new RsPersona(
				email : "franciscorodriguez@gmail.com",
				apellidoPaterno: "RODRIGUEZ",
				apellidoMaterno: "MAZA",
				primerNombre: "FRANCISCO",
				segundoNombre: "EL MAZA",
				sexo: "MASCULINO",
				estadoCivil : "CASADO - BIENES MANCOMUNADOS",
				fechaNacimiento : new Date('09/30/1980'),
				identificacionOficial : SimCatDocumento.findByClaveDocumento('LICENCIA_CONDUCIR'),
				numeroIdentificacionOficial : "MAZA727328328",
				rfc : "MAZA89778",
				curp : "MAZA76878968",
				escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('PRIMARIA'),
				tiposPersona : [
					SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
				],
				).save(flush: true,failOnError: true)

		def clienteFrancisco = new RsCliente(persona: franciscorodriguez,
				dependencia: EntDependencia.findByClaveDependencia('CFE'),
				numeroDeNomina: 'MAZASDFYUYUI',
				).save(flush: true,failOnError: true)



		// CLIENTE 4

		def guillermoochoa = new RsPersona(
				email : "guillermoochoa@gmail.com",
				apellidoPaterno: "PACOMEMO",
				apellidoMaterno: "OCHOA",
				primerNombre: "FRANCISCO",
				segundoNombre: "PACO MEMO",
				sexo: "MASCULINO",
				estadoCivil : "CASADO - BIENES MANCOMUNADOS",
				fechaNacimiento : new Date('09/30/1985'),
				identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
				numeroIdentificacionOficial : "PACO727328328",
				rfc : "PACO89778",
				curp : "PACO76878968",
				escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('SECUNDARIA'),
				tiposPersona : [
					SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
				],
				).save(flush: true,failOnError: true)

		def clienteGuillermo = new RsCliente(persona: guillermoochoa,
				dependencia: EntDependencia.findByClaveDependencia('CFE'),
				numeroDeNomina: 'PACOSDFYUYUI',
				).save(flush: true,failOnError: true)



		// CLIENTE 5

		def jesuscorona = new RsPersona(
				email : "jesuscorona@gmail.com",
				apellidoPaterno: "CHUY",
				apellidoMaterno: "CORONA",
				primerNombre: "JESUS",
				segundoNombre: "CHUY CORONA",
				sexo: "MASCULINO",
				estadoCivil : "SOLTERO",
				fechaNacimiento : new Date('09/20/1973'),
				identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
				numeroIdentificacionOficial : "CHUY727328328",
				rfc : "CHUY89778",
				curp : "CHUY76878968",
				escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('LIC'),
				tiposPersona : [
					SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
				],
				).save(flush: true,failOnError: true)

		def clienteJesus = new RsCliente(persona: jesuscorona,
				dependencia: EntDependencia.findByClaveDependencia('CFE'),
				numeroDeNomina: 'CHUYSDFYUYUI',
				).save(flush: true,failOnError: true)


		// CLIENTE 6

		def gerardotorrado = new RsPersona(
				email : "gerardotorrado@gmail.com",
				apellidoPaterno: "TORRADO",
				apellidoMaterno: "CASTRO",
				primerNombre: "GERARDO",
				segundoNombre: "GERA",
				sexo: "MASCULINO",
				estadoCivil : "SOLTERO",
				fechaNacimiento : new Date('09/20/1967'),
				identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
				numeroIdentificacionOficial : "TORR727328328",
				rfc : "TORR89778",
				curp : "TORR76878968",
				escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('LIC'),
				tiposPersona : [
					SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
				],
				).save(flush: true,failOnError: true)

		def clienteGerardo = new RsCliente(persona: gerardotorrado,
				dependencia: EntDependencia.findByClaveDependencia('CFE'),
				numeroDeNomina: 'TORRSDFYUYUI',
				).save(flush: true,failOnError: true)



		// CLIENTE 7

		def oribeperalta = new RsPersona(
				email : "oribeperalta@gmail.com",
				apellidoPaterno: "PERALTA",
				apellidoMaterno: "CASTRO",
				primerNombre: "ORIBE",
				segundoNombre: "ORI",
				sexo: "MASCULINO",
				estadoCivil : "SOLTERO",
				fechaNacimiento : new Date('09/20/1967'),
				identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
				numeroIdentificacionOficial : "ORIB727328328",
				rfc : "ORIB89778",
				curp : "ORIB76878968",
				escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('LIC'),
				tiposPersona : [
					SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
				],
				).save(flush: true,failOnError: true)

		def clienteOribe = new RsCliente(persona: oribeperalta,
				dependencia: EntDependencia.findByClaveDependencia('CFE'),
				numeroDeNomina: 'ORIBSDFYUYUI',
				).save(flush: true,failOnError: true)



		// CLIENTE 8

		def benjamingalindo = new RsPersona(
				email : "benjamingalindo@gmail.com",
				apellidoPaterno: "GALINDO",
				apellidoMaterno: "MENCHACA",
				primerNombre: "GALINDO",
				segundoNombre: "EL MAESTRO",
				sexo: "MASCULINO",
				estadoCivil : "CASADO - BIENES MANCOMUNADOS",
				fechaNacimiento : new Date('09/13/1960'),
				identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
				numeroIdentificacionOficial : "BENJ727328328",
				rfc : "BENJ89778",
				curp : "BENJ76878968",
				escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('MAESTRO'),
				tiposPersona : [
					SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
				],
				).save(flush: true,failOnError: true)

		def clienteBenjamin = new RsCliente(persona: benjamingalindo,
				dependencia: EntDependencia.findByClaveDependencia('CFE'),
				numeroDeNomina: 'BENJSDFYUYUI',
				).save(flush: true,failOnError: true)



		// CLIENTE 9

		def alfredotena = new RsPersona(
				email : "alfredotena@gmail.com",
				apellidoPaterno: "TENA",
				apellidoMaterno: "GALINDO",
				primerNombre: "ALFREDO",
				segundoNombre: "AMERICA",
				sexo: "MASCULINO",
				estadoCivil : "CASADO - BIENES MANCOMUNADOS",
				fechaNacimiento : new Date('09/13/1968'),
				identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
				numeroIdentificacionOficial : "TENA727328328",
				rfc : "TENA89778",
				curp : "TENA76878968",
				escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('AMERICA'),
				tiposPersona : [
					SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
				],
				).save(flush: true,failOnError: true)

		def clienteAlfredo = new RsCliente(persona: alfredotena,
				dependencia: EntDependencia.findByClaveDependencia('IMSS'),
				numeroDeNomina: 'TENASDFYUYUI',
				).save(flush: true,failOnError: true)



		// CLIENTE 10

		def luisgarcia = new RsPersona(
				email : "luisgarcia@gmail.com",
				apellidoPaterno: "GARCIA",
				apellidoMaterno: "POSTIGO",
				primerNombre: "LUIS",
				segundoNombre: "DOCTOR",
				sexo: "MASCULINO",
				estadoCivil : "CASADO - BIENES MANCOMUNADOS",
				fechaNacimiento : new Date('09/13/1971'),
				identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
				numeroIdentificacionOficial : "LUIS727328328",
				rfc : "LUIS89778",
				curp : "LUIS76878968",
				escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('LIC'),
				tiposPersona : [
					SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
				],
				).save(flush: true,failOnError: true)

		def clienteLuis = new RsCliente(persona: luisgarcia,
				dependencia: EntDependencia.findByClaveDependencia('CFE'),
				numeroDeNomina: 'LUISSDFYUYUI',
				).save(flush: true,failOnError: true)




		// CLIENTE 11

		def joaquinlopez = new RsPersona(
				email : "joaquinlopez@gmail.com",
				apellidoPaterno: "LOPEZ",
				apellidoMaterno: "DORIGA",
				primerNombre: "JOAQUIN",
				segundoNombre: "TEACHER",
				sexo: "MASCULINO",
				estadoCivil : "CASADO - BIENES MANCOMUNADOS",
				fechaNacimiento : new Date('09/13/1960'),
				identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
				numeroIdentificacionOficial : "JOAQ727328328",
				rfc : "JOAQ89778",
				curp : "JOAQ76878968",
				escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('MAESTRO'),
				tiposPersona : [
					SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
				],
				).save(flush: true,failOnError: true)

		def clienteJoaquin = new RsCliente(persona: joaquinlopez,
				dependencia: EntDependencia.findByClaveDependencia('IMSS'),
				numeroDeNomina: 'JOAQSDFYUYUI',
				).save(flush: true,failOnError: true)



		// CLIENTE 12

		def javieralatorre = new RsPersona(
				email : "javieralatorre@hotmail.com",
				apellidoPaterno: "ALA",
				apellidoMaterno: "TORRE",
				primerNombre: "JAVIER",
				segundoNombre: "HECHOS",
				sexo: "MASCULINO",
				estadoCivil : "CASADO - BIENES MANCOMUNADOS",
				fechaNacimiento : new Date('09/23/1970'),
				identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
				numeroIdentificacionOficial : "JAVI727328328",
				rfc : "JAVIQ89778",
				curp : "JAVIQ76878968",
				escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('INGENIERO'),
				tiposPersona : [
					SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
				],
				).save(flush: true,failOnError: true)

		def clienteJaviera = new RsCliente(persona: javieralatorre,
				dependencia: EntDependencia.findByClaveDependencia('IMSS'),
				numeroDeNomina: 'JAVISDFYUYUI',
				).save(flush: true,failOnError: true)




		// CLIENTE 13

		def alejandrovillalvazo = new RsPersona(
				email : "alejandrovillalvazo@hotmail.com",
				apellidoPaterno: "VILLALVAZO",
				apellidoMaterno: "BUSTO",
				primerNombre: "ALEJANDRO",
				segundoNombre: "ALEX",
				sexo: "MASCULINO",
				estadoCivil : "CASADO - BIENES MANCOMUNADOS",
				fechaNacimiento : new Date('09/23/1980'),
				identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
				numeroIdentificacionOficial : "ALEX727328328",
				rfc : "ALEXQ89778",
				curp : "ALEXQ76878968",
				escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('INGENIERO'),
				tiposPersona : [
					SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
				],
				).save(flush: true,failOnError: true)

		def clienteAlejandro = new RsCliente(persona: alejandrovillalvazo,
				dependencia: EntDependencia.findByClaveDependencia('IMSS'),
				numeroDeNomina: 'ALEXSDFYUYUI',
				).save(flush: true,failOnError: true)



		// CLIENTE 14

		def adelamicha = new RsPersona(
				email : "adelamicha@hotmail.com",
				apellidoPaterno: "MICHA",
				apellidoMaterno: "BUSTO",
				primerNombre: "ADELA",
				segundoNombre: "ADELINA",
				sexo: "FEMENINO",
				estadoCivil : "CASADO - BIENES MANCOMUNADOS",
				fechaNacimiento : new Date('09/23/1982'),
				identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
				numeroIdentificacionOficial : "ADEL727328328",
				rfc : "ADELQ89778",
				curp : "ADELQ76878968",
				escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('ADMIN'),
				tiposPersona : [
					SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
				],
				).save(flush: true,failOnError: true)

		def clienteAdela = new RsCliente(persona: adelamicha,
				dependencia: EntDependencia.findByClaveDependencia('IMSS'),
				numeroDeNomina: 'ADELSDFYUYUI',
				).save(flush: true,failOnError: true)



		// CLIENTE 15

		def eduardosalazar = new RsPersona(
				email : "eduardosalazar@hotmail.com",
				apellidoPaterno: "SALAZAR",
				apellidoMaterno: "BUSTO",
				primerNombre: "EDUARDO",
				segundoNombre: "LALO",
				sexo: "MASCULINO",
				estadoCivil : "CASADO - BIENES MANCOMUNADOS",
				fechaNacimiento : new Date('09/23/1982'),
				identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
				numeroIdentificacionOficial : "ADEL727328328",
				rfc : "ADELQ89778",
				curp : "ADELQ76878968",
				escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('ADMIN'),
				tiposPersona : [
					SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
				],
				).save(flush: true,failOnError: true)

		def clienteEduardo = new RsCliente(persona: eduardosalazar,
				dependencia: EntDependencia.findByClaveDependencia('IMSS'),
				numeroDeNomina: 'EDUSALBUS',
				).save(flush: true,failOnError: true)



		// CLIENTE 16

		def carmenaristegui = new RsPersona(
				email : "carmenaristegui@gmail.com",
				apellidoPaterno: "ARISTEGUI",
				apellidoMaterno: "BUSTO",
				primerNombre: "CARMEN",
				segundoNombre: "CARMELA",
				sexo: "FEMENINO",
				estadoCivil : "CASADO - BIENES MANCOMUNADOS",
				fechaNacimiento : new Date('03/27/1982'),
				identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
				numeroIdentificacionOficial : "CARM727328328",
				rfc : "CARMQ89778",
				curp : "CARMQ76878968",
				escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('CONTADORA'),
				tiposPersona : [
					SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
				],
				).save(flush: true,failOnError: true)

		def clienteCarmen = new RsCliente(persona: carmenaristegui,
				dependencia: EntDependencia.findByClaveDependencia('CFE'),
				numeroDeNomina: 'CARMSDFYUYUI',
				).save(flush: true,failOnError: true)



		// CLIENTE 17

		def araceliPaz = new RsPersona(
				email : "arelypaz@hotmail.com",
				apellidoPaterno: "PAZ",
				apellidoMaterno: "SALAS",
				primerNombre: "ARELY",
				segundoNombre: "ARACELI",
				sexo: "FEMENINO",
				estadoCivil : "CASADO - BIENES MANCOMUNADOS",
				fechaNacimiento : new Date('09/20/1982'),
				identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
				numeroIdentificacionOficial : "ARAP727328328",
				rfc : "ARAPQ89778",
				curp : "ARAPQ76878968",
				escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('PREPA'),
				tiposPersona : [
					SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
				],
				).save(flush: true,failOnError: true)

		def clienteAraceli = new RsCliente(persona: araceliPaz,
				dependencia: EntDependencia.findByClaveDependencia('IMSS'),
				numeroDeNomina: 'ARAPSDFYUYUI',
				).save(flush: true,failOnError: true)




		// CLIENTE 18

		def lidyaCacho = new RsPersona(
				email : "lidya09@hotmail.com",
				apellidoPaterno: "CACHO",
				apellidoMaterno: "PASTRANA",
				primerNombre: "LIDYA",
				segundoNombre: "LIDYACA",
				sexo: "FEMENINO",
				estadoCivil : "CASADO - BIENES MANCOMUNADOS",
				fechaNacimiento : new Date('06/20/1972'),
				identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
				numeroIdentificacionOficial : "LIDY727328328",
				rfc : "LIDYQ89778",
				curp : "LIDYQ76878968",
				escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('PREPA'),
				tiposPersona : [
					SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
				],
				).save(flush: true,failOnError: true)

		def clienteLydiac = new RsCliente(persona: lidyaCacho,
				dependencia: EntDependencia.findByClaveDependencia('IMSS'),
				numeroDeNomina: 'LIDYSDFYUYUI',
				).save(flush: true,failOnError: true)



		// CLIENTE 19

		def hanniaNovel = new RsPersona(
				email : "hannia@hotmail.com",
				apellidoPaterno: "NOVEL",
				apellidoMaterno: "PEREZ",
				primerNombre: "HANNIA",
				segundoNombre: "HANNIAARA",
				sexo: "FEMENINO",
				estadoCivil : "CASADO - BIENES MANCOMUNADOS",
				fechaNacimiento : new Date('09/20/1972'),
				identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
				numeroIdentificacionOficial : "HANN727328328",
				rfc : "HANNQ89778",
				curp : "HANNQ76878968",
				escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('PREPA'),
				tiposPersona : [
					SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
				],
				).save(flush: true,failOnError: true)

		def clienteHannia = new RsCliente(persona: hanniaNovel,
				dependencia: EntDependencia.findByClaveDependencia('IMSS'),
				numeroDeNomina: 'HANNSDFYUYUI',
				).save(flush: true,failOnError: true)


		// CLIENTE 20

		def anamariaNovelli = new RsPersona(
				email : "anama13@gmail.com",
				apellidoPaterno: "NOVELLI",
				apellidoMaterno: "ESTRADA",
				primerNombre: "ANA",
				segundoNombre: "MARIA",
				sexo: "FEMENINO",
				estadoCivil : "CASADO - BIENES MANCOMUNADOS",
				fechaNacimiento : new Date('03/27/1962'),
				identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
				numeroIdentificacionOficial : "ANAM727328328",
				rfc : "ANAMQ89778",
				curp : "ANAMQ76878968",
				escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('CONTADORA'),
				tiposPersona : [
					SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
				],
				).save(flush: true,failOnError: true)

		def clienteAnam = new RsCliente(persona: anamariaNovelli,
				dependencia: EntDependencia.findByClaveDependencia('CFE'),
				numeroDeNomina: 'ANAMSDFYUYUI',
				).save(flush: true,failOnError: true)



		// CLIENTE 21

		def anaGuevara = new RsPersona(
				email : "guevara@gmail.com",
				apellidoPaterno: "GUEVARA",
				apellidoMaterno: "BUSTOS",
				primerNombre: "ANA",
				segundoNombre: "GABRIELA",
				sexo: "FEMENINO",
				estadoCivil : "CASADO - BIENES MANCOMUNADOS",
				fechaNacimiento : new Date('05/27/1982'),
				identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
				numeroIdentificacionOficial : "ANAG727328328",
				rfc : "ANAGQ89778",
				curp : "ANAGQ76878968",
				escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('CONTADORA'),
				tiposPersona : [
					SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
				],
				).save(flush: true,failOnError: true)

		def clienteAnag = new RsCliente(persona: anaGuevara,
				dependencia: EntDependencia.findByClaveDependencia('CFE'),
				numeroDeNomina: 'ANAGSDFYUYUI',
				).save(flush: true,failOnError: true)


		// CLIENTE 22

		def estebanArce = new RsPersona(
				email : "esteban03@gmail.com",
				apellidoPaterno: "ARCE",
				apellidoMaterno: "GORDILLO",
				primerNombre: "ESTEBAN",
				segundoNombre: "ESTEB",
				sexo: "MASCULINO",
				estadoCivil : "CASADO - BIENES MANCOMUNADOS",
				fechaNacimiento : new Date('05/27/1962'),
				identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
				numeroIdentificacionOficial : "ESTE727328328",
				rfc : "ESTEQ89778",
				curp : "ESTEQ76878968",
				escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('CONTADOR'),
				tiposPersona : [
					SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
				],
				).save(flush: true,failOnError: true)

		def clienteEsteb = new RsCliente(persona: estebanArce,
				dependencia: EntDependencia.findByClaveDependencia('CFE'),
				numeroDeNomina: 'ESTBSDFYUYUI',
				).save(flush: true,failOnError: true)





		// CLIENTE 23

		def marianoOsorio = new RsPersona(
				email : "mariano09@gmail.com",
				apellidoPaterno: "OSORIO",
				apellidoMaterno: "ARCE",
				primerNombre: "MARIANO",
				segundoNombre: "MAR",
				sexo: "MASCULINO",
				estadoCivil : "CASADO - BIENES MANCOMUNADOS",
				fechaNacimiento : new Date('05/27/1982'),
				identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
				numeroIdentificacionOficial : "MARI727328328",
				rfc : "MARIQ89778",
				curp : "MARIQ76878968",
				escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('CONTADOR'),
				tiposPersona : [
					SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
				],
				).save(flush: true,failOnError: true)

		def clienteMariano = new RsCliente(persona: marianoOsorio,
				dependencia: EntDependencia.findByClaveDependencia('CFE'),
				numeroDeNomina: 'MARISDFYUYUI',
				).save(flush: true,failOnError: true)




		// CLIENTE 24

		def jorgeZarza = new RsPersona(
				email : "zaeza09@gmail.com",
				apellidoPaterno: "zarza",
				apellidoMaterno: "cortes",
				primerNombre: "jorge",
				segundoNombre: "jor",
				sexo: "MASCULINO",
				estadoCivil : "CASADO - BIENES MANCOMUNADOS",
				fechaNacimiento : new Date('05/17/1982'),
				identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
				numeroIdentificacionOficial : "jorg727328328",
				rfc : "jorgQ89778",
				curp : "jorgQ76878968",
				escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('CONTADOR'),
				tiposPersona : [
					SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
				],
				).save(flush: true,failOnError: true)

		def clienteJorgez = new RsCliente(persona: jorgeZarza,
				dependencia: EntDependencia.findByClaveDependencia('CFE'),
				numeroDeNomina: 'jorgSDFYUYUI',
				).save(flush: true,failOnError: true)



		// CLIENTE 25

		def eugenioderbez = new RsPersona(
				email : "eugenioder09@gmail.com",
				apellidoPaterno: "DERBEZ",
				apellidoMaterno: "ONTERO",
				primerNombre: "EUGENIO",
				segundoNombre: "EU",
				sexo: "MASCULINO",
				estadoCivil : "CASADO - BIENES MANCOMUNADOS",
				fechaNacimiento : new Date('06/12/1978'),
				identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
				numeroIdentificacionOficial : "EUGE7328328",
				rfc : "EUGE879778",
				curp : "EUGE6878968",
				escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('CONTADOR'),
				tiposPersona : [
					SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
				],
				).save(flush: true,failOnError: true)

		def clienteEugenio = new RsCliente(persona:eugenioderbez,
				dependencia: EntDependencia.findByClaveDependencia('CFE'),
				numeroDeNomina: 'EUGENIOHUYTG',
				).save(flush: true,failOnError: true)


		// CLIENTE 26

		def omarchaparro = new RsPersona(
				email : "omarchaparro@gmail.com",
				apellidoPaterno: "chaparro",
				apellidoMaterno: "guevara",
				primerNombre: "omar",
				segundoNombre: "ramo",
				sexo: "MASCULINO",
				estadoCivil : "CASADO - BIENES MANCOMUNADOS",
				fechaNacimiento : new Date('08/25/1954'),
				identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
				numeroIdentificacionOficial : "omarw27328328",
				rfc : "omarom89778",
				curp : "omarom6878968",
				escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('CONTADOR'),
				tiposPersona : [
					SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
				],
				).save(flush: true,failOnError: true)

		def clienteOmar = new RsCliente(persona: omarchaparro,
				dependencia: EntDependencia.findByClaveDependencia('CFE'),
				numeroDeNomina: 'OMARDFYUYUI',
				).save(flush: true,failOnError: true)



		// CLIENTE 27

		def javierlopez = new RsPersona(
				email : "javierlopez@gmail.com",
				apellidoPaterno: "LOPEZ",
				apellidoMaterno: "CHABELO",
				primerNombre: "JAVIER",
				segundoNombre: "CHABE",
				sexo: "MASCULINO",
				estadoCivil : "SOLTERO",
				fechaNacimiento : new Date('03/02/1956'),
				identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
				numeroIdentificacionOficial : "CHABE57328328",
				rfc : "CHABE589778",
				curp : "CHABE5878968",
				escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('CONTADOR'),
				tiposPersona : [
					SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
				],
				).save(flush: true,failOnError: true)

		def clienteJavierL = new RsCliente(persona: javierlopez,
				dependencia: EntDependencia.findByClaveDependencia('IMSS'),
				numeroDeNomina: 'CHABE5FYUYUI',
				).save(flush: true,failOnError: true)


		// CLIENTE 28

		def adrianarodriguez = new RsPersona(
				email : "adrianarodrigu@gmail.com",
				apellidoPaterno: "RODRGUEZ",
				apellidoMaterno: "RODRGUEZ",
				primerNombre: "ADRIANA",
				segundoNombre: "ADI",
				sexo: "FEMENINO",
				estadoCivil : "CASADO - BIENES MANCOMUNADOS",
				fechaNacimiento : new Date('06/01/1985'),
				identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
				numeroIdentificacionOficial : "ADRIANA7328328",
				rfc : "ADRIANA79778",
				curp : "ADRIANA778968",
				escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('PREPA'),
				tiposPersona : [
					SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
				],
				).save(flush: true,failOnError: true)

		def clienteAdriana = new RsCliente(persona: adrianarodriguez,
				dependencia: EntDependencia.findByClaveDependencia('CFE'),
				numeroDeNomina: 'ADISDFYUYUI',
				).save(flush: true,failOnError: true)


		// CLIENTE 29

		def carlosperez = new RsPersona(
				email : "carlosperez@gmail.com",
				apellidoPaterno: "PEREZ",
				apellidoMaterno: "RUIZ",
				primerNombre: "CARLOS",
				segundoNombre: "LEE",
				sexo: "MASCULINO",
				estadoCivil : "SOLTERO",
				fechaNacimiento : new Date('01/25/1987'),
				identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
				numeroIdentificacionOficial : "LEE44466",
				rfc : "LEE44466",
				curp : "LEE44466",
				escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('CONTADOR'),
				tiposPersona : [
					SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
				],
				).save(flush: true,failOnError: true)

		def clienteCarlosl = new RsCliente(persona: carlosperez,
				dependencia: EntDependencia.findByClaveDependencia('CFE'),
				numeroDeNomina: 'LEE41235',
				).save(flush: true,failOnError: true)



		// CLIENTE 30

		def paulinarubio = new RsPersona(
				email : "paulinarubio@gmail.com",
				apellidoPaterno: "RUBIO",
				apellidoMaterno: "RUBIO",
				primerNombre: "PAULINA",
				segundoNombre: "PAU",
				sexo: "FEMENINO",
				estadoCivil : "CASADO - BIENES MANCOMUNADOS",
				fechaNacimiento : new Date('02/30/1967'),
				identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
				numeroIdentificacionOficial : "PAU321215",
				rfc : "PAU3212155TR",
				curp : "PAU321215UG",
				escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('CONTADOR'),
				tiposPersona : [
					SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
				],
				).save(flush: true,failOnError: true)

		def clientePaulina = new RsCliente(persona: paulinarubio,
				dependencia: EntDependencia.findByClaveDependencia('CFE'),
				numeroDeNomina: 'PAU678990',
				).save(flush: true,failOnError: true)



		// CLIENTE 31

		def romeosantos = new RsPersona(
				email : "romeosantos@gmail.com",
				apellidoPaterno: "SANTOS",
				apellidoMaterno: "JEREZ",
				primerNombre: "ROMEO",
				segundoNombre: "JUAN",
				sexo: "MASCULINO",
				estadoCivil : "SOLTERO",
				fechaNacimiento : new Date('12/05/1997'),
				identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
				numeroIdentificacionOficial : "ROMEO328328",
				rfc : "ROMEO328328YT",
				curp : "ROMEO328328",
				escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('LICENCIATURA'),
				tiposPersona : [
					SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
				],
				).save(flush: true,failOnError: true)

		def clienteRomeo = new RsCliente(persona: romeosantos,
				dependencia: EntDependencia.findByClaveDependencia('CFE'),
				numeroDeNomina: 'ROMEO342',
				).save(flush: true,failOnError: true)



		// CLIENTE 32

		def henrisanchez = new RsPersona(
				email : "henrisanchez@gmail.com",
				apellidoPaterno: "SANCHEZ",
				apellidoMaterno: "ROJAS",
				primerNombre: "HENRI",
				segundoNombre: "HERNAN",
				sexo: "MASCULINO",
				estadoCivil : "CASADO - BIENES MANCOMUNADOS",
				fechaNacimiento : new Date('02/28/1973'),
				identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
				numeroIdentificacionOficial : "HENRI123457",
				rfc : "HENRI123457",
				curp : "HENRI123457",
				escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('CONTADOR'),
				tiposPersona : [
					SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
				],
				).save(flush: true,failOnError: true)

		def clienteHenri = new RsCliente(persona: henrisanchez,
				dependencia: EntDependencia.findByClaveDependencia('CFE'),
				numeroDeNomina: 'HNERI462',
				).save(flush: true,failOnError: true)



		// CLIENTE 33

		def luissolis = new RsPersona(
				email : "luissolis@gmail.com",
				apellidoPaterno: "SOLIS",
				apellidoMaterno: "SOLAR",
				primerNombre: "LUIS",
				segundoNombre: "RYAN",
				sexo: "MASCULINO",
				estadoCivil : "SOLTERO",
				fechaNacimiento : new Date('18/09/1956'),
				identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
				numeroIdentificacionOficial : "RYAN4567828",
				rfc : "RYAN4567828",
				curp : "RYAN4567828",
				escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('LICENCIATURA'),
				tiposPersona : [
					SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
				],
				).save(flush: true,failOnError: true)

		def clienteLuisR = new RsCliente(persona: luissolis,
				dependencia: EntDependencia.findByClaveDependencia('CFE'),
				numeroDeNomina: 'LUI456',
				).save(flush: true,failOnError: true)



		// CLIENTE 34

		def elsaruiz = new RsPersona(
				email : "elsaruizs@gmail.com",
				apellidoPaterno: "RUIZ",
				apellidoMaterno: "RIOS",
				primerNombre: "ELSA",
				segundoNombre: "ISABEL",
				sexo: "FEMENINO",
				estadoCivil : "SOLTERO",
				fechaNacimiento : new Date('07/03/1943'),
				identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
				numeroIdentificacionOficial : "ELSA789328",
				rfc : "ELSA789328",
				curp : "ELSA789328",
				escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('PREPA'),
				tiposPersona : [
					SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
				],
				).save(flush: true,failOnError: true)

		def clienteElsa = new RsCliente(persona: elsaruiz,
				dependencia: EntDependencia.findByClaveDependencia('CFE'),
				numeroDeNomina: 'ELSA543',
				).save(flush: true,failOnError: true)



		// CLIENTE 35

		def guillermoochoal = new RsPersona(
				email : "memo@gmail.com",
				apellidoPaterno: "OCHOA",
				apellidoMaterno: "SALAZ",
				primerNombre: "GUILLERMO",
				segundoNombre: "PACO",
				sexo: "MASCULINO",
				estadoCivil : "SOLTERO",
				fechaNacimiento : new Date('18/09/1986'),
				identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
				numeroIdentificacionOficial : "PACO4567828",
				rfc : "PACO4567828",
				curp : "PACO4567828",
				escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('LICENCIATURA'),
				tiposPersona : [
					SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
				],
				).save(flush: true,failOnError: true)

		def clienteGuillermoo = new RsCliente(persona: guillermoochoal,
				dependencia: EntDependencia.findByClaveDependencia('CFE'),
				numeroDeNomina: 'PACO456',
				).save(flush: true,failOnError: true)


		// CLIENTE 36

		def rafaelMarquez = new RsPersona(
				email : "rmarquez@gmail.com",
				apellidoPaterno: "MARQUEZ",
				apellidoMaterno: "ROSAS",
				primerNombre: "RAFAEL",
				segundoNombre: "RAFA",
				sexo: "MASCULINO",
				estadoCivil : "SOLTERO",
				fechaNacimiento : new Date('07/03/1943'),
				identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
				numeroIdentificacionOficial : "RAFA789328",
				rfc : "RAFA789328",
				curp : "RAFA789328",
				escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('PREPA'),
				tiposPersona : [
					SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
				],
				).save(flush: true,failOnError: true)

		def clienteRafam = new RsCliente(persona: rafaelMarquez,
				dependencia: EntDependencia.findByClaveDependencia('CFE'),
				numeroDeNomina: 'RAFA543',
				).save(flush: true,failOnError: true)



		// CLIENTE 37

		def gerardoTorrado = new RsPersona(
				email : "gerardo4@gmail.com",
				apellidoPaterno: "TORRADO",
				apellidoMaterno: "PASTRANA",
				primerNombre: "GERARDO",
				segundoNombre: "BORREGO",
				sexo: "MASCULINO",
				estadoCivil : "SOLTERO",
				fechaNacimiento : new Date('18/09/1976'),
				identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
				numeroIdentificacionOficial : "GERA4567828",
				rfc : "GERA4567828",
				curp : "GERA4567828",
				escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('LICENCIATURA'),
				tiposPersona : [
					SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
				],
				).save(flush: true,failOnError: true)

		def clienteGerardot = new RsCliente(persona: gerardoTorrado,
				dependencia: EntDependencia.findByClaveDependencia('CFE'),
				numeroDeNomina: 'GERA456',
				).save(flush: true,failOnError: true)



		// CLIENTE 38

		def juanDiaz = new RsPersona(
				email : "juand@gmail.com",
				apellidoPaterno: "DIAZ",
				apellidoMaterno: "TORRES",
				primerNombre: "JUAN",
				segundoNombre: "JUANGA",
				sexo: "MASCULINO",
				estadoCivil : "SOLTERO",
				fechaNacimiento : new Date('18/09/1986'),
				identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
				numeroIdentificacionOficial : "JUAN4567828",
				rfc : "JUAN4567828",
				curp : "JUAN4567828",
				escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('LICENCIATURA'),
				tiposPersona : [
					SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
				],
				).save(flush: true,failOnError: true)

		def clienteJuang = new RsCliente(persona: juanDiaz,
				dependencia: EntDependencia.findByClaveDependencia('CFE'),
				numeroDeNomina: 'JUAN456',
				).save(flush: true,failOnError: true)

		// CLIENTE 39

		def carlosPerez = new RsPersona(
				email : "carpe@gmail.com",
				apellidoPaterno: "PEREZ",
				apellidoMaterno: "ROSALES",
				primerNombre: "CARLOS",
				segundoNombre: "CARPE",
				sexo: "MASCULINO",
				estadoCivil : "CASADO - BIENES MANCOMUNADOS",
				fechaNacimiento : new Date('06/12/1980'),
				identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
				numeroIdentificacionOficial : "CARP7328328",
				rfc : "CARP879778",
				curp : "CARP6878968",
				escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('CONTADOR'),
				tiposPersona : [
					SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
				],
				).save(flush: true,failOnError: true)

		def clienteCarlo = new RsCliente(persona:carlosPerez,
				dependencia: EntDependencia.findByClaveDependencia('CFE'),
				numeroDeNomina: 'CARPNIOHUYTG',
				).save(flush: true,failOnError: true)


		// CLIENTE 40

		def cristianBenitez = new RsPersona(
				email : "cristianbenitez@gmail.com",
				apellidoPaterno: "BENITES",
				apellidoMaterno: "SALCEDO",
				primerNombre: "CRISTIAN",
				segundoNombre: "CHUCHO",
				sexo: "MASCULINO",
				estadoCivil : "SOLTERO",
				fechaNacimiento : new Date('10/09/1976'),
				identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
				numeroIdentificacionOficial : "CRIS4567828",
				rfc : "CRIS4567828",
				curp : "CRIS4567828",
				escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('LICENCIATURA'),
				tiposPersona : [
					SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
				],
				).save(flush: true,failOnError: true)

		def clienteCristianb = new RsCliente(persona: cristianBenitez,
				dependencia: EntDependencia.findByClaveDependencia('CFE'),
				numeroDeNomina: 'CRIS456',
				).save(flush: true,failOnError: true)


		/*																											
				// CLIENTE 41
					
					def laloMolina = new RsPersona(
							email : "lalom@gmail.com",
							apellidoPaterno: "MOLINA",
							apellidoMaterno: "CERVANTES",
							primerNombre: "LALO",
							segundoNombre: "EDUARDO",
							sexo: "MASCULINO",
							estadoCivil : "SOLTERO",
							fechaNacimiento : new Date('11/09/1986'),
							identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
							numeroIdentificacionOficial : "LALO4567828",
							rfc : "LALO4567828",
							curp : "LALO4567828",
							escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('LICENCIATURA'),
							tiposPersona : [
									SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
							],
						)save(flush: true,failOnError: true)
					
					def clienteLalom = new RsCliente(persona: laloMolina,
							dependencia: EntDependencia.findByClaveDependencia('CFE'),
							numeroDeNomina: 'LALO456',
					)save(flush: true,failOnError: true)
						
				
					// CLIENTE 42
					
						 def marcoFabian = new RsPersona(
									email : "marcofab@gmail.com",
									apellidoPaterno: "FABIAN",
									apellidoMaterno: "MORAN",
									primerNombre: "MARCO",
									segundoNombre: "MARKITO",
									sexo: "MASCULINO",
									estadoCivil : "SOLTERO",
									fechaNacimiento : new Date('12/09/1986'),
									identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
									numeroIdentificacionOficial : "MARC4567828",
									rfc : "MARC4567828",
									curp : "MARC4567828",
									escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('LICENCIATURA'),
									tiposPersona : [
											SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
									],
							)save(flush: true,failOnError: true)
				
							def clienteMarcof = new RsCliente(persona: marcoFabian,
									dependencia: EntDependencia.findByClaveDependencia('CFE'),
									numeroDeNomina: 'MARC456',
							)save(flush: true,failOnError: true)
																													
																												
						// CLIENTE 43
																															
					   def diegoReyes = new RsPersona(
								email : "diegorey@gmail.com",
								apellidoPaterno: "REYES",
								apellidoMaterno: "ROSAS",
								primerNombre: "DIEGO",
								segundoNombre: "DIEGOREY",
								sexo: "MASCULINO",
								estadoCivil : "SOLTERO",
								fechaNacimiento : new Date('07/03/1983'),
								identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
								numeroIdentificacionOficial : "DIEG789328",
								rfc : "DIEG789328",
								curp : "DIEG789328",
								escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('PREPA'),
								tiposPersona : [
										SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
								],
						)save(flush: true,failOnError: true)
				
						def clienteDiegor = new RsCliente(persona: diegoReyes,
								dependencia: EntDependencia.findByClaveDependencia('CFE'),
								numeroDeNomina: 'DIEG543',
						)save(flush: true,failOnError: true)
																														
																														
																														
					  // CLIENTE 44
								
						def luisGarciad = new RsPersona(
								email : "garciadr@gmail.com",
								apellidoPaterno: "GARCIA",
								apellidoMaterno: "POSTIGO",
								primerNombre: "LUIS",
								segundoNombre: "DOCTOR",
								sexo: "MASCULINO",
								estadoCivil : "SOLTERO",
								fechaNacimiento : new Date('12/09/1976'),
								identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
								numeroIdentificacionOficial : "LUIG4567828",
								rfc : "LUIG4567828",
								curp : "LUIG4567828",
								escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('LICENCIATURA'),
								tiposPersona : [
										SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
								],
						)save(flush: true,failOnError: true)
				
						def clienteLuisg = new RsCliente(persona: luisGarciad,
								dependencia: EntDependencia.findByClaveDependencia('CFE'),
								numeroDeNomina: 'LUIG456',
						)save(flush: true,failOnError: true)
																														
																													
																													
						// CLIENTE 45
								
						def leoMesi = new RsPersona(
								email : "messis@gmail.com",
								apellidoPaterno: "MESI",
								apellidoMaterno: "MONTES",
								primerNombre: "LEO",
								segundoNombre: "LEONARDO",
								sexo: "MASCULINO",
								estadoCivil : "SOLTERO",
								fechaNacimiento : new Date('09/09/1986'),
								identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
								numeroIdentificacionOficial : "LEOM4567828",
								rfc : "LEOM4567828",
								curp : "LEOM4567828",
								escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('LICENCIATURA'),
								tiposPersona : [
										SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
								],
						)save(flush: true,failOnError: true)
				
						def clienteLeom = new RsCliente(persona: leoMesi,
								dependencia: EntDependencia.findByClaveDependencia('CFE'),
								numeroDeNomina: 'LEOM456',
						)save(flush: true,failOnError: true)
																													
					
							// CLIENTE 46
							
					def andresIniesta = new RsPersona(
							email : "andresta@gmail.com",
							apellidoPaterno: "INIESTA",
							apellidoMaterno: "FERNANDEZ",
							primerNombre: "ANDRES",
							segundoNombre: "ANDREIN",
							sexo: "MASCULINO",
							estadoCivil : "CASADO - BIENES MANCOMUNADOS",
							fechaNacimiento : new Date('03/07/1962'),
							identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
							numeroIdentificacionOficial : "ANDI727328328",
							rfc : "ANDIQ89778",
							curp : "ANDIQ76878968",
							escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('CONTADORA'),
							tiposPersona : [
									SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
							],
					)save(flush: true,failOnError: true)
				
					def clienteAndresi = new RsCliente(persona: andresIniesta,
							dependencia: EntDependencia.findByClaveDependencia('CFE'),
							numeroDeNomina: 'ANDISDFYUYUI',
					)save(flush: true,failOnError: true)
										
								
								
				// CLIENTE 47
					
					def jorgeCampos = new RsPersona(
						email : "jorge54@gmail.com",
						apellidoPaterno: "CAMPOS",
						apellidoMaterno: "BUSTOS",
						primerNombre: "JORGE",
						segundoNombre: "CAMP",
						sexo: "MASCULINO",
						estadoCivil : "CASADO - BIENES MANCOMUNADOS",
						fechaNacimiento : new Date('05/07/1972'),
						identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
						numeroIdentificacionOficial : "JORG727328328",
						rfc : "JORGQ89778",
						curp : "JORGQ76878968",
						escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('CONTADORA'),
						tiposPersona : [
								SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
						],
					)save(flush: true,failOnError: true)
				
					def clienteJoergeca = new RsCliente(persona: jorgeCampos,
							dependencia: EntDependencia.findByClaveDependencia('CFE'),
							numeroDeNomina: 'JORGSDFYUYUI',
					)save(flush: true,failOnError: true)
							
							
					// CLIENTE 48
							
					def severoMesa = new RsPersona(
							email : "severo03@gmail.com",
							apellidoPaterno: "MESA",
							apellidoMaterno: "GORDILLO",
							primerNombre: "SEVERO",
							segundoNombre: "SEVEROM",
							sexo: "MASCULINO",
							estadoCivil : "CASADO - BIENES MANCOMUNADOS",
							fechaNacimiento : new Date('05/09/1962'),
							identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
							numeroIdentificacionOficial : "SEVE727328328",
							rfc : "SEVEQ89778",
							curp : "SEVEQ76878968",
							escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('CONTADOR'),
							tiposPersona : [
									SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
							],
					)save(flush: true,failOnError: true)
				
					def clienteSeverom = new RsCliente(persona: severoMesa,
							dependencia: EntDependencia.findByClaveDependencia('CFE'),
							numeroDeNomina: 'SEVESDFYUYUI',
					)save(flush: true,failOnError: true)
								
								
											
						
										
				// CLIENTE 49
				
					def alanPulido = new RsPersona(
						email : "alano09@gmail.com",
						apellidoPaterno: "PULIDO",
						apellidoMaterno: "ARCE",
						primerNombre: "ALAN",
						segundoNombre: "ALANMAR",
						sexo: "MASCULINO",
						estadoCivil : "CASADO - BIENES MANCOMUNADOS",
						fechaNacimiento : new Date('05/04/1982'),
						identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
						numeroIdentificacionOficial : "ALAN727328328",
						rfc : "ALANQ89778",
						curp : "ALANQ76878968",
						escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('CONTADOR'),
						tiposPersona : [
								SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
						],
					)save(flush: true,failOnError: true)
				
					def clienteAlanp = new RsCliente(persona: alanPulido,
							dependencia: EntDependencia.findByClaveDependencia('CFE'),
							numeroDeNomina: 'ALANSDFYUYUI',
					)save(flush: true,failOnError: true)
				
					
					
					
				// CLIENTE 50
				
					def pedroPineda = new RsPersona(
							email : "pedroPineda@gmail.com",
							apellidoPaterno: "PINEDA",
							apellidoMaterno: "CORTES",
							primerNombre: "PEDRO",
							segundoNombre: "PEDROJ",
							sexo: "MASCULINO",
							estadoCivil : "CASADO - BIENES MANCOMUNADOS",
							fechaNacimiento : new Date('05/10/1982'),
							identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
							numeroIdentificacionOficial : "PEDR727328328",
							rfc : "PEDRQ89778",
							curp : "PEDRQ76878968",
							escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('CONTADOR'),
							tiposPersona : [
									SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
							],
					)save(flush: true,failOnError: true)
				
					def clientePedrop = new RsCliente(persona: pedroPineda,
							dependencia: EntDependencia.findByClaveDependencia('CFE'),
							numeroDeNomina: 'PEDRSDFYUYUI',
					)save(flush: true,failOnError: true)
							
							
							
				// CLIENTE 51
				
					def sergioRamos = new RsPersona(
								email : "sergioramos@gmail.com",
								apellidoPaterno: "RAMOS",
								apellidoMaterno: "CONTERRAS",
								primerNombre: "SERGIO",
								segundoNombre: "SERGO",
								sexo: "MASCULINO",
								estadoCivil : "CASADO - BIENES MANCOMUNADOS",
								fechaNacimiento : new Date('06/12/1978'),
								identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
								numeroIdentificacionOficial : "SERG7328328",
								rfc : "SERG879778",
								curp : "SERG6878968",
								escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('CONTADOR'),
								tiposPersona : [
										SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
								],
						)save(flush: true,failOnError: true)
				
						def clienteSergior = new RsCliente(persona:sergioRamos,
								dependencia: EntDependencia.findByClaveDependencia('CFE'),
								numeroDeNomina: 'SERGNIOHUYTG',
						)save(flush: true,failOnError: true)
									
										
				// CLIENTE 52
				
					def joseRamon = new RsPersona(
								email : "joseramon@gmail.com",
								apellidoPaterno: "RAMON",
								apellidoMaterno: "JOSE",
								primerNombre: "SANCRITOBAL",
								segundoNombre: "CRISTOB",
								sexo: "MASCULINO",
								estadoCivil : "CASADO - BIENES MANCOMUNADOS",
								fechaNacimiento : new Date('08/09/1954'),
								identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
								numeroIdentificacionOficial : "JOSE27328328",
								rfc : "JOSE9889778",
								curp : "JOSE766878968",
								escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('CONTADOR'),
								tiposPersona : [
										SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
								],
						)save(flush: true,failOnError: true)
				
						def clienteJoser = new RsCliente(persona: joseRamon,
								dependencia: EntDependencia.findByClaveDependencia('CFE'),
								numeroDeNomina: 'JOSEDFYUYUI',
						)save(flush: true,failOnError: true)
											
											
										
				// CLIENTE 53
				
					def angelReina = new RsPersona(
								email : "angelrey@gmail.com",
								apellidoPaterno: "REYNA",
								apellidoMaterno: "PONCE",
								primerNombre: "ANGEL",
								segundoNombre: "ANGO",
								sexo: "MASCULINO",
								estadoCivil : "SOLTERO",
								fechaNacimiento : new Date('03/02/1996'),
								identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
								numeroIdentificacionOficial : "ANGEE57328328",
								rfc : "ANGEE589778",
								curp : "ANGEE5878968",
								escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('CONTADOR'),
								tiposPersona : [
										SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
								],
					)save(flush: true,failOnError: true)
				
					def clienteAngelr = new RsCliente(persona: angelReina,
							dependencia: EntDependencia.findByClaveDependencia('IMSS'),
							numeroDeNomina: 'ANGEE5FYUYUI',
					)save(flush: true,failOnError: true)
													
													
				// CLIENTE 54
				
					def ikerCasillas = new RsPersona(
							email : "iker@gmail.com",
							apellidoPaterno: "CASILLAS",
							apellidoMaterno: "ANGEL",
							primerNombre: "IKER",
							segundoNombre: "IKERANG",
							sexo: "FEMENINO",
							estadoCivil : "CASADO - BIENES MANCOMUNADOS",
							fechaNacimiento : new Date('06/01/1985'),
							identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
							numeroIdentificacionOficial : "IKERANA7328328",
							rfc : "IKERANA79778",
							curp : "IKERANA778968",
							escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('PREPA'),
							tiposPersona : [
									SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
							],
					)save(flush: true,failOnError: true)
				
					def clienteIker = new RsCliente(persona: ikerCasillas,
							dependencia: EntDependencia.findByClaveDependencia('CFE'),
							numeroDeNomina: 'IKERDFYUYUI',
					)save(flush: true,failOnError: true)
															
															
				// CLIENTE 55
				
					def jessicaRedondo = new RsPersona(
								email : "jessicared@gmail.com",
								apellidoPaterno: "REDONDO",
								apellidoMaterno: "GONZALES",
								primerNombre: "JESSICA",
								segundoNombre: "JESSI",
								sexo: "FEMENINO",
								estadoCivil : "SOLTERO",
								fechaNacimiento : new Date('01/11/1987'),
								identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
								numeroIdentificacionOficial : "JESS44466",
								rfc : "JESS44466",
								curp : "JESS44466",
								escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('CONTADOR'),
								tiposPersona : [
										SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
								],
					)save(flush: true,failOnError: true)
				
					def clienteJessicar = new RsCliente(persona: jessicaRedondo,
							dependencia: EntDependencia.findByClaveDependencia('CFE'),
							numeroDeNomina: 'JESS41235',
					)save(flush: true,failOnError: true)
																
																	
																	
				// CLIENTE 56
				
					def moisesMunoz = new RsPersona(
							email : "munoz@gmail.com",
							apellidoPaterno: "MUNOZ",
							apellidoMaterno: "PUEBLA",
							primerNombre: "MOISES",
							segundoNombre: "MOY",
							sexo: "MASCULINO",
							estadoCivil : "CASADO - BIENES MANCOMUNADOS",
							fechaNacimiento : new Date('02/30/1987'),
							identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
							numeroIdentificacionOficial : "MOIS321215",
							rfc : "MOIS212155TR",
							curp : "MOIS21215UG",
							escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('CONTADOR'),
							tiposPersona : [
									SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
							],
					)save(flush: true,failOnError: true)
				
					def clienteMoises = new RsCliente(persona: moisesMunoz,
							dependencia: EntDependencia.findByClaveDependencia('CFE'),
							numeroDeNomina: 'MOIS678990',
					)save(flush: true,failOnError: true)
																			
																			
																			
				// CLIENTE 57
				
					def isaccRomo = new RsPersona(
							email : "isaccromos@gmail.com",
							apellidoPaterno: "ROMO",
							apellidoMaterno: "RAMIREZ",
							primerNombre: "ISACC",
							segundoNombre: "JUAN",
							sexo: "MASCULINO",
							estadoCivil : "SOLTERO",
							fechaNacimiento : new Date('12/05/1997'),
							identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
							numeroIdentificacionOficial : "ISACO328328",
							rfc : "ISACO328328YT",
							curp : "ISACO328328",
							escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('LICENCIATURA'),
							tiposPersona : [
									SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
							],
					)save(flush: true,failOnError: true)
				
					def clienteIsaccro = new RsCliente(persona: isaccRomo,
							dependencia: EntDependencia.findByClaveDependencia('CFE'),
							numeroDeNomina: 'ISACO342',
					)save(flush: true,failOnError: true)
																					
																					
																					
				// CLIENTE 58
				
					def carlosTeves = new RsPersona(
							email : "carlosteves@gmail.com",
							apellidoPaterno: "TEVES",
							apellidoMaterno: "ROJAS",
							primerNombre: "CARLOS",
							segundoNombre: "HERNAN",
							sexo: "MASCULINO",
							estadoCivil : "CASADO - BIENES MANCOMUNADOS",
							fechaNacimiento : new Date('02/08/1973'),
							identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
							numeroIdentificacionOficial : "CARTI123457",
							rfc : "CARTI123457",
							curp : "CARTI123457",
							escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('CONTADOR'),
							tiposPersona : [
									SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
							],
					)save(flush: true,failOnError: true)
				
					def clientecarlost = new RsCliente(persona: carlosTeves,
							dependencia: EntDependencia.findByClaveDependencia('CFE'),
							numeroDeNomina: 'CARTI462',
					)save(flush: true,failOnError: true)
																							
																							
																							
				// CLIENTE 59
				
					def patricioAraujo = new RsPersona(
							email : "patricioaraujo@gmail.com",
							apellidoPaterno: "SOLIS",
							apellidoMaterno: "SOLAR",
							primerNombre: "LUIS",
							segundoNombre: "RYAN",
							sexo: "MASCULINO",
							estadoCivil : "SOLTERO",
							fechaNacimiento : new Date('18/09/1956'),
							identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
							numeroIdentificacionOficial : "RYAN4567828",
							rfc : "RYAN4567828",
							curp : "RYAN4567828",
							escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('LICENCIATURA'),
							tiposPersona : [
									SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
							],
					)save(flush: true,failOnError: true)
				
					def clientePatricioar = new RsCliente(persona: patricioAraujo,
							dependencia: EntDependencia.findByClaveDependencia('CFE'),
							numeroDeNomina: 'LUI456',
					)save(flush: true,failOnError: true)
																									
																									
																									
				// CLIENTE 60
				
					def danielCacho = new RsPersona(
							email : "danielcacho@gmail.com",
							apellidoPaterno: "CACHO",
							apellidoMaterno: "RIOS",
							primerNombre: "DANIEL",
							segundoNombre: "DANY",
							sexo: "MASCULINO",
							estadoCivil : "SOLTERO",
							fechaNacimiento : new Date('07/03/1980'),
							identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
							numeroIdentificacionOficial : "CACH789328",
							rfc : "CACH789328",
							curp : "CACH789328",
							escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('PREPA'),
							tiposPersona : [
									SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
							],
					)save(flush: true,failOnError: true)
				
					def clienteDanielca = new RsCliente(persona: danielCacho,
							dependencia: EntDependencia.findByClaveDependencia('CFE'),
							numeroDeNomina: 'CACHA543',
					)save(flush: true,failOnError: true)
																																																																				
				
				  // CLIENTE 61
				
						def uliseDavila = new RsPersona(
								email : "ulisedavila@gmail.com",
								apellidoPaterno: "DAVILA",
								apellidoMaterno: "POSTIGO",
								primerNombre: "ULICES",
								segundoNombre: "ULI",
								sexo: "MASCULINO",
								estadoCivil : "CASADO - BIENES MANCOMUNADOS",
								fechaNacimiento : new Date('09/11/1971'),
								identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
								numeroIdentificacionOficial : "ULIS727328328",
								rfc : "ULIS89778",
								curp : "ULIS76878968",
								escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('LIC'),
								tiposPersona : [
										SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
								],
						)save(flush: true,failOnError: true)
				
						def clienteUlicesda = new RsCliente(persona: uliseDavila,
								dependencia: EntDependencia.findByClaveDependencia('CFE'),
								numeroDeNomina: 'ULISSDFYUYUI',
						)save(flush: true,failOnError: true)
									
											
				// CLIENTE 62
				
						def edsonRivera = new RsPersona(
								email : "edsonrivera@gmail.com",
								apellidoPaterno: "RIVERA",
								apellidoMaterno: "DORIGA",
								primerNombre: "EDSON",
								segundoNombre: "EDS",
								sexo: "MASCULINO",
								estadoCivil : "CASADO - BIENES MANCOMUNADOS",
								fechaNacimiento : new Date('09/09/1960'),
								identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
								numeroIdentificacionOficial : "EDSO727328328",
								rfc : "EDSO89778",
								curp : "EDSO76878968",
								escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('MAESTRO'),
								tiposPersona : [
										SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
								],
						)save(flush: true,failOnError: true)
				
						def clienteEdson = new RsCliente(persona: edsonRivera,
								dependencia: EntDependencia.findByClaveDependencia('IMSS'),
								numeroDeNomina: 'EDSOSDFYUYUI',
						)save(flush: true,failOnError: true)
											
													
													
				// CLIENTE 63
				
						def pabloBarrera = new RsPersona(
								email : "pablobarrera@hotmail.com",
								apellidoPaterno: "BARRERA",
								apellidoMaterno: "TORRE",
								primerNombre: "PABLO",
								segundoNombre: "PABLITO",
								sexo: "MASCULINO",
								estadoCivil : "CASADO - BIENES MANCOMUNADOS",
								fechaNacimiento : new Date('09/03/1970'),
								identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
								numeroIdentificacionOficial : "PABL727328328",
								rfc : "PABLQ89778",
								curp : "PABLQ76878968",
								escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('INGENIERO'),
								tiposPersona : [
										SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
								],
						)save(flush: true,failOnError: true)
				
						def clientePablob = new RsCliente(persona: pabloBarrera,
								dependencia: EntDependencia.findByClaveDependencia('IMSS'),
								numeroDeNomina: 'PABLSDFYUYUI',
						)save(flush: true,failOnError: true)
											
													
													
													
				// CLIENTE 64
				
						def efrainJuarez = new RsPersona(
								email : "efrainjuarez@hotmail.com",
								apellidoPaterno: "JUAREZ",
								apellidoMaterno: "BUSTO",
								primerNombre: "EFRAIN",
								segundoNombre: "ALEX",
								sexo: "MASCULINO",
								estadoCivil : "CASADO - BIENES MANCOMUNADOS",
								fechaNacimiento : new Date('09/07/1980'),
								identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
								numeroIdentificacionOficial : "EFRA727328328",
								rfc : "EFRAQ89778",
								curp : "EFRAQ76878968",
								escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('INGENIERO'),
								tiposPersona : [
										SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
								],
						)save(flush: true,failOnError: true)
				
						def clienteEfrainj = new RsCliente(persona: efrainJuarez,
								dependencia: EntDependencia.findByClaveDependencia('IMSS'),
								numeroDeNomina: 'EFRASDFYUYUI',
						)save(flush: true,failOnError: true)
											
													
											
				// CLIENTE 65
				
						def hugoAyala = new RsPersona(
								email : "hugoayala@hotmail.com",
								apellidoPaterno: "AYALA",
								apellidoMaterno: "BUSTO",
								primerNombre: "HUGO",
								segundoNombre: "HUGUIN",
								sexo: "MASCULINO",
								estadoCivil : "CASADO - BIENES MANCOMUNADOS",
								fechaNacimiento : new Date('09/04/1982'),
								identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
								numeroIdentificacionOficial : "HUGA727328328",
								rfc : "HUGAQ89778",
								curp : "HUGAQ76878968",
								escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('ADMIN'),
								tiposPersona : [
										SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
								],
						)save(flush: true,failOnError: true)
				
						def clienteHugoy = new RsCliente(persona: hugoAyala,
								dependencia: EntDependencia.findByClaveDependencia('IMSS'),
								numeroDeNomina: 'HUGASDFYUYUI',
						)save(flush: true,failOnError: true)
											
													
											
				// CLIENTE 66
				
						def rubenSambueza = new RsPersona(
								email : "rubensambueza@hotmail.com",
								apellidoPaterno: "SAMBUESA",
								apellidoMaterno: "BUSTO",
								primerNombre: "RUBEN",
								segundoNombre: "LALO",
								sexo: "MASCULINO",
								estadoCivil : "CASADO - BIENES MANCOMUNADOS",
								fechaNacimiento : new Date('09/03/1982'),
								identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
								numeroIdentificacionOficial : "RUBE727328328",
								rfc : "RUBEQ89778",
								curp : "RUBEQ76878968",
								escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('ADMIN'),
								tiposPersona : [
										SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
								],
						)save(flush: true,failOnError: true)
				
						def clienteRuben = new RsCliente(persona: rubenSambueza,
								dependencia: EntDependencia.findByClaveDependencia('IMSS'),
								numeroDeNomina: 'RUBESDFYUYUI',
						)save(flush: true,failOnError: true)
											
													
											
				// CLIENTE 67
				
						def saulAlvarez = new RsPersona(
								email : "saulalvarez@gmail.com",
								apellidoPaterno: "ALVAREZ",
								apellidoMaterno: "ESTRADA",
								primerNombre: "SAUL",
								segundoNombre: "CANELO",
								sexo: "MASCULINO",
								estadoCivil : "CASADO - BIENES MANCOMUNADOS",
								fechaNacimiento : new Date('03/02/1982'),
								identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
								numeroIdentificacionOficial : "SAUL727328328",
								rfc : "SAULQ89778",
								curp : "SAULQ76878968",
								escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('CONTADORA'),
								tiposPersona : [
										SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
								],
						)save(flush: true,failOnError: true)
				
						def clienteSaulca = new RsCliente(persona: saulAlvarez,
								dependencia: EntDependencia.findByClaveDependencia('CFE'),
								numeroDeNomina: 'SAULSDFYUYUI',
						)save(flush: true,failOnError: true)
											
												
													
					// CLIENTE 68
						
						def ernestoFlores = new RsPersona(
								email : "ernestoflores@hotmail.com",
								apellidoPaterno: "FLORES",
								apellidoMaterno: "SALAS",
								primerNombre: "ERNESTO",
								segundoNombre: "NETO",
								sexo: "MASCULINO",
								estadoCivil : "CASADO - BIENES MANCOMUNADOS",
								fechaNacimiento : new Date('09/01/1982'),
								identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
								numeroIdentificacionOficial : "NETO727328328",
								rfc : "NETOQ89778",
								curp : "NETOQ76878968",
								escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('PREPA'),
								tiposPersona : [
										SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
								],
						)save(flush: true,failOnError: true)
				
						def clienteErnestof = new RsCliente(persona: ernestoFlores,
								dependencia: EntDependencia.findByClaveDependencia('IMSS'),
								numeroDeNomina: 'NETOSDFYUYUI',
						)save(flush: true,failOnError: true)
											
											
													
													
									// CLIENTE 69
											
						def arathGuzman = new RsPersona(
								email : "arathguzman@hotmail.com",
								apellidoPaterno: "GUZMAN",
								apellidoMaterno: "PASTRANA",
								primerNombre: "ARATH",
								segundoNombre: "ARON",
								sexo: "MASCULINO",
								estadoCivil : "CASADO - BIENES MANCOMUNADOS",
								fechaNacimiento : new Date('06/08/1972'),
								identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
								numeroIdentificacionOficial : "ARAT727328328",
								rfc : "ARATQ89778",
								curp : "ARATQ76878968",
								escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('PREPA'),
								tiposPersona : [
										SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
								],
						)save(flush: true,failOnError: true)
				
						def clienteArath = new RsCliente(persona: arathGuzman,
								dependencia: EntDependencia.findByClaveDependencia('IMSS'),
								numeroDeNomina: 'ARATSDFYUYUI',
						)save(flush: true,failOnError: true)
											
													
											
					// CLIENTE 70
							
						def danielaGambordino = new RsPersona(
								email : "danielagambordino@hotmail.com",
								apellidoPaterno: "GAMBORDINO",
								apellidoMaterno: "PEREZ",
								primerNombre: "DANIEL",
								segundoNombre: "DANY",
								sexo: "FEMENINO",
								estadoCivil : "CASADO - BIENES MANCOMUNADOS",
								fechaNacimiento : new Date('09/02/1972'),
								identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
								numeroIdentificacionOficial : "DANI727328328",
								rfc : "DANIQ89778",
								curp : "DANIQ76878968",
								escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('PREPA'),
								tiposPersona : [
										SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
								],
						)save(flush: true,failOnError: true)
				
						def clienteDanielga = new RsCliente(persona: danielaGambordino,
								dependencia: EntDependencia.findByClaveDependencia('IMSS'),
								numeroDeNomina: 'DANISDFYUYUI',
						)save(flush: true,failOnError: true)
												
													
					// CLIENTE 71
								
						def kasandraGarduno = new RsPersona(
								email : "kasandragarduno@gmail.com",
								apellidoPaterno: "GARDUNO",
								apellidoMaterno: "ESTRADA",
								primerNombre: "KASANDRA",
								segundoNombre: "MARIA",
								sexo: "FEMENINO",
								estadoCivil : "CASADO - BIENES MANCOMUNADOS",
								fechaNacimiento : new Date('03/04/1962'),
								identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
								numeroIdentificacionOficial : "KASA727328328",
								rfc : "KASAQ89778",
								curp : "KASAQ76878968",
								escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('CONTADORA'),
								tiposPersona : [
										SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
								],
						)save(flush: true,failOnError: true)
				
						def clienteKasandraga = new RsCliente(persona: kasandraGarduno,
								dependencia: EntDependencia.findByClaveDependencia('CFE'),
								numeroDeNomina: 'KASASDFYUYUI',
						)save(flush: true,failOnError: true)
											
				
						
						// CLIENTE 72
						
						  def fatimaRedondo = new RsPersona(
									email : "fatimaredondo@gmail.com",
									apellidoPaterno: "REDONDO",
									apellidoMaterno: "CAMPOS",
									primerNombre: "FATIMA",
									segundoNombre: "FATI",
									sexo: "FEMENINO",
									estadoCivil : "CASADO - BIENES MANCOMUNADOS",
									fechaNacimiento : new Date('09/05/1970'),
									identificacionOficial : SimCatDocumento.findByClaveDocumento('LICENCIA_CONDUCIR'),
									numeroIdentificacionOficial : "FATI727328328",
									rfc : "FATI89778",
									curp : "FATI76878968",
									escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('QUINDER'),
									tiposPersona : [
											SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
									],
							)save(flush: true,failOnError: true)
					
							def clienteFatima = new RsCliente(persona: fatimaRedondo,
									dependencia: EntDependencia.findByClaveDependencia('CFE'),
									numeroDeNomina: 'FATISDFYUYUI',
							)save(flush: true,failOnError: true)
								
					// CLIENTE 73
						
						  def carlaGonzales = new RsPersona(
									email : "carlagonzales@gmail.com",
									apellidoPaterno: "GONZALES",
									apellidoMaterno: "MAZA",
									primerNombre: "CARLA",
									segundoNombre: "CHARLY",
									sexo: "FEMENINO",
									estadoCivil : "CASADO - BIENES MANCOMUNADOS",
									fechaNacimiento : new Date('09/05/1980'),
									identificacionOficial : SimCatDocumento.findByClaveDocumento('LICENCIA_CONDUCIR'),
									numeroIdentificacionOficial : "CARL727328328",
									rfc : "CARL89778",
									curp : "CARL76878968",
									escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('PRIMARIA'),
									tiposPersona : [
											SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
									],
							)save(flush: true,failOnError: true)
					
							def clienteCarlago = new RsCliente(persona: carlaGonzales,
									dependencia: EntDependencia.findByClaveDependencia('CFE'),
									numeroDeNomina: 'CARLSDFYUYUI',
							)save(flush: true,failOnError: true)
								
										
										
					// CLIENTE 74
						
						 def rocioSanchez = new RsPersona(
									email : "rociosanchez@gmail.com",
									apellidoPaterno: "SANCHEZ",
									apellidoMaterno: "OCHOA",
									primerNombre: "ROCIO",
									segundoNombre: "CHIO",
									sexo: "FEMENINO",
									estadoCivil : "CASADO - BIENES MANCOMUNADOS",
									fechaNacimiento : new Date('09/07/1985'),
									identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
									numeroIdentificacionOficial : "ROSY727328328",
									rfc : "ROSY89778",
									curp : "ROSY76878968",
									escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('SECUNDARIA'),
									tiposPersona : [
											SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
									],
							)save(flush: true,failOnError: true)
					
							def clienteRociosa = new RsCliente(persona: rocioSanchez,
									dependencia: EntDependencia.findByClaveDependencia('CFE'),
									numeroDeNomina: 'ROSYSDFYUYUI',
							)save(flush: true,failOnError: true)
						
										
										
						// CLIENTE 75
							
					def jeniFerLopez = new RsPersona(
								email : "jeniferlopez@gmail.com",
								apellidoPaterno: "LOPEZ",
								apellidoMaterno: "CORONA",
								primerNombre: "JENI",
								segundoNombre: "FERNANDA",
								sexo: "FEMENINO",
								estadoCivil : "SOLTERO",
								fechaNacimiento : new Date('09/09/1973'),
								identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
								numeroIdentificacionOficial : "JENI727328328",
								rfc : "JENI89778",
								curp : "JENI76878968",
								escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('LIC'),
								tiposPersona : [
										SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
								],
						)save(flush: true,failOnError: true)
				
						def clienteJenifer = new RsCliente(persona: jeniFerLopez,
								dependencia: EntDependencia.findByClaveDependencia('CFE'),
								numeroDeNomina: 'JENISDFYUYUI',
						)save(flush: true,failOnError: true)
								
										
						// CLIENTE 76
							
						   def gustavoAyon = new RsPersona(
									email : "gustavoayon@gmail.com",
									apellidoPaterno: "AYON",
									apellidoMaterno: "CASTRO",
									primerNombre: "GUSTAVO",
									segundoNombre: "TAVO",
									sexo: "MASCULINO",
									estadoCivil : "SOLTERO",
									fechaNacimiento : new Date('09/20/1967'),
									identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
									numeroIdentificacionOficial : "TAVO727328328",
									rfc : "TAVO89778",
									curp : "TAVO76878968",
									escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('LIC'),
									tiposPersona : [
											SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
									],
							)save(flush: true,failOnError: true)
					
							def clienteGustavoay = new RsCliente(persona: gustavoAyon,
									dependencia: EntDependencia.findByClaveDependencia('CFE'),
									numeroDeNomina: 'TAVOSDFYUYUI',
							)save(flush: true,failOnError: true)
										
												
										
					// CLIENTE 77
									
					def joseClemente = new RsPersona(
								email : "joseclemente@gmail.com",
								apellidoPaterno: "CLEMENTE",
								apellidoMaterno: "CASTRO",
								primerNombre: "JOSE",
								segundoNombre: "MARCO",
								sexo: "MASCULINO",
								estadoCivil : "SOLTERO",
								fechaNacimiento : new Date('09/07/1967'),
								identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
								numeroIdentificacionOficial : "JOSC727328328",
								rfc : "JOSC89778",
								curp : "JOSC76878968",
								escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('LIC'),
								tiposPersona : [
										SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
								],
						)save(flush: true,failOnError: true)
				
						def clienteJosecle = new RsCliente(persona: joseClemente,
								dependencia: EntDependencia.findByClaveDependencia('CFE'),
								numeroDeNomina: 'JOSCSDFYUYUI',
						)save(flush: true,failOnError: true)
										
												
												
					// CLIENTE 78
					
							def federicoLeon = new RsPersona(
									email : "federicoleon@gmail.com",
									apellidoPaterno: "LEON",
									apellidoMaterno: "MENCHACA",
									primerNombre: "FEDERICO",
									segundoNombre: "CARLOS",
									sexo: "MASCULINO",
									estadoCivil : "CASADO - BIENES MANCOMUNADOS",
									fechaNacimiento : new Date('09/03/1960'),
									identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
									numeroIdentificacionOficial : "FEDE727328328",
									rfc : "FEDE89778",
									curp : "FEDE76878968",
									escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('MAESTRO'),
									tiposPersona : [
											SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
									],
							)save(flush: true,failOnError: true)
					
							def clienteFederico = new RsCliente(persona: federicoLeon,
									dependencia: EntDependencia.findByClaveDependencia('CFE'),
									numeroDeNomina: 'FEDESDFYUYUI',
							)save(flush: true,failOnError: true)
										
												
												
					// CLIENTE 79
					
						def rafaelMontes = new RsPersona(
								email : "rafaelmontes@gmail.com",
								apellidoPaterno: "MONTES",
								apellidoMaterno: "GALINDO",
								primerNombre: "RAFAEL",
								segundoNombre: "JUAN",
								sexo: "MASCULINO",
								estadoCivil : "CASADO - BIENES MANCOMUNADOS",
								fechaNacimiento : new Date('09/11/1968'),
								identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
								numeroIdentificacionOficial : "RAFM727328328",
								rfc : "RAFM89778",
								curp : "RAFM76878968",
								escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('AMERICA'),
								tiposPersona : [
										SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
								],
						)save(flush: true,failOnError: true)
				
						def clienteRafaelm = new RsCliente(persona: rafaelMontes,
								dependencia: EntDependencia.findByClaveDependencia('IMSS'),
								numeroDeNomina: 'RAFMSDFYUYUI',
						)save(flush: true,failOnError: true)
									
											
									
				  // CLIENTE 80
				
						def claudioSuarez = new RsPersona(
								email : "claudiosuarez@gmail.com",
								apellidoPaterno: "SUAREZ",
								apellidoMaterno: "POSTIGO",
								primerNombre: "CLAUDIO",
								segundoNombre: "RICARDO",
								sexo: "MASCULINO",
								estadoCivil : "CASADO - BIENES MANCOMUNADOS",
								fechaNacimiento : new Date('09/03/1971'),
								identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
								numeroIdentificacionOficial : "CLAD727328328",
								rfc : "CLAD89778",
								curp : "CLAD76878968",
								escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('LIC'),
								tiposPersona : [
										SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
								],
						)save(flush: true,failOnError: true)
				
						def clienteClaudios = new RsCliente(persona: claudioSuarez,
								dependencia: EntDependencia.findByClaveDependencia('CFE'),
								numeroDeNomina: 'CLADSDFYUYUI',
						)save(flush: true,failOnError: true)
									
				
						// CLIENTE 81
						
							def raulosOsrio = new RsPersona(
									email : "raulososrio@gmail.com",
									apellidoPaterno: "OSORIO",
									apellidoMaterno: "POSTIGO",
									primerNombre: "RAUL",
									segundoNombre: "FERNANDO",
									sexo: "MASCULINO",
									estadoCivil : "CASADO - BIENES MANCOMUNADOS",
									fechaNacimiento : new Date('09/11/1981'),
									identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
									numeroIdentificacionOficial : "RULO727328328",
									rfc : "RULO89778",
									curp : "RULO76878968",
									escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('LIC'),
									tiposPersona : [
											SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
									],
							)save(flush: true,failOnError: true)
					
							def clienteRaulos = new RsCliente(persona: raulosOsrio,
									dependencia: EntDependencia.findByClaveDependencia('CFE'),
									numeroDeNomina: 'RULOSDFYUYUI',
							)save(flush: true,failOnError: true)
											
														
						// CLIENTE 82
						
								def juanCamil = new RsPersona(
										email : "juancamil@gmail.com",
										apellidoPaterno: "CAMIL",
										apellidoMaterno: "DORIGA",
										primerNombre: "JUAN",
										segundoNombre: "RENE",
										sexo: "MASCULINO",
										estadoCivil : "CASADO - BIENES MANCOMUNADOS",
										fechaNacimiento : new Date('09/09/1960'),
										identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
										numeroIdentificacionOficial : "CAMI727328328",
										rfc : "CAMI89778",
										curp : "CAMI76878968",
										escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('MAESTRO'),
										tiposPersona : [
												SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
										],
								)save(flush: true,failOnError: true)
						
								def clienteJuancami = new RsCliente(persona: juanCamil,
										dependencia: EntDependencia.findByClaveDependencia('IMSS'),
										numeroDeNomina: 'CAMISDFYUYUI',
								)save(flush: true,failOnError: true)
													
															
															
						// CLIENTE 83
						
								def orlandoVilla = new RsPersona(
										email : "orlandovilla@hotmail.com",
										apellidoPaterno: "VILLA",
										apellidoMaterno: "TORRE",
										primerNombre: "ORLANDO",
										segundoNombre: "PABLITO",
										sexo: "MASCULINO",
										estadoCivil : "CASADO - BIENES MANCOMUNADOS",
										fechaNacimiento : new Date('09/03/1980'),
										identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
										numeroIdentificacionOficial : "ORLA727328328",
										rfc : "ORLAQ89778",
										curp : "ORLAQ76878968",
										escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('INGENIERO'),
										tiposPersona : [
												SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
										],
								)save(flush: true,failOnError: true)
						
								def clienteOrlandovi = new RsCliente(persona: orlandoVilla,
										dependencia: EntDependencia.findByClaveDependencia('IMSS'),
										numeroDeNomina: 'ORLASDFYUYUI',
								)save(flush: true,failOnError: true)
													
															
															
															
						// CLIENTE 84
						
								def julioChavez = new RsPersona(
										email : "juliochavez@hotmail.com",
										apellidoPaterno: "CHAVEZ",
										apellidoMaterno: "BUSTO",
										primerNombre: "JULIO",
										segundoNombre: "CESAR",
										sexo: "MASCULINO",
										estadoCivil : "CASADO - BIENES MANCOMUNADOS",
										fechaNacimiento : new Date('09/07/1980'),
										identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
										numeroIdentificacionOficial : "JCCH727328328",
										rfc : "JCCHQ89778",
										curp : "JCCHQ76878968",
										escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('INGENIERO'),
										tiposPersona : [
												SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
										],
								)save(flush: true,failOnError: true)
						
								def clienteJulioc = new RsCliente(persona: julioChavez,
										dependencia: EntDependencia.findByClaveDependencia('IMSS'),
										numeroDeNomina: 'JCCHSDFYUYUI',
								)save(flush: true,failOnError: true)
													
															
													
						// CLIENTE 85
						
								def carlosSalcidos = new RsPersona(
										email : "carlossalcidos@hotmail.com",
										apellidoPaterno: "SALCIDO",
										apellidoMaterno: "BUSTO",
										primerNombre: "CARLOS",
										segundoNombre: "CARLO",
										sexo: "MASCULINO",
										estadoCivil : "CASADO - BIENES MANCOMUNADOS",
										fechaNacimiento : new Date('09/04/1982'),
										identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
										numeroIdentificacionOficial : "SALS727328328",
										rfc : "SALSQ89778",
										curp : "SALSQ76878968",
										escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('ADMIN'),
										tiposPersona : [
												SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
										],
								)save(flush: true,failOnError: true)
						
								def clienteCarlosal = new RsCliente(persona: carlosSalcidos,
										dependencia: EntDependencia.findByClaveDependencia('IMSS'),
										numeroDeNomina: 'SALSSDFYUYUI',
								)save(flush: true,failOnError: true)
													
															
													
					// CLIENTE 86
					
							def adolfoBautista = new RsPersona(
									email : "adolfobautista@hotmail.com",
									apellidoPaterno: "BAUTISTA",
									apellidoMaterno: "BUSTO",
									primerNombre: "ADOLFO",
									segundoNombre: "BOFO",
									sexo: "MASCULINO",
									estadoCivil : "CASADO - BIENES MANCOMUNADOS",
									fechaNacimiento : new Date('09/03/1982'),
									identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
									numeroIdentificacionOficial : "BOFO727328328",
									rfc : "BOFOQ89778",
									curp : "BOFOQ76878968",
									escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('ADMIN'),
									tiposPersona : [
											SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
									],
							)save(flush: true,failOnError: true)
					
							def clienteAdolfoba = new RsCliente(persona: adolfoBautista,
									dependencia: EntDependencia.findByClaveDependencia('IMSS'),
									numeroDeNomina: 'BOFOSDFYUYUI',
							)save(flush: true,failOnError: true)
												
														
												
					// CLIENTE 87
					
							def enriquereMarquez = new RsPersona(
									email : "enriquemen@gmail.com",
									apellidoPaterno: "MARQUEZ",
									apellidoMaterno: "ESTRADA",
									primerNombre: "ENRIQUE",
									segundoNombre: "JUAN",
									sexo: "MASCULINO",
									estadoCivil : "CASADO - BIENES MANCOMUNADOS",
									fechaNacimiento : new Date('03/02/1982'),
									identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
									numeroIdentificacionOficial : "ENMA727328328",
									rfc : "ENMAQ89778",
									curp : "ENMAQ76878968",
									escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('CONTADORA'),
									tiposPersona : [
											SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
									],
							)save(flush: true,failOnError: true)
					
							def clienteEnriquema = new RsCliente(persona: enriquereMarquez,
									dependencia: EntDependencia.findByClaveDependencia('CFE'),
									numeroDeNomina: 'ENMASDFYUYUI',
							)save(flush: true,failOnError: true)
												
													
														
						// CLIENTE 88
							
							def robinPersie = new RsPersona(
									email : "robinpersie@hotmail.com",
									apellidoPaterno: "PERSIE",
									apellidoMaterno: "SALAS",
									primerNombre: "ROBIN",
									segundoNombre: "BATMAN",
									sexo: "MASCULINO",
									estadoCivil : "CASADO - BIENES MANCOMUNADOS",
									fechaNacimiento : new Date('09/01/1982'),
									identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
									numeroIdentificacionOficial : "ROPE727328328",
									rfc : "ROPEQ89778",
									curp : "ROPEQ76878968",
									escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('PREPA'),
									tiposPersona : [
											SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
									],
							)save(flush: true,failOnError: true)
					
							def clienteRobin = new RsCliente(persona: robinPersie,
									dependencia: EntDependencia.findByClaveDependencia('IMSS'),
									numeroDeNomina: 'ROPESDFYUYUI',
							)save(flush: true,failOnError: true)
												
												
														
														
					// CLIENTE 89
												
							def gerardPique = new RsPersona(
									email : "gerardpique@hotmail.com",
									apellidoPaterno: "PIQUE",
									apellidoMaterno: "PASTRANA",
									primerNombre: "GERAR",
									segundoNombre: "GERARDO",
									sexo: "MASCULINO",
									estadoCivil : "CASADO - BIENES MANCOMUNADOS",
									fechaNacimiento : new Date('06/08/1972'),
									identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
									numeroIdentificacionOficial : "GERA727328328",
									rfc : "GERAQ89778",
									curp : "GERAQ76878968",
									escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('PREPA'),
									tiposPersona : [
											SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
									],
							)save(flush: true,failOnError: true)
					
							def clienteGerar = new RsCliente(persona: gerardPique,
									dependencia: EntDependencia.findByClaveDependencia('IMSS'),
									numeroDeNomina: 'GERASDFYUYUI',
							)save(flush: true,failOnError: true)
												
														
												
						// CLIENTE 90
								
							def cristianoRonaldo = new RsPersona(
									email : "cristianoronaldo@hotmail.com",
									apellidoPaterno: "RONALDO",
									apellidoMaterno: "PEREZ",
									primerNombre: "CRISTIANO",
									segundoNombre: "IKER",
									sexo: "MASCULINO",
									estadoCivil : "CASADO - BIENES MANCOMUNADOS",
									fechaNacimiento : new Date('09/02/1972'),
									identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
									numeroIdentificacionOficial : "CRIT727328328",
									rfc : "CRITQ89778",
									curp : "CRITQ76878968",
									escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('PREPA'),
									tiposPersona : [
											SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
									],
							)save(flush: true,failOnError: true)
					
							def clienteCristiano = new RsCliente(persona: cristianoRonaldo,
									dependencia: EntDependencia.findByClaveDependencia('IMSS'),
									numeroDeNomina: 'CRITSDFYUYUI',
							)save(flush: true,failOnError: true)
					
							
									
					// CLIENTE 91
					
							def gabyCruz = new RsPersona(
									email : "gabycruz@gmail.com",
									apellidoPaterno: "CRUZ",
									apellidoMaterno: "POSTIGO",
									primerNombre: "GABRIELA",
									segundoNombre: "GABY",
									sexo: "FEMENINO",
									estadoCivil : "CASADO - BIENES MANCOMUNADOS",
									fechaNacimiento : new Date('09/11/1971'),
									identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
									numeroIdentificacionOficial : "GABY727328328",
									rfc : "GABY89778",
									curp : "GABY76878968",
									escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('LIC'),
									tiposPersona : [
											SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
									],
							)save(flush: true,failOnError: true)
					
							def clienteGabycr = new RsCliente(persona: gabyCruz,
									dependencia: EntDependencia.findByClaveDependencia('CFE'),
									numeroDeNomina: 'GABYSDFYUYUI',
							)save(flush: true,failOnError: true)
										
												
					// CLIENTE 92
					
							def selenaGomez = new RsPersona(
									email : "selenagomez@gmail.com",
									apellidoPaterno: "GOMEZ",
									apellidoMaterno: "DORIGA",
									primerNombre: "SELENE",
									segundoNombre: "SELENA",
									sexo: "FEMENINO",
									estadoCivil : "CASADO - BIENES MANCOMUNADOS",
									fechaNacimiento : new Date('09/09/1960'),
									identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
									numeroIdentificacionOficial : "SELE727328328",
									rfc : "SELE89778",
									curp : "SELE76878968",
									escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('MAESTRO'),
									tiposPersona : [
											SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
									],
							)save(flush: true,failOnError: true)
					
							def clienteSeleneg = new RsCliente(persona: selenaGomez,
									dependencia: EntDependencia.findByClaveDependencia('IMSS'),
									numeroDeNomina: 'SELESDFYUYUI',
							)save(flush: true,failOnError: true)
												
														
														
					// CLIENTE 93
					
							def ximenaMoreno = new RsPersona(
									email : "ximenamoreno@hotmail.com",
									apellidoPaterno: "MORENO",
									apellidoMaterno: "TORRE",
									primerNombre: "XIMENA",
									segundoNombre: "CARLA",
									sexo: "FEMENINO",
									estadoCivil : "CASADO - BIENES MANCOMUNADOS",
									fechaNacimiento : new Date('09/03/1970'),
									identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
									numeroIdentificacionOficial : "XIME727328328",
									rfc : "XIMEQ89778",
									curp : "XIMEQ76878968",
									escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('INGENIERO'),
									tiposPersona : [
											SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
									],
							)save(flush: true,failOnError: true)
					
							def clienteXimena = new RsCliente(persona: ximenaMoreno,
									dependencia: EntDependencia.findByClaveDependencia('IMSS'),
									numeroDeNomina: 'XIMESDFYUYUI',
							)save(flush: true,failOnError: true)
												
														
														
														
					// CLIENTE 94
					
							def antoniaRico = new RsPersona(
									email : "antoniarico@hotmail.com",
									apellidoPaterno: "RICO",
									apellidoMaterno: "BUSTO",
									primerNombre: "ANTONIA",
									segundoNombre: "TONA",
									sexo: "FEMENINO",
									estadoCivil : "CASADO - BIENES MANCOMUNADOS",
									fechaNacimiento : new Date('09/07/1980'),
									identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
									numeroIdentificacionOficial : "TONA727328328",
									rfc : "TONAQ89778",
									curp : "TONAQ76878968",
									escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('INGENIERO'),
									tiposPersona : [
											SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
									],
							)save(flush: true,failOnError: true)
					
							def clienteAntoniari = new RsCliente(persona: antoniaRico,
									dependencia: EntDependencia.findByClaveDependencia('IMSS'),
									numeroDeNomina: 'TONASDFYUYUI',
							)save(flush: true,failOnError: true)
												
														
												
					// CLIENTE 95
					
							def paolaEspainoza = new RsPersona(
									email : "paolaespainoza@hotmail.com",
									apellidoPaterno: "ESPINOZA",
									apellidoMaterno: "BUSTO",
									primerNombre: "PAOLA",
									segundoNombre: "PAO",
									sexo: "FEMENINO",
									estadoCivil : "CASADO - BIENES MANCOMUNADOS",
									fechaNacimiento : new Date('09/04/1982'),
									identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
									numeroIdentificacionOficial : "PAOL727328328",
									rfc : "PAOLQ89778",
									curp : "PAOLQ76878968",
									escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('ADMIN'),
									tiposPersona : [
											SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
									],
							)save(flush: true,failOnError: true)
					
							def clientePaoes = new RsCliente(persona: paolaEspainoza,
									dependencia: EntDependencia.findByClaveDependencia('IMSS'),
									numeroDeNomina: 'PAOLSDFYUYUI',
							)save(flush: true,failOnError: true)
												
														
												
					// CLIENTE 96
					
							def juliaRoberts = new RsPersona(
									email : "juliaroberts@hotmail.com",
									apellidoPaterno: "ROBERTS",
									apellidoMaterno: "BUSTO",
									primerNombre: "JULIA",
									segundoNombre: "JULIETA",
									sexo: "FEMENINO",
									estadoCivil : "CASADO - BIENES MANCOMUNADOS",
									fechaNacimiento : new Date('09/03/1982'),
									identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
									numeroIdentificacionOficial : "JULI727328328",
									rfc : "JULIQ89778",
									curp : "JULIQ76878968",
									escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('ADMIN'),
									tiposPersona : [
											SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
									],
							)save(flush: true,failOnError: true)
					
							def clienteJuliaro = new RsCliente(persona: juliaRoberts,
									dependencia: EntDependencia.findByClaveDependencia('IMSS'),
									numeroDeNomina: 'JULISDFYUYUI',
							)save(flush: true,failOnError: true)
												
														
												
					// CLIENTE 97
					
							def rosaLombardo = new RsPersona(
									email : "rosalombardo@gmail.com",
									apellidoPaterno: "LOMBARDO",
									apellidoMaterno: "ESTRADA",
									primerNombre: "ROSA",
									segundoNombre: "MARIA",
									sexo: "FEMENINO",
									estadoCivil : "CASADO - BIENES MANCOMUNADOS",
									fechaNacimiento : new Date('03/02/1982'),
									identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
									numeroIdentificacionOficial : "ROSA727328328",
									rfc : "ROSAQ89778",
									curp : "ROSAQ76878968",
									escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('CONTADORA'),
									tiposPersona : [
											SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
									],
							)save(flush: true,failOnError: true)
					
							def clienteRosalo = new RsCliente(persona: rosaLombardo,
									dependencia: EntDependencia.findByClaveDependencia('CFE'),
									numeroDeNomina: 'ROSASDFYUYUI',
							)save(flush: true,failOnError: true)
												
													
														
						// CLIENTE 98
							
							def taniaRincon = new RsPersona(
									email : "taniarincon@hotmail.com",
									apellidoPaterno: "RINCON",
									apellidoMaterno: "SALAS",
									primerNombre: "TANIA",
									segundoNombre: "JIMENA",
									sexo: "FEMENINO",
									estadoCivil : "CASADO - BIENES MANCOMUNADOS",
									fechaNacimiento : new Date('09/01/1982'),
									identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
									numeroIdentificacionOficial : "TANI727328328",
									rfc : "TANIQ89778",
									curp : "TANIQ76878968",
									escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('PREPA'),
									tiposPersona : [
											SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
									],
							)save(flush: true,failOnError: true)
					
							def clienteTaniari = new RsCliente(persona: taniaRincon,
									dependencia: EntDependencia.findByClaveDependencia('IMSS'),
									numeroDeNomina: 'TANISDFYUYUI',
							)save(flush: true,failOnError: true)
												
												
														
														
										// CLIENTE 99
												
							def jesusOchoa = new RsPersona(
									email : "chucho2@hotmail.com",
									apellidoPaterno: "OCHOA",
									apellidoMaterno: "PASTRANA",
									primerNombre: "JESUS",
									segundoNombre: "CHUCHO",
									sexo: "MASCULINO",
									estadoCivil : "CASADO - BIENES MANCOMUNADOS",
									fechaNacimiento : new Date('06/08/1972'),
									identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
									numeroIdentificacionOficial : "JESO727328328",
									rfc : "JESOQ89778",
									curp : "JESOQ76878968",
									escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('PREPA'),
									tiposPersona : [
											SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
									],
							)save(flush: true,failOnError: true)
					
							def clienteJesusoc = new RsCliente(persona: jesusOchoa,
									dependencia: EntDependencia.findByClaveDependencia('IMSS'),
									numeroDeNomina: 'JESOSDFYUYUI',
							)save(flush: true,failOnError: true)
												
														
												
						// CLIENTE 100
								
							def emilianoZapata = new RsPersona(
									email : "zapata@hotmail.com",
									apellidoPaterno: "ZAPATA",
									apellidoMaterno: "PEREZ",
									primerNombre: "EMILIANO",
									segundoNombre: "ERNESTO",
									sexo: "MASCULINO",
									estadoCivil : "CASADO - BIENES MANCOMUNADOS",
									fechaNacimiento : new Date('09/02/1972'),
									identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
									numeroIdentificacionOficial : "EMILI727328328",
									rfc : "EMILIQ89778",
									curp : "EMILIQ76878968",
									escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('PREPA'),
									tiposPersona : [
											SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
									],
							)save(flush: true,failOnError: true)
					
							def clienteEmilianoza = new RsCliente(persona: emilianoZapata,
									dependencia: EntDependencia.findByClaveDependencia('IMSS'),
									numeroDeNomina: 'EMILSDFYUYUI',
							)save(flush: true,failOnError: true)
							*/
						
	
		redirect(action: "list")
    }

}
