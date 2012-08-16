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
			<% path.listFiles().each { file -> %>
				<uploadr:file name="${file.name}">
					<uploadr:fileSize>${file.size()}</uploadr:fileSize>
					<uploadr:fileModified>${file.lastModified()}</uploadr:fileModified>
					<uploadr:fileId>myId-${RandomStringUtils.random(32, true, true)}</uploadr:fileId>
				</uploadr:file>
			<% } %>
		</uploadr:add>
	
	
	</body>
</html>
