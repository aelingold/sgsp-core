function selectAllWorkAreas(){
	$('.workAreaCheks').attr('checked',true);
}

function verifyUserTypeChecked(){	
	if ($("input[name='isProfessional']:checked").length > 0){
		$(".pro-field").show();
	}else {
		$(".pro-field").hide();
	}
}

$("input[name='isProfessional']").change(function() {
    verifyUserTypeChecked();
});

verifyUserTypeChecked();

