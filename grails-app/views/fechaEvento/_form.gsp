<%@ page import="com.sim.calendario.FechaEvento" %>



<div class="fieldcontain ${hasErrors(bean: fechaEventoInstance, field: 'evento', 'error')} required">
	<label for="evento">
		<g:message code="fechaEvento.evento.label" default="Evento" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="evento" name="evento.id" from="${com.sim.calendario.Evento.list()}" optionKey="id" required="" value="${fechaEventoInstance?.evento?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: fechaEventoInstance, field: 'dependencia', 'error')} required">
	<label for="dependencia">
		<g:message code="fechaEvento.dependencia.label" default="Dependencia" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="dependencia" name="dependencia.id" from="${com.sim.calendario.Dependencia.list()}" optionKey="id" required="" value="${fechaEventoInstance?.dependencia?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: fechaEventoInstance, field: 'fecha', 'error')} required">
	<label for="fecha">
		<g:message code="fechaEvento.fecha.label" default="Fecha" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="fecha" precision="day"  value="${fechaEventoInstance?.fecha}"  />
</div>

