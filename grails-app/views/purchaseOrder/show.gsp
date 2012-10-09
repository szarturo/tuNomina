
<%@ page import="abltutorial.PurchaseOrder" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'purchaseOrder.label', default: 'PurchaseOrder')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-purchaseOrder" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-purchaseOrder" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list purchaseOrder">
			
				<g:if test="${purchaseOrderInstance?.amountTotal}">
				<li class="fieldcontain">
					<span id="amountTotal-label" class="property-label"><g:message code="purchaseOrder.amountTotal.label" default="Amount Total" /></span>
					
						<span class="property-value" aria-labelledby="amountTotal-label"><g:fieldValue bean="${purchaseOrderInstance}" field="amountTotal"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${purchaseOrderInstance?.customer}">
				<li class="fieldcontain">
					<span id="customer-label" class="property-label"><g:message code="purchaseOrder.customer.label" default="Customer" /></span>
					
						<span class="property-value" aria-labelledby="customer-label"><g:link controller="customer" action="show" id="${purchaseOrderInstance?.customer?.id}">${purchaseOrderInstance?.customer?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${purchaseOrderInstance?.lineItems}">
				<li class="fieldcontain">
					<span id="lineItems-label" class="property-label"><g:message code="purchaseOrder.lineItems.label" default="Line Items" /></span>
					
						<g:each in="${purchaseOrderInstance.lineItems}" var="l">
						<span class="property-value" aria-labelledby="lineItems-label"><g:link controller="lineItem" action="show" id="${l.id}">${l?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${purchaseOrderInstance?.notes}">
				<li class="fieldcontain">
					<span id="notes-label" class="property-label"><g:message code="purchaseOrder.notes.label" default="Notes" /></span>
					
						<span class="property-value" aria-labelledby="notes-label"><g:fieldValue bean="${purchaseOrderInstance}" field="notes"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${purchaseOrderInstance?.paid}">
				<li class="fieldcontain">
					<span id="paid-label" class="property-label"><g:message code="purchaseOrder.paid.label" default="Paid" /></span>
					
						<span class="property-value" aria-labelledby="paid-label"><g:formatBoolean boolean="${purchaseOrderInstance?.paid}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${purchaseOrderInstance?.ready}">
				<li class="fieldcontain">
					<span id="ready-label" class="property-label"><g:message code="purchaseOrder.ready.label" default="Ready" /></span>
					
						<span class="property-value" aria-labelledby="ready-label"><g:formatBoolean boolean="${purchaseOrderInstance?.ready}" /></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${purchaseOrderInstance?.id}" />
					<g:link class="edit" action="edit" id="${purchaseOrderInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
