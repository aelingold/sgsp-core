<div class="col-md-5 form-home col-centered">
	<div class="form-home-content">
		<div class="row">
			<div class="col-md-12">
				<span class="selected-work-area">Necesito un Gasista</span>
				<a href="" title="Cambiar"><img class="edit-icon" src="<@c.url value='/static/new/img/icons/edit.png'/>" alt=""></a>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<label>¿Qué tipo de inmueble es?</label>
				<select name="workAreaItemCodes[0]">
					<#list workAreas as workArea>
						<#if workArea.code='GAS'>
							<#list workArea.workAreaItemIds as workAreaItemId>
    							<#list workAreaItems as workAreaItem>
    								<#if workAreaItem.id=workAreaItemId && workAreaItem.groupType='PROPERTY'>
										<option value='${workAreaItem.code}'>${workAreaItem.description}</option>    									
    								</#if>
    							</#list>
							</#list>					
						</#if>
					</#list>					
				</select>
			</div>	    				
			<div class="col-md-6">        				
				<label>¿Qué hay que hacer?</label>
				<select name="workAreaItemCodes[1]">
					<#list workAreas as workArea>
						<#if workArea.code='GAS'>
							<#list workArea.workAreaItemIds as workAreaItemId>
    							<#list workAreaItems as workAreaItem>
    								<#if workAreaItem.id=workAreaItemId && workAreaItem.groupType='WORK'>
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
				<label>¿Qué artefacto es?</label>	        				
			</div>
					<#list workAreas as workArea>
						<#if workArea.code='GAS'>
							<#list workArea.workAreaItemIds as workAreaItemId>
    							<#list workAreaItems as workAreaItem>
    								<#if workAreaItem.id=workAreaItemId && workAreaItem.groupType='ARTIFACT'>
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
			<div class="col-md-12">    
				<label>Detalle en qué consiste el trabajo</label>    				
				<textarea name="workDescription" id="workDescription" class="detail" placeholder="Escriba aquí..."></textarea>
			</div>
		</div>
		<div class="row" style="margin-bottom: 0px">
			<div class="col-md-12">    
				<a href="" class="btn btn-md btn-primary pull-right">Aceptar</a>
			</div>
		</div>
	</div>
</div>
