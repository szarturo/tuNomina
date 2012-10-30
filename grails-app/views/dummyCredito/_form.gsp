<%@ page import="test.DummyCredito" %>



<div class="fieldcontain ${hasErrors(bean: dummyCreditoInstance, field: 'nombre', 'error')} ">
	<label for="nombre">
		<g:message code="dummyCredito.nombre.label" default="Nombre" />
		
	</label>
	<g:textField name="nombre" value="${dummyCreditoInstance?.nombre}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: dummyCreditoInstance, field: 'ingresos', 'error')} required">
	<label for="ingresos">
		<g:message code="dummyCredito.ingresos.label" default="Ingresos" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="ingresos" type="number" value="${dummyCreditoInstance.ingresos}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: dummyCreditoInstance, field: 'montoPrestamo', 'error')} required">
	<label for="montoPrestamo">
		<g:message code="dummyCredito.montoPrestamo.label" default="Monto Prestamo" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="montoPrestamo" type="number" value="${dummyCreditoInstance.montoPrestamo}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: dummyCreditoInstance, field: 'status', 'error')} ">
	<label for="status">
		<g:message code="dummyCredito.status.label" default="Status" />
		
	</label>
	<g:textField name="status" value="${dummyCreditoInstance?.status}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: dummyCreditoInstance, field: 'creditoOk', 'error')} ">
	<label for="creditoOk">
		<g:message code="dummyCredito.creditoOk.label" default="Credito Ok" />
		
	</label>
	<g:checkBox name="creditoOk" value="${dummyCreditoInstance?.creditoOk}" />
</div>

<div class="fieldcontain ${hasErrors(bean: dummyCreditoInstance, field: 'idCliente', 'error')} ">
	<label for="idCliente">
		<g:message code="dummyCredito.idCliente.label" default="Id Cliente" />
		
	</label>
	<g:textField name="idCliente" value="${dummyCreditoInstance?.idCliente}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: dummyCreditoInstance, field: 'idCredito', 'error')} ">
	<label for="idCredito">
		<g:message code="dummyCredito.idCredito.label" default="Id Credito" />
		
	</label>
	<g:textField name="idCredito" value="${dummyCreditoInstance?.idCredito}"/>
</div>

