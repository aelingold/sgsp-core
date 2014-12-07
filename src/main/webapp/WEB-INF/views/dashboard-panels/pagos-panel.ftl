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
					        	<th>Concepto</th>
					            <th>Monto</th>
								<th>Fecha Vencimiento</th>					            
					            <th>Estado</th>
					            <th>Fecha Pago</th>
					        </tr>
					    </thead>  
					    <tbody>
					    	<#list payments as payment>  
						    	<tr>  						        	
						        	<td>${payment.ratePlanDescription}</td>
						            <td>${payment.amount.currency.symbol} ${payment.amount.amount}</td>
						            <td>${payment.paymentDateAllowedBefore?string("dd-MM-yyyy")}</td>
						            <td>
						            	<#if payment.statusType="DONE">
						            		PAGADO
						            	<#elseif payment.statusType="PENDING">
						            		PENDIENTE
										<#else>
						            		VENCIDO						            		
						            	</#if>
						            </td>
						            <td>${(payment.paymentEffectiveDate?string("dd-MM-yyyy"))!""}</td>
						        </tr>
							</#list>
					    </tbody>
					</table>					  			
	  			</div>
	  		</div>
	  	</div>
	</div>   		
</div>        