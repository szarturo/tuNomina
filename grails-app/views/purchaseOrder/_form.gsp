<%@ page import="abltutorial.PurchaseOrder" %>



<div class="fieldcontain ${hasErrors(bean: purchaseOrderInstance, field: 'amountTotal', 'error')} required">
	<label for="amountTotal">
		<g:message code="purchaseOrder.amountTotal.label" default="Amount Total" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="amountTotal" required="" value="${fieldValue(bean: purchaseOrderInstance, field: 'amountTotal')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: purchaseOrderInstance, field: 'customer', 'error')} required">
	<label for="customer">
		<g:message code="purchaseOrder.customer.label" default="Customer" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="customer" name="customer.id" from="${abltutorial.Customer.list()}" optionKey="id" required="" value="${purchaseOrderInstance?.customer?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: purchaseOrderInstance, field: 'lineItems', 'error')} ">
	<label for="lineItems">
		<g:message code="purchaseOrder.lineItems.label" default="Line Items" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${purchaseOrderInstance?.lineItems?}" var="l">
    <li><g:link controller="lineItem" action="show" id="${l.id}">${l?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="lineItem" action="create" params="['purchaseOrder.id': purchaseOrderInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'lineItem.label', default: 'LineItem')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: purchaseOrderInstance, field: 'notes', 'error')} ">
	<label for="notes">
		<g:message code="purchaseOrder.notes.label" default="Notes" />
		
	</label>
	<g:textField name="notes" value="${purchaseOrderInstance?.notes}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: purchaseOrderInstance, field: 'paid', 'error')} ">
	<label for="paid">
		<g:message code="purchaseOrder.paid.label" default="Paid" />
		
	</label>
	<g:checkBox name="paid" value="${purchaseOrderInstance?.paid}" />
</div>

<div class="fieldcontain ${hasErrors(bean: purchaseOrderInstance, field: 'ready', 'error')} ">
	<label for="ready">
		<g:message code="purchaseOrder.ready.label" default="Ready" />
		
	</label>
	<g:checkBox name="ready" value="${purchaseOrderInstance?.ready}" />
</div>

