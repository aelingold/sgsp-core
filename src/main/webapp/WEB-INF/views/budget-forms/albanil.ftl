<div id="ALB-budget-form" class="col-md-5 form-home col-centered" style="display:none;">
	<div class="form-home-content">
		<form name="ALBorderForm" id="ALBorderForm" action="<@c.url value='/place-order' />" method="POST" enctype="utf8">
			<input type="hidden" name="workAreaCode" value="ALB">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			<div class="row">
				<div class="col-md-12">
					<div style="text-align: center;position:relative;">
						<span class="changeServiceType" title="Volver">Volver</span>
						<span class="selected-work-area">Necesito un Albañil</span>						
					</div>					
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<label>
						<#list workAreaQuestions as workAreaQuestion>
							<#if workAreaQuestion.workAreaCode="ALB" && workAreaQuestion.groupType='WORK'>
								${workAreaQuestion.description}							
							</#if>
						</#list>
					</label>
					<select name="workAreaItemCodes[0]" class="form-control">
						<#list workAreas as workArea>
							<#if workArea.code='ALB'>
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
			<#include "common.ftl">
			<div class="row" style="margin-bottom: 0px">
				<div class="col-md-12">    
					<a href="javascript:document.ALBorderForm.submit();" class="btn btn-md btn-primary pull-right">Aceptar</a>
				</div>
			</div>
		</form>
	</div>
</div>
