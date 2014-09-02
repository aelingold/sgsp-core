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
                <div class="intro-lead-in">Resolvé los problemas de tu hogar</div>
                <div class="intro-heading">Qué servicio necesitas?</div>
                <div class="col-lg-12">
                	<#list workAreas as workArea>
                		<div class="col-lg-4 home-service">
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
			<#-- AGREGAR ACÁ EL RESTO DE LOS FORMULARIOS -->
        </div>
    </header>

    <!-- Services Section -->
    <section id="howItWorks">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <h2 class="section-heading">Cómo funciona?</h2>
                    <h3 class="section-subheading text-muted">
                    	Explicación de cómo funciona
					</h3>
                </div>
            </div>
       </div>
    </section>

    <section id="contact">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <h2 class="section-heading">Contacto</h2>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <form name="sentMessage" id="contactForm" novalidate>
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <input type="text" class="form-control" placeholder="Nombre *" id="name" required data-validation-required-message="Please enter your name.">
                                    <p class="help-block text-danger"></p>
                                </div>
                                <div class="form-group">
                                    <input type="email" class="form-control" placeholder="Email *" id="email" required data-validation-required-message="Please enter your email address.">
                                    <p class="help-block text-danger"></p>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <textarea class="form-control" placeholder="Mensaje *" id="message" required data-validation-required-message="Please enter a message."></textarea>
                                    <p class="help-block text-danger"></p>
                                </div>
                            </div>
                            <div class="clearfix"></div>
                            <div class="col-lg-12 text-center">
                                <div id="success"></div>
                                <button type="submit" class="btn btn-xl">Enviar</button>
                            </div>
                        </div>
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
    <script src="<@c.url value='/static/new/js/cbpAnimatedHeader.js'/>"></script>

    <!-- Contact Form JavaScript -->
    <script src="<@c.url value='/static/new/js/jqBootstrapValidation.js'/>"></script>
    <script src="<@c.url value='/static/new/js/contact_me.js'/>"></script>

    <!-- Custom Theme JavaScript -->
    <script src="<@c.url value='/static/new/js/agency.js'/>"></script>    
    <script src="<@c.url value='/static/new/js/views/index.js'/>"></script>

</body>

</html>
