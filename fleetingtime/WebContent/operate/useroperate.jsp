<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<% 
	String userId = session.getAttribute("userId")==null?"":session.getAttribute("userId").toString();
	String userName = session.getAttribute("userName")==null?"":session.getAttribute("userName").toString();
	String email = session.getAttribute("email")==null?"":session.getAttribute("email").toString();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>用户后台 </title>
<script type="text/javascript">
window.contextPath="<%=request.getContextPath()%>";
</script>

<script src="../common/js/jquery.min.js"></script>
<link href="../common/css/bootstrap.min.css" rel="stylesheet">
<script src="../common/js/bootstrap.min.js"></script>
<script src="js/useroperate.js"></script>

<style type="text/css">
/* .navcss{width:100%;position:fixed;top:0;left:0;} */
</style>
</head>
<body>
	<nav class="navbar navbar-default" role="navigation">
		<div class="container-fluid"> 
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#example-navbar-collapse">
					<span class="sr-only">切换导航</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="javascript:void(0);">你好，<%=userName %></a>
			</div>
			<div class="collapse navbar-collapse" id="example-navbar-collapse">
				<ul class="nav navbar-nav navbar-right">
					<li><input id="searchText" type="text" class="form-control" placeholder="别人分享的日志.." style="width: 150px;float:left;margin-left:10px;margin-top: 7px;">
						<button type="button" class="btn btn-default btn-sm" style="height: 34px;margin-top: 7px;" onclick="search()">
						     <span class="glyphicon glyphicon-search"></span>
						</button></li>
					<li><a href="../index.html">首页</a></li>
					<li><a href="javascript:void(0);" onclick="showPage('dailyList')">日志列表</a></li>
					<li><a href="javascript:void(0);" onclick="openDialog()">个人信息修改</a></li>
					<li><a href="javascript:void(0);" onclick="logout()">注销</a></li>
				</ul>
			</div>
		</div>
		
		<div title="domain" style="padding:0px;height:80%;"><!-- margin-top: 80px; -->
			<iframe id="mainFrame" style="border: 0px;width: 100%;height:700px;overflow: hidden;" src="dailyList.html"></iframe>
		</div>
	</nav>
	
	<div id="userInfoDialog" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					<h4 class="modal-title">个人信息修改</h4>
				</div>
				<div class="modal-body">
					<p>昵称:<input id="userName" type="text" class="form-control" value="<%=userName %>" style=""></p>
					<p>邮箱:<input id="email" type="text" class="form-control" value="<%=email %>" style=""></p>
					<p>密码:<input id="password" type="password" class="form-control" style=""></p>
					<p>确认密码:<input id="comfirmPassword" type="password" class="form-control" style=""></p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" name="likeButton" class="btn btn-primary" onclick="commit()" style="height:33px;">保存</button>
				</div>
				<input id="infoId" type="hidden">
			</div>
		</div>
	</div>
	
	<input id="userId" type="hidden" value="<%= userId %>">

</body>
</html>