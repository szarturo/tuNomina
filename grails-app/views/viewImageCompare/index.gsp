<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
		<iframe src="${info}" 
			style="position:absolute;top:0%;left:0px;width:50%;height:100%;z-index:999" ></iframe>
		
		<img src="${request.contextPath + '/viewImageCompare/loadImagen?imagen=' + params.imagen}" 
		style="position:absolute;top:0%;left:50%;width:50%;height:100%;z-index:999"  />
		
	</body>
</html>