<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/public/public.jsp"%>
<%
String userName = (String)session.getAttribute("userName");
String email = (String)session.getAttribute("email");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>主页</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="description" content="This is my page">
<style type="text/css">
.layout-panel-west {
	border-right: 1px solid #c5c5c5;
}
</style>
<script src="<%=path %>/js/index.js"></script>
<script>
	$(function() {
		/*布局部分*/
		$('#master-layout').layout({
			fit : true
		/*布局框架全屏*/
		});
	});
	function showMsg(){
		
		var msgFlag = false;
		$("#rightTabs").find('ul[class=tabs]').find('span[class="tabs-title tabs-closable"]').each(function(){
			if($(this).text()=='消息栏'){
					//点击相同标签时不重复打开相同tab
					$(this).parent().parent().parent().find('li').each(function(){
						$(this).removeClass('tabs-selected');
					});
					//选中该tab页
					$(this).parent().parent().addClass('tabs-selected');
					msgFlag=true; 
					return;
				}
		});
		
		if(msgFlag){
			$("#rightTabs").find('div[class=tabs-panels]').find('div[class=panel]').each(function(){
				$(this).hide();
				if($(this).find("iframe").attr('name')=='msg'){
					$(this).show();
				}
			});
			
			return;
		}

		$('#rightTabs').tabs('add',{
			title: '消息栏',
			content: '<iframe name="msg" src="<%=path %>/busipages/msg/msg.jsp" style="border: 0px;width: 100%;height:100%;overflow: hidden;" ></iframe>',
			closable: true
		});
		
	}
</script>
<!--第三方插件加载-->
<!-- <script src="<%=path%>/easyui/plugin/justgage-1.2.2/raphael-2.1.4.min.js"></script>
<script src="<%=path%>/easyui/plugin/justgage-1.2.2/justgage.js"></script>

<script src="<%=path%>/easyui/plugin/Highcharts-5.0.0/js/highcharts.js"></script>

<script type="text/javascript" src="<%=path%>/easyui/plugin/ueditor-1.4.3.3/ueditor.config.js"></script>
<script type="text/javascript" src="<%=path%>/easyui/plugin/ueditor-1.4.3.3/ueditor.all.min.js"></script>

<link href="<%=path%>/easyui/plugin/cropper-2.3.4/dist/cropper.min.css" rel="stylesheet" type="text/css">
<script src="<%=path%>/easyui/plugin/cropper-2.3.4/dist/cropper.min.js"></script> -->
</head>

<body>
	<div id="master-layout">
		<div data-options="region:'north',border:false,bodyCls:'theme-header-layout'">
			<div class="theme-navigate">
				<div class="left">
					<a href="#" class="easyui-menubutton theme-navigate-user-button" data-options="menu:'.theme-navigate-user-panel'"><%=userName %></a>
					<div class="theme-navigate-user-panel">
						<dl>
							<dd>
								<b class="badge-prompt"><%=userName %></b>
								<span><%=email %></span>
							</dd>
							<dt>
								<a class="theme-navigate-user-modify">修改密码</a>
								<a class="theme-navigate-user-logout" onclick="exit()">注销</a>
							</dt>
						</dl>
					</div>
				</div>
			</div>
		</div>
		<!--开始左侧菜单-->
		<div id="leftMenuDiv" data-options="region:'west',border:false,bodyCls:'theme-left-layout'" style="width: 200px;">
			<!--正常菜单-->
			<div class="theme-left-normal">
				<!--start class="easyui-layout"-->
				<div class="easyui-layout" data-options="border:false,fit:true">
					<!--start region:'center'-->
					<div data-options="region:'center',border:false">
						<!--start easyui-accordion-->
						<div id="leftMenu" class="easyui-accordion" data-options="border:false,fit:true">
						</div>
						<!--end easyui-accordion-->
					</div>
					<!--end region:'center'-->
				</div>
				<!--end class="easyui-layout"-->
			</div>
		</div>
		<!--结束左侧菜单-->
		<!-- 中心Tab -->
		<div data-options="region:'center',border:false" id="control" style="padding: 0px; background: #fff;">
			<div id="rightTabs" class="easyui-tabs theme-tab-black-block theme-tab-line-bold" style="width:100%;height:100%" data-options="tabPosition:'top'">
	            <div title="About" style="padding:0px">
	                <iframe id="mainFrame" style="border: 0px;width: 100%;height:100%;overflow: hidden;" src=""></iframe>
	            </div>
        	</div>
		</div>
	</div>
</body>
</html>
