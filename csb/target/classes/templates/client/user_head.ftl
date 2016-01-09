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
		<title>上传头像</title>
		
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
		
		function addone(obj)
		{
			formSubmit();
			var path = getFullPath(obj);
			$("#choice_poto").css("background","url("+path+")");
		}
		
		function formSubmit()
		{
			form = document.forms["upload"];
			form.submit();
		}
		
		function headChange()
		{
			$("#file").click();
		}
	</script>
	<body style="background: #f2f2f2;">
		<form id="upload" name="upload" enctype="multipart/form-data" action="/client/userHead/upload" method="post">
			<input type="hidden" id="id" name="id" value="<#if user??>${user.id?c!''}</#if>"></input>
			<input id="file" style="display:none;" class="area_save_btn" name="Filedata"  type="file" value="" onchange="javascript:formSubmit();"/>
			<header class="header_one">
				<a href="javascript:history.back(-1);"></a>
				<div>上传头像</div>
			</header>
			<#--
			<section class="poto" style="height:auto;">	
				<ul class="poto_box" style="display:table;text-align:center;max-height:300px;">
					<#if user.headImageUrl??>
						<li style="width:100%;">
							<img src="${user.headImageUrl!''}"/>
							<a></a>
						</li>
					<#else>
						<li>
							<div  id="choice_poto"  ></div>
						</li>
					</#if>
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
			-->
			<section class="about">
			<div class="about_bd">
				<div class="about_head">
					<div class="about_top"  onclick="javascript:headChange();">						
						<img src="<#if user.headImageUrl??&&user.headImageUrl != "">${user.headImageUrl}<#else>/client/images/default.jpg</#if>" onclick="location.href='/user/head';" />
						<span></span>
						<span></span>
						<span></span>
					</div>
				</div>
				<p>${user.username}</p>
			</div>
			<div style="height: 80px;width: 100%; float: left;"></div>
		</section>		
			<div class="get_war" style="display:none;">
				
			</div>
			<input class="get_sub" type="button" name="" id="billSubmit" onclick="location.href='/user/center';" value="确定" />
			<input class="get_sub" type="button" name="" id="billSubmit" onclick="javascript:headChange();" value="更换" />
		</form>		
	</body>
</html>
