<%@ page import="test.DummyPersona" %>



<div class="fieldcontain ${hasErrors(bean: dummyPersonaInstance, field: 'nombre', 'error')} ">
	<label for="nombre">
		<g:message code="dummyPersona.nombre.label" default="Nombre" />
		
	</label>
	<g:textField name="nombre" value="${dummyPersonaInstance?.nombre}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: dummyPersonaInstance, field: 'apellido', 'error')} ">
	<label for="apellido">
		<g:message code="dummyPersona.apellido.label" default="Apellido" />
		
	</label>
	<g:textField name="apellido" value="${dummyPersonaInstance?.apellido}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: dummyPersonaInstance, field: 'calle', 'error')} ">
	<label for="calle">
		<g:message code="dummyPersona.calle.label" default="Calle" />
		
	</label>
	<g:textField name="calle" value="${dummyPersonaInstance?.calle}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: dummyPersonaInstance, field: 'numero', 'error')} ">
	<label for="numero">
		<g:message code="dummyPersona.numero.label" default="Numero" />
		
	</label>
	<g:textField name="numero" value="${dummyPersonaInstance?.numero}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: dummyPersonaInstance, field: 'codigoPostal', 'error')} ">
	<label for="codigoPostal">
		<g:message code="dummyPersona.codigoPostal.label" default="Codigo Postal" />
		
	</label>
	<g:textField name="codigoPostal" value="${dummyPersonaInstance?.codigoPostal}"/>
</div>

