<%@ page import="com.sim.credito.Prestamo" %>



<div class="fieldcontain ${hasErrors(bean: prestamoInstance, field: 'cliente', 'error')} required">
	<label for="cliente">
		<g:message code="prestamo.cliente.label" default="Cliente" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="cliente" name="cliente.id" from="${com.sim.cliente.RsCliente.list()}" optionKey="id" required="" value="${prestamoInstance?.cliente?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: prestamoInstance, field: 'clavePrestamo', 'error')} required">
	<label for="clavePrestamo">
		<g:message code="prestamo.clavePrestamo.label" default="Clave Prestamo" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="clavePrestamo" maxlength="20" required="" value="${prestamoInstance?.clavePrestamo}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: prestamoInstance, field: 'folioSolicitud', 'error')} required">
	<label for="folioSolicitud">
		<g:message code="prestamo.folioSolicitud.label" default="Folio Solicitud" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="folioSolicitud" type="number" value="${prestamoInstance.folioSolicitud}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: prestamoInstance, field: 'promocion', 'error')} required">
	<label for="promocion">
		<g:message code="prestamo.promocion.label" default="Promocion" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="promocion" name="promocion.id" from="${com.sim.producto.ProPromocion.list()}" optionKey="id" required="" value="${prestamoInstance?.promocion?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: prestamoInstance, field: 'dependencia', 'error')} required">
	<label for="dependencia">
		<g:message code="prestamo.dependencia.label" default="Dependencia" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="dependencia" name="dependencia.id" from="${com.sim.entidad.EntDependencia.list()}" optionKey="id" required="" value="${prestamoInstance?.dependencia?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: prestamoInstance, field: 'sucursal', 'error')} ">
	<label for="sucursal">
		<g:message code="prestamo.sucursal.label" default="Sucursal" />
		
	</label>
	<g:select id="sucursal" name="sucursal.id" from="${com.sim.entidad.EntSucursal.list()}" optionKey="id" value="${prestamoInstance?.sucursal?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: prestamoInstance, field: 'delegacion', 'error')} ">
	<label for="delegacion">
		<g:message code="prestamo.delegacion.label" default="Delegacion" />
		
	</label>
	<g:select id="delegacion" name="delegacion.id" from="${com.sim.entidad.EntDelegacion.list()}" optionKey="id" value="${prestamoInstance?.delegacion?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: prestamoInstance, field: 'vendedor', 'error')} required">
	<label for="vendedor">
		<g:message code="prestamo.vendedor.label" default="Vendedor" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="vendedor" name="vendedor.id" from="${com.sim.empresa.EmpEmpleado.list()}" optionKey="id" required="" value="${prestamoInstance?.vendedor?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: prestamoInstance, field: 'fechaSolicitud', 'error')} required">
	<label for="fechaSolicitud">
		<g:message code="prestamo.fechaSolicitud.label" default="Fecha Solicitud" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="fechaSolicitud" precision="day"  value="${prestamoInstance?.fechaSolicitud}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: prestamoInstance, field: 'montoSolicitado', 'error')} required">
	<label for="montoSolicitado">
		<g:message code="prestamo.montoSolicitado.label" default="Monto Solicitado" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="montoSolicitado" value="${fieldValue(bean: prestamoInstance, field: 'montoSolicitado')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: prestamoInstance, field: 'estatusSolicitud', 'error')} required">
	<label for="estatusSolicitud">
		<g:message code="prestamo.estatusSolicitud.label" default="Estatus Solicitud" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="estatusSolicitud" name="estatusSolicitud.id" from="${com.sim.catalogo.SimCatEtapaPrestamo.list()}" optionKey="id" required="" value="${prestamoInstance?.estatusSolicitud?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: prestamoInstance, field: 'formaDeDispercion', 'error')} required">
	<label for="formaDeDispercion">
		<g:message code="prestamo.formaDeDispercion.label" default="Forma De Dispercion" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="formaDeDispercion" name="formaDeDispercion.id" from="${com.sim.catalogo.SimCatFormaEntrega.list()}" optionKey="id" required="" value="${prestamoInstance?.formaDeDispercion?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: prestamoInstance, field: 'documentosCorrectos', 'error')} ">
	<label for="documentosCorrectos">
		<g:message code="prestamo.documentosCorrectos.label" default="Documentos Correctos" />
		
	</label>
	<g:checkBox name="documentosCorrectos" value="${prestamoInstance?.documentosCorrectos}" />
</div>

<div class="fieldcontain ${hasErrors(bean: prestamoInstance, field: 'approvalStatus', 'error')} required">
	<label for="approvalStatus">
		<g:message code="prestamo.approvalStatus.label" default="Approval Status" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="approvalStatus" from="${org.grails.activiti.ApprovalStatus?.values()}" keys="${org.grails.activiti.ApprovalStatus.values()*.name()}" required="" value="${prestamoInstance?.approvalStatus?.name()}"/>
</div>

