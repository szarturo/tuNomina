
<%@ page import="abltutorial.PurchaseOrder" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'purchaseOrder.label', default: 'PurchaseOrder')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-purchaseOrder" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-purchaseOrder" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="amountTotal" title="${message(code: 'purchaseOrder.amountTotal.label', default: 'Amount Total')}" />
					
						<th><g:message code="purchaseOrder.customer.label" default="Customer" /></th>
					
						<g:sortableColumn property="notes" title="${message(code: 'purchaseOrder.notes.label', default: 'Notes')}" />
					
						<g:sortableColumn property="paid" title="${message(code: 'purchaseOrder.paid.label', default: 'Paid')}" />
					
						<g:sortableColumn property="ready" title="${message(code: 'purchaseOrder.ready.label', default: 'Ready')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${purchaseOrderInstanceList}" status="i" var="purchaseOrderInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${purchaseOrderInstance.id}">${fieldValue(bean: purchaseOrderInstance, field: "amountTotal")}</g:link></td>
					
						<td>${fieldValue(bean: purchaseOrderInstance, field: "customer")}</td>
					
						<td>${fieldValue(bean: purchaseOrderInstance, field: "notes")}</td>
					
						<td><g:formatBoolean boolean="${purchaseOrderInstance.paid}" /></td>
					
						<td><g:formatBoolean boolean="${purchaseOrderInstance.ready}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${purchaseOrderInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
