<#assign c=JspTaglibs["http://java.sun.com/jsp/jstl/core"]/>
<#assign security=JspTaglibs["http://www.springframework.org/security/tags"] />
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Sin Guía - Profesionales de confianza</title>

    <!-- Bootstrap Core CSS -->
    <link href="<@c.url value='/static/new/css/bootstrap.min.css'/>" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="<@c.url value='/static/new/css/agency.css'/>" rel="stylesheet">
    <link href="<@c.url value='/static/new/css/dashboard.css'/>" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="<@c.url value='/static/new/font-awesome-4.1.0/css/font-awesome.min.css'/>" rel="stylesheet" type="text/css">
    <link href="http://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css">
    <link href='http://fonts.googleapis.com/css?family=Kaushan+Script' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Droid+Serif:400,700,400italic,700italic' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700' rel='stylesheet' type='text/css'>

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body id="page-top" class="index">
    
    <!-- Navigation -->
    <nav class="navbar navbar-default navbar-fixed-top navbar-shrink">
        <#include "header.ftl">    
    </nav>

    <!-- Services Section -->
    <section id="services" style="margin-top: 120px;">
        <div class="container no-top-border-radius">
            <div class="row" style="margin-bottom: 15px;">
            	<div class="col-md-6">
            		<h1 class="pull-left" style="margin: 0px;">Panel de control</h1>
            	</div>
            	<div class="col-md-6">
            		<@security.authorize ifNotGranted="ROLE_ADMIN">	
            			<a href="<@c.url value='/'/>" class="button btn btn-info pull-right" style="margin-top: 3px;">Pedir nuevo presupuesto</a>
            		</@security.authorize>
            	</div>
            </div>
            <div class="row">
            	<div class="col-md-3">            	
            		<ul id="dash-menu" class="nav nav-pills nav-stacked">
            			<@security.authorize ifNotGranted="ROLE_ADMIN">
					      <li id="perfil-option"><a href="<@c.url value='/dashboard/profile'/>">Mi perfil</a></li>
					      <li id="pedidos-option"><a href="<@c.url value='/dashboard/requests'/>">Mis presupuestos pedidos</a></li>
					      <li id="calificaciones-option"><a href="<@c.url value='/dashboard/ratings'/>"><span class="badge pull-right">7</span> Calificaciones</a></li>
					      <li id="redessociales-option"><a href="<@c.url value='/dashboard/socialmedia'/>">Redes Sociales</a></li>
					      <#if user.isProfessional> 
					      	<li id="presupuestos-option"><a href="<@c.url value='/dashboard/budgets'/>"><span class="badge pull-right">5</span> Responder pedidos</a></li>
					      	<li id="preguntas-option"><a href="<@c.url value='/dashboard/questions'/>"><span class="badge pull-right">2</span> Preguntas</a></li>
					      	<li id="pagos-option"><a href="<@c.url value='/dashboard/payments'/>">Pagos</a></li>
					      	<li id="configuracion-option"><a href="<@c.url value='/dashboard/config'/>">Configuración</a></li>
					      </#if>					      					      
					  </@security.authorize>
					  <@security.authorize ifAllGranted="ROLE_ADMIN">
        				<li id="administracion-option"><a href="<@c.url value='/dashboard/admin'/>">Administracion</a></li>
        				<li id="reportes-option"><a href="<@c.url value='/dashboard/reports'/>">Reportes</a></li>
    				  </@security.authorize>				      
				    </ul>
            	</div>
            	
            	<div class="col-md-9">
            	
            		<div class="alert alert-success alert-dismissible" role="alert">
				  		<button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
				  		<span class="glyphicon glyphicon-ok"></span> Su presupuesto fue enviado con exito.
					</div>
					<div class="alert alert-warning alert-dismissible" role="alert">
				  		<button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
				  		<span class="fa fa-exclamation-triangle"></span> Mensaje de atencion.
					</div>
					<div class="alert alert-warning alert-danger" role="alert">
				  		<button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
				  		<span class="glyphicon glyphicon-remove"></span> Mensaje de error.
					</div>
            	
            	
            		<#if user.isProfessional>
	            		<div id="pagos-panel" class="col-md-12 dashboard-panel">
							<div class="row">
								<div class="panel panel-default">
						  			<div class="panel-heading">
							    		<h3 class="panel-title">
							    			Pagos
							    		</h3>
							  		</div>
							  		<div class="panel-body">
							  			<div class="col-md-12">						  				
											<table class="table">  
												<thead>  
													<tr>  
											        	<th>Fecha</th>
											        	<th>Concepto</th>
											            <th>Monto</th>
											            <th>Estado</th>
											        </tr>
											    </thead>  
											    <tbody>
											    	<#list payments as payment>  
												    	<tr>  
												        	<td>${payment.updatedAt?string("dd-MM-yyyy")}</td>
												        	<td>${ratePlan.description}</td>
												            <td>${payment.amount.currency.symbol} ${payment.amount.amount}</td>
												            <td>
												            	<#if payment.statusType="DONE">
												            		PAGADO
												            	<#else>
												            		PENDIENTE
												            	</#if>
												            </td>
												        </tr>
													</#list>
											    </tbody>
											</table>					  			
							  			</div>
							  		</div>
							  	</div>
							</div>   		
	            		</div>            	
            		</#if>
            		
            		<@security.authorize ifAllGranted="ROLE_ADMIN">
	            		<div id="reportes-panel" class="col-md-12 dashboard-panel">
							<div class="row">
								<div class="panel panel-default">
						  			<div class="panel-heading">
							    		<h3 class="panel-title">
							    			Reportes
							    		</h3>
							  		</div>
							  		<div class="panel-body">
							  			<div class="col-md-12">
							  				<h3 class="panel-title">
												Cantidad de usuarios registrados por mes
											</h3>						  				
											<table class="table">  
												<thead>  
													<tr>  
											        	<th>Año-mes</th>
											            <th>Usuarios</th>
											            <th>Profesionales</th>
											        </tr>
											    </thead>  
											    <tbody>
											    	<#list reportUsers as reportUser>  
												    	<tr>  
												        	<td>${reportUser.yearMonth}</td>
												            <td>${reportUser.userCount}</td>
												            <td>${reportUser.isProfessionalCount}</td>
												        </tr>
													</#list>
											    </tbody>
											</table>										
							  				<h3 class="panel-title">
												Cantidad de servicios concretados por dia
											</h3>						  				
											<table class="table">  
												<thead>  
													<tr>  
											        	<th>Fecha</th>
											            <th>Rubro</th>
											            <th>Cantidad</th>
											        </tr>
											    </thead>  
											    <tbody>
											    	<#list quoteServicesDone as quoteServiceDone>  
												    	<tr>  
												        	<td>${quoteServiceDone.date}</td>
												            <td>${quoteServiceDone.workAreaDescription}</td>
												            <td>${quoteServiceDone.count}</td>
												        </tr>
													</#list>
											    </tbody>
											</table>										
							  				<h3 class="panel-title">
												Cantidad de profesionales postulados por rubro por dia
											</h3>						  				
											<table class="table">  
												<thead>  
													<tr>  
											        	<th>Fecha</th>
											            <th>Rubro</th>
											            <th>Cantidad</th>
											        </tr>
											    </thead>  
											    <tbody>
											    	<#list quoteServicesReplied as quoteServicesReply>  
												    	<tr>  
												        	<td>${quoteServicesReply.date}</td>
												            <td>${quoteServicesReply.workAreaDescription}</td>
												            <td>${quoteServicesReply.count}</td>
												        </tr>
													</#list>
											    </tbody>
											</table>																										  			
							  			</div>
							  		</div>
							  	</div>
							</div>   		
	            		</div>
	            		
	            		<div id="administracion-panel" class="col-md-12 dashboard-panel">
							<div class="row">
								<div class="panel panel-default">
						  			<div class="panel-heading">
							    		<h3 class="panel-title">
							    			Administrar usuarios
							    		</h3>
							  		</div>
							  		<div class="panel-body">
							  			<div class="col-md-6">
							  			</div>
							  		</div>
							  	</div>
							</div>   		
	            		</div>            		
					</@security.authorize>
					
            		<div id="redessociales-panel" class="col-md-12 dashboard-panel">
						<div class="row">
							<div class="panel panel-default">
					  			<div class="panel-heading">
						    		<h3 class="panel-title">
						    			Configurar redes sociales
						    		</h3>
						  		</div>
						  		<div class="panel-body">
							  		<div class="col-md-6">
								  		<div class="social-column">
								  			<#if connections??>
												<form name="fb_disconnect" action="<@c.url value='/connect/facebook'/>" method="POST">
													<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
													<input type="hidden" name="_method" value="delete" />							  			
													<a href="javascript:document.fb_disconnect.submit()">
						      							<div class="fb-button">
						      								<span>Desconectar de facebook</span>
						      							</div>
						      						</a>
												</form>						  		
								  			<#else>
												<form name="fb_connect" action="<@c.url value='/connect/facebook'/>" method="POST">
													<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>							  			
													<a href="javascript:document.fb_connect.submit()">
						      							<div class="fb-button">
						      								<span>Conectar con facebook</span>
						      							</div>
						      						</a>
												</form>
								  			</#if>
							  			</div>	
						  			</div>								  		
						  		</div>
						  	</div>
						</div>   		
            		</div>
            		
            		<#if user.isProfessional>
	            		<div id="configuracion-panel" class="col-md-12 dashboard-panel">
							<form name="configForm" action="<@c.url value='/dashboard/config' />" method="POST" enctype="utf8">
								<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
								<@spring.bind "config" />            		
			            		<div class="row">
									<div class="panel panel-default">
								  		<div class="panel-heading">
									    	<h3 class="panel-title">
									    		Configurar zona de trabajo
									    	</h3>
									  	</div>
									  	<div class="panel-body">
									  		<#list states as state>
										  		<div class="row">
											  		<div class="col-md-12 form-group">
														<h4>${state.description}</h4>
													</div>
													<div class="col-md-12 form-group">
														<label>Localidades:</label>
														<a class="btn btn-default pull-right select-all-cities">Seleccionar todas</a>
													</div>
													<div class="col-md-12 form-group">
														<#list cities as city>
															<#if city.stateCode=state.code>									
																<div class="col-md-3">
																	<input ${config.cityCodes?seq_contains(city.code)?string("checked", "")} name="cityCodes" type="checkbox" value="${city.code}"> ${city.description}
																</div>
															</#if>
														</#list>
													</div>
												</div>									
											</#list>
									  	</div>							  	
								  	</div>
							  	</div>
							  	<div class="row">
			                        <div class="col-md-12" style="text-align: center;">
			                        	<a href="javascript:document.configForm.submit()" class="button btn btn-warning">Guardar</a>
			                        </div>
		                       	</div>
		                	</form>
						</div>
						
						
						<div id="preguntas-panel" class="col-md-12 dashboard-panel">
							<div class="row">
								<div class="panel panel-default">
						  			<div class="panel-heading">
							    		<h3 class="panel-title">
							    			Nombre Usuario - 02/09/2014
							    		</h3>
							  		</div>
							  		<div class="panel-body">
								  		<div class="col-md-12 form-group">
									  		MOSTRAR DETALLE DEL PEDIDO
							  			</div>
							  			<div class="col-md-12 form-group">
									  		MOSTRAR PRESUPUESTO REALIZADO
							  			</div>
							  			<div class="col-md-12 form-group">
									  		<label>Pregunta</label>
									  		<div>Que pasa si tengo que aal lal lal all lalal alal la?</div>
							  			</div>						  		
							  			<div class="col-md-12 form-group">
									  		<label>Respuesta</label>
									  		<textarea class="form-control"></textarea>
							  			</div>
							  			<div class="col-md-12">
							  				<a href="#" class="button btn btn-warning pull-right">Enviar respuesta</a>
							  			</div>
							  		</div>
							  	</div>
							</div>   		
	            		</div>
            	    </#if>
            	            	
            		<div id="calificaciones-panel" class="col-md-12 dashboard-panel">
	            		<div class="row">
		            		<div class="btn-group btn-group-justified">
		            		  <div class="btn-group">
							    <button type="button" class="btn btn-default" data-show-class="user-work-rate-PENDING">Pendientes</button>
							  </div>
							  <div class="btn-group">
							    <button type="button" class="btn btn-default" data-show-class="user-work-rate-DONE">Hechas</button>
							  </div>
							  <div class="btn-group">
							    <button type="button" class="btn btn-default">Recibidas</button>
							  </div>
							</div>
						</div>
						<div class="row">
							<div class="panel-group" id="accordion">
								<#list userWorkRates as userWorkRate>
									<#if userWorkRate.statusType='DONE'>
									  	<div class="panel panel-default user-work-rate-${userWorkRate.statusType}" id="panel${userWorkRate_index}">
									    	<div class="panel-heading" data-toggle="collapse" data-target="#collapse${userWorkRate_index}" style="cursor:pointer;">
										      	<h4 class="panel-title">
										        	<a  href="#collapse${userWorkRate_index}">
										          		${userWorkRate.userFirstName} ${userWorkRate.userLastName}
										          		<span class="fa"></span>							          		
										        	</a>							        	
										      	</h4>
									    	</div>
										    <div id="collapse${userWorkRate_index}" class="panel-collapse collapse in">
										      	<div class="panel-body">
										      		<div class="col-md-12">
								        				<div class="row form-group">
								        					<div class="col-md-12">
									        					<label>Trabajo realizado: </label> ${userWorkRate.workCompleted?string('Si', 'No')}
									        				</div>
									        			</div>
									        			<div class="row form-group">
								        					<div class="col-md-12">
									        					<label>Calificacion: </label> 
									        					<#if userWorkRate.ratingType="POSITIVE">
									        						POSITIVA	
									        					<#elseif userWorkRate.ratingType="NEUTRAL">
									        						NEUTRAL
									        					<#else>
									        						NEGATIVA		
									        					</#if>
									        				</div>		
									        			</div>
									        			<div class="row form-group">
								        					<div class="col-md-12">
									        					<label>Comentario: </label> ${userWorkRate.comment}
									        				</div>		
									        			</div>
								        			</div>						        	
										      	</div>
									    	</div>
									  	</div>
									<#else>
										<form name="userWorkRatesForm${userWorkRate_index}" action="<@c.url value='/dashboard/ratings' />" method="POST" enctype="utf8">
											<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
											<@spring.bind "userWorkRate" />
											<input type="hidden" name="id" value="${userWorkRate.id}">
											<input type="hidden" name="quoteId" value="${userWorkRate.quoteId}">
										  	<div class="panel panel-default user-work-rate-${userWorkRate.statusType}" id="panel${userWorkRate_index}">
										    	<div class="panel-heading" data-toggle="collapse" data-target="#collapse${userWorkRate_index}" style="cursor:pointer;">
											      	<h4 class="panel-title">
											        	<a  href="#collapse${userWorkRate_index}">
											          		${userWorkRate.quoteUserFirstName} ${userWorkRate.quoteUserLastName} (${userWorkRate.quoteUserWorkAreaDescription})
											          		<span class="fa"></span>							          		
											        	</a>							        	
											      	</h4>
										    	</div>
											    <div id="collapse${userWorkRate_index}" class="panel-collapse collapse in">
											      	<div class="panel-body">
											      		<div class="col-md-12">
									        				<div class="row form-group">
									        					<div class="col-md-12">
										        					<label>¿El trabajo fue realizado?</label>
										        				</div>
										        				<div class="col-md-2">
										        					<input type="radio" name="workCompleted" value="YES"> Si
										        				</div>
											        			<div class="col-md-2">
											        				<input type="radio" name="workCompleted" value="NO"> No
											        			</div>	
										        			</div>
										        			<div class="row form-group">
									        					<div class="col-md-12">
										        					<label>¿Cómo calificarias a Jose Marmol?</label>
										        				</div>
										        				<div class="col-md-2">
										        					<input type="radio" name="ratingType" value="POSITIVE"> Positivo
										        				</div>
											        			<div class="col-md-2">
											        				<input type="radio" name="ratingType" value="NEUTRAL"> Neutral
											        			</div>
											        			<div class="col-md-2">
											        				<input type="radio" name="ratingType" value="NEGATIVE"> Negativo
											        			</div>		
										        			</div>
										        			<div class="row form-group">
									        					<div class="col-md-12">
										        					<label>¿Tienes algún comentario?</label>
										        					<textarea name="comment" class="form-control"></textarea>
										        				</div>		
										        			</div>
										        			<div class="row form-group">
									        					<div class="col-md-12">
										        					<a href="javascript:document.userWorkRatesForm${userWorkRate_index}.submit()" class="button btn btn-warning pull-right">Calificar</a>
										        				</div>		
										        			</div>
									        			</div>						        	
											      	</div>
										    	</div>
										  	</div>
										</form>									
								  	</#if>							
								</#list>
							</div>
						</div>
					</div>
            	
            		<div id="pedidos-panel" class="col-md-12 dashboard-panel">
            			<div class="row">
		            		<div class="btn-group btn-group-justified">
							  <div class="btn-group">
							    <button type="button" class="btn btn-default active" data-show-class="order-IN_PROGRESS">En curso</button>
							  </div>
							  <div class="btn-group">
							    <button type="button" class="btn btn-default" data-show-class="order-FINISHED">Finalizados</button>
							  </div>
							</div>
						</div>
												
						<#list orders as order>
							<#-- ejemplo de un presupuesto pedido -->
							<div class="row">
								<div class="panel panel-default order-${order.statusType}">
							  		<div class="panel-heading">
								    	<h3 class="panel-title">
								    		Necesito un ${order.workAreaDescription}
								    		<#if order.workDateType='URGENT'>
								    			<span class="pull-right urgente">Es urgente</span>
								    		</#if>
								    	</h3>
								  	</div>
								  	<div class="panel-body">
								  		<div class="row">
								  			<#list order.workAreaItemCodes as workAreaItemCode>
								  				<#list workAreaItems as workAreaItem>
													<#if workAreaItem.code=workAreaItemCode>
														<#list workAreaQuestions as workAreaQuestion>
															<#if workAreaQuestion.workAreaCode=workAreaItem.workAreaCode && workAreaQuestion.groupType=workAreaItem.groupType>
						                        				<div class="form-group col-md-6">
						                        					<label>${workAreaQuestion.description}</label> ${workAreaItem.description}
						                        				</div>
								    						</#if>
								    					</#list>
								    				</#if>								  					
								  				</#list>
					                   		</#list>						                        
					                   </div>
					                   <#if order.squareMeters??>
					                       <div class="row">
						                        <div class="form-group col-md-12">
						                        	<label>¿Que tamaño tiene la superficie?</label> ${order.squareMeters} m2
						                        </div>
					                       </div>
									   </#if>
					                   <#if order.airConditionerPower??>
					                       <div class="row">
						                        <div class="form-group col-md-12">
						                        	<label>¿Cuantas frigorias tiene el aire?</label> ${order.airConditionerPower} frigorias
						                        </div>
					                       </div>
									   </#if>									   					                       					                   
				                       <div class="row">
					                        <div class="form-group col-md-12">
					                        	<label>Trabajo a realizar en:</label> ${order.stateDescription}, ${(order.cityDescription)!""}
					                        </div>
				                       </div>				                       
				                       <div class="row">
					                        <div class="form-group col-md-12">
					                        	<label>Detalle del trabajo:</label> ${order.workDescription}
					                        </div>
				                       </div>
								  	</div>								  	
								</div>
								<div class="response-list-section col-md-12">
									<div class="col-md-12 response-list-panel">
										<div class="col-md-12">
											<h5>Presupuestos</h5>
										</div>
										<ul class="response-list">
											<#assign hasReplies=false />										
											<#list quotes as quote>
												<#if quote.orderId==order.id && (quote.statusType="REPLIED" || quote.statusType="ACCEPTED" || quote.statusType="DONE")>
													<#assign hasReplies=true />				
											  		<li class="budget-response col-md-12">
											  			<div class="col-md-3">
											  				${quote.firstName} ${quote.lastName} (${userWorkRatesQtyMap[quote.username]})
											  			</div>
											  			<div class="col-md-2">
											  				<#if quote.requireVisit == false>
											  					Total ${quote.amount.currency.symbol}${quote.amount.amount}
											  				<#else>
											  					Visita ${quote.visitAmount.currency.symbol}${quote.visitAmount.amount}
											  				</#if>
											  			</div>
											  			<div class="col-md-3" style="color: #bbb;">
											  				<#if quote.requireVisit == false>
											  					Válido hasta ${quote.validDateUntil?string("dd/MM/yy")}
											  				</#if>
											  			</div>
											  			<div class="col-md-4">
											  				<#if quote.statusType="REPLIED">
												  				<form name="budgetsFormAccepted${quote_index}" action="<@c.url value='/dashboard/requests/accepted/${quote.id}' />" method="POST" enctype="utf8">
												  					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
												  					<a href="javascript:document.budgetsFormAccepted${quote_index}.submit()" class="button btn btn-xs btn-warning pull-right">Aceptar</a>
												  				</form>
												  			<#elseif quote.statusType="ACCEPTED">
												  				Aceptado(Pendiente calificacion)
												  			</#if>
											  				<a class="button btn btn-xs btn-default pull-right make-question" style="margin-right:10px;cursor:pointer;">Realizar pregunta</a>							  				
											  			</div>
											  			<form name="questionsForm${quote_index}" action="<@c.url value='/dashboard/questions' />" method="POST" enctype="utf8" style="display:none;">
															<@spring.bind "quoteQuestion" />
											  				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
											  				<input type="hidden" name="quoteId" value="${quote.id}">
												  			<ul class="messages-list">
													  				<#list quote.quoteQuestions as quoteQuestion>
														  				<li class="col-md-12">
														  					<div class="question-message message">
															  					<div class="user-name-message">${quoteQuestion.description}</div>
															  					<div class="message-content">${(quoteQuestion.quoteQuestionReply.description)!""}</div>
															  				</div>
														  				</li>
														  			</#list>
												  			</ul>
												  			<div class="col-md-12" style="margin-top:8px;">
												  				<textarea name="description" class="form-control"></textarea>
												  				<a href="javascript:document.questionsForm${quote_index}.submit()" class="button btn btn-xs btn-info pull-right" style="margin-top:5px;">Enviar mensaje</a>
												  			</div>												  			
													  	</form>
											  		</li>											  		
											  	</#if>
											</#list>
											<#if hasReplies == false>
												<li class="budget-response col-md-12">
										  			<div class="col-md-12">
										  				Aún no se han recibido presupuestos.
										  			</div>
										  		</li>
											</#if>											
							  			</ul>
							  		</div>
						  		</div>
							</div>
							<#-- fin de ejemplo de un presupuesto pedido -->						
						</#list>						
						
					</div>
					
					<div id="perfil-panel" class="col-md-12 dashboard-panel">
						<div class="row">
							<form action="<@c.url value='/dashboard/profile' />" method="POST" enctype="utf8">
								<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
								<@spring.bind "user" />
								<div class="row">
			                        <div class="form-group col-md-6">	                        
			                        	<label>Nombre</label>
										<@spring.formInput "user.firstName", 'class="form-control"'/>
			                        	<@spring.showErrors "<br>" />	                        	
			                        </div>
			                        <div class="form-group col-md-6">
			                            <label>Apellido</label>
										<@spring.formInput "user.lastName", 'class="form-control"'/>
			                        	<@spring.showErrors "<br>" />
			                        </div>
			                    </div>
			                    <div class="row">
			                        <div class="form-group col-md-6">
			                            <label>Email</label>
			                        	<@spring.formInput "user.email", 'class="form-control" readonly'/>
			                        	<@spring.showErrors "<br>" />	                                           	
			                        </div>
			                        <div class="form-group col-md-6">
			                            <label>Telefono</label>
		                                <@spring.formInput "user.telephone", 'class="form-control"'/>
		                                <@spring.showErrors "<br>" />
			                        </div>
			                    </div>
			                    <div class="row">
			                        <div class="form-group col-md-6">
			                            <label>Contraseña actual</label>
		                                <@spring.formPasswordInput "user.password", 'class="form-control"'/>
		                                <@spring.showErrors "<br>" />
			                        </div>
			                    </div>		                    	                    
			                    <div class="row">
			                        <div class="form-group col-md-6">
			                            <label>Contraseña nueva</label>
		                                <@spring.formPasswordInput "user.newPassword", 'class="form-control"'/>
		                                <@spring.showErrors "<br>" />	                            
			                        </div>
			                        <div class="form-group col-md-6">
			                            <label>Repetir contraseña nueva</label>
		                                <@spring.formPasswordInput "user.newPasswordVerification", 'class="form-control"'/>
		                                <@spring.showErrors "<br>" />	                            
			                        </div>
			                    </div>		                    
			                    <button type="submit" class="btn btn-warning pull-right">Guardar</button>
		                    </form>
	                   	</div>
                   	</div>
                   	
                   	<#if user.isProfessional>
	                   	<div id="presupuestos-panel" class="col-md-12 dashboard-panel">
	            			<div class="row">
			            		<div class="btn-group btn-group-justified">
								  <div class="btn-group">
								    <button type="button" class="btn btn-default" data-show-class="quote-PENDING">Presupuestos pendientes</button>
								  </div>
								  <div class="btn-group">
								    <button type="button" class="btn btn-default" data-show-class="quote-REPLIED">Presupuestos realizados</button>
								  </div>
								</div>
							</div>
																			
							<#list quotes as quote>
								<#if quote.statusType="PENDING">
									<div class="row">
										<div class="panel panel-default quote-${quote.statusType}">						
											<form name="budgetsForm${quote_index}" action="<@c.url value='/dashboard/budgets/replied' />" method="POST" enctype="utf8">
												<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
												<input type="hidden" name="statusType" value="${quote.statusType}">
												<input type="hidden" name="id" value="${quote.id}">
												<@spring.bind "quote" />																						
												<#-- ejemplo de un presupuesto pedido -->
										  		<div class="panel-heading">
											    	<h3 class="panel-title">
											    		Necesito un ${quote.order.workAreaDescription}
											    		<#if quote.order.workDateType='URGENT'>
											    			<span class="pull-right urgente">Es urgente</span>
											    		</#if>
											    	</h3>
											  	</div>
											  	<div class="panel-body">
											  		<div class="row">
											  			<#list quote.order.workAreaItemCodes as workAreaItemCode>
											  				<#list workAreaItems as workAreaItem>
																<#if workAreaItem.code=workAreaItemCode>
																	<#list workAreaQuestions as workAreaQuestion>
																		<#if workAreaQuestion.workAreaCode=workAreaItem.workAreaCode && workAreaQuestion.groupType=workAreaItem.groupType>
									                        				<div class="form-group col-md-6">
									                        					<label>${workAreaQuestion.description}</label> ${workAreaItem.description}
									                        				</div>
											    						</#if>
											    					</#list>
											    				</#if>								  					
											  				</#list>
								                   		</#list>						                        
								                   </div>
								                   <#if quote.order.squareMeters??>
								                       <div class="row">
									                        <div class="form-group col-md-12">
									                        	<label>¿Que tamaño tiene la superficie?</label> ${quote.order.squareMeters} m2
									                        </div>
								                       </div>
												   </#if>
								                   <#if quote.order.airConditionerPower??>
								                       <div class="row">
									                        <div class="form-group col-md-12">
									                        	<label>¿Cuantas frigorias tiene el aire?</label> ${quote.order.airConditionerPower} frigorias
									                        </div>
								                       </div>
												   </#if>									   					                       					                   
							                       <div class="row">
								                        <div class="form-group col-md-12">
								                        	<label>Trabajo a realizar en:</label> ${quote.order.stateDescription}, ${(quote.order.cityDescription)!""}
								                        </div>
							                       </div>				                       
							                       <div class="row">
								                        <div class="form-group col-md-12">
								                        	<label>Detalle del trabajo:</label> ${quote.order.workDescription}
								                        </div>
							                       </div>
							                       
							                       <div class="row">
						                       			<div class="col-md-12">
						                       				<div class="budget-form-border">
									                       		<div class="row budget-common-form">
											                        <div class="form-group col-md-4">
											                        	<label>Costo del trabajo</label> 
											                        	<div class="input-group">
																	      <span class="input-group-addon">${currency.symbol}</span>
																	      <@spring.formInput "quote.amount.amount", 'class="form-control"'/>
																	      <input type="hidden" name="amount.currency.code" value="${currency.code}">
																	    </div>
											                        </div>
											                        <div class="form-group col-md-4">
											                        	<label>Valido hasta</label>
										                        		<@spring.formInput "quote.validDateUntil", 'class="form-control"'/>
											                        </div>
										                       </div>
										                       <div class="row budget-common-form">
											                        <div class="form-group col-md-12">
											                        	<label>Comentarios sobre el trabajo</label> 
											                        	<@spring.formTextarea "quote.description", 'class="form-control"'/>
											                        </div>
											                   </div>
										                       <div class="row">
											                        <div class="form-group col-md-12">
											                        	<label style="font-weight: initial;">
											                        		<@spring.formCheckbox "quote.requireVisit",'class="need-visit"'/>
											                        		Se necesita una visita para presupuestar
											                        	</label>
											                        </div>
											                        <div class="form-group col-md-4 visit-form" style="display:none;">
											                        	<label>Costo de la visita</label> 
											                        	<div class="input-group">
																	      <span class="input-group-addon">$</span>
																	      <@spring.formInput "quote.visitAmount.amount", 'class="form-control"'/>
																	      <input type="hidden" name="visitAmount.currency.code" value="${currency.code}">
																	    </div>
											                        </div>
										                       </div>
										                       <div class="row">
											                        <div class="col-md-12">
											                        	<a href="javascript:document.budgetsForm${quote_index}.submit()" class="button btn btn-warning pull-right">Presupuestar</a>
											                        </div>
										                       </div>
									                      	</div>
								                      	</div>
										        	</div>						                    
												</div>
											</form>										  	
										</div>
									</div>
								<#elseif quote.statusType="REPLIED" || quote.statusType="DONE">
									<div class="row">
										<div class="panel panel-default quote-${quote.statusType}">
											<div class="panel-heading">
												<h3 class="panel-title">
											    	Necesito un ${quote.order.workAreaDescription}
											    	<#if quote.order.workDateType='URGENT'>
											    		<span class="pull-right urgente">Es urgente</span>
											    	</#if>
											    </h3>
											</div>
											<div class="panel-body">
												<div class="row">
										  			<#list quote.order.workAreaItemCodes as workAreaItemCode>
										  				<#list workAreaItems as workAreaItem>
															<#if workAreaItem.code=workAreaItemCode>
																<#list workAreaQuestions as workAreaQuestion>
																	<#if workAreaQuestion.workAreaCode=workAreaItem.workAreaCode && workAreaQuestion.groupType=workAreaItem.groupType>
								                        				<div class="form-group col-md-6">
								                        					<label>${workAreaQuestion.description}</label> ${workAreaItem.description}
								                        				</div>
										    						</#if>
										    					</#list>
										    				</#if>								  					
										  				</#list>
							                   		</#list>						                        
							                   </div>
							                   <#if quote.order.squareMeters??>
							                       <div class="row">
								                        <div class="form-group col-md-12">
								                        	<label>¿Que tamaño tiene la superficie?</label> ${quote.order.squareMeters} m2
								                        </div>
							                       </div>
											   </#if>
							                   <#if quote.order.airConditionerPower??>
							                       <div class="row">
								                        <div class="form-group col-md-12">
								                        	<label>¿Cuantas frigorias tiene el aire?</label> ${quote.order.airConditionerPower} frigorias
								                        </div>
							                       </div>
											   </#if>									   					                       					                   
						                       <div class="row">
							                        <div class="form-group col-md-12">
							                        	<label>Trabajo a realizar en:</label> ${quote.order.stateDescription}, ${(quote.order.cityDescription)!""}
							                        </div>
						                       </div>				                       
						                       <div class="row">
							                        <div class="form-group col-md-12">
							                        	<label>Detalle del trabajo:</label> ${quote.order.workDescription}
							                        </div>
						                       </div>
						                       <#if quote.amount??>
												   <div class="row">
								                   		<div class="form-group col-md-12">
								                        	<label>Costo del trabajo:</label> ${(quote.amount.currency.symbol)!""} ${(quote.amount.amount)!""}
								                        </div>
							                       </div>
							                   </#if>
							                   <#if quote.description?? && quote.description!="">
												   <div class="row">
								                   		<div class="form-group col-md-12">
								                        	<label>Comentarios sobre el trabajo:</label> ${quote.description}
								                        </div>
							                       </div>
							                   </#if>
						                       <#if quote.validDateUntil??>
												   <div class="row">
								                   		<div class="form-group col-md-12">
								                        	<label>Valido hasta:</label> ${(quote.validDateUntil?string("dd/MM/yy"))!""}
								                        </div>
							                       </div>
							                   </#if>
							                   <#if quote.requireVisit??>
												   <div class="row">
								                   		<div class="form-group col-md-12">
								                        	<label>Se necesita una visita para presupuestar:</label> ${quote.requireVisit?string('Si', 'No')}
								                        </div>
							                       </div>
							                   </#if>
							                   <#if quote.visitAmount??>
												   <div class="row">
								                   		<div class="form-group col-md-12">
								                        	<label>Costo de la visita:</label> ${(quote.visitAmount.currency.symbol)!""} ${(quote.visitAmount.amount)!""}
								                        </div>
							                       </div>
							                   </#if>				                       		                       					                       					                       
						            		</div>
						            	</div>
						        	</div>														
								</#if>
								<#-- fin de ejemplo de un presupuesto pedido -->
							</#list>
						</div>
					</#if>											
            	</div>            	
           </div>
        </div>
    </section>

	<#include "footer.ftl">

    <!-- jQuery Version 1.11.0 -->
    <script src="<@c.url value='/static/new/js/jquery-1.11.0.js'/>"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="<@c.url value='/static/new/js/bootstrap.min.js'/>"></script>

    <!-- Plugin JavaScript -->
    <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js"></script>
    <script src="<@c.url value='/static/new/js/classie.js'/>"></script>
    
    <script>
    	var tabToShow = "${tabToShow!""}";
    </script>
    <script src="<@c.url value='/static/new/js/views/dashboard.js'/>"></script>

</body>

</html>
