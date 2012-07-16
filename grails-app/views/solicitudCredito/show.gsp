
<%@ page import="com.sim.procesos.credito.SolicitudCredito" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'solicitudCredito.label', default: 'SolicitudCredito')}" />
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
                            <td valign="top" class="name"><g:message code="solicitudCredito.id.label" default="Id" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: solicitudCreditoInstance, field: "id")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="solicitudCredito.nombreSolicitante.label" default="Nombre Solicitante" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: solicitudCreditoInstance, field: "nombreSolicitante")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="solicitudCredito.cantidadSolicitada.label" default="Cantidad Solicitada" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: solicitudCreditoInstance, field: "cantidadSolicitada")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="solicitudCredito.motivo.label" default="Motivo" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: solicitudCreditoInstance, field: "motivo")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="solicitudCredito.approvalStatus.label" default="Approval Status" /></td>
                            
                            <td valign="top" class="value">${solicitudCreditoInstance?.approvalStatus?.encodeAsHTML()}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="solicitudCredito.approvalRemark.label" default="Approval Remark" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: solicitudCreditoInstance, field: "approvalRemark")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="solicitudCredito.reenviarSolicitud.label" default="Reenviar Solicitud" /></td>
                            
                            <td valign="top" class="value"><g:formatBoolean boolean="${solicitudCreditoInstance?.reenviarSolicitud}" /></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="solicitudCredito.fechaSolicitud.label" default="Fecha Solicitud" /></td>
                            
                            <td valign="top" class="value"><g:formatDate date="${solicitudCreditoInstance?.fechaSolicitud}" /></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="solicitudCredito.fechaActualizacion.label" default="Fecha Actualizacion" /></td>
                            
                            <td valign="top" class="value"><g:formatDate date="${solicitudCreditoInstance?.fechaActualizacion}" /></td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <g:if test="${!params.complete && params.taskId}">
            <div class="buttons">
                <g:form>
                    <g:hiddenField name="id" value="${solicitudCreditoInstance?.id}" />
                    <g:hiddenField name="taskId" value="${params.taskId}" />
                    <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </g:form>
            </div>
            </g:if>
        </div>
    </body>
</html>
