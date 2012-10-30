

<%@ page import="test.DummyCredito" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'dummyCredito.label', default: 'DummyCredito')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
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
            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${dummyCreditoInstance}">
            <div class="errors">
                <g:renderErrors bean="${dummyCreditoInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${dummyCreditoInstance?.id}" />
                <g:hiddenField name="version" value="${dummyCreditoInstance?.version}" />
                <g:hiddenField name="taskId" value="${params.taskId}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="nombre"><g:message code="dummyCredito.nombre.label" default="Nombre" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: dummyCreditoInstance, field: 'nombre', 'errors')}">
                                    <g:textField name="nombre" value="${dummyCreditoInstance?.nombre}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="ingresos"><g:message code="dummyCredito.ingresos.label" default="Ingresos" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: dummyCreditoInstance, field: 'ingresos', 'errors')}">
                                    <g:textField name="ingresos" value="${fieldValue(bean: dummyCreditoInstance, field: 'ingresos')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="montoPrestamo"><g:message code="dummyCredito.montoPrestamo.label" default="Monto Prestamo" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: dummyCreditoInstance, field: 'montoPrestamo', 'errors')}">
                                    <g:textField name="montoPrestamo" value="${fieldValue(bean: dummyCreditoInstance, field: 'montoPrestamo')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="status"><g:message code="dummyCredito.status.label" default="Status" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: dummyCreditoInstance, field: 'status', 'errors')}">
                                    <g:textField name="status" value="${dummyCreditoInstance?.status}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="creditoOk"><g:message code="dummyCredito.creditoOk.label" default="Credito Ok" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: dummyCreditoInstance, field: 'creditoOk', 'errors')}">
                                    <g:checkBox name="creditoOk" value="${dummyCreditoInstance?.creditoOk}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="idCliente"><g:message code="dummyCredito.idCliente.label" default="Id Cliente" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: dummyCreditoInstance, field: 'idCliente', 'errors')}">
                                    <g:textField name="idCliente" value="${dummyCreditoInstance?.idCliente}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="idCredito"><g:message code="dummyCredito.idCredito.label" default="Id Credito" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: dummyCreditoInstance, field: 'idCredito', 'errors')}">
                                    <g:textField name="idCredito" value="${dummyCreditoInstance?.idCredito}" />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" /></span>
                    <span class="button"><g:actionSubmit class="save" action="update" value="${message(code: 'default.button.complete.label', default: 'Complete')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
