<#include "pedidos-macro.ftl">
<#include "presupuestos-macro.ftl">
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
				  		<@presupuestos quotesWithQuoteQuestionReply/>
		  			</div>
		  			<#list quotesWithQuoteQuestionReply.quoteQuestions as quoteQuestion>
		  				<form name="quoteQuestions${quoteQuestion_index}" action="<@c.url value='/dashboard/question-replies' />" method="POST" enctype="utf8">
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
							<@spring.bind "quoteQuestionReply" />
							<input type="hidden" name="quoteQuestionId" value="${quoteQuestion.id}">
							<input type="hidden" name="id" value="${quoteQuestion.quoteQuestionReply.id}">
				  			<div class="col-md-12 form-group">
						  		<label>Pregunta</label>
						  		<div>${quoteQuestion.description}</div>
				  			</div>
				  			<#if quoteQuestion.quoteQuestionReply.statusType="DONE">				  		
					  			<div class="col-md-12 form-group">
							  		<label>Respuesta</label>
							  		<div>${quoteQuestion.quoteQuestionReply.description}</div>
					  			</div>
					  		<#else>
					  			<div class="col-md-12 form-group">
							  		<label>Respuesta</label>
							  		<textarea name="description" class="form-control"></textarea>
					  			</div>
					  			<div class="col-md-12">
					  				<a href="javascript:document.quoteQuestions${quoteQuestion_index}.submit()" class="button btn btn-warning pull-right">Enviar respuesta</a>
					  			</div>					  			
					  		</#if>
				  		</form>
			  		</#list>
		  		</div>
		  	</div>
		</#list>  	
	</div>   		
</div>