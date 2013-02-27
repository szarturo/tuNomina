
<%@ page import="com.sim.cliente.RsClienteEmpleo" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'rsClienteEmpleo.label', default: 'RsClienteEmpleo')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-rsClienteEmpleo" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-rsClienteEmpleo" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list rsClienteEmpleo">
			
				<g:if test="${rsClienteEmpleoInstance?.cliente}">
				<li class="fieldcontain">
					<span id="cliente-label" class="property-label"><g:message code="rsClienteEmpleo.cliente.label" default="Cliente" /></span>
					
						<span class="property-value" aria-labelledby="cliente-label"><g:link controller="rsCliente" action="show" id="${rsClienteEmpleoInstance?.cliente?.id}">${rsClienteEmpleoInstance?.cliente?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${rsClienteEmpleoInstance?.fechaIngreso}">
				<li class="fieldcontain">
					<span id="fechaIngreso-label" class="property-label"><g:message code="rsClienteEmpleo.fechaIngreso.label" default="Fecha Ingreso" /></span>
					
						<span class="property-value" aria-labelledby="fechaIngreso-label"><g:formatDate date="${rsClienteEmpleoInstance?.fechaIngreso}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${rsClienteEmpleoInstance?.area}">
				<li class="fieldcontain">
					<span id="area-label" class="property-label"><g:message code="rsClienteEmpleo.area.label" default="Area" /></span>
					
						<span class="property-value" aria-labelledby="area-label"><g:fieldValue bean="${rsClienteEmpleoInstance}" field="area"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${rsClienteEmpleoInstance?.ingresoMensual}">
				<li class="fieldcontain">
					<span id="ingresoMensual-label" class="property-label"><g:message code="rsClienteEmpleo.ingresoMensual.label" default="Ingreso Mensual" /></span>
					
						<span class="property-value" aria-labelledby="ingresoMensual-label"><g:fieldValue bean="${rsClienteEmpleoInstance}" field="ingresoMensual"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${rsClienteEmpleoInstance?.domicilios}">
				<li class="fieldcontain">
					<span id="domicilios-label" class="property-label"><g:message code="rsClienteEmpleo.domicilios.label" default="Domicilios" /></span>
					
						<span class="property-value" aria-labelledby="domicilios-label"><g:link controller="rsGralDomicilio" action="show" id="${rsClienteEmpleoInstance?.domicilios?.id}">${rsClienteEmpleoInstance?.domicilios?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${rsClienteEmpleoInstance?.id}" />
					<g:link class="edit" action="edit" id="${rsClienteEmpleoInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
