<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/public/public.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>权限管理</title>
<script src='<%=path%>/busipages/rightsManagement/rightsManagement.js'></script>
<style type="text/css">
/* table{
margin-top:20px;
margin-left:100px;
} */
</style>
</head>
<body>
	<div class="easyui-panel" title="<b>角色列表</b><span></span>" style="width: 100%; height: 100%; margin: auto;" data-options="footer:'#ft',cls:'theme-title-lines-black'">
		<div style="margin: auto; width: 40%; margin-top: 30px;">
			<table id="roleListTable"
				style="border: 1px solid #DCDCDC; display: block; height: 642px;"></table>
		</div>
	</div>
	<div id="tb" style="padding:2px 5px;">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" data-type="add" onclick="editRole(this)">新增</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-type="edit" onclick="editRole(this)">编辑</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
		<div style="float: right"><input id="userName" class="easyui-textbox theme-textbox-radius" data-options="prompt:'角色名...'" style=""/>
		<a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="searchByRoleName()">Search</a></div>
	</div>
	<div id="ft" style="padding: 5px;"></div>
	<div id="editWin" style="display: none;">
		<iframe src="" id="editFrame" name="editFrame" frameborder='0' scrolling="no" style="width: 100%; height: 95%;"></iframe>
	</div>
	<div id="addWin" style="display: none;">
		<iframe src="" id="addFrame" name="addFrame" frameborder='0' scrolling="no" style="width: 100%; height: 95%;"></iframe>
	</div>
</body>
</html>