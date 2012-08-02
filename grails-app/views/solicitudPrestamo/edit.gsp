

<%@ page import="com.sim.procesos.credito.SolicitudPrestamo" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'solicitudPrestamo.label', default: 'SolicitudPrestamo')}" />
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
            <g:hasErrors bean="${solicitudPrestamoInstance}">
            <div class="errors">
                <g:renderErrors bean="${solicitudPrestamoInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${solicitudPrestamoInstance?.id}" />
                <g:hiddenField name="version" value="${solicitudPrestamoInstance?.version}" />
                <g:hiddenField name="taskId" value="${params.taskId}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="nombreSolicitante"><g:message code="solicitudPrestamo.nombreSolicitante.label" default="Nombre Solicitante" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: solicitudPrestamoInstance, field: 'nombreSolicitante', 'errors')}">
                                    <g:textField name="nombreSolicitante" maxlength="50" value="${solicitudPrestamoInstance?.nombreSolicitante}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="correoSolicitante"><g:message code="solicitudPrestamo.correoSolicitante.label" default="Correo Solicitante" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: solicitudPrestamoInstance, field: 'correoSolicitante', 'errors')}">
                                    <g:textField name="correoSolicitante" value="${solicitudPrestamoInstance?.correoSolicitante}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="sueldoMensual"><g:message code="solicitudPrestamo.sueldoMensual.label" default="Sueldo Mensual" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: solicitudPrestamoInstance, field: 'sueldoMensual', 'errors')}">
                                    <g:textField name="sueldoMensual" value="${fieldValue(bean: solicitudPrestamoInstance, field: 'sueldoMensual')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="prestamo"><g:message code="solicitudPrestamo.prestamo.label" default="Prestamo" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: solicitudPrestamoInstance, field: 'prestamo', 'errors')}">
                                    <g:textField name="prestamo" value="${fieldValue(bean: solicitudPrestamoInstance, field: 'prestamo')}" />
                                </td>
                            </tr>
                            
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="explicacionSolicitud"><g:message code="solicitudPrestamo.explicacionSolicitud.label" default="Explicacion Solicitud" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: solicitudPrestamoInstance, field: 'explicacionSolicitud', 'errors')}">
                                    <g:textArea name="explicacionSolicitud" cols="40" rows="5" value="${solicitudPrestamoInstance?.explicacionSolicitud}" />
                                </td>
                            </tr>
                                                        
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="prestamoAutorizado"><g:message code="solicitudPrestamo.prestamoAutorizado.label" default="Prestamo Autorizado" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: solicitudPrestamoInstance, field: 'prestamoAutorizado', 'errors')}">
                                    <g:checkBox name="prestamoAutorizado" value="${solicitudPrestamoInstance?.prestamoAutorizado}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="explicacionCredito"><g:message code="solicitudPrestamo.explicacionCredito.label" default="Explicacion Credito" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: solicitudPrestamoInstance, field: 'explicacionCredito', 'errors')}">
                                    <g:textArea name="explicacionCredito" cols="40" rows="5" value="${solicitudPrestamoInstance?.explicacionCredito}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="approvalStatus"><g:message code="solicitudPrestamo.approvalStatus.label" default="Approval Status" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: solicitudPrestamoInstance, field: 'approvalStatus', 'errors')}">
                                    <g:select name="approvalStatus" from="${org.grails.activiti.ApprovalStatus?.values()}" value="${solicitudPrestamoInstance?.approvalStatus}"  />
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
