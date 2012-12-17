
<%@ page import="com.sim.credito.Prestamo" %>
<%@ page import="org.grails.activiti.ApprovalStatus"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'prestamo.label', default: 'Prestamo')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
			<div class="nav" role="navigation">
			 <ul> 
            <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
	          <li><g:link class="list" controller="task" action="myTaskList"><g:message code="myTasks.label" default="My Tasks ({0})" args="[myTasksCount]" /></g:link></li>
            <li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
            <li><g:link class="list" controller="prestamoDocumentos" action="listaDocumentos" id="${prestamoInstance.clavePrestamo}"  
            	params="[clavePrestamo: prestamoInstance.clavePrestamo,
            	    claveCliente: prestamoInstance?.cliente?.id,
            	    nombreCliente: prestamoInstance?.cliente?.persona?.apellidoPaterno + ' ' +
            	             prestamoInstance?.cliente?.persona?.apellidoMaterno + ' ' +
            	             prestamoInstance?.cliente?.persona?.primerNombre + ' ' +
            	             prestamoInstance?.cliente?.persona?.segundoNombre ]">Documentos</g:link><li>
             <li><g:link class="list" action="generaTablaAmortizacion" params="[idPrestamo:prestamoInstance.id]">Tabla de Amortizaci&oacute;n</g:link></li>
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
                            <td valign="top" class="name"><g:message code="prestamo.id.label" default="Id" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: prestamoInstance, field: "id")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="prestamo.cliente.label" default="Cliente" /></td>
                            
                            <td valign="top" class="value"><g:link controller="rsCliente" action="show" id="${prestamoInstance?.cliente?.id}">${prestamoInstance?.cliente?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                        
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="prestamo.correoSolicitante.label" default="Correo Solicitante" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: prestamoInstance, field: "correoSolicitante")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="prestamo.clavePrestamo.label" default="Clave Prestamo" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: prestamoInstance, field: "clavePrestamo")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="prestamo.folioSolicitud.label" default="Folio Solicitud" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: prestamoInstance, field: "folioSolicitud")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="prestamo.promocion.label" default="Promocion" /></td>
                            
                            <td valign="top" class="value"><g:link controller="proPromocion" action="show" id="${prestamoInstance?.promocion?.id}">${prestamoInstance?.promocion?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="prestamo.dependencia.label" default="Dependencia" /></td>
                            
                            <td valign="top" class="value"><g:link controller="entDependencia" action="show" id="${prestamoInstance?.dependencia?.id}">${prestamoInstance?.dependencia?.encodeAsHTML()}</g:link></td>
                            
                        </tr>

                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="prestamo.sucursal.label" default="Sucursal" /></td>
                            
                            <td valign="top" class="value"><g:link controller="entSucursal" action="show" id="${prestamoInstance?.sucursal?.id}">${prestamoInstance?.sucursal?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="prestamo.delegacion.label" default="Delegacion" /></td>
                            
                            <td valign="top" class="value"><g:link controller="entDelegacion" action="show" id="${prestamoInstance?.delegacion?.id}">${prestamoInstance?.delegacion?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="prestamo.vendedor.label" default="Vendedor" /></td>
                            
                            <td valign="top" class="value"><g:link controller="empEmpleado" action="show" id="${prestamoInstance?.vendedor?.id}">${prestamoInstance?.vendedor?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="prestamo.fechaSolicitud.label" default="Fecha Solicitud" /></td>
                            
                            <td valign="top" class="value"><g:formatDate date="${prestamoInstance?.fechaSolicitud}" /></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="prestamo.montoSolicitado.label" default="Monto Solicitado" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: prestamoInstance, field: "montoSolicitado")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="prestamo.estatusSolicitud.label" default="Estatus Solicitud" /></td>
                            
                            <td valign="top" class="value"><g:link controller="simCatEtapaPrestamo" action="show" id="${prestamoInstance?.estatusSolicitud?.id}">${prestamoInstance?.estatusSolicitud?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="prestamo.formaDeDispercion.label" default="Forma De Dispercion" /></td>
                            
                            <td valign="top" class="value"><g:link controller="simCatFormaEntrega" action="show" id="${prestamoInstance?.formaDeDispercion?.id}">${prestamoInstance?.formaDeDispercion?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="prestamo.documentosCorrectos.label" default="Documentos Correctos" /></td>
                            
                            <td valign="top" class="value"><g:formatBoolean boolean="${prestamoInstance?.documentosCorrectos}" /></td>
                            
                        </tr>
                        
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="prestamo.aprobado.label" default="Aprobado" /></td>
                            
                            <td valign="top" class="value"><g:formatBoolean boolean="${prestamoInstance?.aprobado}" /></td>
                            
                        </tr>
                        
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="prestamo.approvalStatus.label" default="Approval Status" /></td>
                            
                            <td valign="top" class="value">${prestamoInstance?.approvalStatus?.encodeAsHTML()}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="prestamo.dateCreated.label" default="Date Created" /></td>
                            
                            <td valign="top" class="value"><g:formatDate date="${prestamoInstance?.dateCreated}" /></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="prestamo.lastUpdated.label" default="Last Updated" /></td>
                            
                            <td valign="top" class="value"><g:formatDate date="${prestamoInstance?.lastUpdated}" /></td>
                            
                        </tr>
                        
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="prestamo.comentarios.label" default="Comentarios" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: prestamoInstance, field: "comentarios")}</td>
                            
                        </tr>
                        
						<g:if test="${prestamoInstance.approvalStatus == ApprovalStatus.REJECTED}">
								
	    	                <tr class="prop">
	                            <td valign="top" class="name"><g:message code="prestamo.explicacionDevolucion.label" default="Motivo Devoluci&oacute;n" /></td>
	                            
	                            <td valign="top" class="value">${fieldValue(bean: prestamoInstance, field: "explicacionDevolucion")}</td>
	                        </tr>
	
						</g:if>                        
                        
                    
                    </tbody>
                </table>
            </div>
            <g:if test="${!params.complete && params.taskId}">
            
				<div class="fieldcontain">
					<label>
						Comparar con el documento:
					</label>
				
					<g:each in="${documentos}" var="doc">
						<g:link url="javascript:popup('${request.contextPath }/viewImageCompare/index?imagen=${doc.id }&info=/prestamo/edit/${prestamoInstance?.id}?taskId=${params.taskId}');"> ${doc.name}</g:link> |
					</g:each>
				</div>
						               
	            <div class="buttons">
	                <g:form>
	                    <g:hiddenField name="id" value="${prestamoInstance?.id}" />
	                    <g:hiddenField name="taskId" value="${params.taskId}" />
	                    <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
	                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
	                </g:form>
	            </div>
            </g:if>
        </div>
    </body>
</html>
