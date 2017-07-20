$(function(){
	
});

function addUser(){
	var userName = $("#userName").val();
	var phoneNumber = $("#phoneNumber").val();
	var email = $("#email").val();
	
	var param = {
			userName : userName,
			phoneNumber :phoneNumber,
			email : email
	}
	alert(JSON.stringify(param));
	$.ajax({
		dataType : "json",
		type : "POST",
		data : {param : JSON.stringify(param)},
		async:false,
		url : window.contextPath+"/user/addUser.action",
		success : function(data) {
			alert(JSON.stringify(data));
		}
	});
}