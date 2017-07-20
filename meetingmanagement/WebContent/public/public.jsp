<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="utf-8"%>
<%
request.setCharacterEncoding("UTF-8");
String path = request.getContextPath();
%>
<link href="<%=path%>/easyui/themes/insdep/easyui.css" rel="stylesheet" type="text/css">
<link href="<%=path%>/easyui/themes/insdep/easyui_animation.css" rel="stylesheet" type="text/css">
<link href="<%=path%>/easyui/themes/insdep/easyui_plus.css" rel="stylesheet" type="text/css">
<link href="<%=path%>/easyui/themes/insdep/insdep_theme_default.css" rel="stylesheet" type="text/css">
<link href="<%=path%>/easyui/themes/insdep/icon.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="<%=path%>/easyui/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path%>/easyui/themes/insdep/jquery.insdep-extend.min.js"></script>

<script type="text/javascript">
window.contextPath="<%=request.getContextPath()%>";
</script>
<script type="text/javascript" src="<%=path%>/public/loading/loading.js"></script>
<script type="text/javascript">
$(function(){
	$('#loading').loading();
});
function dateFormate(_date){
	var date = new Date(_date);
	
	var year = date.getFullYear();  // 获取完整的年份(4位,1970)
	var month = pad(date.getMonth()+1,2);    // 获取月份(0-11,0代表1月,用的时候记得加上1)
	var day = pad(date.getDate(),2);   // 获取日(1-31)
	var hour = pad(date.getHours(),2);  // 获取小时数(0-23)
	var min = pad(date.getMinutes(),2);  // 获取分钟数(0-59)
	var sec = pad(date.getSeconds(),2);  // 获取秒数(0-59)
	
	return year +"-"+month+"-"+day+" "+hour+":"+min+":"+sec;
}

function pad(num, length) {   
	return (Array(length).join('0') + num).slice(-length); 
}  
</script>