
<%@ page import="com.sim.cliente.RsCliente" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'rsCliente.label', default: 'RsCliente')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-rsCliente" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>

				<sec:ifAllGranted roles="ROLE_MESA_CONTROL">
					<li><g:link class="create" controller="prestamo" action="start" params="[idCliente : rsClienteInstance.id]">
	     				Iniciar Solicitud
					</g:link></li>
				</sec:ifAllGranted>					
			</ul>
		</div>
		<div id="show-rsCliente" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list rsCliente">
			
				<g:if test="${rsClienteInstance?.persona}">
				<li class="fieldcontain">
					<span id="persona-label" class="property-label"><g:message code="rsCliente.persona.label" default="Persona" /></span>
					
						<span class="property-value" aria-labelledby="persona-label"><g:link controller="rsPersona" action="show" id="${rsClienteInstance?.persona?.id}">${rsClienteInstance?.persona?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${rsClienteInstance?.dependencia}">
				<li class="fieldcontain">
					<span id="dependencia-label" class="property-label"><g:message code="rsCliente.dependencia.label" default="Dependencia" /></span>
					
						<g:each in="${rsClienteInstance.dependencia}" var="d">
						<span class="property-value" aria-labelledby="dependencia-label"><g:link controller="entDependencia" action="show" id="${d.id}">${d?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${rsClienteInstance?.numeroDeNomina}">
				<li class="fieldcontain">
					<span id="numeroDeNomina-label" class="property-label"><g:message code="rsCliente.numeroDeNomina.label" default="Numero De Nomina" /></span>
					
						<span class="property-value" aria-labelledby="numeroDeNomina-label"><g:fieldValue bean="${rsClienteInstance}" field="numeroDeNomina"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${rsClienteInstance?.creditos}">
				<li class="fieldcontain">
					<span id="creditos-label" class="property-label"><g:message code="rsCliente.creditos.label" default="Creditos" /></span>
					
						<g:each in="${rsClienteInstance.creditos}" var="c">
						<span class="property-value" aria-labelledby="creditos-label"><g:link controller="prestamo" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${rsClienteInstance?.cuentas}">
				<li class="fieldcontain">
					<span id="cuentas-label" class="property-label"><g:message code="rsCliente.cuentas.label" default="Cuentas" /></span>
					
						<g:each in="${rsClienteInstance.cuentas}" var="c">
						<span class="property-value" aria-labelledby="cuentas-label"><g:link controller="pfinCuenta" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${rsClienteInstance?.cuentaBancaria}">
				<li class="fieldcontain">
					<span id="cuentaBancaria-label" class="property-label"><g:message code="rsCliente.cuentaBancaria.label" default="Cuenta Bancaria" /></span>
					
						<g:each in="${rsClienteInstance.cuentaBancaria}" var="c">
						<span class="property-value" aria-labelledby="cuentaBancaria-label"><g:link controller="rsClienteCtaBancaria" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${rsClienteInstance?.clienteEmpleo}">
				<li class="fieldcontain">
					<span id="clienteEmpleo-label" class="property-label"><g:message code="rsCliente.clienteEmpleo.label" default="Cliente Empleo" /></span>
					
						<g:each in="${rsClienteInstance.clienteEmpleo}" var="c">
						<span class="property-value" aria-labelledby="clienteEmpleo-label"><g:link controller="rsClienteEmpleo" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${rsClienteInstance?.referenciasClientes}">
				<li class="fieldcontain">
					<span id="referenciasClientes-label" class="property-label"><g:message code="rsCliente.referenciasClientes.label" default="Referencias Clientes" /></span>
					
						<g:each in="${rsClienteInstance.referenciasClientes}" var="r">
						<span class="property-value" aria-labelledby="referenciasClientes-label"><g:link controller="rsClienteReferencia" action="show" id="${r.id}">${r?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${rsClienteInstance?.id}" />
					<g:link class="edit" action="edit" id="${rsClienteInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
