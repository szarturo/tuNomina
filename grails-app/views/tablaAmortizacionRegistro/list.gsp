
<%@ page import="com.sim.tablaAmortizacion.TablaAmortizacionRegistro" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'tablaAmortizacionRegistro.label', default: 'TablaAmortizacionRegistro')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-tablaAmortizacionRegistro" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-tablaAmortizacionRegistro" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="numeroPago" title="${message(code: 'tablaAmortizacionRegistro.numeroPago.label', default: 'Numero Pago')}" />
					
						<g:sortableColumn property="listaCobro" title="${message(code: 'tablaAmortizacionRegistro.listaCobro.label', default: 'Periodicidad Pago')}" />
					
						<g:sortableColumn property="impSaldoInicial" title="${message(code: 'tablaAmortizacionRegistro.impSaldoInicial.label', default: 'Imp Saldo Inicial')}" />
					
						<g:sortableColumn property="tasaInteres" title="${message(code: 'tablaAmortizacionRegistro.tasaInteres.label', default: 'Tasa Interes')}" />
					
						<g:sortableColumn property="impInteres" title="${message(code: 'tablaAmortizacionRegistro.impInteres.label', default: 'Imp Interes')}" />
					
						<g:sortableColumn property="impIvaInteres" title="${message(code: 'tablaAmortizacionRegistro.impIvaInteres.label', default: 'Imp Iva Interes')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${tablaAmortizacionRegistroInstanceList}" status="i" var="tablaAmortizacionRegistroInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${tablaAmortizacionRegistroInstance.id}">${fieldValue(bean: tablaAmortizacionRegistroInstance, field: "numeroPago")}</g:link></td>
					
						<td>${fieldValue(bean: tablaAmortizacionRegistroInstance, field: "listaCobro")}</td>
					
						<td>${fieldValue(bean: tablaAmortizacionRegistroInstance, field: "impSaldoInicial")}</td>
					
						<td>${fieldValue(bean: tablaAmortizacionRegistroInstance, field: "tasaInteres")}</td>
					
						<td>${fieldValue(bean: tablaAmortizacionRegistroInstance, field: "impInteres")}</td>
					
						<td>${fieldValue(bean: tablaAmortizacionRegistroInstance, field: "impIvaInteres")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>

		</div>
	</body>
</html>
