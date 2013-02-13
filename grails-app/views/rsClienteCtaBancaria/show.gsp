
<%@ page import="com.sim.cliente.RsClienteCtaBancaria" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'rsClienteCtaBancaria.label', default: 'RsClienteCtaBancaria')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-rsClienteCtaBancaria" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-rsClienteCtaBancaria" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list rsClienteCtaBancaria">
			
				<g:if test="${rsClienteCtaBancariaInstance?.cliente}">
				<li class="fieldcontain">
					<span id="cliente-label" class="property-label"><g:message code="rsClienteCtaBancaria.cliente.label" default="Cliente" /></span>
					
						<span class="property-value" aria-labelledby="cliente-label"><g:link controller="rsCliente" action="show" id="${rsClienteCtaBancariaInstance?.cliente?.id}">${rsClienteCtaBancariaInstance?.cliente?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${rsClienteCtaBancariaInstance?.banco}">
				<li class="fieldcontain">
					<span id="banco-label" class="property-label"><g:message code="rsClienteCtaBancaria.banco.label" default="Banco" /></span>
					
						<span class="property-value" aria-labelledby="banco-label"><g:link controller="simCatBanco" action="show" id="${rsClienteCtaBancariaInstance?.banco?.id}">${rsClienteCtaBancariaInstance?.banco?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${rsClienteCtaBancariaInstance?.depositoPrestamo}">
				<li class="fieldcontain">
					<span id="depositoPrestamo-label" class="property-label"><g:message code="rsClienteCtaBancaria.depositoPrestamo.label" default="Deposito Prestamo" /></span>
					
						<span class="property-value" aria-labelledby="depositoPrestamo-label"><g:formatBoolean boolean="${rsClienteCtaBancariaInstance?.depositoPrestamo}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${rsClienteCtaBancariaInstance?.numeroDeCuenta}">
				<li class="fieldcontain">
					<span id="numeroDeCuenta-label" class="property-label"><g:message code="rsClienteCtaBancaria.numeroDeCuenta.label" default="Numero De Cuenta" /></span>
					
						<span class="property-value" aria-labelledby="numeroDeCuenta-label"><g:fieldValue bean="${rsClienteCtaBancariaInstance}" field="numeroDeCuenta"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${rsClienteCtaBancariaInstance?.clabe}">
				<li class="fieldcontain">
					<span id="clabe-label" class="property-label"><g:message code="rsClienteCtaBancaria.clabe.label" default="Clabe" /></span>
					
						<span class="property-value" aria-labelledby="clabe-label"><g:fieldValue bean="${rsClienteCtaBancariaInstance}" field="clabe"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${rsClienteCtaBancariaInstance?.id}" />
					<g:link class="edit" action="edit" id="${rsClienteCtaBancariaInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
