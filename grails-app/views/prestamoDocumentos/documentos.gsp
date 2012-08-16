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
			maxSize="52428800">
			<g:each in="${imagenes}" var="file">
				<uploadr:file name="${file.name}">
					<uploadr:fileSize>${file.size()}</uploadr:fileSize>
					<uploadr:fileModified>${file.lastModified()}</uploadr:fileModified>
					<uploadr:fileId>myId-${RandomStringUtils.random(32, true, true)}</uploadr:fileId>
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
		</uploadr:add>
	
		<div id="ventana" display:none"><iframe style="width:0px; height: 0px; src=""></iframe></div>
	</body>
</html>
