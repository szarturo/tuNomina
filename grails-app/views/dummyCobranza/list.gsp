
<%@ page import="test.DummyCobranza" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'dummyCobranza.label', default: 'DummyCobranza')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
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
			
			<g:link  url="javascript:select(1);">Select all</g:link>
			<g:link url="javascript:select(2);">Deselect all</g:link> 
			
			<table>
				<thead>
					<tr>
						<td></td>
						
						<g:sortableColumn property="field1" title="${message(code: 'dummyCobranza.field1.label', default: 'Field1')}" />
					
						<g:sortableColumn property="field2" title="${message(code: 'dummyCobranza.field2.label', default: 'Field2')}" />
					
						<g:sortableColumn property="field3" title="${message(code: 'dummyCobranza.field3.label', default: 'Field3')}" />
					
						<g:sortableColumn property="field4" title="${message(code: 'dummyCobranza.field4.label', default: 'Field4')}" />
					
						<g:sortableColumn property="field5" title="${message(code: 'dummyCobranza.field5.label', default: 'Field5')}" />
						
						<td>Action r1</td>
						
						<td>Action r2</td>
					
					</tr>
				</thead>
				<tbody>
				<g:form name="frmLista" action="clean">
					<g:hiddenField name="numeroFila" value=""/>
				
					<g:each in="${dummyCobranzaInstanceList}" status="i" var="dummyCobranzaInstance">
						<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
							
							<td>
								<g:checkBox name="rowcheck${i}" />
							</td>
							
							<td>
								<g:hiddenField name="field1" value="${fieldValue(bean: dummyCobranzaInstance, field: "field1")}"/>
								<g:hiddenField name="id" value="${dummyCobranzaInstance.id}"/>
							<g:link action="show" id="${dummyCobranzaInstance.id}">${fieldValue(bean: dummyCobranzaInstance, field: "field1")}</g:link>
							</td>
						
							<td>
								<g:textField name="field2" value="${fieldValue(bean: dummyCobranzaInstance, field: "field2")}"/>
							</td>
						
							<td>
								<g:textField name="field3" value="${fieldValue(bean: dummyCobranzaInstance, field: "field3")}"/>
							</td>
						
							<td>${fieldValue(bean: dummyCobranzaInstance, field: "field4")}
								<g:hiddenField name="field4" value="${fieldValue(bean: dummyCobranzaInstance, field: "field4")}"/>
							</td>
						
							<td>${fieldValue(bean: dummyCobranzaInstance, field: "field5")}
								<g:hiddenField name="field5" value="${fieldValue(bean: dummyCobranzaInstance, field: "field5")}"/>
							</td>
							
							<td>
								<g:link url="javascript:send('actionr1', ${i});" >save row</g:link>
							</td>
							
							<td>
								<g:link url="javascript:send('deleteRow', ${i});" > Delete row</g:link>
							</td>
						
						</tr>
					</g:each>
				</g:form>
				</tbody>
				<tr>	
					<td>
						<g:link url="javascript:send('saveAll', 0);" >Save All</g:link>
					</td>
					<td>
						<g:link url="javascript:send('deleteAll', 0);" >deleteAll</g:link>
					</td>
				</tr>
			</table>
			<div class="pagination">
				<g:paginate total="${dummyCobranzaInstanceTotal}" />
			</div>
		</div>
		
	</body>
	
	
</html>
