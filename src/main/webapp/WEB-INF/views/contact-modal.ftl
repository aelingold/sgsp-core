<div class="modal fade" id="contactModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<form name="sentMessage" id="contactForm" action="<@c.url value='/contact-messages'/>" novalidate method="POST">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
      			<div class="modal-header">
        			<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        			<h4 class="modal-title" id="myModalLabel">Contacto</h4>
      			</div>
      			<div class="modal-body">
     				<div class="row">
            			<div class="col-lg-12">
		                    <div class="row">
		                        <div class="form-group col-md-6">
		                        	<label>Nombre</label>
		                            <input type="text" class="form-control" id="name" name="name">
		                        </div>
		                    </div>
		                    <div class="row">
		                        <div class="form-group col-md-6">
		                        	<label>Email</label>
		                            <input type="email" class="form-control" id="email" name="email">
		                        </div>
		                    </div>
		                    <div class="row">
		                        <div class="form-group col-md-12">
		                        	<label>Mensaje</label>
		                            <textarea class="form-control" id="message" name="message"></textarea>
		                        </div>
		                    </div>
            			</div>
        			</div>
				</div>
				<div class="modal-footer">
			        <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
					<button type="submit" class="btn btn-primary">Enviar</button>
				</div>
			</form>
		</div>
	</div>
</div>