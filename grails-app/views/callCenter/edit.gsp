

<%@ page import="com.sim.call.CallCenter" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'callCenter.label', default: 'CallCenter')}" />
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
            <g:hasErrors bean="${callCenterInstance}">
            <div class="errors">
                <g:renderErrors bean="${callCenterInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${callCenterInstance?.id}" />
                <g:hiddenField name="version" value="${callCenterInstance?.version}" />
                <g:hiddenField name="taskId" value="${params.taskId}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="prestamo"><g:message code="callCenter.prestamo.label" default="Prestamo" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: callCenterInstance, field: 'prestamo', 'errors')}">
                                    <g:select name="prestamo.id" from="${com.sim.credito.Prestamo.list()}" optionKey="id" value="${callCenterInstance?.prestamo?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="fechaLlamada"><g:message code="callCenter.fechaLlamada.label" default="Fecha Llamada" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: callCenterInstance, field: 'fechaLlamada', 'errors')}">
                                    <g:datePicker name="fechaLlamada" precision="day" value="${callCenterInstance?.fechaLlamada}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="prestamoCobrado"><g:message code="callCenter.prestamoCobrado.label" default="Prestamo Cobrado" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: callCenterInstance, field: 'prestamoCobrado', 'errors')}">
                                    <g:checkBox name="prestamoCobrado" value="${callCenterInstance?.prestamoCobrado}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="comentarios"><g:message code="callCenter.comentarios.label" default="Comentarios" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: callCenterInstance, field: 'comentarios', 'errors')}">
                                    <g:textArea name="comentarios" cols="40" rows="5" value="${callCenterInstance?.comentarios}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="prestamoCancelado"><g:message code="callCenter.prestamoCancelado.label" default="Prestamo Cancelado" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: callCenterInstance, field: 'prestamoCancelado', 'errors')}">
                                    <g:checkBox name="prestamoCancelado" value="${callCenterInstance?.prestamoCancelado}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="seContacto"><g:message code="callCenter.seContacto.label" default="Se Contacto" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: callCenterInstance, field: 'seContacto', 'errors')}">
                                    <g:checkBox name="seContacto" value="${callCenterInstance?.seContacto}" />
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
