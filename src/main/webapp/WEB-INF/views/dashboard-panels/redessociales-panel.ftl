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
	      								<div class="fb-button" style="height: 44px;">
						      				<img src="<@c.url value='/static/new/img/social/facebook.png'/>">
						      				<span style="margin-top: 11px;font-size: 16px;">Desconectar de facebook</span>
						      			</div>
	      							</div>
	      						</a>
							</form>						  		
			  			<#else>
							<form name="fb_connect" action="<@c.url value='/connect/facebook'/>" method="POST">
								<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>							  			
								<a href="javascript:document.fb_connect.submit()">
									<div class="fb-button" style="height: 44px;">
					      				<img src="<@c.url value='/static/new/img/social/facebook.png'/>">
					      				<span style="margin-top: 11px;font-size: 16px;">Conectar con facebook</span>
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