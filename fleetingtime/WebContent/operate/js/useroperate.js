$(function(){
	
});

function showPage(_t){
	var pageName = _t;
	$("#mainFrame").attr("src",pageName+".html");
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
					$.messager.alert('提示',"注销失败，错误代码："+data.errorCode+",错误原因："+data.errorMsg,'warning');
				} 
			}
		});
	}
}

function search(){
	$("#mainFrame").attr("src","searchList.html?keywords="+$("#searchText").val());
}