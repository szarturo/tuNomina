<%@ page import="test.DummyPersona" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'dummyPersona.label', default: 'DummyPersona')}" />
		<title><g:message code="default.edit.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#edit-dummyPersona" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="edit-dummyPersona" class="content scaffold-edit" role="main">
			<h1><g:message code="default.edit.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${dummyPersonaInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${dummyPersonaInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form method="post" >
				<g:hiddenField name="id" value="${dummyPersonaInstance?.id}" />
				<g:hiddenField name="version" value="${dummyPersonaInstance?.version}" />
				<fieldset class="form">
					<g:render template="form"/>
					
					<g:if test="${params.showMenu==null}">
						<script type="text/javascript"> function popup(u){ littleWindow = window.open(u, "littleWindow", "location=center,width=800,height=600"); } </script>
						<div class="fieldcontain">
							<label>
								Comparar con el documento:
							</label>
						
							<g:each in="${documentos}" var="doc">
								<g:link target='_blank' url="javascript:popup('${request.contextPath }/viewImageCompare/index?imagen=${doc.id }&info=/dummyPersona/edit/${dummyPersonaInstance.id }');"> ${doc.name}</g:link> |
							</g:each>
						</div>
					</g:if>
					
				</fieldset>
				<fieldset class="buttons">
					<g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" />
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" formnovalidate="" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
		
	</body>
</html>
