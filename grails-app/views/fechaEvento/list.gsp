
<%@ page import="com.sim.calendario.FechaEvento" %>
<%@ page import="com.sim.calendario.Evento" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'fechaEvento.label', default: 'FechaEvento')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
		<link rel='stylesheet' type='text/css' href='${request.contextPath}/calendario/fullcalendar/fullcalendar.css' />
		<link rel='stylesheet' type='text/css' href='${request.contextPath}/calendario/fullcalendar/fullcalendar.print.css' media='print' />
		<script type='text/javascript' src='${request.contextPath}/calendario/jquery/jquery-1.8.1.min.js'></script>
		<script type='text/javascript' src='${request.contextPath}/calendario/jquery/jquery-ui-1.8.23.custom.min.js'></script>
		<script type='text/javascript' src='${request.contextPath}/calendario/fullcalendar/fullcalendar.min.js'></script>
		<script type='text/javascript'>

var idContador=1;
var objSave = new Array();var cSave=0;
var objRemove = new Array();var cRemove=0;
		
	$(document).ready(function() {
	
	
		/* initialize the external events
		-----------------------------------------------------------------*/
	
		$('#external-events div.external-event').each(function() {


			// create an Event Object (http://arshaw.com/fullcalendar/docs/event_data/Event_Object/)
			// it doesn't need to have a start or end
			var eventObject = {
				title: $.trim($(this).text()) // use the element's text as the event title
			};
			
			// store the Event Object in the DOM element so we can get to it later
			$(this).data('eventObject', eventObject);
			
			// make the event draggable using jQuery UI
			$(this).draggable({
				zIndex: 999,
				revert: true,      // will cause the event to go back to its
				revertDuration: 0  //  original position after the drag
			});
			
		});
	
	
		/* initialize the calendar
		-----------------------------------------------------------------*/
		
		$('#calendar').fullCalendar({
			header: {
				left: 'prev,next today',
				center: 'title',
				//right: 'month,agendaWeek,agendaDay'
				right: ''
			},
			editable: false,
			droppable: true, // this allows things to be dropped onto the calendar !!!
			drop: function(date, allDay) { // this function is called when something is dropped

				
				// retrieve the dropped element's stored Event Object
				var originalEventObject = $(this).data('eventObject');
				
				// we need to copy it, so that multiple events don't have a reference to the same object
				var copiedEventObject = $.extend({}, originalEventObject);
				
				// assign it the date that was reported
				copiedEventObject.start = date;
				copiedEventObject.allDay = allDay;
				copiedEventObject.id="save"+idContador++;
				objSave[cSave++]=copiedEventObject;

				
				// render the event on the calendar
				// the last `true` argument determines if the event "sticks" (http://arshaw.com/fullcalendar/docs/event_rendering/renderEvent/)
				$('#calendar').fullCalendar('renderEvent', copiedEventObject, true);
				
				// is the "remove after drop" checkbox checked?
				//if ($('#drop-remove').is(':checked')) {
					// if so, remove the element from the "Draggable Events" list
					//$(this).remove();
				//}

			},
			eventClick: function(calEvent, jsEvent, view) {

				if(calEvent.id.indexOf('save') == -1){
					objRemove[cRemove++]=calEvent;
				}else{
					for(var i in objSave){
						
						if(objSave[i]!=null && objSave[i].id==calEvent.id){
							objSave[i]="null";
						}
					}
				}
				
				$('#calendar').fullCalendar( 'removeEvents' ,[calEvent.id]);
			}
		});
		
		
	});

	
	$(document).ready(function() {
	    <g:each in="${fechaEventoInstanceList}" status="i" var="evento">
			$('#calendar').fullCalendar('renderEvent', {title: '${evento.evento.evento}', date:'${evento.fecha}', allDay : true, id:'${evento.id}' }, true);
		</g:each>
	});
	


	function submit(){
		//alert(objSave);
		//alert(objRemove);

		document.frmAction.action=document.frmAction.action+"?f=1";
		var c1=0, c2=0;
		for(var i in objSave){
			if(objSave[i]!=null && objSave[i]!="null"){
				document.frmAction.action=document.frmAction.action+
				"&eventoSave="+objSave[i].title+
				"&fechaSave="+$.fullCalendar.formatDate( objSave[i].start , "dd/MM/yyyy" );
				c1++;		
			}
		}
		for(var i in objRemove){
			if(objRemove[i]!=null && objRemove[i]!="null"){
				document.frmAction.action=document.frmAction.action+"&idRemove="+objRemove[i].id;
				c2++;	
			}
		}

		if(c1<=0 && c2<=0){
			return;
		}

		document.frmAction.nsave.value=c1;
		document.frmAction.nremove.value=c2;
		
		document.frmAction.submit();

		return false;
	}

</script>
<style type='text/css'>

	body {
		margin-top: 40px;
		text-align: center;
		font-size: 14px;
		font-family: "Lucida Grande",Helvetica,Arial,Verdana,sans-serif;
		}
		
	#wrap {
		margin: 0 auto;
		}
		
	#external-events {
		float: left;
		width: 130px;
		padding: 0 10px;
		border: 1px solid #ccc;
		background: #eee;
		text-align: left;
		}
		
	#external-events h4 {
		font-size: 16px;
		margin-top: 0;
		padding-top: 1em;
		}
		
	.external-event { /* try to mimick the look of a real event */
		margin: 10px 0;
		padding: 2px 4px;
		background: #3366CC;
		color: #fff;
		font-size: .85em;
		cursor: pointer;
		}
		
	#external-events p {
		margin: 1.5em 0;
		font-size: 11px;
		color: #666;
		}
		
	#external-events p input {
		margin: 0;
		vertical-align: middle;
		}

	#calendar {
		float: right;
		width: 800px;
		}

</style>
		
	</head>
	<body>
		<a href="#list-fechaEvento" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
			
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li>
					<g:form name="frmReload" action="index">
						<g:select name="dependencia" value="${dependencia.nombre }"  
					      from="${dependencias}"
					      optionValue="nombre"
					      optionKey="nombre" />
					</g:form>				      
				</li>
				<li>
					<h1>Dependencia ${dependencia }</h1>
				</li>
			 <!-- 
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
				-->
			</ul>
		</div>
		
		<br />
		
		<div id='wrap' >
			<div id='external-events'>
			<h4>Eventos </h4>
			<g:each in="${eventos}" status="i" var="eventoInstance">
				<div class='external-event'>${fieldValue(bean: eventoInstance, field: "evento")}</div>
			</g:each>
			</div>
			
			<div id='calendar'></div>
			
			<div style='clear:both'></div>
		</div>
		
		<g:form name="frmAction" action="saveEvento">
			<g:hiddenField name="dependenciaid"  value="${dependencia.id}" />
			<g:hiddenField name="nsave"  value="0" />
			<g:hiddenField name="nremove"  value="0" />
			<g:hiddenField name="dependencia"  value="${dependencia}" />
			
		</g:form>
		
		<input type="button" value="Guardar" onclick="submit();" />
		
		
		<script>	
		document.frmReload.dependencia.onchange=function(){
			document.frmReload.submit();
		}
		</script>
</body>

</html>

