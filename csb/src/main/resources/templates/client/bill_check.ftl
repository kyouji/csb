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
		<title>查询进度</title>
		
		<link rel="stylesheet" type="text/css" href="/client/css/rich_base.css"/>
		<link rel="stylesheet" type="text/css" href="/client/css/rich_other.css"/>
		<script src="/client/js/jquery-1.11.0.js" type="text/javascript"></script>
		<script src="/client/js/Rich_Lee.js" type="text/javascript"></script>
		<script src="/client/js/rich_one.js" type="text/javascript"></script>
		
	</head>
	<body style="background: #f2f2f2;">
		<header class="header_one">
			<a href="javascript:history.back(-1);"></a>
			<div>查询进度</div>  
		</header>
		<article class="check_rate">
			<section class="rate_line">
				<div class="rate_box">
					<span><a></a></span>
				</div>
				<div class="rate_box">
					<span><a></a></span>
				</div>
				<div class="rate_box">
					<span><a></a></span>
				</div>
				<div class="rate_box">
					<span><a></a></span>
				</div>
				<div class="rate_box">
					<span><a></a></span>
				</div>
			</section>
			<ul class="rate_go">
				<li>
					<div>票据上传</div>
						<#if !bill.statusId?? >
							<span class="yet">未开始</span>
						<#elseif bill.statusId?? && (bill.statusId == 0 || bill.statusId == 1) >
							<span class="down">上传未完成</span>
						<#else>
							<span class="down">上传完成</span>
						</#if>	
				</li>
				<li>
					<div>票据整理</div>
						<#if !bill.statusId?? || bill.statusId?? && bill.statusId lt 3>
							<span class="yet">未开始</span>
						<#else> 	
							<span class="down">已整理</span>
						</#if>	
				</li>
				<li>
					<div>财务处理</div>
						<#if !bill.statusId?? || bill.statusId?? && bill.statusId lt 4>
							<span class="yet">未开始</span>		
						<#else>				
							<span class="down">已处理</span>
						</#if>	
				</li>
				<li>
					<div>税费扣缴</div>
						<#if !bill.statusId?? || bill.statusId?? && bill.statusId lt 5>
							<span class="yet">未开始</span>
						<#else> 	
							<span class="down">已扣缴</span>
						</#if>	
				</li>
				<li>
					<div>财务状况表</div>
					<#if !bill.statusId?? || bill.statusId?? && bill.statusId lt 6>
						<span class="yet">未开始</span>
					<#else> 	
						<span class="down">已完成</span>
					</#if>	
				</li>
			</ul>
			<a href="javascript:history.back(-1);" class="rot_back">返回</a>
		</article>	
	</body>
</html>
