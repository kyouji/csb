<!DOCTYPE html>
<html>
	<head>
		<style type="text/css">
			body{width: 100%;height: 100%;}
		</style>
		<meta name="keywords" content="">
		<meta name="description" content="">
		<meta name="copyright" content="" />
		<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
		<meta charset="utf-8">
		<title>首页</title>
		
		<link rel="stylesheet" type="text/css" href="/client/css/rich_base.css"/>
		<link rel="stylesheet" type="text/css" href="/client/css/rich_other.css"/>
		<script src="/client/js/Rich_Lee.js" type="text/javascript"></script>
		<script src="/client/js/TweenMax1.18.0.min.js" type="text/javascript"></script>
		<script src="/client/js/rich_one.js" type="text/javascript"></script>
	</head>
	<script type="text/javascript">
		window.onload = function(){
				html_hi();//body宽高
				choice_food('footer dd a','#00b8e1');///footer	
		};
	</script>
	<body style="background: #f2f2f2;">
		<div class="index_banner">
			<img src="/client/images/index_banner.png"/>
		</div>
		<ul class="index_nav01">
			<li>
				<a href="/bill/upload<#if user??>?id=${user.id?c}</#if>">
					<div class="index_box01">
						<img src="/client/images/index_icon01.png"/>
						<p>上传票据</p>
						<span>随时随地 上传票据</span>
					</div>
				</a>
			</li>
			<li>
				<a href="#">
					<div class="index_box01">
						<img src="/client/images/index_icon02.png"/>
						<p>查询进度</p>
						<span>财税进度 尽在掌握</span>
					</div>
				</a>
			</li>
		</ul>
		<ul class="index_nav02">
			<li>
				<a href="#">
						<img src="/client/images/index_icon03.png"/>
						<p>票据整理</p>
				</a>
			</li>
			<li>
				<a href="#">
						<img src="/client/images/index_icon04.png"/>
						<p>财务状况</p>
				</a>
			</li>
			<li>
				<a href="#">
						<img src="/client/images/index_icon05.png"/>
						<p>资料下载</p>
				</a>
			</li>
		</ul>
		<div style="height: 80px;width: 100%; float: left;"></div>
		<#if roleId?? && roleId == 0>
			<dl class="footer">
				<dd>
					<a href="/">
						<img src="/client/images/foote01.png"/>
						<img src="/client/images/foote11.png"/>					
						<span>我的</span>
					</a>
				</dd>
				<dd>
					<a href="/logout">
						<img src="/client/images/foote02.png"/>
						<img src="/client/images/foote22.png"/>					
						<span>我要</span>
					</a>
				</dd>
				<dd>
					<a href="/login">
						<img src="/client/images/foote03.png"/>
						<img src="/client/images/foote33.png"/>					
						<span>财税宝</span>
					</a>
				</dd>
			</dl>
		</#if>
	</body>
</html>
