<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../public/public.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>登录</title>
</head>
<script>  
    function reloadImage() {   
        document.getElementById('identity').src = 'image.jsp?ts=' + new Date().getTime();  
    }  
</script>
<body>
<div class="easyui-panel" title="" style="width:100%;max-width:600px;padding:30px 60px;border-style: hidden;">
	<div style="margin-bottom:20px;">
		<input class="easyui-textbox theme-textbox-radius" data-options="prompt:'UserName...'" style="width:70%;"/>
	</div>
	<div style="margin-bottom:20px;">
		<input class="easyui-textbox theme-textbox-radius" data-options="prompt:'PhoneNumber...'" style="width:70%;"/>
	</div>
	<div style="margin-bottom:20px;">
		<input class="easyui-textbox theme-textbox-radius" data-options="prompt:'E-mail...'" style="width:70%;"/>
	</div>
	<div style="margin-bottom:20px;">
		<input class="easyui-textbox theme-textbox-radius" data-options="prompt:'Enter Your Code...'" style="width:42%;"/>
		<a class="easyui-linkbutton button-default l-btn l-btn-small">
			<span class="l-btn-left"><span class="l-btn-text">send a message</span></span>
		</a>
	</div>
	<div style="margin-bottom:20px;">
		<input class="easyui-passwordbox theme-textbox-radius" prompt="Password..." iconWidth="28" style="width:70%;"/>
	</div>
	<div style="margin-bottom:20px;">
		<input class="easyui-passwordbox theme-textbox-radius" prompt="Confirm Your Password..." iconWidth="28" style="width:70%;"/>
	</div>
	<div style="margin-bottom:20px;">
		<a class="easyui-linkbutton button-default l-btn l-btn-small">
			<span class="l-btn-left"><span class="l-btn-text">Commit</span></span>
		</a>
	</div>
</div>
</body>
</html>