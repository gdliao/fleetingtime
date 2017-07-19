<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%	String keywords = request.getParameter("keywords")==null?"": request.getParameter("keywords").toString();%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title></title>
<link href="../css/operate.css" rel="stylesheet">
	<script src="../common/js/jquery.min.js"></script>
<link href="../common/css/bootstrap.min.css" rel="stylesheet">
<script src="../common/js/bootstrap.min.js"></script>
<script src="js/searchList.js"></script>

<script type="text/javascript">
window.contextPath="<%=request.getContextPath()%>";
</script>
<style type="text/css">
.table tr{
text-indent: 80px;
}
</style>
</head>
<body>
	<div class="container" style="margin: auto;">
		<div style="margin:0 auto;">
			<h3>查询列表</h3>
		</div>
		<div>
			<table class="table" id="table" style="margin:0 auto;"></table>
			<div style="margin:auto;"><ul class="pagination pagination-sm" id="paging"></ul></div>
		</div>
	</div>
	
	<div id="bootstrapDialog" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					<h4 class="modal-title">标题:<span id="dTitle"></span></h4>
				</div>
				<div class="modal-body">
					<p>时间:<span id="dTime"></span></p>
					<p id="actP" style="display:none;">活动:<span id="dActList" style="margin-left:10px;" ></span></p>
					<p>内容:</p><textarea id="dDes" readonly="readonly" data-limit="0" rows="6" class="form-control"></textarea>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" name="likeButton" class="btn btn-primary" onclick="like(this)" style="height:33px;"><span class="glyphicon glyphicon-thumbs-up">喜欢</span></button>
				</div>
				<input id="infoId" type="hidden">
			</div>
		</div>
	</div>
	<input id="keywords" type="hidden" value="<%= keywords %>">
</body>
</html>