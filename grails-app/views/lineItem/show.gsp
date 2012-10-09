
<%@ page import="abltutorial.LineItem" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'lineItem.label', default: 'LineItem')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-lineItem" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-lineItem" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list lineItem">
			
				<g:if test="${lineItemInstance?.amount}">
				<li class="fieldcontain">
					<span id="amount-label" class="property-label"><g:message code="lineItem.amount.label" default="Amount" /></span>
					
						<span class="property-value" aria-labelledby="amount-label"><g:fieldValue bean="${lineItemInstance}" field="amount"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${lineItemInstance?.product}">
				<li class="fieldcontain">
					<span id="product-label" class="property-label"><g:message code="lineItem.product.label" default="Product" /></span>
					
						<span class="property-value" aria-labelledby="product-label"><g:link controller="product" action="show" id="${lineItemInstance?.product?.id}">${lineItemInstance?.product?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${lineItemInstance?.productPrice}">
				<li class="fieldcontain">
					<span id="productPrice-label" class="property-label"><g:message code="lineItem.productPrice.label" default="Product Price" /></span>
					
						<span class="property-value" aria-labelledby="productPrice-label"><g:fieldValue bean="${lineItemInstance}" field="productPrice"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${lineItemInstance?.purchaseOrder}">
				<li class="fieldcontain">
					<span id="purchaseOrder-label" class="property-label"><g:message code="lineItem.purchaseOrder.label" default="Purchase Order" /></span>
					
						<span class="property-value" aria-labelledby="purchaseOrder-label"><g:link controller="purchaseOrder" action="show" id="${lineItemInstance?.purchaseOrder?.id}">${lineItemInstance?.purchaseOrder?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${lineItemInstance?.qtyOrdered}">
				<li class="fieldcontain">
					<span id="qtyOrdered-label" class="property-label"><g:message code="lineItem.qtyOrdered.label" default="Qty Ordered" /></span>
					
						<span class="property-value" aria-labelledby="qtyOrdered-label"><g:fieldValue bean="${lineItemInstance}" field="qtyOrdered"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${lineItemInstance?.id}" />
					<g:link class="edit" action="edit" id="${lineItemInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
