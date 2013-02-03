<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title>Cartera Generada</title>
    </head>
    <body>
    
        <div class="body">
            <h1>Cartera Generada</h1>
            <g:form method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="distribuidor">Distribuidor</label>
                                </td>
                                <td valign="top">
                                    <g:select name="distribuidor" from="${['1866', '1833']}" />
                                </td>
                            </tr>

                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="dia">Día</label>
                                </td>
                                <td valign="top">
                                    <g:datePicker name="fecha" precision="day" value=""  />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" action="carteraGeneradaDia" value="Aplicar" /></span>
                </div>

                
            </g:form>
        </div>
    </body>
</html>

