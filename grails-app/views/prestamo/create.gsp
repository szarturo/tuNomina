<%@ page import="com.sim.credito.Prestamo" %>
<g:javascript library="prototype" />

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'prestamo.label', default: 'Prestamo')}" />
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
            <g:hasErrors bean="${prestamoInstance}">
            <div class="errors">
                <g:renderErrors bean="${prestamoInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="cliente"><g:message code="prestamo.cliente.label" default="Cliente" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: prestamoInstance, field: 'cliente', 'errors')}">
                                    <g:select name="cliente.id" from="${com.sim.cliente.RsCliente.list()}" optionKey="id" value="${prestamoInstance?.cliente?.id}"  />
                                </td>
                            </tr>

                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="correoSolicitante"><g:message code="prestamo.correoSolicitante.label" default="Correo Solicitante" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: prestamoInstance, field: 'correoSolicitante', 'errors')}">
                                    <g:textField name="correoSolicitante" value="${prestamoInstance?.correoSolicitante}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="folioSolicitud"><g:message code="prestamo.folioSolicitud.label" default="Folio Solicitud" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: prestamoInstance, field: 'folioSolicitud', 'errors')}">
                                    <g:textField name="folioSolicitud" value="${fieldValue(bean: prestamoInstance, field: 'folioSolicitud')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="promocion"><g:message code="prestamo.promocion.label" default="Promocion" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: prestamoInstance, field: 'promocion', 'errors')}">
                                    <g:select name="promocion.id" from="${com.sim.producto.ProPromocion.list()}" optionKey="id" value="${prestamoInstance?.promocion?.id}"  />
                                </td>
                            </tr>

             <tr class="prop">
                <td valign="top" class="name">
                    <label for="dependencia"><g:message code="prestamo.dependencia.label" default="Dependencia" /></label>
                </td>
                <td valign="top" class="value ${hasErrors(bean: prestamoInstance, field: 'dependencia', 'errors')}">
                    <g:select 
                        name="dependencia.id" 
                        from="${com.sim.entidad.EntDependencia.list()}" optionKey="id" 
                        value="${prestamoInstance?.dependencia?.id}" 
                        onchange="${remoteFunction(
                            controller:'entDependencia', 
                            action:'ajaxGetTipoEmpleado', 
                            params:'\'id=\' + escape(this.value)', 
                            onComplete:'updateTipoEmpleado(e)')}"/>
                </td>
            </tr>
        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="tipoEmpleadoDep"><g:message code="prestamo.tipoEmpleadoDep.label" default="Tipo Empleado Dependencia" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: prestamoInstance, field: 'tipoEmpleadoDep', 'errors')}">
                                    <g:select name="tipoEmpleadoDep.id" from="${com.sim.catalogo.SimCatTipoEmp.list()}" optionKey="id" 
                                    value="${prestamoInstance?.tipoEmpleadoDep?.id}"  />
                                </td>
                            </tr>

                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="sucursal"><g:message code="prestamo.sucursal.label" default="Sucursal" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: prestamoInstance, field: 'sucursal', 'errors')}">
                                    <g:select name="sucursal.id" from="${com.sim.entidad.EntSucursal.list()}" optionKey="id" value="${prestamoInstance?.sucursal?.id}" noSelection="['null': '']" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="delegacion"><g:message code="prestamo.delegacion.label" default="Delegacion" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: prestamoInstance, field: 'delegacion', 'errors')}">
                                    <g:select name="delegacion.id" from="${com.sim.entidad.EntDelegacion.list()}" optionKey="id" value="${prestamoInstance?.delegacion?.id}" noSelection="['null': '']" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="vendedor"><g:message code="prestamo.vendedor.label" default="Vendedor" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: prestamoInstance, field: 'vendedor', 'errors')}">
                                    <g:select name="vendedor.id" from="${com.sim.empresa.EmpEmpleado.list()}" optionKey="id" value="${prestamoInstance?.vendedor?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="fechaSolicitud"><g:message code="prestamo.fechaSolicitud.label" default="Fecha Solicitud" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: prestamoInstance, field: 'fechaSolicitud', 'errors')}">
                                    <g:datePicker name="fechaSolicitud" precision="day" value="${prestamoInstance?.fechaSolicitud}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="montoSolicitado"><g:message code="prestamo.montoSolicitado.label" default="Monto Solicitado" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: prestamoInstance, field: 'montoSolicitado', 'errors')}">
                                    <g:textField name="montoSolicitado" value="${fieldValue(bean: prestamoInstance, field: 'montoSolicitado')}" />
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
                            
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="comentarios"><g:message code="prestamo.comentarios.label" default="Comentarios" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: prestamoInstance, field: 'comentarios', 'errors')}">
                                    <g:textArea name="comentarios" cols="40" rows="5" value="${prestamoInstance?.comentarios}" />
                                </td>
                            </tr>
                       
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" /></span>
                </div>
                <g:hiddenField name="taskId" value="${params.taskId}" />
            </g:form>
        </div>
    </body>
</html>

<g:javascript>
    function updateTipoEmpleado(e) {
        // The response comes back as a bunch-o-JSON
        var tipoEmpleado = eval("(" + e.responseText + ")")   
        // evaluate JSON

        if (tipoEmpleado) {
            var rselect = document.getElementById('tipoEmpleadoDep.id')

            // Clear all previous options
            var l = rselect.length

            while (l > 0) {
                l--
                rselect.remove(l)
            }

            // Rebuild the select
            for (var i=0; i < tipoEmpleado.length; i++) {
                var tipoEmpleadoDependencia = tipoEmpleado[i]
                var opt = document.createElement('option');
                opt.text = tipoEmpleadoDependencia.nombreTipoEmpleadoDep
                opt.value = tipoEmpleadoDependencia.id
                try {
                    rselect.add(opt, null) // standards compliant; doesn't work in IE
                }
                catch(ex) {
                    rselect.add(opt) // IE only
                }
            }
        }
    }

    
    // This is called when the page loads to initialize dependencia
    var zselect = document.getElementById('dependencia.id')
    var zopt = zselect.options[zselect.selectedIndex]
    ${remoteFunction(controller:"entDependencia", action:"ajaxGetTipoEmpleado", params:"'id=' + zopt.value", onComplete:"updateTipoEmpleado(e)")}

</g:javascript>
