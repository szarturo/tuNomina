
<%@ page import="com.sim.cliente.RsCliente" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'rsCliente.label', default: 'RsCliente')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
		<filterpane:includes />
	</head>
	<body>
		<a href="#list-rsCliente" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
			</ul>
		</div>
		<div id="list-rsCliente" class="content scaffold-list" role="main">
			<filterpane:filterButton text="Mostrar Criterios de Búsqueda" />
			<filterpane:filterPane domain="com.sim.cliente.RsCliente"
				additionalProperties=""
                associatedProperties="dependencia.nombreDependencia"
                excludeProperties="numeroDeNomina"
                filterPropertyValues=""
			/>			
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<th><g:message code="rsCliente.persona.label" default="Persona" /></th>
					
						<th><g:message code="rsCliente.dependencia.label" default="Dependencia" /></th>
					
						<g:sortableColumn property="numeroDeNomina" title="${message(code: 'rsCliente.numeroDeNomina.label', default: 'Numero De Nomina')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${rsClienteInstanceList}" status="i" var="rsClienteInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${rsClienteInstance.id}">${fieldValue(bean: rsClienteInstance, field: "persona")}</g:link></td>
					
						<td>${fieldValue(bean: rsClienteInstance, field: "dependencia")}</td>
					
						<td>${fieldValue(bean: rsClienteInstance, field: "numeroDeNomina")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${rsClienteCount == null ? RsCliente.count(): rsClienteCount}" params="${filterParams}" />
			</div>
		</div>
	</body>
</html>
