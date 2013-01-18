
<%@ page import="com.sim.credito.Prestamo" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'prestamo.label', default: 'Prestamo')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
			<div class="nav" role="navigation">
			<ul> 
            <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
	        <li><g:link class="list" controller="task" action="myTaskList"><g:message code="myTasks.label" default="My Tasks ({0})" args="[myTasksCount]" /></g:link></li>
	        <sec:ifAllGranted roles="ROLE_MESA_CONTROL">
            	<li><g:link class="create" action="start"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </sec:ifAllGranted>
			</ul>
			</div>
        <div class="body">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                            <g:sortableColumn property="id" title="${message(code: 'prestamo.id.label', default: 'Id')}" />
                        
                            <th><g:message code="prestamo.cliente.label" default="Cliente" /></th>
                        
                            <g:sortableColumn property="folioSolicitud" title="${message(code: 'prestamo.folioSolicitud.label', default: 'Folio Solicitud')}" />
                        
                            <th><g:message code="prestamo.promocion.label" default="Promocion" /></th>
                        
                            <th><g:message code="prestamo.estatusSolicitud.label" default="Estatus" /></th>
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${prestamoInstanceList}" status="i" var="prestamoInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${prestamoInstance.id}">${fieldValue(bean: prestamoInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: prestamoInstance, field: "cliente")}</td>
                        
                            <td>${fieldValue(bean: prestamoInstance, field: "folioSolicitud")}</td>
                        
                            <td>${fieldValue(bean: prestamoInstance, field: "promocion")}</td>
                        
                            <td>
                                <g:link action="editCambioEstatus" id="${prestamoInstance.id}">
                                ${fieldValue(bean: prestamoInstance, field: "estatusSolicitud")}</g:link>
                            </td>

                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="pagination">
                <g:paginate total="${prestamoInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
