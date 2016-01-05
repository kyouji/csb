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
		<title>上传图片</title>
		
		<link rel="stylesheet" type="text/css" href="/client/css/rich_base.css"/>
		<link rel="stylesheet" type="text/css" href="/client/css/rich_other.css"/>
		<script src="/client/js/jquery-1.11.0.js" type="text/javascript"></script>
		<script src="/client/js/Rich_Lee.js" type="text/javascript"></script>
		<script src="/client/js/rich_one.js" type="text/javascript"></script>
		
	</head>
	<script type="text/javascript">
		window.onload = function(){
			html_hi();
			img_big();
		};

	</script>
	<body style="background: #f2f2f2;">
		<form id="upload" name="upload" enctype="multipart/form-data" action="/bill/img/upload" method="post">
			<input type="hidden" id="id" name="id" value="<#if user??>${user.id?c!''}</#if>"></input>
			<input id="file" style="display:none;" class="area_save_btn" name="Filedata"  type="file" value="" onchange="javascript:formSubmit();"/>
			<header class="header_one">
				<a href="javascript:history.back(-1);"></a>
				<div>上传图片</div>
			</header>
			<section class="poto" style="height:300px;">	
				<ul class="poto_box" style="display:table;text-align:center;">
						<#if bill??><img src="/images/${bill.imgUrl!''}" width=100%/></#if>
				</ul>
				
				<ul class="affirm">
					<li>确认删除？</li>
					<li>返回</li>
				</ul>
				<ul class="add_poto">
					<li>即拍即传</li>
					<li>从相册中选取</li>
					<li>返回</li>
				</ul>
			</section>
			<input class="get_sub" type="button" name="" id="billSubmit" onclick="location.href='/bill/upload/confirm?billId='+<#if bill??>${bill.id?c}</#if>;" value="确定" />
		</form>		
	</body>
</html>
