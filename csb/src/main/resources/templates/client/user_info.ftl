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
		<title>我的资料</title>
		
		<link rel="stylesheet" type="text/css" href="/client/css/rich_base.css"/>
		<link rel="stylesheet" type="text/css" href="/client/css/rich_other.css"/>
		<script src="/client/js/jquery-1.11.0.js" type="text/javascript"></script>
		<script src="/client/js/Rich_Lee.js" type="text/javascript"></script>
		<script src="/client/js/rich_one.js" type="text/javascript"></script>
		
	</head>
	<script type="text/javascript">
		$(document).ready(function(){
			<#if hatu?? && hatu ==0>
				alert("请上传您的营业执照");
			<#elseif hatu?? && hatu==1>
				alert("请上传您的会计证照");
			</#if>
		});
	
		window.onload = function(){			
			img_big();
		};
		
		function userInfoSubmit()
		{
			var enterName = $("#enterName").val();
			var username = $("#username").val();
			var realName = $("#realName").val();
			var mobile = $("#mobile").val();
			var photoIds = $(".photoId").val();
			var enterTypeId = $("input[name='enterTypeId']:checked").val(); //赋值
		
			  $.ajax({
			      type:"post",
			      url:"/user/info",
			      data:{"enterName":enterName,
						    "enterTypeId":enterTypeId,
						    "username":username,
						    "realName":realName,
			    	  		"mobile":mobile,
			    	  		"photoIds":photoIds},
			      success:function(data){
					if (data.code == 1)
					{
			            alert(data.msg);
			            if(data.login==1)
			            {
			            	location.href='/login';
			            }
					}
					else{
						alert("修改成功！")
						<#if hatu??>
							location.href='/';
						<#else>
							location.href='/user/center';
						</#if>
					}
			      }
			  });
		}
	function formSubmit()
		{
			form = document.forms["upload"];
			form.submit();
		}
	</script>
	<form id="upload" name="upload" enctype="multipart/form-data" action="/client/photo/upload" method="post">
	<input type="hidden" id="id" name="id" value="<#if user??>${user.id?c!''}</#if>"></input>
	<input id="file" style="display:none;" class="area_save_btn" name="Filedata"  type="file" value="" onchange="javascript:formSubmit();"/>
		<body style="background: #f2f2f2;">
			<header class="header_one">
				<a href="/user/center"></a>
				<div>我的资料</div>
			</header> 
			<article class="my_data">
			<#if user.roleId==0>
				<p class="choice_com">公司名称</p>
				<input class="input01" type="text" placeholder="请输入公司名称" autocomplete="on" name="enterName" id="enterName"  value="${user.enterName!''}">
				<p class="choice_com">公司类型</p>
				<#if enterType_list??>
					<#list enterType_list as item>
						<input class="type_radio" type="radio" style="display:none;"  id="enterTypeId${item_index}"  name="enterTypeId"  value="${item.id?c!''}" <#if user.enterTypeId??&&user.enterTypeId == item.id> checked="checked" </#if>>
						<input class="input02 <#if user.enterTypeId??&&user.enterTypeId == item.id> inputSelected </#if>" type="button" id="type${item_index}" onclick="javascript:selectType(${item_index});"  value="${item.title!''}">
						<#--<input type="hidden" id="typeId${item_index}"  <#if item_index == 0> name="enterTypeId" </#if> value="<#if item??&&item.id??>${item.id?c}</#if>">-->
					</#list>
				</#if>	
			</#if>	
				<p class="choice_com">用户名</p>
				<input class="input01" type="text" placeholder="请输入用户名" id="username" name="username"  value="${user.username!''}">
				<p class="choice_com">联系人姓名</p>
				<input class="input01" type="text" placeholder="请输入联系人姓名" name="realName" id="realName" value="${user.realName!''}">
				<p class="choice_com">联系人电话</p>
				<input class="input01" type="tel" placeholder="请输入联系人电话" name="mobile" id="mobile" value="${user.mobile}">
				<p class="choice_com"><#if user.roleId==0>营业执照<#else>会计证照</#if></p>
					<section class="poto">	
						<ul class="poto_box">
							<#if photo_list1??>
								<#list photo_list1 as item>
									<li>
										<img src="/images/${item.imgUrl!''}"/>
										<a></a>
										<input type="hidden" class="photoId" value="${item.id?c}">
									</li>
								</#list>
							</#if>		
							<#if photo_list2??>
								<#list photo_list2 as item>
									<li>
										<img src="/images/${item.imgUrl!''}"/>
										<a></a>
										<input type="hidden" class="photoId" value="${item.id?c}">
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
				<input type="button" class="inputSelected" id="enterInfoSubmit" onclick="javascript:userInfoSubmit();" value="确定">
			</article>
		</body>
	</form>	
</html>
