<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName"
           value="${message(code: 'cliente.label', default: 'Cliente')}"/>
    <title>B&uacute;squeda de Clientes</title>

    <jq:resources/>
    <jqui:resources/>

    <jqgrid:scriptResources/>

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
                    if (ui.index == 0 && !subgridInitialized) {
                        subgridInitialized = true;
                        <g:render template="subgrid"/>
                    }
                }
            });
        });

    </script>
</head>

<body>

<div class="body" style="width: 115%;">
    <h1>
        <g:message code="default.list.label" args="[entityName]"/>
    </h1>
    <g:if test="${flash.message}">
        <div class="message">
            ${flash.message}
        </div>
    </g:if>
    <div class="list">
        <div id="tabs">
            <ul>
                <li><a href="#tabs-1">B&uacute;squeda de Clientes</a></li>
            </ul>

            <div id="tabs-1">
                <h3>Con Cr&eacute;ditos</h3>
                <jqgrid:wrapper id="contactSubgrid"/>
            </div>


        </div>
    </div>
</div>
</body>
</html>
