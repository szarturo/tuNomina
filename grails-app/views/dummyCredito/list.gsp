
<%@ page import="test.DummyCredito" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'dummyCredito.label', default: 'DummyCredito')}" />
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'dummyCredito.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="nombre" title="${message(code: 'dummyCredito.nombre.label', default: 'Nombre')}" />
                        
                            <g:sortableColumn property="ingresos" title="${message(code: 'dummyCredito.ingresos.label', default: 'Ingresos')}" />
                        
                            <g:sortableColumn property="montoPrestamo" title="${message(code: 'dummyCredito.montoPrestamo.label', default: 'Monto Prestamo')}" />
                        
                            <g:sortableColumn property="status" title="${message(code: 'dummyCredito.status.label', default: 'Status')}" />
                        
                            <g:sortableColumn property="creditoOk" title="${message(code: 'dummyCredito.creditoOk.label', default: 'Credito Ok')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${dummyCreditoInstanceList}" status="i" var="dummyCreditoInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${dummyCreditoInstance.id}">${fieldValue(bean: dummyCreditoInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: dummyCreditoInstance, field: "nombre")}</td>
                        
                            <td>${fieldValue(bean: dummyCreditoInstance, field: "ingresos")}</td>
                        
                            <td>${fieldValue(bean: dummyCreditoInstance, field: "montoPrestamo")}</td>
                        
                            <td>${fieldValue(bean: dummyCreditoInstance, field: "status")}</td>
                        
                            <td><g:formatBoolean boolean="${dummyCreditoInstance.creditoOk}" /></td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${dummyCreditoInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
