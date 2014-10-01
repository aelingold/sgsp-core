<div id="configuracion-panel" class="col-md-12 dashboard-panel">
	<form name="configForm" action="<@c.url value='/dashboard/config' />" method="POST" enctype="utf8">
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
								<#list cities as city>
									<#if city.stateCode=state.code>									
										<div class="col-md-3">
											<input ${config.cityCodes?seq_contains(city.code)?string("checked", "")} name="cityCodes" type="checkbox" value="${city.code}"> ${city.description}
										</div>
									</#if>
								</#list>
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