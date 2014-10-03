<div id="calificaciones-panel" class="col-md-12 dashboard-panel">
	<div class="row">
		<div class="btn-group btn-group-justified">
		  <div class="btn-group">
		    <button type="button" class="btn btn-default" data-show-class="user-work-rate-PENDING">Pendientes</button>
		  </div>
		  <div class="btn-group">
		    <button type="button" class="btn btn-default" data-show-class="user-work-rate-DONE">Hechas</button>
		  </div>
		  <div class="btn-group">
		    <button type="button" class="btn btn-default">Recibidas</button>
		  </div>
		</div>
	</div>
	<div class="row">
		<div class="panel-group" id="accordion">
			<#list userWorkRates as userWorkRate>
				<#if userWorkRate.statusType='DONE'>
				  	<div class="panel panel-default user-work-rate-${userWorkRate.statusType}" id="panel${userWorkRate_index}">
				    	<div class="panel-heading" data-toggle="collapse" data-target="#collapse${userWorkRate_index}" style="cursor:pointer;">
					      	<h4 class="panel-title">
					        	<a  href="#collapse${userWorkRate_index}">
					          		${userWorkRate.userFirstName} ${userWorkRate.userLastName} - ${userWorkRate.updatedAt?string("dd/MM/yy")}
					          		<span class="fa"></span>							          		
					        	</a>							        	
					      	</h4>
				    	</div>
					    <div id="collapse${userWorkRate_index}" class="panel-collapse collapse in">
					      	<div class="panel-body">
					      		<div class="col-md-12">
			        				<div class="row form-group">
			        					<div class="col-md-12">
				        					<label>Trabajo realizado: </label> ${userWorkRate.workCompleted?string('Si', 'No')}
				        				</div>
				        			</div>
				        			<div class="row form-group">
			        					<div class="col-md-12">
				        					<label>Calificacion: </label> 
				        					<#if userWorkRate.ratingType="POSITIVE">
				        						POSITIVA	
				        					<#elseif userWorkRate.ratingType="NEUTRAL">
				        						NEUTRAL
				        					<#else>
				        						NEGATIVA		
				        					</#if>
				        				</div>		
				        			</div>
				        			<div class="row form-group">
			        					<div class="col-md-12">
				        					<label>Comentario: </label> ${userWorkRate.comment}
				        				</div>		
				        			</div>
			        			</div>						        	
					      	</div>
				    	</div>
				  	</div>
				<#else>
					<form name="userWorkRatesForm${userWorkRate_index}" action="<@c.url value='/dashboard/ratings' />" method="POST" enctype="utf8">
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
						<@spring.bind "userWorkRate" />
						<input type="hidden" name="id" value="${userWorkRate.id}">
						<input type="hidden" name="quoteId" value="${userWorkRate.quoteId}">
					  	<div class="panel panel-default user-work-rate-${userWorkRate.statusType}" id="panel${userWorkRate_index}">
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