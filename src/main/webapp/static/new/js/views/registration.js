function selectAllWorkAreas(){
	$('.workAreaCheks').attr('checked',true);
}

function verifyUserTypeChecked(){	
	if ($("input[name='userType']:checked").length > 0){
		$(".pro-field").show();
	}else {
		$(".pro-field").hide();
	}
}

$("input[name='userType']").change(function() {
    verifyUserTypeChecked();
});

verifyUserTypeChecked();

