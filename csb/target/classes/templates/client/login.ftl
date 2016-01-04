<!DOCTYPE html>
<html>
	<head>
		<style type="text/css">
			html,body{width: 100%;height: 100%;}
		</style>
		<meta name="keywords" content="">
		<meta name="description" content="">
		<meta name="copyright" content="" />
		<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
		<meta charset="utf-8">
		<title>登陆</title>
		
		<link rel="stylesheet" type="text/css" href="/client/css/rich_base.css"/>
		<link rel="stylesheet" type="text/css" href="/client/css/rich_other.css"/>
		<script src="/client/js/jquery-1.11.0.js" type="text/javascript"></script>
		<script src="/client/js/Rich_Lee.js" type="text/javascript"></script>
		<script src="/client/js/rich_one.js" type="text/javascript"></script>
		<script type="text/javascript" src="/client/js/jquery.cookie.js"></script>
		
		<script>
		$(document).ready(function(){
			//记住密码
			if ($.cookie("savePassword") == "true") { 
		        $("#savePassword").attr("checked", true); 
		        $("#username").val($.cookie("username")); 
		        $("#password").val($.cookie("password")); 
		    } 
		    $(function(){
		    	$("#btn_login").click(function(){
		    	    saveUserInfo();    	   
		    	});	
		    });   
		    //自动登陆
		    if($.cookie("autoLogin") == "true"){
		    	$("#autoLogin").attr("checked",true);
		    	loginSubmit();
		    }	
		});
		
		//保存用户信息 
		function saveUserInfo() { 
		    if (document.getElementById("savePassword").checked==true) { 
		        var username = $("#username").val(); 
		        var password = $("#password").val(); 
		        $.cookie("savePassword", "true", { expires: 14 }); // 存储一个带7天期限的 cookie 
		        $.cookie("username", username, { expires: 14 }); // 存储一个带7天期限的 cookie 
		        $.cookie("password", password, { expires: 14 }); // 存储一个带7天期限的 cookie 
		    } 
		    else { 
		        $.cookie("savePassword", "false", { expires: -1 }); 
		        $.cookie("username", '', { expires: -1 }); 
		        $.cookie("password", '', { expires: -1 }); 
		    } 
		}        
		
		function auto(){
			if($("#autoLogin").checked == true)
			{
				$("#savePassword").removeAttr("checked"); 
				//$("#autoLogin").removeAttr("checked"); 
			}
			else{
				$("#savePassword").attr("checked", true); 
				//$("#autoLogin").attr("checked", true); 
			}
			
		}
		</script>
	</head>
	<body>
		<section class="onload">
			<div class="onload_bd">
				<img src="/client/images/onload_bd.png"/>
			</div>
			<form action="/login" method="post">
				<ul>
					<li class="li01">
						<input type="text" placeholder="请输入您的帐号" autocomplete="on" name="username" id="username" value="" />
						<input type="password" placeholder="请输入您的密码" name="password" id="password" value="" />
					</li>
					<li class="li02">
						<div>	
							<input type="checkbox" name="" id="savePassword" value="" />
							<span>记住密码</span>
						</div>
						<div>	
							<input type="checkbox" name="" id="autoLogin" value="" onclick="javascript:auto();"/>
							<span>自动登录</span>
						</div>
					</li>
					<li class="li05">
						<p id="alert_msg" style="color: #db4d11;text-align: center;line-height:30px;"></p>
					</li>
					<li class="li03">
						<input type="button" id="btn_login" onclick="javascript:loginSubmit();" value="登录" />
						<span></span>
					</li>
					<li class="li04">
						<a href="/reg">注册</a>
						<a href="/retrive">忘记密码？</a>
					</li>
				</ul>
			</form>
		</section>

		
	</body>
</html>
