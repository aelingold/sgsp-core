<#include "pedidos-macro.ftl">
<#include "presupuestos-macro.ftl">
<div id="preguntas-panel" class="col-md-12 dashboard-panel">
	<div class="row">
		<div class="btn-group btn-group-justified">
		  <div class="btn-group">
		    <button type="button" class="btn btn-default active" data-show-class="question-PENDING">Pendientes (${pendingQuotesWithQuoteQuestionRepliesQty})</button>
		  </div>
		  <div class="btn-group">
		    <button type="button" class="btn btn-default" data-show-class="question-DONE">Respondidas (${doneQuotesWithQuoteQuestionRepliesQty})</button>
		  </div>
		</div>
	</div>

	<div class="row">
		<#if pendingQuotesWithQuoteQuestionRepliesQty == 0>
			<div class="question-PENDING">
				<div class="col-md-12" style="margin-top:15px;">
					No tienes preguntas pendientes.
				</div>
			</div>
		</#if>	
	</div>
	
	<div class="row">
		<#if doneQuotesWithQuoteQuestionRepliesQty == 0>
			<div class="question-DONE">
				<div class="col-md-12" style="margin-top:15px;">
					No tienes preguntas respondidas.
				</div>
			</div>
		</#if>	
	</div>
		
	<#list quotesWithQuoteQuestionReplies as quotesWithQuoteQuestionReply>
		<div class="row">
			<div class="panel panel-default">			
					<#if quotesWithQuoteQuestionReply.pendingQuoteQuestionsReply>
						<div class="question-PENDING">
					  		<div class="panel-body">
					  			<div class="col-md-12 form-group">
							  		<@presupuestos quotesWithQuoteQuestionReply/>
					  			</div>
					  			<#list quotesWithQuoteQuestionReply.quoteQuestions as quoteQuestion>
					  				<#if quoteQuestion.quoteQuestionReply.statusType="DONE">
							  			<div class="col-md-12 form-group">
									  		<label>Pregunta</label>
									  		<div>${quoteQuestion.description}</div>
							  			</div>				  		
							  			<div class="col-md-12 form-group">
									  		<label>Respuesta</label>
									  		<div>${quoteQuestion.quoteQuestionReply.description}</div>
							  			</div>
									<#else>			
						  				<form name="quoteQuestions${quotesWithQuoteQuestionReply_index}${quoteQuestion_index}" action="<@c.url value='/dashboard/question-replies' />" method="POST" enctype="utf8">
											<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
											<@spring.bind "quoteQuestionReply" />
											<input type="hidden" name="quoteQuestionId" value="${quoteQuestion.id}">
											<input type="hidden" name="id" value="${quoteQuestion.quoteQuestionReply.id}">
								  			<div class="col-md-12 form-group">
										  		<label>Pregunta</label>
										  		<div>${quoteQuestion.description}</div>
								  			</div>				  		
								  			<div class="col-md-12 form-group">
										  		<label>Respuesta</label>
										  		<textarea name="description" class="form-control"></textarea>
								  			</div>
								  			<div class="col-md-12">
								  				<a href="javascript:document.quoteQuestions${quotesWithQuoteQuestionReply_index}${quoteQuestion_index}.submit()" class="button btn btn-warning pull-right">Enviar respuesta</a>
								  			</div>					  			
								  		</form>
									</#if>
						  		</#list>
						  	</div>
						</div>
					<#else>
						<div class="question-DONE">
					  		<div class="panel-body">
					  			<div class="col-md-12 form-group">
							  		<@presupuestos quotesWithQuoteQuestionReply/>
					  			</div>
					  			<#list quotesWithQuoteQuestionReply.quoteQuestions as quoteQuestion>
					  				<#if quoteQuestion.quoteQuestionReply.statusType="DONE">
							  			<div class="col-md-12 form-group">
									  		<label>Pregunta</label>
									  		<div>${quoteQuestion.description}</div>
							  			</div>				  		
							  			<div class="col-md-12 form-group">
									  		<label>Respuesta</label>
									  		<div>${quoteQuestion.quoteQuestionReply.description}</div>
							  			</div>
									<#else>			
						  				<form name="quoteQuestions${quotesWithQuoteQuestionReply_index}${quoteQuestion_index}" action="<@c.url value='/dashboard/question-replies' />" method="POST" enctype="utf8">
											<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
											<@spring.bind "quoteQuestionReply" />
											<input type="hidden" name="quoteQuestionId" value="${quoteQuestion.id}">
											<input type="hidden" name="id" value="${quoteQuestion.quoteQuestionReply.id}">
								  			<div class="col-md-12 form-group">
										  		<label>Pregunta</label>
										  		<div>${quoteQuestion.description}</div>
								  			</div>				  		
								  			<div class="col-md-12 form-group">
										  		<label>Respuesta</label>
										  		<textarea name="description" class="form-control"></textarea>
								  			</div>
								  			<div class="col-md-12">
								  				<a href="javascript:document.quoteQuestions${quotesWithQuoteQuestionReply_index}${quoteQuestion_index}.submit()" class="button btn btn-warning pull-right">Enviar respuesta</a>
								  			</div>					  			
								  		</form>
									</#if>
						  		</#list>
						  	</div>
						</div>
			  		</#if>
				
			</div>  	
		</div>
	</#list>   		
</div>