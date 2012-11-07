
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
					
						<g:sortableColumn property="numeroDePago" title="${message(code: 'tablaAmortizacion.numeroDePago.label', default: 'Numero De Pago')}" />
					
						<g:sortableColumn property="saldoInsoluto" title="${message(code: 'tablaAmortizacion.saldoInsoluto.label', default: 'Saldo Insoluto')}" />
						
						<g:sortableColumn property="amortizacionCapital" title="${message(code: 'tablaAmortizacion.amortizacionCapital.label', default: 'Amortizacion Capital')}" />
						
						<g:sortableColumn property="interes" title="${message(code: 'tablaAmortizacion.interes.label', default: 'Interes')}" />
					
						<g:sortableColumn property="pagoTotal" title="${message(code: 'tablaAmortizacion.pagoTotal.label', default: 'Pago Total')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${tablaAmortizacionInstanceList}" status="i" var="tablaAmortizacionInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td>${fieldValue(bean: tablaAmortizacionInstance, field: "numeroDePago")}</td>

						<td>${fieldValue(bean: tablaAmortizacionInstance, field: "saldoInsoluto")}</td>						

						<td>${fieldValue(bean: tablaAmortizacionInstance, field: "amortizacionCapital")}</td>
												
						<td>${fieldValue(bean: tablaAmortizacionInstance, field: "interes")}</td>
					
						<td>${fieldValue(bean: tablaAmortizacionInstance, field: "pagoTotal")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>

		</div>
	</body>
</html>
