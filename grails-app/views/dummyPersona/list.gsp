
<%@ page import="test.DummyPersona" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'dummyPersona.label', default: 'DummyPersona')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-dummyPersona" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-dummyPersona" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="nombre" title="${message(code: 'dummyPersona.nombre.label', default: 'Nombre')}" />
					
						<g:sortableColumn property="apellido" title="${message(code: 'dummyPersona.apellido.label', default: 'Apellido')}" />
					
						<g:sortableColumn property="calle" title="${message(code: 'dummyPersona.calle.label', default: 'Calle')}" />
					
						<g:sortableColumn property="numero" title="${message(code: 'dummyPersona.numero.label', default: 'Numero')}" />
					
						<g:sortableColumn property="codigoPostal" title="${message(code: 'dummyPersona.codigoPostal.label', default: 'Codigo Postal')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${dummyPersonaInstanceList}" status="i" var="dummyPersonaInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${dummyPersonaInstance.id}">${fieldValue(bean: dummyPersonaInstance, field: "nombre")}</g:link></td>
					
						<td>${fieldValue(bean: dummyPersonaInstance, field: "apellido")}</td>
					
						<td>${fieldValue(bean: dummyPersonaInstance, field: "calle")}</td>
					
						<td>${fieldValue(bean: dummyPersonaInstance, field: "numero")}</td>
					
						<td>${fieldValue(bean: dummyPersonaInstance, field: "codigoPostal")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${dummyPersonaInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
