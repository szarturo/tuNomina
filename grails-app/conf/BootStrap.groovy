import com.sim.catalogo.*
import com.sim.usuario.*
import com.rs.gral.*
import com.sim.entidad.*
import com.sim.cliente.*
import com.sim.credito.*
import com.sim.empresa.*
import com.sim.producto.*

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
		
		// CLIENTE 1
		
				  def javierHernandez = new RsPersona(
							email : "javierhernandez@gmail.com",
							apellidoPaterno: "HERNANDEZb",
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
						dependencia: EntDependencia.findByClaveDependencia('CFE'),
						numeroDeNomina: 'JCHSDFYUYUI',
					).save(failOnError: true)
			
			
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
				).save(failOnError: true)
		
				def clienteCarlos = new RsCliente(persona: carlossalcido,
						dependencia: EntDependencia.findByClaveDependencia('CFE'),
						numeroDeNomina: 'CSALSDFYUYUI',
				).save(failOnError: true)
					
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
						).save(failOnError: true)
				
						def clienteFrancisco = new RsCliente(persona: franciscorodriguez,
								dependencia: EntDependencia.findByClaveDependencia('CFE'),
								numeroDeNomina: 'MAZASDFYUYUI',
						).save(failOnError: true)
					
							
							
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
					).save(failOnError: true)
			
					def clienteGuillermo = new RsCliente(persona: guillermoochoa,
							dependencia: EntDependencia.findByClaveDependencia('CFE'),
							numeroDeNomina: 'PACOSDFYUYUI',
					).save(failOnError: true)
			
							
							
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
				).save(failOnError: true)
		
				def clienteJesus = new RsCliente(persona: jesuscorona,
						dependencia: EntDependencia.findByClaveDependencia('CFE'),
						numeroDeNomina: 'CHUYSDFYUYUI',
				).save(failOnError: true)
					
							
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
					).save(failOnError: true)
			
					def clienteGerardo = new RsCliente(persona: gerardotorrado,
							dependencia: EntDependencia.findByClaveDependencia('CFE'),
							numeroDeNomina: 'TORRSDFYUYUI',
					).save(failOnError: true)
							
									
							
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
				).save(failOnError: true)
		
				def clienteOribe = new RsCliente(persona: oribeperalta,
						dependencia: EntDependencia.findByClaveDependencia('CFE'),
						numeroDeNomina: 'ORIBSDFYUYUI',
				).save(failOnError: true)
							
									
									
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
				).save(failOnError: true)
		
				def clienteBenjamin = new RsCliente(persona: benjamingalindo,
						dependencia: EntDependencia.findByClaveDependencia('CFE'),
						numeroDeNomina: 'BENJSDFYUYUI',
				).save(failOnError: true)
							
									
									
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
				).save(failOnError: true)
		
				def clienteAlfredo = new RsCliente(persona: alfredotena,
						dependencia: EntDependencia.findByClaveDependencia('IMSS'),
						numeroDeNomina: 'TENASDFYUYUI',
				).save(failOnError: true)
							
									
							
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
				).save(failOnError: true)
		
				def clienteLuis = new RsCliente(persona: luisgarcia,
						dependencia: EntDependencia.findByClaveDependencia('CFE'),
						numeroDeNomina: 'LUISSDFYUYUI',
				).save(failOnError: true)
							
/*									
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
				).save(failOnError: true)
		
				def clienteJoaquin = new RsCliente(persona: joaquinlopez,
						dependencia: EntDependencia.findByClaveDependencia('IMSS'),
						numeroDeNomina: 'JOAQSDFYUYUI',
				).save(failOnError: true)
									
											
											
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
				).save(failOnError: true)
		
				def clienteJaviera = new RsCliente(persona: javieralatorre,
						dependencia: EntDependencia.findByClaveDependencia('IMSS'),
						numeroDeNomina: 'JAVISDFYUYUI',
				).save(failOnError: true)
									
											
											
											
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
				).save(failOnError: true)
		
				def clienteAlejandro = new RsCliente(persona: alejandrovillalvazo,
						dependencia: EntDependencia.findByClaveDependencia('IMSS'),
						numeroDeNomina: 'ALEXSDFYUYUI',
				).save(failOnError: true)
									
											
									
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
				).save(failOnError: true)
		
				def clienteAdela = new RsCliente(persona: adelamicha,
						dependencia: EntDependencia.findByClaveDependencia('IMSS'),
						numeroDeNomina: 'ADELSDFYUYUI',
				).save(failOnError: true)
									
											
									
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
				).save(failOnError: true)
		
				def clienteEduardo = new RsCliente(persona: eduardosalazar,
						dependencia: EntDependencia.findByClaveDependencia('IMSS'),
						numeroDeNomina: 'ADELSDFYUYUI',
				).save(failOnError: true)
									
											
									
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
				).save(failOnError: true)
		
				def clienteCarmen = new RsCliente(persona: carmenaristegui,
						dependencia: EntDependencia.findByClaveDependencia('CFE'),
						numeroDeNomina: 'CARMSDFYUYUI',
				).save(failOnError: true)
									
										
											
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
				).save(failOnError: true)
		
				def clienteAraceli = new RsCliente(persona: araceliPaz,
						dependencia: EntDependencia.findByClaveDependencia('IMSS'),
						numeroDeNomina: 'ARAPSDFYUYUI',
				).save(failOnError: true)
									
									
											
											
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
				).save(failOnError: true)
		
				def clienteLydiac = new RsCliente(persona: lidyaCacho,
						dependencia: EntDependencia.findByClaveDependencia('IMSS'),
						numeroDeNomina: 'LIDYSDFYUYUI',
				).save(failOnError: true)
									
											
									
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
				).save(failOnError: true)
		
				def clienteHannia = new RsCliente(persona: hanniaNovel,
						dependencia: EntDependencia.findByClaveDependencia('IMSS'),
						numeroDeNomina: 'HANNSDFYUYUI',
				).save(failOnError: true)
										
											
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
				).save(failOnError: true)
		
				def clienteAnam = new RsCliente(persona: anamariaNovelli,
						dependencia: EntDependencia.findByClaveDependencia('CFE'),
						numeroDeNomina: 'ANAMSDFYUYUI',
				).save(failOnError: true)
									
							
							
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
				).save(failOnError: true)
		
				def clienteAnag = new RsCliente(persona: anaGuevara,
						dependencia: EntDependencia.findByClaveDependencia('CFE'),
						numeroDeNomina: 'ANAGSDFYUYUI',
				).save(failOnError: true)
						
						
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
				).save(failOnError: true)
		
				def clienteEsteb = new RsCliente(persona: estebanArce,
						dependencia: EntDependencia.findByClaveDependencia('CFE'),
						numeroDeNomina: 'ESTBSDFYUYUI',
				).save(failOnError: true)
							
							
										
					
									
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
				).save(failOnError: true)
		
				def clienteMariano = new RsCliente(persona: marianoOsorio,
						dependencia: EntDependencia.findByClaveDependencia('CFE'),
						numeroDeNomina: 'MARISDFYUYUI',
				).save(failOnError: true)
		
				
				
				
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
				).save(failOnError: true)
		
				def clienteJorgez = new RsCliente(persona: jorgeZarza,
						dependencia: EntDependencia.findByClaveDependencia('CFE'),
						numeroDeNomina: 'jorgSDFYUYUI',
				).save(failOnError: true)
						
						
						
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
					).save(failOnError: true)
			
					def clienteEugenio = new RsCliente(persona:eugenioderbez,
							dependencia: EntDependencia.findByClaveDependencia('CFE'),
							numeroDeNomina: 'EUGENIOHUYTG',
					).save(failOnError: true)
								
								
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
					).save(failOnError: true)
			
					def clienteOmar = new RsCliente(persona: omarchaparro,
							dependencia: EntDependencia.findByClaveDependencia('CFE'),
							numeroDeNomina: 'OMARDFYUYUI',
					).save(failOnError: true)
										
										
									
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
				).save(failOnError: true)
		
				def clienteJavierL = new RsCliente(persona: javierlopez,
						dependencia: EntDependencia.findByClaveDependencia('IMSS'),
						numeroDeNomina: 'CHABE5FYUYUI',
				).save(failOnError: true)
												
												
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
				).save(failOnError: true)
		
				def clienteAdriana = new RsCliente(persona: adrianarodriguez,
						dependencia: EntDependencia.findByClaveDependencia('CFE'),
						numeroDeNomina: 'ADISDFYUYUI',
				).save(failOnError: true)
														
														
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
				).save(failOnError: true)
		
				def clienteCarlosl = new RsCliente(persona: carlosperez,
						dependencia: EntDependencia.findByClaveDependencia('CFE'),
						numeroDeNomina: 'LEE41235',
				).save(failOnError: true)
															
																
																
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
				).save(failOnError: true)
		
				def clientePaulina = new RsCliente(persona: paulinarubio,
						dependencia: EntDependencia.findByClaveDependencia('CFE'),
						numeroDeNomina: 'PAU678990',
				).save(failOnError: true)
																		
																		
																		
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
				).save(failOnError: true)
		
				def clienteRomeo = new RsCliente(persona: romeosantos,
						dependencia: EntDependencia.findByClaveDependencia('CFE'),
						numeroDeNomina: 'ROMEO342',
				).save(failOnError: true)
																				
																				
																				
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
				).save(failOnError: true)
		
				def clienteHenri = new RsCliente(persona: henrisanchez,
						dependencia: EntDependencia.findByClaveDependencia('CFE'),
						numeroDeNomina: 'HNERI462',
				).save(failOnError: true)
																						
																						
																						
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
				).save(failOnError: true)
		
				def clienteLuisR = new RsCliente(persona: luissolis,
						dependencia: EntDependencia.findByClaveDependencia('CFE'),
						numeroDeNomina: 'LUI456',
				).save(failOnError: true)
																								
																								
																								
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
				).save(failOnError: true)
		
				def clienteElsa = new RsCliente(persona: elsaruiz,
						dependencia: EntDependencia.findByClaveDependencia('CFE'),
						numeroDeNomina: 'ELSA543',
				).save(failOnError: true)
																										
																										
																										
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
					).save(failOnError: true)
		
					def clienteGuillermoo = new RsCliente(persona: guillermoochoal,
							dependencia: EntDependencia.findByClaveDependencia('CFE'),
							numeroDeNomina: 'PACO456',
					).save(failOnError: true)
																										
																									
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
				).save(failOnError: true)
		
				def clienteRafam = new RsCliente(persona: rafaelMarquez,
						dependencia: EntDependencia.findByClaveDependencia('CFE'),
						numeroDeNomina: 'RAFA543',
				).save(failOnError: true)
																											
																											
																											
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
				).save(failOnError: true)
		
				def clienteGerardot = new RsCliente(persona: gerardoTorrado,
						dependencia: EntDependencia.findByClaveDependencia('CFE'),
						numeroDeNomina: 'GERA456',
				).save(failOnError: true)
																											
																										
																										
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
				).save(failOnError: true)
		
				def clienteJuang = new RsCliente(persona: juanDiaz,
						dependencia: EntDependencia.findByClaveDependencia('CFE'),
						numeroDeNomina: 'JUAN456',
				).save(failOnError: true)
*/
    }

	def destroy = {
	}
}
