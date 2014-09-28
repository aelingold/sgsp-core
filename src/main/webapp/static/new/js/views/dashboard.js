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
