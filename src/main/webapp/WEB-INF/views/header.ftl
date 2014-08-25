<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div class="container">
    <div class="header-shadow"></div>
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header page-scroll">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand page-scroll" href="#page-top">Sin Guía</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
        <ul class="nav navbar-nav navbar-right">
            <li class="hidden">
                <a href="#page-top"></a>
            </li>
            <li>
                <a class="page-scroll" href="#services">Servicios</a>
            </li>
            <li>
                <a class="page-scroll" href="#contact">Contacto</a>
            </li>
            <li> 
               <#--
            	<#if user?? && user.firstName??>
               		<span class="login-name">Hola ${user.firstName}! (<a href="" style="text-transform: none;">Salir</a>)</span>
               	<#else>
               	</#if>
               --> 
               		<div>
            			<a href="register" class="button btn btn-danger">Registrarme</a>
                    	<a href="login" class="button btn btn-info">Ingresar</a>
                	</div>
          	 	     
          	 	
          	 	<sec:authentication property="principal.firstName"/>          
            </li>
        </ul>
    </div>
    <!-- /.navbar-collapse -->
</div>
<!-- /.container-fluid -->