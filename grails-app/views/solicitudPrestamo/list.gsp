
<%@ page import="com.sim.procesos.credito.SolicitudPrestamo" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'solicitudPrestamo.label', default: 'SolicitudPrestamo')}" />
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'solicitudPrestamo.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="nombreSolicitante" title="${message(code: 'solicitudPrestamo.nombreSolicitante.label', default: 'Nombre Solicitante')}" />
                        
                            <g:sortableColumn property="correoSolicitante" title="${message(code: 'solicitudPrestamo.correoSolicitante.label', default: 'Correo Solicitante')}" />
                        
                            <g:sortableColumn property="sueldoMensual" title="${message(code: 'solicitudPrestamo.sueldoMensual.label', default: 'Sueldo Mensual')}" />
                        
                            <g:sortableColumn property="prestamo" title="${message(code: 'solicitudPrestamo.prestamo.label', default: 'Prestamo')}" />
                        
                            <g:sortableColumn property="prestamoAutorizado" title="${message(code: 'solicitudPrestamo.prestamoAutorizado.label', default: 'Prestamo Autorizado')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${solicitudPrestamoInstanceList}" status="i" var="solicitudPrestamoInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${solicitudPrestamoInstance.id}">${fieldValue(bean: solicitudPrestamoInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: solicitudPrestamoInstance, field: "nombreSolicitante")}</td>
                        
                            <td>${fieldValue(bean: solicitudPrestamoInstance, field: "correoSolicitante")}</td>
                        
                            <td>${fieldValue(bean: solicitudPrestamoInstance, field: "sueldoMensual")}</td>
                        
                            <td>${fieldValue(bean: solicitudPrestamoInstance, field: "prestamo")}</td>
                        
                            <td><g:formatBoolean boolean="${solicitudPrestamoInstance.prestamoAutorizado}" /></td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${solicitudPrestamoInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
