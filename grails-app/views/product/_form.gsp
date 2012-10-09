<%@ page import="abltutorial.Product" %>



<div class="fieldcontain ${hasErrors(bean: productInstance, field: 'lineitems', 'error')} ">
	<label for="lineitems">
		<g:message code="product.lineitems.label" default="Lineitems" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${productInstance?.lineitems?}" var="l">
    <li><g:link controller="lineItem" action="show" id="${l.id}">${l?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="lineItem" action="create" params="['product.id': productInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'lineItem.label', default: 'LineItem')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: productInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="product.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${productInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: productInstance, field: 'price', 'error')} required">
	<label for="price">
		<g:message code="product.price.label" default="Price" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="price" required="" value="${fieldValue(bean: productInstance, field: 'price')}"/>
</div>

