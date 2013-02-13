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

<g:if test="${rsClienteInstance?.id}">
    <div class="fieldcontain ${hasErrors(bean: rsClienteInstance, field: 'cuentaBancaria', 'error')} ">
        <label for="cuentaBancaria">
            <g:message code="rsCliente.cuentaBancaria.label" default="Cuenta Bancaria" />

        </label>
        <ul class="one-to-many">
            <g:each in="${rsClienteInstance?.cuentaBancaria?}" var="t">
                <li><g:link controller="rsClienteCtaBancaria" action="show" id="${t.id}">${t?.encodeAsHTML()}</g:link></li>
            </g:each>
            <li class="add">
                <g:link controller="rsClienteCtaBancaria" action="create" params="['rsCliente.id': rsClienteInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'rsClienteCtaBancaria.label', default: 'Cuenta Bancaria')])}</g:link>
            </li>
        </ul>
    </div>
    <div class="fieldcontain ${hasErrors(bean: rsClienteInstance, field: 'clienteEmpleo', 'error')} ">
        <label for="clienteEmpleo">
            <g:message code="rsCliente.clienteEmpleo.label" default="Empleo" />

        </label>
        <ul class="one-to-many">
            <g:each in="${rsClienteInstance?.clienteEmpleo?}" var="t">
                <li><g:link controller="rsClienteEmpleo" action="show" id="${t.id}">${t?.encodeAsHTML()}</g:link></li>
            </g:each>
            <li class="add">
                <g:link controller="rsClienteEmpleo" action="create" params="['rsCliente.id': rsClienteInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'rsClienteEmpleo.label', default: 'Empleo')])}</g:link>
            </li>
        </ul>
    </div>
    <div class="fieldcontain ${hasErrors(bean: rsClienteInstance, field: 'referenciasClientes', 'error')} ">
        <label for="referenciasClientes">
            <g:message code="rsCliente.referenciasClientes.label" default="Referencia" />

        </label>
        <ul class="one-to-many">
            <g:each in="${rsClienteInstance?.referenciasClientes?}" var="t">
                <li><g:link controller="rsClienteReferencia" action="show" id="${t.id}">${t?.encodeAsHTML()}</g:link></li>
            </g:each>
            <li class="add">
                <g:link controller="rsClienteReferencia" action="create" params="['rsCliente.id': rsClienteInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'rsClienteReferencia.label', default: 'Referencia')])}</g:link>
            </li>
        </ul>
    </div>
</g:if>

<g:hiddenField name="persona.id" value="${rsClienteInstance?.persona?.id}" />
