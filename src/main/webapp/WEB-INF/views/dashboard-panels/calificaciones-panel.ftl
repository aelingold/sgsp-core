<div id="calificaciones-panel" class="col-md-12 dashboard-panel">
	<div class="row">
		<div class="btn-group btn-group-justified">
		  <div class="btn-group">
		    <button type="button" class="btn btn-default" data-show-class="user-work-rate-PENDING">Pendientes (${userWorkRatesPendingQty})</button>
		  </div>
		  <div class="btn-group">
		    <button type="button" class="btn btn-default" data-show-class="user-work-rate-DONE">Hechas (${userWorkRatesDoneQty})</button>
		  </div>
		  <div class="btn-group">
		    <button type="button" class="btn btn-default" data-show-class="user-work-rate-RECEIVED">Recibidas (${userWorkRatesReceivedQty})</button>
		  </div>
		</div>
	</div>
	<div class="row">
		<div class="panel-group" id="accordion">
		
			<#if userWorkRatesReceived?size == 0>
				<div class="rating-section user-work-rate-PENDING">
					<div class="col-md-12" style="margin-top:15px;">
						No tienes calificaciones pendientes.
					</div>
				</div>
			</#if>
			<#if userWorkRatesReceived?size == 0>
				<div class="rating-section user-work-rate-DONE">
					<div class="col-md-12" style="margin-top:15px;">
						No tienes calificaciones hechas.
					</div>
				</div>
			</#if>
			<#if userWorkRatesReceived?size == 0>
				<div class="rating-section user-work-rate-RECEIVED">
					<div class="col-md-12" style="margin-top:15px;">
						No tienes calificaciones recibidas.
					</div>
				</div>
			</#if>						
			<#list userWorkRatesReceived as userWorkRateReceived>
				<div class="rating-section user-work-rate-RECEIVED">
					<div class="col-md-12" style="margin-top:15px;">
						<#if userWorkRateReceived.ratingType="POSITIVE">
    						<span class="glyphicon glyphicon-circle-arrow-up" style="font-size: 18px;color: #1616A8;"></span>
    					<#else>
    						<#if userWorkRateReceived.ratingType="NEUTRAL">
	    						<span class="glyphicon glyphicon-circle-arrow-right" style="font-size: 18px;color: #E0E909;"></span>
	    					<#else>
    							<span class="glyphicon glyphicon-circle-arrow-down" style="font-size: 18px;color: #E70000;"></span>		
    						</#if>	
    					</#if>			    		
			    		<span class="rating-user">${userWorkRateReceived.userFirstName} ${userWorkRateReceived.userLastName}</span> 
						<span class="rating-date">(${userWorkRateReceived.updatedAt?string("dd/MM/yy hh:mm a")})</span>
			    	</div>
			    	<div class="col-md-12">
			    		<span class="rating-comment-line-conection"></span>${userWorkRateReceived.comment}
			    	</div>
		    	</div>			
			</#list>
			
			<#list userWorkRates as userWorkRate>
				<#if userWorkRate.statusType='DONE'>
				
					<div class="rating-section user-work-rate-${userWorkRate.statusType}">
						<div class="col-md-12" style="margin-top:15px;">
							<#if userWorkRate.ratingType="POSITIVE">
	    						<span class="glyphicon glyphicon-circle-arrow-up" style="font-size: 18px;color: #1616A8;"></span>
	    					<#else>
	    						<#if userWorkRate.ratingType="NEUTRAL">
		    						<span class="glyphicon glyphicon-circle-arrow-right" style="font-size: 18px;color: #E0E909;"></span>
		    					<#else>
	    							<span class="glyphicon glyphicon-circle-arrow-down" style="font-size: 18px;color: #E70000;"></span>		
	    						</#if>	
	    					</#if>			    		
				    		<span class="rating-user">${userWorkRate.quoteUserFirstName} ${userWorkRate.quoteUserLastName}</span> 
							<span class="rating-date">(${userWorkRate.updatedAt?string("dd/MM/yy hh:mm a")})</span>
				    	</div>
				    	<div class="col-md-12">
				    		<span class="rating-comment-line-conection"></span>${userWorkRate.comment}
				    	</div>
			    	</div>
			    	
				<#else>
					<form name="userWorkRatesForm${userWorkRate_index}" action="<@c.url value='/dashboard/ratings' />" method="POST" enctype="utf8">
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
						<@spring.bind "userWorkRate" />
						<input type="hidden" name="id" value="${userWorkRate.id}">
						<input type="hidden" name="quoteId" value="${userWorkRate.quoteId}">
					  	<div class="rating-section panel panel-default user-work-rate-${userWorkRate.statusType}" id="panel${userWorkRate_index}">
					    	<div class="panel-heading" data-toggle="collapse" data-target="#collapse${userWorkRate_index}" style="cursor:pointer;">
						      	<h4 class="panel-title">
						        	<a  href="#collapse${userWorkRate_index}">
						          		${userWorkRate.quoteUserFirstName} ${userWorkRate.quoteUserLastName} (${userWorkRate.quoteUserWorkAreaDescription})
						          		<span class="fa"></span>							          		
						        	</a>							        	
						      	</h4>
					    	</div>
						    <div id="collapse${userWorkRate_index}" class="panel-collapse collapse in">
						      	<div class="panel-body">
						      		<div class="col-md-12">
				        				<div class="row form-group">
				        					<div class="col-md-12">
					        					<label>¿El trabajo fue realizado?</label>
					        				</div>
					        				<div class="col-md-2">
					        					<input type="radio" name="workCompleted" value="YES"> Si
					        				</div>
						        			<div class="col-md-2">
						        				<input type="radio" name="workCompleted" value="NO"> No
						        			</div>	
					        			</div>
					        			<div class="row form-group">
				        					<div class="col-md-12">
					        					<label>¿Cómo calificarias a Jose Marmol?</label>
					        				</div>
					        				<div class="col-md-2">
					        					<input type="radio" name="ratingType" value="POSITIVE"> Positivo
					        				</div>
						        			<div class="col-md-2">
						        				<input type="radio" name="ratingType" value="NEUTRAL"> Neutral
						        			</div>
						        			<div class="col-md-2">
						        				<input type="radio" name="ratingType" value="NEGATIVE"> Negativo
						        			</div>		
					        			</div>
					        			<div class="row form-group">
				        					<div class="col-md-12">
					        					<label>¿Tienes algún comentario?</label>
					        					<textarea name="comment" class="form-control"></textarea>
					        				</div>		
					        			</div>
					        			<div class="row form-group">
				        					<div class="col-md-12">
					        					<a href="javascript:document.userWorkRatesForm${userWorkRate_index}.submit()" class="button btn btn-warning pull-right">Calificar</a>
					        				</div>		
					        			</div>
				        			</div>						        	
						      	</div>
					    	</div>
					  	</div>
					</form>									
			  	</#if>							
			</#list>
		</div>
	</div>
</div>