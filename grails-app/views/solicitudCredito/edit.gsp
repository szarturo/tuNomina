
<%@ page import="com.sim.procesos.credito.SolicitudCredito"%>
<%@ page import="org.grails.activiti.ApprovalStatus"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="layout" content="main" />
<g:set var="entityName"
	value="${message(code: 'solicitudCredito.label', default: 'SolicitudCredito')}" />
<title><g:message code="default.edit.label" args="[entityName]" /></title>
</head>
<body>
	<div class="nav" role="navigation">
		<ul>
			<li><a class="home" href="${createLink(uri: '/')}"><g:message
						code="default.home.label" /></a></li>
			<li><g:link class="list" controller="task" action="myTaskList">
					<g:message code="myTasks.label" default="My Tasks ({0})"
						args="[myTasksCount]" />
				</g:link></li>
			<li><g:link class="list" action="list">
					<g:message code="default.list.label" args="[entityName]" />
				</g:link></li>
			<li><g:link class="create" action="start">
					<g:message code="default.new.label" args="[entityName]" />
				</g:link></li>
		</ul>
	</div>
	<div class="body">
		<h1>
			<g:message code="default.edit.label" args="[entityName]" />
		</h1>
		<g:if test="${flash.message}">
			<div class="message">
				${flash.message}
			</div>
		</g:if>
		<g:hasErrors bean="${solicitudCreditoInstance}">
			<div class="errors">
				<g:renderErrors bean="${solicitudCreditoInstance}" as="list" />
			</div>
		</g:hasErrors>
		<g:form method="post">
			<g:hiddenField name="id" value="${solicitudCreditoInstance?.id}" />
			<g:hiddenField name="version"
				value="${solicitudCreditoInstance?.version}" />
			<g:hiddenField name="taskId" value="${params.taskId}" />
			<div class="dialog">
				<table>
					<tbody>

						<tr class="prop">
							<td valign="top" class="name"><label for="nombreSolicitante"><g:message
										code="solicitudCredito.nombreSolicitante.label"
										default="Nombre Solicitante" /></label></td>
							<td valign="top"
								class="value ${hasErrors(bean: solicitudCreditoInstance, field: 'nombreSolicitante', 'errors')}">
								<g:textField name="nombreSolicitante" maxlength="50"
									value="${solicitudCreditoInstance?.nombreSolicitante}" />
							</td>
						</tr>

						<tr class="prop">
							<td valign="top" class="name"><label
								for="cantidadSolicitada"><g:message
										code="solicitudCredito.cantidadSolicitada.label"
										default="Cantidad Solicitada" /></label></td>
							<td valign="top"
								class="value ${hasErrors(bean: solicitudCreditoInstance, field: 'cantidadSolicitada', 'errors')}">
								<g:textField name="cantidadSolicitada"
									value="${fieldValue(bean: solicitudCreditoInstance, field: 'cantidadSolicitada')}" />
							</td>
						</tr>

						<tr class="prop">
							<td valign="top" class="name"><label for="motivo"><g:message
										code="solicitudCredito.motivo.label" default="Motivo" /></label></td>
							<td valign="top"
								class="value ${hasErrors(bean: solicitudCreditoInstance, field: 'motivo', 'errors')}">
								<g:textArea name="motivo" cols="40" rows="5"
									value="${solicitudCreditoInstance?.motivo}" />
							</td>
						</tr>

						<g:if
							test="${solicitudCreditoInstance.approvalStatus != ApprovalStatus.PENDING}">
							<tr class="prop">
								<td valign="top" class="name"><g:message
										code="solicitudCredito.approvalStatus.label"
										default="Approval Status" /></td>

								<td valign="top" class="value">
									${solicitudCreditoInstance?.approvalStatus?.encodeAsHTML()}
								</td>

							</tr>


							<tr class="prop">
								<td valign="top" class="name"><g:message
										code="solicitudCredito.approvalRemark.label"
										default="Approval Remark" /></td>

								<td valign="top" class="value">
									${fieldValue(bean: solicitudCreditoInstance, field: "approvalRemark")}
								</td>

							</tr>

						</g:if>

						<g:if
							test="${solicitudCreditoInstance.approvalStatus == ApprovalStatus.REJECTED}">
							<tr class="prop">
								<td valign="top" class="name"><label
									for="reenviarSolicitud"><g:message
											code="solicitudCredito.reenviarSolicitud.label"
											default="Reenviar Solicitud" /></label></td>
								<td valign="top"
									class="value ${hasErrors(bean: solicitudCreditoInstance, field: 'reenviarSolicitud', 'errors')}">
									<g:checkBox name="reenviarSolicitud"
										value="${solicitudCreditoInstance?.reenviarSolicitud}" />
								</td>
							</tr>

						</g:if>

					</tbody>
				</table>
			</div>
			<div class="buttons">
				<span class="button"><g:actionSubmit class="save"
						action="update"
						value="${message(code: 'default.button.update.label', default: 'Update')}" /></span>
				<span class="button"><g:actionSubmit class="save"
						action="update"
						value="${message(code: 'default.button.complete.label', default: 'Complete')}" /></span>
				<span class="button"><g:actionSubmit class="delete"
						action="delete"
						value="${message(code: 'default.button.delete.label', default: 'Delete')}"
						onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
			</div>

		</g:form>
	</div>
</body>
</html>
