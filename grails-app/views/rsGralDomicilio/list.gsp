
<%@ page import="com.rs.gral.RsGralDomicilio" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'rsGralDomicilio.label', default: 'RsGralDomicilio')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-rsGralDomicilio" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-rsGralDomicilio" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="calle" title="${message(code: 'rsGralDomicilio.calle.label', default: 'Calle')}" />
					
						<g:sortableColumn property="numeroInterior" title="${message(code: 'rsGralDomicilio.numeroInterior.label', default: 'Numero Interior')}" />
					
						<g:sortableColumn property="numeroExterior" title="${message(code: 'rsGralDomicilio.numeroExterior.label', default: 'Numero Exterior')}" />
					
						<th><g:message code="rsGralDomicilio.rsGralAsentamiento.label" default="Rs Gral Asentamiento" /></th>
					
						<g:sortableColumn property="esFiscal" title="${message(code: 'rsGralDomicilio.esFiscal.label', default: 'Es Fiscal')}" />
					
						<g:sortableColumn property="comentarios" title="${message(code: 'rsGralDomicilio.comentarios.label', default: 'Comentarios')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${rsGralDomicilioInstanceList}" status="i" var="rsGralDomicilioInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${rsGralDomicilioInstance.id}">${fieldValue(bean: rsGralDomicilioInstance, field: "calle")}</g:link></td>
					
						<td>${fieldValue(bean: rsGralDomicilioInstance, field: "numeroInterior")}</td>
					
						<td>${fieldValue(bean: rsGralDomicilioInstance, field: "numeroExterior")}</td>
					
						<td>${fieldValue(bean: rsGralDomicilioInstance, field: "rsGralAsentamiento")}</td>
					
						<td><g:formatBoolean boolean="${rsGralDomicilioInstance.esFiscal}" /></td>
					
						<td>${fieldValue(bean: rsGralDomicilioInstance, field: "comentarios")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${rsGralDomicilioInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
