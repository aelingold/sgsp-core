function showPanel(selector){
	$(".dashboard-panel").hide();
	$(selector).show();
}

$(".nav-pills li").on("click", function(){
	$(".nav-pills li").removeClass("active");
	$(this).addClass("active");
});
