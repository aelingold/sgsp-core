<#assign security=JspTaglibs["http://www.springframework.org/security/tags"] />
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
        <a class="navbar-brand page-scroll" href="<@c.url value='/'/>">
			<img src="<@c.url value='/static/new/img/logos/singuia-128x128blanco.png'/>" style="height: 100%;">
		</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
        <ul class="nav navbar-nav navbar-right">
            <li class="hidden">
                <a href="#page-top"></a>
            </li>
            <li>
                <a class="page-scroll" href="#" data-toggle="modal" data-target="#contactModal">Contacto</a>
            </li>            
            <@security.authorize access="isAuthenticated()">
            	<li>
	                <a class="page-scroll" href="<@c.url value='/dashboard'/>">Panel de control</a>
	            </li>
	            <li>
            		<span class="login-name">Hola <@security.authentication property="principal.firstName"/>! <form action="<@c.url value='/logout'/>" method="POST" name="logout"><input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>(<a href="javascript:logout.submit()" style="text-transform: none;">Salir</a>)</form></span>
            	</li>
            </@security.authorize>
			<@security.authorize access="isAnonymous()">
				<li>	
               		<div>
            			<a href="register" class="button btn btn-danger">Registrarme</a>
                    	<a href="login" class="button btn btn-info">Ingresar</a>
                	</div>
                </li>
			</@security.authorize>                 
            
        </ul>
    </div>
    <!-- /.navbar-collapse -->
</div>
<!-- /.container-fluid -->