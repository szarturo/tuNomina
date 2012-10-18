<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main"/>

        <title>B&uacute;squeda de Clientes</title>

        <jq:resources/>
        <jqui:resources/>

        <jqgrid:scriptResources/>

        <link rel="stylesheet"
              href="${resource(dir: 'css', file: 'jqgrid.css')}" type="text/css">

        <link rel="stylesheet"
              href="${resource(dir: 'css', file: 'ui.jqgrid.css')}" type="text/css">

        <script type="text/javascript">

            $(document).ready(function() {
                var subgridInitialized = false;

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

        <div class="body" style="width: 100%;">

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
