function hideAll() {
	$("#userProfile").hide();
	$("#verMyStats").hide();
	$("#verEditProfile").hide();
}

$(document).ready(function() {
	hideAll();
});

$("#btnMyStats").click(function() {
	hideAll();
	var selected = getGameSelected();
	if (selected == '0') {
		alert('Seleccionar Juego.');
		return;
	}

	$.ajax({
		type : "GET",
		url : "/solommr/user/profile/view",
		data : {
			gameId : selected
		},
		success : function(data) {
			alert(data);
			hideAll();
			$('#verMyStats').html(data);
			$("#verMyStats").show();
		},
		error : function(e) {
			alert('ERROR');
		}
	});
});

$("#btnEditProfile").click(function() {
	hideAll();
	var selected = getGameSelected();
	if (selected == '0') {
		alert('Seleccionar Juego.');
		return;
	}

	$.ajax({
	type : "GET",
	url : "/solommr/user/profile/editProfile",
	success : function(data) {
		$('#idFragmentEditProfile').html(data);
		hideAll();
		$("#verEditProfile").show();
	},
	error : function(e) {
		alert('E');
	}
});

});

$("#btnSaveProfile").click(function() {
	alert('1234');
	alert('Aun Estamos construyendo esta funcionalidad.');
	return;
});

function getGameSelected() {
	var e = document.getElementById("criteria");
	return e.options[e.selectedIndex].value;
}

$("#criteria").on('change', function() {
	var selected = getGameSelected();
	if (selected == '0') {
		hideAll();
	}
});