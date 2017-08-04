var pageNo = 1;
var actList = [];
var pageSize;
var userId = parent.$("#userId").val();
$(function(){
	queryDailyList(userId);
	queryActivity();
});

function bindclick(){
	$("#table tr[name=queryData]").click(function(){
		showDetail($(this).attr("data-infoId"));
	});
}

function showActList(_t){
	if(_t==1){
		$("#actP").show();
		$("#limitDiv").show();
	}else{
		$("#actP").hide();
		/*隐藏字数限制 并清空*/
		$("#limitDiv").hide();
		$("#content").removeAttr("maxlength");
	}
}

function showDetail(_t){
	var infoList = $("#infoList").data("infoList");
	var infoDetail ={};
	for(var i=0;i<infoList.length;i++){
		if(_t==infoList[i].infoId){
			infoDetail = infoList[i];
			break;
		}
	}
	$("#dTitle").val(infoDetail.publishTitle);
	$("#dTime").text(dateFormate(infoDetail.publishTime));
	$("#dDes").val(infoDetail.infoDes);
	$("#infoId").val(infoDetail.infoId);
	//alert(JSON.stringify(infoDetail));
	//alert(JSON.stringify(actList));
	
	var html="<option value='0'>--选择一个活动--</option>";
	for(var i=0;i<actList.length;i++){
		if(actList[i].actId==infoDetail.actId){
			html+="<option selected value='"+actList[i].actId+"' data-limit='"+actList[i].wordLimit+"'>"+actList[i].actName+"</option>";
		}else{
			html+="<option value='"+actList[i].actId+"' data-limit='"+actList[i].wordLimit+"'>"+actList[i].actName+"</option>";
		}
		
	}
	$("#dActList").html(html);
	if(infoDetail.ifShare==1){
		$("#dIfShare").find("option[value=1]").attr("selected",true);
		$("#actP").show();
		getLimit();
		$("#limitDiv").show();
	}else{
		$("#dIfShare").find("option[value=0]").attr("selected",true);
		$("#actP").hide();
		$("#limitDiv").hide();
	}
	
	$('#bootstrapDialog').modal();
}

function queryDailyList(_userId,_pageNo,_keywords){
	var param={
			userId:_userId,
			page:_pageNo,
			keywords:_keywords
			};
	$.ajax({
		dataType : "json",
		type : "POST",
		data:param,
		async:false,
		url : window.contextPath+"/publication/queryDailyList.action",
		success : function(data) {
			if(data.result=="success"){
				pageNo = data.pageNo;
				pageSize = data.nums.length;
				/*遍历表数据*/
				var dlist = data.rows;
				
				var dataHtml = "";
				if(dlist.length==0){
					dataHtml = "<tr name='queryData'><th><h4>您还没写过日志！</h4></th></tr>";
					$("#table").append(dataHtml);
				}else{
					for(var i=0;i<dlist.length;i++){
						dataHtml+="<tr name='queryData' data-infoId='"+dlist[i].infoId+"'><td>"+dlist[i].publishTitle+"</td></tr>";
					}
					dataHtml+="<input id='infoList' type='hidden'>";
					$("tr[name='queryData']").remove();
					$("#table").append(dataHtml);
					$("#infoList").data("infoList",dlist);
					
					bindclick();//更新完数据 给所有tr绑定单击事件
					
					/*遍历分页*/
					var pageNums = data.nums;
					var pagingHtml = "<li><a onclick='paging(this)'>&laquo;</a></li>";
					for(var i=0;i<pageNums.length;i++){
						if(pageNums[i]==pageNo){
							pagingHtml += "<li><a onclick='paging(this)' style='background-color:#eee;'>"+pageNums[i]+"</a></li>";
						}else{
							pagingHtml += "<li><a onclick='paging(this)'>"+pageNums[i]+"</a></li>";
						}
					}
					pagingHtml += "<li><a onclick='paging(this)'>&raquo;</a></li>";
					$("#paging").html(pagingHtml);
				}

			}else{
				alert("查询日志列表失败。错误编码："+data.errorCode+"   错误原因："+data.errorMsg);
			}
		}
	});	
}

function dateFormate(_date){
	var date = new Date(_date);
	
	var year = date.getFullYear();  // 获取完整的年份(4位,1970)
	var month = pad(date.getMonth()+1,2);    // 获取月份(0-11,0代表1月,用的时候记得加上1)
	var day = pad(date.getDate(),2);   // 获取日(1-31)
	/*var hour = pad(date.getHours(),2);  // 获取小时数(0-23)
	var min = pad(date.getMinutes(),2);  // 获取分钟数(0-59)
	var sec = pad(date.getSeconds(),2);  // 获取秒数(0-59)*/	

	return year +"-"+month+"-"+day;
}

function pad(num, length) {   
	return (Array(length).join('0') + num).slice(-length); 
} 

function paging(_t){
	
	if($(_t).text()=="»"){
		if(pageNo<pageSize){
			pageNo=pageNo+1;
		}
	}else if($(_t).text()=="«"){
		if(pageNo>1){
			pageNo=pageNo-1;
		}
	}else{
		pageNo=$(_t).text();
	}
	queryDailyList(userId,pageNo);
}

function queryActivity(){
	$.ajax({
		dataType : "json",
		type : "POST",
		async:false,
		url : window.contextPath+"/publication/queryActivity.action",
		success : function(data) {
			if(data.result=="success"){
				actList = data.data;
			}else{
				alert(data.errorMsg);
			} 
		}
	});
}

function getLimit(){
	var maxLength = $("#dActList option:selected").attr("data-limit");
	$("#dDes").attr("data-limit",maxLength);
	$("#maxLimit").html(maxLength);
	
	$("#rest").html(maxLength-$("#dDes").val().length);
	$("#count").html($("#dDes").val().length);
	if($("#dActList").val()=="0"){
		$("#limitDiv").hide();
	}else{
		$("#limitDiv").show();
	}
}

function textCounter(_t) {//field, countfield, maxlimit
	
	var length = $(_t).val().length;
	var maxlimit = $(_t).attr("data-limit");
	var rest = maxlimit-length;
	if($("#dIfShare").val()==1){
		$(_t).attr("maxlength",maxlimit);
	}
	$("#rest").html(rest);
	$("#count").html(length);
}

function commit(){
	
	var ifShare = $("#dIfShare").val();
	
	
	var infoPublish = {
			publishTitle : $("#dTitle").val(),
			infoId : $("#infoId").val(),
			infoDes:$("#dDes").val(),
			userId:userId,
			ifShare :ifShare
			};
	
	if(ifShare==1){
		infoPublish['actId'] = $("#dActList").val();
		
		var limit = $("#dDes").attr("data-limit");
		var count = $("#dDes").val().length;
		if(count>limit){
			alert("已超过本次活动的限制字数"+(count-limit)+"个字，修改后重新提交。");
			return;
		}
		if($("#dActList").val()=='0'){
			alert("分享给别人，必须选择一个想要参加的主题活动呦~");
			return;
		}
	}
	//alert(JSON.stringify(infoPublish));//return;
	$.ajax({
		dataType : "json",
		type : "POST",
		data:infoPublish,
		async:false,
		url : window.contextPath+"/publication/update.action",
		success : function(data) {
			if(data.result=="success"){
				alert("success");
				queryDailyList(userId);
			}else{
				alert(data.errorMsg);
			} 
		}
	});	
}
