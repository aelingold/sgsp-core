<div id="contacto-panel" class="col-md-12 dashboard-panel">
	<div class="row">
		<div class="panel panel-default">
  			<div class="panel-heading">
	    		<h3 class="panel-title">
	    			Contacto
	    		</h3>
	  		</div>
	  		<div class="panel-body">
	  			<div class="col-md-12">
	  				<h3 class="panel-title">
						Mensajes recibidos
					</h3>						  				
					<table class="table">  
						<thead>  
							<tr>  
					        	<th>Nombre</th>
					            <th>Email</th>
					            <th>Mensaje</th>
					        </tr>
					    </thead>  
					    <tbody>
					    	<#list contactMessages as contactMessage>  
						    	<tr>  
						        	<td>${contactMessage.name}</td>
						            <td>${contactMessage.email}</td>
						            <td>${contactMessage.message}</td>
						        </tr>
							</#list>
					    </tbody>
					</table>																																														  			
	  			</div>
	  		</div>
	  	</div>
	</div>   		
</div>