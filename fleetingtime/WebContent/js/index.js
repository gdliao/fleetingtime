var touchFlag=false;
var actId = "";
var likeList = [];

$(function(){
	touchFlag=ifTouch();
	initShare();
	loginOrWrite();
	initScroll();
	$('#registerDialog').on('hide.bs.modal', function(){reloadImage();});
});

function loginOrWrite(){
	if($("#userId").val()!=""){
		$("#doMainWin").show();
		$("#loginWin").hide();
		$("#doMainFrame").attr("src","textFrame.jsp");
		$("#checkoutlink").show();
		likeList = $("#likeList").val().split(",");
		$("#roll2").find("a").each(function(i){
			for(var i=0;i<likeList.length;i++){
				if($(this).attr("data-infoId")==likeList[i]){;
					$(this).html("<span class='glyphicon glyphicon-heart'></span>");
				}
			}
		});
	}else{
		$("#loginWin").show();
		$("#checkoutlink").hide();
	}
}

function login(){
	
	var loginNo = $("#loginNo").val();
	var password = $("#password").val();
	var rand = $("#rand").val();
	
	var param = {loginNo:loginNo,password:password,rand:rand};
	
	$.ajax({
		dataType : "json",
		type : "POST",
		data:param,
		async:false,
		url : window.contextPath+"/user/login.action",
		success : function(data) {
			if(data.result=="success"){
				$("#loginWin").hide();
				$("#doMainWin").show();
				$("#userId").val(data.data.userId);
				$("#checkoutlink").show();
				$("#doMainFrame").attr("src","textFrame.jsp");//?userId= +userId
			}else{
				alert(data.errorMsg);
			} 
		}
	});	
	
}

function initScroll(){
	var speed2 = 80;
	var rollbottom = document.getElementById("rollbottom");
	var rolltop = document.getElementById("rolltop");
	var roll2 = document.getElementById("roll2");
	rollbottom.innerHTML = rolltop.innerHTML;
	function Marquee2() {
		if (rollbottom.offsetHeight - roll2.scrollTop <= 0) {
			roll2.scrollTop -= rolltop.offsetHeight;
		} else {
			roll2.scrollTop++;
		}
	}
	var MyMar2 = setInterval(Marquee2, speed2);
	
	if(touchFlag){
		roll2.ontouchstart = function() {
			clearInterval(MyMar2);
		};
		roll2.ontouchend = function() {
			MyMar2 = setInterval(Marquee2, speed2);
		};
	}else{
		roll2.onmouseover = function() {
			clearInterval(MyMar2);
		};
		roll2.onmouseout = function() {
			MyMar2 = setInterval(Marquee2, speed2);
		};
	}
	
}

function initShare(){
	$.ajax({
		dataType : "json",
		type : "POST",
		async:false,
		url : window.contextPath+"/publication/queryShare.action",
		success : function(data) {
			if(data.result=="success"){
				var shareList = data.data.list;
				$("#subject").append(data.data.subject);
				var html="<table><tr><td><div>";
				for(var i=0;i<shareList.length;i++){
					if(i==0){
						actId = shareList[i].actId;
					}
					html=html+'<h3>'+shareList[i].publishTitle+'</h3>'+
						 '<p>'+dateFormate(shareList[i].publishTime)+'</p>'+
						 '<p>'+shareList[i].infoDes+'</p>'+
						 '<a data-infoId="'+shareList[i].infoId+'" onclick="like(this);"><span class="glyphicon glyphicon-thumbs-up"></span></a>&nbsp;&nbsp;&nbsp;&nbsp;<a onclick="share('+shareList[i].infoId+');">'+shareHtml+'</a>'+
						 '</div></td></tr>';//<span class="glyphicon glyphicon-share"></span>
				}
				$("#rolltop").html(html);
			}else{
				alert("系统异常："+data.errorMsg+"错误代码："+data.errorCode);
			} 
		}
	});	
}

function like(_t){
	var userId = $("#userId").val();
	var infoId = $(_t).attr("data-infoId");
	if(""==userId||null==userId){
		alert("需要登录后才能投票，若未注册请先注册。");
		return;
	}
	var vote = {userId : userId,infoId : infoId,actId : actId};
	
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
					
					$("#roll2").find("a").each(function(i){
						if($(this).attr("data-infoId")==infoId){
							$(this).html("<span class='glyphicon glyphicon-thumbs-up'></span>");
						}
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
		$.ajax({
			dataType : "json",
			type : "POST",
			data : vote,
			async:false,
			url : window.contextPath+"/activity/like.action",
			success : function(data) {
				if(data.result=="success"){
					
					$("#roll2").find("a").each(function(i){
						if($(this).attr("data-infoId")==infoId){
							$(this).html("<span class='glyphicon glyphicon-heart'></span>");
						}
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
}
function share(_t){
	alert('share'+_t);
}

function dateFormate(_date){
	var date = new Date(_date);
	
	var year = date.getFullYear();  // 获取完整的年份(4位,1970)
	var month = pad(date.getMonth()+1,2);    // 获取月份(0-11,0代表1月,用的时候记得加上1)
	var day = pad(date.getDate(),2);   // 获取日(1-31)
	
	return year +"年"+month+"月"+day+"日";
}
function pad(num, length) {   
	return (Array(length).join('0') + num).slice(-length); 
}
function ifTouch(){
    var touchObj={};
    touchObj.isSupportTouch = "ontouchend" in document ? true : false;
    touchObj.isEvent=touchObj.isSupportTouch?true:false;
    return touchObj.isEvent;
}

function openDialog(){
	$('#registerDialog').modal();
}

function register(){
	var param = {
			userName : $("#userName").val(),
			phoneNum : $("#phoneNum").val(),
			email : $("#email").val(),
			password : $("#repassword").val(),
			rand : $("#rRand").val()
	};
	//alert(JSON.stringify(param));
	$.ajax({
		dataType : "json",
		type : "POST",
		data : param,
		async:false,
		url : window.contextPath+"/user/register.action",
		success : function(data) {
			if(data.result=="success"){
				//alert(JSON.stringify(data));
				$("#registerDialog").modal("hide");
				$("#loginWin").hide();
				$("#doMainWin").show();
				$("#userId").val(data.data.userId);
				$("#doMainFrame").attr("src","textFrame.jsp");
			}else{
				alert("系统异常："+data.errorMsg+"错误代码："+data.errorCode);
			} 
		}
	});	
}

function check(_t){
	switch(_t.id){
	case "loginNo":
		checkRule.loginNo($(_t).val());
		break;
	case "userName":
		checkRule.userName($(_t).val());
		break;
	case "email":
		checkRule.email($(_t).val());
		break;
	case "password":
		checkRule.password($(_t).val());
		break;
	case "comfirmPassword":
		checkRule.comfirmPassword($(_t).val());
		break;
	case "verificationCode":
		checkRule.verificationCode($(_t).val());
		break;
	}
}

var checkRule={
		loginNo:function(_t){
			if(_t.length>0){
				var mobile = /^1[3|5|8|4|7]\d{9}$/;
				if (!mobile.test(_t)) {
					$("#loginNoCheck").show();
				}else{
					$("#loginNoCheck").hide();
				}	
			}else{
				$("#loginNoCheck").hide();
			}
			
		},
		userName:function(_t){
			var reg =/^[\u4e00-\u9fa5_a-zA-Z0-9\s，。、；,.;?？\/'’\"\”“‘]{0,16}$/;
			if (!reg.test(_t)){
				$("#userNameCheck").show();
			}else{
				$("#userNameCheck").hide();
			}	
			
		},
		email:function(_t){
			var reg = /^(?:\w+\.?)*\w+@(?:\w+\.)*\w+$/;
			if(!reg.test(_t)){
				$("#emailCheck").show();
			}else{
				$("#emailCheck").hide();
			}
		},
		password:function(_t){
			
		},
		comfirmPassword:function(_t){
			if(_t!=$("#repassword").val()){
				$("$#comfirmPasswordCheck").show();
			}else{
				$("$#comfirmPasswordCheck").hide();
			}
		},
		verificationCode:function(_t){
			
		}
};

function logout(){
	if(confirm('确定注销吗？')){
		$.ajax({
			dataType : "json",
			type : "POST",
			async:false,
			url : window.contextPath+"/user/logout.action",
			success : function(data) {
				if(data.result=="success"){
					top.location.href=window.contextPath + "/index.html";
				}else{
					$.messager.alert('提示',"注销失败，错误代码："+data.errorCode+",错误原因："+data.errorMsg,'warning');
				} 
			}
		});
	}
}

var shareHtml='<div class="bdsharebuttonbox"><a href="#" class="bds_more" data-cmd="more"></a><a href="#" class="bds_qzone" data-cmd="qzone"></a><a href="#" class="bds_tsina" data-cmd="tsina"></a><a href="#" class="bds_tqq" data-cmd="tqq"></a><a href="#" class="bds_renren" data-cmd="renren"></a><a href="#" class="bds_weixin" data-cmd="weixin"></a></div><script>window._bd_share_config={"common":{"bdSnsKey":{},"bdText":"","bdMini":"2","bdPic":"","bdStyle":"0","bdSize":"16"},"share":{},"image":{"viewList":["qzone","tsina","tqq","renren","weixin"],"viewText":"分享到：","viewSize":"16"},"selectShare":{"bdContainerClass":null,"bdSelectMiniList":["qzone","tsina","tqq","renren","weixin"]}};with(document)0[(getElementsByTagName("head")[0]||body).appendChild(createElement("script")).src="http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion="+~(-new Date()/36e5)];</script>';