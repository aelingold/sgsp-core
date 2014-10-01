<div id="pedidos-panel" class="col-md-12 dashboard-panel">
	<div class="row">
		<div class="btn-group btn-group-justified">
		  <div class="btn-group">
		    <button type="button" class="btn btn-default active" data-show-class="order-IN_PROGRESS">En curso</button>
		  </div>
		  <div class="btn-group">
		    <button type="button" class="btn btn-default" data-show-class="order-FINISHED">Finalizados</button>
		  </div>
		</div>
	</div>
							
	<#list orders as order>
		<#-- ejemplo de un presupuesto pedido -->
		<div class="row">
			<div class="panel panel-default order-${order.statusType}">
		  		<div class="panel-heading">
			    	<h3 class="panel-title">
			    		Necesito un ${order.workAreaDescription}
			    		<#if order.workDateType='URGENT'>
			    			<span class="pull-right urgente">Es urgente</span>
			    		</#if>
			    	</h3>
			  	</div>
			  	<div class="panel-body">
			  		<div class="row">
			  			<#list order.workAreaItemCodes as workAreaItemCode>
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
                   <#if order.squareMeters??>
                       <div class="row">
	                        <div class="form-group col-md-12">
	                        	<label>¿Que tamaño tiene la superficie?</label> ${order.squareMeters} m2
	                        </div>
                       </div>
				   </#if>
                   <#if order.airConditionerPower??>
                       <div class="row">
	                        <div class="form-group col-md-12">
	                        	<label>¿Cuantas frigorias tiene el aire?</label> ${order.airConditionerPower} frigorias
	                        </div>
                       </div>
				   </#if>									   					                       					                   
                   <div class="row">
                        <div class="form-group col-md-12">
                        	<label>Trabajo a realizar en:</label> ${order.stateDescription}, ${(order.cityDescription)!""}
                        </div>
                   </div>				                       
                   <div class="row">
                        <div class="form-group col-md-12">
                        	<label>Detalle del trabajo:</label> ${order.workDescription}
                        </div>
                   </div>
			  	</div>								  	
			</div>
			<div class="response-list-section col-md-12">
				<div class="col-md-12 response-list-panel">
					<div class="col-md-12">
						<h5>Presupuestos</h5>
					</div>
					<ul class="response-list">
						<#assign hasReplies=false />										
						<#list quotes as quote>
							<#if quote.orderId==order.id && (quote.statusType="REPLIED" || quote.statusType="ACCEPTED" || quote.statusType="DONE")>
								<#assign hasReplies=true />				
						  		<li class="budget-response col-md-12">
						  			<div class="col-md-3">
						  				${quote.firstName} ${quote.lastName} (${userWorkRatesQtyMap[quote.username]})
						  			</div>
						  			<div class="col-md-2">
						  				<#if quote.requireVisit == false>
						  					Total ${quote.amount.currency.symbol}${quote.amount.amount}
						  				<#else>
						  					Visita ${quote.visitAmount.currency.symbol}${quote.visitAmount.amount}
						  				</#if>
						  			</div>
						  			<div class="col-md-3" style="color: #bbb;">
						  				<#if quote.requireVisit == false>
						  					Válido hasta ${quote.validDateUntil?string("dd/MM/yy")}
						  				</#if>
						  			</div>
						  			<div class="col-md-4">
						  				<#if quote.statusType="REPLIED">
							  				<form name="budgetsFormAccepted${quote_index}" action="<@c.url value='/dashboard/requests/accepted/${quote.id}' />" method="POST" enctype="utf8">
							  					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
							  					<a href="javascript:document.budgetsFormAccepted${quote_index}.submit()" class="button btn btn-xs btn-warning pull-right">Aceptar</a>
							  				</form>
							  			<#elseif quote.statusType="ACCEPTED">
							  				Aceptado(Pendiente calificacion)
							  			</#if>
						  				<a class="button btn btn-xs btn-default pull-right make-question" style="margin-right:10px;cursor:pointer;">Realizar pregunta</a>							  				
						  			</div>
						  			<form name="questionsForm${quote_index}" action="<@c.url value='/dashboard/questions' />" method="POST" enctype="utf8" style="display:none;">
										<@spring.bind "quoteQuestion" />
						  				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
						  				<input type="hidden" name="quoteId" value="${quote.id}">
							  			<ul class="messages-list">
								  				<#list quote.quoteQuestions as quoteQuestion>
									  				<li class="col-md-12">
									  					<div class="question-message message">
										  					<div class="user-name-message">${quoteQuestion.description}</div>
										  					<div class="message-content">${(quoteQuestion.quoteQuestionReply.description)!""}</div>
										  				</div>
									  				</li>
									  			</#list>
							  			</ul>
							  			<div class="col-md-12" style="margin-top:8px;">
							  				<textarea name="description" class="form-control"></textarea>
							  				<a href="javascript:document.questionsForm${quote_index}.submit()" class="button btn btn-xs btn-info pull-right" style="margin-top:5px;">Enviar mensaje</a>
							  			</div>												  			
								  	</form>
						  		</li>											  		
						  	</#if>
						</#list>
						<#if hasReplies == false>
							<li class="budget-response col-md-12">
					  			<div class="col-md-12">
					  				Aún no se han recibido presupuestos.
					  			</div>
					  		</li>
						</#if>											
		  			</ul>
		  		</div>
	  		</div>
		</div>
		<#-- fin de ejemplo de un presupuesto pedido -->						
	</#list>						
	
</div>