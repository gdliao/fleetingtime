<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../public/public.jsp"%>
 <% 
String type = null == request.getParameter("type")? "" :(String)request.getParameter("type"); 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>用户资料编辑</title>
<script src='<%=path %>/busipages/rightsManagement/editRoleInfo/edit.js'></script>
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
	
	<table style="margin: auto; margin-top: 15px;">
				<tr>
					<td>角色名称</td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;<span id="roleName"></span></td>
					<td><a href="#" class="easyui-linkbutton button-default button-sm" onclick="commit()">更新</a></td>
				</tr>
				<tr>
					<td valign="top"><span>功能权限</span></td>
					<td><div style="padding: 5px">
							<ul id="rightsTree" class="easyui-tree"></ul>
						</div></td>
				</tr>
				<tr style="border-bottom-color: #ffffff"></tr>
			</table>
	
	<input type="hidden" id="type_bak" value="<%=type%>" />
	<div id="loading"></div>
</body>
</html>