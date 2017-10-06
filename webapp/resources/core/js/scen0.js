var shipwidth = 0;
var scenewidth = 0;
var ufid = 0;
var pfid = 0;
var ustuck = [];
var pstuck = [];
var username;
var playername;
var intervalId = 0;

var bgheight = 0;

var alertflag = true;

jQuery(document).ready(function($) {
	scen0init();
});

function scen0init(){

	
	shipwidth = $("#usership").width();
	var borderwidth = $("#bg").css("border-left-width")
	scenewidth = $("#bg").width() - borderwidth.substr(0, borderwidth.length - 2);
	username = $("#username").val();
	playername = $("#playername").val();
	
	bgheight = $("#bg").height();
	
	intervalId = setInterval(function(){
		gamebeat();
	 },10);
	
	$(window).on("keyup", function(e) {
		var speed = 20;
		var ajaxflag = false;
		
		if(e.keyCode == 37) { // left
			if($("#usership").position().left < speed){
				 $("#usership").animate({
				      left: 0
				    },50);
			}else{
				 $("#usership").animate({
				      left: "-=" + speed
				    },50);
			}
			ajaxflag = true;
		  }
 		  else if (e.keyCode == 39) { // right
			if ($("#usership").position().left > scenewidth - shipwidth - speed) {
				$("#usership").animate({
					left : scenewidth - shipwidth
				}, 50);
			} else {
				$("#usership").animate({
					left : "+=" + speed
				}, 50);
			}
			ajaxflag = true;
		}else if(e.keyCode == 90) {//z
			var uf = "<div id='uf" + ufid + "' class='fire'></div>";
			$("#bg").append(uf);
			var uoffset = $("#usership").offset();
			$("#uf" + ufid).css("left", uoffset.left + shipwidth / 2 -2);
			
			ustuck.push(ufid);
			$("#uf" + ufid).animate({
				top : 0
			}, 2000, "linear" , function() {
			    // Animation complete.
				
				$("#uf" + ustuck.shift()).remove();
			  });
			ajaxflag = true;
			ufid++;
		}
		
		if(ajaxflag){
			userajax(e.keyCode);
		}
	});
	
}

function userajax(code){

	var search = {}
	search["username"] = username;
	search["code"] = code;
	search["left"] = $("#usership").offset().left;
	
	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : "/s/usercontroll",
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

function gamebeat(){
	var search = {}
	search["username"] = username;
	search["playername"] = playername;
	search["left"] = $("#usership").offset().left;
	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : "/s/gamebeat",
		data : JSON.stringify(search),
		dataType : 'json',
		timeout : 100,
		success : function(data) {
			playercontroll(data);
		},
		error : function(e) {
			clearInterval(intervalId);
			console.log('clearInterval: ' + intervalId);
	    	intervalId = 0;
	    	if(alertflag){
				alertflag = false;
		    	alert("You Win!");
				window.location = "/";
			}
		},
		done : function(e) {

		}
	});
}

function playercontroll(data){
	if(null == data || null == data.fire || null == data.left){
		if(alertflag){
			alertflag = false;
			alert("You Win!");
		}
		window.location = "/";
	}
	if(data.fire){
		console.log("player fire");
		
		var uf = "<div id='pf" + pfid + "' class='fire'></div>";
		$("#bg").append(uf);
		var poffset = $("#playership").offset();
		$("#pf" + pfid).css({left: poffset.left + shipwidth / 2 -2, top : 0});
		
		pstuck.push(pfid);
		$("#pf" + pfid).animate({
			top : bgheight
		}, 2000, "linear" , function() {
			var psf = pstuck.shift();
		    // Animation complete.
			var uofs = $("#usership").offset();
			var pofs = $("#pf" + psf).offset();
			
			if(uofs.left < pofs.left && uofs.left + shipwidth > pofs.left){
				if(alertflag){
					alertflag = false;
					ajxyoulost();
				}
				window.location = "/";
			}
			$("#pf" + psf).remove();
		  });
		pfid++;
	}
	$("#playership").css({left: data.left});
	console.log("player left: " + data.left);
}

function ajxyoulost(){
//	var la = "<label id='lalose'> you lose! </label>";

//	$("#bg").append(la);
//	var bgofset = $("#bg").offset();
//	$("#lalose").css({top : bgofset.top / 2, left: bgofset.left /2})
	
	var search = {}
	search["username"] = username;
	search["playername"] = playername;
	search["left"] = $("#usership").offset().left;
	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : "/s/youlose",
		data : JSON.stringify(search),
		dataType : 'json',
		timeout : 100,
		success : function(data) { 
			alert("You lose!");
			//await sleep(3000);
	    	window.location = "/";
		},
		error : function(e) { },
		done : function(e) {

		}
	});
}
