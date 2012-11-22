
<%@ page import="com.sim.pfin.pruebas.PfinPagoCredito" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'pfinPagoCredito.label', default: 'PfinPagoCredito')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-pfinPagoCredito" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-pfinPagoCredito" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list pfinPagoCredito">
			
				<g:if test="${pfinPagoCreditoInstance?.fechaPago}">
				<li class="fieldcontain">
					<span id="fechaPago-label" class="property-label"><g:message code="pfinPagoCredito.fechaPago.label" default="Fecha Pago" /></span>
					
						<span class="property-value" aria-labelledby="fechaPago-label"><g:formatDate date="${pfinPagoCreditoInstance?.fechaPago}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${pfinPagoCreditoInstance?.importePago}">
				<li class="fieldcontain">
					<span id="importePago-label" class="property-label"><g:message code="pfinPagoCredito.importePago.label" default="Importe Pago" /></span>
					
						<span class="property-value" aria-labelledby="importePago-label"><g:fieldValue bean="${pfinPagoCreditoInstance}" field="importePago"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${pfinPagoCreditoInstance?.prestamo}">
				<li class="fieldcontain">
					<span id="prestamo-label" class="property-label"><g:message code="pfinPagoCredito.prestamo.label" default="Prestamo" /></span>
					
						<span class="property-value" aria-labelledby="prestamo-label"><g:link controller="prestamo" action="show" id="${pfinPagoCreditoInstance?.prestamo?.id}">${pfinPagoCreditoInstance?.prestamo?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${pfinPagoCreditoInstance?.primerPago}">
				<li class="fieldcontain">
					<span id="primerPago-label" class="property-label"><g:message code="pfinPagoCredito.primerPago.label" default="Primer Pago" /></span>
					
						<span class="property-value" aria-labelledby="primerPago-label"><g:link controller="listaCobro" action="show" id="${pfinPagoCreditoInstance?.primerPago?.id}">${pfinPagoCreditoInstance?.primerPago?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${pfinPagoCreditoInstance?.id}" />
					<g:link class="edit" action="edit" id="${pfinPagoCreditoInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
