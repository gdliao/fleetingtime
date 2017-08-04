<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
<script src="js/dailyList.js"></script>

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
					<h4 class="modal-title">标题:<input id="dTitle" type="text" class="form-control"style=""></h4>
				</div>
				<div class="modal-body">
					<p>分享<select id="dIfShare" class="form-control" style="width: 20%;" onchange="showActList(this.value);"><option value="0">否</option><option value="1">是</option></select></p>
					<p id="actP" style="display:none;">活动<select id="dActList" class="form-control" style="width: 40%;" onchange="getLimit(this);"></select></p>
					<p>内容</p>
					<textarea id="dDes" data-limit="0" rows="6" class="form-control" onKeyUp="textCounter(this);"></textarea>
					<!-- <div id="limitDiv">本次活动限制为<span id="maxLimit"></span>字，还能输入<span id="rest"></span>个字</div> -->
					<div>已输入<span id="count"></span>字<span id="limitDiv" style="display:none;">(活动限制为<span id="maxLimit" style="color: red;"></span>字，还能输入<span id="rest" style="color: red;"></span>字)</span></div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" onclick="commit()">保存</button>
				</div>
				<input id="infoId" type="hidden">
			</div>
		</div>
	</div>
</body>
</html>