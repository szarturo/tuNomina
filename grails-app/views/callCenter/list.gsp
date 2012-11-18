
<%@ page import="com.sim.call.CallCenter" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'callCenter.label', default: 'CallCenter')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
			<div class="nav" role="navigation">
			 <ul> 
            <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
	          <li><g:link class="list" controller="task" action="myTaskList"><g:message code="myTasks.label" default="My Tasks ({0})" args="[myTasksCount]" /></g:link></li>
            <li><g:link class="create" action="start"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'callCenter.id.label', default: 'Id')}" />
                        
                            <th><g:message code="callCenter.prestamo.label" default="Prestamo" /></th>
                        
                            <g:sortableColumn property="fechaLlamada" title="${message(code: 'callCenter.fechaLlamada.label', default: 'Fecha Llamada')}" />
                        
                            <g:sortableColumn property="prestamoCobrado" title="${message(code: 'callCenter.prestamoCobrado.label', default: 'Prestamo Cobrado')}" />
                        
                            <g:sortableColumn property="comentarios" title="${message(code: 'callCenter.comentarios.label', default: 'Comentarios')}" />
                        
                            <g:sortableColumn property="cerrarRegistro" title="${message(code: 'callCenter.cerrarRegistro.label', default: 'Cerrar Registro')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${callCenterInstanceList}" status="i" var="callCenterInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${callCenterInstance.id}">${fieldValue(bean: callCenterInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: callCenterInstance, field: "prestamo")}</td>
                        
                            <td><g:formatDate date="${callCenterInstance.fechaLlamada}" /></td>
                        
                            <td><g:formatBoolean boolean="${callCenterInstance.prestamoCobrado}" /></td>
                        
                            <td>${fieldValue(bean: callCenterInstance, field: "comentarios")}</td>
                        
                            <td><g:formatBoolean boolean="${callCenterInstance.cerrarRegistro}" /></td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${callCenterInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
