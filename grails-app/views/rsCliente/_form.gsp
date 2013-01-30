<%@ page import="com.sim.cliente.RsCliente" %>



<div class="fieldcontain ${hasErrors(bean: rsClienteInstance, field: 'persona', 'error')} required">
	<label for="persona">
		<g:message code="rsCliente.persona.label" default="Persona" />
		<span class="required-indicator">*</span>
	</label>
	${rsClienteInstance?.persona}
</div>

<div class="fieldcontain ${hasErrors(bean: rsClienteInstance, field: 'dependencia', 'error')} ">
	<label for="dependencia">
		<g:message code="rsCliente.dependencia.label" default="Dependencia" />
		
	</label>
	<g:select name="dependencia" from="${com.sim.entidad.EntDependencia.list()}" multiple="multiple" optionKey="id" size="5" value="${rsClienteInstance?.dependencia*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: rsClienteInstance, field: 'numeroDeNomina', 'error')} ">
	<label for="numeroDeNomina">
		<g:message code="rsCliente.numeroDeNomina.label" default="Numero De Nomina" />
		
	</label>
	<g:textField name="numeroDeNomina" value="${rsClienteInstance?.numeroDeNomina}"/>
</div>

<g:hiddenField name="persona.id" value="${rsClienteInstance?.persona?.id}" />
