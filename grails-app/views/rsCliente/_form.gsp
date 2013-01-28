<%@ page import="com.sim.cliente.RsCliente" %>



<div class="fieldcontain ${hasErrors(bean: rsClienteInstance, field: 'persona', 'error')} required">
	<label for="persona">
		<g:message code="rsCliente.persona.label" default="Persona" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="persona" name="persona.id" from="${com.rs.gral.RsPersona.list()}" optionKey="id" required="" value="${rsClienteInstance?.persona?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: rsClienteInstance, field: 'dependencia', 'error')} required">
	<label for="dependencia">
		<g:message code="rsCliente.dependencia.label" default="Dependencia" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="dependencia" name="dependencia.id" from="${com.sim.entidad.EntDependencia.list()}" optionKey="id" required="" value="${rsClienteInstance?.dependencia?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: rsClienteInstance, field: 'numeroDeNomina', 'error')} ">
	<label for="numeroDeNomina">
		<g:message code="rsCliente.numeroDeNomina.label" default="Numero De Nomina" />
		
	</label>
	<g:textField name="numeroDeNomina" value="${rsClienteInstance?.numeroDeNomina}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: rsClienteInstance, field: 'creditos', 'error')} ">
	<label for="creditos">
		<g:message code="rsCliente.creditos.label" default="Creditos" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${rsClienteInstance?.creditos?}" var="c">
    <li><g:link controller="prestamo" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="prestamo" action="create" params="['rsCliente.id': rsClienteInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'prestamo.label', default: 'Prestamo')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: rsClienteInstance, field: 'cuentas', 'error')} ">
	<label for="cuentas">
		<g:message code="rsCliente.cuentas.label" default="Cuentas" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${rsClienteInstance?.cuentas?}" var="c">
    <li><g:link controller="pfinCuenta" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="pfinCuenta" action="create" params="['rsCliente.id': rsClienteInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'pfinCuenta.label', default: 'PfinCuenta')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: rsClienteInstance, field: 'cuentaBancaria', 'error')} ">
	<label for="cuentaBancaria">
		<g:message code="rsCliente.cuentaBancaria.label" default="Cuenta Bancaria" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${rsClienteInstance?.cuentaBancaria?}" var="c">
    <li><g:link controller="rsClienteCtaBancaria" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="rsClienteCtaBancaria" action="create" params="['rsCliente.id': rsClienteInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'rsClienteCtaBancaria.label', default: 'RsClienteCtaBancaria')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: rsClienteInstance, field: 'clienteEmpleo', 'error')} ">
	<label for="clienteEmpleo">
		<g:message code="rsCliente.clienteEmpleo.label" default="Cliente Empleo" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${rsClienteInstance?.clienteEmpleo?}" var="c">
    <li><g:link controller="rsClienteEmpleo" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="rsClienteEmpleo" action="create" params="['rsCliente.id': rsClienteInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'rsClienteEmpleo.label', default: 'RsClienteEmpleo')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: rsClienteInstance, field: 'referenciasClientes', 'error')} ">
	<label for="referenciasClientes">
		<g:message code="rsCliente.referenciasClientes.label" default="Referencias Clientes" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${rsClienteInstance?.referenciasClientes?}" var="r">
    <li><g:link controller="rsClienteReferencia" action="show" id="${r.id}">${r?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="rsClienteReferencia" action="create" params="['rsCliente.id': rsClienteInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'rsClienteReferencia.label', default: 'RsClienteReferencia')])}</g:link>
</li>
</ul>

</div>

