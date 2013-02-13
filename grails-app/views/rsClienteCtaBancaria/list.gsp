
<%@ page import="com.sim.cliente.RsClienteCtaBancaria" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'rsClienteCtaBancaria.label', default: 'RsClienteCtaBancaria')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-rsClienteCtaBancaria" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-rsClienteCtaBancaria" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<th><g:message code="rsClienteCtaBancaria.cliente.label" default="Cliente" /></th>
					
						<th><g:message code="rsClienteCtaBancaria.banco.label" default="Banco" /></th>
					
						<g:sortableColumn property="depositoPrestamo" title="${message(code: 'rsClienteCtaBancaria.depositoPrestamo.label', default: 'Deposito Prestamo')}" />
					
						<g:sortableColumn property="numeroDeCuenta" title="${message(code: 'rsClienteCtaBancaria.numeroDeCuenta.label', default: 'Numero De Cuenta')}" />
					
						<g:sortableColumn property="clabe" title="${message(code: 'rsClienteCtaBancaria.clabe.label', default: 'Clabe')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${rsClienteCtaBancariaInstanceList}" status="i" var="rsClienteCtaBancariaInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${rsClienteCtaBancariaInstance.id}">${fieldValue(bean: rsClienteCtaBancariaInstance, field: "cliente")}</g:link></td>
					
						<td>${fieldValue(bean: rsClienteCtaBancariaInstance, field: "banco")}</td>
					
						<td><g:formatBoolean boolean="${rsClienteCtaBancariaInstance.depositoPrestamo}" /></td>
					
						<td>${fieldValue(bean: rsClienteCtaBancariaInstance, field: "numeroDeCuenta")}</td>
					
						<td>${fieldValue(bean: rsClienteCtaBancariaInstance, field: "clabe")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${rsClienteCtaBancariaInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
