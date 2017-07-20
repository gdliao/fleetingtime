var roleListTable;
var roleInfo;
$(function(){
	initRoleListTable();
	initRightsTree();
});
function initRoleListTable(){
	roleListTable=$("#roleListTable").datagrid({
		url : window.contextPath+"/userRole/queryRoleListPaging.action",//null,
		fit : false,
		nowrap : false,
		striped : true,
		fitColumns : true,
		idField : "roleId",
		scrollbarSize : 0,
		fitColumns : true,
		rownumbers : false,
		checkbox : true,
		columns : [ [ {
			field : "roleId",
			title : "编码",
			width : 10,
			align : "center"
		}, {
			field : "roleName",
			title : "角色名",
			width : 100,
			align : "center"
		} ] ],
		toolbar:'#tb',
		pagination : true,
		singleSelect : true,
		pageNumber : 1,
		pageSize : 15,
		pageList : [ 5, 10, 15 ],
		onLoadSuccess :function(data){
			//alert(JSON.stringify(data));
		},
		onClickRow : function(){
			roleInfo = roleListTable.datagrid('getSelections');
			//alert(JSON.stringify(roleInfo));
		},
		onDblClickRow : function(){
			roleInfo = roleListTable.datagrid('getSelections');
			editRole();
		}
	});
}
function initRightsTree(){
	$.ajax({
		dataType : "json",
		type : "POST",
		async:false,
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

function editRole(_this){
	
	var type = "add"== $(_this).attr("data-type") ? "add":"edit";
	var url = "/busipages/rightsManagement/editRoleInfo/"+type+".jsp?type="+type;
	var tWin = $("#"+type+"Win").dialog({
		title:type=="add"?"新增角色":"修改角色",
		resizable : false,
		top: 130,
	    width: 400,
	    height: 500,
	    closed: false,
	    cache: false,
	    modal: true,
	    onClose: function(){
	    	$("#"+type+"Win").html('<iframe src="" id="'+type+'Frame" name="'+type+'Frame" frameborder="0" scrolling="no" style="width: 100%; height: 95%;"></iframe>');
	    }
	});
	$("#"+type+"Frame").attr("src",window.contextPath + url);
	tWin.dialog("open");
}

function commit(){
/*	$.ajax({
		dataType : "json",
		type : "POST",
		async:false,
		url : window.contextPath+"/userRole/getRoleTreeList.action",
		success : function(data) {
			if(data.result=='success'){
			
			}else{
				alert("创建成功");
			}
		}
	});*/
}