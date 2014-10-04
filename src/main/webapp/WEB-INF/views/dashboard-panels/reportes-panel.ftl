<div id="reportes-panel" class="col-md-12 dashboard-panel">
	<div class="row">
		<div class="panel panel-default">
  			<div class="panel-heading">
	    		<h3 class="panel-title">
	    			Reportes
	    		</h3>
	  		</div>
	  		<div class="panel-body">
	  			<div class="col-md-12">
	  				<h3 class="panel-title">
						Cantidad de usuarios registrados por mes
					</h3>						  				
					<table class="table">  
						<thead>  
							<tr>  
					        	<th>Año-mes</th>
					            <th>Usuarios</th>
					            <th>Oferentes</th>
					        </tr>
					    </thead>  
					    <tbody>
					    	<#list reportUsers as reportUser>  
						    	<tr>  
						        	<td>${reportUser.yearMonth}</td>
						            <td>${reportUser.userCount}</td>
						            <td>${reportUser.isProfessionalCount}</td>
						        </tr>
							</#list>
					    </tbody>
					</table>										
	  				<h3 class="panel-title">
						Cantidad de servicios concretados por dia
					</h3>						  				
					<table class="table">  
						<thead>  
							<tr>  
					        	<th>Fecha</th>
					            <th>Rubro</th>
					            <th>Cantidad</th>
					        </tr>
					    </thead>  
					    <tbody>
					    	<#list quoteServicesDone as quoteServiceDone>  
						    	<tr>  
						        	<td>${quoteServiceDone.date}</td>
						            <td>${quoteServiceDone.workAreaDescription}</td>
						            <td>${quoteServiceDone.count}</td>
						        </tr>
							</#list>
					    </tbody>
					</table>										
	  				<h3 class="panel-title">
						Cantidad de oferentes postulados por rubro por dia
					</h3>						  				
					<table class="table">  
						<thead>  
							<tr>  
					        	<th>Fecha</th>
					            <th>Rubro</th>
					            <th>Cantidad</th>
					        </tr>
					    </thead>  
					    <tbody>
					    	<#list quoteServicesReplied as quoteServicesReply>  
						    	<tr>  
						        	<td>${quoteServicesReply.date}</td>
						            <td>${quoteServicesReply.workAreaDescription}</td>
						            <td>${quoteServicesReply.count}</td>
						        </tr>
							</#list>
					    </tbody>
					</table>																										  			
	  			</div>
	  		</div>
	  	</div>
	</div>   		
</div>