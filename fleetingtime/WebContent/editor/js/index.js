var touchFlag=false;
$(function(){
	touchFlag=ifTouch();
	initShare();
	initScroll();
});

function login(){
	
	var loginNo = $("#loginNo").val();
	var password = $("#password").val();
	var rand = $("#rand").val();
	//alert("loginNo123="+loginNo+",password="+password+",url="+window.contextPath+"/user/login.action");
	
	var param = {loginNo:loginNo,password:password,rand:rand};
	$.ajax({
		dataType : "json",
		type : "POST",
		data:param,
		url : window.contextPath+"/user/login.action",
		success : function(data) {
			if(data.result=="success"){
				$("#loginWin").hide();
				$("#doMainWin").show();
				alert(JSON.stringify(data));
				//var userId = data.data.
				$("#doMainFrame").attr("src","textFrame.jsp");//?userId= +userId
			}else{
				alert("error");
			} 
		}
	});	
	
}

function initScroll(){
	var speed2 = 50;
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
				//alert(JSON.stringify(data));
				var shareList = data.data.list;
				$("#subject").append(data.data.subject);
				var html="<table><tr><td><div>";
				for(var i=0;i<shareList.length;i++){
					html=html+'<h3>'+shareList[i].publishTitle+'</h3>'+
						 '<p>'+dateFormate(shareList[i].publishTime)+'</p>'+
						 '<p>'+shareList[i].infoDes+'</p>'+
						 '<a onclick="like('+shareList[i].infoId+');"><span class="glyphicon glyphicon-thumbs-up"></span></a>&nbsp;&nbsp;&nbsp;&nbsp;<a onclick="share('+shareList[i].infoId+');"><span class="glyphicon glyphicon-share"></span></a>'+
						 '</div></td></tr>';
						//&nbsp;&nbsp;&nbsp;&nbsp;<a onclick="unlike('+shareList[i].infoId+');"><span class="glyphicon glyphicon-thumbs-down"></span></a>
				}
				$("#rolltop").html(html);
			}else{
				alert("error");
			} 
		}
	});	
}

function like(_t){
	alert('like'+_t);
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
