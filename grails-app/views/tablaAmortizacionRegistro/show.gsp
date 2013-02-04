
<%@ page import="com.sim.tablaAmortizacion.TablaAmortizacionRegistro" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'tablaAmortizacionRegistro.label', default: 'TablaAmortizacionRegistro')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-tablaAmortizacionRegistro" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-tablaAmortizacionRegistro" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list tablaAmortizacionRegistro">
			
				<g:if test="${tablaAmortizacionRegistroInstance?.numeroPago}">
				<li class="fieldcontain">
					<span id="numeroPago-label" class="property-label"><g:message code="tablaAmortizacionRegistro.numeroPago.label" default="Numero Pago" /></span>
					
						<span class="property-value" aria-labelledby="numeroPago-label"><g:fieldValue bean="${tablaAmortizacionRegistroInstance}" field="numeroPago"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${tablaAmortizacionRegistroInstance?.fecha}">
				<li class="fieldcontain">
					<span id="fecha-label" class="property-label"><g:message code="tablaAmortizacionRegistro.fecha.label" default="Fecha" /></span>
					
						<span class="property-value" aria-labelledby="fecha-label"><g:formatDate date="${tablaAmortizacionRegistroInstance?.fecha}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${tablaAmortizacionRegistroInstance?.impSaldoInicial}">
				<li class="fieldcontain">
					<span id="impSaldoInicial-label" class="property-label"><g:message code="tablaAmortizacionRegistro.impSaldoInicial.label" default="Imp Saldo Inicial" /></span>
					
						<span class="property-value" aria-labelledby="impSaldoInicial-label"><g:fieldValue bean="${tablaAmortizacionRegistroInstance}" field="impSaldoInicial"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${tablaAmortizacionRegistroInstance?.tasaInteres}">
				<li class="fieldcontain">
					<span id="tasaInteres-label" class="property-label"><g:message code="tablaAmortizacionRegistro.tasaInteres.label" default="Tasa Interes" /></span>
					
						<span class="property-value" aria-labelledby="tasaInteres-label"><g:fieldValue bean="${tablaAmortizacionRegistroInstance}" field="tasaInteres"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${tablaAmortizacionRegistroInstance?.impInteres}">
				<li class="fieldcontain">
					<span id="impInteres-label" class="property-label"><g:message code="tablaAmortizacionRegistro.impInteres.label" default="Imp Interes" /></span>
					
						<span class="property-value" aria-labelledby="impInteres-label"><g:fieldValue bean="${tablaAmortizacionRegistroInstance}" field="impInteres"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${tablaAmortizacionRegistroInstance?.impIvaInteres}">
				<li class="fieldcontain">
					<span id="impIvaInteres-label" class="property-label"><g:message code="tablaAmortizacionRegistro.impIvaInteres.label" default="Imp Iva Interes" /></span>
					
						<span class="property-value" aria-labelledby="impIvaInteres-label"><g:fieldValue bean="${tablaAmortizacionRegistroInstance}" field="impIvaInteres"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${tablaAmortizacionRegistroInstance?.impCapital}">
				<li class="fieldcontain">
					<span id="impCapital-label" class="property-label"><g:message code="tablaAmortizacionRegistro.impCapital.label" default="Imp Capital" /></span>
					
						<span class="property-value" aria-labelledby="impCapital-label"><g:fieldValue bean="${tablaAmortizacionRegistroInstance}" field="impCapital"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${tablaAmortizacionRegistroInstance?.impPago}">
				<li class="fieldcontain">
					<span id="impPago-label" class="property-label"><g:message code="tablaAmortizacionRegistro.impPago.label" default="Imp Pago" /></span>
					
						<span class="property-value" aria-labelledby="impPago-label"><g:fieldValue bean="${tablaAmortizacionRegistroInstance}" field="impPago"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${tablaAmortizacionRegistroInstance?.impSaldoFinal}">
				<li class="fieldcontain">
					<span id="impSaldoFinal-label" class="property-label"><g:message code="tablaAmortizacionRegistro.impSaldoFinal.label" default="Imp Saldo Final" /></span>
					
						<span class="property-value" aria-labelledby="impSaldoFinal-label"><g:fieldValue bean="${tablaAmortizacionRegistroInstance}" field="impSaldoFinal"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${tablaAmortizacionRegistroInstance?.pagoPuntual}">
				<li class="fieldcontain">
					<span id="pagoPuntual-label" class="property-label"><g:message code="tablaAmortizacionRegistro.pagoPuntual.label" default="Pago Puntual" /></span>
					
						<span class="property-value" aria-labelledby="pagoPuntual-label"><g:formatBoolean boolean="${tablaAmortizacionRegistroInstance?.pagoPuntual}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${tablaAmortizacionRegistroInstance?.impInteresPagado}">
				<li class="fieldcontain">
					<span id="impInteresPagado-label" class="property-label"><g:message code="tablaAmortizacionRegistro.impInteresPagado.label" default="Imp Interes Pagado" /></span>
					
						<span class="property-value" aria-labelledby="impInteresPagado-label"><g:fieldValue bean="${tablaAmortizacionRegistroInstance}" field="impInteresPagado"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${tablaAmortizacionRegistroInstance?.impIvaInteresPagado}">
				<li class="fieldcontain">
					<span id="impIvaInteresPagado-label" class="property-label"><g:message code="tablaAmortizacionRegistro.impIvaInteresPagado.label" default="Imp Iva Interes Pagado" /></span>
					
						<span class="property-value" aria-labelledby="impIvaInteresPagado-label"><g:fieldValue bean="${tablaAmortizacionRegistroInstance}" field="impIvaInteresPagado"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${tablaAmortizacionRegistroInstance?.impCapitalPagado}">
				<li class="fieldcontain">
					<span id="impCapitalPagado-label" class="property-label"><g:message code="tablaAmortizacionRegistro.impCapitalPagado.label" default="Imp Capital Pagado" /></span>
					
						<span class="property-value" aria-labelledby="impCapitalPagado-label"><g:fieldValue bean="${tablaAmortizacionRegistroInstance}" field="impCapitalPagado"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${tablaAmortizacionRegistroInstance?.impPagoPagado}">
				<li class="fieldcontain">
					<span id="impPagoPagado-label" class="property-label"><g:message code="tablaAmortizacionRegistro.impPagoPagado.label" default="Imp Pago Pagado" /></span>
					
						<span class="property-value" aria-labelledby="impPagoPagado-label"><g:fieldValue bean="${tablaAmortizacionRegistroInstance}" field="impPagoPagado"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${tablaAmortizacionRegistroInstance?.pagado}">
				<li class="fieldcontain">
					<span id="pagado-label" class="property-label"><g:message code="tablaAmortizacionRegistro.pagado.label" default="Pagado" /></span>
					
						<span class="property-value" aria-labelledby="pagado-label"><g:formatBoolean boolean="${tablaAmortizacionRegistroInstance?.pagado}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${tablaAmortizacionRegistroInstance?.fechaPagoUltimo}">
				<li class="fieldcontain">
					<span id="fechaPagoUltimo-label" class="property-label"><g:message code="tablaAmortizacionRegistro.fechaPagoUltimo.label" default="Fecha Pago Ultimo" /></span>
					
						<span class="property-value" aria-labelledby="fechaPagoUltimo-label"><g:formatDate date="${tablaAmortizacionRegistroInstance?.fechaPagoUltimo}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${tablaAmortizacionRegistroInstance?.fechaValorCalculado}">
				<li class="fieldcontain">
					<span id="fechaValorCalculado-label" class="property-label"><g:message code="tablaAmortizacionRegistro.fechaValorCalculado.label" default="Fecha Valor Calculado" /></span>
					
						<span class="property-value" aria-labelledby="fechaValorCalculado-label"><g:formatDate date="${tablaAmortizacionRegistroInstance?.fechaValorCalculado}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${tablaAmortizacionRegistroInstance?.listaCobro}">
				<li class="fieldcontain">
					<span id="listaCobro-label" class="property-label"><g:message code="tablaAmortizacionRegistro.listaCobro.label" default="Lista Cobro" /></span>
					
						<span class="property-value" aria-labelledby="listaCobro-label"><g:link controller="listaCobro" action="show" id="${tablaAmortizacionRegistroInstance?.listaCobro?.id}">${tablaAmortizacionRegistroInstance?.listaCobro?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${tablaAmortizacionRegistroInstance?.prestamo}">
				<li class="fieldcontain">
					<span id="prestamo-label" class="property-label"><g:message code="tablaAmortizacionRegistro.prestamo.label" default="Prestamo" /></span>
					
						<span class="property-value" aria-labelledby="prestamo-label"><g:link controller="prestamo" action="show" id="${tablaAmortizacionRegistroInstance?.prestamo?.id}">${tablaAmortizacionRegistroInstance?.prestamo?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${tablaAmortizacionRegistroInstance?.tablaAmortizacionAccesorio}">
				<li class="fieldcontain">
					<span id="tablaAmortizacionAccesorio-label" class="property-label"><g:message code="tablaAmortizacionRegistro.tablaAmortizacionAccesorio.label" default="Tabla Amortizacion Accesorio" /></span>
					
						<g:each in="${tablaAmortizacionRegistroInstance.tablaAmortizacionAccesorio}" var="t">
						<span class="property-value" aria-labelledby="tablaAmortizacionAccesorio-label"><g:link controller="tablaAmortizacionAccesorio" action="show" id="${t.id}">${t?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${tablaAmortizacionRegistroInstance?.id}" />

				</fieldset>
			</g:form>
		</div>
	</body>
</html>
