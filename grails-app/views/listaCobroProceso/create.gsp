

<%@ page import="com.sim.listacobro.ListaCobroProceso" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'listaCobroProceso.label', default: 'ListaCobroProceso')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
			<div class="nav" role="navigation">
			 <ul>   
          <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
	        <li><g:link class="list" controller="task" action="myTaskList"><g:message code="myTasks.label" default="My Tasks ({0})" args="[myTasksCount]" /></g:link></li>
          <li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
			  </ul>
			</div>
        <div class="body">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${listaCobroProcesoInstance}">
            <div class="errors">
                <g:renderErrors bean="${listaCobroProcesoInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                        <g:if
                            test="${existeListaCobro == false}">                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="listaCobro"><g:message code="listaCobroProceso.listaCobro.label" default="Lista Cobro" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: listaCobroProcesoInstance, field: 'listaCobro', 'errors')}">
                                    <g:select name="listaCobro.id" from="${com.sim.listacobro.ListaCobro.list()}" optionKey="id" value="${listaCobroProcesoInstance?.listaCobro?.id}"  />
                                </td>
                            </tr>

                        </g:if>

                        <g:if
                            test="${existeListaCobro == true}">

                            <tr class="prop">
                                <td valign="top" class="name"><g:message code="listaCobroProceso.listaCobro.label" default="Lista Cobro" /></td>
                                
                                <td valign="top" class="value"><g:link controller="listaCobro" action="show" id="${listaCobroProcesoInstance?.listaCobro?.id}">${listaCobroProcesoInstance?.listaCobro?.encodeAsHTML()}</g:link></td>
                            </tr>

                            <g:hiddenField name="listaCobro.id" value="${listaCobroProcesoInstance?.listaCobro?.id}" />

                        </g:if>                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="fechaAplicacion"><g:message code="listaCobroProceso.fechaAplicacion.label" default="Fecha Aplicacion" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: listaCobroProcesoInstance, field: 'fechaAplicacion', 'errors')}">
                                    <g:datePicker name="fechaAplicacion" precision="day" value="${listaCobroProcesoInstance?.fechaAplicacion}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="comentarios"><g:message code="listaCobroProceso.comentarios.label" default="Comentarios" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: listaCobroProcesoInstance, field: 'comentarios', 'errors')}">
                                    <g:textField name="comentarios" value="${listaCobroProcesoInstance?.comentarios}" />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" /></span>
                    <span class="button"><g:submitButton name="complete" class="save" value="${message(code: 'default.button.complete.label', default: 'Complete')}" /></span>
                </div>
                <g:hiddenField name="taskId" value="${params.taskId}" />
                 <g:hiddenField name="estatusListaCobro.id" value="${listaCobroProcesoInstance?.estatusListaCobro?.id}" />

            </g:form>
        </div>
    </body>
</html>
