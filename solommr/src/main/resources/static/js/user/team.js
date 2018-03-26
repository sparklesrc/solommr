function hideAll(){
	$("#csgoBox").hide();
	$("#dotaBox").hide();
	$("#searchTeamBox").hide();
	$("#searchTeamResult").hide();
	$("#buildTeamBox").hide();
	$("#idMostrarResultado").hide();
	$("#teamProfile").hide();
	$("#idMyTeam").hide();
	$("#reclutar").hide();
	$("#reclutarResult").hide();
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

$("#btnBuildTeam").click(function() {
	hideAll();
	var selected = getGameSelected();
	if (selected == '0') {
		alert('Seleccionar Juego.');
		return;
	}
	$("#buildTeamBox").show();
});

$("#btnDoBuildTeam").click(function() {
	alert('Aun Estamos construyendo esta funcionalidad.');
	return;
});

$("#btnSearchTeam").click(function() {
	hideAll();
	var selected = getGameSelected();
//	return;
	if (selected == '0') {
		alert('Seleccionar Juego.');
		return;
	}
	$("#searchTeamBox").show();
	// Clean Text Field
	$("#teamName").val('');
});

// BUSCAR EQUIPO
$("#btnDoSearch").click(
		function() {
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
					$('#idTeamSearchResult').html(
							'No se encontraron coincidencias.');
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

function reclutar(){	
	$("#myTeamContent").hide();
	$("#reclutar").show();
}

function btnDoSearchRecruitment(){	
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
	var rol = $('#rol').val();

	$.ajax({
		type : "POST",
		url : "/solommr/user/team/reclutarSearch",
		data : {
			pais : pais,
			edad : edad,
			nickName : nickName,
			email : email,
			rol : null
		},
		success : function(data) {
			$('#idReclutarResult').html(data);
			$("#reclutarResult").show();
		},
		error : function(e) {
			$('#idReclutarResult').html(
					'No se encontraron coincidencias.');
			hideAll();
			$("#reclutarResult2").show();
		}
	});
}
