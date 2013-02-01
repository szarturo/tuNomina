import com.rs.gral.*
import com.sim.catalogo.*
import com.sim.usuario.*
import com.sim.entidad.*
import com.sim.cliente.*
import com.sim.credito.*
import com.sim.empresa.*
import com.sim.producto.*
import com.sim.calendario.*
import com.sim.pfin.*
import com.sim.listacobro.*

import test.*
import abltutorial.*

import org.codehaus.groovy.grails.commons.ApplicationHolder as AH

class BootStrap {

    def listaCobroService

	def init = { servletContext ->

		new SimCatTipoEmp(claveTipoEmpleadoDep:  'NA',
				nombreTipoEmpleadoDep: 'NO APLICA',
				).save(failOnError: true)

		new SimCatTipoEmp(claveTipoEmpleadoDep:  'HOMOLOGADOS',
				nombreTipoEmpleadoDep: 'HOMOLOGADOS',
				).save(failOnError: true)

		new SimCatTipoEmp(claveTipoEmpleadoDep:  'ESTATALES',
				nombreTipoEmpleadoDep: 'ESTATALES',
				).save(failOnError: true)

		new SimCatTipoEmp(claveTipoEmpleadoDep:  'CENTRALIZADOS',
				nombreTipoEmpleadoDep: 'CENTRALIZADOS',
				).save(failOnError: true)
		
		new SimCatTipoEmp(claveTipoEmpleadoDep:  'FEDERALES',
				nombreTipoEmpleadoDep: 'FEDERALES',
				).save(failOnError: true)

		new SimCatTipoPersona(claveTipoPersona:  'AVAL',
				nombreTipoPersona: 'AVAL',
				descripcionTipoPersona: 'DESCRIPCION AVAL',
				).save(failOnError: true)

		new SimCatTipoPersona(claveTipoPersona:  'CLIENTE',
				nombreTipoPersona: 'CLIENTE',
				descripcionTipoPersona: 'DESCRIPCION CLIENTE',
				).save(failOnError: true)

		new SimCatTipoPersona(claveTipoPersona:  'USUARIO',
				nombreTipoPersona: 'USUARIO',
				descripcionTipoPersona: 'USUARIO DEL SISTEMA',
				).save(failOnError: true)

		new SimCatTipoPersona(claveTipoPersona:  'EMPLEADO',
				nombreTipoPersona: 'EMPLEADO',
				descripcionTipoPersona: 'EMPLEADO DE LA EMPRESA',
				).save(failOnError: true)

		new SimCatTipoPersona(claveTipoPersona:  'REFCLIENTE',
				nombreTipoPersona: 'REFERENCIA DEL CLIENTE',
				descripcionTipoPersona: 'REFERENCIA DEL CLIENTE',
				).save(failOnError: true)

		new SimCatTipoPersona(claveTipoPersona:  'UEF',
				nombreTipoPersona: 'UEF DEL CLIENTE',
				descripcionTipoPersona: 'UNIDAD ECONOMICA FAMILIAR DEL CLIENTE',
				).save(failOnError: true)

		new SimCatTipoPersona(claveTipoPersona:  'GARDEP',
				nombreTipoPersona: 'GARANTE DEPOSITARIO',
				descripcionTipoPersona: 'GARANTE DEPOSITARIO DEL CLIENTE',
				).save(failOnError: true)


		def adminRole = new Rol(authority: 'ROLE_ADMIN', name: 'Admin')
		adminRole.id = 'ROLE_ADMIN'
		adminRole.save(failOnError: true)

		def userRole = new Rol(authority: 'ROLE_USER', name: 'User')
		userRole.id = 'ROLE_USER'
		userRole.save(failOnError: true)
		
		def managerRole = new Rol(authority: 'ROLE_MANAGER', name: 'Manager')
		managerRole.id = 'ROLE_MANAGER'
		managerRole.save(failOnError: true)
		
		def mesaControlRole = new Rol(authority: 'ROLE_MESA_CONTROL', name: 'Mesa de Control')
		mesaControlRole.id = 'ROLE_MESA_CONTROL'
		mesaControlRole.save(failOnError: true)

		def controlCalidadRole = new Rol(authority: 'ROLE_CONTROL_CALIDAD', name: 'Control de Calidad')
		controlCalidadRole.id = 'ROLE_CONTROL_CALIDAD'
		controlCalidadRole.save(failOnError: true)

		def dispersionRole = new Rol(authority: 'ROLE_DISPERSION', name: 'Tesoreria - Dispersion')
		dispersionRole.id = 'ROLE_DISPERSION'
		dispersionRole.save(failOnError: true)

		def callCenterRole = new Rol(authority: 'ROLE_CALLCENTER', name: 'Call Center')
		callCenterRole.id = 'ROLE_CALLCENTER'
		callCenterRole.save(failOnError: true)

		def operadorRole = new Rol(authority: 'ROLE_OPERADOR', name: 'Operador')
		operadorRole.id = 'ROLE_OPERADOR'
		operadorRole.save(failOnError: true)

		def cobranzaRole = new Rol(authority: 'ROLE_COBRANZA', name: 'Tesoreria - Cobranza')
		cobranzaRole.id = 'ROLE_COBRANZA'
		cobranzaRole.save(failOnError: true)

		def usuarioAdmin = new Usuario(username: 'admin', enabled: true, password: '1234')
		usuarioAdmin.save(failOnError: true)

		UsuarioRol.create usuarioAdmin, adminRole, true
		
		assert Usuario.count() == 1
		assert Rol.count() == 9
		assert UsuarioRol.count() == 1
		
		//DA DE ALTA UNA PERSONA Y LE ASIGNA EL USUARIO ADMINISTRADOR
		def adminPersona = new RsPersona(
				apellidoPaterno: "ADMINISTRADOR",
				primerNombre: "MICROFINANCIERAS",
				email : "sistema.microfinanciera@gmail.com",
				rfc : "AAAA8688",
				tiposPersona : [
					SimCatTipoPersona.findByClaveTipoPersona('USUARIO')
				],
				usuario : usuarioAdmin).save(failOnError: true)
				
				
		//USUARIOS DADOS DE ALTA POR ACTIVITI
		def usuarioKermit = new Usuario(username: 'kermit', enabled: true, password: 'kermit')
		usuarioKermit.save(failOnError: true)
		UsuarioRol.create usuarioKermit, userRole, true
		
		def usuarioFozzie = new Usuario(username: 'fozzie', enabled: true, password: 'fozzie')
		usuarioFozzie.save(failOnError: true)
		UsuarioRol.create usuarioFozzie, managerRole, true

		def usuarioPeter = new Usuario(username: 'peter', enabled: true, password: 'peter')
		usuarioPeter.save(failOnError: true)
		UsuarioRol.create usuarioPeter, userRole, true
		
		def usuarioRuben = new Usuario(username: 'ruben', enabled: true, password: 'ruben')
		usuarioRuben.save(failOnError: true)
		UsuarioRol.create usuarioRuben, mesaControlRole, true
		
		def usuarioRaul = new Usuario(username: 'raul', enabled: true, password: 'raul')
		usuarioRaul.save(failOnError: true)
		UsuarioRol.create usuarioRaul, controlCalidadRole, true

		def usuarioRamon = new Usuario(username: 'ramon', enabled: true, password: 'ramon')
		usuarioRamon.save(failOnError: true)
		UsuarioRol.create usuarioRamon, dispersionRole, true

		def usuarioRosa = new Usuario(username: 'rosa', enabled: true, password: 'rosa')
		usuarioRosa.save(failOnError: true)
		UsuarioRol.create usuarioRosa, callCenterRole, true

		def usuarioRoman = new Usuario(username: 'roman', enabled: true, password: 'roman')
		usuarioRoman.save(failOnError: true)
		UsuarioRol.create usuarioRoman, cobranzaRole, true

		//DA DE ALTA UNA PERSONA Y LE ASIGNA EL USUARIO KERMIT
		def kermitPersona = new RsPersona(
				apellidoPaterno: "Perez",
				primerNombre: "Kermit",
				email : "mrugerio@gmail.com",
				rfc : "AAAZ444",
				tiposPersona : [
					SimCatTipoPersona.findByClaveTipoPersona('USUARIO')
				],
				usuario : usuarioKermit).save(failOnError: true)

		//IMPLEMENTACION DE SEGURIDAD A NIVEL Dynamic request maps
		//new Requestmap(url: '/user/**', configAttribute: 'ROLE_ADMIN').save(failOnError: true)
		//new Requestmap(url: '/rsConfGpoEmpresa/**', configAttribute: 'ROLE_USER').save(failOnError: true)
		new Requestmap(url: '/simCatBanco/create', configAttribute: 'ROLE_ADMIN').save(failOnError: true)
		
		
		//ACTIVITI
		
		["mail.smtp.auth":"true",
			"mail.smtp.socketFactory.port":"465",
			"mail.smtp.socketFactory.class":"javax.net.ssl.SSLSocketFactory",
			"mail.smtp.socketFactory.fallback":"false",
			"mail.smtp.starttls.required": "true"].each { k, v ->
			System.setProperty k, v
			}
			
			
		new SimCatMetodoCalculo(claveMetodoCalculo:  'METODO01',
				nombreMetodoCalculo: 'PAGOS IGUALES DE CAPITAL E INTERES',
				descripcionMetodoCalculo: 'INTERES GLOBAL',
				).save(failOnError: true)

		new SimCatMetodoCalculo(claveMetodoCalculo:  'METODO02',
				nombreMetodoCalculo: 'PAGOS IGUALES DE CAPITAL, CALCULO DE INTERES SOBRE EL SALDO INSOLUTO',
				descripcionMetodoCalculo: 'INTERES SOBRE SALDO INSOLUTO',
				).save(failOnError: true)

		new SimCatMetodoCalculo(claveMetodoCalculo:  'METODO05',
				nombreMetodoCalculo: 'PAGOS IGUALES DE CAPITAL MAS INTERES, CALCULO DE INTERES SOBRE EL SALDO INSOLUTO',
				descripcionMetodoCalculo: 'SIN RECALCULO DE INTERESES POR PAGOS ADELANTADOS, CONOCIDO COMO METODO FRANCES',
				).save(failOnError: true)

		new SimCatMetodoCalculo(claveMetodoCalculo:  'METODO06',
				nombreMetodoCalculo: 'PAGOS IGUALES DE CAPITAL MAS INTERES, CALCULO DE INTERES SOBRE EL SALDO INSOLUTO RI',
				descripcionMetodoCalculo: 'RECALCULO DE INTERESES POR PAGOS ADELANTADOS, CONOCIDO COMO METODO FRANCES',
				).save(failOnError: true)
				
		new SimCatPeriodicidad(clavePeriodicidad:  'SEMANA',
					nombrePeriodicidad: 'SEMANAL',
					cantidadPagos: 52,
					numeroDias: 7,
					).save(failOnError: true)
	
		new SimCatPeriodicidad(clavePeriodicidad:  'CATORCENA',
					nombrePeriodicidad: 'CATORCENAL',
					cantidadPagos: 26,
					numeroDias: 14,
					).save(failOnError: true)
	
		SimCatPeriodicidad periodicidadQuincena = new SimCatPeriodicidad(
					clavePeriodicidad:  'QUINCENA',
					nombrePeriodicidad: 'QUINCENAL',
					cantidadPagos:  24 ,
					numeroDias: 15,
					).save(failOnError: true)
					
		new SimCatPeriodicidad(clavePeriodicidad:  'MES',
					nombrePeriodicidad: 'MENSUAL',
					cantidadPagos: 12,
					numeroDias: 30,
					).save(failOnError: true)
		
		new SimCatTipoDocumento(claveTipoDocumento:  'IDENTIFICACION',
				nombreTipoDocumento: 'IDENTIFICACION OFICIAL',
				).save(failOnError: true)

		new SimCatTipoDocumento(claveTipoDocumento:  'DOMICILIO',
				nombreTipoDocumento: 'COMPROBANTE DE DOMICILIO',
				).save(failOnError: true)

		new SimCatTipoDocumento(claveTipoDocumento:  'LEGAL',
				nombreTipoDocumento: 'LEGAL',
				).save(failOnError: true, flush:true)

		new SimCatTipoDocumento(claveTipoDocumento:  'PRESTAMO',
				nombreTipoDocumento: 'DOCUMENTACION DEL PRESTAMO',
				).save(failOnError: true, flush:true)

		new SimCatDocumento(claveDocumento:  'CFE',
				nombreDocumento: 'COMPROBANTE DE LUZ',
				descripcion: 'COMPROBANTE DE LUZ',
				tipoDocumento : SimCatTipoDocumento.findByClaveTipoDocumento('DOMICILIO'),
				).save(failOnError: true)

		new SimCatDocumento(claveDocumento:  'ACTA_NACIMIENTO',
				nombreDocumento: 'ACTA DE NACIMIENTO',
				descripcion: 'ACTA DE NACIMIENTO',
				tipoDocumento : SimCatTipoDocumento.findByClaveTipoDocumento('LEGAL'),
				).save(failOnError: true)

		new SimCatDocumento(claveDocumento:  'IFE',
				nombreDocumento: 'CREDENCIAL IFE',
				descripcion: 'CREDENCIAL IFE',
				tipoDocumento : SimCatTipoDocumento.findByClaveTipoDocumento('IDENTIFICACION'),
				).save(failOnError: true)

		new SimCatDocumento(claveDocumento:  'LICENCIA_CONDUCIR',
				nombreDocumento: 'LICENCIA DE CONDUCIR',
				descripcion: 'LICENCIA DE CONDUCIR',
				tipoDocumento : SimCatTipoDocumento.findByClaveTipoDocumento('IDENTIFICACION'),
				).save(failOnError: true)

		new SimCatDocumento(claveDocumento:  'SOLICITUD_PRESTAMO',
				nombreDocumento: 'SOLICITUD DE PRESTAMO',
				tipoDocumento : SimCatTipoDocumento.findByClaveTipoDocumento('PRESTAMO'),				
				).save(failOnError: true)

		new SimCatDocumento(claveDocumento:  'PAGARE_PRESTAMO',
				nombreDocumento: 'PAGARE DEL PRESTAMO',
				tipoDocumento : SimCatTipoDocumento.findByClaveTipoDocumento('PRESTAMO'),
				).save(failOnError: true)

		new SimCatDocumento(claveDocumento:  'IDENTIFICACION_PRESTAMO',
				nombreDocumento: 'IDENTIFICACION DEL PRESTAMO',
				tipoDocumento : SimCatTipoDocumento.findByClaveTipoDocumento('PRESTAMO'),				
				).save(failOnError: true)

		new SimCatDocumento(claveDocumento:  'NO_DEFINIDO',
				nombreDocumento: 'NO DEFINIDO',
				tipoDocumento : SimCatTipoDocumento.findByClaveTipoDocumento('PRESTAMO'),
				).save(failOnError: true)

		new SimCatEscolaridad(claveEscolaridad:  'PRIMARIA',
				nombreEscolaridad: 'PRIMARIA',
				).save(failOnError: true)

		new SimCatEscolaridad(claveEscolaridad:  'SECUNDARIA',
				nombreEscolaridad: 'SECUNDARIA',
				).save(failOnError: true)

		new SimCatEscolaridad(claveEscolaridad:  'PREPA',
				nombreEscolaridad: 'PREPARATORIA',
				).save(failOnError: true)

		new SimCatEscolaridad(claveEscolaridad:  'LIC',
				nombreEscolaridad: 'LICENCIATURA',
				).save(failOnError: true)


		new SimCatDescTelefono(claveDescripcionTelefono: 'CASA',
				nombreDescripcionTelefono: 'CASA',
				).save(failOnError: true)

		new SimCatDescTelefono(claveDescripcionTelefono: 'OFICINA',
				nombreDescripcionTelefono: 'OFICINA',
				).save(failOnError: true)

		new SimCatDescTelefono(claveDescripcionTelefono: 'MOVIL',
				nombreDescripcionTelefono: 'MOVIL',
				).save(failOnError: true)

        new EntRegion(claveRegion: 'UNICA',
                nombreRegion: 'UNICA',
        		).save(failOnError: true, flush: true)


		new RsGralEstado(cveEstado: 'TODOS',
				nombreEstado: 'TODOS',
				aliasEstado : 'TODOS',
				region: EntRegion.findByClaveRegion('UNICA'),
				).save(failOnError: true, flush: true)

		/*

		new RsGralEstado(cveEstado: 'DF',
				nombreEstado: 'DISTRITO FEDERAL',
				aliasEstado : 'DF',
				region: EntRegion.findByClaveRegion('CENTRO'),
				).save(failOnError: true)

		new RsGralEstado(cveEstado: 'EDOMEX',
				nombreEstado: 'ESTADO DE MEXICO',
				aliasEstado : 'EDOMEX',
				region: EntRegion.findByClaveRegion('CENTRO'),
				).save(failOnError: true, flush: true)

		new RsGralDelegacionMunicipio(nombreDelegacionMunicipio: 'Aguascalientes',
				estado : RsGralEstado.findByCveEstado('AGS')).save(failOnError: true)

		new RsGralDelegacionMunicipio(nombreDelegacionMunicipio: 'San Francisco de los Romo',
				estado : RsGralEstado.findByCveEstado('AGS')).save(failOnError: true)

		new RsGralDelegacionMunicipio(nombreDelegacionMunicipio: 'El Llano',
				estado : RsGralEstado.findByCveEstado('AGS')).save(failOnError: true)

		new RsGralDelegacionMunicipio(nombreDelegacionMunicipio: 'EL AMAPOLAS UNO',
				estado : RsGralEstado.findByCveEstado('AGS')).save(failOnError: true)

		new RsGralDelegacionMunicipio(nombreDelegacionMunicipio: 'EL AMAPOLAS DOS',
				estado : RsGralEstado.findByCveEstado('AGS')).save(failOnError: true)

		new RsGralDelegacionMunicipio(nombreDelegacionMunicipio: 'EL AMAPOLAS TRES',
				estado : RsGralEstado.findByCveEstado('AGS')).save(failOnError: true)

		new RsGralDelegacionMunicipio(nombreDelegacionMunicipio: 'EL CONEJAL UNO',
				estado : RsGralEstado.findByCveEstado('AGS')).save(failOnError: true)

		new RsGralDelegacionMunicipio(nombreDelegacionMunicipio: 'EL CONEJAL DOS',
				estado : RsGralEstado.findByCveEstado('AGS')).save(failOnError: true)

		new RsGralDelegacionMunicipio(nombreDelegacionMunicipio: 'EL CONEJAL TRES',
				estado : RsGralEstado.findByCveEstado('AGS')).save(failOnError: true)

		new RsGralDelegacionMunicipio(nombreDelegacionMunicipio: 'CUAUHTEMOC',
				estado : RsGralEstado.findByCveEstado('DF')).save(failOnError: true)

		new RsGralDelegacionMunicipio(nombreDelegacionMunicipio: 'VENUSTIANO CARRANZA',
				estado : RsGralEstado.findByCveEstado('DF')).save(failOnError: true)

		new RsGralDelegacionMunicipio(nombreDelegacionMunicipio: 'ALVARO OBREGON',
				estado : RsGralEstado.findByCveEstado('DF')).save(failOnError: true)

		new RsGralDelegacionMunicipio(nombreDelegacionMunicipio: 'BENITO JUAREZ',
				estado : RsGralEstado.findByCveEstado('DF')).save(failOnError: true)

		new RsGralDelegacionMunicipio(nombreDelegacionMunicipio: 'COYOACAN',
				estado : RsGralEstado.findByCveEstado('DF')).save(failOnError: true)

		new RsGralDelegacionMunicipio(nombreDelegacionMunicipio: 'TLALPAN',
				estado : RsGralEstado.findByCveEstado('DF')).save(failOnError: true)

		new RsGralDelegacionMunicipio(nombreDelegacionMunicipio: 'METEPEC UNO',
				estado : RsGralEstado.findByCveEstado('EDOMEX')).save(failOnError: true)

		new RsGralDelegacionMunicipio(nombreDelegacionMunicipio: 'METEPEC DOS',
				estado : RsGralEstado.findByCveEstado('EDOMEX')).save(failOnError: true)

		new RsGralDelegacionMunicipio(nombreDelegacionMunicipio: 'METEPEC TRES',
				estado : RsGralEstado.findByCveEstado('EDOMEX')).save(failOnError: true)

		new RsGralDelegacionMunicipio(nombreDelegacionMunicipio: 'CHIMALHUACAN UNO',
				estado : RsGralEstado.findByCveEstado('EDOMEX')).save(failOnError: true)

		new RsGralDelegacionMunicipio(nombreDelegacionMunicipio: 'CHIMALHUACAN DOS',
				estado : RsGralEstado.findByCveEstado('EDOMEX')).save(failOnError: true)

		new RsGralDelegacionMunicipio(nombreDelegacionMunicipio: 'CHIMALHUACAN TRES',
				estado : RsGralEstado.findByCveEstado('EDOMEX')).save(failOnError: true)


		new RsGralDelegacionMunicipio(nombreDelegacionMunicipio: 'MEXICO UNO',
				estado : RsGralEstado.findByCveEstado('EDOMEX')).save(failOnError: true)

		new RsGralDelegacionMunicipio(nombreDelegacionMunicipio: 'MEXICO DOS',
				estado : RsGralEstado.findByCveEstado('EDOMEX')).save(failOnError: true)

		new RsGralDelegacionMunicipio(nombreDelegacionMunicipio: 'MEXICO TRES',
				estado : RsGralEstado.findByCveEstado('EDOMEX')).save(failOnError: true, flush: true)

		new SimCatTipoAsentamiento(claveTipoAsentamiento:  'CIUDAD',
				nombreTipoAsentamiento: 'CIUDAD',
				).save(failOnError: true)
		new SimCatTipoAsentamiento(claveTipoAsentamiento:  'COLONIA',
				nombreTipoAsentamiento: 'COLONIA',
				).save(failOnError: true)
		new SimCatTipoAsentamiento(claveTipoAsentamiento:  'GRANUSUARIO',
				nombreTipoAsentamiento: 'GRAN USUARIO',
				).save(failOnError: true)
		new SimCatTipoAsentamiento(claveTipoAsentamiento:  'URBANA',
				nombreTipoAsentamiento: 'URBANA',
				).save(failOnError: true, flush: true)


		new RsGralAsentamiento(nombreAsentamiento: 'Zona Centro',
				codigoPostal: '20000',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('Aguascalientes'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'Delegacion de La Secretaria de Comercio y Fomento Industrial',
				codigoPostal: '20008',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('Aguascalientes'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('GRANUSUARIO')).save(failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'Palacio de Gobierno del Estado de Aguascalientes',
				codigoPostal: '20009',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('Aguascalientes'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('GRANUSUARIO')).save(failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'EL AMAPOLAS 1 ASENTAMIENTO UNO',
				codigoPostal: '01230',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('EL AMAPOLAS UNO'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'EL AMAPOLAS 1 ASENTAMIENTO DOS',
				codigoPostal: '01240',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('EL AMAPOLAS UNO'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'EL AMAPOLAS 1 ASENTAMIENTO TRES',
				codigoPostal: '01250',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('EL AMAPOLAS UNO'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'EL AMAPOLAS 2 ASENTAMIENTO UNO',
				codigoPostal: '01260',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('EL AMAPOLAS DOS'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'EL AMAPOLAS 2 ASENTAMIENTO DOS',
				codigoPostal: '01270',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('EL AMAPOLAS DOS'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'EL AMAPOLAS 2 ASENTAMIENTO TRES',
				codigoPostal: '01280',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('EL AMAPOLAS DOS'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'EL AMAPOLAS 3 ASENTAMIENTO UNO',
				codigoPostal: '01290',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('EL AMAPOLAS TRES'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'EL AMAPOLAS 3 ASENTAMIENTO DOS',
				codigoPostal: '01300',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('EL AMAPOLAS TRES'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'EL AMAPOLAS 3 ASENTAMIENTO TRES',
				codigoPostal: '01310',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('EL AMAPOLAS TRES'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'EL CONEJAL 1 ASENTAMIENTO UNO',
				codigoPostal: '01320',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('EL CONEJAL UNO'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'EL CONEJAL 1 ASENTAMIENTO DOS',
				codigoPostal: '01330',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('EL CONEJAL UNO'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'EL CONEJAL 1 ASENTAMIENTO TRES',
				codigoPostal: '01340',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('EL CONEJAL UNO'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'EL CONEJAL 2 ASENTAMIENTO UNO',
				codigoPostal: '01350',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('EL CONEJAL DOS'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'EL CONEJAL 2 ASENTAMIENTO DOS',
				codigoPostal: '01360',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('EL CONEJAL DOS'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'EL CONEJAL 2 ASENTAMIENTO TRES',
				codigoPostal: '01370',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('EL CONEJAL DOS'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'EL CONEJAL 3 ASENTAMIENTO UNO',
				codigoPostal: '01380',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('EL CONEJAL TRES'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'EL CONEJAL 3 ASENTAMIENTO DOS',
				codigoPostal: '01390',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('EL CONEJAL TRES'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'EL CONEJAL 3 ASENTAMIENTO TRES',
				codigoPostal: '01400',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('EL CONEJAL TRES'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'VISTA ALEGRE',
				codigoPostal: '06860',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('CUAUHTEMOC'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'BUENOS AIRES',
				codigoPostal: '06861',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('CUAUHTEMOC'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'BOTURINI',
				codigoPostal: '06862',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('CUAUHTEMOC'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'VENUSTIANO CARRANZA UNO',
				codigoPostal: '06900',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('VENUSTIANO CARRANZA'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'VENUSTIANO CARRANZA DOS',
				codigoPostal: '06910',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('VENUSTIANO CARRANZA'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'VENUSTIANO CARRANZA TRES',
				codigoPostal: '06920',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('VENUSTIANO CARRANZA'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'ALVARO OBREGON UNO',
				codigoPostal: '01410',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('ALVARO OBREGON'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'ALVARO OBREGON DOS',
				codigoPostal: '01420',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('ALVARO OBREGON'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'ALVARO OBREGON TRES',
				codigoPostal: '01430',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('ALVARO OBREGON'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'SANTA CRUZ ATOYAC',
				codigoPostal: '01440',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('COYOACAN'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'DEL VALLE',
				codigoPostal: '01450',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('COYOACAN'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'NARVARTE',
				codigoPostal: '01460',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('COYOACAN'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'BENITO JUAREZ UNO',
				codigoPostal: '01470',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('BENITO JUAREZ'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'BENITO JUAREZ DOS',
				codigoPostal: '01480',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('BENITO JUAREZ'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'BENITO JUAREZ TRES',
				codigoPostal: '01490',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('BENITO JUAREZ'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'TLALPAN UNO',
				codigoPostal: '01500',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('TLALPAN'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'TLALPAN DOS',
				codigoPostal: '01510',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('TLALPAN'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'TLALPAN TRES',
				codigoPostal: '01520',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('TLALPAN'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'CHIMALHUACAN 1 ASENTAMIENTO UNO',
				codigoPostal: '01530',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('CHIMALHUACAN UNO'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'CHIMALHUACAN 1 ASENTAMIENTO DOS',
				codigoPostal: '01540',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('CHIMALHUACAN UNO'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'CHIMALHUACAN 1 ASENTAMIENTO TRES',
				codigoPostal: '01550',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('CHIMALHUACAN UNO'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'CHIMALHUACAN 2 ASENTAMIENTO UNO',
				codigoPostal: '01560',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('CHIMALHUACAN DOS'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'CHIMALHUACAN 2 ASENTAMIENTO DOS',
				codigoPostal: '01570',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('CHIMALHUACAN DOS'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'CHIMALHUACAN 2 ASENTAMIENTO TRES',
				codigoPostal: '01580',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('CHIMALHUACAN DOS'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'CHIMALHUACAN 3 ASENTAMIENTO UNO',
				codigoPostal: '01590',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('CHIMALHUACAN TRES'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'CHIMALHUACAN 3 ASENTAMIENTO DOS',
				codigoPostal: '01600',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('CHIMALHUACAN TRES'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'CHIMALHUACAN 3 ASENTAMIENTO TRES',
				codigoPostal: '01610',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('CHIMALHUACAN TRES'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'METEPEC 1 ASENTAMIENTO UNO',
				codigoPostal: '01620',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('METEPEC UNO'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'METEPEC 1 ASENTAMIENTO DOS',
				codigoPostal: '01630',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('METEPEC UNO'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'METEPEC 1 ASENTAMIENTO TRES',
				codigoPostal: '01640',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('METEPEC UNO'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'METEPEC 2 ASENTAMIENTO UNO',
				codigoPostal: '01650',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('METEPEC DOS'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'METEPEC 2 ASENTAMIENTO DOS',
				codigoPostal: '01660',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('METEPEC DOS'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'METEPEC 2 ASENTAMIENTO TRES',
				codigoPostal: '01670',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('METEPEC DOS'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'METEPEC 3 ASENTAMIENTO UNO',
				codigoPostal: '01680',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('METEPEC TRES'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'METEPEC 3 ASENTAMIENTO DOS',
				codigoPostal: '01690',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('METEPEC TRES'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'METEPEC 3 ASENTAMIENTO TRES',
				codigoPostal: '01700',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('METEPEC TRES'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'MEXICO 1 ASENTAMIENTO UNO',
				codigoPostal: '01710',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('MEXICO UNO'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'MEXICO 1 ASENTAMIENTO DOS',
				codigoPostal: '01720',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('MEXICO UNO'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'MEXICO 1 ASENTAMIENTO TRES',
				codigoPostal: '01730',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('MEXICO UNO'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'MEXICO 2 ASENTAMIENTO UNO',
				codigoPostal: '01740',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('MEXICO DOS'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'MEXICO 2 ASENTAMIENTO DOS',
				codigoPostal: '01750',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('MEXICO DOS'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'MEXICO 2 ASENTAMIENTO TRES',
				codigoPostal: '01760',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('MEXICO DOS'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'MEXICO 3 ASENTAMIENTO UNO',
				codigoPostal: '01770',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('MEXICO TRES'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'MEXICO 3 ASENTAMIENTO DOS',
				codigoPostal: '01780',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('MEXICO TRES'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'MEXICO 3 ASENTAMIENTO TRES',
				codigoPostal: '01790',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('MEXICO TRES'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true, flush: true)
		 
		new RsGralDomicilio(calle: 'BATALLONES ROJOS 205',
				numeroInterior: '504',
				numeroExterior: 'EDIF 8',
				esFiscal: 'true',
				comentarios : 'UNIDAD ALBARRADA',
				rsGralAsentamiento : RsGralAsentamiento.findByCodigoPostal('06862'),
				persona : RsPersona.findByEmail('sistema.microfinanciera@gmail.com'),
				).save(failOnError: true)
		new RsGralDomicilio(calle: 'PROGRESISTA',
				numeroInterior: '202',
				numeroExterior: 'EDIF 6',
				esFiscal: 'false',
				comentarios : 'UNIDAD VICENTE',
				rsGralAsentamiento : RsGralAsentamiento.findByCodigoPostal('01600'),
				persona : RsPersona.findByEmail('sistema.microfinanciera@gmail.com'),
				).save(failOnError: true)
		*/


		new RsGralTelefono(telefono:  '55578796',
				descripcionTelefono : SimCatDescTelefono.findByClaveDescripcionTelefono('OFICINA'),
				persona : RsPersona.findByEmail('sistema.microfinanciera@gmail.com'),
				).save(failOnError: true)

		new RsGralTelefono(telefono:  '55997876',
				descripcionTelefono : SimCatDescTelefono.findByClaveDescripcionTelefono('MOVIL'),
				persona : RsPersona.findByEmail('sistema.microfinanciera@gmail.com'),
				).save(failOnError: true)

		new SimCatBanco(claveBanco: 'BANCOMER',
				nombreBanco: 'BANCOMER BBVA',
				).save(failOnError: true)

		new SimCatBanco(claveBanco: 'BANAMEX',
				nombreBanco: 'BANAMEX MÉXICO',
				).save(failOnError: true)

		new SimCatBanco(claveBanco: 'IXE',
				nombreBanco: 'BANCO IXE',
				).save(failOnError: true)

		new SimCatBanco(claveBanco: 'SANTANDER',
				nombreBanco: 'SANTANDER MÉXICO',
				).save(failOnError: true)

		//ASIGNA ATRIBUTOS A LA PERSONA mrugerio@gmail.com
		def personaAdmin = RsPersona.findByEmail('sistema.microfinanciera@gmail.com')
		personaAdmin.sexo = "MASCULINO"
		personaAdmin.estadoCivil = "CASADO - BIENES MANCOMUNADOS"
		personaAdmin.fechaNacimiento = new Date('09/30/1974')
		personaAdmin.nombreAlterno = "SISTEMA MICROFINANCIERAS"
		personaAdmin.identificacionOficial = SimCatDocumento.findByClaveDocumento('IFE')
		personaAdmin.numeroIdentificacionOficial = "RUFM727328328"
		personaAdmin.rfc = "RUFM89778"
		personaAdmin.curp = "RUMD76878968"
		personaAdmin.escolaridad  = SimCatEscolaridad.findByClaveEscolaridad('LIC')
		personaAdmin.tiposPersona = [
			SimCatTipoPersona.findByClaveTipoPersona('USUARIO'),
			SimCatTipoPersona.findByClaveTipoPersona('EMPLEADO')
		]
		personaAdmin.save(failOnError: true)


        new EntSucursal(claveSucursal: 'EDOMEX',
                nombreSucursal: 'ESTADO DE MEXICO',
                tipoSucursal: "SUCURSAL FISICA",
                estado : RsGralEstado.findByCveEstado('TODOS'),
        ).save(failOnError: true)

        new EntSucursal(claveSucursal: 'ZACATECAS',
                nombreSucursal: 'ZACATECAS',
         		tipoSucursal: "SUCURSAL FISICA",
         		estado : RsGralEstado.findByCveEstado('TODOS'),
        ).save(failOnError: true)

        new EntSucursal(claveSucursal: 'AGUASCALIENTES',
                nombreSucursal: 'AGUASCALIENTES',
         		tipoSucursal: "SUCURSAL FISICA",
         		estado : RsGralEstado.findByCveEstado('TODOS'),
        ).save(failOnError: true)

        new EntSucursal(claveSucursal: 'D.F.',
                nombreSucursal: 'DISTRITO FEDERAL',
         		tipoSucursal: "SUCURSAL FISICA",
         		estado : RsGralEstado.findByCveEstado('TODOS'),
        ).save(failOnError: true)

        new EntDelegacion(claveDelegacion: 'EDOMEX',
                nombreDelegacion: 'ESTADO MEXICO',
                sucursal: EntSucursal.findByClaveSucursal('EDOMEX'),
        ).save(failOnError: true)

        new EntDelegacion(claveDelegacion: 'ZACATECAS',
                nombreDelegacion: 'ZACATECAS',
                sucursal: EntSucursal.findByClaveSucursal('EDOMEX'),
        ).save(failOnError: true)

        def dependenciaImss = new EntDependencia(claveDependencia: 'IMSS',
                nombreDependencia: 'INSTITUTO MEXICANO DEL SEGURO SOCIAL',
                distribuidor : '1866',
        ).save(failOnError: true)

        def dependenciaCfe  = new EntDependencia(claveDependencia: 'CFE',
				nombreDependencia: 'COMISION FEDERAL DE ELECTRICIDAD',
				distribuidor : '1866',
				).save(failOnError: true)

        //DA DE ALTA UNA PERSONA PARA ASIGNARLO A UN CLIENTE
        def robertoPerez = new RsPersona(
                apellidoPaterno: "PEREZ",
                primerNombre: "ROBERTO",
                email : "rperez@gmail.com",
                rfc : "AAAA5555",
                tiposPersona : [
                        SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
                ],
        ).save(failOnError: true, flush:true)

        new RsCliente(persona: robertoPerez,
                numeroDeNomina: '6967896JK8',
                apellidoPaterno: robertoPerez.apellidoPaterno,
                primerNombre: robertoPerez.primerNombre,
                dependencia : [
                        EntDependencia.findByClaveDependencia('IMSS')
                ],
        ).save(failOnError: true)

        def arturoSalazar = new RsPersona(
                email : "arturo.salazar@gmail.com",
                apellidoPaterno: "SALAZAR",
                apellidoMaterno: "CASTAÑEDA",
                primerNombre: "ARTURO",
                segundoNombre: "ARTURIN",
                sexo: "MASCULINO",
                estadoCivil : "CASADO - BIENES MANCOMUNADOS",
                fechaNacimiento : new Date('09/30/1974'),
                identificacionOficial : SimCatDocumento.findByClaveDocumento('LICENCIA_CONDUCIR'),
                numeroIdentificacionOficial : "RUFM727328328",
                rfc : "RUFM89778",
                curp : "RUMD76878968",
                escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('PREPA'),
                tiposPersona : [
                        SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
                ],
        ).save(failOnError: true)

        def clienteArturo = new RsCliente(persona: arturoSalazar,
                numeroDeNomina: 'KJKSDFYUYUI',
                apellidoPaterno: arturoSalazar.apellidoPaterno,
                apellidoMaterno: arturoSalazar.apellidoMaterno,
                primerNombre: arturoSalazar.primerNombre,
                segundoNombre: arturoSalazar.segundoNombre,
                rfc : arturoSalazar.rfc,
                dependencia : [
                        EntDependencia.findByClaveDependencia('CFE')
                ],                
        ).save(failOnError: true)

        new EmpPuesto(clavePuesto:  'DIRGEN',
                nombrePuesto: 'DIRECTOR GENERAL',
                descripcionPuesto: 'DIRECTOR GENERAL MICRO',
        ).save(failOnError: true)

        new EmpPuesto(clavePuesto:  'GERRIE',
                nombrePuesto: 'GERENTE DE RIESGOS',
                descripcionPuesto: 'GERENTE DE RIESGOS',
                dependeDe : EmpPuesto.findByClavePuesto('DIRGEN'),
        ).save(failOnError: true)

        new EmpPuesto(clavePuesto:  'COORIE',
                nombrePuesto: 'COORDINADOR DE RIESGOS',
                descripcionPuesto: 'COORDINADOR DE RIESGOS',
                dependeDe : EmpPuesto.findByClavePuesto('GERRIE'),
        ).save(failOnError: true)

        new EmpPuesto(clavePuesto:  'ASESORSUC',
                nombrePuesto: 'ASESOR DE SUCURSAL',
                descripcionPuesto: 'ASESOR DE SUCURSAL',
                dependeDe : EmpPuesto.findByClavePuesto('DIRGEN'),
        ).save(failOnError: true)

        new EmpPuesto(clavePuesto:  'GERREG',
                nombrePuesto: 'GERENTE REGIONAL',
                descripcionPuesto: 'GERENTE DE REGIONAL',
                dependeDe : EmpPuesto.findByClavePuesto('DIRGEN'),
        ).save(failOnError: true)

        new EmpPuesto(clavePuesto:  'COOREG',
                nombrePuesto: 'COORDINADOR REGIONAL',
                descripcionPuesto: 'COORDINADOR DE REGIONAL',
                dependeDe : EmpPuesto.findByClavePuesto('DIRGEN'),
        ).save(failOnError: true)

        new EmpPuesto(clavePuesto:  'GERSUC',
                nombrePuesto: 'GERENTE SUCURSAL',
                descripcionPuesto: 'GERENTE DE SUCURSAL',
                dependeDe : EmpPuesto.findByClavePuesto('DIRGEN'),
        ).save(failOnError: true)

        new EmpPuesto(clavePuesto:  'COOSUC',
                nombrePuesto: 'COORDINADOR SUCURSAL',
                descripcionPuesto: 'COORDINADOR DE SUCURSAL',
                dependeDe : EmpPuesto.findByClavePuesto('DIRGEN'),
        ).save(failOnError: true)

        new EmpPuesto(clavePuesto:  'VENDE',
                nombrePuesto: 'VENDEDOR',
                descripcionPuesto: 'VENDEDOR MTN',
        ).save(failOnError: true)

        def personaAlex = new RsPersona(
                email : "alejandro.salazar@gmail.com",
                apellidoPaterno: "MIRANDA",
                apellidoMaterno: "CASTAÑEDA",
                primerNombre: "ALEJANDRO",
                segundoNombre: "ALEX",
                sexo: "MASCULINO",
                estadoCivil : "CASADO - BIENES MANCOMUNADOS",
                fechaNacimiento : new Date('04/30/1980'),
                identificacionOficial : SimCatDocumento.findByClaveDocumento('LICENCIA_CONDUCIR'),
                numeroIdentificacionOficial : "RUFM727328328",
                rfc : "RUFM89778",
                curp : "RUMD76878968",
                escolaridad  :  SimCatEscolaridad.findByClaveEscolaridad('PREPA'),
                tiposPersona : [
                        SimCatTipoPersona.findByClaveTipoPersona('EMPLEADO')
                ],
        ).save(failOnError: true, flush: true)

        //DA DE ALTA A UN EMPLEADO
        def empleado = new EmpEmpleado(
                claveEmpleado : "MOR78987",
                persona : personaAlex,
                tipoEmpleado : "INTERNO",
                puesto : EmpPuesto.findByClavePuesto('VENDE'),
                fechaIngreso  : new Date('08/20/1999'),
                numeroNomina : "001",
                esVigente: 'true',
                sucursal: EntSucursal.findByClaveSucursal('EDOMEX'),
        ).save(failOnError: true)

        //DA DE ALTA UNA PROMOCION
        def promocionUno = new ProPromocion(
                clavePromocion : "MOR78987",
                nombrePromocion : "PROMOCION ATREVETE A PEDIR",
                tasaDeInteres:  5.45,
				metodoCalculo : SimCatMetodoCalculo.findByClaveMetodoCalculo('METODO01'),
				periodicidadTasa: SimCatPeriodicidad.findByClavePeriodicidad('MES'),
                numeroDePagos:  10,
				periodicidadPagos: SimCatPeriodicidad.findByClavePeriodicidad('QUINCENA'),
                fechaInicioVigencia : new Date('04/30/2012'),
                fechaFinVigencia  : new Date('09/30/2015'),
                iva : 16,
        ).save(failOnError: true)

        def promocionDos = new ProPromocion(
				clavePromocion : "OPORTUNIDAD",
				nombrePromocion : "PROMOCION LO IDEAL",
				tasaDeInteres:  4.5,
				metodoCalculo : SimCatMetodoCalculo.findByClaveMetodoCalculo('METODO01'),
				periodicidadTasa: SimCatPeriodicidad.findByClavePeriodicidad('MES'),
				numeroDePagos:  24,
				periodicidadPagos: SimCatPeriodicidad.findByClavePeriodicidad('QUINCENA'),
				fechaInicioVigencia : new Date('04/30/2012'),
				fechaFinVigencia  : new Date('09/30/2015'),
				iva : 16,
				).save(failOnError: true)

        dependenciaImss.addToPromocion(promocionUno).save()
        dependenciaCfe.addToPromocion(promocionDos).save()

        new SimCatFormaEntrega(claveFormaEntrega: 'VENBANCO',
                nombreFormaEntrega: 'ENTREGA EN VENTANILLA DE BANCO',
        ).save(failOnError: true)

        new SimCatFormaEntrega(claveFormaEntrega: 'ELECTRONICA',
                nombreFormaEntrega: 'TRANSFERENCIA ELECTRONICA',
        ).save(failOnError: true)

        new SimCatFormaEntrega(claveFormaEntrega: 'CAJA',
                nombreFormaEntrega: 'DIRECTAMENTE EN CAJA',
        ).save(failOnError: true)

        new SimCatFormaEntrega(claveFormaEntrega: 'CHEQUE',
                nombreFormaEntrega: 'EN CHEQUE',
        ).save(failOnError: true, flush: true)

        new Prestamo(
                cliente : clienteArturo,
				correoSolicitante: "arturo.salazar@rapidsist.com",
                folioSolicitud : 34534,
                dependencia : EntDependencia.findByClaveDependencia('IMSS'),
                tipoEmpleadoDep : SimCatTipoEmp.findByClaveTipoEmpleadoDep('HOMOLOGADOS'),
                promocion: promocionUno,
                sucursal: EntSucursal.findByClaveSucursal('EDOMEX'),
                delegacion: EntDelegacion.findByClaveDelegacion('ZACATECAS'),
                vendedor : empleado,
                fechaSolicitud: new Date('04/30/2012'),
                montoSolicitado: 6000,
                percepcionesMensuales: 12000,
                deduccionesMensuales: 2000,
                estatusSolicitud: PrestamoEstatus.ACTIVO,
                formaDeDispercion: SimCatFormaEntrega.findByClaveFormaEntrega('VENBANCO'),
                documentosCorrectos: false,
				aprobado: false,
				reenviarSolicitud: false,
				incluirEnListasCobro: true,
				fechaCobro: new Date('01/20/2013'),	//20 ENERO 2013
        ).save(failOnError: true)

        /*
		// Load the basic test data. Note that, since the data is loaded in a transaction, all the
		// business logic will fire, and all derived attributes will be automagically calculated.
		if (!Customer.count()) {
			Customer.withTransaction{tx ->
				def alpha = new Customer(name: "Alpha and Sons", balance: 0, creditLimit: 900, isPreferred: true).save(failOnError: true)
				def bravo = new Customer(name: "Bravo Hardware", balance: 0, creditLimit: 5000, isPreferred: false).save(failOnError: true)
				def charlie = new Customer(name: "Charlie's Construction", balance: 0, creditLimit: 1500, isPreferred: true).save(failOnError: true)
				def delta = new Customer(name: "Delta Engineering", balance: 0, creditLimit: 0, isPreferred: false).save(failOnError: true)
				
				def hammer = new Product(name: "Hammer", price: 10).save(failOnError: true)
				def shovel = new Product(name: "Shovel", price: 25).save(failOnError: true)
				def drill = new Product(name: "Drill", price: 315).save(failOnError: true)
				
				def po1 = new PurchaseOrder(amountTotal: 0, paid: false, ready: true, notes: "This is a small order", customer: alpha).save(failOnError: true)
				def po2 = new PurchaseOrder(amountTotal: 0, paid: true, ready: true, notes: "PRUEBA", customer: bravo).save(failOnError: true)
				def po3 = new PurchaseOrder(amountTotal: 0, paid: false, ready: true, notes: "Please rush this order", customer: bravo).save(failOnError: true)
				def po4 = new PurchaseOrder(amountTotal: 0, paid: false, ready: true, notes: "Deliver by overnight with signature required", customer: charlie).save(failOnError: true)
				def po5 = new PurchaseOrder(amountTotal: 0, paid: false, ready: true, notes: "PRUEBA", customer: charlie).save(failOnError: true)
				def po6 = new PurchaseOrder(amountTotal: 0, paid: false, ready: false, notes: "Pack with care - fragile merchandise", customer: alpha).save(failOnError: true)
				
				new LineItem(qtyOrdered: 1, amount: 0, productPrice: 0, product: hammer, purchaseOrder: po1).save(failOnError: true)
				new LineItem(qtyOrdered: 2, amount: 0, productPrice: 0, product: hammer, purchaseOrder: po2).save(failOnError: true)
				new LineItem(qtyOrdered: 1, amount: 0, productPrice: 0, product: shovel, purchaseOrder: po2).save(failOnError: true)
				new LineItem(qtyOrdered: 3, amount: 0, productPrice: 0, product: drill, purchaseOrder: po2).save(failOnError: true)
				new LineItem(qtyOrdered: 1, amount: 0, productPrice: 0, product: hammer, purchaseOrder: po3).save(failOnError: true)
				new LineItem(qtyOrdered: 2, amount: 0, productPrice: 0, product: shovel, purchaseOrder: po3).save(failOnError: true)
				new LineItem(qtyOrdered: 1, amount: 0, productPrice: 0, product: hammer, purchaseOrder: po4).save(failOnError: true)
				new LineItem(qtyOrdered: 3, amount: 0, productPrice: 0, product: shovel, purchaseOrder: po4).save(failOnError: true)
				new LineItem(qtyOrdered: 1, amount: 0, productPrice: 0, product: hammer, purchaseOrder: po5).save(failOnError: true)
				new LineItem(qtyOrdered: 5, amount: 0, productPrice: 0, product: shovel, purchaseOrder: po5).save(failOnError: true)
				new LineItem(qtyOrdered: 2, amount: 0, productPrice: 0, product: shovel, purchaseOrder: po6).save(failOnError: true)
				new LineItem(qtyOrdered: 1, amount: 0, productPrice: 0, product: shovel, purchaseOrder: po1).save(failOnError: true)
			}
		}
		*/
		new SimCatEvento(evento:"Envio Lista de Cobro").save(failOnError: true)
		new SimCatEvento(evento:"Aplicacion Lista de Cobro").save(failOnError: true)
		new SimCatEvento(evento:"Devolucion Lista de Cobro").save(failOnError: true)
		new SimCatEvento(evento:"Pago de la Dependencia").save(failOnError: true)
		
		new DummyPersona(nombre: "Miguel",apellido: "Mendoza", calle: "Melchor Ocampo", numero: "Mz 10 Lt 4", codigoPostal: "54870").save(failOnError: true)

        new PfinCatConcepto(claveConcepto:  'INTERES',
                descripcionCorta: 'INTERES',
                descripcionLarga: 'INTERES',
                situacion: 'ACTIVO'
        ).save(failOnError: true)

        new PfinCatConcepto(claveConcepto:  'CAPITAL',
                descripcionCorta: 'CAPITAL',
                descripcionLarga: 'CAPITAL',
                situacion: 'ACTIVO'
        ).save(failOnError: true)

        new PfinCatConcepto(claveConcepto:  'IVAINT',
                descripcionCorta: 'IVA INTERES',
                descripcionLarga: 'IVA DE INTERES',
                situacion: 'ACTIVO'
        ).save(failOnError: true)

        new PfinCatConcepto(claveConcepto:  'IMPBRU',
                descripcionCorta: 'IMPORTE BRUTO',
                descripcionLarga: 'IMPORTE BRUTO',
                situacion: 'ACTIVO'
        ).save(failOnError: true)

        new PfinCatConcepto(claveConcepto:  'SEGUNICO',
                descripcionCorta: 'SEGURO UNICO',
                descripcionLarga: 'SEGURO UNICO',
                situacion: 'ACTIVO'
        ).save(failOnError: true)

        new PfinCatConcepto(claveConcepto:  'SEGUNICOA',
                descripcionCorta: 'SEGURO A',
                descripcionLarga: 'SEGURO A',
                situacion: 'ACTIVO'
        ).save(failOnError: true)

        new PfinCatConcepto(claveConcepto:  'SEGUNICOB',
                descripcionCorta: 'SEGURO B',
                descripcionLarga: 'SEGURO B',
                situacion: 'ACTIVO'
        ).save(failOnError: true)

        new PfinCatConcepto(claveConcepto:  'SEGUNICOC',
                descripcionCorta: 'SEGURO C',
                descripcionLarga: 'SEGURO C',
                situacion: 'ACTIVO'
        ).save(failOnError: true)

		new PfinCatAfectaOperacion(claveAfecta:  'CREDITO',
			descripcionAfecta: 'AFECTA AL CREDITO'
		).save(failOnError: true)
		
		new PfinCatAfectaOperacion(claveAfecta:  'CUENTA',
			descripcionAfecta: 'AFECTA A LA CUENTA'
		).save(failOnError: true)

		new PfinCatAfectaOperacion(claveAfecta:  'CAJA',
			descripcionAfecta: 'AFECTA A LA CAJA'
		).save(failOnError: true)

		new PfinCatOperacion(claveOperacion:  'TEDEPEFE',
			claveAfectaSaldo: 'INCREMENTA',
			descripcionCorta: 'DEPOSITO DE EFECTIVO',
			descripcionLarga: 'DEPOSITO DE EFECTIVO A LA CUENTA DEL CLIENTE',
			situacion: 'ACTIVO',
			afecta: PfinCatAfectaOperacion.findByClaveAfecta('CUENTA')
		).save(failOnError: true)

		new PfinCatOperacion(claveOperacion:  'CRPAGOPRES',
			claveAfectaSaldo: 'DECREMENTA',
			descripcionCorta: 'PAGO PRESTAMO',
			descripcionLarga: 'PAGO DEL PRESTAMO',
			situacion: 'ACTIVO',
			afecta: PfinCatAfectaOperacion.findByClaveAfecta('CREDITO')
		).save(failOnError: true)

		new PfinCatOperacion(claveOperacion:  'CANCELA_PAGO',
			claveAfectaSaldo: 'INCREMENTA',
			descripcionCorta: 'CANCELA PAGO',
			descripcionLarga: 'CANCELACION DEL PAGO DEL CREDITO',
			situacion: 'ACTIVO',
			afecta: PfinCatAfectaOperacion.findByClaveAfecta('CREDITO')
		).save(failOnError: true)

		new PfinCatOperacion(claveOperacion:  'AJUSTE_CARGO',
			claveAfectaSaldo: 'DECREMENTA',
			descripcionCorta: 'AJUSTE CARGO',
			descripcionLarga: 'AJUSTE CARGO AL SALDO DEL CLIENTE',
			situacion: 'ACTIVO',
			afecta: PfinCatAfectaOperacion.findByClaveAfecta('CUENTA')
		).save(failOnError: true)

		new PfinCatOperacion(claveOperacion:  'AJUSTE_ABONO',
			claveAfectaSaldo: 'INCREMENTA',
			descripcionCorta: 'AJUSTE ABONO',
			descripcionLarga: 'AJUSTE ABONO AL SALDO DEL CLIENTE',
			situacion: 'ACTIVO',
			afecta: PfinCatAfectaOperacion.findByClaveAfecta('CUENTA')
		).save(failOnError: true)

		//NO ENCUENTRO DONDE EL CORE UTILIZA LA RELACION OPERACION-CONCEPTO
		/*
		new PfinCatOperacionConcepto(operacion:  PfinCatOperacion.findByClaveOperacion('TEDEPEFE'),
			concepto:  PfinCatConcepto.findByClaveConcepto('IMPBRU'),
			claveAfecta: 'INCREMENTA',
			situacion: 'ACTIVO',
		).save(failOnError: true)
		
		new PfinCatOperacionConcepto(operacion:  PfinCatOperacion.findByClaveOperacion('CANPAG'),
			concepto:  PfinCatConcepto.findByClaveConcepto('INTERES'),
			claveAfecta: 'INCREMENTA',
			situacion: 'ACTIVO',
		).save(failOnError: true)
		
		new PfinCatOperacionConcepto(operacion:  PfinCatOperacion.findByClaveOperacion('CANPAG'),
			concepto:  PfinCatConcepto.findByClaveConcepto('CAPITAL'),
			claveAfecta: 'INCREMENTA',
			situacion: 'ACTIVO',
		).save(failOnError: true)
		
		new PfinCatOperacionConcepto(operacion:  PfinCatOperacion.findByClaveOperacion('CANPAG'),
			concepto:  PfinCatConcepto.findByClaveConcepto('IVAINT'),
			claveAfecta: 'INCREMENTA',
			situacion: 'ACTIVO',
		).save(failOnError: true)

		
		new PfinCatOperacionConcepto(operacion:  PfinCatOperacion.findByClaveOperacion('CRPAGOPRES'),
			concepto:  PfinCatConcepto.findByClaveConcepto('INTERES'),
			claveAfecta: 'INCREMENTA',
			situacion: 'ACTIVO',
		).save(failOnError: true)
		
		new PfinCatOperacionConcepto(operacion:  PfinCatOperacion.findByClaveOperacion('CRPAGOPRES'),
			concepto:  PfinCatConcepto.findByClaveConcepto('CAPITAL'),
			claveAfecta: 'INCREMENTA',
			situacion: 'ACTIVO',
		).save(failOnError: true)
		
		new PfinCatOperacionConcepto(operacion:  PfinCatOperacion.findByClaveOperacion('CRPAGOPRES'),
			concepto:  PfinCatConcepto.findByClaveConcepto('IVAINT'),
			claveAfecta: 'INCREMENTA',
			situacion: 'ACTIVO',
		).save(failOnError: true)
		*/

		def cuenta1 = new PfinCuenta(tipoCuenta:  'EJE',
			situacion: 'ACTIVO',
			cliente: clienteArturo,
		).save(failOnError: true)

		//NO ENCUENTRO DONDE SE UTILIZA LA CUENTA TIPO CREDITO
		/*
		new PfinCuenta(tipoCuenta:  'CREDITO',
			situacion: 'ACTIVO',
			cliente: clienteArturo,
		).save(failOnError: true)
		*/
		
		new PfinDivisa(claveDivisa:'MXP',
			descripcionDivisa:'Peso Mexicano'
		).save(failOnError: true)
	
		/*new PfinSaldo(fechaFoto:  new Date('09/30/2012'),
			divisa:  PfinDivisa.findByClaveDivisa('MXP'),
			saldo: 100, 
			cuenta: cuenta1,
		).save(failOnError: true)

		new PfinSaldo(fechaFoto:  new Date('10/30/2012'),
			divisa:  PfinDivisa.findByClaveDivisa('MXP'),
			saldo: 10,
			cuenta: cuenta1,
		).save(failOnError: true)
		
		def preMovimientoUno = new PfinPreMovimiento(cuenta:  cuenta1,
			divisa: PfinDivisa.findByClaveDivisa('MXP'),
			fechaOperacion:new Date('09/30/2012'),
			fechaLiquidacion:new Date('09/30/2012'),
			importeNeto: 1000.50,
			referencia: 34,
			prestamo : Prestamo.findByFolioSolicitud('34534'),
			nota : 'Pago de Prestamo',
			listaCobro : 3453,
			//pfinMovimiento()
			situacionPreMovimiento : SituacionPremovimiento.NO_PROCESADO,
			fechaRegistro:new Date('09/30/2012'),
			logIpDireccion: 'xxxxxxxxx',
			logUsuario:'xxxxxxxxxx',
			logHost:'xxxxxxxxxx',
			usuario : usuarioKermit,
			fechaAplicacion:new Date('09/30/2012'),
			numeroPagoAmortizacion: 1,
			operacion: PfinCatOperacion.findByClaveOperacion('TEDEPEFE'), 
		).save(failOnError: true)
		
		new PfinPreMovimientoDet(concepto:  PfinCatConcepto.findByClaveConcepto('IMPBRU'),
			importeConcepto: 1000.50,
			nota : 'Capital',
			preMovimiento : preMovimientoUno,
		).save(failOnError: true)

		new PfinPreMovimientoDet(concepto:  PfinCatConcepto.findByClaveConcepto('INTERES'),
			importeConcepto: 88888.88,
			nota : 'Capital',
			preMovimiento : preMovimientoUno,
		).save(failOnError: true)*/
		
		new PfinCatParametro(
			claveMedio : 'SistemaMtn',
			fechaMedio : new Date('01/31/2013'),
			operaSabado : 'false',
			operaDomingo : 'false',
			operaDiaFestivo : 'false',
			numeroDigitosDespliega : 2,
			consecutivoPublicacion : 100,
			pruebasClienteWsCr:'true',
		).save(failOnError: true)
		
		new SimCatFormaAplicacion(
			claveFormaAplicacion : 'CARGO_INICIAL',
			nombreFormaAplicacion : 'Cargo inicial'
		).save(failOnError: true)
		
		new SimCatFormaAplicacion(
			claveFormaAplicacion : 'MONTO_PRESTADO',
			nombreFormaAplicacion : 'Periodicamente dependiendo del monto prestamo'
		).save(failOnError: true)
		
		new SimCatFormaAplicacion(
			claveFormaAplicacion : 'SALDOA_LAFECHA',
			nombreFormaAplicacion : 'Periodicamente dependiendo del saldo a la fecha de calculo'
		).save(failOnError: true)
		
		new SimCatFormaAplicacion(
			claveFormaAplicacion : 'CARGO_FIJO',
			nombreFormaAplicacion : 'Periodicamente cargo fijo'
		).save(failOnError: true)
		
		new SimCatTipoAccesorio(
			claveTipoAccesorio : 'COMISION',
			nombreTipoAccesorio : 'Comisiones del Prestamo'
			).save(failOnError: true)

		new SimCatTipoAccesorio(
			claveTipoAccesorio : 'FIJO',
			nombreTipoAccesorio : 'Fijo'
			).save(failOnError: true)
	
		new SimCatTipoAccesorio(
			claveTipoAccesorio : 'RECARGO',
			nombreTipoAccesorio : 'Recargo'
			).save(failOnError: true, flush: true)

        new SimCatAccesorio(
                tipoAccesorio : SimCatTipoAccesorio.findByClaveTipoAccesorio('FIJO'),
                concepto :      PfinCatConcepto.findByClaveConcepto('INTERES')
        ).save(failOnError: true)

        new SimCatAccesorio(
                tipoAccesorio : SimCatTipoAccesorio.findByClaveTipoAccesorio('FIJO'),
                concepto :      PfinCatConcepto.findByClaveConcepto('IVAINT')
        ).save(failOnError: true)

        new SimCatAccesorio(
                tipoAccesorio : SimCatTipoAccesorio.findByClaveTipoAccesorio('COMISION'),
                concepto :      PfinCatConcepto.findByClaveConcepto('SEGUNICO')
        ).save(failOnError: true)

        new SimCatAccesorio(
                tipoAccesorio : SimCatTipoAccesorio.findByClaveTipoAccesorio('COMISION'),
                concepto :      PfinCatConcepto.findByClaveConcepto('SEGUNICOA')
        ).save(failOnError: true)

        new SimCatAccesorio(
                tipoAccesorio : SimCatTipoAccesorio.findByClaveTipoAccesorio('COMISION'),
                concepto :      PfinCatConcepto.findByClaveConcepto('SEGUNICOB')
        ).save(failOnError: true)

        new SimCatAccesorio(
                tipoAccesorio : SimCatTipoAccesorio.findByClaveTipoAccesorio('COMISION'),
                concepto :      PfinCatConcepto.findByClaveConcepto('SEGUNICOC')
        ).save(failOnError: true)

		new SimCatUnidad(
				claveUnidad  : 'UNIDAD',
				nombreUnidad : 'Unidad',
				valor 		 : '1'
				).save(failOnError: true)

		new SimCatUnidad(
				claveUnidad  : 'PORCENTUAL',
				nombreUnidad : 'Porcentual',
				valor 		 : '100'
				).save(failOnError: true)
	
		new SimCatUnidad(
				claveUnidad  : 'ALMILLAR',
				nombreUnidad : 'Al millar',
				valor 		 : '1000'
				).save(failOnError: true, flush: true)

		
		new PrestamoAccesorio(
				accesorio		:	SimCatAccesorio.findByConcepto(PfinCatConcepto.findByClaveConcepto('SEGUNICO')),
				valor			:	'1',
				unidad			:	SimCatUnidad.findByClaveUnidad('PORCENTUAL'),
				periodicidad	:	SimCatPeriodicidad.findByClavePeriodicidad('MES'),
				prestamo		: 	Prestamo.findByFolioSolicitud('34534')
				).save(failOnError: true)
        
		new PrestamoAccesorio(
				accesorio		:	SimCatAccesorio.findByConcepto(PfinCatConcepto.findByClaveConcepto('SEGUNICOA')),
				valor			:	'10',
				unidad			:	SimCatUnidad.findByClaveUnidad('UNIDAD'),
				periodicidad	:	SimCatPeriodicidad.findByClavePeriodicidad('SEMANA'),
				prestamo		: 	Prestamo.findByFolioSolicitud('34534')
				).save(failOnError: true)

		new PrestamoAccesorio(
				accesorio		:	SimCatAccesorio.findByConcepto(PfinCatConcepto.findByClaveConcepto('SEGUNICOB')),
				valor			:	'10',
				unidad			:	SimCatUnidad.findByClaveUnidad('ALMILLAR'),
				periodicidad	:	SimCatPeriodicidad.findByClavePeriodicidad('CATORCENA'),
				prestamo		: 	Prestamo.findByFolioSolicitud('34534')
				).save(failOnError: true)

		new PrestamoAccesorio(
				accesorio		:	SimCatAccesorio.findByConcepto(PfinCatConcepto.findByClaveConcepto('SEGUNICOC')),
				valor			:	'10',
				unidad			:	SimCatUnidad.findByClaveUnidad('PORCENTUAL'),
				periodicidad	:	SimCatPeriodicidad.findByClavePeriodicidad('QUINCENA'),
				prestamo		: 	Prestamo.findByFolioSolicitud('34534')
				).save(failOnError: true)

        new ProPromocionAccesorio (
                accesorio :   SimCatAccesorio.findByConcepto(PfinCatConcepto.findByClaveConcepto('INTERES')),
                orden :       1,
                proPromocion: ProPromocion.findByClavePromocion("MOR78987"),
                formaAplicacion	:	SimCatFormaAplicacion.findByClaveFormaAplicacion('SALDOA_LAFECHA'),
        ).save(failOnError: true)

        new ProPromocionAccesorio (
                accesorio :   SimCatAccesorio.findByConcepto(PfinCatConcepto.findByClaveConcepto('IVAINT')),
                orden :       2,
                proPromocion: ProPromocion.findByClavePromocion("MOR78987"),
                formaAplicacion	:	SimCatFormaAplicacion.findByClaveFormaAplicacion('SALDOA_LAFECHA'),
        ).save(failOnError: true)

        new ProPromocionAccesorio (
                accesorio :   SimCatAccesorio.findByConcepto(PfinCatConcepto.findByClaveConcepto('SEGUNICO')),
                orden :       3,
                proPromocion: ProPromocion.findByClavePromocion("MOR78987"),
                formaAplicacion	:	SimCatFormaAplicacion.findByClaveFormaAplicacion('SALDOA_LAFECHA'),
        ).save(failOnError: true)

        new ProPromocionAccesorio (
                accesorio :   SimCatAccesorio.findByConcepto(PfinCatConcepto.findByClaveConcepto('SEGUNICOA')),
                orden :       4,
                proPromocion: ProPromocion.findByClavePromocion("MOR78987"),
                formaAplicacion	:	SimCatFormaAplicacion.findByClaveFormaAplicacion('CARGO_INICIAL'),
        ).save(failOnError: true)

        new ProPromocionAccesorio (
                accesorio :   SimCatAccesorio.findByConcepto(PfinCatConcepto.findByClaveConcepto('SEGUNICOB')),
                orden :       5,
                proPromocion: ProPromocion.findByClavePromocion("MOR78987"),
                formaAplicacion	:	SimCatFormaAplicacion.findByClaveFormaAplicacion('CARGO_FIJO'),
        ).save(failOnError: true)

        new ProPromocionAccesorio (
                accesorio :   SimCatAccesorio.findByConcepto(PfinCatConcepto.findByClaveConcepto('SEGUNICOC')),
                orden :       6,
                proPromocion: ProPromocion.findByClavePromocion("MOR78987"),
                formaAplicacion	:	SimCatFormaAplicacion.findByClaveFormaAplicacion('MONTO_PRESTADO'),
        ).save(failOnError: true)

        //OPORTUNIDAD

        new ProPromocionAccesorio (
                accesorio :   SimCatAccesorio.findByConcepto(PfinCatConcepto.findByClaveConcepto('INTERES')),
                orden :       1,
                proPromocion: ProPromocion.findByClavePromocion("OPORTUNIDAD"),
                formaAplicacion	:	SimCatFormaAplicacion.findByClaveFormaAplicacion('SALDOA_LAFECHA'),
        ).save(failOnError: true)

        new ProPromocionAccesorio (
                accesorio :   SimCatAccesorio.findByConcepto(PfinCatConcepto.findByClaveConcepto('IVAINT')),
                orden :       2,
                proPromocion: ProPromocion.findByClavePromocion("OPORTUNIDAD"),
                formaAplicacion	:	SimCatFormaAplicacion.findByClaveFormaAplicacion('SALDOA_LAFECHA'),
        ).save(failOnError: true)

        new ProPromocionAccesorio (
                accesorio :   SimCatAccesorio.findByConcepto(PfinCatConcepto.findByClaveConcepto('SEGUNICO')),
                orden :       3,
                proPromocion: ProPromocion.findByClavePromocion("OPORTUNIDAD"),
                formaAplicacion	:	SimCatFormaAplicacion.findByClaveFormaAplicacion('SALDOA_LAFECHA'),
        ).save(failOnError: true)

        new ProPromocionAccesorio (
                accesorio :   SimCatAccesorio.findByConcepto(PfinCatConcepto.findByClaveConcepto('SEGUNICOA')),
                orden :       4,
                proPromocion: ProPromocion.findByClavePromocion("OPORTUNIDAD"),
                formaAplicacion	:	SimCatFormaAplicacion.findByClaveFormaAplicacion('CARGO_INICIAL'),
        ).save(failOnError: true)

        new ProPromocionAccesorio (
                accesorio :   SimCatAccesorio.findByConcepto(PfinCatConcepto.findByClaveConcepto('SEGUNICOB')),
                orden :       5,
                proPromocion: ProPromocion.findByClavePromocion("OPORTUNIDAD"),
                formaAplicacion	:	SimCatFormaAplicacion.findByClaveFormaAplicacion('CARGO_FIJO'),
        ).save(failOnError: true)

        new ProPromocionAccesorio (
                accesorio :   SimCatAccesorio.findByConcepto(PfinCatConcepto.findByClaveConcepto('SEGUNICOC')),
                orden :       6,
                proPromocion: ProPromocion.findByClavePromocion("OPORTUNIDAD"),
                formaAplicacion	:	SimCatFormaAplicacion.findByClaveFormaAplicacion('MONTO_PRESTADO'),
        ).save(failOnError: true)

        //Otra

        new SimCatListaCobroEstatus (
			    claveListaEstatus : "NO_GENERADA",
			    nombreListaEstatus : "Lista de cobro no Generada",
			    aplicaParcial : 'true',
       	).save(failOnError: true)

        new SimCatListaCobroEstatus (
			    claveListaEstatus : "GENERADA",
			    nombreListaEstatus : "Lista de cobro Generada",
			    aplicaParcial : 'true',
       	).save(failOnError: true)

        new SimCatListaCobroEstatus (
			    claveListaEstatus : "ENVIADA_DEPENDENCIA",
			    nombreListaEstatus : "Lista de cobro enviada a la Dependencia",
			    aplicaParcial : 'true',
       	).save(failOnError: true)

        new SimCatListaCobroEstatus (
			    claveListaEstatus : "INSTALADA_DEPENDENCIA",
			    nombreListaEstatus : "Lista de cobro instalada por la dependencia",
			    aplicaParcial : 'true',
       	).save(failOnError: true)

        new SimCatListaCobroEstatus (
			    claveListaEstatus : "DEVUELTA_DEPENDENCIA",
			    nombreListaEstatus : "Lista de cobro devuelta por la Dependencia",
			    aplicaParcial : 'true',
       	).save(failOnError: true)

        new SimCatListaCobroEstatus (
			    claveListaEstatus : "REGISTRO_PAGOS",
			    nombreListaEstatus : "Registro de pagos de la lista de cobro",
			    aplicaParcial : 'true',
       	).save(failOnError: true)

       	new SimCatListaCobroEstatus (
			    claveListaEstatus : "APLICADA_PARCIALMENTE",
			    nombreListaEstatus : "Lista de cobro aplicada parcialmente",
			    aplicaParcial : 'true',
       	).save(failOnError: true)       	

       	new SimCatListaCobroEstatus (
			    claveListaEstatus : "APLICADA_COMPLETAMENTE",
			    nombreListaEstatus : "Lista de cobro aplicada completamente",
			    aplicaParcial : 'true',
       	).save(failOnError: true)       	

       	new SimCatListaCobroEstatus (
			    claveListaEstatus : "PUBLICADA",
			    nombreListaEstatus : "Lista de cobro publicada",
			    aplicaParcial : 'true',
       	).save(failOnError: true,flush: true)       	

        //GENERA LAS LISTAS DE COBRO
        listaCobroService.crearListasCobro(dependenciaImss,2013,periodicidadQuincena)
        listaCobroService.crearListasCobro(dependenciaImss,2014,periodicidadQuincena)
        listaCobroService.crearListasCobro(dependenciaCfe,2013,periodicidadQuincena)
		listaCobroService.crearListasCobro(dependenciaCfe,2014,periodicidadQuincena)

		
		//ALTA TEMPORAL DE UN PRESTAMO PARA PROBAR SOLICITUDES DE CREDITO REAL
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
				).save(failOnError: true)

		def clienteJavier = new RsCliente(persona: javierHernandez,
				apellidoPaterno : javierHernandez.apellidoPaterno,
				primerNombre :  javierHernandez.primerNombre,
				dependencia : [
                        EntDependencia.findByClaveDependencia('CFE')
                ], 
				numeroDeNomina: 'JCHSDFYUYUI',
				).save(failOnError: true)

		new Prestamo(
				cliente : 			   clienteJavier,
				correoSolicitante:     "javierhernandez@gmail.com",
				folioSolicitud : 	   1,
				dependencia : 		   EntDependencia.findByClaveDependencia('IMSS'),
				promocion: 			   ProPromocion.findByClavePromocion('MOR78987'),
				sucursal: 			   EntSucursal.findByClaveSucursal('EDOMEX'),
				delegacion: 		   EntDelegacion.findByClaveDelegacion('ZACATECAS'),
				vendedor : 			   EmpEmpleado.findByNumeroNomina('001'),
				fechaSolicitud:		   new Date('01/01/2013'),
				montoSolicitado: 	   10000,
				percepcionesMensuales: 20000,
				deduccionesMensuales:  25000,
				estatusSolicitud:      PrestamoEstatus.ACTIVO,
				formaDeDispercion:     SimCatFormaEntrega.findByClaveFormaEntrega('VENBANCO'),
				documentosCorrectos:   false,
				aprobado:              false,
				reenviarSolicitud:     false,
				incluirEnListasCobro:  true,
				fechaCobro:            new Date('01/16/2013'),
				).save(failOnError: true)	

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
				).save(failOnError: true)

		def clienteFrancisco = new RsCliente(persona: franciscorodriguez,
				apellidoPaterno : franciscorodriguez.apellidoPaterno,
				primerNombre :  franciscorodriguez.primerNombre,

				dependencia : [
                        EntDependencia.findByClaveDependencia('CFE')
                ], 
				numeroDeNomina: 'MAZASDFYUYUI',
				).save(failOnError: true)

		new Prestamo(
				cliente : 			   clienteFrancisco,
				correoSolicitante:     "franciscorodriguez@gmail.com",
				folioSolicitud : 	   3,
				dependencia : 		   EntDependencia.findByClaveDependencia('IMSS'),
				promocion: 			   ProPromocion.findByClavePromocion('MOR78987'),
				sucursal: 			   EntSucursal.findByClaveSucursal('EDOMEX'),
				delegacion: 		   EntDelegacion.findByClaveDelegacion('ZACATECAS'),
				vendedor : 			   EmpEmpleado.findByNumeroNomina('001'),
				fechaSolicitud:		   new Date('01/01/2013'),
				montoSolicitado: 	   10000,
				percepcionesMensuales: 20000,
				deduccionesMensuales:  25000,
				estatusSolicitud:      PrestamoEstatus.ACTIVO,
				formaDeDispercion:     SimCatFormaEntrega.findByClaveFormaEntrega('VENBANCO'),
				documentosCorrectos:   false,
				aprobado:              false,
				reenviarSolicitud:     false,
				incluirEnListasCobro:  true,
				fechaCobro:            new Date('01/18/2013'),
				).save(failOnError: true,flush:true)
		

        //BORRAR LA SESION DEL OBJETO The Hibernate Session
        def ctx = AH.application.mainContext
		def sessionFactory = ctx.sessionFactory
		def session = sessionFactory.currentSession
		session.clear()
    }

	def destroy = {
	}
}
