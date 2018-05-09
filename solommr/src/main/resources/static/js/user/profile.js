function hideAll() {
	$("#userProfile").hide();
	$("#verMyStats").hide();
	$("#verEditProfile").hide();
	$("#verMyGameProfile").hide();
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
			hideAll();
			$('#verMyStats').html(data);
			$("#verMyStats").show();
		},
		error : function(e) {
			alert('Ups. Al parecer tu perfil de Steam no esta publico.');
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

$("#btnEditGameProfile").click(function() {
	hideAll();
	var selected = getGameSelected();
	if (selected == '0') {
		alert('Seleccionar Juego.');
		return;
	}

	$.ajax({
		type : "GET",
		url : "/solommr/user/profile/editMyGameProfile",
		data : {
			gameId : selected
		},
		success : function(data) {
			$("#idMyGameProfile").html(data);
			hideAll();
			$("#verMyGameProfile").show();
		},
		error : function(e) {
			alert('E');
		}
	});
});

$(".checkbox-inline").click(function() {
	var checkbox = document.querySelector('input[type="checkbox"]');
	alert(checkbox.value);
});

function btnUpdateGameProfile(){
	var selected = getGameSelected();
	if (selected == '0') {
		alert('Seleccionar Juego.');
		return;
	}
	var nickName = $('#nickName').val();
	var celular = $('#celular').val();
	var description = $('#description').val();
	var rol = (function() {
        var a = [];
        $("#rol:checked").each(function() {
            a.push(this.value);
        });
        return a;
    });

	$.ajax({
		type : "POST",
		url : "/solommr/user/profile/updateGameProfile",
		data : {
			gameId : selected,
			nickname : nickName,
			celular : celular,
			description : description,
			roles : rol
		},
		success : function(data) {
			alert('Actualizado');
		},
		error : function(e) {
			alert('Error');
		}
	});
}