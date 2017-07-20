var roomInfo=[];
$(function(){
	initRoomInfo();
	initEquipment();
	initRoomBookingInfo();
});

function initRoomInfo(){
	$('#loading').loading('open');
	//初始化 房间下拉列表
	var roomInfoJson=[];
	$.ajax({
		dataType : "json",
		type : "POST",
		async:false,
		url : window.contextPath+"/booking/queryRoomInfo.action",
		success : function(data) {
			$('#loading').loading('close');
			if(data.result=='success'){
				roomInfoJson=data.data;
				
				var roomInfoHtml="";
				var topRoomInfoHtml="";
				for(var i=0;i<roomInfoJson.length;i++){
					roomInfoHtml=roomInfoHtml+"<option value='"+roomInfoJson[i].roomId+"'>"+roomInfoJson[i].roomName+",可容纳："+roomInfoJson[i].roomCapacity+"人</option>";
					topRoomInfoHtml=topRoomInfoHtml+"<option value='"+roomInfoJson[i].roomId+"'>"+roomInfoJson[i].roomName+"</option>";
					var tempInfo={roomId:roomInfoJson[i].roomId,roomName:roomInfoJson[i].roomName};
					roomInfo.push(tempInfo);
				}
				$("#roomId").append(roomInfoHtml);
				$("#topRoomId").append(topRoomInfoHtml);
			}else{
				$.messager.alert('提示',"查询房间信息异常,错误原因："+data.errorMsg,'warning');
			}
		}
	});	
	
}
function changeRoomId(_roomId){
	
	$("#roomId").find("option[value='"+_roomId+"']").attr("selected",true);
	$("#topRoomId").find("option[value='"+_roomId+"']").attr("selected",true);
	initRoomBookingInfo();
}
function initRoomBookingInfo(){
	$('#loading').loading('open');
	var roomId = $("#roomId").val();
	var bookInfo = [];
	//初始化房间预订信息
	$.ajax({
		dataType : "json",
		type : "POST",
		async:false,
		data: {roomId:roomId},
		url : window.contextPath+"/booking/queryRoomBookingInfo.action",
		success : function(data) {
			$('#loading').loading('close');
			if(data.result=='success'){
				$("#roomInfoTable").find('tr[name=infoTr]').remove();
				var roomInfoHtml = "";
				if(data.errorCode=='0000'){
					bookInfo=data.data;
					
					var roomName="";
					for(var i=0;i<roomInfo.length;i++){
						if(roomInfo[i].roomId==roomId) {roomName=roomInfo[i].roomName;}
					}
					
					for(var i=0;i<bookInfo.length;i++){
						roomInfoHtml=roomInfoHtml+"<tr name='infoTr'><td>"+roomName+"</td><td>"+dateFormate(bookInfo[i].startTime)+"</td><td>"+dateFormate(bookInfo[i].endTime)+"</td><td>"+bookInfo[i].userName+"</td><td>"+(bookInfo[i].startTime<new Date()?"进行中":"预约")+"</td></tr>";
					}
					
				}else{ 
					roomInfoHtml=roomInfoHtml+"<tr name='infoTr'><td style='border-right: 0px;'>所选房间无预约。</td><td colspan='4'></td></tr>";
					//$.messager.alert('提示',data.data,'warning');
				}
				$("#roomInfoTable").append(roomInfoHtml);

				

			}else{
				$.messager.alert('提示',"查询房间预订信息异常,错误原因："+data.errorMsg,'warning');
			}
		}
	});	
	
}

function initEquipment(){
	$('#loading').loading('open');
	var equipmentInfoJson=[];
	$.ajax({
		dataType : "json",
		type : "POST",
		async:false,
		url : window.contextPath+"/booking/queryEquipment.action",
		success : function(data) {
			$('#loading').loading('close');
			if(data.result=='success'){
				equipmentInfoJson=data.data;
				
				var equipmentInfoHtml="";
				for(var i=0;i<equipmentInfoJson.length;i++){
					equipmentInfoHtml=equipmentInfoHtml+"<input type='checkbox' value='"+equipmentInfoJson[i].equipId+"' data-equipName='"+equipmentInfoJson[i].equipName+"' />"+equipmentInfoJson[i].equipName+"&nbsp;&nbsp;";
				}
				$("#equipBox").append(equipmentInfoHtml);
			}else{
				$.messager.alert('提示',"查询房间信息异常,错误原因："+data.errorMsg,'warning');
			}
		}
	});	
}

function commit(){
	
	var startTime = $("#startTime").val();
	var endTime = $("#endTime").val();
	if(startTime>=endTime){
		$.messager.alert('提示',"结束时间需要大于开始时间！",'warning');
		return;
	}
	var roomId = $("#roomId").val();
	var title = $("#title").val();
	if(title==undefined||title==""){
		$.messager.alert('提示',"未填写会议主题！",'warning');
		return;
	}
	var equipList =[];
	var equipNames="";
	$("#equipBox").find("input[type=checkbox]").each(function(){
		if($(this).prop("checked")){
			equipList.push($(this).val());
			equipNames= (equipNames==""?"":(equipNames+","))+$(this).attr('data-equipName');
		}
	});
	var param ={
			startTime:startTime,
			endTime:endTime,
			title:title,
			roomId:roomId,
			equipList:equipList
	};
	
	$.messager.confirm('预约提交', '<br>请确认预约内容：<br><br>开始时间：'+startTime+"<br>结束时间："+endTime+"<br>会议室："+$("#roomId").find("option:selected").text()+"<br>主题："+title+ (equipNames==""?"":("<br>设备："+equipNames)), function(r){
		if(r){
			$('#loading').loading('open');
			$.ajax({
				dataType : "json",
				type : "POST",
				data:{param:JSON.stringify(param)},
				async:false,
				url : window.contextPath+"/booking/commitBookingInfo.action",
				success : function(data) {
					$('#loading').loading('close');
					if(data.result=='success'){
						alert("预定成功！");
						initRoomBookingInfo();
					}else{
						$.messager.alert('提示',"预约提交异常,错误编码："+data.errorCode+"原因："+data.errorMsg,'warning');
					}
				}
			});	
		}
    });
	
}