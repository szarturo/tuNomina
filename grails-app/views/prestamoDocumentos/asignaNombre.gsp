<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title>Asignar nombre a Documentos</title>
    </head>
    <body>

    <g:form controller="prestamoDocumentos">

        <div class="nav" role="navigation">
            <ul> 
            <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
            </ul>
        </div>  

        <div class="body">
            <h1><g:message default="Documentos" /></h1>

            <div class="list">
                <table>
                    <thead>
                        <tr>
                            <th>Documento</th>
                        
                            <th>Nombre</th>
                        </tr>
                    </thead>

   

                    <tbody>
                    <g:each in="${documentos}" status="i" var="documento">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                            <td>${documento.name}</td>

                        <td><g:select name="${documento.id}" from="${18..30}" value="${age}"/></td>

                        </tr>


                    </g:each>
                    </tbody>                    


  

                </table>
            </div>
        </div>

        <g:hiddenField name="idCliente" value="${idCliente}" />
        <g:hiddenField name="folioSolicitud" value="${folioSolicitud}" />

        <g:actionSubmit value="Guardar" action="guardaNombre"/>
    </g:form>          

    </body>
</html>
