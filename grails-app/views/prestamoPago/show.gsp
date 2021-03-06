
<%@ page import="com.sim.credito.PrestamoPago" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'prestamoPago.label', default: 'PrestamoPago')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-prestamoPago" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-prestamoPago" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list prestamoPago">
			
				<g:if test="${prestamoPagoInstance?.fechaPago}">
				<li class="fieldcontain">
					<span id="fechaPago-label" class="property-label"><g:message code="prestamoPago.fechaPago.label" default="Fecha Pago" /></span>
					
						<span class="property-value" aria-labelledby="fechaPago-label"><g:formatDate date="${prestamoPagoInstance?.fechaPago}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${prestamoPagoInstance?.importePago}">
				<li class="fieldcontain">
					<span id="importePago-label" class="property-label"><g:message code="prestamoPago.importePago.label" default="Importe Pago" /></span>
					
						<span class="property-value" aria-labelledby="importePago-label"><g:fieldValue bean="${prestamoPagoInstance}" field="importePago"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${prestamoPagoInstance?.pfinMovimiento}">
				<li class="fieldcontain">
					<span id="pfinMovimiento-label" class="property-label"><g:message code="prestamoPago.pfinMovimiento.label" default="Pfin Movimiento" /></span>
					
						<g:each in="${prestamoPagoInstance.pfinMovimiento}" var="p">
						<span class="property-value" aria-labelledby="pfinMovimiento-label"><g:link controller="pfinMovimiento" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${prestamoPagoInstance?.prestamo}">
				<li class="fieldcontain">
					<span id="prestamo-label" class="property-label"><g:message code="prestamoPago.prestamo.label" default="Prestamo" /></span>
					
						<span class="property-value" aria-labelledby="prestamo-label"><g:link controller="prestamo" action="show" id="${prestamoPagoInstance?.prestamo?.id}">${prestamoPagoInstance?.prestamo?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${prestamoPagoInstance?.id}" />
                    <g:actionSubmit class="save" action="aplicaPago" value="${message(code: 'generaPago.aplicaPago', default: 'Aplica Pago')}" />
                    <g:actionSubmit class="save" action="cancelaPagoGuardado" value="${message(code: 'generaPago.cancelaPagoGuardado', default: 'Cancela Pago Guardado')}" />
                    <g:actionSubmit class="save" action="cancelaPagoAplicado" value="${message(code: 'generaPago.cancelaPagoAplicado', default: 'Cancela Pago Aplicado')}" />										
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
