<%@ page import="com.rs.gral.RsGralDomicilio"%>

<g:if test="${rsGralDomicilioInstance?.persona?.id}">
	<div
		class="fieldcontain ${hasErrors(bean: rsGralDomicilioInstance, field: 'persona', 'error')} ">
		<label for="persona"> <g:message code="rsGralDomicilio.persona.label" default="Persona" /></label> 
		<label> ${rsGralDomicilioInstance?.persona}</label>
		<g:hiddenField name='persona.id' value='${rsGralDomicilioInstance?.persona?.id}' />
	</div>
</g:if>
<g:if test="${rsGralDomicilioInstance?.sucursal?.id}">
	<div
		class="fieldcontain ${hasErrors(bean: rsGralDomicilioInstance, field: 'sucursal', 'error')} ">
		<label for="sucursal"> <g:message code="rsGralDomicilio.sucursal.label" default="Sucursal" /></label> 
		<label> ${rsGralDomicilioInstance?.sucursal}</label>
		<g:hiddenField name='sucursal.id' value='${rsGralDomicilioInstance?.sucursal?.id}' />
	</div>
</g:if>

<g:if test="${rsGralDomicilioInstance?.empleo?.id}">
    <div class="fieldcontain ${hasErrors(bean: rsGralDomicilioInstance, field: 'empleo', 'error')} ">
        <label for="empleo"> <g:message code="rsGralDomicilio.empleo.label" default="Empleo" /></label>
        <label> ${rsGralDomicilioInstance?.empleo}</label>
        <g:hiddenField name='empleo.id' value='${rsGralDomicilioInstance?.empleo?.id}' />
    </div>
</g:if>

<div
	class="fieldcontain ${hasErrors(bean: rsGralDomicilioInstance, field: 'calle', 'error')} required">
	<label for="calle"> <g:message
			code="rsGralDomicilio.calle.label" default="Calle" /> <span
		class="required-indicator">*</span>
	</label>
	<g:textField name="calle" maxlength="100" required=""
		value="${rsGralDomicilioInstance?.calle}" />
</div>

<div
	class="fieldcontain ${hasErrors(bean: rsGralDomicilioInstance, field: 'numeroInterior', 'error')} ">
	<label for="numeroInterior"> <g:message
			code="rsGralDomicilio.numeroInterior.label" default="Numero Interior" />

	</label>
	<g:textField name="numeroInterior"
		value="${rsGralDomicilioInstance?.numeroInterior}" />
</div>

<div
	class="fieldcontain ${hasErrors(bean: rsGralDomicilioInstance, field: 'numeroExterior', 'error')} ">
	<label for="numeroExterior"> <g:message
			code="rsGralDomicilio.numeroExterior.label" default="Numero Exterior" />

	</label>
	<g:textField name="numeroExterior"
		value="${rsGralDomicilioInstance?.numeroExterior}" />
</div>


<div class="fieldcontain ${hasErrors(bean: rsGralDomicilioInstance, field: 'tipoVivienda', 'error')} required">
	<label for="tipoVivienda">
		<g:message code="rsGralDomicilio.tipoVivienda.label" default="Tipo Vivienda" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="tipoVivienda" from="${rsGralDomicilioInstance.constraints.tipoVivienda.inList}" required="" value="${rsGralDomicilioInstance?.tipoVivienda}" valueMessagePrefix="rsGralDomicilio.tipoVivienda"/>
</div>

<div
	class="fieldcontain ${hasErrors(bean: rsGralDomicilioInstance, field: 'entreCalles', 'error')} ">
	<label for="entreCalles"> <g:message
			code="rsGralDomicilio.entreCalles.label" default="Entre que calles" />

	</label>
	<g:textArea name="entreCalles" cols="40" rows="5" maxlength="300"
		value="${rsGralDomicilioInstance?.entreCalles}" />
</div>

<div
	class="fieldcontain ${hasErrors(bean: rsGralDomicilioInstance, field: 'aniosResidencia', 'error')} ">
	<label for="aniosResidencia"> <g:message
			code="rsGralDomicilio.aniosResidencia.label" default="A&ntilde;os de Residencia" />

	</label>
	<g:textField name="aniosResidencia"
		value="${rsGralDomicilioInstance?.aniosResidencia}" />
</div>

<div
	class="fieldcontain ${hasErrors(bean: rsGralDomicilioInstance, field: 'rsGralAsentamiento', 'error')} required">
	<label>Estado:</label>
	<g:select optionKey='id' optionValue='nombreEstado'
		name='rsGralEstado.nombreEstado' id='rsGralEstado.nombreEstado'
		noSelection="${['null':'Seleccione un Estado']}"
		from='${com.rs.gral.RsGralEstado.list()}'
		onchange="${remoteFunction(
					            controller:'rsGralEstado', 
					            action:'ajaxGetDelegacionMunicipio', 
					            params:'\'id=\' + escape(this.value)', 
					            onComplete:'updateDelegacionMunicipio(e)')}"
		class="many-to-one"></g:select>
</div>

<div
	class="fieldcontain ${hasErrors(bean: rsGralDomicilioInstance, field: 'rsGralAsentamiento', 'error')} required">
	<label>Delegaci&oacute;n o Municipio:</label>
	<g:select name='delegacionMunicipio' id='delegacionMunicipio' from=''
		onchange="${remoteFunction(
					            controller:'rsGralDelegacionMunicipio', 
					            action:'ajaxGetAsentamiento', 
					            params:'\'id=\' + escape(this.value)', 
				    	        onComplete:'updateAsentamiento(e)')}"
		class="many-to-one"></g:select>
</div>

<div class="fieldcontain ${hasErrors(bean: rsGralDomicilioInstance, field: 'rsGralAsentamiento', 'error')} required">
    <label>C&oacute;digo Postal:</label>
    <g:textField name='rsGralAsentamiento.codigoPostal' value=''
                 onKeyUp="${remoteFunction(
                         controller:'rsGralAsentamiento',
                         action:'ajaxGetCombos',
                         params:'\'cp=\' + escape(this.value)',
                         onComplete:'updateCombos(e)')}">
    </g:textField>
    <g:hiddenField name='rsGralAsentamiento.id' value='' />
</div>

<div
	class="fieldcontain ${hasErrors(bean: rsGralDomicilioInstance, field: 'rsGralAsentamiento', 'error')} required">
	<label>Colonia:</label>
	<g:select name='asentamiento' id='asentamiento' from=''
		onchange="${remoteFunction(
					            controller:'rsGralAsentamiento', 
					            action:'ajaxGetCodigoPostal', 
					            params:'\'id=\' + escape(this.value)', 
					            onComplete:'updateCodigoPostal(e)')}"
		class="many-to-one"></g:select>
</div>

<div
	class="fieldcontain ${hasErrors(bean: rsGralDomicilioInstance, field: 'esFiscal', 'error')} ">
	<label for="esFiscal"> <g:message
			code="rsGralDomicilio.esFiscal.label" default="Es Fiscal" />

	</label>
	<g:checkBox name="esFiscal"
		value="${rsGralDomicilioInstance?.esFiscal}" />
</div>

<div
	class="fieldcontain ${hasErrors(bean: rsGralDomicilioInstance, field: 'comentarios', 'error')} ">
	<label for="comentarios"> <g:message
			code="rsGralDomicilio.comentarios.label" default="Comentarios" />

	</label>
	<g:textArea name="comentarios" cols="40" rows="5" maxlength="300"
		value="${rsGralDomicilioInstance?.comentarios}" />
</div>



