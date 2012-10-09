<%@ page import="abltutorial.Customer" %>



<div class="fieldcontain ${hasErrors(bean: customerInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="customer.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${customerInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: customerInstance, field: 'balance', 'error')} required">
	<label for="balance">
		<g:message code="customer.balance.label" default="Balance" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="balance" required="" value="${fieldValue(bean: customerInstance, field: 'balance')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: customerInstance, field: 'creditLimit', 'error')} required">
	<label for="creditLimit">
		<g:message code="customer.creditLimit.label" default="Credit Limit" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="creditLimit" required="" value="${fieldValue(bean: customerInstance, field: 'creditLimit')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: customerInstance, field: 'customerAudits', 'error')} ">
	<label for="customerAudits">
		<g:message code="customer.customerAudits.label" default="Customer Audits" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${customerInstance?.customerAudits?}" var="c">
    <li><g:link controller="customerAudit" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="customerAudit" action="create" params="['customer.id': customerInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'customerAudit.label', default: 'CustomerAudit')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: customerInstance, field: 'purchaseOrders', 'error')} ">
	<label for="purchaseOrders">
		<g:message code="customer.purchaseOrders.label" default="Purchase Orders" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${customerInstance?.purchaseOrders?}" var="p">
    <li><g:link controller="purchaseOrder" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="purchaseOrder" action="create" params="['customer.id': customerInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'purchaseOrder.label', default: 'PurchaseOrder')])}</g:link>
</li>
</ul>

</div>

