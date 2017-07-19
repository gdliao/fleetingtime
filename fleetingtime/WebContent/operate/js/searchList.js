var pageNo = 1;
var userId = parent.$("#userId").val();
var actList = [],likeList = [];
var pageSize;
$(function(){
	queryDailyList(1,$("#keywords").val());
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
	$("#dTitle").text(infoDetail.publishTitle);
	$("#dTime").text(dateFormate(infoDetail.publishTime));
	$("#dDes").val(infoDetail.infoDes);
	$("#infoId").val(infoDetail.infoId);
	//alert(JSON.stringify(infoDetail));
	//alert(JSON.stringify(actList));
	
	var html="";
	for(var i=0;i<actList.length;i++){
		if(actList[i].actId==infoDetail.actId){
			$("#dActList").html(actList[i].actName);
			$("#dActList").attr("data-actId",actList[i].actId);
		}
	}
	if(infoDetail.ifShare==1){
		$("#actP").show();
	}else{
		$("#actP").hide();
	}
	
	for(var i=0;i<likeList.length;i++){
		if(likeList[i].infoId==infoDetail.infoId&&likeList[i].actId==infoDetail.actId){
			$("#bootstrapDialog").find("div [name=likeButton]").each(function(i){
				$(this).html("<span class='glyphicon glyphicon-heart'>已赞</span>");
			});
			break;
		}else{
			$("#bootstrapDialog").find("div [name=likeButton]").each(function(i){
				$(this).html("<span class='glyphicon glyphicon-thumbs-up'>喜欢</span>");
			});
		}
	}
	
	$('#bootstrapDialog').modal();
}

function queryDailyList(_pageNo,_keywords){
	var param={
			page:_pageNo,
			keywords:_keywords
			};
	//alert(JSON.stringify(param));
	$.ajax({
		dataType : "json",
		type : "POST",
		data:param,
		async:false,
		url : window.contextPath+"/publication/queryDailyList.action",
		success : function(data) {
			if(data.result=="success"){
				pageNo = data.pageNo;
				likeList = data.likeList;
				//alert(JSON.stringify(data));
				//alert(JSON.stringify(likeList));
				/*遍历表数据*/
				var dlist = data.rows;
				var dataHtml = "";
				pageSize = data.nums.length;
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
			}else{
				alert("error");
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
	queryDailyList(pageNo,$("#keywords").val());
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


function like(_t){
	var infoId = $("#infoId").val();
	var actId = $("#dActList").attr("data-actId");
	if(""==userId||null==userId){
		alert("需要登录后才能投票，若未注册请先注册。");
		return;
	}
	var vote = {userId : userId,infoId : infoId,actId : actId};
	alert(JSON.stringify(vote));
	if($(_t).html().indexOf("glyphicon-heart")>0){
		alert('cancelVote');
		
		$.ajax({
			dataType : "json",
			type : "POST",
			data : vote,
			async:false,
			url : window.contextPath+"/activity/cancelVote.action",
			success : function(data) {
				if(data.result=="success"){
					$("#bootstrapDialog").find("div [name=likeButton]").each(function(i){
						$(this).html("<span class='glyphicon glyphicon-thumbs-up'>喜欢</span>");
					});
				}else{
					if(data.errorMsg.indexOf("index_vote_actid_infoid_userid")>0){
						alert("再喜欢也只能投一次呦。");
					}else{
						alert("系统异常："+data.errorMsg+"错误代码："+data.errorCode);
					}
				} 
			}
		});	
		
	}else{
		alert('vote');
		$.ajax({
			dataType : "json",
			type : "POST",
			data : vote,
			async:false,
			url : window.contextPath+"/activity/like.action",
			success : function(data) {
				if(data.result=="success"){
					$("#bootstrapDialog").find("div [name=likeButton]").each(function(i){
							$(this).html("<span class='glyphicon glyphicon-heart'>已赞</span>");
					});
				}else{
					if(data.errorMsg.indexOf("index_vote_actid_infoid_userid")>0){
						alert("再喜欢也只能投一次呦。");
					}else{
						alert("系统异常："+data.errorMsg+"错误代码："+data.errorCode);
					}
				} 
			}
		});	
	}
	
	queryDailyList(pageNo,$("#keywords").val());
}