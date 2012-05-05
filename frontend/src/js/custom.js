// Load the SDK Asynchronously
var me = null;
var accessToken = null;
var serverUrl = "http://voyzala.appspot.com/";

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
		pageLoad();
	}
	else{
		$("#btn-login .btn-text").text("Login");
	}
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

function loginRequired(){
	if(!me){
		$.mobile.changePage("#home");
		login();
	}
}

function pageChangeNew(){
	if(window.location.hash != "#new")
		return;
	
	var url = '/me/friends?accessToken='+accessToken;
	console.log(url);
	FB.api(url, function(data){
		if(data.error){
			return;
		}
		console.log(data);
		for (var i = 0; i < data.data.length; i++) {
			var friend = data.data[i];
			var content = "<li><a href='javascript:startGame("+friend.id+");'>";
				content += "<img src='https://graph.facebook.com/"+friend.id+"/picture' /> ";
				content += friend.name+"</a></li>";
			$("#list-friends").append(content);
		}
	});
}

function startGame(friendId){
	var input = {
		userId: me.id,
		friendId: friendId
	};
	$.get(serverUrl+"game", input, function(data){
		console.log(data);
	});
}

$(document).ready(function(){
	
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
});