<%@ page import="com.sim.listacobro.ListaCobroDetalleEstatus"%>
<%@ page import="com.sim.listacobro.ListaCobro" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'listaCobro.label', default: 'ListaCobro')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
		<filterpane:includes />
		<calendar:resources lang="es" theme="green"  />
	</head>
	<body>
		<a href="#show-listaCobro" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list">Listas de Cobro</g:link></li>
			</ul>
		</div>
		<div id="show-listaCobro" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list listaCobro">
			
				<g:if test="${listaCobroInstance?.dependencia}">
				<li class="fieldcontain">
					<span id="dependencia-label" class="property-label"><g:message code="listaCobro.dependencia.label" default="Dependencia" /></span>
					
						<span class="property-value" aria-labelledby="dependencia-label">${listaCobroInstance?.dependencia?.encodeAsHTML()}</span>
					
				</li>
				</g:if>
			
				<g:if test="${listaCobroInstance?.anio}">
				<li class="fieldcontain">
					<span id="anio-label" class="property-label"><g:message code="listaCobro.anio.label" default="Anio" /></span>
					
						<span class="property-value" aria-labelledby="anio-label"><g:fieldValue bean="${listaCobroInstance}" field="anio"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${listaCobroInstance?.periodicidad}">
				<li class="fieldcontain">
					<span id="periodicidad-label" class="property-label"><g:message code="listaCobro.periodicidad.label" default="Periodicidad" /></span>
					
						<span class="property-value" aria-labelledby="periodicidad-label">${listaCobroInstance?.periodicidad?.encodeAsHTML()}</span>
					
				</li>
				</g:if>
			
				<g:if test="${listaCobroInstance?.numeroPago}">
				<li class="fieldcontain">
					<span id="numeroPago-label" class="property-label"><g:message code="listaCobro.numeroPago.label" default="Numero Pago" /></span>
					
						<span class="property-value" aria-labelledby="numeroPago-label"><g:fieldValue bean="${listaCobroInstance}" field="numeroPago"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${listaCobroInstance?.fechaInicio}">
				<li class="fieldcontain">
					<span id="fechaInicio-label" class="property-label"><g:message code="listaCobro.fechaInicio.label" default="Fecha Inicio" /></span>
					
						<span class="property-value" aria-labelledby="fechaInicio-label"><g:formatDate format="dd-MM-yyyy" date="${listaCobroInstance?.fechaInicio}"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${listaCobroInstance?.fechaFin}">
				<li class="fieldcontain">
					<span id="fechaFin-label" class="property-label"><g:message code="listaCobro.fechaFin.label" default="Fecha Fin" /></span>
					
						<span class="property-value" aria-labelledby="fechaFin-label"><g:formatDate format="dd-MM-yyyy" date="${listaCobroInstance?.fechaFin}"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${listaCobroInstance?.estatus}">
				<li class="fieldcontain">
					<span id="estatus-label" class="property-label"><g:message code="listaCobro.estatus.label" default="Estatus" /></span>
					
						<span class="property-value" aria-labelledby="estatus-label">${listaCobroInstance?.estatus?.encodeAsHTML()}</span>
					
				</li>
				</g:if>
			
				<g:if test="${listaCobroInstance?.listaCobroProceso}">
				<li class="fieldcontain">
					<span id="listaCobroProceso-label" class="property-label"><g:message code="listaCobro.listaCobroProceso.label" default="Lista Cobro Proceso" /></span>
					
						<g:each in="${listaCobroInstance.listaCobroProceso}" var="l">
						<span class="property-value" aria-labelledby="listaCobroProceso-label"><g:link controller="listaCobroProceso" action="show" id="${l.id}">${l?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			
				<g:if test="${listaCobroInstance?.parcialidades}">
				<li class="fieldcontain">
					<span id="parcialidades-label" class="property-label"><g:message code="listaCobro.parcialidades.label" default="Parcialidades" /></span>
					
						<span class="property-value" aria-labelledby="parcialidades-label"><g:formatBoolean boolean="${listaCobroInstance?.parcialidades}" /></span>
					
				</li>
				</g:if>
			
			</ol>
		<!--
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${listaCobroInstance?.id}" />
					<g:link class="edit" action="edit" id="${listaCobroInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
				</fieldset>
			</g:form>
		-->
		</div>

		<div id="list-listaCobroDetalle" class="content scaffold-list" role="main">

			<filterpane:filterButton text="Mostrar Criterios de Busqueda" />	
			<form id="listacobro-form" name="listacobro-form" method="post">
				<filterpane:filterPane domain="com.sim.listacobro.ListaCobroDetalle" 
				title = "Filtrar Detalles"
				action = "mostrarDetalles"
				associatedProperties="amortizacion.numeroPago,
				amortizacion.impPago,
				tipoEmpleadoDep.nombreTipoEmpleadoDep"/>
			</form>

			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>

						<g:sortableColumn property="estatus" title="${message(code: 'listaCobroDetalle.estatus.label', default: 'Estatus')}" />
					
						<th><g:message code="listaCobroDetalle.amortizacion.label" default="Prestamo:No. Amortizacion" /></th>

						<th><g:message code="listaCobroDetalle.apagar.label" default="A Pagar" /></th>

						<th><g:message code="listaCobroDetalle.saldo.label" default="Saldo" /></th>

						<th><g:message code="listaCobroDetalle.fechaAplicacion.label" default="Fecha Aplicacion" /></th>
					
						<th><g:message code="listaCobroDetalle.pago.label" default="Pago" /></th>

						<th></th>

						<th></th>						
					
					</tr>
				</thead>

				<tbody>

	<g:form name="frmLista" action="clean">
		<g:each in="${listaCobroDetalleInstanceList}" status="i" var="listaCobroDetalleInstance">
			<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

				<td>${fieldValue(bean: listaCobroDetalleInstance, field: "estatus")}</td>
			
				<td>${fieldValue(bean: listaCobroDetalleInstance, field: "amortizacion")}</td>

				<td>${fieldValue(bean: listaCobroDetalleInstance.amortizacion, field: "impPago")}</td>		

				<td>${fieldValue(bean: listaCobroDetalleInstance.amortizacion, field: "impSaldoInicial")}</td>	

				<g:if test="${listaCobroDetalleInstance.estatus == ListaCobroDetalleEstatus.INICIO}">

					<td nowrap="nowrap">
						<calendar:datePicker dateFormat="%d/%m/%Y"  name="fecha${i}" defaultValue="${listaCobroDetalleInstance?.pago?.fechaPago}" />
						<script type="text/javascript">
							document.frmLista.fecha${i}_value.style.width="90px";
							document.getElementById('fecha${i}_value').style.width="90px";
						</script>
					</td>

					<td>
						<input type="text" name="pago${i}" id="pago${i}" value="<g:formatNumber  number="${listaCobroDetalleInstance?.pago?.importePago}" format="#.####" locale="es_MX" />" size="2" />
					</td>						

					<td>
						<g:link url="javascript:send('guardarPagoLc', ${i});"> Guardar Pago </g:link>
					</td>

					<td>
						<g:link url="javascript:send('aplicarPagoLc', ${i});" >Aplicar Pago</g:link>
					</td>
				</g:if>

				<g:if test="${listaCobroDetalleInstance.estatus == ListaCobroDetalleEstatus.GUARDADO}">

					<td><g:formatDate format="dd-MM-yyyy" date="${listaCobroDetalleInstance.pago.fechaPago}" /></td>

					<td>${fieldValue(bean: listaCobroDetalleInstance.pago, field: "importePago")}</td>

					<td>
						<g:link url="javascript:send('aplicarPagoLc', ${i});"> Aplicar Pago </g:link>
					</td>

					<td>
						<g:link url="javascript:send('cancelarPagoGuardadoLc', ${i});" >Cancelar Pago Guardado</g:link>
					</td>

					<g:hiddenField name="pago${i}" value="${listaCobroDetalleInstance?.pago?.importePago}"/>
					<g:hiddenField name="fecha${i}" value="${listaCobroDetalleInstance?.pago?.fechaPago}"/>

				</g:if>

				<g:if test="${listaCobroDetalleInstance.estatus == ListaCobroDetalleEstatus.APLICADO}">

					<td><g:formatDate format="dd-MM-yyyy" date="${listaCobroDetalleInstance.pago.fechaPago}" /></td>

					<td>${fieldValue(bean: listaCobroDetalleInstance.pago, field: "importePago")}</td>

					<td>
						<g:link url="javascript:send('cancelarPagoAplicadoLc', ${i});"> Cancelar Pago Aplicado</g:link>
					</td>

					<td>
					</td>

					<g:hiddenField name="pago${i}" value="${listaCobroDetalleInstance?.pago?.importePago}"/>
					<g:hiddenField name="fecha${i}" value="${listaCobroDetalleInstance?.pago?.fechaPago}"/>

				</g:if>

			</tr>
			<g:hiddenField name="idListaCobroDetalle${i}" value="${listaCobroDetalleInstance.id}"/>
			<g:hiddenField name="idPrestamo${i}" value="${listaCobroDetalleInstance.amortizacion.prestamo.id}"/>
		</g:each>

		<g:hiddenField name="idListaCobro" value="${listaCobroInstance?.id}"/>
		<g:hiddenField name="numeroFila" value=""/>
		
	</g:form>

				</tbody>
			</table>
			<div class="pagination">
				<!--g:paginate total="${listaCobroDetalleInstanceTotal}" /-->
			</div>
		</div>
		<script>
			function send(action, numeroFila){
				document.frmLista.action="${request.contextPath}/listaCobro/"+action;
				document.frmLista.numeroFila.value=numeroFila;
				document.frmLista.submit();
			}
		</script>

	</body>
</html>


