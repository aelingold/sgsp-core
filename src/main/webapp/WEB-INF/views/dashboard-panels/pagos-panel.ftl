<div id="pagos-panel" class="col-md-12 dashboard-panel">
	<div class="row">
		<div class="panel panel-default">
  			<div class="panel-heading">
	    		<h3 class="panel-title">
	    			Pagos
	    		</h3>
	  		</div>
	  		<div class="panel-body">
	  			<div class="col-md-12">						  				
					<table class="table">  
						<thead>  
							<tr>  
					        	<th>Fecha Vencimiento</th>
					        	<th>Concepto</th>
					            <th>Monto</th>
					            <th>Estado</th>
					        </tr>
					    </thead>  
					    <tbody>
					    	<#list payments as payment>  
						    	<tr>  
						        	<td>${payment.paymentDateAllowedBefore?string("dd-MM-yyyy")}</td>
						        	<td>${ratePlan.description}</td>
						            <td>${payment.amount.currency.symbol} ${payment.amount.amount}</td>
						            <td>
						            	<#if payment.statusType="DONE">
						            		PAGADO
						            	<#elseif payment.statusType="PENDING">
						            		PENDIENTE
										<#else>
						            		VENCIDO						            		
						            	</#if>
						            </td>
						        </tr>
							</#list>
					    </tbody>
					</table>					  			
	  			</div>
	  		</div>
	  	</div>
	</div>   		
</div>        