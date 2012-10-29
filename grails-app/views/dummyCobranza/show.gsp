
<%@ page import="test.DummyCobranza" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'dummyCobranza.label', default: 'DummyCobranza')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-dummyCobranza" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-dummyCobranza" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list dummyCobranza">
			
				<g:if test="${dummyCobranzaInstance?.field1}">
				<li class="fieldcontain">
					<span id="field1-label" class="property-label"><g:message code="dummyCobranza.field1.label" default="Field1" /></span>
					
						<span class="property-value" aria-labelledby="field1-label"><g:fieldValue bean="${dummyCobranzaInstance}" field="field1"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${dummyCobranzaInstance?.field2}">
				<li class="fieldcontain">
					<span id="field2-label" class="property-label"><g:message code="dummyCobranza.field2.label" default="Field2" /></span>
					
						<span class="property-value" aria-labelledby="field2-label"><g:fieldValue bean="${dummyCobranzaInstance}" field="field2"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${dummyCobranzaInstance?.field3}">
				<li class="fieldcontain">
					<span id="field3-label" class="property-label"><g:message code="dummyCobranza.field3.label" default="Field3" /></span>
					
						<span class="property-value" aria-labelledby="field3-label"><g:fieldValue bean="${dummyCobranzaInstance}" field="field3"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${dummyCobranzaInstance?.field4}">
				<li class="fieldcontain">
					<span id="field4-label" class="property-label"><g:message code="dummyCobranza.field4.label" default="Field4" /></span>
					
						<span class="property-value" aria-labelledby="field4-label"><g:fieldValue bean="${dummyCobranzaInstance}" field="field4"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${dummyCobranzaInstance?.field5}">
				<li class="fieldcontain">
					<span id="field5-label" class="property-label"><g:message code="dummyCobranza.field5.label" default="Field5" /></span>
					
						<span class="property-value" aria-labelledby="field5-label"><g:fieldValue bean="${dummyCobranzaInstance}" field="field5"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${dummyCobranzaInstance?.id}" />
					<g:link class="edit" action="edit" id="${dummyCobranzaInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
