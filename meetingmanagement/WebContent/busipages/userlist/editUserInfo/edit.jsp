<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../public/public.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>修改用户资料</title>
<script src='<%=path %>/busipages/userlist/editUserInfo/edit.js'></script>
<style type="text/css">
table{
margin-top: 30px;
}
table tr{
height: 50px;
}
</style>
</head>
<body>
<div class="easyui-panel" title="" style="width:100%;max-width:600px;padding:30px 60px;border-style: hidden;">
	<table>
		<tr><td>用&nbsp;户&nbsp;名&nbsp;：</td><td><input id="userName" class="easyui-textbox theme-textbox-radius" data-options="prompt:'用户名...'" style="width:300;"/></td></tr>
		<tr><td>联系电话：</td><td><input id="phoneNumber" class="easyui-textbox theme-textbox-radius" data-options="prompt:'电话...'" style="width:300;"/></td></tr>
		<tr><td>邮&nbsp;&nbsp;箱&nbsp;：</td><td><input id="email" class="easyui-textbox theme-textbox-radius" data-options="prompt:'邮箱...'" style="width:300;"/></td></tr>
		<tr><td>用户角色：</td><td><select></select></td></tr>
	</table>
	<!-- <div style="margin-bottom:20px;">
		用户名：<input id="userName" class="easyui-textbox theme-textbox-radius" data-options="prompt:'用户名...'" style="width:70%;"/>
	</div>
	<div style="margin-bottom:20px;">
		联系电话：<input id="phoneNumber" class="easyui-textbox theme-textbox-radius" data-options="prompt:'电话...'" style="width:70%;"/>
	</div>
	<div style="margin-bottom:20px;">
		邮箱：<input id="email" class="easyui-textbox theme-textbox-radius" data-options="prompt:'邮箱...'" style="width:70%;"/>
	</div>
	<div style="margin-bottom:20px;">
		用户角色：<select></select>
	</div> -->
	
	<div style="margin-top:30px;">
		<a class="easyui-linkbutton button-default l-btn l-btn-small" onclick="">
			<span class="l-btn-left"><span class="l-btn-text">保存</span></span>
		</a>
	</div>
</div>
</body>
</html>