
<%@ page import="com.sim.listacobro.ListaCobro" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'listaCobro.label', default: 'ListaCobro')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-listaCobro" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-listaCobro" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list listaCobro">
			
				<g:if test="${listaCobroInstance?.dependencia}">
				<li class="fieldcontain">
					<span id="dependencia-label" class="property-label"><g:message code="listaCobro.dependencia.label" default="Dependencia" /></span>
					
						<span class="property-value" aria-labelledby="dependencia-label">${listaCobroInstance?.dependencia?.encodeAsHTML()}</span>
					
				</li>
				</g:if>
			
				<g:if test="${listaCobroInstance?.anio}">
				<li class="fieldcontain">
					<span id="anio-label" class="property-label"><g:message code="listaCobro.anio.label" default="Anio" /></span>
					
						<span class="property-value" aria-labelledby="anio-label"><g:fieldValue bean="${listaCobroInstance}" field="anio"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${listaCobroInstance?.periodicidad}">
				<li class="fieldcontain">
					<span id="periodicidad-label" class="property-label"><g:message code="listaCobro.periodicidad.label" default="Periodicidad" /></span>
					
						<span class="property-value" aria-labelledby="periodicidad-label">${listaCobroInstance?.periodicidad?.encodeAsHTML()}</span>
					
				</li>
				</g:if>
			
				<g:if test="${listaCobroInstance?.numeroPago}">
				<li class="fieldcontain">
					<span id="numeroPago-label" class="property-label"><g:message code="listaCobro.numeroPago.label" default="Numero Pago" /></span>
					
						<span class="property-value" aria-labelledby="numeroPago-label"><g:fieldValue bean="${listaCobroInstance}" field="numeroPago"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${listaCobroInstance?.fechaInicio}">
				<li class="fieldcontain">
					<span id="fechaInicio-label" class="property-label"><g:message code="listaCobro.fechaInicio.label" default="Fecha Inicio" /></span>
					
						<span class="property-value" aria-labelledby="fechaInicio-label"><g:formatDate date="${listaCobroInstance?.fechaInicio}" format="dd-MM-yyyy"/></span>
				</li>
				</g:if>
			
				<g:if test="${listaCobroInstance?.fechaFin}">
				<li class="fieldcontain">
					<span id="fechaFin-label" class="property-label"><g:message code="listaCobro.fechaFin.label" default="Fecha Fin" /></span>
					
						<span class="property-value" aria-labelledby="fechaFin-label"><g:formatDate date="${listaCobroInstance?.fechaFin}" format="dd-MM-yyyy"/></span>
				</li>
				</g:if>
			
				<g:if test="${listaCobroInstance?.estatus}">
				<li class="fieldcontain">
					<span id="estatus-label" class="property-label"><g:message code="listaCobro.estatus.label" default="Estatus" /></span>
					
						<span class="property-value" aria-labelledby="estatus-label">${listaCobroInstance?.estatus?.encodeAsHTML()}</span>
					
				</li>
				</g:if>
			
				<g:if test="${listaCobroInstance?.listaCobroProceso}">
				<li class="fieldcontain">
					<span id="listaCobroProceso-label" class="property-label"><g:message code="listaCobro.listaCobroProceso.label" default="Lista Cobro Proceso" /></span>
					
						<g:each in="${listaCobroInstance.listaCobroProceso}" var="l">
						<span class="property-value" aria-labelledby="listaCobroProceso-label"><g:link controller="listaCobroProceso" action="show" id="${l.id}">${l?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${listaCobroInstance?.parcialidades}">
				<li class="fieldcontain">
					<span id="parcialidades-label" class="property-label"><g:message code="listaCobro.parcialidades.label" default="Parcialidades" /></span>
						<span class="property-value" aria-labelledby="parcialidades-label"><g:formatBoolean boolean="${listaCobroInstance?.parcialidades}" /></span>
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${listaCobroInstance?.id}" />
					<g:link class="edit" action="mostrarDetalles" id="${listaCobroInstance?.id}">Mostrar Detalles</g:link>					
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
