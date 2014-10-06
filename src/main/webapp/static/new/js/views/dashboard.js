function showPanel(selector){
	$(".dashboard-panel").hide();
	$(selector).show();
}

$("#dash-menu li").removeClass("active");
switch(tabToShow){
	case "profile":
		showPanel("#perfil-panel");
		$("#perfil-option").addClass("active");
		break;
	case "requests":
		showPanel("#pedidos-panel");
		$("#pedidos-option").addClass("active");
		break;
	case "ratings":
		showPanel("#calificaciones-panel");
		$("#calificaciones-option").addClass("active");
		break;
	case "budgets":
		showPanel("#presupuestos-panel");
		$("#presupuestos-option").addClass("active");
		break;
	case "config":
		showPanel("#configuracion-panel");
		$("#configuracion-option").addClass("active");		
		break;
	case "socialmedia":
		showPanel("#redessociales-panel");
		$("#redessociales-option").addClass("active");		
		break;
	case "admin":
		showPanel("#administracion-panel");
		$("#administracion-option").addClass("active");		
		break;
	case "reports":
		showPanel("#reportes-panel");
		$("#reportes-option").addClass("active");		
		break;
	case "payments":
		showPanel("#pagos-panel");
		$("#pagos-option").addClass("active");		
		break;
	case "questions":
		showPanel("#preguntas-panel");
		$("#preguntas-option").addClass("active");		
		break;		
}

$(".need-visit").change(function() {
	if ($(this).is(":checked")){
		$(this).parents(".row").find(".visit-form").show();
		$(this).parents(".panel-body").find(".budget-common-form").hide();
	}else {
		$(this).parents(".row").find(".visit-form").hide();
		$(this).parents(".panel-body").find(".budget-common-form").show();
	}
});

$(".select-all-cities").on("click", function(){
	var cities = $($(this).parents(".row")[0]).find("input[type='checkbox']");
	$(cities).attr("checked",true);	
});

$(".btn-group .btn").on("click", function(){
	$(this).parent().parent().find(".btn").removeClass("active");
	$(this).addClass("active");	
});

$("#calificaciones-panel .btn-group .btn").on("click", function(){
	var showClass = $(this).attr("data-show-class");
	$("#calificaciones-panel .panel-group .rating-section").hide();
	$("#calificaciones-panel .panel-group .rating-section."+showClass).show();
});
$("#calificaciones-panel .btn-group .btn:first").click();

$("#pedidos-panel .btn-group .btn").on("click", function(){
	var showClass = $(this).attr("data-show-class");
	$("#pedidos-panel .order-IN_PROGRESS").parent().hide();
	$("#pedidos-panel .order-FINISHED").parent().hide();
	$("#pedidos-panel ."+showClass).parent().show();
});
$("#pedidos-panel .btn-group .btn:first").click();

$("#presupuestos-panel .btn-group .btn").on("click", function(){
	var showClass = $(this).attr("data-show-class");
	$("#presupuestos-panel .panel").parent().hide();
	$("#presupuestos-panel .panel."+showClass).parent().show();
});
$("#presupuestos-panel .btn-group .btn:first").click();

$("#preguntas-panel .btn-group .btn").on("click", function(){
	var showClass = $(this).attr("data-show-class");
	$("#preguntas-panel .question-DONE").parent().hide();
	$("#preguntas-panel .question-PENDING").parent().hide();
	$("#preguntas-panel ."+showClass).parent().show();
});
$("#preguntas-panel .btn-group .btn:first").click();


$(".make-question").on("click", function(){
	$(this).parents(".budget-response").find("form").show();
});