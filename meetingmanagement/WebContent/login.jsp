<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/public/public.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>登录</title>
</head>
<script src="<%=path%>/js/login.js"></script>
<script>
	if (window != top)
		top.location.href = location.href;

	function reloadImage() {
		document.getElementById('identity').src = 'register/image.jsp?ts='
				+ new Date().getTime();
	}
</script>
<body>
	<div class="easyui-panel" title="Login" style="width: 100%; max-width: 600px; padding: 30px 60px;">
		<div id="loginNoBorder" style="margin-bottom: 20px;">
			<input id="loginNo" class="easyui-textbox theme-textbox-radius" data-options="prompt:'PhoneNumber or Email...'" style="width: 70%;" />
			<!-- &nbsp;<img name="rightIcons" src="easyui/themes/insdep/icons/mini-ok.png"> -->
			<!-- <div name="warningBlock" class="alert-error alert-block" style="width: 100px;height:10px;float: right;margin-right: 5px;">
			<div class="alert-icons"></div>格式错误
  		</div> -->
		</div>
		<div id="passwordBorder" style="margin-bottom: 20px;">
			<input id="password" class="easyui-passwordbox theme-textbox-radius" prompt="Password..." iconWidth="28" style="width: 70%;" />
		</div>
		<div id="randBorder" style="margin-bottom: 20px;">
			<input id="rand" class="easyui-textbox theme-textbox-radius" data-options="prompt:'Enter the Right Code...'" style="width: 50%;" />
			<div style="float: right; margin-right: 140px;">
				<img src="register/image.jsp" id="identity" onclick="reloadImage()" title="change one...">
			</div>
		</div>
		<div style="margin-bottom: 20px;">
			<a class="easyui-linkbutton button-default l-btn l-btn-small" onclick="login()"> <span class="l-btn-left"><span class="l-btn-text">login</span></span></a>
			<a class="easyui-linkbutton button-default l-btn l-btn-small" onclick="register()" style="margin-left: 80px;"> <span class="l-btn-left"><span class="l-btn-text">register</span></span></a>
		</div>
	</div>
	<div id="registerWin" style="display: none;">
		<iframe id="registerFrame" style="width: 98%; height: 98%; border: 0px;"></iframe>
	</div>
	<input type="hidden" onkeypress="enterPress(event)" />
	<div id="loading"></div>
</body>
</html>