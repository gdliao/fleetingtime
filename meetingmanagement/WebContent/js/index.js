var funcList=[];
//判断浏览器是否有数组的indexOf方法 没有则增加
if (!Array.indexOf) {  
    Array.prototype.indexOf = function (obj) {  
        for (var i = 0; i < this.length; i++) {  
            if (this[i] == obj) {  
                return i;  
            }  
        }  
        return -1;  
    };
}

$(function(){
	initUserRole();
	messager();
	//window.setInterval("messager()",10000);
});

function initUserRole(){
	$.ajax({
		dataType : "json",
		type : "POST",
		url : window.contextPath+"/login/getUserFunc.action",//.action
		success : function(data) {
			if(data.result=="success"){
					if(null!=data.data[0]&&""!=data.data[0]&&undefined!=data.data[0]){
							var roleFunc = data.data;//返回的所有功能点的json数据;
							var roleFuncParentIdList=[];//用来存放父节点id的list
							
							var roleFuncChildIdList = [];//存放子节点id的list
							var headFuncTitle=roleFunc[0].func.funcName;//第一个栏目的名称
							
							for(var i=0;i<roleFunc.length;i++){
								if(roleFuncParentIdList.indexOf(roleFunc[i].func.funcId)==-1){
									
									if(roleFunc[i].func.parentId=='0'){
										roleFuncParentIdList.push(roleFunc[i].func.funcId);
									}else{
										roleFuncChildIdList.push(roleFunc[i].func.funcId);
									}
								}
							}
							//存放已经添加到左边栏的父节点Id
							var alreadyList=[];
							//拼装左边栏功能信息
							for(var k=0;k<roleFuncParentIdList.length;k++){
								
								var htmlLi="";
								var childFuncIdList=[];
								for(var i=0;i<roleFunc.length;i++){
									var func = roleFunc[i].func;
									if(func.parentId==roleFuncParentIdList[k]&&childFuncIdList.indexOf(func.funcId)==-1){
										htmlLi=htmlLi+"<li><a onclick='addTab("+func.funcId+")'>"+func.funcName+"</a></li>";
										var t = {'funcId':func.funcId,'funcName':func.funcName,'url':func.url};
										childFuncIdList.push(func.funcId);
										funcList.push(t);
									}
								}
								
								for(var i=0;i<roleFunc.length;i++){
									var func = roleFunc[i].func;
									if(func.parentId=='0'&&func.funcId==roleFuncParentIdList[k]&&alreadyList.indexOf(func.funcId)==-1){
										addMenu(func.funcName,"<ul class='easyui-datalist' data-options='border:false,fit:true'>"+htmlLi+"</ul>");
										alreadyList.push(func.funcId);
									}
								}
							}
							$('#leftMenu').accordion('select',headFuncTitle);
						
					}else{
						$.messager.alert('提示',"未配置权限，请联系管理员配置该用户权限！",'warning');
					}
				}else{
				$.messager.alert('提示',"初始化异常，错误代码："+data.errorCode+",错误原因："+data.errorMsg,'warning');
			} 
		}
	});
}

function addMenu(_title,_content){
	$("#leftMenu").accordion('add',{
		title:_title,
		content:_content
	});
}

function addTab(_funcId){
	var funcName = "";
	var flag=false;
	
	for(var i=0;i<funcList.length;i++){
		
		if(funcList[i].funcId==_funcId){
			funcName=funcList[i].funcName;
			url=window.contextPath+funcList[i].url;
			
			$("#rightTabs").find('ul[class=tabs]').find('span[class="tabs-title tabs-closable"]').each(function(){
				if($(this).text()==funcName){
						//点击相同标签时不重复打开相同tab
						$(this).parent().parent().parent().find('li').each(function(){
							$(this).removeClass('tabs-selected');
						});
						//选中该tab页
						$(this).parent().parent().addClass('tabs-selected');
						flag=true; 
						return;
					}
			});
			
			if(flag){
				flag=false;
				$("#rightTabs").find('div[class=tabs-panels]').find('div[class=panel]').each(function(){
					$(this).hide();
					if($(this).find("iframe").attr('name')==funcName){
						$(this).show();
					}
				});
				return;
				}
			
			$('#rightTabs').tabs('add',{
				title: funcName,
				content: '<iframe name="'+funcName+'" src="'+url+'" style="border: 0px;width: 100%;height:100%;overflow: hidden;" ></iframe>',
				closable: true
			});
			
			return;
		}
	}
}

function exit(){
	$.messager.confirm('注销', '确定注销吗？', function(r){
		if(r){
			$.ajax({
				dataType : "json",
				type : "POST",
				url : window.contextPath+"/login/exit.action",
				success : function(data) {
					if(data.result=="success"){
						window.location.href=window.contextPath + "/login.jsp";
					}else{
						$.messager.alert('提示',"注销失败，错误代码："+data.errorCode+",错误原因："+data.errorMsg,'warning');
					} 
				}
			});
		}
    });
}

function messager(){
	$.ajax({
		dataType : "json",
		type : "POST",
		url : window.contextPath+"/user/queryUserMsg.action",
		success : function(data) {
			if(data.result=="success"){
				if(data.count>0){
					$.messager.show({
				        title:'消息窗口',
				        msg:'<a href="javascript:showMsg();">您有 '+data.count+'  条新信息！</a>',
				        showType:'show'
				    });
				}
			}else{
				//$.messager.alert('提示',"注销失败，错误代码："+data.errorCode+",错误原因："+data.errorMsg,'warning');
			} 
		}
	});


	
    /*$.messager.show({
        title:'My Title',
        msg:'The message content',
        showType:'show'
    });*/
}