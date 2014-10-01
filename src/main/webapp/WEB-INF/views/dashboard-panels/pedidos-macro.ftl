<#macro pedidos order>
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
</#macro>	