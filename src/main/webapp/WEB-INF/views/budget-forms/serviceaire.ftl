<div id="SAA-budget-form" class="col-md-5 form-home col-centered" style="display:none;">
	<div class="form-home-content">
		<form name="SAAorderForm" id="SAAorderForm" action="<@c.url value='/place-order' />" method="POST" enctype="utf8">
			<input type="hidden" name="workAreaCode" value="SAA">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			<div class="row">
				<div class="col-md-12">
					<div style="text-align: center;position:relative;">
						<span class="changeServiceType" title="Volver">Volver</span>
						<span class="selected-work-area">Necesito un Service de Aire Acondicionado</span>						
					</div>					
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<label>
						<#list workAreaQuestions as workAreaQuestion>
							<#if workAreaQuestion.workAreaCode="SAA" && workAreaQuestion.groupType='WORK'>
								${workAreaQuestion.description}							
							</#if>
						</#list>
					</label>
					<select name="workAreaItemCodes[0]" class="form-control">
						<#list workAreas as workArea>
							<#if workArea.code='SAA'>
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
				<div class="col-md-6">
					<label>
						<#list workAreaQuestions as workAreaQuestion>
							<#if workAreaQuestion.workAreaCode="SAA" && workAreaQuestion.groupType='AIR_SYSTEM'>
								${workAreaQuestion.description}							
							</#if>
						</#list>
					</label>
					<select name="workAreaItemCodes[1]" class="form-control">
						<#list workAreas as workArea>
							<#if workArea.code='SAA'>
								<#list workArea.workAreaItemCodes as workAreaItemCode>
	    							<#list workAreaItems as workAreaItem>
	    								<#if workAreaItem.code=workAreaItemCode && workAreaItem.groupType='AIR_SYSTEM'>
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
				<div class="col-md-6">
					<label>
						<#list workAreaQuestions as workAreaQuestion>
							<#if workAreaQuestion.workAreaCode="SAA" && workAreaQuestion.groupType='BRAND'>
								${workAreaQuestion.description}							
							</#if>
						</#list>
					</label>
					<select name="workAreaItemCodes[2]" class="form-control">
						<#list workAreas as workArea>
							<#if workArea.code='SAA'>
								<#list workArea.workAreaItemCodes as workAreaItemCode>
	    							<#list workAreaItems as workAreaItem>
	    								<#if workAreaItem.code=workAreaItemCode && workAreaItem.groupType='BRAND'>
											<option value='${workAreaItem.code}'>${workAreaItem.description}</option>    									
	    								</#if>
	    							</#list>
								</#list>					
							</#if>
						</#list>					
					</select>
				</div>
				<div class="col-md-6">
					<label>¿Cuántas frigorías tiene el aire?</label>
					<input type="text" name="airConditionerPower" placeholder="Ej 2000, 3000, etc." size="30" class="form-control" />
				</div>
			</div>			
			<#include "common.ftl">
			<div class="row" style="margin-bottom: 0px">
				<div class="col-md-12">    
					<a href="javascript:document.SAAorderForm.submit();" class="btn btn-md btn-primary pull-right">Aceptar</a>
				</div>
			</div>
		</form>
	</div>
</div>
