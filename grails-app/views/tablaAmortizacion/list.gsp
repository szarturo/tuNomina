
<%@ page import="com.sim.tablaAmortizacion.TablaAmortizacion" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'tablaAmortizacion.label', default: 'TablaAmortizacion')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-tablaAmortizacion" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-tablaAmortizacion" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="numeroPago" title="${message(code: 'tablaAmortizacion.numeroPago.label', default: 'Numero Pago')}" />
					
						<g:sortableColumn property="fecha" title="${message(code: 'tablaAmortizacion.fecha.label', default: 'Fecha')}" />
					
						<g:sortableColumn property="impSaldoInicial" title="${message(code: 'tablaAmortizacion.impSaldoInicial.label', default: 'Imp Saldo Inicial')}" />
					
						<g:sortableColumn property="tasaInteres" title="${message(code: 'tablaAmortizacion.tasaInteres.label', default: 'Tasa Interes')}" />
					
						<g:sortableColumn property="impInteres" title="${message(code: 'tablaAmortizacion.impInteres.label', default: 'Imp Interes')}" />
					
						<g:sortableColumn property="impIvaInteres" title="${message(code: 'tablaAmortizacion.impIvaInteres.label', default: 'Imp Iva Interes')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${tablaAmortizacionInstanceList}" status="i" var="tablaAmortizacionInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${tablaAmortizacionInstance.id}">${fieldValue(bean: tablaAmortizacionInstance, field: "numeroPago")}</g:link></td>
					
						<td><g:formatDate date="${tablaAmortizacionInstance.fecha}" /></td>
					
						<td>${fieldValue(bean: tablaAmortizacionInstance, field: "impSaldoInicial")}</td>
					
						<td>${fieldValue(bean: tablaAmortizacionInstance, field: "tasaInteres")}</td>
					
						<td>${fieldValue(bean: tablaAmortizacionInstance, field: "impInteres")}</td>
					
						<td>${fieldValue(bean: tablaAmortizacionInstance, field: "impIvaInteres")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${tablaAmortizacionInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
