<%@ page import="org.apache.commons.lang.RandomStringUtils"%>

<html>
	<head>
		<meta name="layout" content="main">
		<r:require modules="uploadr" />
	</head>
	<body>
		<h1>Documentos para iniciar apertura de Cr&eacute;dito</h1>
		<uploadr:add name="${clavePrestamo}" path="${path}" direction="up"
			maxVisible="8"
			unsupported="${createLink(plugin: 'uploadr', controller: 'upload', action: 'warning')}"
			maxSize="52428800" rating="true" voting="true">
			<g:each in="${imagenes}" var="file">
				<uploadr:file name="${file.name}">
					<uploadr:fileSize>${file.size()}</uploadr:fileSize>
					<uploadr:fileModified>${file.lastModified()}</uploadr:fileModified>
					<uploadr:fileId>myId-${RandomStringUtils.random(32, true, true)}</uploadr:fileId>
                    <uploadr:ratingText>Selecciona los archivos a comparar</uploadr:ratingText>
				</uploadr:file>
			</g:each>
			
			<uploadr:onView>
				// open a modal dialog to view the file contents
				var width = 750;
				var height= 500;
				
				$("#ventana").dialog(
				{
					title       : file.fileName,
					position 	: 'top',
					autoOpen 	: true,
					width 		: width,
					height 		: height,
					modal 		: true,
					close       : function() { window.location.reload() },
					buttons 	: {
						close: function() { $(this).dialog('close'); }
					},
					create: function(){

					      $(this).append($("<iframe style='width:700px; height: 500px;'></iframe>").attr('src',
					      '${createLink(action:'obtenerImagen')}?fileName='+
					      escape(file.fileName)+'&ruta='+escape('${path}')))

					}
				}).width(width).height(height).animate({ top: '10' });				
			</uploadr:onView>

            <uploadr:onLike>
                // callback if like action was successfull
                // and pass the new file rating
                // En ocaciones lo ejecuta a la segunda
                callback(file.fileRating + 1);
                callback(file.fileRating + 1);
                // Agrega la imagen al arreglo
                imagenes[file.fileName] = file.fileName;
            </uploadr:onLike>

            <uploadr:onUnlike>
                // callback if unlike action was successfull
                // and pass the new file rating
                // En ocaciones lo ejecuta a la segunda
                callback(file.fileRating - 1);
                callback(file.fileRating - 1);
                // Borra la imagen del arreglo
                delete imagenes[file.fileName];
            </uploadr:onUnlike>

		</uploadr:add>

    <button onclick="mostrarDocumentos()">Ver Documentos</button>



    <g:javascript>
        var imagenes = new Object();
        var documentos = new Array();


        function mostrarDocumentos(){
            var i = 0;
            for (var key in imagenes)
            {
                if (imagenes.hasOwnProperty(key)){
                    //alert(key + " = " + imagenes[key]);
                    documentos[i] = imagenes[key];
                    i = i +1;
                }
            }

            // open a modal dialog to view the file contents
            var width = 1200;
            var height= 600;

            $("#ventana").dialog(
            {
                title       : 'Documentos',
                position 	: 'top',
                autoOpen 	: true,
                width 		: width,
                height 		: height,
                modal 		: true,
                close       : function() { window.location.reload() },
                buttons 	: {
                    close: function() { $(this).dialog('close'); }
                },
                create: function(){

                      $(this).append($("<iframe style='width:1150px; height: 600px;'></iframe>").attr('src',
                      '${createLink(action:'compararDocumentos')}?fileName0='+
                      documentos[0]+'&fileName1='+documentos[1]+'&ruta='+escape('${path}')))

                }
            }).width(width).height(height).animate({ top: '10' });



        }
    </g:javascript>

    <div id="ventana" display:none"><iframe style="width:0px; height: 0px; src=""></iframe></div>

    </body>
</html>
