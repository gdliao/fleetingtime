<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
</head>
<body>
	<div class="container" style="margin-top: 10px;">
		<div class="jumbotron" style="">
			<h1>流年</h1>
			<p>年华似水，似水流年</p>
		</div>
		<div class="row">
			<div class="col-sm-4" style="width: 60%;">
				<h1>与你分享</h1>
				<div>
					<h3>与你分享</h3>
					<p>2017年6月22日09:56:32</p>
					<p>再牛逼的梦想,也抵不住你傻逼似的坚持！再牛逼的梦想,也抵不住你傻逼似的坚持！再牛逼的梦想,也抵不住你傻逼似的坚持！再牛逼的梦想,也抵不住你傻逼似的坚持！再牛逼的梦想,也抵不住你傻逼似的坚持！</p>
					<span class="glyphicon glyphicon-thumbs-up"></span>&nbsp;&nbsp;
					<span class="glyphicon glyphicon-thumbs-down"></span>
				</div>
				<div>
					<h3>与你分享</h3>
					<p>学的不仅是技术，更是梦想！</p>
					<p>再牛逼的梦想,也抵不住你傻逼似的坚持！</p>
					<span class="glyphicon glyphicon-thumbs-up"></span>&nbsp;&nbsp;
					<span class="glyphicon glyphicon-thumbs-down"></span>
				</div>
				<div>
					<h3>与你分享</h3>
					<p>学的不仅是技术，更是梦想！</p>
					<p>再牛逼的梦想,也抵不住你傻逼似的坚持！</p>
					<span class="glyphicon glyphicon-thumbs-up"></span>&nbsp;&nbsp;
					<span class="glyphicon glyphicon-thumbs-down"></span>
				</div>
			</div>
			<div class="col-sm-4" style="width: 30%; margin-top: 40px;">
				<div id="doMainWin" style="display:none;border: 0;">
					<iframe id="doMainFrame" style="border: 0;height: 400px;" frameBorder="0"></iframe>
				</div>
				<div id="loginWin" style="height: 320px;">
					<div class="input-group" style="margin-top: 60px;width: 230px;">
						<!-- <span class="input-group-addon">用户名</span> -->
						<input id="loginNo" type="text" class="form-control" placeholder="手机号" style="width: 230px;">
					</div>
					<br>
					<div class="input-group" style="width: 230px;">
						<!-- <span class="input-group-addon">密&nbsp;&nbsp;&nbsp;&nbsp;码</span> -->
						<input id="password" type="text" class="form-control" placeholder="密码" style="width: 230px;">
					</div>
					<br>
					<div class="input-group" style="width: 230px;">
						<input type="text" class="form-control" placeholder="动态密码">
						<span class="input-group-btn">
							<button class="btn btn-default" type="button">发送</button>
						</span>
					</div>
					<br>
					<div id="myButtons3" class="bs-example" style="width:220px;white-space:nowrap;overflow: hidden;">
						<button type="button" class="btn btn-primary" style="margin-right: 50px;" onclick="login()">登录</button>
						<button type="button" class="btn btn-primary">注册</button>
					</div>
				</div>
				<div id="footMsg" style="">
					<h3>我们想说</h3>
					<p>&nbsp;&nbsp;&nbsp;&nbsp;每一个人，我想，都有自己的怪癖。但是为了要保持正常，符合世界的眼光，他们克服了这些怪癖。因此，也毁掉了他们的异禀。</p>
				</div>
			</div>
		</div>
	</div>
</body>
</html>