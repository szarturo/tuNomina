<%@ page import="com.sim.listacobro.ListaCobro" %>



<div class="fieldcontain ${hasErrors(bean: listaCobroInstance, field: 'dependencia', 'error')} required">
	<label for="dependencia">
		<g:message code="listaCobro.dependencia.label" default="Dependencia" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="dependencia" name="dependencia.id" from="${com.sim.entidad.EntDependencia.list()}" optionKey="id" required="" value="${listaCobroInstance?.dependencia?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: listaCobroInstance, field: 'anio', 'error')} required">
	<label for="anio">
		<g:message code="listaCobro.anio.label" default="Anio" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="anio" from="${2012..2020}" class="range" required="" value="${fieldValue(bean: listaCobroInstance, field: 'anio')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: listaCobroInstance, field: 'periodicidad', 'error')} required">
	<label for="periodicidad">
		<g:message code="listaCobro.periodicidad.label" default="Periodicidad" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="periodicidad" name="periodicidad.id" from="${com.sim.catalogo.SimCatPeriodicidad.list()}" optionKey="id" required="" value="${listaCobroInstance?.periodicidad?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: listaCobroInstance, field: 'numeroPago', 'error')} required">
	<label for="numeroPago">
		<g:message code="listaCobro.numeroPago.label" default="Numero Pago" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="numeroPago" type="number" value="${listaCobroInstance.numeroPago}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: listaCobroInstance, field: 'fechaInicio', 'error')} ">
	<label for="fechaInicio">
		<g:message code="listaCobro.fechaInicio.label" default="Fecha Inicio" />
		
	</label>
	<g:datePicker name="fechaInicio" precision="day"  value="${listaCobroInstance?.fechaInicio}" default="none" noSelection="['': '']" />
</div>

<div class="fieldcontain ${hasErrors(bean: listaCobroInstance, field: 'fechaFin', 'error')} ">
	<label for="fechaFin">
		<g:message code="listaCobro.fechaFin.label" default="Fecha Fin" />
		
	</label>
	<g:datePicker name="fechaFin" precision="day"  value="${listaCobroInstance?.fechaFin}" default="none" noSelection="['': '']" />
</div>

<div class="fieldcontain ${hasErrors(bean: listaCobroInstance, field: 'estatus', 'error')} required">
	<label for="estatus">
		<g:message code="listaCobro.estatus.label" default="Estatus" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="estatus" name="estatus.id" from="${com.sim.catalogo.SimCatListaCobroEstatus.list()}" optionKey="id" required="" value="${listaCobroInstance?.estatus?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: listaCobroInstance, field: 'listaCobroProceso', 'error')} ">
	<label for="listaCobroProceso">
		<g:message code="listaCobro.listaCobroProceso.label" default="Lista Cobro Proceso" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${listaCobroInstance?.listaCobroProceso?}" var="l">
    <li><g:link controller="listaCobroProceso" action="show" id="${l.id}">${l?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="listaCobroProceso" action="create" params="['listaCobro.id': listaCobroInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'listaCobroProceso.label', default: 'ListaCobroProceso')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: listaCobroInstance, field: 'detalles', 'error')} ">
	<label for="detalles">
		<g:message code="listaCobro.detalles.label" default="Detalles" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${listaCobroInstance?.detalles?}" var="d">
    <li><g:link controller="listaCobroDetalle" action="show" id="${d.id}">${d?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="listaCobroDetalle" action="create" params="['listaCobro.id': listaCobroInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'listaCobroDetalle.label', default: 'ListaCobroDetalle')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: listaCobroInstance, field: 'amortizaciones', 'error')} ">
	<label for="amortizaciones">
		<g:message code="listaCobro.amortizaciones.label" default="Amortizaciones" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${listaCobroInstance?.amortizaciones?}" var="a">
    <li><g:link controller="tablaAmortizacionRegistro" action="show" id="${a.id}">${a?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="tablaAmortizacionRegistro" action="create" params="['listaCobro.id': listaCobroInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'tablaAmortizacionRegistro.label', default: 'TablaAmortizacionRegistro')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: listaCobroInstance, field: 'parcialidades', 'error')} ">
	<label for="parcialidades">
		<g:message code="listaCobro.parcialidades.label" default="Parcialidades" />
		
	</label>
	<g:checkBox name="parcialidades" value="${listaCobroInstance?.parcialidades}" />
</div>

