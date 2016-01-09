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
		<title>			
			<#if typeTitle??>
				${typeTitle!''}-
			</#if>
			申请
		</title>
		
		<link rel="stylesheet" type="text/css" href="/client/css/rich_base.css"/>
		<link rel="stylesheet" type="text/css" href="/client/css/rich_other.css"/>
		<script src="/client/js/jquery-1.11.0.js" type="text/javascript"></script>
		<script src="/client/js/Rich_Lee.js" type="text/javascript"></script>
		<script src="/client/js/rich_one.js" type="text/javascript"></script>
		
	</head>
	<script type="text/javascript">
	<#if aru??&&aru==1>
		$(document).ready(function(){
			 if(!confirm("有尚未处理的业务，要重新填写一个表单吗？"))
			 {
			 	location.href='/apply';
			 }
		});
	</#if>
		window.onload = function(){
			html_hi();
		};
		
		function applySubmit()
		{
			var realName = $("#realName").val();
			var mobile = $("#mobile").val();
			var areaId = $("#areaId").val();
			var address = $("#address").val();
			var remark = $("#remark").val();
			var applyTypeId = $("#applyTypeId").val();
											
			var enterTypeIds = document.getElementsByName("enterTypeId");
			if(enterTypeIds.length > 0)
			{
				var typeIdIndex = 0;
				for(var i=0; i<enterTypeIds.length; i++)
					{
						if(enterTypeIds[i].checked = true)    //遍历出被选中的那个radio
							{
								typeIdIndex = i;
								break;
							}
					}
				var enterTypeId	= enterTypeIds[typeIdIndex].value;
			}
			else{
				var enterTypeId	= null;
			}

			
			$.ajax({
			      type:"post",
			      url:"/apply/submit",
			      data:{"realName":realName,
					        "mobile":mobile,
					        "areaId":areaId,
					        "address":address,
					        "remark":remark,
					        "applyTypeId":applyTypeId,
			    	  		"enterTypeId":enterTypeId},
			      success:function(data){
					if (data.code == 1)
					{
			            $("#alert_msg").html(data.msg);
					}
					else{
						alert("提交成功！")
						location.href="/apply";
					}
			      }
			  });
			
		}
	</script>
	<input type="hidden" name="applyTypeId" id="applyTypeId" value="${applyTypeId?c!''}" /> 
	<body style="background: #f2f2f2;">
		<header class="header_one">
			<a href="javascript:history.back(-1);"></a>
			<div>
			<#if typeTitle??>
				${typeTitle!''}
			</#if>
			</div>
		</header>
		<article class="acco_agen">
			<p class="acco" id="alert_msg">联系人和联系电话必填哦！否则无法联系您！</p>		
			<input class="input01" type="text" placeholder="请输入联系人姓名" name="realName" id="realName" value="<#if user??>${user.realName!''}</#if>">
			<input class="input01" type="tel" placeholder="请输入联系电话" name="mobile" id="mobile" value="<#if user??>${user.mobile!''}</#if>">	
			<#if spAcc??&& spAcc==1>
				<p class="choice_com">请选择公司类型</p>
				<#if enterType_list??>
					<#list enterType_list as item>
						<input class="type_radio" type="radio" style="display:none;"  id="enterType${item_index}"  name="enterType" value="${item.title!''}" <#if item_index == 0> selected="selected" </#if>>
						<input class="type_radio" type="radio" style="display:none;"  id="enterTypeId${item_index}"  name="enterTypeId"  value="${item.id?c!''}" <#if item_index == 0> selected="selected" </#if>>
						<input class="input02 <#if item_index == 0> inputSelected </#if>" type="button" id="type${item_index}" onclick="javascript:selectType(${item_index});" <#if item_index == 0> name="type" </#if>  value="${item.title!''}">
						<#--<input type="hidden" id="typeId${item_index}"  <#if item_index == 0> name="enterTypeId" </#if> value="<#if item??&&item.id??>${item.id?c}</#if>">-->
					</#list>
				</#if>				
			</#if>
			<select name="areaId" id="areaId">
				<option value="">请选择公司所处区域</option>
				<#if area_list??>
					<#list area_list as item>
						<option value="${item.id?c}">${item.title!''}</option>
					</#list>
				</#if>	
			</select>
			<input type="text" placeholder="请输入详细公司地址" name="address" id="address" value="">
			<textarea name="remark" id="remark" placeholder="请输入补充说明"></textarea>
		</article>
		<div style="height: 15px;background: none;"></div>
		<a href="javascript:applySubmit();" class="acco_back">提交</a>
		<a href="javascript:history.back(-1);" class="acco_back">返回</a>
	</body>
</html>
