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

            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>            

            <div class="list">
                <table>
                    <thead>
                        <tr>
                            <th>Documento</th>
                            <th>Nombre</th>
                            <th>
                                <sec:ifAllGranted roles="ROLE_CONTROL_CALIDAD">
                                Enviar a Credito Real
                                </sec:ifAllGranted>
                            </th>
                        </tr>
                    </thead>

                    <tbody>
                    <g:each in="${documentosPrestamo}" status="i" var="documento">
                            <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                                <td>${documento.nombreArchivo}</td>

                                <td><g:select name="${documento.nombreArchivo}" from="${com.sim.catalogo.SimCatDocumento.findAllByTipoDocumento(com.sim.catalogo.SimCatTipoDocumento.findByClaveTipoDocumento('PRESTAMO'))}" value="${documento.documento.id}" optionKey="id"/></td>
                                <td>
                                <sec:ifAllGranted roles="ROLE_CONTROL_CALIDAD">
                                    <g:link action="enviaDocumento" params="[idDocumento : documento.id, folioSolicitud : folioSolicitud, idCliente : idCliente, taskId : params.taskId, idPrestamo : params.idPrestamo]">
                                        Enviar Documento
                                    </g:link>    
                                </sec:ifAllGranted>
                                </td>
                            </tr>
                            
                        <!--/g:form-->          
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
