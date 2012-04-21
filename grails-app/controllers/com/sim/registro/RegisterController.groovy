package com.sim.registro

import groovy.text.SimpleTemplateEngine

import org.codehaus.groovy.grails.plugins.springsecurity.NullSaltSource
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils
import org.codehaus.groovy.grails.plugins.springsecurity.ui.RegistrationCode

class RegisterController extends grails.plugins.springsecurity.ui.RegisterController {
	
	def index = {
		def copy = [:] + (flash.chainedParams ?: [:])
		copy.remove 'controller'
		copy.remove 'action'
		[command: new RegisterCommand(copy)]
	}

	def register = { RegisterCommand command ->
		
		if (command.hasErrors()) {
			render view: 'index', model: [command: command]
			return
		}
		
		String salt = saltSource instanceof NullSaltSource ? null : command.username
		def user = lookupUserClass().newInstance(email: command.email, username: command.username,
				accountLocked: true, enabled: true)
		
		RegistrationCode registrationCode = springSecurityUiService.register(user, command.password, salt)
		if (registrationCode == null || registrationCode.hasErrors()) {
			log.info "NO SALVO USUARIO"
			// null means problem creating the user
			flash.error = message(code: 'spring.security.ui.register.miscError')
			flash.chainedParams = params
			redirect action: 'index'
			return
		}
		//OBTIENE LA CLASE DE DOMINIO TIPOS DE PERSONA
		def tipoPersona = command.grailsApplication.getDomainClass("com.sim.catalogo.SimCatTipoPersona").clazz
		//OBTIENE LA CLASE DE DOMINIO DE PERSONA
		Class<?> clasePersona = grailsApplication.getDomainClass("com.rs.gral.RsPersona").clazz
		
		def rsPersona = clasePersona.newInstance(email: command.email, apellidoPaterno: command.apellidoPaterno, apellidoMaterno: command.apellidoMaterno,
			 primerNombre: command.primerNombre, segundoNombre: command.segundoNombre, usuario: user, 
			 tiposPersona : [tipoPersona.findByClaveTipoPersona('USUARIO')])
		
		if (!rsPersona.save()) {
			log.info "NO SALVO PERSONA"
		}else{
			log.info "SALVO PERSONA"
		}

		String url = generateLink('verifyRegistration', [t: registrationCode.token])

		def conf = SpringSecurityUtils.securityConfig
		def body = conf.ui.register.emailBody
		if (body.contains('$')) {
			body = evaluate(body, [user: user, url: url])
		}
		mailService.sendMail {
			to command.email
			from conf.ui.register.emailFrom
			subject conf.ui.register.emailSubject
			html body.toString()
		}

		render view: 'index', model: [emailSent: true]
	}
}

class RegisterCommand {

	String username
	String email
	String password
	String password2
	
	String apellidoPaterno
	String apellidoMaterno
	String primerNombre
	String segundoNombre

	def grailsApplication

	static constraints = {
		username blank: false, nullable: false, validator: { value, command ->
			if (value) {
				def User = command.grailsApplication.getDomainClass(
						SpringSecurityUtils.securityConfig.userLookup.userDomainClassName).clazz
				if (User.findByUsername(value)) {
					return 'registerCommand.username.unique'
				}
			}
		}
		email blank: false, nullable: false, email: true, validator: { value, command ->
			if (value) {
				def Persona = command.grailsApplication.getDomainClass("com.rs.gral.RsPersona").clazz
				if (Persona.findByEmail(value)) {
					return 'registerCommand.username.email' 
				}
			}
		}
		
		password blank: false, nullable: false, validator: RegisterController.passwordValidator
		password2 validator: RegisterController.password2Validator
		
		apellidoPaterno size:5..25, blank: false, unique: false
		apellidoMaterno nullable: true, size:0..25
		primerNombre size:5..25, blank: false, unique: false
		segundoNombre nullable: true, size:0..25
	}
}

class ResetPasswordCommand {
	String username
	String password
	String password2

	static constraints = {
		username nullable: false
		password blank: false, nullable: false, validator: RegisterController.passwordValidator
		password2 validator: RegisterController.password2Validator
	}
}
