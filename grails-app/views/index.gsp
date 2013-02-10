<!doctype html>

<%@ page import="org.grails.activiti.ActivitiUtils"%>
<%@ page import="org.codehaus.groovy.grails.commons.ConfigurationHolder"%>
<%@ page import="org.grails.activiti.ActivitiConstants"%>

<g:set var="sessionUsernameKey"
	value="${ConfigurationHolder.config.activiti.sessionUsernameKey?:ActivitiConstants.DEFAULT_SESSION_USERNAME_KEY}" />

<html>
<head>
<meta name="layout" content="main" />
<title>Bienvenido al SIM</title>
<style type="text/css" media="screen">
#status {
	background-color: #eee;
	border: .2em solid #fff;
	margin: 2em 2em 1em;
	padding: 1em;
	width: 12em;
	float: left;
	-moz-box-shadow: 0px 0px 1.25em #ccc;
	-webkit-box-shadow: 0px 0px 1.25em #ccc;
	box-shadow: 0px 0px 1.25em #ccc;
	-moz-border-radius: 0.6em;
	-webkit-border-radius: 0.6em;
	border-radius: 0.6em;
}

.ie6 #status {
	display: inline;
	/* float double margin fix http://www.positioniseverything.net/explorer/doubled-margin.html */
}

#status ul {
	font-size: 0.9em;
	list-style-type: none;
	margin-bottom: 0.6em;
	padding: 0;
}

#status li {
	line-height: 1.3;
}

#status h1 {
	text-transform: uppercase;
	font-size: 1.1em;
	margin: 0 0 0.3em;
}

#page-body {
	margin: 2em 1em 1.25em 18em;
}

h2 {
	margin-top: 1em;
	margin-bottom: 0.3em;
	font-size: 1em;
}

p {
	line-height: 1.5;
	margin: 0.25em 0;
}

#controller-list ul {
	list-style-position: inside;
}

#controller-list li {
	line-height: 1.3;
	list-style-position: inside;
	margin: 0.25em 0;
}

@media screen and (max-width: 480px) {
	#status {
		display: none;
	}
	#page-body {
		margin: 0 1em 1em;
	}
	#page-body h1 {
		margin-top: 0;
	}
}
</style>
</head>
<body>


	<table>
		<tr>
			<td><a href="#page-body" class="skip"><g:message
						code="default.link.skip.label" default="Skip to content&hellip;" /></a>
				<div id="status" role="complementary">
					<h1>Múltiplica tu Nómina</h1>
					<li>Multiplica tu Nómina es una empresa 100% mexicana fundada en el año 2009, 
					    que nace para atender a un creciente número de empleados de empresas 
					    públicas y privadas, que requieren de préstamos en efectivo.
					</li>	
						
					<h1>Información Técnica</h1>			
					<ul>
						<li>App version: <g:meta name="app.version" /></li>
						<li>Grails version: <g:meta name="app.grails.version" /></li>
						<li>Groovy version: ${GroovySystem.getVersion()}</li>
						<li>JVM version: ${System.getProperty('java.version')}</li>
						<li>Reloading active: ${grails.util.Environment.reloadingAgentEnabled}</li>
						<li>Controllers: ${grailsApplication.controllerClasses.size()}</li>
						<li>Domains: ${grailsApplication.domainClasses.size()}</li>
						<li>Services: ${grailsApplication.serviceClasses.size()}</li>
						<li>Tag Libraries: ${grailsApplication.tagLibClasses.size()}</li>
					</ul>
					
					<h1>Plugins instalados</h1>
					<ul>
						<g:each var="plugin"
							in="${applicationContext.getBean('pluginManager').allPlugins}">
							<li>
								${plugin.name} - ${plugin.version}
							</li>
						</g:each>
					</ul>
				</div>
				<div id="page-body" role="main">
				
					<img src="${resource(dir: 'images', file: 'mas_lana.png')}" alt="Grails" />
					<h1>Sistema Integral de Microfinanciamiento</h1>
					<p>Nuestra empresa tiene como objetivo dar soluciones innovadoras e integrales 
					   para el otorgamiento de préstamos en efectivo vía descuento nómina, garantizando 
					   tasas competitivas, servicio de alta calidad, atención personalizada, seriedad y 
					   respuestas inmediatas a las necesidades del empleado.
					   La diferencia y característica única de nuestro servicio es la rapidez, 
                       y sencillez en el trámite, aunado al servicio profesional de nuestros asesores.</p>

					<div id="controller-list" role="navigation">
						<h2>Controladoras disponibles</h2>
						<ul>
							<g:each var="c"
								in="${grailsApplication.controllerClasses.sort { it.fullName } }">
								<li class="controller"><g:link
										controller="${c.logicalPropertyName}">
										${c.fullName}
									</g:link></li>
							</g:each>
						</ul>
					</div>
					<sec:ifLoggedIn>
						<div id="controller-list" role="navigation">
							<h2>Controladoras Procesos</h2>
							<ul>
								<g:each var="c"
									in="${grailsApplication.controllerClasses.sort { it.fullName } }">
									<g:if test="${c.hasProperty('activiti') && c.clazz.activiti}">
										<li class="controller">
											<g:link
												controller="${c.logicalPropertyName}">
												${c.fullName}
											</g:link> 
											<g:if test="${!c.logicalPropertyName.equals('task')}">
			                       	 			[<g:link
													controller="${c.logicalPropertyName}" action="start">Start</g:link>]
			                       	 		</g:if>
			                       	 	</li>
									</g:if>
								</g:each>
							</ul>
						</div>
					</sec:ifLoggedIn>
					
				</div></td>
		</tr>
	</table>

	<g:set var="pluginManager"
		value="${applicationContext.getBean('pluginManager')}"></g:set>

</body>
</html>
