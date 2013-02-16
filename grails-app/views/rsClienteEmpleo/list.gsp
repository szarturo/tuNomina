
<%@ page import="com.sim.cliente.RsClienteEmpleo" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'rsClienteEmpleo.label', default: 'RsClienteEmpleo')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-rsClienteEmpleo" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-rsClienteEmpleo" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<th><g:message code="rsClienteEmpleo.cliente.label" default="Cliente" /></th>
					
						<g:sortableColumn property="fechaIngreso" title="${message(code: 'rsClienteEmpleo.fechaIngreso.label', default: 'Fecha Ingreso')}" />
					
						<g:sortableColumn property="area" title="${message(code: 'rsClienteEmpleo.area.label', default: 'Area')}" />
					
						<g:sortableColumn property="ingresoMensual" title="${message(code: 'rsClienteEmpleo.ingresoMensual.label', default: 'Ingreso Mensual')}" />
					
						<th><g:message code="rsClienteEmpleo.domicilio.label" default="Domicilio" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${rsClienteEmpleoInstanceList}" status="i" var="rsClienteEmpleoInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${rsClienteEmpleoInstance.id}">${fieldValue(bean: rsClienteEmpleoInstance, field: "cliente")}</g:link></td>
					
						<td><g:formatDate date="${rsClienteEmpleoInstance.fechaIngreso}" /></td>
					
						<td>${fieldValue(bean: rsClienteEmpleoInstance, field: "area")}</td>
					
						<td>${fieldValue(bean: rsClienteEmpleoInstance, field: "ingresoMensual")}</td>
					
						<td>${fieldValue(bean: rsClienteEmpleoInstance, field: "domicilio")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${rsClienteEmpleoInstanceTotal}" />
			</div>
		</div>
	</body>
</html>