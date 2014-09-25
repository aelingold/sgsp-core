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
    
    <link href="<@c.url value='/static/new/css/login.css'/>" rel="stylesheet">
    <link href="<@c.url value='/static/new/css/social-buttons.css'/>" rel="stylesheet">

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
                <div class="col-lg-6" style="border-right: 1px solid #ddd;">     
			      <form action="<@c.url value='/login/authenticate'/>" method="POST" enctype="utf8" class="form-signin">
			      	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
					<#if RequestParameters.error?? && RequestParameters.error == 'bad_credentials'>
					<@spring.message "text.login.page.login.failed.error"/>
					</#if>			      	
			        <h2 class="form-signin-heading">Ingrese sus datos</h2>
			        <input type="email" name="username" class="form-control" placeholder="Email" required="" autofocus="">
			        <input type="password" name="password" class="form-control" placeholder="Contraseña" required="">
			        <button class="btn btn-lg btn-primary btn-block" type="submit">Ingresar</button>
			      </form>
               	</div>  
           	 	<div class="col-lg-6">     
           	 		<div class="social-column">
           	 			<form name="fb_signin" id="fb_signin" action="<@c.url value='/signin/facebook'/>" method="POST">
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        					<input type="hidden" name="scope" value="public_profile,email,offline_access,user_friends"/>           	 			
           	 				<a href="javascript:document.fb_signin.submit()">
				      			<div class="fb-button">
				      				<img src="<@c.url value='/static/new/img/social/facebook.png'/>">
				      				<span>Ingresar con facebook</span>
				      			</div>
				      		</a>
				      	</form>
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

    <!-- Custom Theme JavaScript -->
    <script src="<@c.url value='/static/new/js/views/registration.js'/>"></script>

</body>

</html>
