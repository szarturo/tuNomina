<%@ page import="com.rs.gral.RsGralTelefono"%>


<g:if test="${rsGralTelefonoInstance?.persona?.id}">
	<div
		class="fieldcontain ${hasErrors(bean: rsGralTelefonoInstance, field: 'persona', 'error')} ">
		<label for="persona"> <g:message
				code="rsGralTelefono.persona.label" default="Persona" />

		</label> <label>
			${rsGralTelefonoInstance?.persona}
		</label>
		<g:hiddenField name='persona.id'
			value='${rsGralTelefonoInstance?.persona?.id}' />

	</div>
</g:if>


<div
	class="fieldcontain ${hasErrors(bean: rsGralTelefonoInstance, field: 'telefono', 'error')} required">
	<label for="telefono"> <g:message
			code="rsGralTelefono.telefono.label" default="Telefono" /> <span
		class="required-indicator">*</span>
	</label>
	<g:textField name="telefono" maxlength="15" required=""
		value="${rsGralTelefonoInstance?.telefono}" />
</div>

<div
	class="fieldcontain ${hasErrors(bean: rsGralTelefonoInstance, field: 'descripcionTelefono', 'error')} required">
	<label for="descripcionTelefono"> <g:message
			code="rsGralTelefono.descripcionTelefono.label"
			default="Descripcion Telefono" /> <span class="required-indicator">*</span>
	</label>
	<g:select id="descripcionTelefono" name="descripcionTelefono.id"
		from="${com.sim.catalogo.SimCatDescTelefono.list()}" optionKey="id"
		required="" value="${rsGralTelefonoInstance?.descripcionTelefono?.id}"
		class="many-to-one" />
</div>


