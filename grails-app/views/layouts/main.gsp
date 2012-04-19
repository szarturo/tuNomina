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
			Bienvenido: ${usuario?.persona} (<sec:username />)  
		</sec:ifLoggedIn>

	</div>

	<div id="menu">
		<ul class="menu">
			<li><a href="#" class="parent"><span>Catalogos</span></a>
				<div>
					<ul>
						<li><a href="#" class="parent"><span>Generales</span></a>
							<div>
								<ul>
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
									<li><a href="#"><span>Sub Item 1.2</span></a></li>
									<li><a href="#"><span>Sub Item 1.3</span></a></li>
									<li><a href="#"><span>Sub Item 1.4</span></a></li>
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
						<li><a href="#"><span>Sub Item 4</span></a></li>
						<li><a href="#"><span>Sub Item 5</span></a></li>
						<li><a href="#"><span>Sub Item 6</span></a></li>
						<li><a href="#"><span>Sub Item 7</span></a></li>
					</ul>
				</div></li>

			<li><a href="#"><span>Servicios</span></a></li>

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