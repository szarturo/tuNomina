
<%@ page import="com.rs.gral.RsPersona" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'rsPersona.label', default: 'RsPersona')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-rsPersona" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-rsPersona" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="apellidoPaterno" title="${message(code: 'rsPersona.apellidoPaterno.label', default: 'Apellido Paterno')}" />
					
						<g:sortableColumn property="apellidoMaterno" title="${message(code: 'rsPersona.apellidoMaterno.label', default: 'Apellido Materno')}" />
					
						<g:sortableColumn property="primerNombre" title="${message(code: 'rsPersona.primerNombre.label', default: 'Primer Nombre')}" />
					
						<g:sortableColumn property="segundoNombre" title="${message(code: 'rsPersona.segundoNombre.label', default: 'Segundo Nombre')}" />
					
				
					</tr>
				</thead>
				<tbody>
				<g:each in="${rsPersonaInstanceList}" status="i" var="rsPersonaInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${rsPersonaInstance.id}">${fieldValue(bean: rsPersonaInstance, field: "apellidoPaterno")}</g:link></td>
					
						<td>${fieldValue(bean: rsPersonaInstance, field: "apellidoMaterno")}</td>
					
						<td>${fieldValue(bean: rsPersonaInstance, field: "primerNombre")}</td>
					
						<td>${fieldValue(bean: rsPersonaInstance, field: "segundoNombre")}</td>
					
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${rsPersonaInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
