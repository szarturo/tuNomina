<%@ page import="com.sim.cliente.RsClienteReferencia" %>



<div class="fieldcontain ${hasErrors(bean: rsClienteReferenciaInstance, field: 'cliente', 'error')} required">
	<label for="cliente">
		<g:message code="rsClienteReferencia.cliente.label" default="Cliente" />
		<span class="required-indicator">*</span>
	</label>
    <label> ${rsClienteReferenciaInstance?.cliente}</label>
    <g:hiddenField name='cliente.id' value='${rsClienteReferenciaInstance?.cliente?.id}'/>
</div>

<div class="fieldcontain ${hasErrors(bean: rsClienteReferenciaInstance, field: 'nombreCompleto', 'error')} ">
	<label for="nombreCompleto">
		<g:message code="rsClienteReferencia.nombreCompleto.label" default="Nombre Completo" />
		
	</label>
	<g:textField name="nombreCompleto" maxlength="50" value="${rsClienteReferenciaInstance?.nombreCompleto}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: rsClienteReferenciaInstance, field: 'telefono', 'error')} required">
	<label for="telefono">
		<g:message code="rsClienteReferencia.telefono.label" default="Telefono" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="telefono" maxlength="15" required="" value="${rsClienteReferenciaInstance?.telefono}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: rsClienteReferenciaInstance, field: 'descripcionTelefono', 'error')} required">
	<label for="descripcionTelefono">
		<g:message code="rsClienteReferencia.descripcionTelefono.label" default="Descripcion Telefono" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="descripcionTelefono" name="descripcionTelefono.id" from="${com.sim.catalogo.SimCatDescTelefono.list()}" optionKey="id" required="" value="${rsClienteReferenciaInstance?.descripcionTelefono?.id}" class="many-to-one"/>
</div>

