<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="layout" content="main" />
<g:set var="entityName"
	value="${message(code: 'contact.label', default: 'Contact')}" />
<title><g:message code="default.list.label" args="[entityName]" /></title>

<jq:resources />
<jqui:resources />

<jqgrid:scriptResources />

<link rel="stylesheet"
	href="${resource(dir: 'css', file: 'jqgrid.css')}" type="text/css">

<link rel="stylesheet"
	href="${resource(dir: 'css', file: 'ui.jqgrid.css')}" type="text/css">

<script type="text/javascript">
	function customAction() {
		alert("Custom button pressed");
	}

	function eventHandler() {
		alert("Event handler fired");
	}

	function showSelectedRows() {
		alert("Selected Rows: "
				+ $("#multiselectContactGrid").getGridParam('selarrrow'));
	}

	$(document).ready(function() {
		var standardInitialized = false;
		var customButtonDefaultInitialized = false;
		var customActionInitialized = false;
		var subgridInitialized = false;
		var groupingInitialized = false;
		var multiselectInitialized = false;

		/**
		 * Our view is tab based so we must set that up.
		 * Notice: We must setup things like autocomplete and jqgrid when the correct
		 *         tab is shown. If we don't then size, etc. will not be right. We
		 *         also need to make sure we only set things up once.
		 */
		$("#tabs").tabs({
			show : function(event, ui) {
				if (ui.index == 0 && !standardInitialized) {
					standardInitialized = true;
					<g:render template="standard" />
				} else if (ui.index == 1 && !customButtonDefaultInitialized) {
					customButtonDefaultInitialized = true;
					<g:render template="custombuttongrailsaction" />
				} else if (ui.index == 2 && !customActionInitialized) {
					customActionInitialized = true;
					<g:render template="custombuttonaction" />
				} else if (ui.index == 3 && !subgridInitialized) {
					subgridInitialized = true;
					<g:render template="subgrid" />
				} else if (ui.index == 4 && !groupingInitialized) {
					groupingInitialized = true;
					<g:render template="grouping" />
				} else if (ui.index == 5 && !multiselectInitialized) {
					multiselectInitialized = true;
					<g:render template="multiselect" />
				}
			}
		});
	});
</script>
</head>
<body>

	<div class="body" style="width: 115%;">
		<h1>
			<g:message code="default.list.label" args="[entityName]" />
		</h1>
		<g:if test="${flash.message}">
			<div class="message">
				${flash.message}
			</div>
		</g:if>
		<div class="list">
			<div id="tabs">
				<ul>
					<li><a href="#tabs-1">Standard Grid</a></li>
					<li><a href="#tabs-2">Custom Buttons - Grails Navigation</a></li>
					<li><a href="#tabs-3">Custom Buttons - Javascript Handler</a></li>
					<li><a href="#tabs-4">Sub Grids</a></li>
					<li><a href="#tabs-5">Grouping</a></li>
					<li><a href="#tabs-6">Multiselect</a></li>
				</ul>

				<div id="tabs-1">
					<h3>Standard JQGrid</h3>
					<jqgrid:wrapper id="contactStandard" />
				</div>
				<div id="tabs-2">
					<h3>Custom buttons - Grails Navigation</h3>
					<jqgrid:wrapper id="contactCustomDefaultAction" />
				</div>
				<div id="tabs-3">
					<h3>Custom buttons - Javascript Handlers</h3>
					<jqgrid:wrapper id="contactCustomActions" />
				</div>
				<div id="tabs-4">
					<h3>Subgrid</h3>
					<jqgrid:wrapper id="contactSubgrid" />
				</div>
				<div id="tabs-5">
					<h3>Grouping</h3>
					<jqgrid:wrapper id="groupingContact" />
				</div>
				<div id="tabs-6">
					<h3>Multiselect</h3>
					<jqgrid:wrapper id="multiselectContact" />
				</div>
			</div>
		</div>
	</div>
</body>
</html>
