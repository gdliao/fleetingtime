$(function(){
	$('#replyDlg').dialog('close');
	init();
});

function init(){
	$.ajax({
		dataType : "json",
		type : "POST",
		url : window.contextPath+"/user/queryUserMsgList.action",
		success : function(data) {
			if(data.result=="success"){
				//alert(JSON.stringify(data));
				var html = "";
				var list = data.data;
				for(var i=0;i<list.length;i++){
					html = html +'<div class="easyui-panel" title="来自&nbsp;【'+ list[i].msgFromUserId+'】<p>发送时间：'+dateFormate(list[i].msgAddTime)+ '</p>"'
								+'style="width:700px;height:200px;padding:10px;margin-bottom:10px;" data-options="closable:true,tools:\'#tt'+list[i].msgId+'\',cls:\'theme-panel-simple theme-header-unheight\',onClose:function(){close(\''+list[i].msgId+'\');}"></div>'
								+'<div id="tt'+list[i].msgId+'">'
								+'<a href="javascript:void(0);" class="icon-edit" onclick="javascript:openReplyBoder(\''+list[i].msgId+'\');"></a>'
								+'</div>';
				}
				$("#msgList").html(html);
				$.parser.parse("#msgList");
			}else{
				alert(data.errorMsg);
			} 
		}
	});
}

function openReplyBoder(_msgId){
	
	var replyDlg = $('#replyDlg');
	$("#msgId").val(_msgId);
	replyDlg.dialog({onClose:function(){
		close(_msgId);
		}
	});
	replyDlg.dialog('open');
	
}

function reply(){
	$('#loading').loading('open');
	
	var msg = {
			msgId : $("#msgId").val(),//回复的msgId
			msgContent : $("#msgContent").text()//回复的新内容
			};
	
	$.ajax({
		dataType : "json",
		data : msg,
		type : "POST",
		async:false,
		url : window.contextPath+"/user/replyMsg.action",
		success : function(data) {
			$('#loading').loading('close');
			if(data.result=='success'){

			}else{
				$.messager.alert('提示',"异常,错误原因："+data.errorMsg,'warning');
			}
		}
	});	
}

function close(_msgId){
	$("#msgContent").text("");
	//alert('close msgId='+_msgId);
}