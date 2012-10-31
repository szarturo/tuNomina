import com.sim.catalogo.*
import com.sim.usuario.*
import com.rs.gral.*
import com.sim.entidad.*
import com.sim.cliente.*
import com.sim.credito.*
import com.sim.empresa.*
import com.sim.producto.*
import com.sim.calendario.*

import test.*;

import abltutorial.Customer
import abltutorial.LineItem
import abltutorial.Product
import abltutorial.PurchaseOrder

class BootStrap {

	def init = { servletContext ->

		new SimCatTipoPersona(claveTipoPersona:  'AVAL',
				nombreTipoPersona: 'AVAL',
				descripcionTipoPersona: 'DESCRIPCION AVAL',
				).save(flush: true,failOnError: true)

		new SimCatTipoPersona(claveTipoPersona:  'CLIENTE',
				nombreTipoPersona: 'CLIENTE',
				descripcionTipoPersona: 'DESCRIPCION CLIENTE',
				).save(flush: true,failOnError: true)

		new SimCatTipoPersona(claveTipoPersona:  'USUARIO',
				nombreTipoPersona: 'USUARIO',
				descripcionTipoPersona: 'USUARIO DEL SISTEMA',
				).save(flush: true,failOnError: true)

		new SimCatTipoPersona(claveTipoPersona:  'EMPLEADO',
				nombreTipoPersona: 'EMPLEADO',
				descripcionTipoPersona: 'EMPLEADO DE LA EMPRESA',
				).save(flush: true,failOnError: true)

		new SimCatTipoPersona(claveTipoPersona:  'REFCLIENTE',
				nombreTipoPersona: 'REFERENCIA DEL CLIENTE',
				descripcionTipoPersona: 'REFERENCIA DEL CLIENTE',
				).save(flush: true,failOnError: true)

		new SimCatTipoPersona(claveTipoPersona:  'UEF',
				nombreTipoPersona: 'UEF DEL CLIENTE',
				descripcionTipoPersona: 'UNIDAD ECONOMICA FAMILIAR DEL CLIENTE',
				).save(flush: true,failOnError: true)

		new SimCatTipoPersona(claveTipoPersona:  'GARDEP',
				nombreTipoPersona: 'GARANTE DEPOSITARIO',
				descripcionTipoPersona: 'GARANTE DEPOSITARIO DEL CLIENTE',
				).save(flush: true,failOnError: true)


		def adminRole = new Rol(authority: 'ROLE_ADMIN', name: 'Admin')
		adminRole.id = 'ROLE_ADMIN'
		adminRole.save(flush: true,failOnError: true)

		def userRole = new Rol(authority: 'ROLE_USER', name: 'User')
		userRole.id = 'ROLE_USER'
		userRole.save(flush: true,failOnError: true)
		
		def managerRole = new Rol(authority: 'ROLE_MANAGER', name: 'Manager')
		managerRole.id = 'ROLE_MANAGER'
		managerRole.save(flush: true,failOnError: true)
		
		def mesaControlRole = new Rol(authority: 'ROLE_MESA_CONTROL', name: 'Mesa de Control')
		mesaControlRole.id = 'ROLE_MESA_CONTROL'
		mesaControlRole.save(flush: true,failOnError: true)

		def controlCalidadRole = new Rol(authority: 'ROLE_CONTROL_CALIDAD', name: 'Control de Calidad')
		controlCalidadRole.id = 'ROLE_CONTROL_CALIDAD'
		controlCalidadRole.save(flush: true,failOnError: true)
				
		def usuarioAdmin = new Usuario(username: 'admin', enabled: true, password: '1234')
		usuarioAdmin.save(flush: true)

		UsuarioRol.create usuarioAdmin, adminRole, true
		
		assert Usuario.count() == 1
		assert Rol.count() == 5
		assert UsuarioRol.count() == 1
		
		//DA DE ALTA UNA PERSONA Y LE ASIGNA EL USUARIO ADMINISTRADOR
		def adminPersona = new RsPersona(
				apellidoPaterno: "ADMINISTRADOR",
				primerNombre: "MICROFINANCIERAS",
				email : "sistema.microfinanciera@gmail.com",
				tiposPersona : [
					SimCatTipoPersona.findByClaveTipoPersona('USUARIO')
				],
				usuario : usuarioAdmin).save(flush: true,failOnError: true)
				
				
		//USUARIOS DADOS DE ALTA POR ACTIVITI
		def usuarioKermit = new Usuario(username: 'kermit', enabled: true, password: 'kermit')
		usuarioKermit.save(flush: true)
		UsuarioRol.create usuarioKermit, userRole, true
		
		def usuarioFozzie = new Usuario(username: 'fozzie', enabled: true, password: 'fozzie')
		usuarioFozzie.save(flush: true)
		UsuarioRol.create usuarioFozzie, managerRole, true

		def usuarioPeter = new Usuario(username: 'peter', enabled: true, password: 'peter')
		usuarioPeter.save(flush: true)
		UsuarioRol.create usuarioPeter, userRole, true
		
		def usuarioRuben = new Usuario(username: 'ruben', enabled: true, password: 'ruben')
		usuarioRuben.save(flush: true)
		UsuarioRol.create usuarioRuben, mesaControlRole, true
		
		def usuarioRaul = new Usuario(username: 'raul', enabled: true, password: 'raul')
		usuarioRaul.save(flush: true)
		UsuarioRol.create usuarioRaul, controlCalidadRole, true

		
		//DA DE ALTA UNA PERSONA Y LE ASIGNA EL USUARIO KERMIT
		def kermitPersona = new RsPersona(
				apellidoPaterno: "Perez",
				primerNombre: "Kermit",
				email : "mrugerio@gmail.com",
				tiposPersona : [
					SimCatTipoPersona.findByClaveTipoPersona('USUARIO')
				],
				usuario : usuarioKermit).save(flush: true,failOnError: true)

		//IMPLEMENTACION DE SEGURIDAD A NIVEL Dynamic request maps
		//new Requestmap(url: '/user/**', configAttribute: 'ROLE_ADMIN').save(flush: true,failOnError: true)
		//new Requestmap(url: '/rsConfGpoEmpresa/**', configAttribute: 'ROLE_USER').save(flush: true,failOnError: true)
		new Requestmap(url: '/simCatBanco/create', configAttribute: 'ROLE_ADMIN').save(flush: true,failOnError: true)
		
		
		//ACTIVITI
		
		["mail.smtp.auth":"true",
			"mail.smtp.socketFactory.port":"465",
			"mail.smtp.socketFactory.class":"javax.net.ssl.SSLSocketFactory",
			"mail.smtp.socketFactory.fallback":"false",
			"mail.smtp.starttls.required": "true"].each { k, v ->
			System.setProperty k, v
			}
			

		new SimCatTipoDocumento(claveTipoDocumento:  'IDENTIFICACION',
				nombreTipoDocumento: 'IDENTIFICACION OFICIAL',
				).save(flush: true,failOnError: true)

		new SimCatTipoDocumento(claveTipoDocumento:  'DOMICILIO',
				nombreTipoDocumento: 'COMPROBANTE DE DOMICILIO',
				).save(flush: true,failOnError: true)

		new SimCatTipoDocumento(claveTipoDocumento:  'LEGAL',
				nombreTipoDocumento: 'LEGAL',
				).save(flush: true,failOnError: true)

		new SimCatDocumento(claveDocumento:  'CFE',
				nombreDocumento: 'COMPROBANTE DE LUZ',
				descripcion: 'COMPROBANTE DE LUZ',
				tipoDocumento : SimCatTipoDocumento.findByClaveTipoDocumento('DOMICILIO'),
				esReporte : 'false',
				).save(flush: true,failOnError: true)

		new SimCatDocumento(claveDocumento:  'ACTA_NACIMIENTO',
				nombreDocumento: 'ACTA DE NACIMIENTO',
				descripcion: 'ACTA DE NACIMIENTO',
				tipoDocumento : SimCatTipoDocumento.findByClaveTipoDocumento('LEGAL'),
				//simCatReporte : SimCatReporte.findByClaveReporte('CLAVE_1'),
				esReporte : 'true',
				).save(flush: true,failOnError: true)

		new SimCatDocumento(claveDocumento:  'IFE',
				nombreDocumento: 'CREDENCIAL IFE',
				descripcion: 'CREDENCIAL IFE',
				tipoDocumento : SimCatTipoDocumento.findByClaveTipoDocumento('IDENTIFICACION'),
				esReporte : 'false',
				).save(flush: true,failOnError: true)

		new SimCatDocumento(claveDocumento:  'LICENCIA_CONDUCIR',
				nombreDocumento: 'LICENCIA DE CONDUCIR',
				descripcion: 'LICENCIA DE CONDUCIR',
				tipoDocumento : SimCatTipoDocumento.findByClaveTipoDocumento('IDENTIFICACION'),
				esReporte : 'false',
				).save(flush: true,failOnError: true)

		new SimCatEscolaridad(claveEscolaridad:  'PRIMARIA',
				nombreEscolaridad: 'PRIMARIA',
				).save(flush: true,failOnError: true)

		new SimCatEscolaridad(claveEscolaridad:  'SECUNDARIA',
				nombreEscolaridad: 'SECUNDARIA',
				).save(flush: true,failOnError: true)

		new SimCatEscolaridad(claveEscolaridad:  'PREPA',
				nombreEscolaridad: 'PREPARATORIA',
				).save(flush: true,failOnError: true)

		new SimCatEscolaridad(claveEscolaridad:  'LIC',
				nombreEscolaridad: 'LICENCIATURA',
				).save(flush: true,failOnError: true)


		new SimCatDescTelefono(claveDescripcionTelefono: 'CASA',
				nombreDescripcionTelefono: 'CASA',
				).save(flush: true,failOnError: true)

		new SimCatDescTelefono(claveDescripcionTelefono: 'OFICINA',
				nombreDescripcionTelefono: 'OFICINA',
				).save(flush: true,failOnError: true)

		new SimCatDescTelefono(claveDescripcionTelefono: 'FAX',
				nombreDescripcionTelefono: 'FAX',
				).save(flush: true,failOnError: true)

		new RsGralEstado(cveEstado: 'AGS',
				nombreEstado: 'AGUASCALIENTES',
				aliasEstado : 'AGS').save(flush: true,failOnError: true)

		new RsGralEstado(cveEstado: 'DF',
				nombreEstado: 'DISTRITO FEDERAL',
				aliasEstado : 'DF').save(flush: true,failOnError: true)

		new RsGralEstado(cveEstado: 'EDOMEX',
				nombreEstado: 'ESTADO DE MEXICO',
				aliasEstado : 'EDOMEX').save(flush: true,failOnError: true)

		new RsGralCiudad(nombreCiudad: 'EL COLORADO',
				estado : RsGralEstado.findByCveEstado('AGS')).save(flush: true,failOnError: true)

		new RsGralCiudad(nombreCiudad: 'AMAPOLAS DEL RIO',
				estado : RsGralEstado.findByCveEstado('AGS')).save(flush: true,failOnError: true)

		new RsGralCiudad(nombreCiudad: 'EL CONEJAL',
				estado : RsGralEstado.findByCveEstado('AGS')).save(flush: true,failOnError: true)

		new RsGralCiudad(nombreCiudad: 'DISTRITO NORTE',
				estado : RsGralEstado.findByCveEstado('DF')).save(flush: true,failOnError: true)

		new RsGralCiudad(nombreCiudad: 'DISTRITO SUR',
				estado : RsGralEstado.findByCveEstado('DF')).save(flush: true,failOnError: true)

		new RsGralCiudad(nombreCiudad: 'METEPEC',
				estado : RsGralEstado.findByCveEstado('EDOMEX')).save(flush: true,failOnError: true)

		new RsGralCiudad(nombreCiudad: 'CHIMALHUACAN',
				estado : RsGralEstado.findByCveEstado('EDOMEX')).save(flush: true,failOnError: true)

		new RsGralCiudad(nombreCiudad: 'MEXICO',
				estado : RsGralEstado.findByCveEstado('EDOMEX')).save(flush: true,failOnError: true)

		new RsGralDelegacionMunicipio(nombreDelegacionMunicipio: 'EL COLORADO UNO',
				ciudad : RsGralCiudad.findByNombreCiudad('EL COLORADO')).save(flush: true,failOnError: true)

		new RsGralDelegacionMunicipio(nombreDelegacionMunicipio: 'EL COLORADO DOS',
				ciudad : RsGralCiudad.findByNombreCiudad('EL COLORADO')).save(flush: true,failOnError: true)

		new RsGralDelegacionMunicipio(nombreDelegacionMunicipio: 'EL COLORADO TRES',
				ciudad : RsGralCiudad.findByNombreCiudad('EL COLORADO')).save(flush: true,failOnError: true)

		new RsGralDelegacionMunicipio(nombreDelegacionMunicipio: 'EL AMAPOLAS UNO',
				ciudad : RsGralCiudad.findByNombreCiudad('AMAPOLAS DEL RIO')).save(flush: true,failOnError: true)

		new RsGralDelegacionMunicipio(nombreDelegacionMunicipio: 'EL AMAPOLAS DOS',
				ciudad : RsGralCiudad.findByNombreCiudad('AMAPOLAS DEL RIO')).save(flush: true,failOnError: true)

		new RsGralDelegacionMunicipio(nombreDelegacionMunicipio: 'EL AMAPOLAS TRES',
				ciudad : RsGralCiudad.findByNombreCiudad('AMAPOLAS DEL RIO')).save(flush: true,failOnError: true)

		new RsGralDelegacionMunicipio(nombreDelegacionMunicipio: 'EL CONEJAL UNO',
				ciudad : RsGralCiudad.findByNombreCiudad('EL CONEJAL')).save(flush: true,failOnError: true)

		new RsGralDelegacionMunicipio(nombreDelegacionMunicipio: 'EL CONEJAL DOS',
				ciudad : RsGralCiudad.findByNombreCiudad('EL CONEJAL')).save(flush: true,failOnError: true)

		new RsGralDelegacionMunicipio(nombreDelegacionMunicipio: 'EL CONEJAL TRES',
				ciudad : RsGralCiudad.findByNombreCiudad('EL CONEJAL')).save(flush: true,failOnError: true)

		new RsGralDelegacionMunicipio(nombreDelegacionMunicipio: 'CUAUHTEMOC',
				ciudad : RsGralCiudad.findByNombreCiudad('DISTRITO NORTE')).save(flush: true,failOnError: true)

		new RsGralDelegacionMunicipio(nombreDelegacionMunicipio: 'VENUSTIANO CARRANZA',
				ciudad : RsGralCiudad.findByNombreCiudad('DISTRITO NORTE')).save(flush: true,failOnError: true)

		new RsGralDelegacionMunicipio(nombreDelegacionMunicipio: 'ALVARO OBREGON',
				ciudad : RsGralCiudad.findByNombreCiudad('DISTRITO NORTE')).save(flush: true,failOnError: true)

		new RsGralDelegacionMunicipio(nombreDelegacionMunicipio: 'BENITO JUAREZ',
				ciudad : RsGralCiudad.findByNombreCiudad('DISTRITO SUR')).save(flush: true,failOnError: true)

		new RsGralDelegacionMunicipio(nombreDelegacionMunicipio: 'COYOACAN',
				ciudad : RsGralCiudad.findByNombreCiudad('DISTRITO SUR')).save(flush: true,failOnError: true)

		new RsGralDelegacionMunicipio(nombreDelegacionMunicipio: 'TLALPAN',
				ciudad : RsGralCiudad.findByNombreCiudad('DISTRITO SUR')).save(flush: true,failOnError: true)

		new RsGralDelegacionMunicipio(nombreDelegacionMunicipio: 'METEPEC UNO',
				ciudad : RsGralCiudad.findByNombreCiudad('METEPEC')).save(flush: true,failOnError: true)

		new RsGralDelegacionMunicipio(nombreDelegacionMunicipio: 'METEPEC DOS',
				ciudad : RsGralCiudad.findByNombreCiudad('METEPEC')).save(flush: true,failOnError: true)

		new RsGralDelegacionMunicipio(nombreDelegacionMunicipio: 'METEPEC TRES',
				ciudad : RsGralCiudad.findByNombreCiudad('METEPEC')).save(flush: true,failOnError: true)

		new RsGralDelegacionMunicipio(nombreDelegacionMunicipio: 'CHIMALHUACAN UNO',
				ciudad : RsGralCiudad.findByNombreCiudad('CHIMALHUACAN')).save(flush: true,failOnError: true)

		new RsGralDelegacionMunicipio(nombreDelegacionMunicipio: 'CHIMALHUACAN DOS',
				ciudad : RsGralCiudad.findByNombreCiudad('CHIMALHUACAN')).save(flush: true,failOnError: true)

		new RsGralDelegacionMunicipio(nombreDelegacionMunicipio: 'CHIMALHUACAN TRES',
				ciudad : RsGralCiudad.findByNombreCiudad('CHIMALHUACAN')).save(flush: true,failOnError: true)


		new RsGralDelegacionMunicipio(nombreDelegacionMunicipio: 'MEXICO UNO',
				ciudad : RsGralCiudad.findByNombreCiudad('MEXICO')).save(flush: true,failOnError: true)

		new RsGralDelegacionMunicipio(nombreDelegacionMunicipio: 'MEXICO DOS',
				ciudad : RsGralCiudad.findByNombreCiudad('MEXICO')).save(flush: true,failOnError: true)

		new RsGralDelegacionMunicipio(nombreDelegacionMunicipio: 'MEXICO TRES',
				ciudad : RsGralCiudad.findByNombreCiudad('MEXICO')).save(flush: true,failOnError: true)

		new SimCatTipoAsentamiento(claveTipoAsentamiento:  'CIUDAD',
				nombreTipoAsentamiento: 'CIUDAD',
				).save(flush: true,failOnError: true)
		new SimCatTipoAsentamiento(claveTipoAsentamiento:  'COLONIA',
				nombreTipoAsentamiento: 'COLONIA',
				).save(flush: true,failOnError: true)
		new SimCatTipoAsentamiento(claveTipoAsentamiento:  'URBANA',
				nombreTipoAsentamiento: 'URBANA',
				).save(flush: true,failOnError: true)


		new RsGralAsentamiento(nombreAsentamiento: 'EL COLORADO 1 ASENTAMIENTO UNO',
				codigoPostal: '01000',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('EL COLORADO UNO'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(flush: true,failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'EL COLORADO 1 ASENTAMIENTO DOS',
				codigoPostal: '01010',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('EL COLORADO UNO'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(flush: true,failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'EL COLORADO 1 ASENTAMIENTO TRES',
				codigoPostal: '01020',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('EL COLORADO UNO'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(flush: true,failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'EL COLORADO 2 ASENTAMIENTO UNO',
				codigoPostal: '01100',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('EL COLORADO DOS'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(flush: true,failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'EL COLORADO 2 ASENTAMIENTO DOS',
				codigoPostal: '01110',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('EL COLORADO DOS'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(flush: true,failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'EL COLORADO 2 ASENTAMIENTO TRES',
				codigoPostal: '01120',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('EL COLORADO DOS'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(flush: true,failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'EL COLORADO 3 ASENTAMIENTO UNO',
				codigoPostal: '01200',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('EL COLORADO TRES'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(flush: true,failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'EL COLORADO 3 ASENTAMIENTO DOS',
				codigoPostal: '01210',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('EL COLORADO TRES'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(flush: true,failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'EL COLORADO 3 ASENTAMIENTO TRES',
				codigoPostal: '01220',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('EL COLORADO TRES'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(flush: true,failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'EL AMAPOLAS 1 ASENTAMIENTO UNO',
				codigoPostal: '01230',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('EL AMAPOLAS UNO'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(flush: true,failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'EL AMAPOLAS 1 ASENTAMIENTO DOS',
				codigoPostal: '01240',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('EL AMAPOLAS UNO'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(flush: true,failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'EL AMAPOLAS 1 ASENTAMIENTO TRES',
				codigoPostal: '01250',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('EL AMAPOLAS UNO'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(flush: true,failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'EL AMAPOLAS 2 ASENTAMIENTO UNO',
				codigoPostal: '01260',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('EL AMAPOLAS DOS'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(flush: true,failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'EL AMAPOLAS 2 ASENTAMIENTO DOS',
				codigoPostal: '01270',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('EL AMAPOLAS DOS'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(flush: true,failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'EL AMAPOLAS 2 ASENTAMIENTO TRES',
				codigoPostal: '01280',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('EL AMAPOLAS DOS'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(flush: true,failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'EL AMAPOLAS 3 ASENTAMIENTO UNO',
				codigoPostal: '01290',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('EL AMAPOLAS TRES'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(flush: true,failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'EL AMAPOLAS 3 ASENTAMIENTO DOS',
				codigoPostal: '01300',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('EL AMAPOLAS TRES'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(flush: true,failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'EL AMAPOLAS 3 ASENTAMIENTO TRES',
				codigoPostal: '01310',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('EL AMAPOLAS TRES'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(flush: true,failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'EL CONEJAL 1 ASENTAMIENTO UNO',
				codigoPostal: '01320',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('EL CONEJAL UNO'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(flush: true,failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'EL CONEJAL 1 ASENTAMIENTO DOS',
				codigoPostal: '01330',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('EL CONEJAL UNO'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(flush: true,failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'EL CONEJAL 1 ASENTAMIENTO TRES',
				codigoPostal: '01340',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('EL CONEJAL UNO'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(flush: true,failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'EL CONEJAL 2 ASENTAMIENTO UNO',
				codigoPostal: '01350',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('EL CONEJAL DOS'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(flush: true,failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'EL CONEJAL 2 ASENTAMIENTO DOS',
				codigoPostal: '01360',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('EL CONEJAL DOS'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(flush: true,failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'EL CONEJAL 2 ASENTAMIENTO TRES',
				codigoPostal: '01370',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('EL CONEJAL DOS'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(flush: true,failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'EL CONEJAL 3 ASENTAMIENTO UNO',
				codigoPostal: '01380',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('EL CONEJAL TRES'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(flush: true,failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'EL CONEJAL 3 ASENTAMIENTO DOS',
				codigoPostal: '01390',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('EL CONEJAL TRES'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(flush: true,failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'EL CONEJAL 3 ASENTAMIENTO TRES',
				codigoPostal: '01400',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('EL CONEJAL TRES'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(flush: true,failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'VISTA ALEGRE',
				codigoPostal: '06860',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('CUAUHTEMOC'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(flush: true,failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'BUENOS AIRES',
				codigoPostal: '06861',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('CUAUHTEMOC'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(flush: true,failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'BOTURINI',
				codigoPostal: '06862',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('CUAUHTEMOC'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(flush: true,failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'VENUSTIANO CARRANZA UNO',
				codigoPostal: '06900',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('VENUSTIANO CARRANZA'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(flush: true,failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'VENUSTIANO CARRANZA DOS',
				codigoPostal: '06910',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('VENUSTIANO CARRANZA'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(flush: true,failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'VENUSTIANO CARRANZA TRES',
				codigoPostal: '06920',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('VENUSTIANO CARRANZA'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(flush: true,failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'ALVARO OBREGON UNO',
				codigoPostal: '01410',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('ALVARO OBREGON'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(flush: true,failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'ALVARO OBREGON DOS',
				codigoPostal: '01420',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('ALVARO OBREGON'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(flush: true,failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'ALVARO OBREGON TRES',
				codigoPostal: '01430',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('ALVARO OBREGON'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(flush: true,failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'SANTA CRUZ ATOYAC',
				codigoPostal: '01440',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('COYOACAN'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(flush: true,failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'DEL VALLE',
				codigoPostal: '01450',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('COYOACAN'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(flush: true,failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'NARVARTE',
				codigoPostal: '01460',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('COYOACAN'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(flush: true,failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'BENITO JUAREZ UNO',
				codigoPostal: '01470',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('BENITO JUAREZ'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(flush: true,failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'BENITO JUAREZ DOS',
				codigoPostal: '01480',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('BENITO JUAREZ'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(flush: true,failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'BENITO JUAREZ TRES',
				codigoPostal: '01490',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('BENITO JUAREZ'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(flush: true,failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'TLALPAN UNO',
				codigoPostal: '01500',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('TLALPAN'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(flush: true,failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'TLALPAN DOS',
				codigoPostal: '01510',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('TLALPAN'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(flush: true,failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'TLALPAN TRES',
				codigoPostal: '01520',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('TLALPAN'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(flush: true,failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'CHIMALHUACAN 1 ASENTAMIENTO UNO',
				codigoPostal: '01530',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('CHIMALHUACAN UNO'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(flush: true,failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'CHIMALHUACAN 1 ASENTAMIENTO DOS',
				codigoPostal: '01540',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('CHIMALHUACAN UNO'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(flush: true,failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'CHIMALHUACAN 1 ASENTAMIENTO TRES',
				codigoPostal: '01550',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('CHIMALHUACAN UNO'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(flush: true,failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'CHIMALHUACAN 2 ASENTAMIENTO UNO',
				codigoPostal: '01560',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('CHIMALHUACAN DOS'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(flush: true,failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'CHIMALHUACAN 2 ASENTAMIENTO DOS',
				codigoPostal: '01570',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('CHIMALHUACAN DOS'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(flush: true,failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'CHIMALHUACAN 2 ASENTAMIENTO TRES',
				codigoPostal: '01580',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('CHIMALHUACAN DOS'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(flush: true,failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'CHIMALHUACAN 3 ASENTAMIENTO UNO',
				codigoPostal: '01590',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('CHIMALHUACAN TRES'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(flush: true,failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'CHIMALHUACAN 3 ASENTAMIENTO DOS',
				codigoPostal: '01600',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('CHIMALHUACAN TRES'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(flush: true,failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'CHIMALHUACAN 3 ASENTAMIENTO TRES',
				codigoPostal: '01610',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('CHIMALHUACAN TRES'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(flush: true,failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'METEPEC 1 ASENTAMIENTO UNO',
				codigoPostal: '01620',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('METEPEC UNO'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(flush: true,failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'METEPEC 1 ASENTAMIENTO DOS',
				codigoPostal: '01630',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('METEPEC UNO'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(flush: true,failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'METEPEC 1 ASENTAMIENTO TRES',
				codigoPostal: '01640',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('METEPEC UNO'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(flush: true,failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'METEPEC 2 ASENTAMIENTO UNO',
				codigoPostal: '01650',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('METEPEC DOS'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(flush: true,failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'METEPEC 2 ASENTAMIENTO DOS',
				codigoPostal: '01660',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('METEPEC DOS'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(flush: true,failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'METEPEC 2 ASENTAMIENTO TRES',
				codigoPostal: '01670',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('METEPEC DOS'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(flush: true,failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'METEPEC 3 ASENTAMIENTO UNO',
				codigoPostal: '01680',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('METEPEC TRES'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(flush: true,failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'METEPEC 3 ASENTAMIENTO DOS',
				codigoPostal: '01690',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('METEPEC TRES'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(flush: true,failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'METEPEC 3 ASENTAMIENTO TRES',
				codigoPostal: '01700',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('METEPEC TRES'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(flush: true,failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'MEXICO 1 ASENTAMIENTO UNO',
				codigoPostal: '01710',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('MEXICO UNO'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(flush: true,failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'MEXICO 1 ASENTAMIENTO DOS',
				codigoPostal: '01720',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('MEXICO UNO'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(flush: true,failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'MEXICO 1 ASENTAMIENTO TRES',
				codigoPostal: '01730',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('MEXICO UNO'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(flush: true,failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'MEXICO 2 ASENTAMIENTO UNO',
				codigoPostal: '01740',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('MEXICO DOS'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(flush: true,failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'MEXICO 2 ASENTAMIENTO DOS',
				codigoPostal: '01750',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('MEXICO DOS'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(flush: true,failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'MEXICO 2 ASENTAMIENTO TRES',
				codigoPostal: '01760',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('MEXICO DOS'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(flush: true,failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'MEXICO 3 ASENTAMIENTO UNO',
				codigoPostal: '01770',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('MEXICO TRES'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(flush: true,failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'MEXICO 3 ASENTAMIENTO DOS',
				codigoPostal: '01780',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('MEXICO TRES'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(flush: true,failOnError: true)
		new RsGralAsentamiento(nombreAsentamiento: 'MEXICO 3 ASENTAMIENTO TRES',
				codigoPostal: '01790',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('MEXICO TRES'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(flush: true,failOnError: true)

		/*
		 new RsGralDomicilio(calle: 'Las Palmas',
		 numeroInterior: '4',
		 numeroExterior: '67',
		 esFiscal: 'true',
		 comentarios : 'ENFRENTE DE UNA FARMACIA',
		 rsGralAsentamiento : RsGralAsentamiento.findByCodigoPostal('06860'),
		 regional : SimRegional.findByClaveRegional('REGION1'),
		 ).save(flush: true,failOnError: true)
		 new RsGralDomicilio(calle: 'Direccion administrador',
		 numeroInterior: '54',
		 numeroExterior: '90',
		 esFiscal: 'false',
		 comentarios : 'CRUZANDO DE UNA AVENIDA',
		 rsGralAsentamiento : RsGralAsentamiento.findByCodigoPostal('01790'),
		 sucursal : SimSucursal.get(1),
		 ).save(flush: true,failOnError: true)
		 new RsGralDomicilio(calle: 'Direccion administrador',
		 numeroInterior: '78',
		 numeroExterior: '905',
		 esFiscal: 'true',
		 comentarios : 'ATRAS CENTRO COMERCIAL',
		 rsGralAsentamiento : RsGralAsentamiento.findByCodigoPostal('01580'),
		 persona : RsPersona.get(1),
		 ).save(flush: true,failOnError: true)
		 */
		new RsGralDomicilio(calle: 'BATALLONES ROJOS 205',
				numeroInterior: '504',
				numeroExterior: 'EDIF 8',
				esFiscal: 'true',
				comentarios : 'UNIDAD ALBARRADA',
				rsGralAsentamiento : RsGralAsentamiento.findByCodigoPostal('06862'),
				persona : RsPersona.findByEmail('sistema.microfinanciera@gmail.com'),
				).save(flush: true,failOnError: true)
		new RsGralDomicilio(calle: 'PROGRESISTA',
				numeroInterior: '202',
				numeroExterior: 'EDIF 6',
				esFiscal: 'false',
				comentarios : 'UNIDAD VICENTE',
				rsGralAsentamiento : RsGralAsentamiento.findByCodigoPostal('01600'),
				persona : RsPersona.findByEmail('sistema.microfinanciera@gmail.com'),
				).save(flush: true,failOnError: true)


		new RsGralTelefono(telefono:  '55578796',
				descripcionTelefono : SimCatDescTelefono.findByClaveDescripcionTelefono('OFICINA'),
				persona : RsPersona.findByEmail('sistema.microfinanciera@gmail.com'),
				).save(flush: true,failOnError: true)

		new RsGralTelefono(telefono:  '55997876',
				descripcionTelefono : SimCatDescTelefono.findByClaveDescripcionTelefono('FAX'),
				persona : RsPersona.findByEmail('sistema.microfinanciera@gmail.com'),
				).save(flush: true,failOnError: true)

		new SimCatBanco(claveBanco: 'BANCOMER',
				nombreBanco: 'BANCOMER BBVA',
				).save(flush: true,failOnError: true)

		new SimCatBanco(claveBanco: 'BANAMEX',
				nombreBanco: 'BANAMEX MÉXICO',
				).save(flush: true,failOnError: true)

		new SimCatBanco(claveBanco: 'IXE',
				nombreBanco: 'BANCO IXE',
				).save(flush: true,failOnError: true)

		new SimCatBanco(claveBanco: 'SANTANDER',
				nombreBanco: 'SANTANDER MÉXICO',
				).save(flush: true,failOnError: true)

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
		personaAdmin.save(flush: true,failOnError: true)

        new EntRegion(claveRegion: 'CENTRO',
                nombreRegion: 'CENTRO MEXICO',
        ).save(flush: true,failOnError: true)

        new EntRegion(claveRegion: 'NORTE',
                nombreRegion: 'NORTE MEXICO',
        ).save(flush: true,failOnError: true)

        new EntSucursal(claveSucursal: 'EDOMEX',
                nombreSucursal: 'ESTADO DE MEXICO',
                regional: EntRegion.findByClaveRegion('CENTRO'),
        ).save(flush: true,failOnError: true)

        new EntSucursal(claveSucursal: 'ZACATECAS',
                nombreSucursal: 'ZACATECAS',
                regional: EntRegion.findByClaveRegion('CENTRO'),
        ).save(flush: true,failOnError: true)

        new EntOficina(claveOficina: 'IXT',
                nombreOficina: 'IXTAPALUCA',
                sucursal: EntSucursal.findByClaveSucursal('EDOMEX'),
        ).save(flush: true,failOnError: true)

        new EntOficina(claveOficina: 'TOL',
                nombreOficina: 'TOLUCA',
                sucursal: EntSucursal.findByClaveSucursal('EDOMEX'),
        ).save(flush: true,failOnError: true)

        new EntDelegacion(claveDelegacion: 'EDOMEX',
                nombreDelegacion: 'ESTADO MEXICO',
                sucursal: EntSucursal.findByClaveSucursal('EDOMEX'),
        ).save(flush: true,failOnError: true)

        new EntDelegacion(claveDelegacion: 'ZACATECAS',
                nombreDelegacion: 'ZACATECAS',
                sucursal: EntSucursal.findByClaveSucursal('EDOMEX'),
        ).save(flush: true,failOnError: true)

        new EntDependencia(claveDependencia: 'IMSS',
                nombreDependencia: 'INSTITUTO MEXICANO DEL SEGURO SOCIAL',
        ).save(flush: true,failOnError: true)

        new EntDependencia(claveDependencia: 'CFE',
                nombreDependencia: 'COMISION FEDERAL DE ELECTRICIDAD',
        ).save(flush: true,failOnError: true)

        //DA DE ALTA UNA PERSONA PARA ASIGNARLO A UN CLIENTE
        def robertoPerez = new RsPersona(
                apellidoPaterno: "PEREZ",
                primerNombre: "ROBERTO",
                email : "rperez@gmail.com",
                tiposPersona : [
                        SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')
                ],
        ).save(flush: true,failOnError: true)

        new RsCliente(persona: robertoPerez,
                dependencia: EntDependencia.findByClaveDependencia('IMSS'),
                numeroDeNomina: '6967896JK8',
        ).save(flush: true,failOnError: true)

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
        ).save(flush: true,failOnError: true)

        def clienteArturo = new RsCliente(persona: arturoSalazar,
                dependencia: EntDependencia.findByClaveDependencia('CFE'),
                numeroDeNomina: 'KJKSDFYUYUI',
        ).save(flush: true,failOnError: true)




        new SimCatPuesto(clavePuesto:  'DIRGEN',
                nombrePuesto: 'DIRECTOR GENERAL',
                descripcionPuesto: 'DIRECTOR GENERAL MICRO',
        ).save(flush: true,failOnError: true)

        new SimCatPuesto(clavePuesto:  'GERRIE',
                nombrePuesto: 'GERENTE DE RIESGOS',
                descripcionPuesto: 'GERENTE DE RIESGOS',
                dependeDe : SimCatPuesto.findByClavePuesto('DIRGEN'),
        ).save(flush: true,failOnError: true)

        new SimCatPuesto(clavePuesto:  'COORIE',
                nombrePuesto: 'COORDINADOR DE RIESGOS',
                descripcionPuesto: 'COORDINADOR DE RIESGOS',
                dependeDe : SimCatPuesto.findByClavePuesto('GERRIE'),
        ).save(flush: true,failOnError: true)

        new SimCatPuesto(clavePuesto:  'ASESORSUC',
                nombrePuesto: 'ASESOR DE SUCURSAL',
                descripcionPuesto: 'ASESOR DE SUCURSAL',
                dependeDe : SimCatPuesto.findByClavePuesto('DIRGEN'),
        ).save(flush: true,failOnError: true)

        new SimCatPuesto(clavePuesto:  'GERREG',
                nombrePuesto: 'GERENTE REGIONAL',
                descripcionPuesto: 'GERENTE DE REGIONAL',
                dependeDe : SimCatPuesto.findByClavePuesto('DIRGEN'),
        ).save(flush: true,failOnError: true)

        new SimCatPuesto(clavePuesto:  'COOREG',
                nombrePuesto: 'COORDINADOR REGIONAL',
                descripcionPuesto: 'COORDINADOR DE REGIONAL',
                dependeDe : SimCatPuesto.findByClavePuesto('DIRGEN'),
        ).save(flush: true,failOnError: true)

        new SimCatPuesto(clavePuesto:  'GERSUC',
                nombrePuesto: 'GERENTE SUCURSAL',
                descripcionPuesto: 'GERENTE DE SUCURSAL',
                dependeDe : SimCatPuesto.findByClavePuesto('DIRGEN'),
        ).save(flush: true,failOnError: true)

        new SimCatPuesto(clavePuesto:  'COOSUC',
                nombrePuesto: 'COORDINADOR SUCURSAL',
                descripcionPuesto: 'COORDINADOR DE SUCURSAL',
                dependeDe : SimCatPuesto.findByClavePuesto('DIRGEN'),
        ).save(flush: true,failOnError: true)

        new SimCatPuesto(clavePuesto:  'VENDE',
                nombrePuesto: 'VENDEDOR',
                descripcionPuesto: 'VENDEDOR MTN',
        ).save(flush: true,failOnError: true)

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
        ).save(flush: true,failOnError: true)

        //DA DE ALTA A UN EMPLEADO
        def empleado = new EmpEmpleado(
                claveEmpleado : "MOR78987",
                persona : personaAlex,
                tipoEmpleado : "INTERNO",
                puesto : SimCatPuesto.findByClavePuesto('VENDE'),
                fechaIngreso  : new Date('08/20/1999'),
                numeroNomina : "001",
                esVigente: 'true',
                oficina: EntOficina.findByClaveOficina('TOL'),
        ).save(flush: true,failOnError: true)

        //DA DE ALTA UNA PROMOCION
        def promocionUno = new ProPromocion(
                clavePromocion : "MOR78987",
                nombrePromocion : "PROMOCION ATREVETE A PEDIR",
                dependencia: EntDependencia.findByClaveDependencia('IMSS'),
                tasa:  5.45,
                plazo:  28,
                fechaInicioVigencia : new Date('04/30/2012'),
                fechaFinVigencia  : new Date('09/30/2012'),
                iva : 16,
        ).save(flush: true,failOnError: true)

        new SimCatFormaEntrega(claveFormaEntrega: 'VENBANCO',
                nombreFormaEntrega: 'ENTREGA EN VENTANILLA DE BANCO',
        ).save(flush: true,failOnError: true)

        new SimCatFormaEntrega(claveFormaEntrega: 'ELECTRONICA',
                nombreFormaEntrega: 'TRANSFERENCIA ELECTRONICA',
        ).save(flush: true,failOnError: true)

        new SimCatFormaEntrega(claveFormaEntrega: 'CAJA',
                nombreFormaEntrega: 'DIRECTAMENTE EN CAJA',
        ).save(flush: true,failOnError: true)

        new SimCatFormaEntrega(claveFormaEntrega: 'CHEQUE',
                nombreFormaEntrega: 'EN CHEQUE',
        ).save(flush: true,failOnError: true)
		
		new SimCatEtapaPrestamo(claveEtapaPrestamo:  'INIMC',
			nombreEtapaPrestamo: 'INICIO MESA DE CONTROL',
			descripcionEtapaPrestamo: 'INICIO MESA DE CONTROL EN MTN',
		).save(flush: true,failOnError: true)

		new SimCatEtapaPrestamo(claveEtapaPrestamo:  'CAPMC',
			nombreEtapaPrestamo: 'CAPTURADA MESA DE CONTROL',
			descripcionEtapaPrestamo: 'CAPTURADA MESA DE CONTROL EN MTN',
		).save(flush: true,failOnError: true)
		
		new SimCatEtapaPrestamo(claveEtapaPrestamo:  'DEV',
			nombreEtapaPrestamo: 'DEVOLUCION A MESA DE CONTROL',
			descripcionEtapaPrestamo: 'DEVOLUCION A MESA DE CONTROL EN MTN',
		).save(flush: true,failOnError: true)
		
		new SimCatEtapaPrestamo(claveEtapaPrestamo:  'PROC',
			nombreEtapaPrestamo: 'PROCESO',
			descripcionEtapaPrestamo: 'PROCESO',
		).save(flush: true,failOnError: true)
		
		new SimCatEtapaPrestamo(claveEtapaPrestamo:  'AUT',
			nombreEtapaPrestamo: 'AUTORIZADO POR CREDITO REAL',
			descripcionEtapaPrestamo: 'AUTORIZADO POR CREDITO REAL',
		).save(flush: true,failOnError: true)

		
		new SimCatEtapaPrestamo(claveEtapaPrestamo:  'COMP',
			nombreEtapaPrestamo: 'COMPRADO POR CREDITO REAL',
			descripcionEtapaPrestamo: 'COMPRADO POR CREDITO REAL',
		).save(flush: true,failOnError: true)


        new Prestamo(clavePrestamo: "KLP987",
                cliente : clienteArturo,
				correoSolicitante: "arturo.salazar@rapidsist.com",
                folioSolicitud : 34534,
                dependencia : EntDependencia.findByClaveDependencia('CFE'),
                promocion: promocionUno,
                sucursal: EntSucursal.findByClaveSucursal('EDOMEX'),
                delegacion: EntDelegacion.findByClaveDelegacion('ZACATECAS'),
                vendedor : empleado,
                fechaSolicitud: new Date('04/30/2012'),
                montoSolicitado: 6000,
                estatusSolicitud: SimCatEtapaPrestamo.findByClaveEtapaPrestamo('INIMC'),
                formaDeDispercion: SimCatFormaEntrega.findByClaveFormaEntrega('VENBANCO'),
                documentosCorrectos: false,
				aprobado: false,
				reenviarSolicitud: false,
        ).save(flush: true,failOnError: true)

		// Load the basic test data. Note that, since the data is loaded in a transaction, all the
		// business logic will fire, and all derived attributes will be automagically calculated.
		if (!Customer.count()) {
			Customer.withTransaction{tx ->
				def alpha = new Customer(name: "Alpha and Sons", balance: 0, creditLimit: 900, isPreferred: true).save(flush: true,failOnError: true)
				def bravo = new Customer(name: "Bravo Hardware", balance: 0, creditLimit: 5000, isPreferred: false).save(flush: true,failOnError: true)
				def charlie = new Customer(name: "Charlie's Construction", balance: 0, creditLimit: 1500, isPreferred: true).save(flush: true,failOnError: true)
				def delta = new Customer(name: "Delta Engineering", balance: 0, creditLimit: 0, isPreferred: false).save(flush: true,failOnError: true)
				
				def hammer = new Product(name: "Hammer", price: 10).save(flush: true,failOnError: true)
				def shovel = new Product(name: "Shovel", price: 25).save(flush: true,failOnError: true)
				def drill = new Product(name: "Drill", price: 315).save(flush: true,failOnError: true)
				
				def po1 = new PurchaseOrder(amountTotal: 0, paid: false, ready: true, notes: "This is a small order", customer: alpha).save(flush: true,failOnError: true)
				def po2 = new PurchaseOrder(amountTotal: 0, paid: true, ready: true, notes: "PRUEBA", customer: bravo).save(flush: true,failOnError: true)
				def po3 = new PurchaseOrder(amountTotal: 0, paid: false, ready: true, notes: "Please rush this order", customer: bravo).save(flush: true,failOnError: true)
				def po4 = new PurchaseOrder(amountTotal: 0, paid: false, ready: true, notes: "Deliver by overnight with signature required", customer: charlie).save(flush: true,failOnError: true)
				def po5 = new PurchaseOrder(amountTotal: 0, paid: false, ready: true, notes: "PRUEBA", customer: charlie).save(flush: true,failOnError: true)
				def po6 = new PurchaseOrder(amountTotal: 0, paid: false, ready: false, notes: "Pack with care - fragile merchandise", customer: alpha).save(flush: true,failOnError: true)
				
				new LineItem(qtyOrdered: 1, amount: 0, productPrice: 0, product: hammer, purchaseOrder: po1).save(flush: true,failOnError: true)
				new LineItem(qtyOrdered: 2, amount: 0, productPrice: 0, product: hammer, purchaseOrder: po2).save(flush: true,failOnError: true)
				new LineItem(qtyOrdered: 1, amount: 0, productPrice: 0, product: shovel, purchaseOrder: po2).save(flush: true,failOnError: true)
				new LineItem(qtyOrdered: 3, amount: 0, productPrice: 0, product: drill, purchaseOrder: po2).save(flush: true,failOnError: true)
				new LineItem(qtyOrdered: 1, amount: 0, productPrice: 0, product: hammer, purchaseOrder: po3).save(flush: true,failOnError: true)
				new LineItem(qtyOrdered: 2, amount: 0, productPrice: 0, product: shovel, purchaseOrder: po3).save(flush: true,failOnError: true)
				new LineItem(qtyOrdered: 1, amount: 0, productPrice: 0, product: hammer, purchaseOrder: po4).save(flush: true,failOnError: true)
				new LineItem(qtyOrdered: 3, amount: 0, productPrice: 0, product: shovel, purchaseOrder: po4).save(flush: true,failOnError: true)
				new LineItem(qtyOrdered: 1, amount: 0, productPrice: 0, product: hammer, purchaseOrder: po5).save(flush: true,failOnError: true)
				new LineItem(qtyOrdered: 5, amount: 0, productPrice: 0, product: shovel, purchaseOrder: po5).save(flush: true,failOnError: true)
				new LineItem(qtyOrdered: 2, amount: 0, productPrice: 0, product: shovel, purchaseOrder: po6).save(flush: true,failOnError: true)
				new LineItem(qtyOrdered: 1, amount: 0, productPrice: 0, product: shovel, purchaseOrder: po1).save(flush: true,failOnError: true)
			}
		}
		
		new Evento(evento:"inicia").save(flush: true,failOnError: true)
		new Evento(evento:"validacion").save(flush: true,failOnError: true)
		new Evento(evento:"autorizacion").save(flush: true,failOnError: true)
		new Evento(evento:"finalizacion").save(flush: true,failOnError: true)
		
		new Dependencia(nombre:"IMSS").save(flush: true,failOnError: true)
		new Dependencia(nombre:"IFE").save(flush: true,failOnError: true)
		new Dependencia(nombre:"SAT").save(flush: true,failOnError: true)
		new Dependencia(nombre:"SE").save(flush: true,failOnError: true)
		
		new DummyPersona(nombre: "Miguel",apellido: "Mendoza", calle: "Melchor Ocampo", numero: "Mz 10 Lt 4", codigoPostal: "54870").save(flush:true, failOnError:true)
	
    }

	def destroy = {
	}
}
