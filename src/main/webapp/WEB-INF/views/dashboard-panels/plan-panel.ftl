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
	  				<form action="<@c.url value='/dashboard/plan' />" method="POST" enctype="utf8">
	  					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
						<#list ratePlans as plan>
							<#if plan.isEnabled>
								<div class="row">
									<div class="col-md-12" style="margin-bottom:15px;">
		            					<label><input type="radio" name="userRatePlan" value="${plan.code}" ${(ratePlan.code == plan.code)?string('checked', '')}> ${plan.description}</label>
		            					<div style="margin-left:15px;color:#666;">
		            						<#if plan.packageType == "FIXED">
		            							${plan.amount.currency.symbol}${plan.amount.amount} por mes
		            						</#if>
		            						<#if plan.packageType == "SERVICE">
		            							${plan.amount.currency.symbol}${plan.amount.amount} por servicio concretado
		            						</#if>
		            						<#if plan.packageType == "PERCENTAGE">
		            							${plan.percentageQuantity}% sobre el precio de cada servicio concretado
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