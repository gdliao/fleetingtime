var registerWin;
$(function(){
	enterPress();
});

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

function login(){
	$('#loading').loading('open');
	var loginNo = $('#loginNo').val();
	var password = $("#password").val();
	var rand = $("#rand").val();

	if(!checkRule.loginNo(loginNo)){
		$('#loading').loading('close');
		return;
	}
	
	var param = {loginNo:loginNo,password:password,rand:rand};

	$.ajax({
		dataType : "json",
		type : "POST",
		data:param,
		url : window.contextPath+"/login/login.action",//
		success : function(data) {
			$('#loading').loading('close');
			if(data.result=="success"){
				window.location.href=window.contextPath + "/index.jsp";
			}else{
				reloadImage();
				$.messager.alert('提示',"登录失败，错误代码："+data.errorCode+",错误原因："+data.errorMsg,'warning');
			} 
		}
	});	
}

function enterPress(){
	$('#rand').textbox({
		inputEvents : $.extend({}, $.fn.textbox.defaults.inputEvents, {
			keypress : function(e) {
				if (e.keyCode == 13) {
					login();
				}
			}
		})
	});
}

var checkRule={
		loginNo:function(_str){
			if(_str.indexOf('@')>=0){
				
				var reg = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
				if(!reg.test(_str)){
					$.messager.alert('提示',"邮箱格式错误！",'warning');
					return false;
				}
				return true;
			}else{
				
				var mobile = /^1[3|5|8|4|7]\d{9}$/;
				var phone = /^0\d{2,3}-?\d{7,8}$/;
				if (!mobile.test(_str) && !phone.test(_str)) {
					$.messager.alert("提示", "电话格式错误！", "warning");
					return false;
				}
				return true;
			}
		}
};
