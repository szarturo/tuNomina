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

		new SimCatBanco(claveBanco: 'BANCOMER',
				nombreBanco: 'BANCOMER BBVA',
				).save(failOnError: true)

		new SimCatBanco(claveBanco: 'BANAMEX',
				nombreBanco: 'BANAMEX MÉXICO',
				).save(failOnError: true)

	}

	def destroy = {
	}
}
