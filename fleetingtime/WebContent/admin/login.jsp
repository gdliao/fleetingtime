<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../admin/include/public.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>login</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="<%=path%>/js/login.js"></script>
  </head>
  
  <body>
   <table>
    	<tr><td>name:</td><td><input id="loginNo" type="text"/></td></tr>
    	<tr><td>password:</td><td><input  id="password" type="password"/></td></tr>
    	<tr><td></td><td><input type="button" onclick="login()" value="login"/><input type="button" onclick="openRegisterFrame()" value="register"/></td></tr>
    </table>
    
    <div id="registerWin" style="display: none;margin-top: 100px;">
		<iframe src=""  id="registerFrame"  frameborder='0' scrolling="no"  style="width:100%;height:95%;"></iframe>
	</div>
  </body>
</html>
