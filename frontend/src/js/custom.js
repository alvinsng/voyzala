// Load the SDK Asynchronously
var me = null;
var accessToken = null;
var serverUrl = "proxy.php";
var gameId = null;

(function(d){
	var js, id = 'facebook-jssdk', ref = d.getElementsByTagName('script')[0];
	if (d.getElementById(id)) {
		return;
	}
	js = d.createElement('script');
	js.id = id;
	js.async = true;
	js.src = "//connect.facebook.net/en_US/all.js";
	ref.parentNode.insertBefore(js, ref);
}(document));
// Init the SDK upon load
window.fbAsyncInit = function() {
	FB.init({
		appId      : '402672649772378', // App ID
		channelUrl : 'channel.html', // Channel File
		status     : true, // check login status
		cookie     : true, // enable cookies to allow the server to access the session
		xfbml      : true  // parse XFBML
	});


	// listen for and handle auth.statusChange events
	FB.Event.subscribe('auth.statusChange', function(response) {
		handleLogin(response);
	});
	FB.Event.subscribe('auth.authResponseChange', function(response) {
		handleLogin(response);
	});
	FB.Event.subscribe('auth.login', function(response) {
		handleLogin(response);
	});
	console.log("loaded fb sdk");
	
}

function handleLogin(response){
	if (response.authResponse) {
		accessToken = response.authResponse.accessToken;
		FB.api('/me', function(data){
			me = data;
			if (data) {
				$("#btn-login .btn-text").text(data.name);
			}
		});
	}
	else{
		$("#btn-login .btn-text").text("Login");
	}
	console.log("handled login");
}

function login(){
	FB.login(function(response) {
		handleLogin(response);
	}, {
		scope: 'friends_about_me'
	});
}
function logout(){
	FB.logout();
	$("#btn-login .btn-text").text("Login");
	
}

function pageChangeNew(){
	var content = $("#list-friends li").html();

	if(content)
		return;
	
	var url = '/me/friends?accessToken='+accessToken;
	console.log(url);
	FB.api(url, function(data){
		console.log(data);
		for (var i = 0; i < data.data.length; i++) {
			var friend = data.data[i];
			var content = "<li><a href='javascript:startGame("+friend.id+");'>";
			content += "<img src='https://graph.facebook.com/"+friend.id+"/picture' /> ";
			content += friend.name+"</a></li>";
			$("#list-friends").append(content);
			if(i > 50)
				break;
		}
		$('#list-friends').listview('refresh');
	});
}

function pageChangeView(){
	var input = {
		script: 'games',
		userId: me.id
	}
	ajax(input, function(data){
		console.log(data);
				
		for(var i = 0; i < games.length; i++){
			$("#list-game").append("<li></li>");
		}
		$("#list-games").listview("refresh");
	});
	
}

function startGame(friendId){
	var input = {
		script: "game",
		userId: me.id,
		friendId: friendId
	};
	
	ajax(input, function(data){
		console.log(data);
		$.mobile.changePage("#game");
		$("#game-word h1").text(data.card.word);
		gameId = data.game.stringKey;
		var cards = data.card.forbiddenWords.split("|");
		$("#game-forbiddenWords").empty();
		for(var i = 0; i < cards.length; i++){
			$("#game-forbiddenWords").append("<li>"+cards[i]+"</li>");
		}
		$("#game-forbiddenWords").listview("refresh");
		$.mobile.changePage("#game");
		
	});
}

function ajax(input, success){
	$.ajax({
		url: serverUrl,
		data: input,
		success: success
	});
}

function pageLoad(page){
	if(page != 'home' && !me){
		$.mobile.changePage("#home");
		return false;
	}
	if(page == 'new'){
		pageChangeNew();
	}
	else if(page == 'game'){
		if(!gameId){
			$.mobile.changePage("#home");
		}
	}
	else if(page == 'view'){
		pageChangeView();
	}
	if(page != 'game')
		gameId = null;
	return true;
}

$(document).ready(function(){
	$( document ).bind( "pagechange", function( event, data ){
		//console.log(data);
		var page = data.toPage[0].dataset.url;
		if(!pageLoad(page)){
			event.preventDefault();
		}
	//var page = data.toPage.split("#").pop();	
	});
	$("#btn-login").click(function(){
		if(me){
			logout();
		}
		else{
			login();
		}
		
	});
	
	$("#btn-game-new").click(function(){
		if(me){
			$.mobile.changePage("#new");
			pageChangeNew();
		}
		else{
			login();
		}
	});
	pageLoad(window.location.hash);
});