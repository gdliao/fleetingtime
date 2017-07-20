<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../public/public.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>新增用户</title>
<script src='<%=path %>/busipages/userlist/addUser/add.js'></script>
</head>
<body>
<div class="easyui-panel" title="" style="width:100%;max-width:600px;padding:30px 60px;border-style: hidden;">
	<div style="margin-bottom:20px;">
		<input id="userName" class="easyui-textbox theme-textbox-radius" data-options="prompt:'用户名...'" style="width:70%;"/>
	</div>
	<div style="margin-bottom:20px;">
		<input id="phoneNumber" class="easyui-textbox theme-textbox-radius" data-options="prompt:'电话...'" style="width:70%;"/>
	</div>
	<div style="margin-bottom:20px;">
		<input id="email" class="easyui-textbox theme-textbox-radius" data-options="prompt:'邮箱...'" style="width:70%;"/>
	</div>
	<div style="margin-bottom:20px;">
		<a class="easyui-linkbutton button-default l-btn l-btn-small" onclick="addUser()">
			<span class="l-btn-left"><span class="l-btn-text">保存</span></span>
		</a>
	</div>
</div>
</body>
</html>