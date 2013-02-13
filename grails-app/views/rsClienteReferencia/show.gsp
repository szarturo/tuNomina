
<%@ page import="com.sim.cliente.RsClienteReferencia" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'rsClienteReferencia.label', default: 'RsClienteReferencia')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-rsClienteReferencia" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-rsClienteReferencia" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list rsClienteReferencia">
			
				<g:if test="${rsClienteReferenciaInstance?.cliente}">
				<li class="fieldcontain">
					<span id="cliente-label" class="property-label"><g:message code="rsClienteReferencia.cliente.label" default="Cliente" /></span>
					
						<span class="property-value" aria-labelledby="cliente-label"><g:link controller="rsCliente" action="show" id="${rsClienteReferenciaInstance?.cliente?.id}">${rsClienteReferenciaInstance?.cliente?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${rsClienteReferenciaInstance?.nombreCompleto}">
				<li class="fieldcontain">
					<span id="nombreCompleto-label" class="property-label"><g:message code="rsClienteReferencia.nombreCompleto.label" default="Nombre Completo" /></span>
					
						<span class="property-value" aria-labelledby="nombreCompleto-label"><g:fieldValue bean="${rsClienteReferenciaInstance}" field="nombreCompleto"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${rsClienteReferenciaInstance?.telefono}">
				<li class="fieldcontain">
					<span id="telefono-label" class="property-label"><g:message code="rsClienteReferencia.telefono.label" default="Telefono" /></span>
					
						<span class="property-value" aria-labelledby="telefono-label"><g:fieldValue bean="${rsClienteReferenciaInstance}" field="telefono"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${rsClienteReferenciaInstance?.descripcionTelefono}">
				<li class="fieldcontain">
					<span id="descripcionTelefono-label" class="property-label"><g:message code="rsClienteReferencia.descripcionTelefono.label" default="Descripcion Telefono" /></span>
					
						<span class="property-value" aria-labelledby="descripcionTelefono-label"><g:link controller="simCatDescTelefono" action="show" id="${rsClienteReferenciaInstance?.descripcionTelefono?.id}">${rsClienteReferenciaInstance?.descripcionTelefono?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${rsClienteReferenciaInstance?.id}" />
					<g:link class="edit" action="edit" id="${rsClienteReferenciaInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
