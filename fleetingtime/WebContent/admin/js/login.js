var registerWin;
$(function(){
	
});

function login(){
	var loginNo = $('#loginNo').val();
	var password = $("#password").val();
	var rand = $("#rand").val();
	var param = {loginNo:loginNo,password:password,rand:rand};
	alert(JSON.stringify(param));
	$.ajax({
		dataType : "json",
		type : "POST",
		data:param,
		url : window.contextPath+"/user/login.action",
		success : function(data) {
			alert(JSON.stringify(data));
			alert("success");
			window.location.href=window.contextPath + "/showUser.jsp";
		}
	});	
}

function register(){
	registerWin = $("#registerWin").dialog({
		title:"Register",
		resizable : false,
		top: 40,
	    width: 600,    
	    height: 600,    
	    closed: false,    
	    cache: false,    
	    modal: true
	});
	$("#registerFrame").attr("src",window.contextPath +"/register/register.jsp");
	registerWin.dialog("open");
	
}

function getAllUsers(){
	$.ajax({
		dataType : "json",
		type : "POST",
		url : window.contextPath+"/user/allUsers.action",
		success : function(data) {
			//alert(JSON.stringify(data));
		}
	});
}
