<%@ page import="com.sim.calendario.Dependencia" %>



<div class="fieldcontain ${hasErrors(bean: dependenciaInstance, field: 'nombre', 'error')} ">
	<label for="nombre">
		<g:message code="dependencia.nombre.label" default="Nombre" />
		
	</label>
	<g:textField name="nombre" value="${dependenciaInstance?.nombre}"/>
</div>

