<%@ page import="com.sim.credito.PrestamoPago" %>



<div class="fieldcontain ${hasErrors(bean: prestamoPagoInstance, field: 'fechaPago', 'error')} required">
	<label for="fechaPago">
		<g:message code="prestamoPago.fechaPago.label" default="Fecha Pago" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="fechaPago" precision="day"  value="${prestamoPagoInstance?.fechaPago}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: prestamoPagoInstance, field: 'importePago', 'error')} required">
	<label for="importePago">
		<g:message code="prestamoPago.importePago.label" default="Importe Pago" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="importePago" value="${fieldValue(bean: prestamoPagoInstance, field: 'importePago')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: prestamoPagoInstance, field: 'prestamo', 'error')} required">
	<label for="prestamo">
		<g:message code="prestamoPago.prestamo.label" default="Prestamo" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="prestamo" name="prestamo.id" from="${com.sim.credito.Prestamo.list()}" optionKey="id" required="" value="${prestamoPagoInstance?.prestamo?.id}" class="many-to-one"/>
</div>

