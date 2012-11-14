<%@ page import="com.sim.tablaAmortizacion.TablaAmortizacion" %>



<div class="fieldcontain ${hasErrors(bean: tablaAmortizacionInstance, field: 'numeroPago', 'error')} required">
	<label for="numeroPago">
		<g:message code="tablaAmortizacion.numeroPago.label" default="Numero Pago" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="numeroPago" type="number" value="${tablaAmortizacionInstance.numeroPago}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: tablaAmortizacionInstance, field: 'fecha', 'error')} required">
	<label for="fecha">
		<g:message code="tablaAmortizacion.fecha.label" default="Fecha" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="fecha" precision="day"  value="${tablaAmortizacionInstance?.fecha}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: tablaAmortizacionInstance, field: 'impSaldoInicial', 'error')} required">
	<label for="impSaldoInicial">
		<g:message code="tablaAmortizacion.impSaldoInicial.label" default="Imp Saldo Inicial" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="impSaldoInicial" value="${fieldValue(bean: tablaAmortizacionInstance, field: 'impSaldoInicial')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: tablaAmortizacionInstance, field: 'tasaInteres', 'error')} required">
	<label for="tasaInteres">
		<g:message code="tablaAmortizacion.tasaInteres.label" default="Tasa Interes" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="tasaInteres" value="${fieldValue(bean: tablaAmortizacionInstance, field: 'tasaInteres')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: tablaAmortizacionInstance, field: 'impInteres', 'error')} required">
	<label for="impInteres">
		<g:message code="tablaAmortizacion.impInteres.label" default="Imp Interes" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="impInteres" value="${fieldValue(bean: tablaAmortizacionInstance, field: 'impInteres')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: tablaAmortizacionInstance, field: 'impIvaInteres', 'error')} required">
	<label for="impIvaInteres">
		<g:message code="tablaAmortizacion.impIvaInteres.label" default="Imp Iva Interes" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="impIvaInteres" value="${fieldValue(bean: tablaAmortizacionInstance, field: 'impIvaInteres')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: tablaAmortizacionInstance, field: 'impCapital', 'error')} required">
	<label for="impCapital">
		<g:message code="tablaAmortizacion.impCapital.label" default="Imp Capital" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="impCapital" value="${fieldValue(bean: tablaAmortizacionInstance, field: 'impCapital')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: tablaAmortizacionInstance, field: 'impPago', 'error')} required">
	<label for="impPago">
		<g:message code="tablaAmortizacion.impPago.label" default="Imp Pago" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="impPago" value="${fieldValue(bean: tablaAmortizacionInstance, field: 'impPago')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: tablaAmortizacionInstance, field: 'impSaldoFinal', 'error')} required">
	<label for="impSaldoFinal">
		<g:message code="tablaAmortizacion.impSaldoFinal.label" default="Imp Saldo Final" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="impSaldoFinal" value="${fieldValue(bean: tablaAmortizacionInstance, field: 'impSaldoFinal')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: tablaAmortizacionInstance, field: 'pagoPuntual', 'error')} ">
	<label for="pagoPuntual">
		<g:message code="tablaAmortizacion.pagoPuntual.label" default="Pago Puntual" />
		
	</label>
	<g:checkBox name="pagoPuntual" value="${tablaAmortizacionInstance?.pagoPuntual}" />
</div>

<div class="fieldcontain ${hasErrors(bean: tablaAmortizacionInstance, field: 'impInteresPagado', 'error')} ">
	<label for="impInteresPagado">
		<g:message code="tablaAmortizacion.impInteresPagado.label" default="Imp Interes Pagado" />
		
	</label>
	<g:field name="impInteresPagado" value="${fieldValue(bean: tablaAmortizacionInstance, field: 'impInteresPagado')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: tablaAmortizacionInstance, field: 'impIvaInteresPagado', 'error')} ">
	<label for="impIvaInteresPagado">
		<g:message code="tablaAmortizacion.impIvaInteresPagado.label" default="Imp Iva Interes Pagado" />
		
	</label>
	<g:field name="impIvaInteresPagado" value="${fieldValue(bean: tablaAmortizacionInstance, field: 'impIvaInteresPagado')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: tablaAmortizacionInstance, field: 'impCapitalPagado', 'error')} ">
	<label for="impCapitalPagado">
		<g:message code="tablaAmortizacion.impCapitalPagado.label" default="Imp Capital Pagado" />
		
	</label>
	<g:field name="impCapitalPagado" value="${fieldValue(bean: tablaAmortizacionInstance, field: 'impCapitalPagado')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: tablaAmortizacionInstance, field: 'impPagoPagado', 'error')} ">
	<label for="impPagoPagado">
		<g:message code="tablaAmortizacion.impPagoPagado.label" default="Imp Pago Pagado" />
		
	</label>
	<g:field name="impPagoPagado" value="${fieldValue(bean: tablaAmortizacionInstance, field: 'impPagoPagado')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: tablaAmortizacionInstance, field: 'pagado', 'error')} ">
	<label for="pagado">
		<g:message code="tablaAmortizacion.pagado.label" default="Pagado" />
		
	</label>
	<g:checkBox name="pagado" value="${tablaAmortizacionInstance?.pagado}" />
</div>

<div class="fieldcontain ${hasErrors(bean: tablaAmortizacionInstance, field: 'fechaPagoUltimo', 'error')} required">
	<label for="fechaPagoUltimo">
		<g:message code="tablaAmortizacion.fechaPagoUltimo.label" default="Fecha Pago Ultimo" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="fechaPagoUltimo" precision="day"  value="${tablaAmortizacionInstance?.fechaPagoUltimo}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: tablaAmortizacionInstance, field: 'fechaValorCalculado', 'error')} required">
	<label for="fechaValorCalculado">
		<g:message code="tablaAmortizacion.fechaValorCalculado.label" default="Fecha Valor Calculado" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="fechaValorCalculado" precision="day"  value="${tablaAmortizacionInstance?.fechaValorCalculado}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: tablaAmortizacionInstance, field: 'prestamo', 'error')} required">
	<label for="prestamo">
		<g:message code="tablaAmortizacion.prestamo.label" default="Prestamo" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="prestamo" name="prestamo.id" from="${com.sim.credito.Prestamo.list()}" optionKey="id" required="" value="${tablaAmortizacionInstance?.prestamo?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: tablaAmortizacionInstance, field: 'tablaAmortizacionAccesorio', 'error')} ">
	<label for="tablaAmortizacionAccesorio">
		<g:message code="tablaAmortizacion.tablaAmortizacionAccesorio.label" default="Tabla Amortizacion Accesorio" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${tablaAmortizacionInstance?.tablaAmortizacionAccesorio?}" var="t">
    <li><g:link controller="tablaAmortizacionAccesorio" action="show" id="${t.id}">${t?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="tablaAmortizacionAccesorio" action="create" params="['tablaAmortizacion.id': tablaAmortizacionInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'tablaAmortizacionAccesorio.label', default: 'TablaAmortizacionAccesorio')])}</g:link>
</li>
</ul>

</div>

