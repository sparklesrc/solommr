$(document).ready(function() {
	$("#csgoBox").hide();
	$("#dotaBox").hide();
	$("#searchTeamBox").hide();
	$("#searchTeamResult").hide();
	$("#buildTeamBox").hide();
	
	
});

$("#btnMyTeams").click(function() {
	$("#searchTeamBox").hide();
	$("#buildTeamBox").hide();
	var selected = getGameSelected();
	if(selected == '0'){
		alert('Seleccionar Juego.');
		return;
	}
});

$("#btnBuildTeam").click(function() {
	$("#searchTeamBox").hide();
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
		  url: "/user/searchTeam",
		  data: {
			  		gameId : selected,
			  		criteria : teamName
		  		},
		  dataType: 'json',
		  success: function (data){
			  
		  },
		  error: function (e){
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