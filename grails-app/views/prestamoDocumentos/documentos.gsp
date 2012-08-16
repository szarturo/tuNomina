<%@ page import="org.apache.commons.lang.RandomStringUtils"%>

<html>
	<head>
		<meta name="layout" content="main">
		<r:require modules="uploadr" />
	</head>
	<body>
		<h1>Documentos para iniciar apertura de Cr&eacute;dito</h1>
		<uploadr:add name="${clavePrestamo}" path="${path}" direction="up"
			maxVisible="5"
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
				//var keys = Object.keys(file);
				//alert(keys);
				//alert(file.fileName);
				
				// open a modal dialog to view the file contents
				var width = 640;
				var height= 400;
				
				$("#checkout-window").dialog(
				{
					title       : file.fileName,
					position 	: 'center',
					autoOpen 	: true,
					width 		: width,
					height 		: height,
					modal 		: true,
					close       : function() { window.location.reload() },
					buttons 	: {
						close: function() { $(this).dialog('close'); }
					},
					create: function(){
					      $(this).append($("<iframe></iframe>").attr('src','${createLink(action:'obtenerImagen')}?fileName='+
					      escape(file.fileName)+'&ruta='+escape('${path}')))
					}
				}).width(width - 10).height(height).animate({ top: '0' });				
			
				//myWindow=window.open('','','width=500,height=400')
				//myWindow.document.write("<p>This is 'myWindow'</p>")
				
				//myWindow.document.write("<HTML>")
				//myWindow.document.write("<HEAD>")
				//myWindow.document.write("<TITLE>A simple frameset document</TITLE>")
				//myWindow.document.write("</HEAD>")
				//myWindow.document.write("<FRAMESET cols='20%, 80%'>")
				//myWindow.document.write("<FRAMESET rows='100, 200'>")
				//myWindow.document.write("<FRAME src='21112010043.jpg'>")
				//myWindow.document.write("<FRAME src='21112010043.jpg'>")
				//myWindow.document.write("</FRAMESET>")
				//myWindow.document.write("<FRAME src='21112010043.jpg'>")
				//myWindow.document.write("</FRAMESET>")
				//myWindow.document.write("</HTML>")	
				//myWindow.focus()
			</uploadr:onView>			
				
		</uploadr:add>
	
		<div id="checkout-window" style="display:none"><iframe src=""></iframe></div>
	</body>
</html>
