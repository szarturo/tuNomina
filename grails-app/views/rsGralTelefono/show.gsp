
<%@ page import="com.rs.gral.RsGralTelefono"%>
<!doctype html>
<html>
<head>
<meta name="layout" content="main">
<g:set var="entityName"
	value="${message(code: 'rsGralTelefono.label', default: 'RsGralTelefono')}" />
<title><g:message code="default.show.label" args="[entityName]" /></title>
</head>
<body>
	<a href="#show-rsGralTelefono" class="skip" tabindex="-1"><g:message
			code="default.link.skip.label" default="Skip to content&hellip;" /></a>
	<div class="nav" role="navigation">
		<ul>
			<li><a class="home" href="${createLink(uri: '/')}"><g:message
						code="default.home.label" /></a></li>
			<li><g:link class="list" action="list">
					<g:message code="default.list.label" args="[entityName]" />
				</g:link></li>
			<li><g:link class="create" action="create">
					<g:message code="default.new.label" args="[entityName]" />
				</g:link></li>
		</ul>
	</div>
	<div id="show-rsGralTelefono" class="content scaffold-show" role="main">
		<h1>
			<g:message code="default.show.label" args="[entityName]" />
		</h1>
		<g:if test="${flash.message}">
			<div class="message" role="status">
				${flash.message}
			</div>
		</g:if>
		<ol class="property-list rsGralTelefono">


			<g:if test="${rsGralTelefonoInstance?.persona?.id}">
				<li class="fieldcontain"><span id="persona-label"
					class="property-label"><g:message
							code="rsGralTelefono.persona.label" default="Persona" /></span> <span
					class="property-value" aria-labelledby="persona-label"><g:link
							controller="rsPersona" action="show"
							id="${rsGralTelefonoInstance?.persona?.id}">
							${rsGralTelefonoInstance?.persona?.encodeAsHTML()}
						</g:link></span></li>
			</g:if>


			<g:if test="${rsGralTelefonoInstance?.telefono}">
				<li class="fieldcontain"><span id="telefono-label"
					class="property-label"><g:message
							code="rsGralTelefono.telefono.label" default="Telefono" /></span> <span
					class="property-value" aria-labelledby="telefono-label"><g:fieldValue
							bean="${rsGralTelefonoInstance}" field="telefono" /></span></li>
			</g:if>

			<g:if test="${rsGralTelefonoInstance?.descripcionTelefono}">
				<li class="fieldcontain"><span id="descripcionTelefono-label"
					class="property-label"><g:message
							code="rsGralTelefono.descripcionTelefono.label"
							default="Descripcion Telefono" /></span> <span class="property-value"
					aria-labelledby="descripcionTelefono-label"><g:link
							controller="simCatDescTelefono" action="show"
							id="${rsGralTelefonoInstance?.descripcionTelefono?.id}">
							${rsGralTelefonoInstance?.descripcionTelefono?.encodeAsHTML()}
						</g:link></span></li>
			</g:if>

		</ol>
		<g:form>
			<fieldset class="buttons">
				<g:hiddenField name="id" value="${rsGralTelefonoInstance?.id}" />
				<g:link class="edit" action="edit"
					id="${rsGralTelefonoInstance?.id}">
					<g:message code="default.button.edit.label" default="Edit" />
				</g:link>
				<g:actionSubmit class="delete" action="delete"
					value="${message(code: 'default.button.delete.label', default: 'Delete')}"
					onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
			</fieldset>
		</g:form>
	</div>
</body>
</html>
