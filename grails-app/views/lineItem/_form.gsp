<%@ page import="abltutorial.LineItem" %>



<div class="fieldcontain ${hasErrors(bean: lineItemInstance, field: 'amount', 'error')} required">
	<label for="amount">
		<g:message code="lineItem.amount.label" default="Amount" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="amount" required="" value="${fieldValue(bean: lineItemInstance, field: 'amount')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: lineItemInstance, field: 'product', 'error')} required">
	<label for="product">
		<g:message code="lineItem.product.label" default="Product" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="product" name="product.id" from="${abltutorial.Product.list()}" optionKey="id" required="" value="${lineItemInstance?.product?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: lineItemInstance, field: 'productPrice', 'error')} required">
	<label for="productPrice">
		<g:message code="lineItem.productPrice.label" default="Product Price" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="productPrice" required="" value="${fieldValue(bean: lineItemInstance, field: 'productPrice')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: lineItemInstance, field: 'purchaseOrder', 'error')} required">
	<label for="purchaseOrder">
		<g:message code="lineItem.purchaseOrder.label" default="Purchase Order" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="purchaseOrder" name="purchaseOrder.id" from="${abltutorial.PurchaseOrder.list()}" optionKey="id" required="" value="${lineItemInstance?.purchaseOrder?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: lineItemInstance, field: 'qtyOrdered', 'error')} required">
	<label for="qtyOrdered">
		<g:message code="lineItem.qtyOrdered.label" default="Qty Ordered" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="qtyOrdered" required="" value="${fieldValue(bean: lineItemInstance, field: 'qtyOrdered')}"/>
</div>

