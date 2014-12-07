<#include "presupuestos-macro.ftl">
<div id="presupuestos-panel" class="col-md-12 dashboard-panel">
	<div class="row">
		<div class="btn-group btn-group-justified">
		  <div class="btn-group">
		    <button type="button" class="btn btn-default" data-show-class="quote-PENDING">Presupuestos pendientes (${pendingQuotesQty})</button>
		  </div>
		  <div class="btn-group">
		    <button type="button" class="btn btn-default" data-show-class="quote-DONE">Presupuestos realizados (${doneQuotesQty})</button>
		  </div>
		</div>
	</div>
	
	<#if pendingQuotesQty == 0>
		<div class="row">
			<div class="panel quote-PENDING">
				<div class="col-md-12" style="margin-top:15px;">
					No tienes presupuestos pendientes.
				</div>
			</div>
		</div>
	</#if>
	
	<#if doneQuotesQty == 0>
		<div class="row">
			<div class="panel quote-REPLIED">
				<div class="col-md-12" style="margin-top:15px;">
					No tienes presupuestos realizados.
				</div>
			</div>
		</div>
	</#if>	
													
	<#list quotes as quote>
		<#if quote.statusType="PENDING">
			<div class="row">
				<div id="quote-${quote.id}" class="panel panel-default quote-${quote.statusType}">						
					<form name="budgetsForm${quote_index}" action="<@c.url value='/dashboard/budgets/replied' />" method="POST" enctype="utf8">
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
						<input type="hidden" name="statusType" value="REPLIED">
						<input type="hidden" name="id" value="${quote.id}">
						<@spring.bind "quote" />																						
						<#-- ejemplo de un presupuesto pedido -->
				  		<div class="panel-heading">
					    	<h3 class="panel-title">
					    		Necesito un ${quote.order.workAreaDescription} - ${quote.order.username}
					    		<#if quote.order.workDateType='URGENT'>
					    			<span class="pull-right urgente">Es urgente</span>
					    		</#if>
					    	</h3>
					  	</div>
					  	<div class="panel-body">
					  		<div class="row">
					  			<#list quote.order.workAreaItemCodes as workAreaItemCode>
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
		                   <#if quote.order.squareMeters??>
		                       <div class="row">
			                        <div class="form-group col-md-12">
			                        	<label>¿Que tamaño tiene la superficie?</label> ${quote.order.squareMeters} m2
			                        </div>
		                       </div>
						   </#if>
		                   <#if quote.order.airConditionerPower??>
		                       <div class="row">
			                        <div class="form-group col-md-12">
			                        	<label>¿Cuantas frigorias tiene el aire?</label> ${quote.order.airConditionerPower} frigorias
			                        </div>
		                       </div>
						   </#if>									   					                       					                   
	                       <div class="row">
		                        <div class="form-group col-md-12">
		                        	<label>Trabajo a realizar en:</label> ${quote.order.stateDescription}, ${(quote.order.cityDescription)!""}
		                        </div>
	                       </div>				                       
	                       <div class="row">
		                        <div class="form-group col-md-12">
		                        	<label>Detalle del trabajo:</label> ${quote.order.workDescription}
		                        </div>
	                       </div>
	                       
	                       <div class="row">
                       			<div class="col-md-12">
                       				<div class="budget-form-border">
			                       		<div class="row budget-common-form">
					                        <div class="form-group col-md-4">
					                        	<label>Costo del trabajo</label> 
					                        	<div class="input-group">
											      <span class="input-group-addon">${currency.symbol}</span>
											      <@spring.formInput "quote.amount.amount", 'class="form-control"'/>
											      <input type="hidden" name="amount.currency.code" value="${currency.code}">
											    </div>
					                        </div>
					                        <div class="form-group col-md-4">
					                        	<label>Valido hasta (dd/mm/yyyy)</label>
				                        		<@spring.formInput "quote.validDateUntil", 'class="form-control"'/>
					                        </div>
				                       </div>
				                       <div class="row budget-common-form">
					                        <div class="form-group col-md-12">
					                        	<label>Comentarios sobre el trabajo</label> 
					                        	<@spring.formTextarea "quote.description", 'class="form-control"'/>
					                        </div>
					                   </div>
				                       <div class="row">
					                        <div class="form-group col-md-12">
					                        	<label style="font-weight: initial;">
					                        		<@spring.formCheckbox "quote.requireVisit",'class="need-visit"'/>
					                        		Se necesita una visita para presupuestar
					                        	</label>
					                        </div>
					                        <div class="form-group col-md-4 visit-form" style="display:none;">
					                        	<label>Costo de la visita</label> 
					                        	<div class="input-group">
											      <span class="input-group-addon">$</span>
											      <@spring.formInput "quote.visitAmount.amount", 'class="form-control"'/>
											      <input type="hidden" name="visitAmount.currency.code" value="${currency.code}">
											    </div>
					                        </div>
				                       </div>
				                       <div class="row">
					                        <div class="col-md-12">
					                        	<a href="javascript:document.budgetsForm${quote_index}.submit()" class="button btn btn-warning pull-right">Presupuestar</a>
					                        </div>
				                       </div>
			                      	</div>
		                      	</div>
				        	</div>						                    
						</div>
					</form>										  	
				</div>
			</div>
		<#elseif quote.statusType="REPLIED" || quote.statusType="DONE">
			<div class="row">
				<@presupuestos quote/>
        	</div>														
		</#if>
		<#-- fin de ejemplo de un presupuesto pedido -->
	</#list>
</div>