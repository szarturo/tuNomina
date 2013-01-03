<%@ page import="com.rs.gral.RsGralDomicilio"%>
<!doctype html>
<html>
<head>
<meta name="layout" content="main">
<g:set var="entityName"
	value="${message(code: 'rsGralDomicilio.label', default: 'RsGralDomicilio')}" />
<title><g:message code="default.create.label"
		args="[entityName]" /></title>
<g:javascript library="prototype" />
</head>
<body>
	<a href="#create-rsGralDomicilio" class="skip" tabindex="-1"><g:message
			code="default.link.skip.label" default="Skip to content&hellip;" /></a>
	<div class="nav" role="navigation">
		<ul>
			<li><a class="home" href="${createLink(uri: '/')}"><g:message
						code="default.home.label" /></a></li>
			<li><g:link class="list" action="list">
					<g:message code="default.list.label" args="[entityName]" />
				</g:link></li>
		</ul>
	</div>
	<div id="create-rsGralDomicilio" class="content scaffold-create"
		role="main">
		<h1>
			<g:message code="default.create.label" args="[entityName]" />
		</h1>
		<g:if test="${flash.message}">
			<div class="message" role="status">
				${flash.message}
			</div>
		</g:if>
		<g:hasErrors bean="${rsGralDomicilioInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${rsGralDomicilioInstance}" var="error">
					<li
						<g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
							error="${error}" /></li>
				</g:eachError>
			</ul>
		</g:hasErrors>
		<g:form action="save">
			<fieldset class="form">
				<g:render template="form" />
			</fieldset>
			<fieldset class="buttons">
				<g:submitButton name="create" class="save"
					value="${message(code: 'default.button.create.label', default: 'Create')}" />
			</fieldset>
		</g:form>

		<g:javascript>			
	
			function updateDelegacionMunicipio(e) {
				// The response comes back as a bunch-o-JSON 
				var delegacionesMunicipios = eval('(' + e.responseText + ')') // evaluate JSON

				if (delegacionesMunicipios) { 
					var rselect = document.getElementById('delegacionMunicipio')
			
					// Clear all previous options
				 	var l = rselect.length
			
					while (l > 0) { 
						l-- 
						rselect.remove(l) 
					}
			
					// Rebuild the select 
					for (var i=0; i < delegacionesMunicipios.length; i++) { 
						var delegacionMunicipio = delegacionesMunicipios[i] 
						var opt = document.createElement('option'); 
						opt.text = delegacionMunicipio.nombreDelegacionMunicipio
						opt.value = delegacionMunicipio.id 
						try { 
							rselect.add(opt, null) // standards compliant; doesn't work in IE 
						} 
						catch(ex) { 
							rselect.add(opt) // IE only 
						} 
						if (i==0){
							${remoteFunction(controller:'rsGralDelegacionMunicipio', action:'ajaxGetAsentamiento', params:"'id=' + delegacionMunicipio.id", onComplete:'updateAsentamiento(e)')}
						}						
					}
				}		
			}			
			
			function updateAsentamiento(e) {
				// The response comes back as a bunch-o-JSON 
				var asentamientos = eval('(' + e.responseText + ')') // evaluate JSON

				if (asentamientos) { 
					var rselect = document.getElementById('asentamiento')
					var codigoPostal = document.getElementById('rsGralAsentamiento.codigoPostal')
					var idAsentamiento = document.getElementById('rsGralAsentamiento.id')
			
					// Clear all previous options
				 	var l = rselect.length
			
					while (l > 0) { 
						l-- 
						rselect.remove(l) 
					}
			
					// Rebuild the select 
					for (var i=0; i < asentamientos.length; i++) { 
						var asentamiento = asentamientos[i] 
						var opt = document.createElement('option'); 
						opt.text = asentamiento.nombreAsentamiento
						opt.value = asentamiento.id 
						try { 
							rselect.add(opt, null) // standards compliant; doesn't work in IE 
						} 
						catch(ex) { 
							rselect.add(opt) // IE only 
						}
						//ASIGNA EL CODIGO POSTAL DEL PRIMER ASENTAMIENTO OBTENIDO
						if (i==0){
							codigoPostal.value = asentamiento.codigoPostal
							idAsentamiento.value = asentamiento.id
						}						
					}
				}		
			}				
			
			function updateCodigoPostal(e) {
				// The response comes back as a JSON 
				var asentamiento = eval('(' + e.responseText + ')') // evaluate JSON
				var codigoPostal = document.getElementById('rsGralAsentamiento.codigoPostal')
				var idAsentamiento = document.getElementById('rsGralAsentamiento.id')

				if (asentamiento) { 
					codigoPostal.value = asentamiento.codigoPostal
					idAsentamiento.value =  asentamiento.id
				}		
			}
			
			//LAS SIGUIENTES FUNCIONES ACTUALIZAN LOS COMBOS DE ESTADO, DELEGACION/MUNICIPIO 
			//Y ASENTAMIENTO A PARTIR DEL CODIGO POSTAL
			function updateCombos(e) {
				var valores = eval('(' + e.responseText + ')') // evaluate JSON
				// OBTIENE EL ESTADO AL QUE PERTENECE EL CODIGO POSTAL
				var idEstado = valores[0];
				
				if (idEstado > 0) {
					
					var idDelegacionMunicipio = valores[1];
					var idAsentamiento = valores[2];
					
					var rselect = document.getElementById('rsGralEstado.nombreEstado')
					//ASIGNA AL COMBO DE ESTADO EL ESTADO SELECCIONADO A PARTIR DEL CODIGO POSTAL
					rselect.value = idEstado;	
					
					${remoteFunction(controller:'rsGralEstado', action:'ajaxGetDelegacionMunicipio', params:"'id=' + idEstado", onComplete:'updateComboDelegacionMunicipio(e,idDelegacionMunicipio,idAsentamiento)')}
				                                                                  			
				}else{
					
					// LIMPIA EL COMBO DE DELEGACION/MUNICIPIO
					var delegacionMunicipio = document.getElementById('delegacionMunicipio')
					// Clear all previous options
				 	var l = delegacionMunicipio.length
					while (l > 0) { 
						l-- 
						delegacionMunicipio.remove(l) 
					}
					
					// LIMPIA EL COMBO DE ASENTAMIENTO
					var asentamiento = document.getElementById('asentamiento')
					// Clear all previous options
				 	var l = asentamiento.length
					while (l > 0) { 
						l-- 
						asentamiento.remove(l) 
					}
				}			
			}			
			
		
			//ACTUALIZA LAS DELEGACIONES/MUNICIPIOS QUE PERTENECEN AL ESTADO DEL CODIGO POSTAL SELECCIONADO
			function updateComboDelegacionMunicipio(e,idDelegacionMunicipio,idAsentamiento) {
				// The response comes back as a bunch-o-JSON 
				var delegacionesMunicipios = eval('(' + e.responseText + ')') // evaluate JSON

				if (delegacionesMunicipios) { 
					var rselect = document.getElementById('delegacionMunicipio')
			
					// Clear all previous options
				 	var l = rselect.length
			
					while (l > 0) { 
						l-- 
						rselect.remove(l) 
					}
			
					// Rebuild the select 
					for (var i=0; i < delegacionesMunicipios.length; i++) { 
						var delegacionMunicipio = delegacionesMunicipios[i] 
						var opt = document.createElement('option'); 
						opt.text = delegacionMunicipio.nombreDelegacionMunicipio
						opt.value = delegacionMunicipio.id 
						try { 
							rselect.add(opt, null) // standards compliant; doesn't work in IE 
						} 
						catch(ex) { 
							rselect.add(opt) // IE only 
						} 
					}
					//ASIGNA AL COMBO DE DELEGACIONES/MUNICIPIOS LA DELEGACION/MUNICIPIO QUE PERTENECE AL CODIGO POSTAL
					rselect.value = idDelegacionMunicipio;
					${remoteFunction(controller:'rsGralDelegacionMunicipio', action:'ajaxGetAsentamiento', params:"'id=' + idDelegacionMunicipio", onComplete:'updateComboAsentamiento(e,idAsentamiento)')}
				}	
			}	
			
			//ACTUALIZA LOS ASENTAMIENTOS QUE PERTENECEN A LA DELEGACION/MUNICIPIO DEL CODIGO POSTAL SELECCIONADO			
			function updateComboAsentamiento(e,idAsentamiento) {
				// The response comes back as a bunch-o-JSON 
				var asentamientos = eval('(' + e.responseText + ')') // evaluate JSON

				if (asentamientos) { 
					var rselect = document.getElementById('asentamiento')
			
					// Clear all previous options
				 	var l = rselect.length
			
					while (l > 0) { 
						l-- 
						rselect.remove(l) 
					}
			
					// Rebuild the select 
					for (var i=0; i < asentamientos.length; i++) { 
						var asentamiento = asentamientos[i] 
						var opt = document.createElement('option'); 
						opt.text = asentamiento.nombreAsentamiento
						opt.value = asentamiento.id 
						try { 
							rselect.add(opt, null) // standards compliant; doesn't work in IE 
						} 
						catch(ex) { 
							rselect.add(opt) // IE only 
						}
					}
					//ASIGNA AL COMBO DE ASENTAMIENTOS EL ASENTAMIENTO AL QUE PERTENECE EL CODIGO POSTAL
					rselect.value = idAsentamiento;
					${remoteFunction(controller:'rsGralAsentamiento', action:'ajaxGetCodigoPostal', params:"'id=' + idAsentamiento", onComplete:'updateIdAsentamiento(e)')}
				}
			}		
				
			function updateIdAsentamiento(e) {
				// The response comes back as a JSON 
				var asentamiento = eval('(' + e.responseText + ')') // evaluate JSON
				var idAsentamiento = document.getElementById('rsGralAsentamiento.id')
				var codigoPostal = document.getElementById('rsGralAsentamiento.codigoPostal')
				if (asentamiento) { 
					idAsentamiento.value =  asentamiento.id
					codigoPostal.value =  asentamiento.codigoPostal
				}		
			}				
						
		</g:javascript>

	</div>
</body>
</html>
