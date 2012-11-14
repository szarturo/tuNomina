
<%@ page import="com.sim.tablaAmortizacion.TablaAmortizacion" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'tablaAmortizacion.label', default: 'TablaAmortizacion')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-tablaAmortizacion" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-tablaAmortizacion" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list tablaAmortizacion">
			
				<g:if test="${tablaAmortizacionInstance?.numeroPago}">
				<li class="fieldcontain">
					<span id="numeroPago-label" class="property-label"><g:message code="tablaAmortizacion.numeroPago.label" default="Numero Pago" /></span>
					
						<span class="property-value" aria-labelledby="numeroPago-label"><g:fieldValue bean="${tablaAmortizacionInstance}" field="numeroPago"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${tablaAmortizacionInstance?.fecha}">
				<li class="fieldcontain">
					<span id="fecha-label" class="property-label"><g:message code="tablaAmortizacion.fecha.label" default="Fecha" /></span>
					
						<span class="property-value" aria-labelledby="fecha-label"><g:formatDate date="${tablaAmortizacionInstance?.fecha}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${tablaAmortizacionInstance?.impSaldoInicial}">
				<li class="fieldcontain">
					<span id="impSaldoInicial-label" class="property-label"><g:message code="tablaAmortizacion.impSaldoInicial.label" default="Imp Saldo Inicial" /></span>
					
						<span class="property-value" aria-labelledby="impSaldoInicial-label"><g:fieldValue bean="${tablaAmortizacionInstance}" field="impSaldoInicial"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${tablaAmortizacionInstance?.tasaInteres}">
				<li class="fieldcontain">
					<span id="tasaInteres-label" class="property-label"><g:message code="tablaAmortizacion.tasaInteres.label" default="Tasa Interes" /></span>
					
						<span class="property-value" aria-labelledby="tasaInteres-label"><g:fieldValue bean="${tablaAmortizacionInstance}" field="tasaInteres"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${tablaAmortizacionInstance?.impInteres}">
				<li class="fieldcontain">
					<span id="impInteres-label" class="property-label"><g:message code="tablaAmortizacion.impInteres.label" default="Imp Interes" /></span>
					
						<span class="property-value" aria-labelledby="impInteres-label"><g:fieldValue bean="${tablaAmortizacionInstance}" field="impInteres"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${tablaAmortizacionInstance?.impIvaInteres}">
				<li class="fieldcontain">
					<span id="impIvaInteres-label" class="property-label"><g:message code="tablaAmortizacion.impIvaInteres.label" default="Imp Iva Interes" /></span>
					
						<span class="property-value" aria-labelledby="impIvaInteres-label"><g:fieldValue bean="${tablaAmortizacionInstance}" field="impIvaInteres"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${tablaAmortizacionInstance?.impCapital}">
				<li class="fieldcontain">
					<span id="impCapital-label" class="property-label"><g:message code="tablaAmortizacion.impCapital.label" default="Imp Capital" /></span>
					
						<span class="property-value" aria-labelledby="impCapital-label"><g:fieldValue bean="${tablaAmortizacionInstance}" field="impCapital"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${tablaAmortizacionInstance?.impPago}">
				<li class="fieldcontain">
					<span id="impPago-label" class="property-label"><g:message code="tablaAmortizacion.impPago.label" default="Imp Pago" /></span>
					
						<span class="property-value" aria-labelledby="impPago-label"><g:fieldValue bean="${tablaAmortizacionInstance}" field="impPago"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${tablaAmortizacionInstance?.impSaldoFinal}">
				<li class="fieldcontain">
					<span id="impSaldoFinal-label" class="property-label"><g:message code="tablaAmortizacion.impSaldoFinal.label" default="Imp Saldo Final" /></span>
					
						<span class="property-value" aria-labelledby="impSaldoFinal-label"><g:fieldValue bean="${tablaAmortizacionInstance}" field="impSaldoFinal"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${tablaAmortizacionInstance?.pagoPuntual}">
				<li class="fieldcontain">
					<span id="pagoPuntual-label" class="property-label"><g:message code="tablaAmortizacion.pagoPuntual.label" default="Pago Puntual" /></span>
					
						<span class="property-value" aria-labelledby="pagoPuntual-label"><g:formatBoolean boolean="${tablaAmortizacionInstance?.pagoPuntual}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${tablaAmortizacionInstance?.impInteresPagado}">
				<li class="fieldcontain">
					<span id="impInteresPagado-label" class="property-label"><g:message code="tablaAmortizacion.impInteresPagado.label" default="Imp Interes Pagado" /></span>
					
						<span class="property-value" aria-labelledby="impInteresPagado-label"><g:fieldValue bean="${tablaAmortizacionInstance}" field="impInteresPagado"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${tablaAmortizacionInstance?.impIvaInteresPagado}">
				<li class="fieldcontain">
					<span id="impIvaInteresPagado-label" class="property-label"><g:message code="tablaAmortizacion.impIvaInteresPagado.label" default="Imp Iva Interes Pagado" /></span>
					
						<span class="property-value" aria-labelledby="impIvaInteresPagado-label"><g:fieldValue bean="${tablaAmortizacionInstance}" field="impIvaInteresPagado"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${tablaAmortizacionInstance?.impCapitalPagado}">
				<li class="fieldcontain">
					<span id="impCapitalPagado-label" class="property-label"><g:message code="tablaAmortizacion.impCapitalPagado.label" default="Imp Capital Pagado" /></span>
					
						<span class="property-value" aria-labelledby="impCapitalPagado-label"><g:fieldValue bean="${tablaAmortizacionInstance}" field="impCapitalPagado"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${tablaAmortizacionInstance?.impPagoPagado}">
				<li class="fieldcontain">
					<span id="impPagoPagado-label" class="property-label"><g:message code="tablaAmortizacion.impPagoPagado.label" default="Imp Pago Pagado" /></span>
					
						<span class="property-value" aria-labelledby="impPagoPagado-label"><g:fieldValue bean="${tablaAmortizacionInstance}" field="impPagoPagado"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${tablaAmortizacionInstance?.pagado}">
				<li class="fieldcontain">
					<span id="pagado-label" class="property-label"><g:message code="tablaAmortizacion.pagado.label" default="Pagado" /></span>
					
						<span class="property-value" aria-labelledby="pagado-label"><g:formatBoolean boolean="${tablaAmortizacionInstance?.pagado}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${tablaAmortizacionInstance?.fechaPagoUltimo}">
				<li class="fieldcontain">
					<span id="fechaPagoUltimo-label" class="property-label"><g:message code="tablaAmortizacion.fechaPagoUltimo.label" default="Fecha Pago Ultimo" /></span>
					
						<span class="property-value" aria-labelledby="fechaPagoUltimo-label"><g:formatDate date="${tablaAmortizacionInstance?.fechaPagoUltimo}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${tablaAmortizacionInstance?.fechaValorCalculado}">
				<li class="fieldcontain">
					<span id="fechaValorCalculado-label" class="property-label"><g:message code="tablaAmortizacion.fechaValorCalculado.label" default="Fecha Valor Calculado" /></span>
					
						<span class="property-value" aria-labelledby="fechaValorCalculado-label"><g:formatDate date="${tablaAmortizacionInstance?.fechaValorCalculado}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${tablaAmortizacionInstance?.prestamo}">
				<li class="fieldcontain">
					<span id="prestamo-label" class="property-label"><g:message code="tablaAmortizacion.prestamo.label" default="Prestamo" /></span>
					
						<span class="property-value" aria-labelledby="prestamo-label"><g:link controller="prestamo" action="show" id="${tablaAmortizacionInstance?.prestamo?.id}">${tablaAmortizacionInstance?.prestamo?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${tablaAmortizacionInstance?.tablaAmortizacionAccesorio}">
				<li class="fieldcontain">
					<span id="tablaAmortizacionAccesorio-label" class="property-label"><g:message code="tablaAmortizacion.tablaAmortizacionAccesorio.label" default="Tabla Amortizacion Accesorio" /></span>
					
						<g:each in="${tablaAmortizacionInstance.tablaAmortizacionAccesorio}" var="t">
						<span class="property-value" aria-labelledby="tablaAmortizacionAccesorio-label"><g:link controller="tablaAmortizacionAccesorio" action="show" id="${t.id}">${t?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${tablaAmortizacionInstance?.id}" />
					<g:link class="edit" action="edit" id="${tablaAmortizacionInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
