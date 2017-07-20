<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/public/public.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<script src='<%=path %>/busipages/booking/booking.js'></script>

<html>
<head>
<title>预定会议</title>

<style>
/* .table */
#bookingInfoTable{
width:900px;
margin:auto;
}
#bookingInfoTable tr{
border-color: #3e3e3e;
}
#bookingInfoTable tr th{
background-color:#d9d9d9;
}
#bookingInfoTable div{
margin-left: 10px;
}
#roomInfoTable{
width:900px;
margin:auto;
}
#roomInfoTable tr th{
padding: 7px;
}
#roomInfoTable tr td{
text-align: center;
}
</style>
</head>
<body>
	<div class="easyui-panel" title="<b>会议室使用情况</b><span></span>" style="width:100%;height:40%;margin:auto; " data-options="footer:'#ft',cls:'theme-title-lines-black'">
		<div style="margin-top:20px;">
			<table class="table table-celled" id="roomInfoTable">
				<tr><td><select id="topRoomId" onchange="changeRoomId(this.value)"></select></td><td colspan="3" style="border: 0px;"></td></tr>
				<tr style="font-weight: bolder;">
					<td>会议室</td>
					<td>开始时间</td>
					<td>结束时间</td>
					<td>预约用户</td>
					<td>状态</td>
				</tr>
			</table>
		</div>
	</div>
	<div class="easyui-panel" title="<b>预定详情</b><span></span>" style="width:100%;height:60%;margin:auto; " data-options="footer:'#ft',cls:'theme-title-lines-black'">
	
		<div style="margin-top:50px;">
			<table id="bookingInfoTable" class="table table-celled">
				<tr>
					<th><span>会议时间</span></th><td><div><input id="startTime" class="easyui-datetimebox" value="new Date()" style="width:155px;height:26px;">--<input id="endTime" class="easyui-datetimebox" value="new Date()" style="width:155px;height:26px"></div></td>
					<th><span>会议主题</span></th><td><div><input id="title" class="easyui-textbox theme-textbox-radius" data-options="prompt:'主题是...'" style="width:250px;"/></div></td>
				</tr>
				<tr><th><span>会议房间</span></th>
					<td>
						<div id="roomInfo">
							<select id="roomId" onchange="changeRoomId(this.value)"></select>
                    	</div>
                    </td>
                    <th><span>设备</span></th>
                    <td><div id="equipBox"></div></td></tr>
			</table>
			<div style="margin-left: 70%;margin-top: 20px;">
			<a href="#" class="easyui-linkbutton button-default button-sm" onclick="commit()">预约</a>
			<a href="#" class="easyui-linkbutton button-default button-sm" style="margin-left: 20px;">重置</a>
			</div>
		</div>
	</div>
	<div id="ft" style="padding:5px;"></div>
	
	<div id="loading"></div>
</body>
</html>