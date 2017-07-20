var usersListTable;
var userInfo;
$(function(){
	initUsersList();
});

function searchByUsername(){
	usersListTable.datagrid({
		queryParams : {userName:$("#userName").val()},
	});
}

function initUsersList(){
	usersListTable = $("#usersListTable").datagrid({
		url : window.contextPath+"/user/queryUserList.action",//null,
		fit : false,
		nowrap : false,
		striped : true,
		fitColumns : true,
		idField : "userId",
		scrollbarSize : 0,
		fitColumns : true,
		rownumbers : true,
		columns : [ [ {
			field : "userId",
			title : "用户编码",
			width : 10,
			align : "center"
		}, {
			field : "userName",
			title : "用户名",
			width : 10,
			align : "center"
		}, {
			field : "email",
			title : "邮箱",
			width : 10,
			align : "center"
		}, {
			field : "phoneNumber",
			title : "联系方式",
			width : 10,
			align : "center"
		} ] ],
		toolbar:'#tb',
		pagination : true,
		singleSelect : true,
		pageNumber : 1,
		pageSize : 5,
		pageList : [ 5, 10, 15 ],
		onLoadSuccess :function(data){
			//alert(JSON.stringify(data));
		},
		onClickRow : function(){
			userInfo = usersListTable.datagrid('getSelections');
		},
		onDblClickRow : function(){
			//userInfo = usersListTable.datagrid('getSelections');
			editUser();
		}
	});
}

function addUser(){
	var addWin = $("#addWin").dialog({
			title:"新增用户",
			resizable : false,
			top: 180,
		    width: 400,    
		    height: 300,    
		    closed: false,    
		    cache: false,    
		    modal: true
		});
	$("#addFrame").attr("src",window.contextPath +"/busipages/userlist/addUser/add.jsp");
	addWin.dialog("open");
}

function editUser(){
	var editWin = $("#editWin").dialog({
			title:"修改用户信息",
			resizable : false,
			top: 130,
		    width: 400,    
		    height: 600,    
		    closed: false,    
		    cache: false,    
		    modal: true
		});
	$("#editFrame").attr("src",window.contextPath +"/busipages/userlist/editUserInfo/edit.jsp");
	editWin.dialog("open");
}