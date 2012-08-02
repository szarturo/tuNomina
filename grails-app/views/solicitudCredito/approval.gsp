
<%@ page import="com.sim.procesos.credito.SolicitudCredito" %>
<%@ page import="org.grails.activiti.ApprovalStatus" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'solicitudCredito.label', default: 'SolicitudCredito')}" />
        <title><g:message code="default.approve.label" args="[entityName]" default="Approve {0}" /></title>
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
            <h1><g:message code="default.approve.label" args="[entityName]" default="Approve {0}"/></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:form action="performApproval" >
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
                            <td valign="top" class="name"><g:message code="solicitudCredito.motivo.label" default="Descripcion Motivo" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: solicitudCreditoInstance, field: "motivo")}</td>
                            
                        </tr>
                    
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="approvalStatus"><g:message code="solicitudCredito.approvalStatus.label" default="Approval Status" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: solicitudCreditoInstance, field: 'approvalStatus', 'errors')}">
                                    <g:select name="approvalStatus" from="${[ApprovalStatus.APPROVED, ApprovalStatus.REJECTED]}" value="${solicitudCreditoInstance?.approvalStatus}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="approvalRemark"><g:message code="solicitudCredito.approvalRemark.label" default="Approval Remark" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: solicitudCreditoInstance, field: 'approvalRemark', 'errors')}">
                                    <g:textArea name="approvalRemark" cols="40" rows="5" value="${solicitudCreditoInstance?.approvalRemark}" />
                                </td>
                            </tr>
                    
                    </tbody>
                </table>
            </div>
            		<div class="buttons">
                    <span class="button"><g:submitButton name="save" class="save" value="${message(code: 'default.button.save.label', default: 'Save')}" /></span>
                    <span class="button"><g:submitButton name="complete" class="save" value="${message(code: 'default.button.complete.label', default: 'Complete')}" /></span>
                </div>
                <g:hiddenField name="id" value="${solicitudCreditoInstance?.id}" />
                <g:hiddenField name="taskId" value="${params.taskId}" />
            </g:form>            
        </div>
    </body>
</html>
