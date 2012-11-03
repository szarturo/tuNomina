<%@ page import="test.DummyCobranza" %>



<div class="fieldcontain ${hasErrors(bean: dummyCobranzaInstance, field: 'field1', 'error')} required">
	<label for="field1">
		<g:message code="dummyCobranza.field1.label" default="Field1" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="field1" required="" value="${dummyCobranzaInstance?.field1}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: dummyCobranzaInstance, field: 'field2', 'error')} required">
	<label for="field2">
		<g:message code="dummyCobranza.field2.label" default="Field2" />
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
		<g:message code="dummyCobranza.field5.label" default="Field5" />
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

