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
                                    ${prestamoInstance.cliente}
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
                                    <label for="claveVendedor"><g:message code="prestamo.claveVendedor.label" default="Clave Vendedor" /></label>
                                </td>
                                <td valign="top">
                                    <g:textField name='claveVendedor.id' value=''
                                                 onKeyUp="${remoteFunction(
                                                         controller:'empEmpleado',
                                                         action:'ajaxGetVendedor',
                                                         params:'\'clave=\' + escape(this.value)',
                                                         onComplete:'updateVendedor(e)')}">
                                    </g:textField>
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="vendedor"><g:message code="prestamo.vendedor.label" default="Vendedor" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: prestamoInstance, field: 'vendedor', 'errors')}">
                                    <g:field type="text" name="nombreVendedor" value="" readonly="true" size="80"/>
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
                <g:hiddenField name="cliente.id" value="${prestamoInstance?.cliente?.id}" />                
                <g:hiddenField name="vendedor.id" value="" />                
            </g:form>
        </div>
    </body>
</html>

<g:javascript>

    function updateVendedor(e) {

        var valores = eval('(' + e.responseText + ')') 
        var nombreVendedor = valores[0];
        var idVendedor = valores[1];

        var nameVendedor = document.getElementById('nombreVendedor')
        var identificadorVendedor = document.getElementById('vendedor.id')
        
        nameVendedor.value = nombreVendedor
        identificadorVendedor.value = idVendedor

    }

    
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

    function updatePromocion(e) {
        // The response comes back as a bunch-o-JSON
        var promociones = eval("(" + e.responseText + ")")   
        // evaluate JSON

        if (promociones) {
            var rselect = document.getElementById('promocion.id')

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

                var today = new Date();
                var finVigencia = new Date(promocion.fechaFinVigencia);

                if(today > finVigencia){
                    //LA VIGENCIA DEL PRESTAMO HA CONCLUIDO
                }else{
                    opt.text = promocion.clavePromocion
                    opt.value = promocion.id                    
                    try {
                        rselect.add(opt, null) // standards compliant; doesn't work in IE
                    }
                    catch(ex) {
                        rselect.add(opt) // IE only
                    }                    
                }
            }
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
        }
    }

    // This is called when the page loads to initialize dependencia
    var zselect = document.getElementById('sucursal.id')
    var zopt = zselect.options[zselect.selectedIndex]
    ${remoteFunction(controller:"EntSucursal", action:"ajaxGetDelegacion", params:"'id=' + zopt.value", onComplete:"updateDelegacion(e)")}

</g:javascript>
