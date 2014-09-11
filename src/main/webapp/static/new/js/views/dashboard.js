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
}
