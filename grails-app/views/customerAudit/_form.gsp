<%@ page import="abltutorial.CustomerAudit" %>



<div class="fieldcontain ${hasErrors(bean: customerAuditInstance, field: 'createdOn', 'error')} ">
	<label for="createdOn">
		<g:message code="customerAudit.createdOn.label" default="Created On" />
		
	</label>
	<g:datePicker name="createdOn" precision="day"  value="${customerAuditInstance?.createdOn}" default="none" noSelection="['': '']" />
</div>

<div class="fieldcontain ${hasErrors(bean: customerAuditInstance, field: 'balance', 'error')} required">
	<label for="balance">
		<g:message code="customerAudit.balance.label" default="Balance" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="balance" required="" value="${fieldValue(bean: customerAuditInstance, field: 'balance')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: customerAuditInstance, field: 'creditLimit', 'error')} required">
	<label for="creditLimit">
		<g:message code="customerAudit.creditLimit.label" default="Credit Limit" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="creditLimit" required="" value="${fieldValue(bean: customerAuditInstance, field: 'creditLimit')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: customerAuditInstance, field: 'customer', 'error')} required">
	<label for="customer">
		<g:message code="customerAudit.customer.label" default="Customer" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="customer" name="customer.id" from="${abltutorial.Customer.list()}" optionKey="id" required="" value="${customerAuditInstance?.customer?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: customerAuditInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="customerAudit.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${customerAuditInstance?.name}"/>
</div>

