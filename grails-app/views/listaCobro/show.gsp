
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
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
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
					
						<span class="property-value" aria-labelledby="dependencia-label"><g:link controller="entDependencia" action="show" id="${listaCobroInstance?.dependencia?.id}">${listaCobroInstance?.dependencia?.encodeAsHTML()}</g:link></span>
					
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
					
						<span class="property-value" aria-labelledby="periodicidad-label"><g:link controller="simCatPeriodicidad" action="show" id="${listaCobroInstance?.periodicidad?.id}">${listaCobroInstance?.periodicidad?.encodeAsHTML()}</g:link></span>
					
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
					
						<span class="property-value" aria-labelledby="fechaInicio-label"><g:formatDate date="${listaCobroInstance?.fechaInicio}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${listaCobroInstance?.fechaFin}">
				<li class="fieldcontain">
					<span id="fechaFin-label" class="property-label"><g:message code="listaCobro.fechaFin.label" default="Fecha Fin" /></span>
					
						<span class="property-value" aria-labelledby="fechaFin-label"><g:formatDate date="${listaCobroInstance?.fechaFin}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${listaCobroInstance?.estatus}">
				<li class="fieldcontain">
					<span id="estatus-label" class="property-label"><g:message code="listaCobro.estatus.label" default="Estatus" /></span>
					
						<span class="property-value" aria-labelledby="estatus-label"><g:link controller="simCatListaCobroEstatus" action="show" id="${listaCobroInstance?.estatus?.id}">${listaCobroInstance?.estatus?.encodeAsHTML()}</g:link></span>
					
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
			
				<g:if test="${listaCobroInstance?.detalles}">
				<li class="fieldcontain">
					<span id="detalles-label" class="property-label"><g:message code="listaCobro.detalles.label" default="Detalles" /></span>
					
						<g:each in="${listaCobroInstance.detalles}" var="d">
						<span class="property-value" aria-labelledby="detalles-label"><g:link controller="listaCobroDetalle" action="show" id="${d.id}">${d?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${listaCobroInstance?.amortizaciones}">
				<li class="fieldcontain">
					<span id="amortizaciones-label" class="property-label"><g:message code="listaCobro.amortizaciones.label" default="Amortizaciones" /></span>
					
						<g:each in="${listaCobroInstance.amortizaciones}" var="a">
						<span class="property-value" aria-labelledby="amortizaciones-label"><g:link controller="tablaAmortizacionRegistro" action="show" id="${a.id}">${a?.encodeAsHTML()}</g:link></span>
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
					<g:link class="edit" action="edit" id="${listaCobroInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:link class="edit" action="mostrarDetalles" id="${listaCobroInstance?.id}">Mostrar Detalles</g:link>					
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
