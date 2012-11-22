<%@ page import="com.sim.tablaAmortizacion.TablaAmortizacionRegistro" %>



<div class="fieldcontain ${hasErrors(bean: tablaAmortizacionRegistroInstance, field: 'numeroPago', 'error')} required">
	<label for="numeroPago">
		<g:message code="tablaAmortizacionRegistro.numeroPago.label" default="Numero Pago" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="numeroPago" type="number" value="${tablaAmortizacionRegistroInstance.numeroPago}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: tablaAmortizacionRegistroInstance, field: 'fecha', 'error')} required">
	<label for="fecha">
		<g:message code="tablaAmortizacionRegistro.fecha.label" default="Fecha" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="fecha" precision="day"  value="${tablaAmortizacionRegistroInstance?.fecha}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: tablaAmortizacionRegistroInstance, field: 'impSaldoInicial', 'error')} required">
	<label for="impSaldoInicial">
		<g:message code="tablaAmortizacionRegistro.impSaldoInicial.label" default="Imp Saldo Inicial" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="impSaldoInicial" value="${fieldValue(bean: tablaAmortizacionRegistroInstance, field: 'impSaldoInicial')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: tablaAmortizacionRegistroInstance, field: 'tasaInteres', 'error')} required">
	<label for="tasaInteres">
		<g:message code="tablaAmortizacionRegistro.tasaInteres.label" default="Tasa Interes" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="tasaInteres" value="${fieldValue(bean: tablaAmortizacionRegistroInstance, field: 'tasaInteres')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: tablaAmortizacionRegistroInstance, field: 'impInteres', 'error')} required">
	<label for="impInteres">
		<g:message code="tablaAmortizacionRegistro.impInteres.label" default="Imp Interes" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="impInteres" value="${fieldValue(bean: tablaAmortizacionRegistroInstance, field: 'impInteres')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: tablaAmortizacionRegistroInstance, field: 'impIvaInteres', 'error')} required">
	<label for="impIvaInteres">
		<g:message code="tablaAmortizacionRegistro.impIvaInteres.label" default="Imp Iva Interes" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="impIvaInteres" value="${fieldValue(bean: tablaAmortizacionRegistroInstance, field: 'impIvaInteres')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: tablaAmortizacionRegistroInstance, field: 'impCapital', 'error')} required">
	<label for="impCapital">
		<g:message code="tablaAmortizacionRegistro.impCapital.label" default="Imp Capital" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="impCapital" value="${fieldValue(bean: tablaAmortizacionRegistroInstance, field: 'impCapital')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: tablaAmortizacionRegistroInstance, field: 'impPago', 'error')} required">
	<label for="impPago">
		<g:message code="tablaAmortizacionRegistro.impPago.label" default="Imp Pago" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="impPago" value="${fieldValue(bean: tablaAmortizacionRegistroInstance, field: 'impPago')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: tablaAmortizacionRegistroInstance, field: 'impSaldoFinal', 'error')} required">
	<label for="impSaldoFinal">
		<g:message code="tablaAmortizacionRegistro.impSaldoFinal.label" default="Imp Saldo Final" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="impSaldoFinal" value="${fieldValue(bean: tablaAmortizacionRegistroInstance, field: 'impSaldoFinal')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: tablaAmortizacionRegistroInstance, field: 'pagoPuntual', 'error')} ">
	<label for="pagoPuntual">
		<g:message code="tablaAmortizacionRegistro.pagoPuntual.label" default="Pago Puntual" />
		
	</label>
	<g:checkBox name="pagoPuntual" value="${tablaAmortizacionRegistroInstance?.pagoPuntual}" />
</div>

<div class="fieldcontain ${hasErrors(bean: tablaAmortizacionRegistroInstance, field: 'impInteresPagado', 'error')} ">
	<label for="impInteresPagado">
		<g:message code="tablaAmortizacionRegistro.impInteresPagado.label" default="Imp Interes Pagado" />
		
	</label>
	<g:field name="impInteresPagado" value="${fieldValue(bean: tablaAmortizacionRegistroInstance, field: 'impInteresPagado')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: tablaAmortizacionRegistroInstance, field: 'impIvaInteresPagado', 'error')} ">
	<label for="impIvaInteresPagado">
		<g:message code="tablaAmortizacionRegistro.impIvaInteresPagado.label" default="Imp Iva Interes Pagado" />
		
	</label>
	<g:field name="impIvaInteresPagado" value="${fieldValue(bean: tablaAmortizacionRegistroInstance, field: 'impIvaInteresPagado')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: tablaAmortizacionRegistroInstance, field: 'impCapitalPagado', 'error')} ">
	<label for="impCapitalPagado">
		<g:message code="tablaAmortizacionRegistro.impCapitalPagado.label" default="Imp Capital Pagado" />
		
	</label>
	<g:field name="impCapitalPagado" value="${fieldValue(bean: tablaAmortizacionRegistroInstance, field: 'impCapitalPagado')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: tablaAmortizacionRegistroInstance, field: 'impPagoPagado', 'error')} ">
	<label for="impPagoPagado">
		<g:message code="tablaAmortizacionRegistro.impPagoPagado.label" default="Imp Pago Pagado" />
		
	</label>
	<g:field name="impPagoPagado" value="${fieldValue(bean: tablaAmortizacionRegistroInstance, field: 'impPagoPagado')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: tablaAmortizacionRegistroInstance, field: 'pagado', 'error')} ">
	<label for="pagado">
		<g:message code="tablaAmortizacionRegistro.pagado.label" default="Pagado" />
		
	</label>
	<g:checkBox name="pagado" value="${tablaAmortizacionRegistroInstance?.pagado}" />
</div>

<div class="fieldcontain ${hasErrors(bean: tablaAmortizacionRegistroInstance, field: 'fechaPagoUltimo', 'error')} ">
	<label for="fechaPagoUltimo">
		<g:message code="tablaAmortizacionRegistro.fechaPagoUltimo.label" default="Fecha Pago Ultimo" />
		
	</label>
	<g:datePicker name="fechaPagoUltimo" precision="day"  value="${tablaAmortizacionRegistroInstance?.fechaPagoUltimo}" default="none" noSelection="['': '']" />
</div>

<div class="fieldcontain ${hasErrors(bean: tablaAmortizacionRegistroInstance, field: 'fechaValorCalculado', 'error')} ">
	<label for="fechaValorCalculado">
		<g:message code="tablaAmortizacionRegistro.fechaValorCalculado.label" default="Fecha Valor Calculado" />
		
	</label>
	<g:datePicker name="fechaValorCalculado" precision="day"  value="${tablaAmortizacionRegistroInstance?.fechaValorCalculado}" default="none" noSelection="['': '']" />
</div>

<div class="fieldcontain ${hasErrors(bean: tablaAmortizacionRegistroInstance, field: 'listaCobro', 'error')} required">
	<label for="listaCobro">
		<g:message code="tablaAmortizacionRegistro.listaCobro.label" default="Lista Cobro" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="listaCobro" name="listaCobro.id" from="${com.sim.listacobro.ListaCobro.list()}" optionKey="id" required="" value="${tablaAmortizacionRegistroInstance?.listaCobro?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: tablaAmortizacionRegistroInstance, field: 'prestamo', 'error')} required">
	<label for="prestamo">
		<g:message code="tablaAmortizacionRegistro.prestamo.label" default="Prestamo" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="prestamo" name="prestamo.id" from="${com.sim.credito.Prestamo.list()}" optionKey="id" required="" value="${tablaAmortizacionRegistroInstance?.prestamo?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: tablaAmortizacionRegistroInstance, field: 'tablaAmortizacionAccesorio', 'error')} ">
	<label for="tablaAmortizacionAccesorio">
		<g:message code="tablaAmortizacionRegistro.tablaAmortizacionAccesorio.label" default="Tabla Amortizacion Accesorio" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${tablaAmortizacionRegistroInstance?.tablaAmortizacionAccesorio?}" var="t">
    <li><g:link controller="tablaAmortizacionAccesorio" action="show" id="${t.id}">${t?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="tablaAmortizacionAccesorio" action="create" params="['tablaAmortizacionRegistro.id': tablaAmortizacionRegistroInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'tablaAmortizacionAccesorio.label', default: 'TablaAmortizacionAccesorio')])}</g:link>
</li>
</ul>

</div>

