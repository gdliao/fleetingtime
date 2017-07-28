<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.fleetingtime.vo.Vote" %>
<% 
	String userId = session.getAttribute("userId")==null?"":session.getAttribute("userId").toString();
	String likeList =session.getAttribute("likeList")==null? null : session.getAttribute("likeList").toString();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">

<title>记录你想要的。</title>

<link href="common/css/bootstrap.min.css" rel="stylesheet">
<link href="css/index.css" rel="stylesheet">
<script src="common/js/jquery-1.9.1.min.js"></script>
<script src="common/js/bootstrap.min.js"></script>
<script src="js/index.js"></script>
<script type="text/javascript">
	window.contextPath="<%=request.getContextPath()%>";

	if (window != top)
		top.location.href = location.href;

	function reloadImage() {
		document.getElementById('reidentity').src =document.getElementById('identity').src = 'image.html?ts=' + new Date().getTime();
		//document.getElementById('reidentity').src = 'image.html?ts=' + new Date().getTime();
	}
	
	var countdown=60; 
	function settime(obj) { 
	    if (countdown == 0) { 
	        obj.removeAttribute("disabled");    
	        obj.value="发送信息"; 
	        countdown = 60; 
	        return;
	    } else { 
	        obj.setAttribute("disabled", true); 
	        obj.value="重发(" + countdown + ")"; 
	        countdown--; 
	    } 
	setTimeout(function() { settime(obj); },1000);
	}
</script>
</head>
<body>
	<div class="container" style="margin-top: 10px;">
		<div class="jumbotron head" style="height:180px;">
			<h1>流年</h1>
			<p>年华似水，似水流年</p>
		</div>
		<div class="row">
			<div class="col-sm-8"><!--  style="width: 60%;" -->
				<h1 id="subject"><!-- <span style="font-size: 15px;">本期主题：</span> --></h1>
				<div id="roll2" style="overflow: hidden;height: 540px; ">
					<table>
						<tr>
							<td id="rolltop">
							</td>
						</tr>
						<tr>
							<td id="rollbottom"></td>
						</tr>
					</table>
				</div>

			</div>
			<div class="col-sm-4" style=" margin-top: 40px;"><!-- width: 30%; -->
				<div id="doMainWin" style="display:none;border: 0;">
					<iframe id="doMainFrame" style="border: 0;height: 650px;" frameBorder="0"></iframe>
				</div>
				<div id="loginWin" style=""><!-- height: 320px;padding: 20px; -->
					<div class="input-group" style="margin-top: 60px;"><!-- width: 230px; -->
						<!-- <span class="input-group-addon">用户名</span> -->
						<input id="loginNo" type="text" class="form-control" placeholder="手机号" style="width: 230px;">
					</div>
					<br>
					<div class="input-group"><!-- width: 230px; -->
						<!-- <span class="input-group-addon">密&nbsp;&nbsp;&nbsp;&nbsp;码</span> -->
						<input id="password" type="password" class="form-control" placeholder="密码" style="width: 230px;">
					</div>
					<br>
					<div class="input-group"><!-- width: 230px; -->
						<input id="rand" type="text" class="form-control" placeholder="动态密码" style="width: 130px;">
						&nbsp;<img src="image.html" id="identity" onclick="reloadImage()" title="换一换">
						<!-- <span class="input-group-btn">
							<button class="btn btn-default" type="button">发送</button>
						</span> -->
					</div>
					<br>
					<div id="myButtons3" class="bs-example" style="white-space:nowrap;overflow: hidden;"><!-- width:220px; -->
						<button type="button" class="btn btn-primary" style="margin-right: 50px;" onclick="login()">登录</button>
						<button type="button" class="btn btn-primary" onclick="openDialog()">注册</button>
					</div>
				</div>
				
			</div>
			<!-- <div class="col-sm-4" id="footMsg" style="margin-top:10px;"> width:255px;
					<h3>我们想说</h3>
					<p>&nbsp;&nbsp;&nbsp;&nbsp;每一个人，我想，都有自己的怪癖。但是为了要保持正常，符合世界的眼光，他们克服了这些怪癖。因此，也毁掉了他们的异禀。</p>
			</div> -->
		</div>
		
		<div class="foot" style="">
			<p>©2017 流年<a href="#" target="_blank"><span>联系我们</span></a> <a href="weixin://scanqrcode">打开微信</a> </p>
		</div>
	</div>
	
	<div id="registerDialog" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					<h4 class="modal-title">注册</h4>
				</div>
				<div class="modal-body">
					<p>
					<div class="input-group">
						<span class="input-group-addon">手&nbsp;机&nbsp;号&nbsp;</span>
						<input id="phoneNum" type="text" class="form-control" placeholder="作为登录账号.." style="width: 50%;" onkeyup="check(this)">
						<div class="alertmsg" id="loginNoCheck">格式不正确</div>
					</div>
					</p>
					<p>
					<div class="input-group">
						<span class="input-group-addon">昵&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称&nbsp;&nbsp;</span>
						<input id="userName" type="text" class="form-control" placeholder="昵称.." style="width: 50%;" onkeyup="check(this)">
						<div class="alertmsg" id="userNameCheck">要小于16个字符</div>
					</div>
					</p>
					<p>
					<div class="input-group">
						<span class="input-group-addon">邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱&nbsp;&nbsp;</span>
						<input id="email" type="text" class="form-control" placeholder="其他方式联系您.." style="width: 50%;" onkeyup="check(this)">
						<div class="alertmsg" id="emailCheck">格式不正确</div>
					</div>
					</p>
					<p>
					<div class="input-group">
						<span class="input-group-addon">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码&nbsp;&nbsp;</span>
						<input id="repassword" type="password" class="form-control" placeholder="密码.." style="width: 50%;"><!--  onkeyup="check(this)" -->
						<div class="alertmsg" id="passwordCheck">格式不正确</div>
					</div>
					</p>
					<p>
					<div class="input-group">
						<span class="input-group-addon">确认密码</span>
						<input id="comfirmPassword" type="password" class="form-control" placeholder="重新输入密码.." style="width: 50%;" onkeyup="check(this)">
						<div class="alertmsg" id="comfirmPasswordCheck">两次不一致</div>
					</div>
					</p>
					<div class="input-group">
						<span class="input-group-addon">验&nbsp;证&nbsp;码&nbsp;</span>
						<input id="rRand" type="text" class="form-control" placeholder="动态密码" style="width: 30%;">
						&nbsp;<img src="image.html" id="reidentity" onclick="reloadImage()" title="换一换">
					</div>
					</p>
					<p><div class="input-group">
						<span class="input-group-addon">验&nbsp;证&nbsp;码&nbsp;</span>
						<input id="verificationCode" type="text" class="form-control" placeholder="手机信息验证码.." style="width: 30%;" onkeyup="check(this)">
						&nbsp;<input type="button" id="btn" value="发送信息" onclick="settime(this)" style="height: 33px;"/>
						<div class="alertmsg" id="verificationCodeCheck">验证码错误</div>
					</div>
					</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" onclick="register()" style="height:33px;">提交</button>
				</div>
			</div>
		</div>
	</div>
	<input id="userId" type="hidden" value="<%= userId %>">
	<input id="likeList" type="hidden" value="<%= likeList %>">
</body>
</html>