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
		<title>资料下载</title>
		
		<link rel="stylesheet" type="text/css" href="/client/css/rich_base.css"/>
		<link rel="stylesheet" type="text/css" href="/client/css/rich_other.css"/>
		<link href="/mag/style/WdatePicker.css" rel="stylesheet" type="text/css">
		<script src="/client/js/jquery-1.11.0.js" type="text/javascript"></script>
		<script src="/client/js/Rich_Lee.js" type="text/javascript"></script>
		<script src="/client/js/rich_one.js" type="text/javascript"></script>
		<script type="text/javascript" src="/mag/js/WdatePicker.js"></script>
	</head>
	<body style="background: #f2f2f2;">
		<header class="header_one">
			<a href="javascript:history.back(-1);"></a>
			<div style="width:60%;padding: 0px;">资料下载</div>
			<a class="det"></a>
		</header>
		<article class="download">			
			<section class="time">
			    <div class="input-date">
                    <input name="time" type="text" id="txtAddTime" value="<#if date??>${date?string("yyyy-MM")}</#if>" class="input date" onfocus="WdatePicker({dateFmt:'yyyy-MM',lang:'zh-cn'})" >
                    <i>日期</i>
                </div>
			</section>
			<ul class="download_box">
				<li>
					<span>历史票据</span>
					<a class="blue">下载</a>
				</li>
				<li>
					<span>利润表</span>
					<a class="red">暂停</a>
				</li>
				<li>
					<span>资产负债表</span>
					<a class="blue">继续</a>
				</li>
				<li>
					<span>财务状况表</span>
					<a class="gray">下载完成</a>
					
				</li>
			</ul>
			<a href="#" class="download_back">返回</a>
		</article>
	</body>
</html>
