<%@ page import="com.sim.credito.Prestamo" %>
<%@ page import="org.grails.activiti.ApprovalStatus"%>
<g:javascript library="prototype" />

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <g:if test="${!params.showMenu.equals('false')}">
	        <meta name="layout" content="main" />
        </g:if>
        <g:set var="entityName" value="${message(code: 'prestamo.label', default: 'Prestamo')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
    
    	<g:if test="${!params.showMenu.equals('false')}">
			<div class="nav" role="navigation">
			 <ul> 
            <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
	          <li><g:link class="list" controller="task" action="myTaskList"><g:message code="myTasks.label" default="My Tasks ({0})" args="[myTasksCount]" /></g:link></li>
            <li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
			  </ul>
			</div>
		</g:if>	
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
                <g:hiddenField name="taskId" value="${params.taskId}" />
                <g:hiddenField name="estatusSolicitud.id" value="${prestamoInstance?.estatusSolicitud?.id}" />
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
                    <label for="dependencia"><g:message code="prestamo.dependencia.label" default="Dependencia" /></label>
                </td>
                <td valign="top" class="value ${hasErrors(bean: prestamoInstance, field: 'dependencia', 'errors')}">
                    <g:select 
                        name="dependencia.id" 
                        from="${com.sim.entidad.EntDependencia.list()}" optionKey="id" 
                        value="${prestamoInstance?.dependencia?.id}" 
                        onchange="cambiaDependencia(escape(this.value))"/>
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
                                  <label for="promocion"><g:message code="prestamo.promocion.label" default="Promocion" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: prestamoInstance, field: 'promocion', 'errors')}">
                                    <g:select name="promocion.id" from="${com.sim.producto.ProPromocion.list()}" optionKey="id" value="${prestamoInstance?.promocion?.id}"  />
                                </td>
                            </tr>                            

                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="sucursal"><g:message code="prestamo.sucursal.label" default="Sucursal" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: prestamoInstance, field: 'sucursal', 'errors')}">
                                    <g:select 
                                    name="sucursal.id" 
                                    from="${com.sim.entidad.EntSucursal.list()}" optionKey="id" 
                                        value="${prestamoInstance?.sucursal?.id}" 
                                        onchange="${remoteFunction(
                                        controller:'entSucursal', 
                                        action:'ajaxGetDelegacion', 
                                        params:'\'id=\' + escape(this.value)', 
                                        onComplete:'updateDelegacion(e)')}"/>
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
                                    <label for="percepcionesMensuales"><g:message code="prestamo.percepcionesMensuales.label" default="Percepciones Mensuales" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: prestamoInstance, field: 'percepcionesMensuales', 'errors')}">
                                    <g:textField name="percepcionesMensuales" value="${fieldValue(bean: prestamoInstance, field: 'percepcionesMensuales')}" />
                                </td>
                            </tr>

                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="deduccionesMensuales"><g:message code="prestamo.deduccionesMensuales.label" default="Deducciones Mensuales" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: prestamoInstance, field: 'deduccionesMensuales', 'errors')}">
                                    <g:textField name="deduccionesMensuales" value="${fieldValue(bean: prestamoInstance, field: 'deduccionesMensuales')}" />
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
                                  <label for="documentosCorrectos"><g:message code="prestamo.documentosCorrectos.label" default="Documentos Correctos" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: prestamoInstance, field: 'documentosCorrectos', 'errors')}">
                                    <g:checkBox name="documentosCorrectos" value="${prestamoInstance?.documentosCorrectos}" />
                                </td>
                            </tr>
                            
                            <sec:ifAllGranted roles="ROLE_CONTROL_CALIDAD">
	                            <tr class="prop">
	                                <td valign="top" class="name">
	                                  <label for="aprobado"><g:message code="prestamo.aprobado.label" default="Aprobado" /></label>
	                                </td>
	                                <td valign="top" class="value ${hasErrors(bean: prestamoInstance, field: 'aprobado', 'errors')}">
	                                    <g:checkBox name="aprobado" value="${prestamoInstance?.aprobado}" />
	                                </td>
	                            </tr>

								<tr class="prop">
									<td valign="top" class="name"><label for="explicacionDevolucion"><g:message
												code="prestamo.explicacionDevolucion.label" default="Motivo Devoluci&oacute;n" /></label></td>
									<td valign="top"
										class="value ${hasErrors(bean: prestamoInstance, field: 'explicacionDevolucion', 'errors')}">
										<g:textArea name="explicacionDevolucion" cols="40" rows="5"
											value="${prestamoInstance?.explicacionDevolucion}" />
									</td>
								</tr>
	                            
                            </sec:ifAllGranted>
                            
							<g:if
								test="${prestamoInstance.approvalStatus == ApprovalStatus.REJECTED}">
                                <g:if
                                    test="${prestamoInstance.estatusSolicitud.claveEtapaPrestamo == 'DEVOLUCION_AMESA'}">

								
    								<tr class="prop">
    									<td valign="top" class="name"><label for="explicacionDevolucion"><g:message
    												code="prestamo.explicacionDevolucion.label" default="Motivo Devoluci&oacute;n" /></label></td>
    									<td valign="top"
    										class="value ${hasErrors(bean: prestamoInstance, field: 'explicacionDevolucion', 'errors')}">
    										<g:textArea name="explicacionDevolucion" cols="40" rows="5"
    											value="${prestamoInstance?.explicacionDevolucion}" />
    									</td>
    								</tr>
    								
    								<tr class="prop">
    									<td valign="top" class="name"><label
    										for="reenviarSolicitud"><g:message
    												code="prestamo.reenviarSolicitud.label"
    												default="Reenviar Solicitud" /></label></td>
    									<td valign="top"
    										class="value ${hasErrors(bean: prestamoInstance, field: 'reenviarSolicitud', 'errors')}">
    										<g:checkBox name="reenviarSolicitud"
    											value="${prestamoInstance?.reenviarSolicitud}" />
    									</td>
    								</tr>
	                           </g:if>
							</g:if>
                            
                            
							<tr class="prop">
								<td valign="top" class="name"><label for="comentarios"><g:message
											code="prestamo.comentarios.label" default="Comentarios" /></label></td>
								<td valign="top"
									class="value ${hasErrors(bean: prestamoInstance, field: 'comentarios', 'errors')}">
									<g:textArea name="comentarios" cols="40" rows="5"
										value="${prestamoInstance?.comentarios}" />
								</td>
							</tr>
                            
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" /></span>
                    <g:if test="${!params.showMenu.equals('false')}">
	                    <span class="button"><g:actionSubmit class="save" action="update" value="${message(code: 'default.button.complete.label', default: 'Complete')}" /></span>
	                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
	                    
	                </g:if>
                </div>
            </g:form>
        </div>
    </body>
</html>

<g:javascript>

    function cambiaDependencia(idDependencia){
        ${remoteFunction(controller:"entDependencia", action:"ajaxGetTipoEmpleado", params:"'id=' + idDependencia", onComplete:"updateTipoEmpleado(e)")}        
        ${remoteFunction(controller:"entDependencia", action:"ajaxGetPromocion", params:"'id=' + idDependencia", onComplete:"updatePromocion(e)")}                
    }

    function updateTipoEmpleado(e) {
        // The response comes back as a bunch-o-JSON
        var tipoEmpleado = eval("(" + e.responseText + ")")   
        // evaluate JSON

        if (tipoEmpleado) {
            var rselect = document.getElementById('tipoEmpleadoDep.id')
            // OBTIENE EL TIPO DE EMPLEADO ASIGNADO EN EL MOMENTO
            // DE CREAR EL PRESTAMO
            var tipoEmpleadoActual =  rselect.value

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
            //ASIGNA EL TIPO DE EMPLEADO ACTUAL
            rselect.value = tipoEmpleadoActual            
        }
    }

   function updatePromocion(e) {
        // The response comes back as a bunch-o-JSON
        var promociones = eval("(" + e.responseText + ")")   
        // evaluate JSON

        if (promociones) {
            var rselect = document.getElementById('promocion.id')
            // OBTIENE LA PROMOCION ASIGNADA EN EL MOMENTO
            // DE CREAR EL PRESTAMO
            var promocionActual =  rselect.value            

            // Clear all previous options
            var l = rselect.length

            while (l > 0) {
                l--
                rselect.remove(l)
            }

            // Rebuild the select
            for (var i=0; i < promociones.length; i++) {
                var promocion = promociones[i]
                var opt = document.createElement('option');
                opt.text = promocion.clavePromocion
                opt.value = promocion.id
                try {
                    rselect.add(opt, null) // standards compliant; doesn't work in IE
                }
                catch(ex) {
                    rselect.add(opt) // IE only
                }
            }
            //ASIGNA LA PROMOCION ACTUAL
            rselect.value = promocionActual             
        }
    }
    
    // This is called when the page loads to initialize dependencia
    var zselect = document.getElementById('dependencia.id')
    var zopt = zselect.options[zselect.selectedIndex]
    ${remoteFunction(controller:"entDependencia", action:"ajaxGetTipoEmpleado", params:"'id=' + zopt.value", onComplete:"updateTipoEmpleado(e)")}
    ${remoteFunction(controller:"entDependencia", action:"ajaxGetPromocion", params:"'id=' + zopt.value", onComplete:"updatePromocion(e)")}        
    function updateDelegacion(e) {
        // The response comes back as a bunch-o-JSON
        var delegaciones = eval("(" + e.responseText + ")")   
        // evaluate JSON

        if (delegaciones) {
            var rselect = document.getElementById('delegacion.id')
            // OBTIENE LA DELEGACION ASIGNADA EN EL MOMENTO
            // DE CREAR EL PRESTAMO
            var delegacionActual =  rselect.value

            // Clear all previous options
            var l = rselect.length

            while (l > 0) {
                l--
                rselect.remove(l)
            }

            // Rebuild the select
            for (var i=0; i < delegaciones.length; i++) {
                var entDelegacion = delegaciones[i]
                var opt = document.createElement('option');
                opt.text = entDelegacion.nombreDelegacion
                opt.value = entDelegacion.id
                try {
                    rselect.add(opt, null) // standards compliant; doesn't work in IE
                }
                catch(ex) {
                    rselect.add(opt) // IE only
                }
            }
            //ASIGNA EL VALOR DE LA DELEGACION GUARDADA
            rselect.value = delegacionActual

        }
    }

    // This is called when the page loads to initialize dependencia
    var zselect = document.getElementById('sucursal.id')
    var zopt = zselect.options[zselect.selectedIndex]
    ${remoteFunction(controller:"entSucursal", action:"ajaxGetDelegacion", params:"'id=' + zopt.value", onComplete:"updateDelegacion(e)")}    
</g:javascript>