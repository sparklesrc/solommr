$(document).ready(function() {
	$("#csgoBox").hide();
	$("#dotaBox").hide();
	$("#searchTeamBox").hide();
	$("#searchTeamResult").hide();
	$("#buildTeamBox").hide();
	$("#idMostrarResultado").hide();
});

$("#btnMyTeams").click(function() {
	$("#searchTeamBox").hide();
	$("#buildTeamBox").hide();
	$("#idMostrarResultado").hide();
	var selected = getGameSelected();
	if(selected == '0'){
		alert('Seleccionar Juego.');
		return;
	}
});

$("#btnBuildTeam").click(function() {
	$("#searchTeamBox").hide();
	$("#idMostrarResultado").hide();
	var selected = getGameSelected();
	if(selected == '0'){
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
	$("#buildTeamBox").hide();
	var selected = getGameSelected();
	if(selected == '0'){
		alert('Seleccionar Juego.');
		return;
	}
	$("#searchTeamBox").show();
	$("#teamName").val('');
	$('#idTeamSearchResult').hide();
});

// BUSCAR EQUIPO
$("#btnDoSearch").click(function() {
	var selected = getGameSelected();
	if(selected == '0'){
		alert('Seleccionar Juego.');
		return;
	}
	var teamName = getTeamName();
	if(teamName == ''){
		alert('Ingrese Nombre/Id del Equipo.');
		return;
	}

	$.ajax({
		  type: "POST",
		  url: "/solommr/user/searchTeam",
		  data: {
			  		gameId : selected,
			  		criteria : teamName
		  		},
		  success: function (data){
			  $('#showSearchTeamResult').html(data);
			  $("#idMostrarResultado").show();
		  },
		  error: function (e){
			  alert('error ' + e);
		  }
		});
	
});





$("#findTeamCSGO").click(function() {
	$("#dotaBox").hide();
	$("#csgoBox").show();
});

$("#findTeamDota").click(function() {
	$("#csgoBox").hide();
	$("#dotaBox").show();
});

function getGameSelected(){
	var e = document.getElementById("criteria");
	return e.options[e.selectedIndex].value;;
}

function getTeamName(){
	return $('#teamName').val();;
}

function getTeamProfile(teamId){
	alert('Id de Equipo ' +teamId);
}

$("#tblTeamSearchResult tr").click(function() {
    window.location = $(this).find('td:eq(3)').attr("href");
});

function rowClicked(value) {
    location.href = "/myurl?param=" + value;
}