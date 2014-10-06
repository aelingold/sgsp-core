<#include "pedidos-macro.ftl">
<#include "presupuestos-macro.ftl">
<div id="preguntas-panel" class="col-md-12 dashboard-panel">
	<div class="row">
		<div class="panel panel-default">
  			<div class="panel-heading">
	    		<h3 class="panel-title">
	    			Preguntas
	    		</h3>
	  		</div>
	  		<#if quotesWithQuoteQuestionReplies?size == 0>
	  			<div class="panel-body">
	  				No se han encontrado preguntas.
				</div>	  				
	  		</#if>
			<#list quotesWithQuoteQuestionReplies as quotesWithQuoteQuestionReply>
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
			</#list>
		</div>  	
	</div>   		
</div>