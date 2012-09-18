
<%@ page import="com.sim.credito.Prestamo" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'prestamo.label', default: 'Prestamo')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-prestamo" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-prestamo" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list prestamo">
			
				<g:if test="${prestamoInstance?.cliente}">
				<li class="fieldcontain">
					<span id="cliente-label" class="property-label"><g:message code="prestamo.cliente.label" default="Cliente" /></span>
					
						<span class="property-value" aria-labelledby="cliente-label"><g:link controller="rsCliente" action="show" id="${prestamoInstance?.cliente?.id}">${prestamoInstance?.cliente?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${prestamoInstance?.clavePrestamo}">
				<li class="fieldcontain">
					<span id="clavePrestamo-label" class="property-label"><g:message code="prestamo.clavePrestamo.label" default="Clave Prestamo" /></span>
					
						<span class="property-value" aria-labelledby="clavePrestamo-label"><g:fieldValue bean="${prestamoInstance}" field="clavePrestamo"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${prestamoInstance?.folioSolicitud}">
				<li class="fieldcontain">
					<span id="folioSolicitud-label" class="property-label"><g:message code="prestamo.folioSolicitud.label" default="Folio Solicitud" /></span>
					
						<span class="property-value" aria-labelledby="folioSolicitud-label"><g:fieldValue bean="${prestamoInstance}" field="folioSolicitud"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${prestamoInstance?.promocion}">
				<li class="fieldcontain">
					<span id="promocion-label" class="property-label"><g:message code="prestamo.promocion.label" default="Promocion" /></span>
					
						<span class="property-value" aria-labelledby="promocion-label"><g:link controller="proPromocion" action="show" id="${prestamoInstance?.promocion?.id}">${prestamoInstance?.promocion?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${prestamoInstance?.dependencia}">
				<li class="fieldcontain">
					<span id="dependencia-label" class="property-label"><g:message code="prestamo.dependencia.label" default="Dependencia" /></span>
					
						<span class="property-value" aria-labelledby="dependencia-label"><g:link controller="entDependencia" action="show" id="${prestamoInstance?.dependencia?.id}">${prestamoInstance?.dependencia?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${prestamoInstance?.sucursal}">
				<li class="fieldcontain">
					<span id="sucursal-label" class="property-label"><g:message code="prestamo.sucursal.label" default="Sucursal" /></span>
					
						<span class="property-value" aria-labelledby="sucursal-label"><g:link controller="entSucursal" action="show" id="${prestamoInstance?.sucursal?.id}">${prestamoInstance?.sucursal?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${prestamoInstance?.delegacion}">
				<li class="fieldcontain">
					<span id="delegacion-label" class="property-label"><g:message code="prestamo.delegacion.label" default="Delegacion" /></span>
					
						<span class="property-value" aria-labelledby="delegacion-label"><g:link controller="entDelegacion" action="show" id="${prestamoInstance?.delegacion?.id}">${prestamoInstance?.delegacion?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${prestamoInstance?.vendedor}">
				<li class="fieldcontain">
					<span id="vendedor-label" class="property-label"><g:message code="prestamo.vendedor.label" default="Vendedor" /></span>
					
						<span class="property-value" aria-labelledby="vendedor-label"><g:link controller="empEmpleado" action="show" id="${prestamoInstance?.vendedor?.id}">${prestamoInstance?.vendedor?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${prestamoInstance?.fechaSolicitud}">
				<li class="fieldcontain">
					<span id="fechaSolicitud-label" class="property-label"><g:message code="prestamo.fechaSolicitud.label" default="Fecha Solicitud" /></span>
					
						<span class="property-value" aria-labelledby="fechaSolicitud-label"><g:formatDate date="${prestamoInstance?.fechaSolicitud}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${prestamoInstance?.montoSolicitado}">
				<li class="fieldcontain">
					<span id="montoSolicitado-label" class="property-label"><g:message code="prestamo.montoSolicitado.label" default="Monto Solicitado" /></span>
					
						<span class="property-value" aria-labelledby="montoSolicitado-label"><g:fieldValue bean="${prestamoInstance}" field="montoSolicitado"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${prestamoInstance?.estatusSolicitud}">
				<li class="fieldcontain">
					<span id="estatusSolicitud-label" class="property-label"><g:message code="prestamo.estatusSolicitud.label" default="Estatus Solicitud" /></span>
					
						<span class="property-value" aria-labelledby="estatusSolicitud-label"><g:fieldValue bean="${prestamoInstance}" field="estatusSolicitud"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${prestamoInstance?.formaDeDispercion}">
				<li class="fieldcontain">
					<span id="formaDeDispercion-label" class="property-label"><g:message code="prestamo.formaDeDispercion.label" default="Forma De Dispercion" /></span>
					
						<span class="property-value" aria-labelledby="formaDeDispercion-label"><g:link controller="simCatFormaEntrega" action="show" id="${prestamoInstance?.formaDeDispercion?.id}">${prestamoInstance?.formaDeDispercion?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${prestamoInstance?.documentosCorrectos}">
				<li class="fieldcontain">
					<span id="documentosCorrectos-label" class="property-label"><g:message code="prestamo.documentosCorrectos.label" default="Documentos Correctos" /></span>
					
						<span class="property-value" aria-labelledby="documentosCorrectos-label"><g:formatBoolean boolean="${prestamoInstance?.documentosCorrectos}" /></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${prestamoInstance?.id}" />
					<g:link class="edit" action="edit" id="${prestamoInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                    <g:link controller="prestamoDocumentos" action="listaDocumentos" id="${prestamoInstance.clavePrestamo}">Documentos</g:link>
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
