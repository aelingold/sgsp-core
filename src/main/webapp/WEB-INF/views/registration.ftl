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
                <div class="col-lg-12">
                    <h2 class="section-heading">Registración</h2>                    
                </div>
                <div class="col-lg-12">                	
                	<form action="/register" method="POST" enctype="utf8">
                		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                		<input type="hidden" name="signInProvider" value="${user.signInProvider}"/>
                		<input type="radio" name="userType" value="user" checked> Usuario
                		<input type="radio" name="userType" value="professional"> Profesional
                	
                		<h3 class="text-muted">Ingresá estos datos y empezá a resolver tus problemas.</h3>
                	
	                    <div class="row">
	                        <div class="form-group col-lg-4">
	                        	<label>Nombre</label>
	                        	<input type="text" name="firstName" class="form-control" value="${user.firstName}">
	                        </div>
	                    </div>
	                    <div class="row">
	                        <div class="form-group col-lg-4">
	                            <label>Apellido</label>
	                        	<input type="text" name="lastName" class="form-control" value="${user.lastName}">
	                        </div>
	                    </div>
	                    <div class="row">
	                        <div class="form-group col-lg-4">
	                            <label>Email</label>
	                        	<input type="text" name="email" class="form-control" value="(${user.email})!''">
	                        </div>
	                    </div>	                    
                        <div class="row">
                            <div class="form-group col-lg-4">
                                <label>Contraseña</label>
	                        	<input type="password" name="password" class="form-control">
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-lg-4">
                                <label>Repetir contraseña</label>
	                        	<input type="password" name="passwordVerification" class="form-control">
                            </div>
                        </div>
                        
                        <div class="row pro-field">
                            <div class="form-group col-lg-4">
                                <label>Teléfono</label>
	                        	<input type="text" name="telephone" class="form-control">
                            </div>
                        </div>
                        
                        <div class="row pro-field">
                        	<div class="col-lg-12">
                        		<label>Seleccione los rubros a los que se dedica</label>
                        	</div>
                            <div class="col-lg-12">                                
                                <#list workAreas as workArea>
                                	<input name="workAreaCodes[${workArea_index}]" value="${workArea.code}" type="checkbox"> ${workArea.description}
                                </#list>                        	
                            </div>
                        </div>
          					                    
	                    <button type="submit" class="btn btn-default">Registrarme</button>
	                </form>
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

    <!-- Custom Theme JavaScript -->
    <script src="<@c.url value='/static/new/js/views/registration.js'/>"></script>

</body>

</html>
