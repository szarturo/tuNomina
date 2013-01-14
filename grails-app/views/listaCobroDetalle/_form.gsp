<%@ page import="com.sim.listacobro.ListaCobroDetalle" %>



<div class="fieldcontain ${hasErrors(bean: listaCobroDetalleInstance, field: 'estatus', 'error')} required">
	<label for="estatus">
		<g:message code="listaCobroDetalle.estatus.label" default="Estatus" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="estatus" from="${com.sim.listacobro.ListaCobroDetalleEstatus?.values()}" keys="${com.sim.listacobro.ListaCobroDetalleEstatus.values()*.name()}" required="" value="${listaCobroDetalleInstance?.estatus?.name()}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: listaCobroDetalleInstance, field: 'listaCobro', 'error')} required">
	<label for="listaCobro">
		<g:message code="listaCobroDetalle.listaCobro.label" default="Lista Cobro" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="listaCobro" name="listaCobro.id" from="${com.sim.listacobro.ListaCobro.list()}" optionKey="id" required="" value="${listaCobroDetalleInstance?.listaCobro?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: listaCobroDetalleInstance, field: 'amortizacion', 'error')} required">
	<label for="amortizacion">
		<g:message code="listaCobroDetalle.amortizacion.label" default="Amortizacion" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="amortizacion" name="amortizacion.id" from="${com.sim.tablaAmortizacion.TablaAmortizacionRegistro.list()}" optionKey="id" required="" value="${listaCobroDetalleInstance?.amortizacion?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: listaCobroDetalleInstance, field: 'pago', 'error')} ">
	<label for="pago">
		<g:message code="listaCobroDetalle.pago.label" default="Pago" />
		
	</label>
	<g:select id="pago" name="pago.id" from="${com.sim.credito.PrestamoPago.list()}" optionKey="id" value="${listaCobroDetalleInstance?.pago?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

