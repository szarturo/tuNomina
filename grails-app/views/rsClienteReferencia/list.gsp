
<%@ page import="com.sim.cliente.RsClienteReferencia" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'rsClienteReferencia.label', default: 'RsClienteReferencia')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-rsClienteReferencia" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-rsClienteReferencia" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<th><g:message code="rsClienteReferencia.cliente.label" default="Cliente" /></th>
					
						<g:sortableColumn property="nombreCompleto" title="${message(code: 'rsClienteReferencia.nombreCompleto.label', default: 'Nombre Completo')}" />
					
						<g:sortableColumn property="telefono" title="${message(code: 'rsClienteReferencia.telefono.label', default: 'Telefono')}" />
					
						<th><g:message code="rsClienteReferencia.descripcionTelefono.label" default="Descripcion Telefono" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${rsClienteReferenciaInstanceList}" status="i" var="rsClienteReferenciaInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${rsClienteReferenciaInstance.id}">${fieldValue(bean: rsClienteReferenciaInstance, field: "cliente")}</g:link></td>
					
						<td>${fieldValue(bean: rsClienteReferenciaInstance, field: "nombreCompleto")}</td>
					
						<td>${fieldValue(bean: rsClienteReferenciaInstance, field: "telefono")}</td>
					
						<td>${fieldValue(bean: rsClienteReferenciaInstance, field: "descripcionTelefono")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${rsClienteReferenciaInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
