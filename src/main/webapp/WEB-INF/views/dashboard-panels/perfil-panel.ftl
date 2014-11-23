<div id="perfil-panel" class="col-md-12 dashboard-panel">
	<div class="row">
		<form action="<@c.url value='/dashboard/profile' />" method="POST" enctype="utf8">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			<@spring.bind "user" />
			<div class="row">
                <div class="form-group col-md-6">	                        
                	<label>Nombre</label>
					<@spring.formInput "user.firstName", 'class="form-control"'/>
                	<@spring.showErrors "<br>" />	                        	
                </div>
                <div class="form-group col-md-6">
                    <label>Apellido</label>
					<@spring.formInput "user.lastName", 'class="form-control"'/>
                	<@spring.showErrors "<br>" />
                </div>
            </div>
            <div class="row">
                <div class="form-group col-md-6">
                    <label>Email</label>
                	<@spring.formInput "user.email", 'class="form-control" readonly'/>
                	<@spring.showErrors "<br>" />	                                           	
                </div>
                <div class="form-group col-md-6">
                    <label>Telefono</label>
                    <@spring.formInput "user.telephone", 'class="form-control"'/>
                    <@spring.showErrors "<br>" />
                </div>
            </div>
            <div class="row">
                <div class="form-group col-md-6">
                    <label>Contraseña actual</label>
                    <@spring.formPasswordInput "user.password", 'class="form-control"'/>
                    <@spring.showErrors "<br>" />
                </div>
            </div>		                    	                    
            <div class="row">
                <div class="form-group col-md-6">
                    <label>Contraseña nueva</label>
                    <@spring.formPasswordInput "user.newPassword", 'class="form-control"'/>
                    <@spring.showErrors "<br>" />	                            
                </div>
                <div class="form-group col-md-6">
                    <label>Repetir contraseña nueva</label>
                    <@spring.formPasswordInput "user.newPasswordVerification", 'class="form-control"'/>
                    <@spring.showErrors "<br>" />	                            
                </div>
            </div>
			<div class="row">
                <#if user.isProfessional>
	                <div class="form-group col-md-12">
	                    <label>Servicios ofrecidos</label>
	                </div>
	                <div class="form-group col-md-12">
	                	<#list workAreas as workArea>
	                    	<input ${user.workAreaCodes?seq_contains(workArea.code)?string("checked", "")} name="workAreaCodes" type="checkbox" value="${workArea.code}"> ${workArea.description}
	                    </#list>	                            
	                </div>
                </#if>
            </div>		                    
            <button type="submit" class="btn btn-warning pull-right">Guardar</button>
        </form>
   	</div>
</div>