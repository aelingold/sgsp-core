<#assign c=JspTaglibs["http://java.sun.com/jsp/jstl/core"]/>
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
            <div class="row">
            	<div class="col-md-12">
            		<h1>Panel de control</h1>
            	</div>
            </div>
            <div class="row">
            	<div class="col-md-3">            	
            		<ul id="dash-menu" class="nav nav-pills nav-stacked">
				      <li id="perfil-option"><a href="<@c.url value='/dashboard/profile'/>">Mi perfil</a></li>
				      <li id="pedidos-option"><a href="<@c.url value='/dashboard/requests'/>">Presupuestos pedidos</a></li>
				      <li id="presupuestos-option"><a href="<@c.url value='/dashboard/budgets'/>">Responder pedidos</a></li>
				      <li id="calificaciones-option"><a href="<@c.url value='/dashboard/ratings'/>">Calificaciones</a></li>
				      <li id="configuracion-option"><a href="<@c.url value='/dashboard/config'/>">Configuración</a></li>
				    </ul>
            	</div>
            	
            	<div class="col-md-9">
            		
            		<div id="configuracion-panel" class="col-md-12 dashboard-panel">
						<form name="configForm" action="/dashboard/config" method="POST" enctype="utf8">
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
															
																<@spring.formCheckboxes "config.cityCodes", configMap[state.code],''/>
																																										
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
            	            	
            		<div id="calificaciones-panel" class="col-md-12 dashboard-panel">
	            		<div class="btn-group btn-group-justified">
	            		  <div class="btn-group">
						    <button type="button" class="btn btn-default active">Pendientes</button>
						  </div>
						  <div class="btn-group">
						    <button type="button" class="btn btn-default">Hechas</button>
						  </div>
						  <div class="btn-group">
						    <button type="button" class="btn btn-default">Recibidas</button>
						  </div>
						</div>
					</div>
            	
            		<div id="pedidos-panel" class="col-md-12 dashboard-panel">
            			<div class="row">
		            		<div class="btn-group btn-group-justified">
							  <div class="btn-group">
							    <button type="button" class="btn btn-default active">En curso</button>
							  </div>
							  <div class="btn-group">
							    <button type="button" class="btn btn-default">Finalizados</button>
							  </div>
							</div>
						</div>
												
						<#list orders as order>
							<#-- ejemplo de un presupuesto pedido -->
							<div class="row">
								<div class="panel panel-default">
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
							</div>
							<#-- fin de ejemplo de un presupuesto pedido -->						
						</#list>						
						
					</div>
					
					<div id="perfil-panel" class="col-md-12 dashboard-panel">
						<form action="/dashboard/profile" method="POST" enctype="utf8">
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
                   	
                   	<div id="presupuestos-panel" class="col-md-12 dashboard-panel">
            			<div class="row">
		            		<div class="btn-group btn-group-justified">
							  <div class="btn-group">
							    <button type="button" class="btn btn-default active">Presupuestos pendientes</button>
							  </div>
							  <div class="btn-group">
							    <button type="button" class="btn btn-default">Presupuestos realizados</button>
							  </div>
							</div>
						</div>
																		
						<#list pendingQuotes as pendingQuote>
							<form name="budgetsForm${pendingQuote_index}" action="/dashboard/budgets" method="POST" enctype="utf8">
								<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
								<@spring.bind "quote" />							
								<input type="hidden" name="id" value="${pendingQuote.id}">								
								<#-- ejemplo de un presupuesto pedido -->
								<div class="row">
									<div class="panel panel-default">
								  		<div class="panel-heading">
									    	<h3 class="panel-title">
									    		Necesito un ${pendingQuote.order.workAreaDescription}
									    		<#if pendingQuote.order.workDateType='URGENT'>
									    			<span class="pull-right urgente">Es urgente</span>
									    		</#if>
									    	</h3>
									  	</div>
									  	<div class="panel-body">
									  		<div class="row">
									  			<#list pendingQuote.order.workAreaItemCodes as workAreaItemCode>
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
						                   <#if pendingQuote.order.squareMeters??>
						                       <div class="row">
							                        <div class="form-group col-md-12">
							                        	<label>¿Que tamaño tiene la superficie?</label> ${pendingQuote.order.squareMeters} m2
							                        </div>
						                       </div>
										   </#if>
						                   <#if pendingQuote.order.airConditionerPower??>
						                       <div class="row">
							                        <div class="form-group col-md-12">
							                        	<label>¿Cuantas frigorias tiene el aire?</label> ${pendingQuote.order.airConditionerPower} frigorias
							                        </div>
						                       </div>
										   </#if>									   					                       					                   
					                       <div class="row">
						                        <div class="form-group col-md-12">
						                        	<label>Trabajo a realizar en:</label> ${pendingQuote.order.stateDescription}, ${(pendingQuote.order.cityDescription)!""}
						                        </div>
					                       </div>				                       
					                       <div class="row">
						                        <div class="form-group col-md-12">
						                        	<label>Detalle del trabajo:</label> ${pendingQuote.order.workDescription}
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
															    </div>
									                        </div>
									                        <div class="form-group col-md-8">
									                        	<label>Comentarios sobre el trabajo</label> 
									                        	<@spring.formTextarea "quote.description", 'class="form-control"'/>
									                        </div>
								                       </div>
								                       <div class="row budget-common-form">
									                        <div class="form-group col-md-12">
									                        	<label>Valido hasta</label>
									                        	<div class="input-group">
									                        		<@spring.formInput "quote.validDateUntil", 'class="form-control"'/>
									                        	</div>
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
															    </div>
									                        </div>
								                       </div>
								                       <div class="row">
									                        <div class="col-md-12">
									                        	<a href="javascript:document.budgetsForm${pendingQuote_index}.submit()" class="button btn btn-warning pull-right">Presupuestar</a>
									                        </div>
								                       </div>
							                      	</div>
						                      	</div>
						                    </div>						                    
									  	</div>
									</div>
								</div>
								<#-- fin de ejemplo de un presupuesto pedido -->												
							</form>	
						</#list>
					</div>										
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

    <!-- Contact Form JavaScript -->
    <script src="<@c.url value='/static/new/js/jqBootstrapValidation.js'/>"></script>
    
    <script>
    	var tabToShow = "${tabToShow!""}";
    </script>
    <script src="<@c.url value='/static/new/js/views/dashboard.js'/>"></script>

</body>

</html>
