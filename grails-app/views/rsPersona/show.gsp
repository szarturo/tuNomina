
<%@ page import="com.rs.gral.RsPersona" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'rsPersona.label', default: 'RsPersona')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-rsPersona" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>

				<li><g:link class="create" controller="rsCliente" action="create" params="[idPersona : rsPersonaInstance.id]">
     				Alta Cliente
				</g:link></li>

			</ul>
		</div>
		<div id="show-rsPersona" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list rsPersona">
			
				<g:if test="${rsPersonaInstance?.apellidoPaterno}">
				<li class="fieldcontain">
					<span id="apellidoPaterno-label" class="property-label"><g:message code="rsPersona.apellidoPaterno.label" default="Apellido Paterno" /></span>
					
						<span class="property-value" aria-labelledby="apellidoPaterno-label"><g:fieldValue bean="${rsPersonaInstance}" field="apellidoPaterno"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${rsPersonaInstance?.apellidoMaterno}">
				<li class="fieldcontain">
					<span id="apellidoMaterno-label" class="property-label"><g:message code="rsPersona.apellidoMaterno.label" default="Apellido Materno" /></span>
					
						<span class="property-value" aria-labelledby="apellidoMaterno-label"><g:fieldValue bean="${rsPersonaInstance}" field="apellidoMaterno"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${rsPersonaInstance?.primerNombre}">
				<li class="fieldcontain">
					<span id="primerNombre-label" class="property-label"><g:message code="rsPersona.primerNombre.label" default="Primer Nombre" /></span>
					
						<span class="property-value" aria-labelledby="primerNombre-label"><g:fieldValue bean="${rsPersonaInstance}" field="primerNombre"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${rsPersonaInstance?.segundoNombre}">
				<li class="fieldcontain">
					<span id="segundoNombre-label" class="property-label"><g:message code="rsPersona.segundoNombre.label" default="Segundo Nombre" /></span>
					
						<span class="property-value" aria-labelledby="segundoNombre-label"><g:fieldValue bean="${rsPersonaInstance}" field="segundoNombre"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${rsPersonaInstance?.email}">
				<li class="fieldcontain">
					<span id="email-label" class="property-label"><g:message code="rsPersona.email.label" default="Email" /></span>
					
						<span class="property-value" aria-labelledby="email-label"><g:fieldValue bean="${rsPersonaInstance}" field="email"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${rsPersonaInstance?.sexo}">
				<li class="fieldcontain">
					<span id="sexo-label" class="property-label"><g:message code="rsPersona.sexo.label" default="Sexo" /></span>
					
						<span class="property-value" aria-labelledby="sexo-label"><g:fieldValue bean="${rsPersonaInstance}" field="sexo"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${rsPersonaInstance?.estadoCivil}">
				<li class="fieldcontain">
					<span id="estadoCivil-label" class="property-label"><g:message code="rsPersona.estadoCivil.label" default="Estado Civil" /></span>
					
						<span class="property-value" aria-labelledby="estadoCivil-label"><g:fieldValue bean="${rsPersonaInstance}" field="estadoCivil"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${rsPersonaInstance?.fechaNacimiento}">
				<li class="fieldcontain">
					<span id="fechaNacimiento-label" class="property-label"><g:message code="rsPersona.fechaNacimiento.label" default="Fecha Nacimiento" /></span>
					
						<span class="property-value" aria-labelledby="fechaNacimiento-label"><g:formatDate date="${rsPersonaInstance?.fechaNacimiento}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${rsPersonaInstance?.telefonos}">
				<li class="fieldcontain">
					<span id="telefonos-label" class="property-label"><g:message code="rsPersona.telefonos.label" default="Telefonos" /></span>
					
						<g:each in="${rsPersonaInstance.telefonos}" var="t">
						<span class="property-value" aria-labelledby="telefonos-label"><g:link controller="rsGralTelefono" action="show" id="${t.id}">${t?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${rsPersonaInstance?.domicilios}">
				<li class="fieldcontain">
					<span id="domicilios-label" class="property-label"><g:message code="rsPersona.domicilios.label" default="Domicilios" /></span>
					
						<g:each in="${rsPersonaInstance.domicilios}" var="d">
						<span class="property-value" aria-labelledby="domicilios-label"><g:link controller="rsGralDomicilio" action="show" id="${d.id}">${d?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${rsPersonaInstance?.nombreAlterno}">
				<li class="fieldcontain">
					<span id="nombreAlterno-label" class="property-label"><g:message code="rsPersona.nombreAlterno.label" default="Nombre Alterno" /></span>
					
						<span class="property-value" aria-labelledby="nombreAlterno-label"><g:fieldValue bean="${rsPersonaInstance}" field="nombreAlterno"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${rsPersonaInstance?.identificacionOficial}">
				<li class="fieldcontain">
					<span id="identificacionOficial-label" class="property-label"><g:message code="rsPersona.identificacionOficial.label" default="Identificacion Oficial" /></span>
					
						<span class="property-value" aria-labelledby="identificacionOficial-label"><g:link controller="simCatDocumento" action="show" id="${rsPersonaInstance?.identificacionOficial?.id}">${rsPersonaInstance?.identificacionOficial?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${rsPersonaInstance?.numeroIdentificacionOficial}">
				<li class="fieldcontain">
					<span id="numeroIdentificacionOficial-label" class="property-label"><g:message code="rsPersona.numeroIdentificacionOficial.label" default="Numero Identificacion Oficial" /></span>
					
						<span class="property-value" aria-labelledby="numeroIdentificacionOficial-label"><g:fieldValue bean="${rsPersonaInstance}" field="numeroIdentificacionOficial"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${rsPersonaInstance?.rfc}">
				<li class="fieldcontain">
					<span id="rfc-label" class="property-label"><g:message code="rsPersona.rfc.label" default="Rfc" /></span>
					
						<span class="property-value" aria-labelledby="rfc-label"><g:fieldValue bean="${rsPersonaInstance}" field="rfc"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${rsPersonaInstance?.curp}">
				<li class="fieldcontain">
					<span id="curp-label" class="property-label"><g:message code="rsPersona.curp.label" default="Curp" /></span>
					
						<span class="property-value" aria-labelledby="curp-label"><g:fieldValue bean="${rsPersonaInstance}" field="curp"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${rsPersonaInstance?.escolaridad}">
				<li class="fieldcontain">
					<span id="escolaridad-label" class="property-label"><g:message code="rsPersona.escolaridad.label" default="Escolaridad" /></span>
					
						<span class="property-value" aria-labelledby="escolaridad-label"><g:link controller="simCatEscolaridad" action="show" id="${rsPersonaInstance?.escolaridad?.id}">${rsPersonaInstance?.escolaridad?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${rsPersonaInstance?.numeroImss}">
				<li class="fieldcontain">
					<span id="numeroImss-label" class="property-label"><g:message code="rsPersona.numeroImss.label" default="Numero Imss" /></span>
					
						<span class="property-value" aria-labelledby="numeroImss-label"><g:fieldValue bean="${rsPersonaInstance}" field="numeroImss"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${rsPersonaInstance?.entidadNacimiento}">
				<li class="fieldcontain">
					<span id="entidadNacimiento-label" class="property-label"><g:message code="rsPersona.entidadNacimiento.label" default="Entidad Nacimiento" /></span>
					
						<span class="property-value" aria-labelledby="entidadNacimiento-label"><g:link controller="rsGralEstado" action="show" id="${rsPersonaInstance?.entidadNacimiento?.id}">${rsPersonaInstance?.entidadNacimiento?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${rsPersonaInstance?.tiposPersona}">
				<li class="fieldcontain">
					<span id="tiposPersona-label" class="property-label"><g:message code="rsPersona.tiposPersona.label" default="Tipos Persona" /></span>
					
						<g:each in="${rsPersonaInstance.tiposPersona}" var="t">
						<span class="property-value" aria-labelledby="tiposPersona-label"><g:link controller="simCatTipoPersona" action="show" id="${t.id}">${t?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${rsPersonaInstance?.usuario}">
				<li class="fieldcontain">
					<span id="usuario-label" class="property-label"><g:message code="rsPersona.usuario.label" default="Usuario" /></span>
					
						<span class="property-value" aria-labelledby="usuario-label"><g:link controller="usuario" action="show" id="${rsPersonaInstance?.usuario?.id}">${rsPersonaInstance?.usuario?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${rsPersonaInstance?.datosEmpleado}">
				<li class="fieldcontain">
					<span id="datosEmpleado-label" class="property-label"><g:message code="rsPersona.datosEmpleado.label" default="Datos Empleado" /></span>
					
						<span class="property-value" aria-labelledby="datosEmpleado-label"><g:link controller="empEmpleado" action="show" id="${rsPersonaInstance?.datosEmpleado?.id}">${rsPersonaInstance?.datosEmpleado?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${rsPersonaInstance?.datosCliente}">
				<li class="fieldcontain">
					<span id="datosCliente-label" class="property-label"><g:message code="rsPersona.datosCliente.label" default="Datos Cliente" /></span>
					
						<span class="property-value" aria-labelledby="datosCliente-label"><g:link controller="rsCliente" action="show" id="${rsPersonaInstance?.datosCliente?.id}">${rsPersonaInstance?.datosCliente?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${rsPersonaInstance?.id}" />
					<g:link class="edit" action="edit" id="${rsPersonaInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
