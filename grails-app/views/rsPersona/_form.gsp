<%@ page import="com.rs.gral.RsPersona" %>



<div class="fieldcontain ${hasErrors(bean: rsPersonaInstance, field: 'apellidoPaterno', 'error')} required">
	<label for="apellidoPaterno">
		<g:message code="rsPersona.apellidoPaterno.label" default="Apellido Paterno" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="apellidoPaterno" maxlength="25" required="" value="${rsPersonaInstance?.apellidoPaterno}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: rsPersonaInstance, field: 'apellidoMaterno', 'error')} ">
	<label for="apellidoMaterno">
		<g:message code="rsPersona.apellidoMaterno.label" default="Apellido Materno" />
		
	</label>
	<g:textField name="apellidoMaterno" maxlength="25" value="${rsPersonaInstance?.apellidoMaterno}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: rsPersonaInstance, field: 'primerNombre', 'error')} required">
	<label for="primerNombre">
		<g:message code="rsPersona.primerNombre.label" default="Primer Nombre" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="primerNombre" maxlength="25" required="" value="${rsPersonaInstance?.primerNombre}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: rsPersonaInstance, field: 'segundoNombre', 'error')} ">
	<label for="segundoNombre">
		<g:message code="rsPersona.segundoNombre.label" default="Segundo Nombre" />
		
	</label>
	<g:textField name="segundoNombre" maxlength="25" value="${rsPersonaInstance?.segundoNombre}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: rsPersonaInstance, field: 'email', 'error')} ">
	<label for="email">
		<g:message code="rsPersona.email.label" default="Email" />
		
	</label>
	<g:field type="email" name="email" value="${rsPersonaInstance?.email}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: rsPersonaInstance, field: 'sexo', 'error')} ">
	<label for="sexo">
		<g:message code="rsPersona.sexo.label" default="Sexo" />
		
	</label>
	<g:select name="sexo" from="${rsPersonaInstance.constraints.sexo.inList}" value="${rsPersonaInstance?.sexo}" valueMessagePrefix="rsPersona.sexo" noSelection="['': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: rsPersonaInstance, field: 'estadoCivil', 'error')} ">
	<label for="estadoCivil">
		<g:message code="rsPersona.estadoCivil.label" default="Estado Civil" />
		
	</label>
	<g:select name="estadoCivil" from="${rsPersonaInstance.constraints.estadoCivil.inList}" value="${rsPersonaInstance?.estadoCivil}" valueMessagePrefix="rsPersona.estadoCivil" noSelection="['': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: rsPersonaInstance, field: 'fechaNacimiento', 'error')} ">
	<label for="fechaNacimiento">
		<g:message code="rsPersona.fechaNacimiento.label" default="Fecha Nacimiento" />
		
	</label>
	<g:datePicker name="fechaNacimiento" precision="day"  value="${rsPersonaInstance?.fechaNacimiento}" default="none" noSelection="['': '']" />
</div>

<g:if test="${rsPersonaInstance?.id}">
<div class="fieldcontain ${hasErrors(bean: rsPersonaInstance, field: 'telefonos', 'error')} ">
	<label for="telefonos">
		<g:message code="rsPersona.telefonos.label" default="Telefonos" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${rsPersonaInstance?.telefonos?}" var="t">
    <li><g:link controller="rsGralTelefono" action="show" id="${t.id}">${t?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="rsGralTelefono" action="create" params="['rsPersona.id': rsPersonaInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'rsGralTelefono.label', default: 'RsGralTelefono')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: rsPersonaInstance, field: 'domicilios', 'error')} ">
	<label for="domicilios">
		<g:message code="rsPersona.domicilios.label" default="Domicilios" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${rsPersonaInstance?.domicilios?}" var="d">
    <li><g:link controller="rsGralDomicilio" action="show" id="${d.id}">${d?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="rsGralDomicilio" action="create" params="['rsPersona.id': rsPersonaInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'rsGralDomicilio.label', default: 'RsGralDomicilio')])}</g:link>
</li>
</ul>

</div>
</g:if>


<div class="fieldcontain ${hasErrors(bean: rsPersonaInstance, field: 'nombreAlterno', 'error')} ">
	<label for="nombreAlterno">
		<g:message code="rsPersona.nombreAlterno.label" default="Nombre Alterno" />
		
	</label>
	<g:textField name="nombreAlterno" maxlength="50" value="${rsPersonaInstance?.nombreAlterno}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: rsPersonaInstance, field: 'identificacionOficial', 'error')} ">
	<label for="identificacionOficial">
		<g:message code="rsPersona.identificacionOficial.label" default="Identificacion Oficial" />
		
	</label>
	<g:select id="identificacionOficial" name="identificacionOficial.id" from="${com.sim.catalogo.SimCatDocumento.findAllByTipoDocumento(com.sim.catalogo.SimCatTipoDocumento.findByClaveTipoDocumento('IDENTIFICACION'))}" optionKey="id" value="${rsPersonaInstance?.identificacionOficial?.id}" class="many-to-one" noSelection="['null': '']"/>

<div class="fieldcontain ${hasErrors(bean: rsPersonaInstance, field: 'numeroIdentificacionOficial', 'error')} ">
	<label for="numeroIdentificacionOficial">
		<g:message code="rsPersona.numeroIdentificacionOficial.label" default="Numero Identificacion Oficial" />
		
	</label>
	<g:textField name="numeroIdentificacionOficial" value="${rsPersonaInstance?.numeroIdentificacionOficial}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: rsPersonaInstance, field: 'rfc', 'error')} ">
	<label for="rfc">
		<g:message code="rsPersona.rfc.label" default="Rfc" />
		
	</label>
	<g:textField name="rfc" value="${rsPersonaInstance?.rfc}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: rsPersonaInstance, field: 'curp', 'error')} ">
	<label for="curp">
		<g:message code="rsPersona.curp.label" default="Curp" />
		
	</label>
	<g:textField name="curp" value="${rsPersonaInstance?.curp}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: rsPersonaInstance, field: 'escolaridad', 'error')} ">
	<label for="escolaridad">
		<g:message code="rsPersona.escolaridad.label" default="Escolaridad" />
		
	</label>
	<g:select id="escolaridad" name="escolaridad.id" from="${com.sim.catalogo.SimCatEscolaridad.list()}" optionKey="id" value="${rsPersonaInstance?.escolaridad?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: rsPersonaInstance, field: 'numeroImss', 'error')} ">
	<label for="numeroImss">
		<g:message code="rsPersona.numeroImss.label" default="Numero Imss" />
		
	</label>
	<g:textField name="numeroImss" maxlength="50" value="${rsPersonaInstance?.numeroImss}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: rsPersonaInstance, field: 'entidadNacimiento', 'error')} ">
	<label for="entidadNacimiento">
		<g:message code="rsPersona.entidadNacimiento.label" default="Entidad Nacimiento" />
		
	</label>
	<g:select id="entidadNacimiento" name="entidadNacimiento.id" from="${com.rs.gral.RsGralEstado.list()}" optionKey="id" value="${rsPersonaInstance?.entidadNacimiento?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: rsPersonaInstance, field: 'tiposPersona', 'error')} ">
	<label for="tiposPersona">
		<g:message code="rsPersona.tiposPersona.label" default="Tipos Persona" />
		
	</label>
	<g:select name="tiposPersona" from="${com.sim.catalogo.SimCatTipoPersona.list()}" multiple="multiple" optionKey="id" size="5" value="${rsPersonaInstance?.tiposPersona*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: rsPersonaInstance, field: 'usuario', 'error')} ">
	<label for="usuario">
		<g:message code="rsPersona.usuario.label" default="Usuario" />
		
	</label>
	<g:select id="usuario" name="usuario.id" from="${com.sim.usuario.Usuario.list()}" optionKey="id" value="${rsPersonaInstance?.usuario?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>



