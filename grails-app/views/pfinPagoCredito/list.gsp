
<%@ page import="com.sim.pfin.pruebas.PfinPagoCredito" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'pfinPagoCredito.label', default: 'PfinPagoCredito')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-pfinPagoCredito" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-pfinPagoCredito" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="fechaPago" title="${message(code: 'pfinPagoCredito.fechaPago.label', default: 'Fecha Pago')}" />
					
						<g:sortableColumn property="importePago" title="${message(code: 'pfinPagoCredito.importePago.label', default: 'Importe Pago')}" />
					
						<th><g:message code="pfinPagoCredito.prestamo.label" default="Prestamo" /></th>
					
						<th><g:message code="pfinPagoCredito.primerPago.label" default="Primer Pago" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${pfinPagoCreditoInstanceList}" status="i" var="pfinPagoCreditoInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${pfinPagoCreditoInstance.id}">${fieldValue(bean: pfinPagoCreditoInstance, field: "fechaPago")}</g:link></td>
					
						<td>${fieldValue(bean: pfinPagoCreditoInstance, field: "importePago")}</td>
					
						<td>${fieldValue(bean: pfinPagoCreditoInstance, field: "prestamo")}</td>
					
						<td>${fieldValue(bean: pfinPagoCreditoInstance, field: "primerPago")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${pfinPagoCreditoInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
