
<%@ page import="abltutorial.LineItem" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'lineItem.label', default: 'LineItem')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-lineItem" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-lineItem" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="amount" title="${message(code: 'lineItem.amount.label', default: 'Amount')}" />
					
						<th><g:message code="lineItem.product.label" default="Product" /></th>
					
						<g:sortableColumn property="productPrice" title="${message(code: 'lineItem.productPrice.label', default: 'Product Price')}" />
					
						<th><g:message code="lineItem.purchaseOrder.label" default="Purchase Order" /></th>
					
						<g:sortableColumn property="qtyOrdered" title="${message(code: 'lineItem.qtyOrdered.label', default: 'Qty Ordered')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${lineItemInstanceList}" status="i" var="lineItemInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${lineItemInstance.id}">${fieldValue(bean: lineItemInstance, field: "amount")}</g:link></td>
					
						<td>${fieldValue(bean: lineItemInstance, field: "product")}</td>
					
						<td>${fieldValue(bean: lineItemInstance, field: "productPrice")}</td>
					
						<td>${fieldValue(bean: lineItemInstance, field: "purchaseOrder")}</td>
					
						<td>${fieldValue(bean: lineItemInstance, field: "qtyOrdered")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${lineItemInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
