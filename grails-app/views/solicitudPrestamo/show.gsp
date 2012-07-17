
<%@ page import="com.sim.procesos.credito.SolicitudPrestamo" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'solicitudPrestamo.label', default: 'SolicitudPrestamo')}" />
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
                            <td valign="top" class="name"><g:message code="solicitudPrestamo.id.label" default="Id" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: solicitudPrestamoInstance, field: "id")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="solicitudPrestamo.nombreSolicitante.label" default="Nombre Solicitante" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: solicitudPrestamoInstance, field: "nombreSolicitante")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="solicitudPrestamo.correoSolicitante.label" default="Correo Solicitante" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: solicitudPrestamoInstance, field: "correoSolicitante")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="solicitudPrestamo.sueldoMensual.label" default="Sueldo Mensual" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: solicitudPrestamoInstance, field: "sueldoMensual")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="solicitudPrestamo.prestamo.label" default="Prestamo" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: solicitudPrestamoInstance, field: "prestamo")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="solicitudPrestamo.prestamoAutorizado.label" default="Prestamo Autorizado" /></td>
                            
                            <td valign="top" class="value"><g:formatBoolean boolean="${solicitudPrestamoInstance?.prestamoAutorizado}" /></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="solicitudPrestamo.explicacionSolicitud.label" default="Explicacion Solicitud" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: solicitudPrestamoInstance, field: "explicacionSolicitud")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="solicitudPrestamo.explicacionCredito.label" default="Explicacion Credito" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: solicitudPrestamoInstance, field: "explicacionCredito")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="solicitudPrestamo.approvalStatus.label" default="Approval Status" /></td>
                            
                            <td valign="top" class="value">${solicitudPrestamoInstance?.approvalStatus?.encodeAsHTML()}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="solicitudPrestamo.dateCreated.label" default="Date Created" /></td>
                            
                            <td valign="top" class="value"><g:formatDate date="${solicitudPrestamoInstance?.dateCreated}" /></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="solicitudPrestamo.lastUpdated.label" default="Last Updated" /></td>
                            
                            <td valign="top" class="value"><g:formatDate date="${solicitudPrestamoInstance?.lastUpdated}" /></td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <g:if test="${!params.complete && params.taskId}">
            <div class="buttons">
                <g:form>
                    <g:hiddenField name="id" value="${solicitudPrestamoInstance?.id}" />
                    <g:hiddenField name="taskId" value="${params.taskId}" />
                    <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </g:form>
            </div>
            </g:if>
        </div>
    </body>
</html>
