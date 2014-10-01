<#include "pedidos-macro.ftl">
<div id="preguntas-panel" class="col-md-12 dashboard-panel">
	<div class="row">
		<#list quotesWithQuoteQuestionReplies as quotesWithQuoteQuestionReply>
			<div class="panel panel-default">
	  			<div class="panel-heading">
		    		<h3 class="panel-title">
		    			${quotesWithQuoteQuestionReply.order.username}
		    		</h3>
		  		</div>
		  		<div class="panel-body">
			  		<div class="col-md-12 form-group">
						<div class="panel panel-default order-${quotesWithQuoteQuestionReply.order.statusType}">
					  		<div class="panel-heading">
						    	<h3 class="panel-title">
						    		Necesito un ${quotesWithQuoteQuestionReply.order.workAreaDescription}
						    		<#if quotesWithQuoteQuestionReply.order.workDateType='URGENT'>
						    			<span class="pull-right urgente">Es urgente</span>
						    		</#if>
						    	</h3>
						  	</div>
						  	<div class="panel-body">
						  		<div class="row">
						  			<#list quotesWithQuoteQuestionReply.order.workAreaItemCodes as workAreaItemCode>
						  				<#list workAreaItems as workAreaItem>
											<#if workAreaItem.code=workAreaItemCode>
												<#list workAreaQuestions as workAreaQuestion>
													<#if workAreaQuestion.workAreaCode=workAreaItem.workAreaCode && workAreaQuestion.groupType=workAreaItem.groupType>
				                        				<div class="form-group col-md-6">
				                        					<label>${workAreaQuestion.description}</label> ${workAreaItem.description}
				                        				</div>
						    						</#if>
						    					</#list>
						    				</#if>								  					
						  				</#list>
			                   		</#list>						                        
			                   </div>
			                   <#if quotesWithQuoteQuestionReply.order.squareMeters??>
			                       <div class="row">
				                        <div class="form-group col-md-12">
				                        	<label>¿Que tamaño tiene la superficie?</label> ${quotesWithQuoteQuestionReply.order.squareMeters} m2
				                        </div>
			                       </div>
							   </#if>
			                   <#if quotesWithQuoteQuestionReply.order.airConditionerPower??>
			                       <div class="row">
				                        <div class="form-group col-md-12">
				                        	<label>¿Cuantas frigorias tiene el aire?</label> ${quotesWithQuoteQuestionReply.order.airConditionerPower} frigorias
				                        </div>
			                       </div>
							   </#if>									   					                       					                   
		                       <div class="row">
			                        <div class="form-group col-md-12">
			                        	<label>Trabajo a realizar en:</label> ${quotesWithQuoteQuestionReply.order.stateDescription}, ${(quotesWithQuoteQuestionReply.order.cityDescription)!""}
			                        </div>
		                       </div>				                       
		                       <div class="row">
			                        <div class="form-group col-md-12">
			                        	<label>Detalle del trabajo:</label> ${quotesWithQuoteQuestionReply.order.workDescription}
			                        </div>
		                       </div>
						  	</div>								  	
						</div>
		  			</div>
		  			<div class="col-md-12 form-group">
				  		MOSTRAR PRESUPUESTO REALIZADO
		  			</div>
		  			<div class="col-md-12 form-group">
				  		<label>Pregunta</label>
				  		<div>Que pasa si tengo que aal lal lal all lalal alal la?</div>
		  			</div>						  		
		  			<div class="col-md-12 form-group">
				  		<label>Respuesta</label>
				  		<textarea class="form-control"></textarea>
		  			</div>
		  			<div class="col-md-12">
		  				<a href="#" class="button btn btn-warning pull-right">Enviar respuesta</a>
		  			</div>
		  		</div>
		  	</div>
		</#list>  	
	</div>   		
</div>