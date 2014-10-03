<#macro presupuestos quote>
	<div class="panel panel-default quote-${quote.statusType}">
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
           <#if quote.amount??>
			   <div class="row">
               		<div class="form-group col-md-12">
                    	<label>Costo del trabajo:</label> ${(quote.amount.currency.symbol)!""} ${(quote.amount.amount)!""}
                    </div>
               </div>
           </#if>
           <#if quote.description?? && quote.description!="">
			   <div class="row">
               		<div class="form-group col-md-12">
                    	<label>Comentarios sobre el trabajo:</label> ${quote.description}
                    </div>
               </div>
           </#if>
           <#if quote.validDateUntil??>
			   <div class="row">
               		<div class="form-group col-md-12">
                    	<label>Valido hasta:</label> ${(quote.validDateUntil?string("dd/MM/yy"))!""}
                    </div>
               </div>
           </#if>
           <#if quote.requireVisit??>
			   <div class="row">
               		<div class="form-group col-md-12">
                    	<label>Se necesita una visita para presupuestar:</label> ${quote.requireVisit?string('Si', 'No')}
                    </div>
               </div>
           </#if>
           <#if quote.visitAmount??>
			   <div class="row">
               		<div class="form-group col-md-12">
                    	<label>Costo de la visita:</label> ${(quote.visitAmount.currency.symbol)!""} ${(quote.visitAmount.amount)!""}
                    </div>
               </div>
           </#if>				                       		                       					                       					                       
		</div>
	</div>
</#macro>	