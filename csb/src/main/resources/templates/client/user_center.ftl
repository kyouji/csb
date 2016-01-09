<!DOCTYPE html>
<html>
	<head>
		<style type="text/css">
			body{width: 100%;height: 100%;}
		</style>
		<meta name="keywords" content="${site.seoKeywords!''}">
		<meta name="description" content="${site.seoDescription!''}">
		<meta name="copyright" content="${site.copyright!''}" />
		<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
		<meta charset="utf-8">
		<title>财税宝-个人中心</title>
		
		<link rel="stylesheet" type="text/css" href="/client/css/rich_base.css"/>
		<link rel="stylesheet" type="text/css" href="/client/css/rich_other.css"/>
		<script src="/client/js/jquery-1.11.0.js" type="text/javascript"></script>
		<script src="/client/js/Rich_Lee.js" type="text/javascript"></script>
		<script src="/client/js/TweenMax1.18.0.min.js" type="text/javascript"></script>
		<script src="/client/js/rich_one.js" type="text/javascript"></script>
		<script type="text/javascript" src="/client/js/jquery.cookie.js"></script>
	</head>
	<script type="text/javascript">
		window.onload = function(){
			var aSpan = rich('.about_top span');
			var go = new TimelineMax();	
				go.staggerTo(aSpan,2,{scale:1.2,opacity:0,repeat:-1},0.4);//头像波浪
				html_hi();//body宽高
				choice_food('footer dd a','#00b8e1');///footer
		};
		
		////////////////////////////////////////
		/////////////////////////footer
		function choice_food(obj,colo){
			var oCh = rich('.'+obj);
			oCh[2].children[1].style.display = 'block';
			oCh[2].children[0].style.display = 'none';
			oCh[2].children[2].style.color = colo;
			for(var i=0;i<oCh.length;i++){
				oCh[i].onclick = function(){
					for(var i=0;i<oCh.length;i++){
						oCh[i].children[0].style.display = 'block';
						oCh[i].children[1].style.display = 'none';
						oCh[i].children[2].style.color = '';
					};		
					this.children[1].style.display = 'block';
					this.children[0].style.display = 'none';
					this.children[2].style.color = colo;
				};
			};
		};
		
		
		function logout()
		{
	        $.cookie("autoLogin", null, { path: '/' }); 
			location.href='/logout';
		}
	</script>
	<body style="background: #f2f2f2;">
		<section class="tel_out">
			<div>
				<p>免费服务电话</p>
				<p><#if site??>${site.telephone!''}</#if></p>
				<span onclick="phone_hide('.tel_out');">取消</span>
				<span>呼叫</span>
			</div>
		</section>
		<section class="share_out"" onclick="phone_hide('.share_out');">
			<ul>
				<li>
					<a href="#">
						<img src="/client/images/share_icon01.png"/>
						<span>QQ</span>
					</a>
				</li>
				<li>
					<a href="#">
						<img src="/client/images/share_icon02.png"/>
						<span>微信</span>
					</a>
				</li>
				<li>
					<a href="#">
						<img src="/client/images/share_icon03.png"/>
						<span>新浪微博</span>
					</a>
				</li>
			</ul>
		</section>
		<section class="about">
			<div class="about_bd">
				<div class="about_head">
					<div class="about_top">						
						<img src="<#if user.headImageUrl??&&user.headImageUrl != "">${user.headImageUrl}<#else>/client/images/default.jpg</#if>" onclick="location.href='/user/head';" />
						<span></span>
						<span></span>
						<span></span>
					</div>
				</div>
				<p>${user.username}</p>
			</div>
			<ul class="about_box">
				<li><a href="/user/info">我的资料</a></li>
				<li><a href="javascript:void(0)" onclick="phone_show('.tel_out')">联系我们</a></li>
				<li><a href="javascript:void(0)" onclick="phone_show('.share_out')">一键分享</a></li>
				<li><a href="/user/about">关于我们</a></li>
				<li><a href="#">交费</a></li>
			</ul>
			<span class="out" onclick="javascript:logout();">退出当前账号</span>
			<div style="height: 80px;width: 100%; float: left;"></div>
		</section>		
		<dl class="footer">
				<dd>
					<a href="/">
						<img src="/client/images/foote01.png"/>
						<img src="/client/images/foote11.png"/>					
						<span>我的</span>
					</a>
				</dd>
				<dd>
					<a href="/apply">
						<img src="/client/images/foote02.png"/>
						<img src="/client/images/foote22.png"/>					
						<span>我要</span>
					</a>
				</dd>
				<dd>
					<a href="/user/center">
						<img src="/client/images/foote03.png"/>
						<img src="/client/images/foote33.png"/>					
						<span>财税宝</span>
					</a>
				</dd>
			</dl>
	</body>
</html>
