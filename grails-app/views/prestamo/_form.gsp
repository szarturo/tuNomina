<%@ page import="com.sim.credito.Prestamo" %>



<div class="fieldcontain ${hasErrors(bean: prestamoInstance, field: 'clavePrestamo', 'error')} required">
	<label for="clavePrestamo">
		<g:message code="prestamo.clavePrestamo.label" default="Clave Prestamo" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="clavePrestamo" maxlength="20" required="" value="${prestamoInstance?.clavePrestamo}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: prestamoInstance, field: 'cliente', 'error')} required">
	<label for="cliente">
		<g:message code="prestamo.cliente.label" default="Cliente" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="cliente" name="cliente.id" from="${com.sim.cliente.RsCliente.list()}" optionKey="id" required="" value="${prestamoInstance?.cliente?.id}" class="many-to-one"/>
</div>

