var userInfo;
$(function(){
	userInfo=parent.userInfo[0];
	initUserInfo();
	initAllRole();
});

function initUserInfo(){
	//alert(JSON.stringify(userInfo));
	$("#userName").textbox('setValue',userInfo.userName);
	$("#phoneNumber").textbox('setValue',userInfo.phoneNumber);
	$("#email").textbox('setValue',userInfo.email);
}

function initAllRole(){
	$.ajax({
		dataType : "json",
		type : "POST",
		async:false,
		url : window.contextPath+"/userRole/queryRoleList.action",
		success : function(data) {
			alert(JSON.stringify(data));
		}
	});
}
