<div id="administracion-panel" class="col-md-12 dashboard-panel">
	<div class="row">
		<div class="panel panel-default">
  			<div class="panel-heading">
	    		<h3 class="panel-title">
	    			Administracion
	    		</h3>
	  		</div>
	  		<div class="panel-body">
	  			<div class="col-md-12">
	  				<h3 class="panel-title">
						Usuarios con pagos vencidos
					</h3>						  				
					<table class="table">  
						<thead>  
							<tr>  
					        	<th>Email</th>
					            <th>Monto</th>
					            <th>Fecha Vencimiento</th>
					            <th>Estado</th>
					            <th>Acciones</th>
					        </tr>
					    </thead>  
					    <tbody>
					    	<#list expiredPayments as expiredPayment>  
						    	<tr>  
						        	<td>${expiredPayment.username}</td>
						            <td>${expiredPayment.amount.currency.symbol} ${expiredPayment.amount.amount}</td>
						            <td>${expiredPayment.paymentDateAllowedBefore?string("dd-MM-yyyy")}</td>
						            <td>
						            	<#if expiredPayment.statusType="DONE">
						            		PAGADO
						            	<#elseif expiredPayment.statusType="PENDING">
						            		PENDIENTE
										<#else>
						            		VENCIDO						            		
						            	</#if>
						            </td>
						            <td>						            	
					            		<#if expiredPayment.userEnabled>
											<form name="userSuspendForm" id="userSuspendForm" action="<@c.url value='/dashboard/disable/user/${expiredPayment.username}'/>" method="POST" enctype="utf8">
					            				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>						            		
					            				<a href="javascript:document.userSuspendForm.submit();" class="btn btn-md btn-primary pull-right">Inhabilitar</a>
					            			</form>
					            		<#else>
					            			<form name="userSuspendForm" id="userSuspendForm" action="<@c.url value='/dashboard/enable/user/${expiredPayment.username}'/>" method="POST" enctype="utf8">
					            				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
					            				<a href="javascript:document.userSuspendForm.submit();" class="btn btn-md btn-primary pull-right">Habilitar</a>
					            			</form>
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