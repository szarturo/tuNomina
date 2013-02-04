
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

						<g:sortableColumn property="fecha" title="${message(code: 'tablaAmortizacionRegistro.fecha.label', default: 'Fecha')}" />

						<g:sortableColumn property="impSaldoInicial" title="${message(code: 'tablaAmortizacionRegistro.impSaldoInicial.label', default: 'Saldo')}" />

						<g:sortableColumn property="tasaInteres" title="${message(code: 'tablaAmortizacionRegistro.tasaInteres.label', default: 'TasaInteres')}" />
					
						<g:sortableColumn property="impCapital" title="${message(code: 'tablaAmortizacionRegistro.impCapital.label', default: 'Capital')}" />
<!--					
						<g:sortableColumn property="impCapitalPagado" title="${message(code: 'tablaAmortizacionRegistro.impCapitalPagado.label', default: 'CapitalPagado')}" />
-->

						<g:sortableColumn property="impInteres" title="${message(code: 'tablaAmortizacionRegistro.impInteres.label', default: 'Interes')}" />
					
						<g:sortableColumn property="impIvaInteres" title="${message(code: 'tablaAmortizacionRegistro.impIvaInteres.label', default: 'IvaInteres')}" />

						<g:sortableColumn property="impPago" title="${message(code: 'tablaAmortizacionRegistro.impPago.label', default: 'PagoTotal')}" />

<!--
						<g:sortableColumn property="impPagoPagado" title="${message(code: 'tablaAmortizacionRegistro.impPagoPagado.label', default: 'PagoPagado')}" />

						<g:sortableColumn property="pagado" title="${message(code: 'tablaAmortizacionRegistro.pagado.label', default: 'Pagado')}" />

						<g:sortableColumn property="impSaldoFinal" title="${message(code: 'tablaAmortizacionRegistro.impSaldoFinal.label', default: 'SaldoFinal')}" />
-->
					</tr>
				</thead>
				<tbody>
				<g:each in="${tablaAmortizacionRegistroInstanceList}" status="i" var="tablaAmortizacionRegistroInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${tablaAmortizacionRegistroInstance.id}">${fieldValue(bean: tablaAmortizacionRegistroInstance, field: "numeroPago")}</g:link></td>

						<td><g:formatDate format="dd-MM-yyyy" date="${tablaAmortizacionRegistroInstance.fecha}"/></td>
					
						<td>${fieldValue(bean: tablaAmortizacionRegistroInstance, field: "impSaldoInicial")}</td>
					
						<td>${fieldValue(bean: tablaAmortizacionRegistroInstance, field: "tasaInteres")}</td>

						<td>${fieldValue(bean: tablaAmortizacionRegistroInstance, field: "impCapital")}</td>	
<!--					
						<td>${fieldValue(bean: tablaAmortizacionRegistroInstance, field: "impCapitalPagado")}</td>
-->
						<td>${fieldValue(bean: tablaAmortizacionRegistroInstance, field: "impInteres")}</td>	

						<td>${fieldValue(bean: tablaAmortizacionRegistroInstance, field: "impIvaInteres")}</td>	
					
						<td>${fieldValue(bean: tablaAmortizacionRegistroInstance, field: "impPago")}</td>

<!--
						<td>${fieldValue(bean: tablaAmortizacionRegistroInstance, field: "impPagoPagado")}</td>						
						<td>${fieldValue(bean: tablaAmortizacionRegistroInstance, field: "pagado")}</td>		

						<td>${tablaAmortizacionRegistroInstance.impPago - tablaAmortizacionRegistroInstance.impPagoPagado }</td>

-->

					</tr>
				</g:each>
				El pago total contempla los accesorios en caso de existir.
				</tbody>
			</table>

		</div>
	</body>
</html>
