
<%@ page import="com.sim.credito.Prestamo" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'prestamo.label', default: 'Prestamo')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-prestamo" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-prestamo" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="clavePrestamo" title="${message(code: 'prestamo.clavePrestamo.label', default: 'Clave Prestamo')}" />
					
						<g:sortableColumn property="folioSolicitud" title="${message(code: 'prestamo.folioSolicitud.label', default: 'Folio Solicitud')}" />
					
						<th><g:message code="prestamo.promocion.label" default="Promocion" /></th>
					
						<th><g:message code="prestamo.dependencia.label" default="Dependencia" /></th>
					
						<th><g:message code="prestamo.sucursal.label" default="Sucursal" /></th>
					
						<th><g:message code="prestamo.delegacion.label" default="Delegacion" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${prestamoInstanceList}" status="i" var="prestamoInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${prestamoInstance.id}">${fieldValue(bean: prestamoInstance, field: "clavePrestamo")}</g:link></td>
					
						<td>${fieldValue(bean: prestamoInstance, field: "folioSolicitud")}</td>
					
						<td>${fieldValue(bean: prestamoInstance, field: "promocion")}</td>
					
						<td>${fieldValue(bean: prestamoInstance, field: "dependencia")}</td>
					
						<td>${fieldValue(bean: prestamoInstance, field: "sucursal")}</td>
					
						<td>${fieldValue(bean: prestamoInstance, field: "delegacion")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${prestamoInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
