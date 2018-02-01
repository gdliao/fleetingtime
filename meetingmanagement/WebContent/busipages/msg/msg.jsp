<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/public/public.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>总览</title>
<script src="<%=path %>/busipages/msg/msg.js"></script>
<link rel="stylesheet" href="../../easyui/plugin/font-awesome-4.7.0/css/font-awesome.min.css">
</head>
<body>
	<div class="easyui-panel" id="msgList" title="消息列表" style="width:100%;height:100%;padding:50px;" data-options="cls:'theme-border-radius'">
	</div>

	<div id="replyDlg" class="easyui-dialog" title="消息回复" style="width:400px;height:200px;padding:10px;"
            data-options="cls:'dialog', buttons: '#dlg-buttons'">
            <textarea id="msgContent" rows="6" cols="70" style="border-style:none;"></textarea>
    </div>
    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="reply()">回复</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#replyDlg').dialog('close')">关闭</a>
    </div>
    
</body>
</html>