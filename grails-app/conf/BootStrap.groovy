import com.sim.catalogo.*
import com.sim.usuario.*

class BootStrap {

	def init = { servletContext ->


		def adminRole = new Rol(authority: 'ROLE_ADMIN').save(flush: true)
		def userRole = new Rol(authority: 'ROLE_USER').save(flush: true)

		def usuarioAdmin = new Usuario(username: 'admin', enabled: true, password: '1234')
		usuarioAdmin.save(flush: true)

		UsuarioRol.create usuarioAdmin, adminRole, true

		assert Usuario.count() == 1
		assert Rol.count() == 2
		assert UsuarioRol.count() == 1

		//IMPLEMENTACION DE SEGURIDAD A NIVEL Dynamic request maps
		//new Requestmap(url: '/user/**', configAttribute: 'ROLE_ADMIN').save(failOnError: true)
		//new Requestmap(url: '/rsConfGpoEmpresa/**', configAttribute: 'ROLE_USER').save(failOnError: true)
		new Requestmap(url: '/simCatBanco/create', configAttribute: 'ROLE_ADMIN').save(failOnError: true)

		new SimCatTipoDocumento(claveTipoDocumento:  'IDENTIFICACION',
				nombreTipoDocumento: 'IDENTIFICACION OFICIAL',
				).save(failOnError: true)

		new SimCatTipoDocumento(claveTipoDocumento:  'DOMICILIO',
				nombreTipoDocumento: 'COMPROBANTE DE DOMICILIO',
				).save(failOnError: true)

		new SimCatTipoDocumento(claveTipoDocumento:  'LEGAL',
				nombreTipoDocumento: 'LEGAL',
				).save(failOnError: true)

		new SimCatDocumento(claveDocumento:  'CFE',
				nombreDocumento: 'COMPROBANTE DE LUZ',
				descripcion: 'COMPROBANTE DE LUZ',
				tipoDocumento : SimCatTipoDocumento.findByClaveTipoDocumento('DOMICILIO'),
				esReporte : 'false',
				).save(failOnError: true)

		new SimCatDocumento(claveDocumento:  'ACTA_NACIMIENTO',
				nombreDocumento: 'ACTA DE NACIMIENTO',
				descripcion: 'ACTA DE NACIMIENTO',
				tipoDocumento : SimCatTipoDocumento.findByClaveTipoDocumento('LEGAL'),
				//simCatReporte : SimCatReporte.findByClaveReporte('CLAVE_1'),
				esReporte : 'true',
				).save(failOnError: true)

		new SimCatDocumento(claveDocumento:  'IFE',
				nombreDocumento: 'CREDENCIAL IFE',
				descripcion: 'CREDENCIAL IFE',
				tipoDocumento : SimCatTipoDocumento.findByClaveTipoDocumento('IDENTIFICACION'),
				esReporte : 'false',
				).save(failOnError: true)

		new SimCatDocumento(claveDocumento:  'LICENCIA_CONDUCIR',
				nombreDocumento: 'LICENCIA DE CONDUCIR',
				descripcion: 'LICENCIA DE CONDUCIR',
				tipoDocumento : SimCatTipoDocumento.findByClaveTipoDocumento('IDENTIFICACION'),
				esReporte : 'false',
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

	}

	def destroy = {
	}
}
