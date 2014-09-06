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
            		<ul class="nav nav-pills nav-stacked">
				      <li class="active"><a href="javascript:showPanel('#perfil-panel')">Mi perfil</a></li>
				      <li><a href="javascript:showPanel('#pedidos-panel')">Presupuestos pedidos</a></li>
				      <li><a href="javascript:showPanel('#calificaciones-panel')">Calificaciones</a></li>
				    </ul>
            	</div>
            	
            	<div class="col-md-9">
            	
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
						
						<#-- ejemplo de un presupuesto pedido -->
						<div class="row">	
							<div class="panel panel-default">
						  		<div class="panel-heading">
							    	<h3 class="panel-title">
							    		Necesito un gasista
							    		<span class="pull-right urgente">Es urgente</span>							    	
							    	</h3>
							  	</div>
							  	<div class="panel-body">
							  		<div class="row">
				                        <div class="form-group col-md-6">
				                        	<label>Tipo de inmueble:</label> Casa
				                        </div>
				                        <div class="form-group col-md-6">
				                        	<label>Tarea:</label> Revisar una perdida de gas
				                        </div>
			                       </div>
			                       <div class="row">
				                        <div class="form-group col-md-6">
				                        	<label>Artefactos relacionados:</label> Calefon, Termotanque
				                        </div>
				                        <div class="form-group col-md-6">
				                        	<label>Trabajo a realizar en:</label> Ciudad de Buenos Aires, Palermo
				                        </div>
			                       </div>
			                       <div class="row">
				                        <div class="form-group col-md-12">
				                        	<label>Detalle del trabajo:</label> Hay que venir y revisar todos los artefactos
				                        </div>
			                       </div>
							  	</div>
							</div>
						</div>
						<#-- fin de ejemplo de un presupuesto pedido -->
						
					</div>
					
					<div id="perfil-panel" class="col-md-12 dashboard-panel" style="display:block;">
						<div class="row">
	                        <div class="form-group col-md-6">
	                        	<label>Nombre</label>
	                        	<input type="text" class="form-control" value="Kevin">
	                        </div>
	                        <div class="form-group col-md-6">
	                            <label>Apellido</label>
	                            <input type="text" class="form-control" value="Furman">
	                        </div>
	                    </div>
	                    <div class="row">
	                        <div class="form-group col-md-6">
	                            <label>Email</label>	                        	
	                        	<input type="text" class="form-control" value="asdasd@hotmail.com" disabled>
	                        </div>
	                        <div class="form-group col-md-6">
	                            <label>Contraseña actual</label>
	                            <input type="password" class="form-control" value="xxxxxx" disabled>
	                        </div>
	                    </div>	                    
	                    <div class="row">
	                        <div class="form-group col-md-6">
	                            <label>Contraseña nueva</label>
	                            <input type="password" class="form-control">
	                        </div>
	                        <div class="form-group col-md-6">
	                            <label>Repetir contraseña nueva</label>
	                            <input type="password" class="form-control">
	                        </div>
	                    </div>
	                    <button type="submit" class="btn btn-default pull-right">Guardar</button>
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
    
    <script src="<@c.url value='/static/new/js/views/dashboard.js'/>"></script>

</body>

</html>
