function showSelectService(){
	$(".intro-text").animate({"left":"0%"}, 1000);
}
function hideSelectService(){
	$(".intro-text").animate({"left":"-150%"}, 1000);
}

function showBudgetForm(serviceType){
	$("#"+serviceType+"-budget-form").css("right","-150%");
	$("#"+serviceType+"-budget-form").show();
	$("#"+serviceType+"-budget-form").animate({"right":"0%"}, 1000);
}
function hideBudgetForm(serviceType){
	$("#"+serviceType+"-budget-form").animate({"right":"-150%"}, 1000, function(){
		$("#"+serviceType+"-budget-form").hide();
	});
}

$(".home-service-option").on("click", function(){
	var serviceType = $(this).attr("data-code");
	switch(serviceType){
		case "GAS":
			hideSelectService()	
			showBudgetForm(serviceType);
			break;
		case "PIN":
			hideSelectService()		
			showBudgetForm(serviceType);
			break;
		case "PLO":
			hideSelectService()		
			showBudgetForm(serviceType);
			break;
		case "ELE":
			hideSelectService()		
			showBudgetForm(serviceType);
			break;	
		case "CER":
			hideSelectService()		
			showBudgetForm(serviceType);
			break;
		case "ALB":
			hideSelectService()		
			showBudgetForm(serviceType);
			break
		case "FLE":
			hideSelectService()		
			showBudgetForm(serviceType);
			break
		case "SAA":
			hideSelectService()		
			showBudgetForm(serviceType);
			break
		case "SLB":
			hideSelectService()		
			showBudgetForm(serviceType);
			break
		case "SRP":
			hideSelectService()		
			showBudgetForm(serviceType);
			break			
		default:
			break;
	}
});

$(".changeServiceType").on("click", function(){
	var serviceType = $($(this).parents("form")[0]).find("input[name='workAreaCode']").val();
	showSelectService();
	hideBudgetForm(serviceType);	
});

$("select[name='stateCode']").change(function() {
	var stateCode = $(this).val();
	var citiesCombo = $(this).parents(".row").find("select[name='cityCode']");
	$.ajax({
	  type: "GET",
	  url: basePath+"cities/state/"+stateCode
	}).done(function(data) {
		var citiesHTML = "";
		if (data){
			for(var i=0;i<data.length;i++){
				var code = data[i].code;
				var desc = data[i].description;
				citiesHTML += "<option value='"+code+"'>"+desc+"</option>";
			}
		}
		$(citiesCombo).html(citiesHTML);
	});
});