
<%@ page import="com.sim.listacobro.ListaCobroDetalle" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'listaCobroDetalle.label', default: 'ListaCobroDetalle')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
		<filterpane:includes />
	</head>
	<body>
		<a href="#list-listaCobroDetalle" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
			</ul>
		</div>
		<div id="list-listaCobroDetalle" class="content scaffold-list" role="main">
			<filterpane:filterButton text="Mostrar Criterios de Busqueda" />
			<filterpane:filterPane domain="com.sim.listacobro.ListaCobroDetalle"
				additionalProperties=""
                associatedProperties="usuario.username"
                excludeProperties=""
                filterPropertyValues=""
			/>
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
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
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${listaCobroDetalleInstanceList}" status="i" var="listaCobroDetalleInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td>${fieldValue(bean: listaCobroDetalleInstance, field: "estatus")}</td>
					
						<td>${fieldValue(bean: listaCobroDetalleInstance, field: "listaCobro")}</td>
					
						<td>${fieldValue(bean: listaCobroDetalleInstance, field: "amortizacion")}</td>
					
						<td>${fieldValue(bean: listaCobroDetalleInstance, field: "pago")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${listaCobroDetalleCount == null ? ListaCobroDetalle.count(): listaCobroDetalleCount}" params="${filterParams}" />
			</div>
		</div>
	</body>
</html>
