<div id="GAS-budget-form" class="col-md-5 form-home col-centered" style="display:none;">
	<div class="form-home-content">
		<form name="GASorderForm" id="GASorderForm" action="/place-order" method="POST" enctype="utf8">
			<input type="hidden" name="workAreaCode" value="GAS">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			<div class="row">
				<div class="col-md-12">
					<span class="selected-work-area">Necesito un Gasista</span>
					<a class="changeServiceType" title="Cambiar"><img class="edit-icon" src="<@c.url value='/static/new/img/icons/edit.png'/>" alt=""></a>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<label>
						<#list workAreaQuestions as workAreaQuestion>
							<#if workAreaQuestion.workAreaCode="GAS" && workAreaQuestion.groupType='PROPERTY'>
								${workAreaQuestion.description}							
							</#if>
						</#list>
					</label>
					<select name="workAreaItemCodes[0]" class="form-control">
						<#list workAreas as workArea>
							<#if workArea.code='GAS'>
								<#list workArea.workAreaItemCodes as workAreaItemCode>
	    							<#list workAreaItems as workAreaItem>
	    								<#if workAreaItem.code=workAreaItemCode && workAreaItem.groupType='PROPERTY'>
											<option value='${workAreaItem.code}'>${workAreaItem.description}</option>    									
	    								</#if>
	    							</#list>
								</#list>					
							</#if>
						</#list>					
					</select>
				</div>	    				
				<div class="col-md-6">
					<label>
						<#list workAreaQuestions as workAreaQuestion>
							<#if workAreaQuestion.workAreaCode="GAS" && workAreaQuestion.groupType='WORK'>
								${workAreaQuestion.description}							
							</#if>
						</#list>
					</label>				        				
					<select name="workAreaItemCodes[1]" class="form-control">
						<#list workAreas as workArea>
							<#if workArea.code='GAS'>
								<#list workArea.workAreaItemCodes as workAreaItemCode>
	    							<#list workAreaItems as workAreaItem>
	    								<#if workAreaItem.code=workAreaItemCode && workAreaItem.groupType='WORK'>
											<option value='${workAreaItem.code}'>${workAreaItem.description}</option>    									
	    								</#if>
	    							</#list>
								</#list>					
							</#if>
						</#list>
					</select>
				</div>					
			</div>
			<div class="row">
				<div class="col-md-12">
					<label>
						<#list workAreaQuestions as workAreaQuestion>
							<#if workAreaQuestion.workAreaCode="GAS" && workAreaQuestion.groupType='ARTIFACT'>
								${workAreaQuestion.description}							
							</#if>
						</#list>
					</label>				        					        				
				</div>
				<#list workAreas as workArea>
					<#if workArea.code='GAS'>
						<#list workArea.workAreaItemCodes as workAreaItemCode>
							<#list workAreaItems as workAreaItem>
								<#if workAreaItem.code=workAreaItemCode && workAreaItem.groupType='ARTIFACT'>
									<div class="col-md-4">
										${workAreaItem.description} <input name="workAreaItemCodes[${workAreaItem_index}]" value="${workAreaItem.code}" type="checkbox">
									</div>									
								</#if>
							</#list>
						</#list>					
					</#if>
				</#list>			
			</div>
			<#include "common.ftl">
			<div class="row" style="margin-bottom: 0px">
				<div class="col-md-12">    
					<a href="javascript:document.GASorderForm.submit();" class="btn btn-md btn-primary pull-right">Aceptar</a>
				</div>
			</div>
		</form>
	</div>
</div>