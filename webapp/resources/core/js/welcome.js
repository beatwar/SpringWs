var searchStage = 0;
var intervalId = 0;
var username = '';
var playername = '';
var userlist = null;

var clickTarget = '';
var submitflag = false;

jQuery(document).ready(function($) {

	$("#search-form").submit(function(event) {

		if(!submitflag){
			// Disble the search button
			enableSearchButton(false);

			// Prevent the form from submitting via the browser.
			event.preventDefault();
			
			searchViaAjax();
		}
		
	});
	
	$(window).on("beforeunload", function() { 
	    if(0 != intervalId){
	    	clearInterval(intervalId);
	    	console.log('clearInterval: ' + intervalId);
	    	intervalId = 0;
	    }
	});
	
});

function heartbeat(){
	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : "/s/heartbeat",
		data : '',
		dataType : 'json',
		timeout : 100,
		success : function(data) {
			console.log("heartbeat SUCCESS: ", data);
			switch(data.code){
			case 200:
				successLogin(data);
				break;
			case 201:
				playername = data.username;
				confirmAlert();
				break;
			case 203:
				waitingstart();
				break;
			case 205:
				gamestart();
				break;
			}
		},
		error : function(e) {
			clearInterval(intervalId);
			console.log('clearInterval: ' + intervalId);
	    	intervalId = 0;
		},
		done : function(e) {

		}
	});
}

function confirmAlert(){
	var json = "<h4>Player: " + playername + "と対戦しますか？</h4><pre>" + "</pre>";
	$('#feedback').html(json);
	$("#search-form").empty();
	var loginlist = '<div class="form-group">';
	loginlist +='<div class="col-sm-offset-2 col-sm-10">';
	loginlist +='<button id="vsYes" onclick="vsConfirm(this); return false;" class="btn btn-default btn-lg">対戦する</button>';
	loginlist +='<button id="vsNo" onclick="vsConfirm(this); return false;" class="btn btn-default btn-lg">対戦しない</button>';
	loginlist +='</div> </div>';
	$("#search-form").append(loginlist);
}

function vsConfirm(vs) {
	var search = {}
	search["username"] = playername;
	if ('vsYes' == vs.id) {
		search["code"] = 1;
	} else {
		search["code"] = 0;
	}
	
	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : "/s/vsConfrm",
		data : JSON.stringify(search),
		dataType : 'json',
		timeout : 100,
		success : function(data) {
			if('vsYes' == vs.id) {
				waitingstart();
			} else {
				successLogin(data)
			}
		},
		error : function(e) {
			
		},
		done : function(e) {

		}
	});
}

function gamestart(){

//	$.ajax({
//		type : "POST",
//		//contentType : "application/json",
//		url : "/s/scen0",
//		data : '',
//		//dataType : 'json',
//		timeout : 100,
//		success : function(data) {
//			clearInterval(intervalId);
//			console.log('clearInterval: ' + intervalId);
//	    	intervalId = 0;
//			$("html").html(data);
//			scen0init();
//		},
//		error : function(e) {
//			clearInterval(intervalId);
//			console.log('clearInterval: ' + intervalId);
//	    	intervalId = 0;
//		},
//		done : function(e) {
//
//		}
//	});
	
	
	var url = "/s/scen0";
//	window.location = url;
	submitflag = true;
	var hidnpara = "<input type='hidden' name='username' value='" + username + "' />";
	hidnpara += "<input type='hidden' name='playername' value='" + playername + "' />";
	$("#search-form").append(hidnpara)
	
	$("#search-form").attr("action", url);
	$("#search-form").attr("method", "post");
	$("form").submit();
}

function waitingstart(){
	$("#search-form").empty();
	var json = "<h4>Waiting for start. </h4><pre></pre>";
	$('#feedback').html(json);
}

function destroy(){
	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : "/s/destroySession",
		data : JSON.stringify(search),
		dataType : 'json',
		timeout : 100,
		success : function(data) {

		},
		error : function(e) {

		},
		done : function(e) {

		}
	});
}

function searchViaAjax() {

	switch(searchStage){
	case 0:
		login();
		break;
	case 1:
		choosePlayer();
		break;
	}
}

function login(){
	var search = {}
	search["username"] = $("#username").val();
	if("" != search["username"]){
		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "/s/api/getSearchResult",
			data : JSON.stringify(search),
			dataType : 'json',
			timeout : 100000,
			success : function(data) {
				console.log("SUCCESS: ", data);
				intervalId = setInterval(function(){
					heartbeat()
				 },3000);
				successLogin(data)
			},
			error : function(e) {
				console.log("ERROR: ", e);
				display(e);
			},
			done : function(e) {
				console.log("DONE");
				enableSearchButton(true);
			}
		});
	}
	
	return;
}

function choosePlayer(bt){
	// Disble the search button
	enableSearchButton(false);
	playername = bt.id.substr(4, bt.id.length)
	var search = {}
	search["username"] = playername;
	$("#search-form").empty();
	if("" != search["username"]){
		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "/s/api/choosePlayer",
			data : JSON.stringify(search),
			dataType : 'json',
			timeout : 100000,
			success : function(data) {
				var json = "<h4>Waiting Player: " + playername + "</h4><pre>" + "</pre>";
				$('#feedback').html(json);
				clearInterval(intervalId);
				console.log('clearInterval: ' + intervalId);
				intervalId = setInterval(function(){
					heartbeat();
				 },10);
			},
			error : function(e) {
				
			},
			done : function(e) {
				console.log("DONE");
			}
		});
	}
	
	
}

function successLogin(data){
	if(data.code == 200){
		
		if(username == ""){
			username = $("#username").val();
		}
		
		userlist = data.result
		
		$("#search-form").empty();
		for(var i = 0; i < userlist.length; i++){
			if(userlist[i].username != username){
				var loginlist = '<div class="form-group">';
					loginlist +='<div class="col-sm-offset-2 col-sm-10">';
					loginlist +='<button id="usr_' + userlist[i].username + '" onclick="choosePlayer(this); return false;" class="btn btn-default btn-lg">' + userlist[i].username + '';
					loginlist +='</button> </div> </div>';
				$("#search-form").append(loginlist);
			}
		}
	}
	
	var json = "<h4>Hello " + username;

	if (userlist !=null && userlist.length > 1) {
		json += ", Choose Player.</h4><pre>"
	} else {
		json += ", No other Player.</h4><pre>"
	}
	json += data.msg + "</pre>";
	$('#feedback').html(json);
}

function enableSearchButton(flag) {
	$("#btn-search").prop("disabled", flag);
	$("input[type='button']").prop("disabled", flag);
}

function display(data) {
	
}