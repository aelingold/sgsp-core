<#include "pedidos-macro.ftl">
<div id="pedidos-panel" class="col-md-12 dashboard-panel">
	<div class="row">
		<div class="btn-group btn-group-justified">
		  <div class="btn-group">
		    <button type="button" class="btn btn-default active" data-show-class="order-IN_PROGRESS">En curso (${inProgressOrdersQty})</button>
		  </div>
		  <div class="btn-group">
		    <button type="button" class="btn btn-default" data-show-class="order-FINISHED">Finalizados (${finishedOrdersQty})</button>
		  </div>
		</div>
	</div>
						
	<#if inProgressOrdersQty == 0>
		<div class="order-IN_PROGRESS">
			<div class="col-md-12" style="margin-top:15px;">
				No tienes presupuestos en curso.
			</div>
		</div>
	</#if>
	
	<#if finishedOrdersQty == 0>
		<div class="order-FINISHED">
			<div class="col-md-12" style="margin-top:15px;">
				No tienes presupuestos finalizados.
			</div>
		</div>
	</#if>							
							
	<#list orders as order>
		<#-- ejemplo de un presupuesto pedido -->
		<div class="row">
			<@pedidos order/>
			<div class="response-list-section col-md-12">
				<div class="col-md-12 response-list-panel">
					<div class="col-md-12">
						<h5>Presupuestos</h5>
					</div>
					<ul class="response-list">
						<#assign hasReplies=false />	
						<#assign hasOneQuoteAccepted=false />	
						<#list quotes as quote>
							<#if quote.orderId==order.id && quote.statusType="ACCEPTED">
								<#assign hasOneQuoteAccepted=true />
							</#if>
						</#list>							
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
						  				<#if quote.requireVisit == false && quote.validDateUntil??>
						  					Válido hasta ${quote.validDateUntil?string("dd/MM/yy")}
						  				</#if>
						  			</div>
						  			<div class="col-md-4">
						  				<#if quote.statusType="REPLIED">
						  					<#if hasOneQuoteAccepted == false>
								  				<form name="budgetsFormAccepted${quote_index}" action="<@c.url value='/dashboard/requests/accepted/${quote.id}' />" method="POST" enctype="utf8">
								  					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
								  					<a href="javascript:document.budgetsFormAccepted${quote_index}.submit()" class="button btn btn-xs btn-warning pull-right">Aceptar</a>
								  				</form>
								  			</#if>
							  			<#elseif quote.statusType="ACCEPTED">
							  				<span class="accepted-quote">Aceptado</span> <span class="pending-rate-quote">(Calificación pendiente)</span>
							  			</#if>
							  			<#if hasOneQuoteAccepted == false || (quote.quoteQuestions?size > 0)>
						  					<a class="button btn btn-xs btn-default pull-right make-question" style="margin-right:10px;cursor:pointer;">
												<#if quote.quoteQuestions?size != 0>
						  							Ver preguntas
						  						<#else>
					  								Realizar pregunta
							  					</#if>		  							
					  						</a>
					  					</#if>
						  			</div>
						  			<#if hasOneQuoteAccepted == false>
							  			<form name="questionsForm${quote_index}" action="<@c.url value='/dashboard/questions' />" method="POST" enctype="utf8" style="display:none;">
											<@spring.bind "quoteQuestion" />
							  				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
							  				<input type="hidden" name="quoteId" value="${quote.id}">
								  			<ul class="messages-list">
									  				<#list quote.quoteQuestions as quoteQuestion>
										  				<li class="col-md-12">
										  					<div class="question-message message">
											  					<div class="user-name-message">${quoteQuestion.description} - ${quoteQuestion.createdAt?string("dd/MM/yy")}</div>
											  					<div class="message-content">${(quoteQuestion.quoteQuestionReply.description)!""} - ${(quoteQuestion.quoteQuestionReply.updatedAt?string("dd/MM/yy"))!""}</div>
											  				</div>
										  				</li>
										  			</#list>
								  			</ul>
								  			<div class="col-md-12" style="margin-top:8px;">
								  				<textarea name="description" class="form-control"></textarea>
								  				<a href="javascript:document.questionsForm${quote_index}.submit()" class="button btn btn-xs btn-info pull-right" style="margin-top:5px;">Enviar mensaje</a>
								  			</div>												  			
									  	</form>
								  	</#if>	
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