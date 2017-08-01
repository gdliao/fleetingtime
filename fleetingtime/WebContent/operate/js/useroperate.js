$(function(){
	
});

function showPage(_t){
	$("#mainFrame").attr("src",_t + ".html");
}

function logout(){
	if(confirm('确定注销吗？')){
		$.ajax({
			dataType : "json",
			type : "POST",
			async:false,
			url : window.contextPath+"/user/logout.action",
			success : function(data) {
				if(data.result=="success"){
					top.location.href=window.contextPath + "/index.html";
				}else{
					alert("注销失败，错误代码："+data.errorCode+",错误原因："+data.errorMsg);
				} 
			}
		});
	}
}

function search(){
	$("#mainFrame").attr("src","searchList.html?keywords="+$("#searchText").val());
}

function openDialog(){
	$('#userInfoDialog').modal();
}

function commit(){
	if(confirm('修改信息需要重新登录。是否重新登录？')){

		var param = {
				userId : $("#userId").val(),
				userName : $("#userName").val(),
				email : $("#email").val()
		};
		if(""!=$("#comfirmPassword").val()){
			param["password"]= $("#comfirmPassword").val();
		}
		$.ajax({
			dataType : "json",
			type : "POST",
			async:false,
			data:param,
			url : window.contextPath+"/user/updateUserInfo.action",
			success : function(data) {
				//alert(JSON.stringify(data));
				if(data.result=="success"){
					$.ajax({
						dataType : "json",
						type : "POST",
						async:false,
						url : window.contextPath+"/user/logout.action",
						success : function(data2) {
							if(data2.result=="success"){
								top.location.href=window.contextPath + "/index.html";
							}else{
								alert("注销失败，错误代码："+data2.errorCode+",错误原因："+data2.errorMsg);
							} 
						}
					});
				}else{
					alert("修改用户信息失败，错误代码："+data.errorCode+",错误原因："+data.errorMsg);
				}
			}
		});
	}
}