function selectAllWorkAreas(){
	$('.workAreaCheks').attr('checked',true);
}

function verifyUserTypeChecked(){
	
	console.log($("input[name='userType']").val() );
	
	if ($("input[name='userType']:checked").val() == "professional"){
		$(".pro-field").show();
	}else {
		$(".pro-field").hide();
	}
}

$("input[name='userType']").change(function() {
    verifyUserTypeChecked();
});

verifyUserTypeChecked();

