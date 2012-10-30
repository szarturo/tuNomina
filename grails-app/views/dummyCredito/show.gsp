
<%@ page import="test.DummyCredito" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'dummyCredito.label', default: 'DummyCredito')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
			<div class="nav" role="navigation">
			 <ul> 
            <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
	          <li><g:link class="list" controller="task" action="myTaskList"><g:message code="myTasks.label" default="My Tasks ({0})" args="[myTasksCount]" /></g:link></li>
            <li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
            <li><g:link class="create" action="start"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			  </ul>
			</div>
        <div class="body">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="dummyCredito.id.label" default="Id" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: dummyCreditoInstance, field: "id")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="dummyCredito.nombre.label" default="Nombre" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: dummyCreditoInstance, field: "nombre")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="dummyCredito.ingresos.label" default="Ingresos" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: dummyCreditoInstance, field: "ingresos")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="dummyCredito.montoPrestamo.label" default="Monto Prestamo" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: dummyCreditoInstance, field: "montoPrestamo")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="dummyCredito.status.label" default="Status" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: dummyCreditoInstance, field: "status")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="dummyCredito.creditoOk.label" default="Credito Ok" /></td>
                            
                            <td valign="top" class="value"><g:formatBoolean boolean="${dummyCreditoInstance?.creditoOk}" /></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="dummyCredito.idCliente.label" default="Id Cliente" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: dummyCreditoInstance, field: "idCliente")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="dummyCredito.idCredito.label" default="Id Credito" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: dummyCreditoInstance, field: "idCredito")}</td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <g:if test="${!params.complete && params.taskId}">
            <div class="buttons">
                <g:form>
                    <g:hiddenField name="id" value="${dummyCreditoInstance?.id}" />
                    <g:hiddenField name="taskId" value="${params.taskId}" />
                    <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </g:form>
            </div>
            </g:if>
        </div>
    </body>
</html>
