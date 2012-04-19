
<%@ page import="com.rs.gral.RsGralDomicilio" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'rsGralDomicilio.label', default: 'RsGralDomicilio')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-rsGralDomicilio" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-rsGralDomicilio" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list rsGralDomicilio">
			
				<g:if test="${rsGralDomicilioInstance?.calle}">
				<li class="fieldcontain">
					<span id="calle-label" class="property-label"><g:message code="rsGralDomicilio.calle.label" default="Calle" /></span>
					
						<span class="property-value" aria-labelledby="calle-label"><g:fieldValue bean="${rsGralDomicilioInstance}" field="calle"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${rsGralDomicilioInstance?.numeroInterior}">
				<li class="fieldcontain">
					<span id="numeroInterior-label" class="property-label"><g:message code="rsGralDomicilio.numeroInterior.label" default="Numero Interior" /></span>
					
						<span class="property-value" aria-labelledby="numeroInterior-label"><g:fieldValue bean="${rsGralDomicilioInstance}" field="numeroInterior"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${rsGralDomicilioInstance?.numeroExterior}">
				<li class="fieldcontain">
					<span id="numeroExterior-label" class="property-label"><g:message code="rsGralDomicilio.numeroExterior.label" default="Numero Exterior" /></span>
					
						<span class="property-value" aria-labelledby="numeroExterior-label"><g:fieldValue bean="${rsGralDomicilioInstance}" field="numeroExterior"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${rsGralDomicilioInstance?.rsGralAsentamiento}">
				<li class="fieldcontain">
					<span id="rsGralAsentamiento-label" class="property-label"><g:message code="rsGralDomicilio.rsGralAsentamiento.label" default="Rs Gral Asentamiento" /></span>
					
						<span class="property-value" aria-labelledby="rsGralAsentamiento-label"><g:link controller="rsGralAsentamiento" action="show" id="${rsGralDomicilioInstance?.rsGralAsentamiento?.id}">${rsGralDomicilioInstance?.rsGralAsentamiento?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${rsGralDomicilioInstance?.esFiscal}">
				<li class="fieldcontain">
					<span id="esFiscal-label" class="property-label"><g:message code="rsGralDomicilio.esFiscal.label" default="Es Fiscal" /></span>
					
						<span class="property-value" aria-labelledby="esFiscal-label"><g:formatBoolean boolean="${rsGralDomicilioInstance?.esFiscal}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${rsGralDomicilioInstance?.comentarios}">
				<li class="fieldcontain">
					<span id="comentarios-label" class="property-label"><g:message code="rsGralDomicilio.comentarios.label" default="Comentarios" /></span>
					
						<span class="property-value" aria-labelledby="comentarios-label"><g:fieldValue bean="${rsGralDomicilioInstance}" field="comentarios"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${rsGralDomicilioInstance?.persona}">
				<li class="fieldcontain">
					<span id="persona-label" class="property-label"><g:message code="rsGralDomicilio.persona.label" default="Persona" /></span>
					
						<span class="property-value" aria-labelledby="persona-label"><g:link controller="rsPersona" action="show" id="${rsGralDomicilioInstance?.persona?.id}">${rsGralDomicilioInstance?.persona?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${rsGralDomicilioInstance?.id}" />
					<g:link class="edit" action="edit" id="${rsGralDomicilioInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
