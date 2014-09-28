<div class="modal fade" id="contactModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel">Contacto</h4>
      </div>
      <div class="modal-body">
     	<div class="row">
            <div class="col-lg-12">
                <form name="sentMessage" id="contactForm" novalidate>
                    <div class="row">
                        <div class="form-group col-md-6">
                        	<label>Nombre</label>
                            <input type="text" class="form-control" id="name">
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group col-md-6">
                        	<label>Email</label>
                            <input type="email" class="form-control" id="email">
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group col-md-12">
                        	<label>Mensaje</label>
                            <textarea class="form-control" id="message"></textarea>
                        </div>
                    </div>
                </form>
            </div>
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
        <button type="button" class="btn btn-primary">Enviar</button>
      </div>
    </div>
  </div>
</div>