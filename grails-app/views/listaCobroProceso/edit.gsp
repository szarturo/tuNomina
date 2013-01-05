

<%@ page import="com.sim.listacobro.ListaCobroProceso" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'listaCobroProceso.label', default: 'ListaCobroProceso')}" />
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
            <g:hasErrors bean="${listaCobroProcesoInstance}">
            <div class="errors">
                <g:renderErrors bean="${listaCobroProcesoInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${listaCobroProcesoInstance?.id}" />
                <g:hiddenField name="version" value="${listaCobroProcesoInstance?.version}" />
                <g:hiddenField name="taskId" value="${params.taskId}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="listaCobro"><g:message code="listaCobroProceso.listaCobro.label" default="Lista Cobro" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: listaCobroProcesoInstance, field: 'listaCobro', 'errors')}">
                                    <g:select name="listaCobro.id" from="${com.sim.listacobro.ListaCobro.list()}" optionKey="id" value="${listaCobroProcesoInstance?.listaCobro?.id}"  />
                                </td>
                            </tr>
                        
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
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="estatusListaCobro"><g:message code="listaCobroProceso.estatusListaCobro.label" default="Estatus Lista Cobro" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: listaCobroProcesoInstance, field: 'estatusListaCobro', 'errors')}">
                                    <g:select name="estatusListaCobro.id" from="${com.sim.catalogo.SimCatListaCobroEstatus.list()}" optionKey="id" value="${listaCobroProcesoInstance?.estatusListaCobro?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="usuario"><g:message code="listaCobroProceso.usuario.label" default="Usuario" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: listaCobroProcesoInstance, field: 'usuario', 'errors')}">
                                    <g:select name="usuario.id" from="${com.sim.usuario.Usuario.list()}" optionKey="id" value="${listaCobroProcesoInstance?.usuario?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="fechaMedio"><g:message code="listaCobroProceso.fechaMedio.label" default="Fecha Medio" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: listaCobroProcesoInstance, field: 'fechaMedio', 'errors')}">
                                    <g:datePicker name="fechaMedio" precision="day" value="${listaCobroProcesoInstance?.fechaMedio}"  />
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
