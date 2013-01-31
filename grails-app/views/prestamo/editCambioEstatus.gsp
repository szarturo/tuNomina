

<%@ page import="com.sim.credito.Prestamo" %>
<%@ page import="com.sim.credito.PrestamoEstatus" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'prestamo.label', default: 'Prestamo')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
    
		<div class="nav" role="navigation">
		<ul> 
            <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        </ul>
		</div>

        <div class="body">
            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${prestamoInstance}">
            <div class="errors">
                <g:renderErrors bean="${prestamoInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${prestamoInstance?.id}" />
                <g:hiddenField name="version" value="${prestamoInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>

                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="estatusSolicitud"><g:message code="prestamo.estatusSolicitud.label" default="Estatus de Solicitud" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: prestamoInstance, field: 'estatusSolicitud', 'errors')}">
                                    <g:select name="estatusSolicitud" from="${com.sim.credito.PrestamoEstatus}" value="${prestamoInstance?.estatusSolicitud}"  />
                                </td>
                            </tr>

                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="formaDeDispercion"><g:message code="prestamo.formaDeDispercion.label" default="Forma De Dispercion" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: prestamoInstance, field: 'formaDeDispercion', 'errors')}">
                                    <g:select name="formaDeDispercion.id" from="${com.sim.catalogo.SimCatFormaEntrega.list()}" optionKey="id" value="${prestamoInstance?.formaDeDispercion?.id}"  />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" action="updateEstatus" value="${message(code: 'default.button.update.label', default: 'Update')}" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>