<%@ page import="com.sim.pfin.pruebas.PfinPagoCredito" %>



<div class="fieldcontain ${hasErrors(bean: pfinPagoCreditoInstance, field: 'fechaPago', 'error')} required">
	<label for="fechaPago">
		<g:message code="pfinPagoCredito.fechaPago.label" default="Fecha Pago" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="fechaPago" precision="day"  value="${pfinPagoCreditoInstance?.fechaPago}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: pfinPagoCreditoInstance, field: 'importePago', 'error')} required">
	<label for="importePago">
		<g:message code="pfinPagoCredito.importePago.label" default="Importe Pago" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="importePago" value="${fieldValue(bean: pfinPagoCreditoInstance, field: 'importePago')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: pfinPagoCreditoInstance, field: 'prestamo', 'error')} required">
	<label for="prestamo">
		<g:message code="pfinPagoCredito.prestamo.label" default="Prestamo" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="prestamo" name="prestamo.id" from="${com.sim.credito.Prestamo.list()}" optionKey="id" required="" value="${pfinPagoCreditoInstance?.prestamo?.id}" class="many-to-one"/>
</div>

