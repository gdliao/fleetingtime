<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="utf-8"%>
<%
request.setCharacterEncoding("UTF-8");
String path = request.getContextPath();
%>
<script type="text/javascript" src="<%=path%>/easyui/jquery.min.js" ></script>
<script type="text/javascript" src="<%=path%>/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path%>/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=path%>/njs/plugins/jquery.autocomplete.min.js"></script>

<link href="<%=path%>/easyui/themes/gray/easyui.css" rel="stylesheet" type="text/css" />
<link href="<%=path%>/easyui/themes/icon.css" rel="stylesheet" type="text/css" />
<link href="<%=path%>/easyui/demo/demo.css" rel="stylesheet" type="text/css"  />
<link href="<%=path%>/njs/plugins/jquery.autocomplete.css" rel="stylesheet" type="text/css"  />

<script type="text/javascript">
window.contextPath="<%=request.getContextPath()%>";
</script>
