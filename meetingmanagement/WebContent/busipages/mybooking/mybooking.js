var bookingId;
var roomInfo=[];
$(function(){
	initRoomInfo();
	initEquipment();
	initMyBooking();
});

function initMyBooking(){
	var myBookingInfo=[];
	$.ajax({
		dataType : "json",
		type : "POST",
		async:false,
		url : window.contextPath+"/myBooking/queryMyBookingInfo.action",
		success : function(data) {
			if(data.result=='success'){
				$("#roomInfoTable").find('tr[name=infoTr]').remove();
				
				var infoHtml="";
				if(data.errorCode=='0000'){
					myBookingInfo=data.data;
			
					for(var i=0;i<myBookingInfo.length;i++){
						var roomName = "";
						for(var j=0;j<roomInfo.length;j++){
							if(myBookingInfo[i].roomId == roomInfo[j].roomId){
								roomName=roomInfo[j].roomName;
							}
						}
						infoHtml=infoHtml+"<tr name='infoTr'><td>"+roomName+"</td><td>"+dateFormate(myBookingInfo[i].startTime)+"</td>" +
								"<td>"+dateFormate(myBookingInfo[i].endTime)+"</td><td>"+(myBookingInfo[i].startTime>new Date()?"预约":(myBookingInfo[i].endTime<new Date()?"已结束":"进行中"))+"</td>" +
								"<td><a name='bd' style='text-decoration: underline;' onclick='editRecord(this);'>修改</a>&nbsp;&nbsp;<a style='text-decoration: underline;' onclick='deleteRecord(this);'>删除</a><input data-id='"+myBookingInfo[i].bookingId+"' type='hide'/></td></tr>";
					}
					$("#roomInfoTable").append(infoHtml);
					$("#roomInfoTable").find('a[name=bd]').each(function(){
						var tid = $(this).parent().find('input').attr('data-id');
						for(var k=0;k<myBookingInfo.length;k++){
							if(tid==myBookingInfo[k].bookingId){
								$(this).parent().find('input').data('bookingInfo',myBookingInfo[k]);
							}
						}
					});
				}else{
					infoHtml=infoHtml+"<tr name='infoTr'><td style='border-right: 0px;'>所选房间无预约。</td><td colspan='4'></td></tr>";
					$("#roomInfoTable").append(infoHtml);
				}
			}else{
				$.messager.alert('提示',"查询房间信息异常,错误原因："+data.errorMsgd,'warning');
			}
		}
	});	
}

function initRoomInfo(){
	//初始化 房间列表
	var roomInfoJson=[];
	$.ajax({
		dataType : "json",
		type : "POST",
		async:false,
		url : window.contextPath+"/booking/queryRoomInfo.action",
		success : function(data) {
			if(data.result=='success'){
				roomInfoJson=data.data;
				
				var roomInfoHtml="";
				for(var i=0;i<roomInfoJson.length;i++){
					roomInfoHtml=roomInfoHtml+"<option value='"+roomInfoJson[i].roomId+"'>"+roomInfoJson[i].roomName+",可容纳："+roomInfoJson[i].roomCapacity+"人</option>";
					var tempInfo={roomId:roomInfoJson[i].roomId,roomName:roomInfoJson[i].roomName};
					roomInfo.push(tempInfo);
				}
				$("#roomId").append(roomInfoHtml);
			}else{
				$.messager.alert('提示',"查询房间信息异常,错误原因："+data.errorMsg,'warning');
			}
		}
	});	
	
}
function initEquipment(){
	var equipmentInfoJson=[];
	$.ajax({
		dataType : "json",
		type : "POST",
		async:false,
		url : window.contextPath+"/booking/queryEquipment.action",
		success : function(data) {
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
function editRecord(_this){
	var tempInfo = $(_this).parent().find('input').data('bookingInfo');
	bookingId = tempInfo.bookingId;
	$("#startTime").datetimebox('setValue',dateFormate(tempInfo.startTime));
	$("#endTime").datetimebox('setValue',dateFormate(tempInfo.endTime));
	$("#title").textbox('setValue',tempInfo.meetingTitle);
	$("#roomId").find('option[value="'+tempInfo.roomId+'"]').attr('selected',true);
	var el=tempInfo.equipList;
	for(var i=0;i<el.length;i++){
		$("#equipBox").find('input[type=checkbox]').each(function(){
			if($(this).val()==el[i]){
				$(this).attr("checked","checked");
			}
		});
	}
	
}

function deleteRecord(_this){
	var tempInfo = $(_this).parent().find('input').data('bookingInfo');
	bookingId = tempInfo.bookingId;
	
	$.messager.confirm('删除记录', '确认删除该预约记录？', function(r){
		if(r){
			$.ajax({
				dataType : "json",
				type : "POST",
				data:{bookingId:bookingId},
				async:false,
				url : window.contextPath+"/myBooking/deleteRecord.action",
				success : function(data) {
					if(data.result=='success'){
						alert("删除成功！");
						initMyBooking();
					}else{
						$.messager.alert('提示',"删除预约记录异常,错误编码："+data.errorCode+"原因："+data.errorMsg,'warning');
					}
				}
			});	
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
			bookingId:bookingId,
			startTime:startTime,
			endTime:endTime,
			meetingTitle:title,
			roomId:roomId,
			equipList:equipList
	};
	
	$.messager.confirm('修改提交', '<br>请确认预约内容：<br><br>开始时间：'+startTime+"<br>结束时间："+endTime+"<br>会议室："+$("#roomId").find("option:selected").text()+"<br>主题："+title+ (equipNames==""?"":("<br>设备："+equipNames)), function(r){
		if(r){
			$.ajax({
				dataType : "json",
				type : "POST",
				data:{param:JSON.stringify(param)},
				async:false,
				url : window.contextPath+"/myBooking/editMyBooking.action",
				success : function(data) {
					if(data.result=='success'){
						alert("修改成功！");
						initMyBooking();
					}else{
						$.messager.alert('提示',"修改提交异常,错误编码："+data.errorCode+"原因："+data.errorMsg,'warning');
					}
				}
			});	
		}
    });
	
}