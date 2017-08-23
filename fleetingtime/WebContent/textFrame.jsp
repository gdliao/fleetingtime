<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<% 
	String userId = session.getAttribute("userId")==null?"":session.getAttribute("userId").toString();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>写</title>
<script src="common/js/jquery.min.js"></script>
<link href="common/css/bootstrap.min.css" rel="stylesheet">
<link href="common/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
<script src="js/textFrame.js"></script>

<!-- umeditor -->
<!-- <link href="umeditor/themes/default/css/umeditor.css" type="text/css" rel="stylesheet">
<link href="umeditor/umeditor_self.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="umeditor/third-party/template.min.js"></script>
<script type="text/javascript" charset="utf-8" src="umeditor/umeditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="umeditor/umeditor.min.js"></script>
<script type="text/javascript" src="lang/zh-cn/zh-cn.js"></script> -->

<script type="text/javascript">
window.contextPath="<%=request.getContextPath()%>";
</script>
<style type="text/css">
</style>
</head>
<body>
	<div id="textWin">
		<div class="input-group">
			<span class="input-group-addon">标题</span>
			<input id="title" type="text" class="form-control" placeholder="" style=""><!-- width: 172px; -->
		</div>
		<br>
		<!-- 
		<div class="input-group" style="margin-bottom: 0">
			<div class="input-group date form_date col-md-1" data-date="" data-date-format="yyyy-mm-dd" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
				<span class="input-group-addon" style="float: left;width: 55px;">日期</span>
				<input id="date" class="form-control" style="width: 100px;float: left;height: 28px;" type="text" value="" readonly>
				<span class="input-group-addon" style="float: left;width: 35px;border-left: 0;">
					<span class="glyphicon glyphicon-remove"></span>
				</span>
				<span class="input-group-addon" style="float: left;width: 35px;">
					<span class="glyphicon glyphicon-calendar"></span>
				</span>
			</div>
			<input type="hidden" id="dtp_input2" value="" />
		</div>
		<br> -->
		<div class="input-group">
			<!-- <label style="float: left;font-size: 18px;">是否参与活动&nbsp;&nbsp;</label> -->
			<span  class="input-group-addon">是否参与活动</span>
			<select id="ifJoinAct" class="form-control" style="" onchange="showActList(this.value);"><!-- width: 100px; -->
				<option value="0">否</option>
				<option value="1">是</option>
			</select>
		</div>
		<br>
		<div id="actDiv" style="display:none;"  class="input-group">
				<span  class="input-group-addon">主题活动名称</span>
				<!-- <label style="float: left;font-size: 18px;">主题活动名称&nbsp;&nbsp;</label>-->
				<select id="actList" class="form-control" style="" onchange="getLimit(this);"><option value="0">--选择一个活动--</option></select><!-- width: 160px; -->
		</div>
		<br>
		<div class="input-group" style=""><!-- width: 230px; -->
			<span class="input-group-addon">内容</span>
			<textarea id="content" data-limit="0" rows="15" class="form-control" onKeyUp="textCounter(this);"></textarea>
			
			<!-- <div class="form-control" contentEditable="true" style="height: 200px;"> type here
			    <img src="http://t2.gstatic.com/images?q=tbn:ANd9GcQCze-mfukcuvzKk7Ilj2zQ0CS6PbOkq7ZhRInnNd1Yz3TQzU4e&t=1" />
			</div> -->
			<!-- <script type="text/plain" id="myEditor" style="width:1000px;height:240px;">
   				<p>这里我可以写一些输入提示</p>
			</script> 
			<div>
			    <h3 id="focush2"></h3>
			</div>-->
		</div>
		<div>已输入<span id="count">0</span>字<span id="limitDiv" style="display:none;">(活动限制为<span id="maxLimit" style="color: red;"></span>字，还能输入<span id="rest" style="color: red;"></span>字)</span></div>
		<br>
		
		<br>
		<div id="myButtons3" class="bs-example" style="width: 220px; white-space: nowrap; overflow: hidden;">
			<button type="button" class="btn btn-primary" style="" onclick="commit()">提交</button><button type="button" class="btn btn-primary" style="margin-left:10px;" onclick="toOperate()">更多</button>
		</div>
	</div>
	<input id="userId" type="hidden" value="<%= userId %>">
<script src="common/js/bootstrap.min.js"></script>
<script type="text/javascript" src="common/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="common/js/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>

<!-- <script type="text/javascript">
    //实例化编辑器
    var um = UM.getEditor('myEditor');
    um.addListener('blur',function(){
        $('#focush2').html('编辑器失去焦点了')
    });
    um.addListener('focus',function(){
        $('#focush2').html('')
    });
    //按钮的操作
    function insertHtml() {
        var value = prompt('插入html代码', '');
        um.execCommand('insertHtml', value)
    }
    function isFocus(){
        alert(um.isFocus())
    }
    function doBlur(){
        um.blur()
    }
    function createEditor() {
        enableBtn();
        um = UM.getEditor('myEditor');
    }
    function getAllHtml() {
        alert(UM.getEditor('myEditor').getAllHtml())
    }
    function getContent() {
        var arr = [];
        arr.push("使用editor.getContent()方法可以获得编辑器的内容");
        arr.push("内容为：");
        arr.push(UM.getEditor('myEditor').getContent());
        alert(arr.join("\n"));
    }
    function getPlainTxt() {
        var arr = [];
        arr.push("使用editor.getPlainTxt()方法可以获得编辑器的带格式的纯文本内容");
        arr.push("内容为：");
        arr.push(UM.getEditor('myEditor').getPlainTxt());
        alert(arr.join('\n'))
    }
    function setContent(isAppendTo) {
        var arr = [];
        arr.push("使用editor.setContent('欢迎使用umeditor')方法可以设置编辑器的内容");
        UM.getEditor('myEditor').setContent('欢迎使用umeditor', isAppendTo);
        alert(arr.join("\n"));
    }
    function setDisabled() {
        UM.getEditor('myEditor').setDisabled('fullscreen');
        disableBtn("enable");
    }

    function setEnabled() {
        UM.getEditor('myEditor').setEnabled();
        enableBtn();
    }

    function getText() {
        //当你点击按钮时编辑区域已经失去了焦点，如果直接用getText将不会得到内容，所以要在选回来，然后取得内容
        var range = UM.getEditor('myEditor').selection.getRange();
        range.select();
        var txt = UM.getEditor('myEditor').selection.getText();
        alert(txt)
    }

    function getContentTxt() {
        var arr = [];
        arr.push("使用editor.getContentTxt()方法可以获得编辑器的纯文本内容");
        arr.push("编辑器的纯文本内容为：");
        arr.push(UM.getEditor('myEditor').getContentTxt());
        alert(arr.join("\n"));
    }
    function hasContent() {
        var arr = [];
        arr.push("使用editor.hasContents()方法判断编辑器里是否有内容");
        arr.push("判断结果为：");
        arr.push(UM.getEditor('myEditor').hasContents());
        alert(arr.join("\n"));
    }
    function setFocus() {
        UM.getEditor('myEditor').focus();
    }
    function deleteEditor() {
        disableBtn();
        UM.getEditor('myEditor').destroy();
    }
    function disableBtn(str) {
        var div = document.getElementById('btns');
        var btns = domUtils.getElementsByTagName(div, "button");
        for (var i = 0, btn; btn = btns[i++];) {
            if (btn.id == str) {
                domUtils.removeAttributes(btn, ["disabled"]);
            } else {
                btn.setAttribute("disabled", "true");
            }
        }
    }
    function enableBtn() {
        var div = document.getElementById('btns');
        var btns = domUtils.getElementsByTagName(div, "button");
        for (var i = 0, btn; btn = btns[i++];) {
            domUtils.removeAttributes(btn, ["disabled"]);
        }
    }
</script> -->

<script type="text/javascript">
    $('.form_datetime').datetimepicker({
        //language:  'zh-CN',
        weekStart: 1,
        todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		forceParse: 0,
        showMeridian: 1
    });
	$('.form_date').datetimepicker({
        language:  'zh-CN',
        weekStart: 1,
        todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		minView: 2,
		forceParse: 0
    });
	$('.form_time').datetimepicker({
        language:  'zh-CN',
        weekStart: 1,
        todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 1,
		minView: 0,
		maxView: 1,
		forceParse: 0
    });
</script>
</body>
</html>