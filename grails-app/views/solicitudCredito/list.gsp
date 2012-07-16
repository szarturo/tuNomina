
<%@ page import="com.sim.procesos.credito.SolicitudCredito" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'solicitudCredito.label', default: 'SolicitudCredito')}" />
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'solicitudCredito.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="nombreSolicitante" title="${message(code: 'solicitudCredito.nombreSolicitante.label', default: 'Nombre Solicitante')}" />
                        
                            <g:sortableColumn property="cantidadSolicitada" title="${message(code: 'solicitudCredito.cantidadSolicitada.label', default: 'Cantidad Solicitada')}" />
                        
                            <g:sortableColumn property="motivo" title="${message(code: 'solicitudCredito.motivo.label', default: 'Motivo')}" />
                        
                            <g:sortableColumn property="approvalStatus" title="${message(code: 'solicitudCredito.approvalStatus.label', default: 'Approval Status')}" />
                        
                            <g:sortableColumn property="approvalRemark" title="${message(code: 'solicitudCredito.approvalRemark.label', default: 'Approval Remark')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${solicitudCreditoInstanceList}" status="i" var="solicitudCreditoInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${solicitudCreditoInstance.id}">${fieldValue(bean: solicitudCreditoInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: solicitudCreditoInstance, field: "nombreSolicitante")}</td>
                        
                            <td>${fieldValue(bean: solicitudCreditoInstance, field: "cantidadSolicitada")}</td>
                        
                            <td>${fieldValue(bean: solicitudCreditoInstance, field: "motivo")}</td>
                        
                            <td>${fieldValue(bean: solicitudCreditoInstance, field: "approvalStatus")}</td>
                        
                            <td>${fieldValue(bean: solicitudCreditoInstance, field: "approvalRemark")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${solicitudCreditoInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
