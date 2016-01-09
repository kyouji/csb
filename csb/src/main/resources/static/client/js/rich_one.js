////////////////计算heml 高
function html_hi(){
	var oHtml = document.getElementsByTagName('html')[0];
	var win_hi = window.screen.height;
	var doc_hi =document.documentElement.offsetHeight;
	if(doc_hi>=win_hi){
		oHtml.style.height = doc_hi + 'px';
	}else{
		oHtml.style.height = win_hi + 'px';
	};
	
};

////////////////////////////////////////
////////////////////////////////////////tel_out电话弹出
function tel_out(obj){
	var oTel = rich(obj)[0];
	var tBox = oTel.children[0];
	oTel.style.height = window.screen.height + 'px';
	var my_top = (window.screen.height - tBox.offsetHeight)/2;
	tBox.style.marginTop = my_top +'px';
};	
function phone_show(obj){
	rich(obj)[0].style.display = 'block';
	tel_out(obj);
};
function phone_hide(obj){
	rich(obj)[0].style.display = 'none';
};
//////////////////////////
function teaty_show(obj){
	rich(obj)[0].style.display = 'block';
	var oTel = rich(obj)[0];
	var tBox = oTel.children[0];
	oTel.style.height = window.screen.height + 'px';
};
////////////////////////////////////////////////////////
////////////////////////////////////////////////////////poto
function img_big(){
	var oBox = rich('.poto_box')[0];
	var aLi = oBox.children;
	var len = aLi.length;
	var aAffi = rich('.affirm')[0];
	var aFdet =rich('.affirm')[0].children[0];
	var aFback =rich('.affirm')[0].children[1];
	for(var i=0;i<len-1;i++){
		aLi[i].onclick =function(){
			var oA = this.children[1];
			var wi_fa =aLi[0].parentNode;
			var wi_fater = richStyle(wi_fa,'width');
			
			if(this.style.width !=='100%'){
				for(var i=0;i<len;i++){
					aLi[i].style.display = 'none';
				};
				this.style.display = 'block';
				this.style.margin = '0px';
				//this.style.overflow = 'hidden';
				this.style.width = '100%';
				this.style.height = '100%';
				oA.style.display = 'block';
			}else{
				//this.style.overflow = 'auto';
				this.style.width = '19%';
				this.style.height = '40%';
				this.style.marginLeft = '1%';
				oA.style.display = 'none';
				var timer = setTimeout(function(){				
					for(var i=0;i<len;i++){
						aLi[i].style.display = 'block';
					};
				},1000);
			};
				
			oA.onclick =function(){
				event.stopPropagation();///阻止 冒泡
				var oPare = oA.parentNode;
				aAffi.style.display = 'block';
				aFdet.onclick =function(){
					oBox.removeChild(oPare);
					len--;
					aAffi.style.display = 'none';
					for(var i=0;i<len;i++){
						aLi[i].style.display = 'block';
					};
				};
				aFback.onclick = function(){
					aAffi.style.display = 'none';
				};	
			};
		};
	};
	var aChoice = rich('#choice_poto');
	var aDd = rich('.add_poto')[0];
	//var aDd_add = rich('.add_poto')[0].children[0];
	var aDd_back= rich('.add_poto')[0].children[2];	
	aChoice.onclick = function(){
//		aDd.style.display = 'block';
//		aDd_back.onclick = function(){
//			aDd.style.display = 'none';
//		};
		$("#file").click();		
	};
	
};


/*--------------------------zhangji 注册 begin ---------------------*/
//注册页面切换
function regChangeRole()
{
	form = document.forms["regForm"];
	form.submit();
}

//提交表单-公司
function regEnterSubmit()
{
	var enterName = document.getElementById("enterName").value; //公司名称
	//取出radio选择的值 zhangji
	//type
	var enterTypes = document.getElementsByName("enterType");  //取出所有该名字的标签数组
	var typeIndex = 0;   //初始化数组序号
	for(var i=0; i<enterTypes.length; i++)
		{
			if(enterTypes[i].checked = true)    //遍历出被选中的那个radio
				{
					typeIndex = i;
					break;
				}
		}
	var enterType = $("input[name='enterType']:checked").val(); //赋值
	var enterTypeId = $("input[name='enterTypeId']:checked").val(); //赋值
	
	var roleId = document.getElementById("roleId").value;
	var realName = document.getElementById("realName").value;
	var username = document.getElementById("username").value;
	var mobile = document.getElementById("mobile").value;
	var password = document.getElementById("password").value;
	var password2 = document.getElementById("password2").value;
	
	  $.ajax({
	      type:"post",
	      url:"/reg",
	      data:{"roleId":roleId,
	    	  		"enterType":enterType,
	    	  		"enterTypeId":enterTypeId,
	    	  		"realName":realName,
	    	  		"username":username,
	    	  		"mobile":mobile,
	    	  		"enterName":enterName,
	    	  		"password":password,
	    	  		"password2":password2},
	      success:function(data){
			if (data.code == 1)
			{
	            alert(data.msg);
			}
			else{
				alert("注册成功！欢迎使用财税宝")
				location.href="/user/info?hatu=0";
			}
	      }
	  });
}

//提交表单-会计
function regAccSubmit()
{
	var roleId = document.getElementById("roleId").value;
	var realName = document.getElementById("realName").value;
	var username = document.getElementById("username").value;
	var mobile = document.getElementById("mobile").value;
	var password = document.getElementById("password").value;
	var password2 = document.getElementById("password2").value;
	
	  $.ajax({
	      type:"post",
	      url:"/reg",
	      data:{"roleId":roleId,
	    	  		"realName":realName,
	    	  		"username":username,
	    	  		"mobile":mobile,
	    	  		"password":password,
	    	  		"password2":password2},
	      success:function(data){
			if (data.code == 1)
			{
	            alert(data.msg);
			}
			else{
				alert("注册成功！欢迎使用财税宝")
				location.href="/user/info?hatu=1";
			}
	      }
	  });
}

//公司注册选择类型
function selectType(id)
{
	$(".input02").removeClass("inputSelected");
	$(".type_radio").removeAttr("checked");
	
	$("#enterType"+id).attr("checked","checked");
	$("#enterTypeId"+id).attr("checked","checked");
	$("#type"+id).addClass("inputSelected");
}	

/*--------------------------zhangji 注册 end ---------------------*/

/*--------------------------zhangji 登陆 begin ---------------------*/
function loginSubmit()
{
	var username = document.getElementById("username").value; //用户名
	var password = document.getElementById("password").value; //密码
	
	  $.ajax({
	      type:"post",
	      url:"/login",
	      data:{"username":username,
	    	  		"password":password},
	      success:function(data){
			if (data.code == 1)
			{
	            $("#alert_msg").html(data.msg);
			}
			else{
				location.href="/index";
			}
	      }
	  });
}





