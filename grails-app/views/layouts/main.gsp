<!doctype html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>SIM &raquo; <g:layoutTitle default="Bienvenido" /></title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="shortcut icon"
	href="${resource(dir: 'images', file: 'credito.jpg')}"
	type="image/x-icon">
<link rel="apple-touch-icon"
	href="${resource(dir: 'images', file: 'apple-touch-icon.png')}">
<link rel="apple-touch-icon" sizes="114x114"
	href="${resource(dir: 'images', file: 'apple-touch-icon-retina.png')}">
<link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}"
	type="text/css">
<link rel="stylesheet"
	href="${resource(dir: 'css', file: 'mobile.css')}" type="text/css">

<link rel="stylesheet" href="${resource(dir: 'css', file: 'menu.css')}"
	type="text/css">

<g:layoutHead />
<r:layoutResources />
</head>
<body>
	<div id="grailsLogo" role="banner">

		<a href="http://google.com"><img
			src="${resource(dir: 'images', file: 'credito.jpg')}" alt="Grails" />
		</a>

		<sec:ifLoggedIn>
			${usuario?.persona} (<sec:username />)  
		</sec:ifLoggedIn>

	</div>

	<div id="menu">
		<ul class="menu">
			<li><a href="#" class="parent"><span>Cat&aacute;logos</span></a>
				<div>
					<ul>
						<li><a href="#" class="parent"><span>Generales</span></a>
							<div>
								<ul>

									<li><a href="#" class="parent"><span>Direcciones</span></a>
										<div>
											<ul>
												<li><g:link controller="rsGralEstado">
														<span>Estados</span>
													</g:link></li>
												<li><g:link controller="rsGralCiudad">
														<span>Ciudades</span>
													</g:link></li>
												<li><g:link controller="rsGralDelegacionMunicipio">
														<span>Delegaciones o Municipios</span>
													</g:link></li>
												<li><g:link controller="RsGralAsentamiento">
														<span>C&oacute;digos Postales</span>
													</g:link></li>
												<li><g:link controller="SimCatTipoAsentamiento">
														<span>Tipos de Asentamiento</span>
													</g:link></li>

											</ul>
										</div></li>


									<li><a href="#" class="parent"><span>Documento</span></a>
										<div>
											<ul>
												<li><g:link controller="simCatTipoDocumento">
														<span>Tipo Documento</span>
													</g:link></li>
												<li><g:link controller="simCatDocumento">
														<span>Documento</span>
													</g:link></li>
											</ul>
										</div></li>
									<li><g:link controller="simCatEscolaridad">
											<span>Escolaridad</span>
										</g:link></li>
									<li><g:link controller="simCatTipoPersona">
											<span>Tipo Persona</span>
										</g:link></li>
									<li><g:link controller="simCatDescTelefono">
											<span>Tipo Tel&eacute;fono</span>
										</g:link></li>
									<li><a href="#"><span>Sub Item 1.5</span></a></li>
									<li><a href="#"><span>Sub Item 1.6</span></a></li>
									<li><a href="#" class="parent"><span>Sub Item
												1.7</span></a>
										<div>
											<ul>
												<li><a href="#"><span>Sub Item 1.7.1</span></a></li>
												<li><a href="#"><span>Sub Item 1.7.2</span></a></li>
											</ul>
										</div></li>
								</ul>
							</div></li>
						<li><g:link controller="simCatBanco">
								<span>Banco</span>
							</g:link></li>
						<li><a href="#"><span>Sub Item 3</span></a></li>
					</ul>
				</div></li>
			<li><a href="#"><span>Productos</span></a>
				<div>
					<ul>
						<li><a href="#" class="parent"><span>Sub Item 1</span></a>
							<div>
								<ul>
									<li><a href="#"><span>Sub Item 1.1</span></a></li>
									<li><a href="#"><span>Sub Item 1.2</span></a></li>
								</ul>
							</div></li>
						<li><a href="#" class="parent"><span>Sub Item 2</span></a>
							<div>
								<ul>
									<li><a href="#"><span>Sub Item 2.1</span></a></li>
									<li><a href="#"><span>Sub Item 2.2</span></a></li>
								</ul>
							</div></li>
						<li><a href="#"><span>Sub Item 3</span></a></li>
						<li><a href="#"><span>Sub Item 4</span></a></li>
						<li><a href="#"><span>Sub Item 5</span></a></li>
						<li><a href="#"><span>Sub Item 6</span></a></li>
						<li><a href="#"><span>Sub Item 7</span></a></li>
					</ul>
				</div></li>

			<li><a href="#"><span>Empresa</span></a>
				<div>
					<ul>
						<li><g:link controller="rsPersona">
								<span>Persona</span>
							</g:link></li>
						<li><g:link controller="rsGralTelefono">
								<span>Tel&eacute;fonos</span>
							</g:link></li>
						<li><g:link controller="rsGralDomicilio">
								<span>Domicilios</span>
							</g:link></li>
						<li><a href="#"><span>Sub Item 5</span></a></li>
						<li><a href="#"><span>Sub Item 6</span></a></li>
						<li><a href="#"><span>Sub Item 7</span></a></li>
					</ul>
				</div></li>
				
			<sec:ifLoggedIn>
				<li><a href="#"><span>Servicios</span></a>
					<div>
						<ul>
							<li><g:link controller="task" action="index">
									<span>Tareas</span>
								</g:link></li>
	
							<li><a href="#" class="parent"><span>Procesos</span></a>
								<div>
									<ul>
										<li><g:link controller="solicitudCredito" action="start">
												<span>Credito</span>
											</g:link></li>
										<li><g:link controller="solicitudPrestamo" action="start">
												<span>Prestamo</span>
											</g:link></li>
									</ul>
								</div>
							</li>
						</ul>
					</div>			
				</li>
			</sec:ifLoggedIn>

			<li><a href="#"><span>Seguridad</span></a>
				<div>
					<ul>

						<li><a href="#" class="parent"><span>Usuarios</span></a>
							<div>
								<ul>
									<li><g:link controller="user">
											<span>Consulta</span>
										</g:link></li>
									<li><g:link controller="user" action="create">
											<span>Alta</span>
										</g:link></li>
								</ul>
							</div></li>

						<li><a href="#" class="parent"><span>Roles</span></a>
							<div>
								<ul>
									<li><g:link controller="role">
											<span>Consulta</span>
										</g:link></li>
									<li><g:link controller="role" action="create">
											<span>Alta</span>
										</g:link></li>
								</ul>
							</div></li>
							
						<li><a href="#" class="parent"><span>Accesos</span></a>
							<div>
								<ul>
									<li><g:link controller="requestmap">
											<span>Consulta</span>
										</g:link></li>
									<li><g:link controller="requestmap" action="create">
											<span>Alta</span>
										</g:link></li>
								</ul>
							</div></li>
						<li><g:link controller="securityInfo" action="config">
								<span>Informaci&oacute;n</span>
							</g:link></li>
					</ul>
				</div></li>

			<sec:ifNotLoggedIn>
				<li class="last"><g:link controller="login" action="auth">
						<span>Entrar</span>
					</g:link></li>
			</sec:ifNotLoggedIn>

			<sec:ifLoggedIn>
				<li class="last"><g:link controller="logout">
						<span>Salir</span>
					</g:link></li>
			</sec:ifLoggedIn>

		</ul>
	</div>

	<g:layoutBody />

	<div class="footer" role="contentinfo"></div>
	<div id="spinner" class="spinner" style="display: none;">
		<g:message code="spinner.alt" default="Loading&hellip;" />
	</div>
	<g:javascript library="application" />
	<r:layoutResources />

</body>
</html>