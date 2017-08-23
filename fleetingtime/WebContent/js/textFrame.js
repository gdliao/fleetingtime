$(function(){
	queryActivity();
});

function showActList(_t){
	if(_t==1){
		$("#actDiv").show();
	}else{
		$("#actDiv").hide();
		/*隐藏字数限制 并清空*/
		$("#limitDiv").hide();
		$("#content").removeAttr("maxlength");
	}
}
function queryActivity(){
	$.ajax({
		dataType : "json",
		type : "POST",
		async:false,
		url : window.contextPath+"/publication/queryActivity.action",
		success : function(data) {
			if(data.result=="success"){
				//alert(JSON.stringify(data.data));
				var actList = data.data;
				var html="";
				for(var i=0;i<actList.length;i++){
					html+="<option value='"+actList[i].actId+"' data-limit='"+actList[i].wordLimit+"'>"+actList[i].actName+"</option>";
				}
				$("#actList").append(html);
			}else{
				alert(data.errorMsg);
			} 
		}
	});
}

function getLimit(){
	var maxLength = $("#actList option:selected").attr("data-limit");
	$("#content").attr("data-limit",maxLength);
	$("#maxLimit").html(maxLength);
	
	$("#rest").html(maxLength-$("#content").val().length);
	
	if($("#actList").val()=="0"){
		$("#limitDiv").hide();
	}else{
		$("#limitDiv").show();
	}
}

function commit(){
	if(null==null||$("#title").val()==""){
		alert("标题不能为空。");
		return;
	}
	var ifShare = $("#ifJoinAct").val();
	
	var infoPublish = {
			publishTitle:$("#title").val(),
			//publishTime:new Date($("#date").val()),
			infoDes:$("#content").val(),
			userId:$("#userId").val(),
			ifShare :ifShare
			};
	if(ifShare==1){
		infoPublish['actId'] = $("#actList").val();
		
		var limit = $("#content").attr("data-limit");
		var count = $("#content").val().length;
		if(count>limit){
			alert("已超过本次活动的限制字数"+(count-limit)+"个字，修改后重新提交。");
			return;
		}
		if($("#actList").val()=="0"){
			alert("想要分享需要选择一个您想要参加的活动。");
			return;
		}
	}
	alert(JSON.stringify(infoPublish));//return;
	$.ajax({
		dataType : "json",
		type : "POST",
		data:infoPublish,
		async:false,
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

function textCounter(_t) {//field, countfield, maxlimit
	
	var length = $(_t).val().length;
	var maxlimit = $(_t).attr("data-limit");
	var rest = maxlimit-length;
	if($("#ifJoinAct").val()==1){
		$(_t).attr("maxlength",maxlimit);
	}
	$("#rest").html(rest);
	
	$("#count").html(length);
	/*if(maxlimit!=0){//0为不设置字数限制
		// 函数，3个参数，表单名字，表单域元素名，限制字符；
		if (field.value.length > maxlimit){
			// 如果元素区字符数大于最大字符数，按照最大字符数截断；
			field.value = field.value.substring(0, maxlimit);
		}
		else{
			// 在记数区文本框内显示剩余的字符数；
			countfield.value = maxlimit - field.value.length;
		}
	}*/
}  

function toOperate(){
	top.location.href=window.contextPath+"/operate/useroperate.html";
}