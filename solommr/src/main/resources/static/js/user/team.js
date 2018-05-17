function hideAll() {
	$("#csgoBox").hide();
	$("#dotaBox").hide();
	$("#searchTeamBox").hide();
	$("#searchTeamResult").hide();
	$("#idMostrarResultado").hide();
	$("#teamProfile").hide();
	$("#idMyTeam").hide();
	$("#reclutar").hide();
	$("#reclutarResult").hide();
	$("#buildTeam2").hide();
}

$(document).ready(function() {
	// Clean
	hideAll();
});

$("#btnMyTeam").click(function() {
	hideAll();
	var selected = getGameSelected();
	if (selected == '0') {
		alert('Seleccionar Juego.');
		return;
	}

	$.ajax({
		type : "GET",
		url : "/solommr/user/team/myTeam",
		data : {
			gameId : selected
		},
		success : function(data) {
			$('#idMyTeam').html(data);
			hideAll();
			$("#idMyTeam").show();
		},
		error : function(e) {
			$('#idMyTeam').html('Al parecer hubo un error.');
			hideAll();
			$("#idMyTeam").show();
		}
	});
});

$("#btnSearchTeam").click(function() {
	hideAll();
	var selected = getGameSelected();
	// return;
	if (selected == '0') {
		alert('Seleccionar Juego.');
		return;
	}
	$("#searchTeamBox").show();
	// Clean Text Field
	$("#teamName").val('');
});

// BUSCAR EQUIPO
$("#btnDoSearch").click(function() {
	var selected = getGameSelected();
	if (selected == '0') {
		alert('Seleccionar Juego.');
		return;
	}
	var teamName = getTeamName();
	if (teamName == '') {
		alert('Ingrese Nombre/Id del Equipo.');
		return;
	}

	$.ajax({
		type : "POST",
		url : "/solommr/user/team/search",
		data : {
			gameId : selected,
			criteria : teamName
		},
		success : function(data) {
			$('#idTeamSearchResult').html(data);
			hideAll();
			$("#idMostrarResultado").show();
		},
		error : function(e) {
			$('#idTeamSearchResult').html('No se encontraron coincidencias.');
			hideAll();
			$("#idMostrarResultado").show();
		}
	});

});

function getGameSelected() {
	var e = document.getElementById("criteria");
	return e.options[e.selectedIndex].value;
}

function getTeamName() {
	return $('#teamName').val();
}

function getTeamProfile(teamId) {
	var selected = getGameSelected();
	if (selected == '0') {
		alert('Seleccionar Juego.');
		return;
	}
	$.ajax({
		type : "POST",
		url : "/solommr/user/team/profile",
		data : {
			gameId : selected,
			criteria : teamId
		},
		success : function(data) {
			$('#idProfile').html(data);
			hideAll();
			$("#teamProfile").show();
		},
		error : function(e) {
			$('#idProfile').html('Error al cargar Equipo.');
			hideAll();
			$("#teamProfile").show();
		}
	});
}

$("#criteria").on('change', function() {
	var selected = getGameSelected();
	if (selected == '0') {
		hideAll();
	}
});

function reclutar() {
	$("#myTeamContent").hide();
	$("#reclutar").show();
}

function btnDoSearchRecruitment() {
	var selected = getGameSelected();
	if (selected == '0') {
		alert('Seleccionar Juego.');
		return;
	}
	var el = document.getElementById("pais");
	var pais = el.options[el.selectedIndex].value;

	var edad = $('#edad').val();
	var nickName = $('#nickName').val();
	var email = $('#email').val();
	var rol = (function() {
		var a = [];
		$("#rol:checked").each(function() {
			a.push(this.value);
		});
		return a;
	});

	var e = document.getElementById("estado");
	var estado = e.options[e.selectedIndex].value;

	$.ajax({
		type : "POST",
		url : "/solommr/user/team/reclutarSearch",
		data : {
			pais : pais,
			edad : edad,
			nickName : nickName,
			email : email,
			rol : rol,
			estado : estado,
			gameId : selected
		},
		success : function(data) {
			$('#idReclutarResult').html(data);
			$("#reclutarResult").show();
		},
		error : function(e) {
			$('#idReclutarResult').html('No se encontraron coincidencias.');
			hideAll();
			$("#reclutarResult2").show();
		}
	});
}

function btnBuildTeam() {
	var selected = getGameSelected();
	if (selected == '0') {
		alert('Seleccionar Juego.');
		return;
	}
	$.ajax({
		type : "GET",
		url : "/solommr/user/team/build",
		data : {},
		success : function(data) {
			$("#showMesage").hide();
			$('#idBuildTeam').html(data);
			$("#buildTeam2").show();
		},
		error : function(e) {
			alert('error');
		}
	});
}

function btnDoBuildTeam() {
	var selected = getGameSelected();
	if (selected == '0') {
		alert('Seleccionar Juego.');
		return;
	}
	var name = $('#name').val();
	var shortName = $('#shortName').val();
	var description = $('#description').val();
	var el = document.getElementById("pais");
	var pais = el.options[el.selectedIndex].value;
	$.ajax({
		type : "POST",
		url : "/solommr/user/team/build",
		data : {
			name : name,
			shortName : shortName,
			description : description,
			country : pais,
			gameId : selected
		},
		success : function(data) {
		},
		error : function(e) {
		}
	});
}

function getPlayerProfile(playerId) {
	if (playerId === 'undefined' && playerId === null) {
		alert('Error al mostrar perfil');
		return;
	}

	var selected = getGameSelected();
	if (selected == '0') {
		alert('Seleccionar Juego.');
		return;
	}

	window.location.href = "/solommr/user/profile/userPublicProfileByGame?gameId="
			+ selected
			+ "&userId="
			+ playerId
			+ "&comesFromRecruitment="
			+ true;

}

function recruitPlayer(playerId) {
	if (playerId === 'undefined' && playerId === null) {
		alert('Error al mostrar perfil');
		return;
	}

	var selected = getGameSelected();
	if (selected == '0') {
		alert('Seleccionar Juego.');
		return;
	}

	$.ajax({
		type : "POST",
		url : "/solommr/user/team/recruitPlayer",
		data : {
			userId : playerId,
			gameId : selected
		},
		success : function(data) {
			if (data.msg === 'ok') {
				alert('Solicitud Enviada');
			} else {
				alert('Error al Reclutar');
			}
		},
		error : function(e) {
			alert('Error al Reclutar');
		}
	});
}

function deleteTeam(){
	
	var isLeader = $('#isLeader').val();
	var teamId = $('#teamId').val();
	
	alert('eliminar ' + isLeader + ' ' + teamId);
	
	var selected = getGameSelected();
	if (selected == '0') {
		alert('Seleccionar Juego.');
		return;
	}

	$.ajax({
		type : "POST",
		url : "/solommr/user/team/deleteTeam",
		data : {
			gameId : selected,
			isLeader : isLeader,
			teamId : teamId,
			userId : null
		},
		success : function(data) {
			if (data.msg === 'ok') {
				alert('Team Eliminado');
			} else {
				alert('Error');
			}
		},
		error : function(e) {
			alert('Error al Eliminar Equipo');
		}
	});
}