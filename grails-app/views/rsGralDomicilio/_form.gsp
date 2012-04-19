<%@ page import="com.rs.gral.RsGralDomicilio"%>


<g:if test="${rsGralDomicilioInstance?.persona?.id}">
	<div
		class="fieldcontain ${hasErrors(bean: rsGralDomicilioInstance, field: 'persona', 'error')} ">
		<label for="persona"> <g:message
				code="rsGralDomicilio.persona.label" default="Persona" />

		</label> <label>
			${rsGralDomicilioInstance?.persona}
		</label>
		<g:hiddenField name='persona.id'
			value='${rsGralDomicilioInstance?.persona?.id}' />
	</div>
</g:if>

<div
	class="fieldcontain ${hasErrors(bean: rsGralDomicilioInstance, field: 'calle', 'error')} required">
	<label for="calle"> <g:message
			code="rsGralDomicilio.calle.label" default="Calle" /> <span
		class="required-indicator">*</span>
	</label>
	<g:textField name="calle" maxlength="100" required=""
		value="${rsGralDomicilioInstance?.calle}" />
</div>

<div
	class="fieldcontain ${hasErrors(bean: rsGralDomicilioInstance, field: 'numeroInterior', 'error')} ">
	<label for="numeroInterior"> <g:message
			code="rsGralDomicilio.numeroInterior.label" default="Numero Interior" />

	</label>
	<g:textField name="numeroInterior"
		value="${rsGralDomicilioInstance?.numeroInterior}" />
</div>

<div
	class="fieldcontain ${hasErrors(bean: rsGralDomicilioInstance, field: 'numeroExterior', 'error')} ">
	<label for="numeroExterior"> <g:message
			code="rsGralDomicilio.numeroExterior.label" default="Numero Exterior" />

	</label>
	<g:textField name="numeroExterior"
		value="${rsGralDomicilioInstance?.numeroExterior}" />
</div>

<div
	class="fieldcontain ${hasErrors(bean: rsGralDomicilioInstance, field: 'rsGralAsentamiento', 'error')} required">
	<label for="rsGralAsentamiento"> <g:message
			code="rsGralDomicilio.rsGralAsentamiento.label"
			default="Rs Gral Asentamiento" /> <span class="required-indicator">*</span>
	</label>
	<g:select id="rsGralAsentamiento" name="rsGralAsentamiento.id"
		from="${com.rs.gral.RsGralAsentamiento.list()}" optionKey="id"
		required="" value="${rsGralDomicilioInstance?.rsGralAsentamiento?.id}"
		class="many-to-one" />
</div>

<div
	class="fieldcontain ${hasErrors(bean: rsGralDomicilioInstance, field: 'esFiscal', 'error')} ">
	<label for="esFiscal"> <g:message
			code="rsGralDomicilio.esFiscal.label" default="Es Fiscal" />

	</label>
	<g:checkBox name="esFiscal"
		value="${rsGralDomicilioInstance?.esFiscal}" />
</div>

<div
	class="fieldcontain ${hasErrors(bean: rsGralDomicilioInstance, field: 'comentarios', 'error')} ">
	<label for="comentarios"> <g:message
			code="rsGralDomicilio.comentarios.label" default="Comentarios" />

	</label>
	<g:textArea name="comentarios" cols="40" rows="5" maxlength="300"
		value="${rsGralDomicilioInstance?.comentarios}" />
</div>



