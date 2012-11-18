
<%@ page import="com.sim.call.CallCenter" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'callCenter.label', default: 'CallCenter')}" />
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
                            <td valign="top" class="name"><g:message code="callCenter.id.label" default="Id" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: callCenterInstance, field: "id")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="callCenter.prestamo.label" default="Prestamo" /></td>
                            
                            <td valign="top" class="value"><g:link controller="prestamo" action="show" id="${callCenterInstance?.prestamo?.id}">${callCenterInstance?.prestamo?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="callCenter.fechaLlamada.label" default="Fecha Llamada" /></td>
                            
                            <td valign="top" class="value"><g:formatDate date="${callCenterInstance?.fechaLlamada}" /></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="callCenter.prestamoCobrado.label" default="Prestamo Cobrado" /></td>
                            
                            <td valign="top" class="value"><g:formatBoolean boolean="${callCenterInstance?.prestamoCobrado}" /></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="callCenter.comentarios.label" default="Comentarios" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: callCenterInstance, field: "comentarios")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="callCenter.cerrarRegistro.label" default="Cerrar Registro" /></td>
                            
                            <td valign="top" class="value"><g:formatBoolean boolean="${callCenterInstance?.cerrarRegistro}" /></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="callCenter.seContacto.label" default="Se Contacto" /></td>
                            
                            <td valign="top" class="value"><g:formatBoolean boolean="${callCenterInstance?.seContacto}" /></td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <g:if test="${!params.complete && params.taskId}">
            <div class="buttons">
                <g:form>
                    <g:hiddenField name="id" value="${callCenterInstance?.id}" />
                    <g:hiddenField name="taskId" value="${params.taskId}" />
                    <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
 
                </g:form>
            </div>
            </g:if>
        </div>
    </body>
</html>
