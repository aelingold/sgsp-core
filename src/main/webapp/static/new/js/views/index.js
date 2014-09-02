$(".home-service-option").on("click", function(){
	var serviceType = $(this).attr("data-code");
	switch(serviceType){
		case "GAS":
			$(".intro-text").animate({"left":"-150%"}, 1000);			
			$("#GAS-budget-form").css("right","-150%");
			$("#GAS-budget-form").show();
			$("#GAS-budget-form").animate({"right":"0%"}, 1000);
			break;
		case "PIN":
			$(".intro-text").animate({"left":"-150%"}, 1000);			
			$("#PIN-budget-form").css("right","-150%");
			$("#PIN-budget-form").show();
			$("#PIN-budget-form").animate({"right":"0%"}, 1000);
			break;			
		default:
			break;
	}
});

$(".changeServiceType").on("click", function(){
	var serviceType = $($(this).parents("form")[0]).find("input[name='workAreaCode']").val();
	
	$(".intro-text").animate({"left":"0%"}, 1000);
	$("#"+serviceType+"-budget-form").animate({"right":"-150%"}, 1000, function(){
		$("#"+serviceType+"-budget-form").hide();
	});
});
