var roleInfo={},roleTreeList,type;
$(function(){
	$('#loading').loading('open');
	init();
});
function init(){
	initRightsTree();
	type=$("#type_bak").val();
	if(type=="edit"){
		roleInfo=parent.roleInfo[0];
		initrolFuncInfo();
	}else{
		initAllRole();
	}
	$('#loading').loading('close');
	
}
function initrolFuncInfo(){
	//alert(JSON.stringify(roleInfo));
	var param = {roleId: roleInfo.roleId};
	$.ajax({
		dataType : "json",
		type : "POST",
		data : param,
		async:false,
		url : window.contextPath+"/userRole/queryRoleFunctionList.action",
		success : function(data) {
			if(data.result=='success'){
				var rolezFuncList = data.data;//alert(JSON.stringify(rolezFuncList.length));
				for(var i=0;i<roleTreeList.length;i++){
					for(var j=0;j<roleTreeList[i].children.length;j++){
						for(var k=0;k<rolezFuncList.length;k++){//alert("funcid="+rolezFuncList[k].funcId+",id="+roleTreeList[i].children[j].id);
							if(rolezFuncList[k].funcId == roleTreeList[i].children[j].id){
								roleTreeList[i].children[j]["checked"]=true;
							}
						}
					}
				}
				$("#rightsTree").tree({
					data : roleTreeList,
					animate:false,
					checkbox:true
				});
			}else{
				alert("查询失败");
			}
		}
	});
	$("#roleName").text(roleInfo.roleName);
}

function initAllRole(){
	$("#rightsTree").tree({
		data : parent.roleTreeList,
		animate:false,
		checkbox:true
	});
}

function initRightsTree(){
	$.ajax({
		dataType : "json",
		type : "POST",
		async: false,
		url : window.contextPath+"/userRole/getRoleTreeList.action",
		success : function(data) {
			if(data.result=='success'){
				roleTreeList = data.data;
				$("#rightsTree").tree({
					data : roleTreeList,
					animate:false,
					checkbox:true
				});
			}else{
				$.messager.alert('提示',"获取功能权限数据异常，错误编码："+data.errorCode+"错误原因:"+data.errorMsg,'warning');
			}
		}
	});
}

function commit(){
	var roleName=null;
	if(type=='add'){
		roleName = $("#roleName").val();
		if(null==roleName||""==roleName){
			$.messager.alert('提示',"请输入角色名称。",'warning');
			return;
		}
	}
	
	var nodes = $('#rightsTree').tree('getChecked');
	var roleFuncIdList = [];
	var funcNameList = [];
	for(var i=0; i<nodes.length; i++){
		roleFuncIdList.push(nodes[i].id);
		funcNameList.push(nodes[i].text);
	}
	
	if(roleFuncIdList.length==0){
		$.messager.alert('提示',"选择功能点。",'warning');
		return;
	}
	var roleStr={
			roleId:roleInfo.roleId,
			roleName:roleName,
			roleFuncIdList:JSON.stringify(roleFuncIdList)
			};
	alert(type);
	
	$.messager.confirm('确认', '确认'+ (type=="edit"?'修改':'添加')+'功能点：'+JSON.stringify(funcNameList), function(r){
		if(r){
			$.ajax({
				dataType : "json",
				type : "POST",
				async: false,
				data:roleStr,
				url : window.contextPath+"/userRole/"+type+"Role.action",
				success : function(data){
					if(data.result=='success'){
						alert("编辑成功");
					}else{
						$.messager.alert('提示',"操作异常，错误编码："+data.errorCode+"错误原因:"+data.errorMsg,'warning');
					}
				}
			});
		}
    });
	
	
	
}