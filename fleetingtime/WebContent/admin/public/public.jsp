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
