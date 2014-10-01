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
    <nav class="navbar navbar-default navbar-fixed-top">
		<#include "header.ftl">
    </nav>

    <!-- Header -->
    <header>
        <div class="container">
            <div class="intro-text">
                <div class="intro-lead-in">Resuelva los problemas de su hogar</div>
                <div class="intro-heading">Qué servicio necesita?</div>
                <div class="col-md-12">
                	<#list workAreas as workArea>
                		<div class="col-md-4 home-service">
	                		<span class="home-service-option" data-code="${workArea.code}">
	                			${workArea.description}
	                		</span>
	                	</div>
                    </#list>
                </div>
            </div>            
        </div>
        <div class="form-home-section">
        	<#include "budget-forms/gasista.ftl">
        	<#include "budget-forms/pintor.ftl">
        	<#include "budget-forms/plomero.ftl">
        	<#include "budget-forms/electricista.ftl">
        	<#include "budget-forms/cerrajero.ftl">
        	<#include "budget-forms/albanil.ftl">
        	<#include "budget-forms/serviceaire.ftl">
        	<#include "budget-forms/serviceblanca.ftl">
        	<#include "budget-forms/servicereparacion.ftl">
			<#-- AGREGAR ACÁ EL RESTO DE LOS FORMULARIOS -->
        </div>
    </header>

    <!-- Services Section -->
    <section id="howItWorks">
        <div class="container">
            <div class="row">
                <div class="col-md-12 text-center">
                    <h2 class="section-heading">Cómo funciona?</h2>                    					
                </div>
                <div class="row" style="margin-top: 100px;">
	                <div class="col-md-4 text-center">
	                	<img src="<@c.url value='/static/new/img/hworks/anuncio.png'/>" style="height: 100px;margin-left: 32px;">
	                	<div class="explain-text">Pida un servicio</div>
					</div>
					<div class="col-md-4 text-center">
						<img src="<@c.url value='/static/new/img/hworks/profesionales.png'/>" style="height: 100px;margin-left: 6px;">
						<div class="explain-text">Reciba presupuestos</div>
					</div>
					<div class="col-md-4 text-center">
						<img src="<@c.url value='/static/new/img/hworks/trato.png'/>" style="height: 100px;">
						<div class="explain-text">Elija el mejor</div>
					</div>
				</div>
            </div>
       </div>
    </section>
    
    <!-- Services Section -->
    <section id="usersBenefits">
        <div class="container">
        	<div class="row">
           		 <div class="col-md-12 text-center">
                    <h2 class="section-heading">Beneficios</h2>                    					
                </div>
           	</div>
            <div class="row">                
                <div class="col-md-6 text-center">
                	<img src="<@c.url value='/static/new/img/characters/user.png'/>">	                	
                	<div class="benefits-section">
                		<ul class="benefits-list">
                			<li><span class="glyphicon glyphicon-ok"></span> Use la plataforma totalmente GRATIS!</li>
                			<li><span class="glyphicon glyphicon-ok"></span> Encuentre profesionales de confianza para los trabajos del hogar</li>
                			<li><span class="glyphicon glyphicon-ok"></span> Pida presupuestos online</li>
                			<li><span class="glyphicon glyphicon-ok"></span> Elija mejor comparando precios y reputación de los profesionales</li>
                		</ul>
                		<div style="text-align: center;">
                			<a href="<@c.url value='/register'/>" class="button btn btn-danger btn-lg">Registrarme</a>
                		</div>
                	</div>
				</div>
				<div class="col-md-6 text-center">
                	<img src="<@c.url value='/static/new/img/characters/prof.png'/>">
                	<div class="benefits-section">
                		<ul class="benefits-list">
                			<li><span class="glyphicon glyphicon-ok"></span> Use la plataforma totalmente GRATIS!</li>
                			<li><span class="glyphicon glyphicon-ok"></span> Consiga más clientes</li>
                			<li><span class="glyphicon glyphicon-ok"></span> Reciba notificaciones de nuevos trabajos y arme presupuestos fácilmente</li>
                			<li><span class="glyphicon glyphicon-ok"></span> Diferenciese de los demás gracias a las calificaciones por sus trabajos y sus tarifas</li>                			
                		</ul>
                		<div style="text-align: center;">
                			<a href="<@c.url value='/register'/>" class="button btn btn-danger btn-lg">Ofrecer mi servicio</a>
                		</div>
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
    <script src="<@c.url value='/static/new/js/cbpAnimatedHeader.js'/>"></script>

	<script>
		basePath="<@c.url value='/' />";
	</script>

    <!-- Custom Theme JavaScript -->
    <script src="<@c.url value='/static/new/js/agency.js'/>"></script>    
    <script src="<@c.url value='/static/new/js/views/index.js'/>"></script>

</body>

</html>
