
<%@ page import="com.sim.listacobro.ListaCobro" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'listaCobro.label', default: 'ListaCobro')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
		<filterpane:includes />
	</head>
	<body>
		<a href="#list-listaCobro" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
			</ul>
		</div>
		<div id="list-listaCobro" class="content scaffold-list" role="main">
			<filterpane:filterButton text="Mostrar Criterios de Busqueda" />
			<filterpane:filterPane domain="com.sim.listacobro.ListaCobro"
				additionalProperties="identifier"
                associatedProperties="dependencia.claveDependencia,dependencia.nombreDependencia"
                excludeProperties=""
                filterPropertyValues="${[fechaInicio:[years:2012..2015,precision:'day'], fechaFin:[years:2012..2015,precision:'day']]}"
			/>
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<th><g:message code="listaCobro.dependencia.label" default="Dependencia" /></th>
					
						<g:sortableColumn property="anio" title="${message(code: 'listaCobro.anio.label', default: 'Anio')}" params="${filterParams}"/>
					
						<th><g:message code="listaCobro.periodicidad.label" default="Periodicidad" /></th>
					
						<g:sortableColumn property="numeroPago" title="${message(code: 'listaCobro.numeroPago.label', default: 'Numero Pago')}" params="${filterParams}"/>
					
						<g:sortableColumn property="fechaInicio" title="${message(code: 'listaCobro.fechaInicio.label', default: 'Fecha Inicio')}" params="${filterParams}"/>
					
						<g:sortableColumn property="fechaFin" title="${message(code: 'listaCobro.fechaFin.label', default: 'Fecha Fin')}" params="${filterParams}"/>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${listaCobroInstanceList}" status="i" var="listaCobroInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${listaCobroInstance.id}">${fieldValue(bean: listaCobroInstance, field: "dependencia")}</g:link></td>
					
						<td>${fieldValue(bean: listaCobroInstance, field: "anio")}</td>
					
						<td>${fieldValue(bean: listaCobroInstance, field: "periodicidad")}</td>
					
						<td>${fieldValue(bean: listaCobroInstance, field: "numeroPago")}</td>
					
						<td><g:formatDate date="${listaCobroInstance.fechaInicio}" format="dd-MM-yyyy"/></td>
					
						<td><g:formatDate date="${listaCobroInstance.fechaFin}" format="dd-MM-yyyy"/></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			
			<div class="pagination">
				<g:paginate total="${listaCobroCount == null ? ListaCobro.count(): listaCobroCount}" params="${filterParams}" />
			</div>
			
		</div>
	</body>
</html>
