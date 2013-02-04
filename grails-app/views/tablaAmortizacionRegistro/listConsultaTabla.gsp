
<%@ page import="com.sim.tablaAmortizacion.TablaAmortizacionRegistro" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<title>Tabla Amortización</title>
	</head>
	<body>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
			</ul>
		</div>
		<div id="list-tablaAmortizacionRegistro" class="content scaffold-list" role="main">
			<h1>${prestamo}</h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<div class="message" role="status">
				El pago total contempla los accesorios en caso de existir.
			</div>
			<table>
				<thead>
					<tr>
					
						<th><g:message code="tablaAmortizacionRegistro.numeroPago.label" default="Pago" /></th>			

						<th><g:message code="tablaAmortizacionRegistro.fecha.label" default="FechaAmort" /></th>		

						<th><g:message code="tablaAmortizacionRegistro.impSaldoInicial.label" default="Saldo" /></th>		

						<th><g:message code="tablaAmortizacionRegistro.impCapital.label" default="Capital" /></th>		

						<th><g:message code="tablaAmortizacionRegistro.impCapitalPagado.label" default="CapPagado" /></th>		
		
						<th><g:message code="tablaAmortizacionRegistro.impPago.label" default="PagoTotal" /></th>	

						<th><g:message code="tablaAmortizacionRegistro.impPagoPagado.label" default="Pagado" /></th>	

						<th><g:message code="tablaAmortizacionRegistro.pagado.label" default="Cubierto" /></th>

						<th><g:message code="tablaAmortizacionRegistro.pagado.label" default="DebeAmort" /></th>	

					</tr>
				</thead>
				<tbody>
				<g:each in="${tablaAmortizacionRegistroInstanceList}" status="i" var="tablaAmortizacionRegistroInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${tablaAmortizacionRegistroInstance.id}">${fieldValue(bean: tablaAmortizacionRegistroInstance, field: "numeroPago")}</g:link></td>

						<td><g:formatDate format="dd-MM-yyyy" date="${tablaAmortizacionRegistroInstance.fecha}"/></td>
					
						<td>${fieldValue(bean: tablaAmortizacionRegistroInstance, field: "impSaldoInicial")}</td>
					
						<td>${fieldValue(bean: tablaAmortizacionRegistroInstance, field: "impCapital")}</td>	
					
						<td>${fieldValue(bean: tablaAmortizacionRegistroInstance, field: "impCapitalPagado")}</td>

				
						<td>${fieldValue(bean: tablaAmortizacionRegistroInstance, field: "impPago")}</td>

						<td>${
							 tablaAmortizacionRegistroInstance.pagado ? tablaAmortizacionRegistroInstance.impPago : tablaAmortizacionRegistroInstance.impPagoPagado}
						</td>

						<td>${
							 tablaAmortizacionRegistroInstance.pagado ? "Si" : "No" }
						</td>	

						<td>${
							 tablaAmortizacionRegistroInstance.pagado ? "Pagado" : tablaAmortizacionRegistroInstance.impPago - tablaAmortizacionRegistroInstance.impPagoPagado }
						</td>

					</tr>
				</g:each>
				
				</tbody>
			</table>

		</div>
	</body>
</html>
