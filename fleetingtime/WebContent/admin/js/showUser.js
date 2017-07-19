var usersListTable;
$(function(){
	initUsersList();
});

function initUsersList(){
	usersListTable = $("#usersListTable").datagrid({
		url :  window.contextPath+"/user/getuserspaging.action",//null,
		fit : false,
		nowrap : false,
		striped : true,
		fitColumns : true,
		idField : "EXEC_CODE",
		scrollbarSize : 0,
		fitColumns : true,
		rownumbers : true,
		columns : [ [ {
			field : "id",
			title : "编号",
			width : 10,
			align : "center"
		}, {
			field : "username",
			title : "用户名",
			width : 10,
			align : "center"
		}, {
			field : "sex",
			title : "性别",
			width : 10,
			align : "center"
		}, {
			field : "nation",
			title : "国籍",
			width : 10,
			align : "center"
		} ] ],
		pagination : true,
		singleSelect : true,
		pageNumber : 1,
		pageSize : 5,
		pageList : [ 5, 10, 15 ],
		onLoadSuccess :function(data){
			//alert(JSON.stringify(data));
		}
	});
}
function initdata(){
	$.ajax({
		dataType : "json",
		type : "POST",
		url : window.contextPath+"/user/getuserspaging.action",
		success : function(data) {
			//alert(JSON.stringify(data));
		}
	});
}