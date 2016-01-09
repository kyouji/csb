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
		<title>上传票据</title>
		
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
			form.submit();
		}
		
		//票据上传完成（修改状态Id）
		function billFinish()
		{
			var billTypeId = $("#billTypeId").val();	
			var billIds = $(".billId").val();
			
			  $.ajax({
			      type:"post",
			      url:"/bill/upload/finish",
			      data:{"billTypeId":billTypeId,
			    	  		"billIds":billIds},
			      success:function(data){
					if (data.code == 1)
					{
						$(".get_war").css("display","block");
			            $(".get_war").html(data.msg);
			            if(data.login == 1)
			            {
			            	setTimeout("location.href='/login'",2000);
			            }
					}
					else{
						alert("上传成功！")
						location.href="/";
					}
			      }
			  });
		}
	</script>
	<body style="background: #f2f2f2;">
		<form id="upload" name="upload" enctype="multipart/form-data" action="/client/bill/upload" method="post">
			<input type="hidden" id="id" name="id" value="<#if user??>${user.id?c!''}</#if>"></input>
			<input id="file" style="display:none;" class="area_save_btn" name="Filedata"  type="file" value="" onchange="javascript:formSubmit();"/>
			<header class="header_one">
				<a href="/index"></a>
				<div>其他业务咨询</div>
			</header>
			<section class="poto">	
				<ul class="poto_box">
					<#if bill_list??>
						<#list bill_list as item>
							<li>
								<img src="/images/${item.imgUrl!''}"/>
								<a></a>
								<input type="hidden" class="billId" value="${item.id?c}">
							</li>
						</#list>
					</#if>		
					<li>
						<div id="choice_poto" >
						</div>
					</li>
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
			<select name="billTypeId" id="billTypeId" class="get_up">
				<option value="" selected="selected">请选择</option>
				<#if billType_list??>
					<#list billType_list as item>
						<option value="${item.id?c}">${item.title!''}</option>
					</#list>
				</#if>		
			</select>
			<div class="get_war" style="display:none;widh:200px;" >
				
			</div>
			<input class="get_sub" type="button" name="" id="billSubmit" onclick="javascript:billFinish();" value="开始上传票据" />
		</form>		
	</body>
</html>
