<%@ page import="com.sim.cliente.RsClienteCtaBancaria" %>

<div class="fieldcontain ${hasErrors(bean: rsClienteCtaBancariaInstance, field: 'cliente', 'error')} required">
	<label for="cliente">
		<g:message code="rsClienteCtaBancaria.cliente.label" default="Cliente" />
		<span class="required-indicator">*</span>
	</label>
    <label> ${rsClienteCtaBancariaInstance?.cliente}</label>
    <g:hiddenField name='cliente.id' value='${rsClienteCtaBancariaInstance?.cliente?.id}'/>
</div>

<div class="fieldcontain ${hasErrors(bean: rsClienteCtaBancariaInstance, field: 'banco', 'error')} required">
	<label for="banco">
		<g:message code="rsClienteCtaBancaria.banco.label" default="Banco" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="banco" name="banco.id" from="${com.sim.catalogo.SimCatBanco.list()}" optionKey="id" required="" value="${rsClienteCtaBancariaInstance?.banco?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: rsClienteCtaBancariaInstance, field: 'depositoPrestamo', 'error')} ">
	<label for="depositoPrestamo">
		<g:message code="rsClienteCtaBancaria.depositoPrestamo.label" default="Deposito Prestamo" />
		
	</label>
	<g:checkBox name="depositoPrestamo" value="${rsClienteCtaBancariaInstance?.depositoPrestamo}" />
</div>

<div class="fieldcontain ${hasErrors(bean: rsClienteCtaBancariaInstance, field: 'numeroDeCuenta', 'error')} required">
	<label for="numeroDeCuenta">
		<g:message code="rsClienteCtaBancaria.numeroDeCuenta.label" default="Numero De Cuenta" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="numeroDeCuenta" maxlength="25" required="" value="${rsClienteCtaBancariaInstance?.numeroDeCuenta}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: rsClienteCtaBancariaInstance, field: 'clabe', 'error')} required">
	<label for="clabe">
		<g:message code="rsClienteCtaBancaria.clabe.label" default="Clabe" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="clabe" maxlength="25" required="" value="${rsClienteCtaBancariaInstance?.clabe}"/>
</div>

