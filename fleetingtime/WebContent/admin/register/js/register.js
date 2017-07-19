$(function(){
	
});

function submit(){
	var userName = $('#userName').val();
	var password = $('#password').val();
	var email = $('#email').val();
	var phoneNum = $('#phoneNum').val();

	var param = {
			userName : userName,
		    password : password,
		    email : email,
		    phoneNum : phoneNum
	};
	alert(JSON.stringify(param));
	$.ajax({
		dataType : "json",
		type : "POST",
		data : param,
		url : window.contextPath + "/user/register.action",
		success : function(data) {
			alert(JSON.stringify(data));
		}
	});
}