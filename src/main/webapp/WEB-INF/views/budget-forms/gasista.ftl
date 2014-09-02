<div id="GAS-budget-form" class="col-md-5 form-home col-centered" style="display:none;">
	<div class="form-home-content">
		<form name="orderForm" id="orderForm" action="/place-order" method="POST" enctype="utf8">
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
					<label>¿Dónde hay que hacer el trabajo?</label>
				</div>
			
								
				<div class="col-md-6">
					<span>Provincia</span>
					<select class="form-control">
						<option value=''>Capital Federal</option>
						<option value=''>Buenos Aires</option>
						<option value=''>Corrientes</option>											
					</select>
				</div>
				<div class="col-md-6">
					<span>Localidad</span>
					<select class="form-control">
						<option value=''>Almagro</option>
						<option value=''>Palermo</option>											
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
			<div class="row">
				<div class="col-md-9">    
					<label>Detalle en qué consiste el trabajo</label>    				
					<textarea name="workDescription" id="workDescription" class="detail form-control" placeholder="Escriba aquí..."></textarea>
				</div>
				<div class="col-md-3">
					<label>¿Es urgente?</label>
					<select name="workDateType" class="form-control">
						<option value='FLEXIBLE'>No</option>
						<option value='URGENT'>Si</option>											
					</select>
				</div>
			</div>
			<div class="row" style="margin-bottom: 0px">
				<div class="col-md-12">    
					<button type="submit" class="btn btn-md btn-primary pull-right">Aceptar</button>
				</div>
			</div>
		</form>
	</div>
</div>
