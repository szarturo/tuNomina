<%@ page import="com.sim.calendario.SimCatEvento" %>



<div class="fieldcontain ${hasErrors(bean: eventoInstance, field: 'evento', 'error')} ">
	<label for="evento">
		<g:message code="evento.evento.label" default="Evento" />
		
	</label>
	<g:textField name="evento" value="${eventoInstance?.evento}"/>
</div>

