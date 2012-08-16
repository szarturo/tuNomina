<%@ page import="com.sim.credito.Prestamo" %>



<div class="fieldcontain ${hasErrors(bean: prestamoInstance, field: 'clavePrestamo', 'error')} required">
	<label for="clavePrestamo">
		<g:message code="prestamo.clavePrestamo.label" default="Clave Prestamo" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="clavePrestamo" maxlength="20" required="" value="${prestamoInstance?.clavePrestamo}"/>
</div>

