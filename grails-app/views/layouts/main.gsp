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
	href="${resource(dir: 'images', file: 'tunomina.png')}"
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

<script type="text/javascript">
	function popup(u){
		littleWindow = window.open(u, "littleWindow", "location=center,width=800,height=600");
		window.location.href = "${request.contextPath}/task/myTaskList";
	} 	
</script>
<script>
	if(parent.window.name=="littleWindow"){
		parent.window.close();
	}
</script>

</head>
<body>
	<div id="grailsLogo" role="banner">

		<a href=""> <img
			src="${resource(dir: 'images', file: 'tunomina.png')}" alt="Grails" />
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

									<li><a href="#" class="parent"><span>Direcci&oacute;n</span></a>
										<div>
											<ul>
												<li><g:link controller="rsGralEstado">
														<span>Estados</span>
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
									<li><g:link controller="simCatPais">
											<span>Pais</span>
										</g:link></li>
									<li><g:link controller="simCatBanco">
											<span>Banco</span>
										</g:link></li>
									<li><g:link controller="simCatEscolaridad">
											<span>Escolaridad</span>
										</g:link></li>
									<li><g:link controller="simCatTipoPersona">
											<span>Tipo Persona</span>
										</g:link></li>
									<li><g:link controller="simCatDescTelefono">
											<span>Tipo Tel&eacute;fono</span>
										</g:link></li>
									<li><g:link controller="simCatListaCobroEstatus">
											<span>Estatus Lista Cobro</span>
										</g:link></li>										
						</ul>
							</div></li>
							
							<li><a href="#" class="parent"><span>Prestamo</span></a>
								<div>
									<ul>
										<li><g:link controller="simCatFormaEntrega">
												<span>Forma Entrega</span>
											</g:link></li>
										<li><g:link controller="simCatCrMotivo">
												<span>CR Motivos </span>
											</g:link></li>	
									</ul>									
								</div>
							</li>
																	
							<li><a href="#" class="parent"><span>Promoci&oacute;n</span></a>
							<div>
								<ul>
								<li><g:link controller="simCatPeriodicidad">
											<span>Periodicidad</span>
										</g:link></li>
								<li><g:link controller="SimCatMetodoCalculo">
											<span>M&eacute;todo de C&aacute;lculo</span>
										</g:link></li>
							    <li><g:link controller="simCatFormaAplicacion">
											<span>Forma Aplicaci&oacute;n</span>
										</g:link></li>								
								<li><g:link controller="simCatTipoAccesorio">
											<span>Tipo Accesorio</span>
										</g:link></li>
								<li><g:link controller="simCatAccesorio">
											<span>Accesorio</span>
										</g:link></li>	
								<li><g:link controller="simCatUnidad">
											<span>Unidad</span>
										</g:link></li>											
								</ul>
							</div></li>

						<li><a href="#" class="parent"><span>Estructura
									Organizacional</span></a>
							<div>
								<ul>
									<li><g:link controller="empPuesto">
											<span>Puesto</span>
										</g:link></li>
									<li><g:link controller="entRegion">
											<span>Regi&oacute;n</span>
										</g:link></li>
									<li><g:link controller="entSucursal">
											<span>Sucursal</span>
										</g:link></li>
									<li><g:link controller="entDelegacion">
											<span>Delegaci&oacute;n</span>
										</g:link></li>
									<li><g:link controller="entDependencia">
											<span>Dependencia</span>
										</g:link></li>
									<li><g:link controller="simCatTipoEmp">
											<span>Tipo Empleado</span>
										</g:link></li>
								</ul>
							</div></li>

							
						<li><a href="#" class="parent"><span>Core Financiero</span></a>
							<div>
								<ul>
									<li><g:link controller="pfinCatOperacion">
											<span>Operacion</span>
										</g:link></li>
									<li><g:link controller="pfinCatConcepto">
											<span>Concepto</span>
										</g:link></li>
									<li><g:link controller="pfinCatOperacionConcepto">
											<span>Operacion Concepto</span>
										</g:link></li>
									<li><g:link controller="pfinCatAfectaOperacion">
											<span>Afecta Operacion</span>
										</g:link></li>
									<li><g:link controller="pfinCuenta">
											<span>Cuenta</span>
										</g:link></li>
									<li><g:link controller="pfinSaldo">
											<span>Saldo</span>
										</g:link></li>
									<li><g:link controller="pfinDivisa">
											<span>Divisa</span>
										</g:link></li>
									<li><g:link controller="pfinCatParametro">
											<span>Parametro</span>
										</g:link></li>
									<li><g:link controller="pfinCatDiaFestivo">
											<span>Dia Festivo</span>
										</g:link></li>
									<li><g:link controller="pfinPreMovimiento">
											<span>Premovimiento</span>
										</g:link></li>
									<li><g:link controller="pfinPreMovimientoDet">
											<span>Premovimiento Detalle</span>
										</g:link></li>
									<li><g:link controller="pfinMovimiento">
											<span>Movimiento</span>
										</g:link></li>
									<li><g:link controller="pfinMovimientoDet">
											<span>Movimiento Detalle</span>
										</g:link></li>										
								</ul>
							</div></li>
							
						<li><a href="#" class="parent"><span>Pruebas CR</span></a>
							<div>
								<ul>
									<li><g:link controller="prestamoPago" action="create">
											<span>Pago Credito</span>
										</g:link></li>
								</ul>
							</div></li>

					    <li><g:link controller="simCatEvento">
								<span>Eventos Dependencias</span>
							</g:link></li>
							
					</ul>
				</div></li>


			<sec:ifLoggedIn>
				<li><a href="#"><span>Prestamos</span></a>
					<div>
						<ul>
							<li><g:link controller="prestamo">
									<span>Solicitados</span>
								</g:link></li>
							<li><g:link controller="rsCliente">
									<span>Clientes</span>
								</g:link></li>
							<li><g:link controller="rsCliente" action="busquedaCliente">
									<span>B&uacute;squeda Cliente</span>
								</g:link></li>
							<li><g:link controller="proPromocion">
									<span>Promociones</span>
								</g:link></li>

							<li><a href="#" class="parent"><span>Credito Real</span></a>
								<div>
									<ul>
										<li><g:link controller="prestamo" action="showSolicitudesDecididasDia">
												<span>Solicitudes Decididas</span>
											</g:link></li>
										<li><g:link controller="prestamo"  action="showComprasDia">
												<span>Solicitudes Compradas</span>
											</g:link></li>
										<li><g:link controller="prestamo"  action="showCarteraGenerada">
												<span>Cartera Generada</span>
											</g:link></li>	
									</ul>
								</div>
							</li>

						</ul>
					</div></li>
			</sec:ifLoggedIn>

			<li><a href="#"><span>Empresa</span></a>
				<div>
					<ul>
						<li><g:link controller="rsPersona">
								<span>Personas</span>
							</g:link></li>
						<li><g:link controller="empEmpleado">
								<span>Empleados</span>
							</g:link></li>
						<li><g:link controller="rsGralTelefono">
								<span>Tel&eacute;fonos</span>
							</g:link></li>
						<li><g:link controller="rsGralDomicilio">
								<span>Domicilios</span>
							</g:link></li>
						<li><g:link controller="fechaEvento">
								<span>Calendario</span>
							</g:link></li>
					</ul>
				</div></li>

			<sec:ifLoggedIn>
				<li><a href="#"><span>Servicios</span></a>
					<div>
						<ul>
							<li><g:link controller="task" action="index">
									<span>Tareas</span>
								</g:link></li>

							<sec:ifAllGranted roles="ROLE_COBRANZA">								

							<li><a href="#" class="parent"><span>Procesos</span></a>
								<div>
									<ul>
										
											<li><g:link controller="listaCobroProceso" action="start">
													<span>Inicio Listas de Cobro</span>
												</g:link></li>
																	
										<!--		
										<li><g:link controller="solicitudCredito" action="start">
												<span>Credito</span>
											</g:link></li>
										<li><g:link controller="solicitudPrestamo" action="start">
												<span>Prestamo</span>
											</g:link></li>
										-->
									</ul>
								</div>
							</li>

							</sec:ifAllGranted>			
						</ul>
					</div></li>
			</sec:ifLoggedIn>

			<li><a href="#" class="parent"><span>Cobranza</span></a>

				<div>
					<ul>
						<li><a href="#" class="parent"><span>Listas de Cobro</span></a>
							<div>
								<ul>
								<li><g:link controller="listaCobro">
									<span>Listas de Cobro</span>
								</g:link></li>
								<li><g:link controller="listaCobroProceso">
									<span>Lista Cobro Procesos</span>
								</g:link></li>			
								<li><g:link controller="listaCobroDetalle">
									<span>Lista Cobro Detalles</span>
								</g:link></li>											
								</ul>
							</div>
						</li>
						<li><g:link controller="publicacionLote" action="publicarLote">
									<span>Publicaci&oacute;n</span>
							</g:link>
						</li>
					</ul>
				</div>
			</li>	

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
									<li><g:link controller="usuarioAcceso">
											<span>Accesos Entidades</span>
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
				<li><g:link controller="login" action="auth">
					</g:link></li>					
			
				<li class="last"><g:link controller="login" action="auth">
						<span>Entrar</span>
					</g:link></li>
					
			</sec:ifNotLoggedIn>

			<sec:ifLoggedIn>
				<li><g:link controller="login" action="auth">
					</g:link></li>				
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