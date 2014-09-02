<div class="row">
	<div class="col-md-12">
		<label>¿Dónde hay que hacer el trabajo?</label>
	</div>											
	<div class="col-md-6">
		<span>Provincia</span>
		<select class="form-control">
			<#list states as state>			
				<option value=${state.code}>${state.description}</option>
			</#list>											
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