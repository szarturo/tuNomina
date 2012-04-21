import grails.plugins.springsecurity.SecurityConfigType

// locations to search for config files that get merged into the main config
// config files can either be Java properties files or ConfigSlurper scripts

// grails.config.locations = [ "classpath:${appName}-config.properties",
//                             "classpath:${appName}-config.groovy",
//                             "file:${userHome}/.grails/${appName}-config.properties",
//                             "file:${userHome}/.grails/${appName}-config.groovy"]

// if (System.properties["${appName}.config.location"]) {
//    grails.config.locations << "file:" + System.properties["${appName}.config.location"]
// }


grails.project.groupId = appName // change this to alter the default package name and Maven publishing destination
grails.mime.file.extensions = true // enables the parsing of file extensions from URLs into the request format
grails.mime.use.accept.header = false
grails.mime.types = [ html: ['text/html','application/xhtml+xml'],
                      xml: ['text/xml', 'application/xml'],
                      text: 'text/plain',
                      js: 'text/javascript',
                      rss: 'application/rss+xml',
                      atom: 'application/atom+xml',
                      css: 'text/css',
                      csv: 'text/csv',
                      all: '*/*',
                      json: ['application/json','text/json'],
                      form: 'application/x-www-form-urlencoded',
                      multipartForm: 'multipart/form-data'
                    ]

// URL Mapping Cache Max Size, defaults to 5000
//grails.urlmapping.cache.maxsize = 1000

// What URL patterns should be processed by the resources plugin
grails.resources.adhoc.patterns = ['/images/*', '/css/*', '/js/*', '/plugins/*']


// The default codec used to encode data with ${}
grails.views.default.codec = "none" // none, html, base64
grails.views.gsp.encoding = "UTF-8"
grails.converters.encoding = "UTF-8"
// enable Sitemesh preprocessing of GSP pages
grails.views.gsp.sitemesh.preprocess = true
// scaffolding templates configuration
grails.scaffolding.templates.domainSuffix = 'Instance'

// Set to false to use the new Grails 1.2 JSONBuilder in the render method
grails.json.legacy.builder = false
// enabled native2ascii conversion of i18n properties files
grails.enable.native2ascii = true
// packages to include in Spring bean scanning
grails.spring.bean.packages = []
// whether to disable processing of multi part requests
grails.web.disable.multipart=false

// request parameters to mask when logging exceptions
grails.exceptionresolver.params.exclude = ['password']

// enable query caching by default
grails.hibernate.cache.queries = true

// set per-environment serverURL stem for creating absolute links
environments {
    development {
        grails.logging.jul.usebridge = true
    }
    production {
        grails.logging.jul.usebridge = false
        // TODO: grails.serverURL = "http://www.changeme.com"
    }
}

// log4j configuration
log4j = {
	// Example of changing the log pattern for the default console
	// appender:
	//
	//appenders {
	//    console name:'stdout', layout:pattern(conversionPattern: '%c{2} %m%n')
	//}
	
	//OBTENER LOGS INFO EN UN ARCHIVO Y EN LA CONSOLA
	
	appenders {
		//console name:'stdout', layout:pattern(conversionPattern: '%c{2} %m%n')
		file name:'file', file:'/home/miguel/logs/sim/logInfo.log'
	}
	
	root {
		info 'file','stdout'
	}

    error  'org.codehaus.groovy.grails.web.servlet',  //  controllers
           'org.codehaus.groovy.grails.web.pages', //  GSP
           'org.codehaus.groovy.grails.web.sitemesh', //  layouts
           'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
           'org.codehaus.groovy.grails.web.mapping', // URL mapping
           'org.codehaus.groovy.grails.commons', // core / classloading
           'org.codehaus.groovy.grails.plugins', // plugins
           'org.codehaus.groovy.grails.orm.hibernate', // hibernate integration
           'org.springframework',
           'org.hibernate',
           'net.sf.ehcache.hibernate'

	// unexpected validation errors during registration
	warn 'grails.app.services.grails.plugins.springsecurity.ui.SpringSecurityUiService'
	
}

// Added by the Spring Security Core plugin:
grails.plugins.springsecurity.userLookup.userDomainClassName = 'com.sim.usuario.Usuario'
grails.plugins.springsecurity.userLookup.authorityJoinClassName = 'com.sim.usuario.UsuarioRol'
grails.plugins.springsecurity.authority.className = 'com.sim.usuario.Rol'

//IMPLEMENTACION DE SEGURIDAD A NIVEL Dynamic request maps
grails.plugins.springsecurity.requestMap.className = 'com.sim.usuario.Requestmap'
grails.plugins.springsecurity.securityConfigType = SecurityConfigType.Requestmap

//URL DONDE SE DIRECCIONA AL USUARIO DESPUES DE HABERSE REGISTRADO AL SISTEMA
//grails.plugins.springsecurity.ui.register.postRegisterUrl = '/welcome'

//ATRIBUTOS DEL CORREO PARA REGISTRO DE USUARIO
grails.plugins.springsecurity.ui.register.emailBody = '''\
Estimado(a) $user.username,<br/>
<br/>
Usted (o alguien mas pretendiendo ser usted) creo una cuenta con este correo.<br/>
<br/>
Si usted realizó la petición, presione <a href="$url">aquí</a> para finalizar el registro.
'''

grails.plugins.springsecurity.ui.register.emailFrom = 'do.not.reply@localhost'
grails.plugins.springsecurity.ui.register.emailSubject = 'Registro usuario SIM'

//ASIGNACION DE ROLES
grails.plugins.springsecurity.ui.register.defaultRoleNames = [] // no roles
//or
//grails.plugins.springsecurity.ui.register.defaultRoleNames = ['ROLE_CUSTOMER']


//RESETEAR PASSWORD
//post-reset destination url
//grails.plugins.springsecurity.ui.register.postResetUrl = '/reset'

//ATRIBUTOS DEL CORREO PARA RECUPERAR EL PASSWORD
grails.plugins.springsecurity.ui.forgotPassword.emailBody = '''\
Estimado(a) $user.username,<br/>
<br/>
Usted (o alguien mas pretendiendo ser usted) solicitó actualizar su contraseña<br/>
<br/>
Si usted no realizó esta solicitud haga caso omiso de este correo, ningún cambio será aplicado.<br/>
<br/>
Si usted realizó esta petición, presione <a href="$url">aquí</a> para establecer su contraseña.
'''
grails.plugins.springsecurity.ui.forgotPassword.emailFrom = 'do.not.reply@localhost'
grails.plugins.springsecurity.ui.forgotPassword.emailSubject = 'Restablecer contraseña SIM'


//CONFIGURACION DEL PLUGIN mail
grails {
	mail {
	  host = "smtp.gmail.com"
	  port = 465
	  username = "sistema.microfinanciera@gmail.com"
	  password = "rapidsist"
	  props = ["mail.smtp.auth":"true",
			   "mail.smtp.socketFactory.port":"465",
			   "mail.smtp.socketFactory.class":"javax.net.ssl.SSLSocketFactory",
			   "mail.smtp.socketFactory.fallback":"false"]
 
 } }

//This option defaults to true to avoid a breaking change, so if you have a newer domain class that handles encryption 
//just disable this plugin's encryption:
grails.plugins.springsecurity.ui.encodePassword = false