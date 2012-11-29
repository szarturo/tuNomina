<%@ page import="com.rs.gral.RsGralDomicilio"%>


<g:if test="${rsGralDomicilioInstance?.persona?.id}">
	<div
		class="fieldcontain ${hasErrors(bean: rsGralDomicilioInstance, field: 'persona', 'error')} ">
		<label for="persona"> <g:message
				code="rsGralDomicilio.persona.label" default="Persona" />

		</label> <label> ${rsGralDomicilioInstance?.persona}
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
	class="fieldcontain ${hasErrors(bean: rsGralDomicilioInstance, field: 'comentarios', 'error')} ">
	<label for="comentarios"> <g:message
			code="rsGralDomicilio.comentarios.label" default="Ciudad, Delegación, Colonia, Código Postal" />

	</label>
	<g:textArea name="comentarios" cols="40" rows="5" maxlength="300"
		value="${rsGralDomicilioInstance?.comentarios}" />
</div>

<div
	class="fieldcontain ${hasErrors(bean: rsGralDomicilioInstance, field: 'entreCalles', 'error')} ">
	<label for="entreCalles"> <g:message
			code="rsGralDomicilio.entreCalles.label" default="Entre que calles" />

	</label>
	<g:textArea name="entreCalles" cols="40" rows="5" maxlength="300"
		value="${rsGralDomicilioInstance?.entreCalles}" />
</div>

<div
	class="fieldcontain ${hasErrors(bean: rsGralDomicilioInstance, field: 'aniosResidencia', 'error')} ">
	<label for="aniosResidencia"> <g:message
			code="rsGralDomicilio.aniosResidencia.label" default="A&ntilde;os de Residencia" />

	</label>
	<g:textField name="aniosResidencia"
		value="${rsGralDomicilioInstance?.aniosResidencia}" />
</div>


<div
	class="fieldcontain ${hasErrors(bean: rsGralDomicilioInstance, field: 'esFiscal', 'error')} ">
	<label for="esFiscal"> <g:message
			code="rsGralDomicilio.esFiscal.label" default="Es Fiscal" />

	</label>
	<g:checkBox name="esFiscal"
		value="${rsGralDomicilioInstance?.esFiscal}" />
</div>



