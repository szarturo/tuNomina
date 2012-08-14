
<%@ page import="com.rs.gral.RsGralTelefono" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'rsGralTelefono.label', default: 'RsGralTelefono')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
		<r:require modules="uploadr"/>
	</head>
	<body>
		<a href="#list-rsGralTelefono" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-rsGralTelefono" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="telefono" title="${message(code: 'rsGralTelefono.telefono.label', default: 'Telefono')}" />
					
						<th><g:message code="rsGralTelefono.descripcionTelefono.label" default="Descripcion Telefono" /></th>
					
						<th><g:message code="rsGralTelefono.persona.label" default="Persona" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${rsGralTelefonoInstanceList}" status="i" var="rsGralTelefonoInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${rsGralTelefonoInstance.id}">${fieldValue(bean: rsGralTelefonoInstance, field: "telefono")}</g:link></td>
					
						<td>${fieldValue(bean: rsGralTelefonoInstance, field: "descripcionTelefono")}</td>
					
						<td>${fieldValue(bean: rsGralTelefonoInstance, field: "persona")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${rsGralTelefonoInstanceTotal}" />
			</div>
		</div>
		<uploadr:demo/>
	</body>
</html>
