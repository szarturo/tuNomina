
<%@ page import="com.sim.listacobro.ListaCobro" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'listaCobro.label', default: 'ListaCobro')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
		<filterpane:includes />
	</head>
	<body>
		<a href="#show-listaCobro" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list">Listas de Cobro</g:link></li>
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
					
						<span class="property-value" aria-labelledby="fechaInicio-label"><g:formatDate format="dd-MM-yyyy" date="${listaCobroInstance?.fechaInicio}"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${listaCobroInstance?.fechaFin}">
				<li class="fieldcontain">
					<span id="fechaFin-label" class="property-label"><g:message code="listaCobro.fechaFin.label" default="Fecha Fin" /></span>
					
						<span class="property-value" aria-labelledby="fechaFin-label"><g:formatDate format="dd-MM-yyyy" date="${listaCobroInstance?.fechaFin}"/></span>
					
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
		<!--
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${listaCobroInstance?.id}" />
					<g:link class="edit" action="edit" id="${listaCobroInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
				</fieldset>
			</g:form>
		-->
		</div>

		<div id="list-listaCobroDetalle" class="content scaffold-list" role="main">

			<filterpane:filterButton text="Mostrar Criterios de Busqueda" />	
			<form id="listacobro-form" name="listacobro-form" method="post">
				<filterpane:filterPane domain="com.sim.listacobro.ListaCobroDetalle" 
				title = "Filtrar Detalles"
				action = "mostrarDetalles"
				associatedProperties="amortizacion.numeroPago,
				amortizacion.impPago,
				tipoEmpleadoDep.nombreTipoEmpleadoDep"/>
			</form>

			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="estatus" title="${message(code: 'listaCobroDetalle.estatus.label', default: 'Estatus')}" />
					
						<th><g:message code="listaCobroDetalle.listaCobro.label" default="Lista Cobro" /></th>
					
						<th><g:message code="listaCobroDetalle.amortizacion.label" default="Amortizacion" /></th>
					
						<th><g:message code="listaCobroDetalle.pago.label" default="Pago" /></th>
					
						<th><g:message code="listaCobroDetalle.tipoEmpleadoDep.label" default="Tipo Empleado" /></th>					
					</tr>
				</thead>
				<tbody>
				<g:each in="${listaCobroDetalleInstanceList}" status="i" var="listaCobroDetalleInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td>${fieldValue(bean: listaCobroDetalleInstance, field: "estatus")}</td>
					
						<td>${fieldValue(bean: listaCobroDetalleInstance, field: "listaCobro")}</td>
					
						<td>${fieldValue(bean: listaCobroDetalleInstance, field: "amortizacion")}</td>
					
						<td>${fieldValue(bean: listaCobroDetalleInstance, field: "pago")}</td>

						<td>${fieldValue(bean: listaCobroDetalleInstance, field: "tipoEmpleadoDep")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<!--g:paginate total="${listaCobroDetalleInstanceTotal}" /-->
			</div>
		</div>



	</body>
</html>
