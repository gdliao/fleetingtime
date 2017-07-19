$(function(){
	
});

function commit(){
	var title = $("#title").val();
	var date = $("#date").val();
	var content = $("#content").val();
	var userId = $("#userId").val();
	
	
	var infoPublish = {
			publishTitle:title,
			publishTime:new Date(date),
			infoDes:content,
			userId:userId
			};
	//alert(JSON.stringify(infoPublish));//return;
	
	$.ajax({
		dataType : "json",
		type : "POST",
		data:infoPublish,
		url : window.contextPath+"/publication/add.action",
		success : function(data) {
			if(data.result=="success"){
				alert("success");
			}else{
				alert(data.errorMsg);
			} 
		}
	});	
}