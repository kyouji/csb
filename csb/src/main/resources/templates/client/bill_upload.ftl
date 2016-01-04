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
		<title>其他业务咨询</title>
		
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
		
		function formSubmit()
		{
			form = document.forms["upload"];
			
		}
	</script>
	<body style="background: #f2f2f2;">
		<form id="upload" name="upload" enctype="multipart/form-data" action="/bill/upload" method="post">
			<input type="hidden" id="id" name="id" value="<#if user??>${user.id?c!''}</#if>"></input>
			<input id="file" style="display:none;" class="area_save_btn" name="Filedata"  type="file" value="" onchange="javascript:addone(this.value);"/>
			<header class="header_one">
				<a href="history.back(-1);"></a>
				<div>其他业务咨询</div>
			</header>
			<section class="poto">	
				<ul class="poto_box">
					<li>
						<div id="choice_poto" onclick="javascript:fileUpload();" >
						</div>
					</li>
					<li>
						<a id="add"></a>
					</li
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
			<select class="get_up">
				<option selected="selected">请选择</option>
				<option>购进类</option>
				<option>销售类</option>
				<option>费用报销类</option>
				<option>资金往来类</option>
				<option>其他</option>
			</select>
			<div class="get_war">
				请先拍摄票据！
			</div>
			<input class="get_sub" type="button" name="" id="billSubmit" onclick="javascript:formSubmit();" value="开始上传票据" />
		</form>		
	</body>
</html>
