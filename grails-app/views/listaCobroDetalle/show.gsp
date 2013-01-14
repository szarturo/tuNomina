
<%@ page import="com.sim.listacobro.ListaCobroDetalle" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'listaCobroDetalle.label', default: 'ListaCobroDetalle')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-listaCobroDetalle" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-listaCobroDetalle" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list listaCobroDetalle">
			
				<g:if test="${listaCobroDetalleInstance?.estatus}">
				<li class="fieldcontain">
					<span id="estatus-label" class="property-label"><g:message code="listaCobroDetalle.estatus.label" default="Estatus" /></span>
					
						<span class="property-value" aria-labelledby="estatus-label"><g:fieldValue bean="${listaCobroDetalleInstance}" field="estatus"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${listaCobroDetalleInstance?.listaCobro}">
				<li class="fieldcontain">
					<span id="listaCobro-label" class="property-label"><g:message code="listaCobroDetalle.listaCobro.label" default="Lista Cobro" /></span>
					
						<span class="property-value" aria-labelledby="listaCobro-label"><g:link controller="listaCobro" action="show" id="${listaCobroDetalleInstance?.listaCobro?.id}">${listaCobroDetalleInstance?.listaCobro?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${listaCobroDetalleInstance?.amortizacion}">
				<li class="fieldcontain">
					<span id="amortizacion-label" class="property-label"><g:message code="listaCobroDetalle.amortizacion.label" default="Amortizacion" /></span>
					
						<span class="property-value" aria-labelledby="amortizacion-label"><g:link controller="tablaAmortizacionRegistro" action="show" id="${listaCobroDetalleInstance?.amortizacion?.id}">${listaCobroDetalleInstance?.amortizacion?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${listaCobroDetalleInstance?.pago}">
				<li class="fieldcontain">
					<span id="pago-label" class="property-label"><g:message code="listaCobroDetalle.pago.label" default="Pago" /></span>
					
						<span class="property-value" aria-labelledby="pago-label"><g:link controller="prestamoPago" action="show" id="${listaCobroDetalleInstance?.pago?.id}">${listaCobroDetalleInstance?.pago?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${listaCobroDetalleInstance?.id}" />
					<g:link class="edit" action="edit" id="${listaCobroDetalleInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
