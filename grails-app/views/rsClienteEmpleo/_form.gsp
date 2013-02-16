<%@ page import="com.sim.cliente.RsClienteEmpleo" %>

<div class="fieldcontain ${hasErrors(bean: rsClienteEmpleoInstance, field: 'cliente', 'error')} required">
	<label for="cliente">
		<g:message code="rsClienteEmpleo.cliente.label" default="Cliente" />
		<span class="required-indicator">*</span>
	</label>
    <label> ${rsClienteEmpleoInstance?.cliente}</label>
    <g:hiddenField name='cliente.id' value='${rsClienteEmpleoInstance?.cliente?.id}'/>
</div>

<div class="fieldcontain ${hasErrors(bean: rsClienteEmpleoInstance, field: 'fechaIngreso', 'error')} required">
	<label for="fechaIngreso">
		<g:message code="rsClienteEmpleo.fechaIngreso.label" default="Fecha Ingreso" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="fechaIngreso" precision="day"  value="${rsClienteEmpleoInstance?.fechaIngreso}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: rsClienteEmpleoInstance, field: 'area', 'error')} required">
	<label for="area">
		<g:message code="rsClienteEmpleo.area.label" default="Area" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="area" maxlength="50" required="" value="${rsClienteEmpleoInstance?.area}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: rsClienteEmpleoInstance, field: 'ingresoMensual', 'error')} required">
	<label for="ingresoMensual">
		<g:message code="rsClienteEmpleo.ingresoMensual.label" default="Ingreso Mensual" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="ingresoMensual" value="${fieldValue(bean: rsClienteEmpleoInstance, field: 'ingresoMensual')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: rsClienteEmpleoInstance, field: 'domicilio', 'error')} ">
	<label for="domicilio">
		<g:message code="rsClienteEmpleo.domicilio.label" default="Domicilio" />
		
	</label>
	<g:select id="domicilio" name="domicilio.id" from="${com.rs.gral.RsGralDomicilio.list()}" optionKey="id" value="${rsClienteEmpleoInstance?.domicilio?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>
