<%@ page import="test.DummyCobranza" %>

<div class="fieldcontain ${hasErrors(bean: dummyCobranzaInstance, field: 'detalleRegistro', 'error')} required">
	<label for="detalleRegistro">
		<g:message code="rsGralDomicilio.detalleRegistro.label" default="Detalle Registro" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="detalleRegistro.id" from="${com.sim.tablaAmortizacion.TablaAmortizacionRegistro.list()}" value="${dummyCobranzaInstance?.detalleRegistro?.id}" optionKey="id" />
</div>

<div class="fieldcontain ${hasErrors(bean: dummyCobranzaInstance, field: 'field1', 'error')} required">
	<label for="field1">
		<g:message code="dummyCobranza.field1.label" default="A Pagar" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="field1" required="" value="${dummyCobranzaInstance?.field1}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: dummyCobranzaInstance, field: 'field2', 'error')} required">
	<label for="field2">
		<g:message code="dummyCobranza.field2.label" default="Cobrado" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="field2" required="" value="${dummyCobranzaInstance?.field2}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: dummyCobranzaInstance, field: 'field3', 'error')} required">
	<label for="field3">
		<g:message code="dummyCobranza.field3.label" default="Field3" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="field3" required="" value="${dummyCobranzaInstance?.field3}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: dummyCobranzaInstance, field: 'field4', 'error')} required">
	<label for="field4">
		<g:message code="dummyCobranza.field4.label" default="Field4" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="field4" required="" value="${dummyCobranzaInstance?.field4}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: dummyCobranzaInstance, field: 'field5', 'error')} required">
	<label for="field5">
		<g:message code="dummyCobranza.field5.label" default="Fecha Aplicacion" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="field5" value="${dummyCobranzaInstance?.field5}" />
</div>

<div class="fieldcontain ${hasErrors(bean: dummyCobranzaInstance, field: 'field6', 'error')} required">
	<label for="field6">
		<g:message code="dummyCobranza.field6.label" default="Field6" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="field6" required="" value="${dummyCobranzaInstance?.field6}"/>
</div>

