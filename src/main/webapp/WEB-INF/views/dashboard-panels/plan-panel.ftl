<div id="plan-panel" class="col-md-12 dashboard-panel">
	<div class="row">
		<div class="panel panel-default">
  			<div class="panel-heading">
	    		<h3 class="panel-title">
	    			Planes de pago
	    		</h3>
	  		</div>
	  		<div class="panel-body">
	  			<div class="col-md-12">						  				
	  				<form action="<@c.url value='/dashboard/rate-plan' />" method="POST" enctype="utf8">
						<#list ratePlans as plan>
							<#if plan.isEnabled>
								<div class="row">
									<div class="col-md-12" style="margin-bottom:15px;">
									
										<#-- El checked="checked" hay que ponerlo si es la que tiene seleccionada para que venga preseleccionada -->
									
		            					<label><input type="radio" name="ratePlan" value="${plan.code}" checked="checked"> ${plan.description}</label>
		            					<div style="margin-left:15px;color:#666;">
		            						<#if plan.packageType == "FIXED">
		            							${plan.amount.currency.symbol}${plan.amount.amount} / ${plan.periodType}
		            						</#if>
		            						<#if plan.packageType == "SERVICE">
		            							${plan.amount.currency.symbol}${plan.amount.amount} por servicio concretado
		            						</#if>
		            						<#if plan.packageType == "PERCENTAGE">
		            							%${plan.percentageQuantity} del precio de cada servicio
		            						</#if>
		            					</div>
		            				</div>
		            			</div>
		            		</#if>
	            		</#list>
	            		<button type="submit" class="btn btn-warning pull-right">Guardar</button>
	            	</form>					  			
	  			</div>
	  		</div>
	  	</div>
	</div>   		
</div>        