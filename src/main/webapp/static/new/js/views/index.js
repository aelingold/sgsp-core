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
		case "PLO":
			$(".intro-text").animate({"left":"-150%"}, 1000);			
			$("#PLO-budget-form").css("right","-150%");
			$("#PLO-budget-form").show();
			$("#PLO-budget-form").animate({"right":"0%"}, 1000);
			break;
		case "ELE":
			$(".intro-text").animate({"left":"-150%"}, 1000);			
			$("#ELE-budget-form").css("right","-150%");
			$("#ELE-budget-form").show();
			$("#ELE-budget-form").animate({"right":"0%"}, 1000);
			break;	
		case "CER":
			$(".intro-text").animate({"left":"-150%"}, 1000);			
			$("#CER-budget-form").css("right","-150%");
			$("#CER-budget-form").show();
			$("#CER-budget-form").animate({"right":"0%"}, 1000);
			break;
		case "ALB":
			$(".intro-text").animate({"left":"-150%"}, 1000);			
			$("#ALB-budget-form").css("right","-150%");
			$("#ALB-budget-form").show();
			$("#ALB-budget-form").animate({"right":"0%"}, 1000);
			break
		case "FLE":
			$(".intro-text").animate({"left":"-150%"}, 1000);			
			$("#FLE-budget-form").css("right","-150%");
			$("#FLE-budget-form").show();
			$("#FLE-budget-form").animate({"right":"0%"}, 1000);
			break
		case "SAA":
			$(".intro-text").animate({"left":"-150%"}, 1000);			
			$("#SAA-budget-form").css("right","-150%");
			$("#SAA-budget-form").show();
			$("#SAA-budget-form").animate({"right":"0%"}, 1000);
			break
		case "SLB":
			$(".intro-text").animate({"left":"-150%"}, 1000);			
			$("#SLB-budget-form").css("right","-150%");
			$("#SLB-budget-form").show();
			$("#SLB-budget-form").animate({"right":"0%"}, 1000);
			break			
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
