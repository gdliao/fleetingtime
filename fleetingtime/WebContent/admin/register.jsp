<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../admin/include/public.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>register</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="<%=path%>/js/register.js"></script>
  </head>
  
  <body>
   <table>
    	<tr><td>userName:</td><td><input id="userName" type="text"/></td></tr>
    	<tr><td>password:</td><td><input  id="password" type="password"/></td></tr>
    	<tr><td>phoneNum:</td><td><input  id="phoneNum" type="text"/></td></tr>
    	<tr><td>email:</td><td><input  id="email" type="text"/></td></tr>
    	<tr><td></td><td><input type="button" onclick="submit()" value="submit"/></td></tr>
    </table>
  </body>
</html>
