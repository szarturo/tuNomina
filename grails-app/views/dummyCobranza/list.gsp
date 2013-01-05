
<%@ page import="test.DummyCobranza" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'dummyCobranza.label', default: 'Detalle Cobro')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
		<link rel='stylesheet' type='text/css' href='${request.contextPath}/css/cobranza/color_estados.css' />
		<calendar:resources lang="es" theme="green"  />
		<script>
			function select(opt){
			    for(var i=0; i < document.frmLista.elements.length; i++) {
				  try{
					  document.frmLista.elements[i].checked=opt==1;
					}
					catch(e){
					}
				}
			}

			function send(action, numeroFila){
				document.frmLista.action="${request.contextPath}/dummyCobranza/"+action;
				document.frmLista.numeroFila.value=numeroFila;

				document.frmLista.submit();
			}
			
		</script>
		<!-- You may redefine styles -->
		<style type="text/css" media="screen">
			div.combobox	*	{font-family: Tahoma;font-size: 12px}
			div.combobox	{position: relative; }
			div.combobox	div.dropdownlist	{display: none;width: 100px;border: solid 1px #000;
				height: 200px;overflow: auto;position: absolute;background-color: #fff;top: 27px;left: 0px;}
			div.combobox	.dropdownlist	a	{display: block;text-decoration: none;color: #000;padding: 1px}
			/*div.combobox	.dropdownlist	a.light	{color: #fff;background-color: #007}
			div.combobox	.dropdownlist, input {font-family: Tahoma;font-size: 12px;}*/
			div.combobox	input {float: left;width: 90px;border: solid 1px #ccc;height: 20px} 
			div.combobox	span	{border: solid 1px #ccc;background: #eee;width: 16px;height: 25px;
				float: left;text-align: center;border-left: none}
				tr:hover {
				background-color: #C7E0EB;
				}
		</style>
	</head>
	<body>
		<a href="#list-dummyCobranza" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-dummyCobranza" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			
			<g:link  url="javascript:select(1);">SeleccionarTodos</g:link>
			<g:link url="javascript:select(2);">LimpiarSeleccion</g:link> 
			
			<table>
				<thead>
					<tr>
						<td></td>

						<g:sortableColumn property="id" title="${message(code: 'dummyCobranza.id.label', default: 'Id')}" />

						<th><g:message code="dummyCobranza.detalleRegistro.cliente.label" default="Cliente" /></th>				
						<th><g:message code="dummyCobranza.detalleRegistro.amortizacion.label" default="Amort" /></th>				
						<g:sortableColumn property="field1" title="${message(code: 'dummyCobranza.field1.label', default: 'Pago')}" />
					
						<g:sortableColumn property="field2" title="${message(code: 'dummyCobranza.field2.label', default: 'Cobrado')}" />
						
						<!--
						<g:sortableColumn property="field3" title="${message(code: 'dummyCobranza.field3.label', default: 'Field3')}" />
						
						<g:sortableColumn property="field6" title="${message(code: 'dummyCobranza.field6.label', default: 'Field6')}" />
						-->
						<g:sortableColumn property="field4" title="${message(code: 'dummyCobranza.field4.label', default: 'Insoluto')}" />
					
						<g:sortableColumn property="field5" title="${message(code: 'dummyCobranza.field5.label', default: 'Fecha Aplicacion')}" />
						
						<td>Guardar</td>

						<td>Aplicar</td>
						<!--
						<td>Action r2</td>
						-->
					</tr>
				</thead>
				<tbody>

<g:form name="frmLista" action="clean">
	<g:hiddenField name="numeroFila" value=""/>

	<g:each in="${dummyCobranzaInstanceList}" status="i" var="dummyCobranzaInstance">
		<tr
			<g:if test="${dummyCobranzaInstance.field1 == dummyCobranzaInstance.field2}">
				class="pago_igual"
			</g:if>
			<g:if test="${dummyCobranzaInstance.field1>dummyCobranzaInstance.field2 && dummyCobranzaInstance.field2>0 }">
				class="pago_menor"
			</g:if>
			<g:if test="${dummyCobranzaInstance.field1<dummyCobranzaInstance.field2}">
				class="pago_mayor"
			</g:if>
			<g:if test="${0 >= dummyCobranzaInstance.field2}">
				class="sin_pago"
			</g:if>
		>
			<td>
				<g:checkBox name="rowcheck${i}" />
			</td>

			<td>${fieldValue(bean: dummyCobranzaInstance, field: "id")}</td>
			<td>${fieldValue(bean: dummyCobranzaInstance, field: "detalleRegistro.prestamo.cliente")}</td>
			<td>${fieldValue(bean: dummyCobranzaInstance, field: "detalleRegistro.numeroPago")}</td>
			<td>				
				<g:hiddenField name="field1" value="${fieldValue(bean: dummyCobranzaInstance, field: "field1")}"/>
				<g:hiddenField name="id" value="${dummyCobranzaInstance.id}"/>
				
				<g:link  action="show" id="${dummyCobranzaInstance.id}"><g:formatNumber  number="${dummyCobranzaInstance.field1}" format="#.####" locale="es_MX" /></g:link>
			</td>
		
			<td>
				<input type="text" name="field2" id="field2" value="<g:formatNumber  number="${dummyCobranzaInstance.field2}" format="#.####" locale="es_MX" />" size="3" />
			</td>
		
			<!--
			<td>
				<input type="text" name="field3" id="field3" value="<g:fieldValue bean="${dummyCobranzaInstance}" field="field3"/>" size="3" />
			</td>
			
			<td>
				
				<div class="combobox">
					<input type="text" name="field6" id="field6${fieldValue(bean: dummyCobranzaInstance, field: "id")}" value="${fieldValue(bean: dummyCobranzaInstance, field: "field6")}">
					<span>↓</span>
					<div class="dropdownlist" style="z-index:100000000;">
						<a>${fieldValue(bean: dummyCobranzaInstance, field: "field6")}</a>
						<g:each var="item" in="${dependencias }">
							<a>${item }</a>
						</g:each>
					</div>
				</div>
				
				<script type="text/javascript" charset="utf-8" src="${request.contextPath }/js/combobox/combobox.js"></script>
				<script type="text/javascript" charset="utf-8">
				var no = new ComboBox('field6${fieldValue(bean: dummyCobranzaInstance, field: "id")}');
				</script>
			
			</td>
			-->
		
			<td>${fieldValue(bean: dummyCobranzaInstance, field: "detalleRegistro.impSaldoFinal")}</td>
			
		
			<td nowrap="nowrap">
				<calendar:datePicker dateFormat="%d/%m/%Y"  name="field5${i }" defaultValue="${dummyCobranzaInstance.field5}" />
				<script type="text/javascript">
					document.frmLista.field5${i }_value.style.width="80px";
					document.getElementById('field5${i }_value').style.width="80px";
				</script>
			</td>
			
			<td>
				<g:link url="javascript:send('actionr1', ${i});" >Guardar Pago</g:link>
			</td>

			<td>
				<g:link url="javascript:send('actionr1', ${i});" >Aplicar Pago</g:link>
			</td>

			
			<!--
			<td>
				<g:link url="javascript:send('deleteRow', ${i});" > Delete row</g:link>
			</td>
			-->
		
		</tr>
	</g:each>
</g:form>
				</tbody>
				<tr>	
					<td>
						<g:link url="javascript:send('saveAll', 0);" >Guardar Pagos</g:link>
					</td>
					<td>
						<g:link url="javascript:send('saveAll', 0);" >Aplicar Pagos</g:link>
					</td>

					<!--
					<td>
						<g:link url="javascript:send('deleteAll', 0);" >deleteAll</g:link>
					</td>
					-->
				</tr>
			</table>
			<div class="pagination">
				<g:paginate total="${dummyCobranzaInstanceTotal}" />
			</div>
		</div>
		
	</body>
	
	
</html>
