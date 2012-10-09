
<%@ page import="abltutorial.Customer" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'customer.label', default: 'Customer')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-customer" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-customer" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list customer">
			
				<g:if test="${customerInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="customer.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${customerInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${customerInstance?.balance}">
				<li class="fieldcontain">
					<span id="balance-label" class="property-label"><g:message code="customer.balance.label" default="Balance" /></span>
					
						<span class="property-value" aria-labelledby="balance-label"><g:fieldValue bean="${customerInstance}" field="balance"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${customerInstance?.creditLimit}">
				<li class="fieldcontain">
					<span id="creditLimit-label" class="property-label"><g:message code="customer.creditLimit.label" default="Credit Limit" /></span>
					
						<span class="property-value" aria-labelledby="creditLimit-label"><g:fieldValue bean="${customerInstance}" field="creditLimit"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${customerInstance?.customerAudits}">
				<li class="fieldcontain">
					<span id="customerAudits-label" class="property-label"><g:message code="customer.customerAudits.label" default="Customer Audits" /></span>
					
						<g:each in="${customerInstance.customerAudits}" var="c">
						<span class="property-value" aria-labelledby="customerAudits-label"><g:link controller="customerAudit" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${customerInstance?.purchaseOrders}">
				<li class="fieldcontain">
					<span id="purchaseOrders-label" class="property-label"><g:message code="customer.purchaseOrders.label" default="Purchase Orders" /></span>
					
						<g:each in="${customerInstance.purchaseOrders}" var="p">
						<span class="property-value" aria-labelledby="purchaseOrders-label"><g:link controller="purchaseOrder" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${customerInstance?.id}" />
					<g:link class="edit" action="edit" id="${customerInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
