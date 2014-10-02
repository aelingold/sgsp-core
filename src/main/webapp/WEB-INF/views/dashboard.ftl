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
            		<#if successMessage??>
	            		<div class="alert alert-success alert-dismissible" role="alert">
					  		<button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
					  		<span class="glyphicon glyphicon-ok"></span> ${successMessage}
						</div>
					</#if>
					<#if alertMessage??>
						<div class="alert alert-warning alert-dismissible" role="alert">
					  		<button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
					  		<span class="fa fa-exclamation-triangle"></span> ${alertMessage}
						</div>
					</#if>	
					<#if errorMessage??>
						<div class="alert alert-warning alert-danger" role="alert">
					  		<button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
					  		<span class="glyphicon glyphicon-remove"></span> ${errorMessage}
						</div>
					</#if>
            	            		
            		<@security.authorize ifAllGranted="ROLE_ADMIN">	            		
	            		<#include "dashboard-panels/reportes-panel.ftl">  
	            		<#include "dashboard-panels/administracion-panel.ftl">	            		          		
					</@security.authorize>
					            		
            		<#if user.isProfessional>
            			<#include "dashboard-panels/pagos-panel.ftl">  
	            		<#include "dashboard-panels/configuracion-panel.ftl">
						<#include "dashboard-panels/preguntas-panel.ftl">		
						<#include "dashboard-panels/presupuestos-panel.ftl">				
            	    </#if>
            	    
            	    <#include "dashboard-panels/redessociales-panel.ftl">
            		<#include "dashboard-panels/calificaciones-panel.ftl">            	
            		<#include "dashboard-panels/pedidos-panel.ftl">					
					<#include "dashboard-panels/perfil-panel.ftl">                   												
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
