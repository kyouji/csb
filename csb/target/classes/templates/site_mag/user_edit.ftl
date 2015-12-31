<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/mag/style/idialog.css" rel="stylesheet" id="lhgdialoglink">
<title>编辑会员信息</title>
<script type="text/javascript" src="/mag/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="/mag/js/Validform_v5.3.2_min.js"></script>
<script type="text/javascript" src="/mag/js/lhgdialog.js"></script>
<script type="text/javascript" src="/mag/js/WdatePicker.js"></script>
<script type="text/javascript" src="/mag/js/swfupload.js"></script>
<script type="text/javascript" src="/mag/js/swfupload.queue.js"></script>
<script type="text/javascript" src="/mag/js/swfupload.handlers.js"></script>
<script type="text/javascript" src="/mag/js/layout.js"></script>
<link href="/mag/style/style.css" rel="stylesheet" type="text/css">
<link href="/mag/style/WdatePicker.css" rel="stylesheet" type="text/css">

<!-- 企业资料样式 -->

<link href="/client/css/team.css" rel="stylesheet" type="text/css" />

<style>
.apply_content dd div .hide{display:none;}
.apply_content dd .hide{display:none;}
.apply_content dt .hide{display:none;}
.apply_content .hide{display:none;}

</style>
<script>
$(document).ready(function(){

	$("#step1").Validform({
			tiptype:4,
	  	    ajaxPost:true,
            callback: function (data) { 
	            if (data.code == 0)
	            {
					$.dialog.alert("提交成功");
				    $(".menu").removeClass("selected");
    				$(".menu").eq(1).addClass("selected");
				    $(".tab-content").hide();
    				$(".tab-content").eq(1).show();
				}
	            else 
	            {
	                $.dialog.alert(data.msg);
	                if (data.check == 0)
	                {
	                	location.href='/Verwalter/login';
	                }
	                else if (data.check ==1)
	                {
	                	location.href='/enterprise/check';
	                }
	            }
       		 }
	});
	
	$("#dataUpload").Validform({
			tiptype:4,
	  	    ajaxPost:true,
            callback: function (data) { 
	            if (data.code == 0)
	            {
					$.dialog.alert("提交成功");
				}
	            else 
	            {
	                $.dialog.alert(data.msg);
	                if (data.check == 0)
	                {
	                	location.href='/Verwalter/login';
	                }
	                else if (data.check ==1)
	                {
	                	location.href='/enterprise/check';
	                }
	            }
       		 }
	});
});

function showEnter(){
	$(".enter").css("display","block");
	$(".pro").css("display","none");
}
function showPro(){
	$(".pro").css("display","block");
	$(".enter").css("display","none");
}

function forbidsubmit()
{
	$("#submitbutton").attr("disabled",true);
	$("#submitbutton").css("background","#666666");
}

function allowsubmit()
{
	$("#submitbutton").removeAttr("disabled");
	$("#submitbutton").css("background","#e67817");
}

function setStatusId(id , statusId)
{
	
	    $.ajax({
        type:"post",
        url:"/Verwalter/user/enterprise/status",
        data:{"id":id,"statusId":statusId},
        success:function(data){
           if (data.code == 0)
           {
            	location.reload();
            }
            else{
            	alert(msg);
            	location.href='/Verwalter/login';
            }
        }
    });
}

function done()
{
    $.dialog.alert("上传资料成功！");	
}
<#if done?? &&done == 1>
window.onload=done;
</#if>

</script>
<script type="text/javascript">
$(function () {
    //初始化表单验证
    $("#form_user").initValidform();

    //初始化上传控件
    $(".upload-img").each(function () {
        $(this).InitSWFUpload({ 
            sendurl: "/Verwalter/dataUpload", 
            flashurl: "/mag/js/swfupload.swf"
        });
    });

        //（缩略图1）
    var txtPic = $("#txtImgUrl1").val();
    if (txtPic == "" || txtPic == null) {
        $(".thumb_ImgUrl_show1").hide();
    }
    else {
        $(".thumb_ImgUrl_show1").html("<ul><li><div class='img-box1'><img src='/images/" + txtPic + "' bigsrc='" + txtPic + "' /></div></li></ul>");
        $(".thumb_ImgUrl_show1").show();
    }
            //（缩略图2）
    var txtPic = $("#txtImgUrl2").val();
    if (txtPic == "" || txtPic == null) {
        $(".thumb_ImgUrl_show2").hide();
    }
    else {
        $(".thumb_ImgUrl_show2").html("<ul><li><div class='img-box1'><img src='/images/" + txtPic + "' bigsrc='" + txtPic + "' /></div></li></ul>");
        $(".thumb_ImgUrl_show2").show();
    }
            //（缩略图3）
    var txtPic = $("#txtImgUrl3").val();
    if (txtPic == "" || txtPic == null) {
        $(".thumb_ImgUrl_show3").hide();
    }
    else {
        $(".thumb_ImgUrl_show3").html("<ul><li><div class='img-box1'><img src='/images/" + txtPic + "' bigsrc='" + txtPic + "' /></div></li></ul>");
        $(".thumb_ImgUrl_show3").show();
    }
            //（缩略图4）
    var txtPic = $("#txtImgUrl4").val();
    if (txtPic == "" || txtPic == null) {
        $(".thumb_ImgUrl_show4").hide();
    }
    else {
        $(".thumb_ImgUrl_show4").html("<ul><li><div class='img-box1'><img src='/images/" + txtPic + "' bigsrc='" + txtPic + "' /></div></li></ul>");
        $(".thumb_ImgUrl_show4").show();
    }
            //（缩略图5）
    var txtPic = $("#txtImgUrl5").val();
    if (txtPic == "" || txtPic == null) {
        $(".thumb_ImgUrl_show5").hide();
    }
    else {
        $(".thumb_ImgUrl_show5").html("<ul><li><div class='img-box1'><img src='/images/" + txtPic + "' bigsrc='" + txtPic + "' /></div></li></ul>");
        $(".thumb_ImgUrl_show5").show();
    }

	  //(刷新图1)
    $("#txtImgUrl1").blur(function () {
        var txtPic = $("#txtImgUrl1").val();
        if (txtPic == "" || txtPic == null) {
            $(".thumb_ImgUrl_show").hide();
        }
        else {
            $(".thumb_ImgUrl_show").html("<ul><li><div class='img-box1'><img src='/images/" + txtPic + "' bigsrc='" + txtPic + "' /></div></li></ul>");
            $(".thumb_ImgUrl_show").show();
        }
    });  
    	  //(刷新图2)
    $("#txtImgUrl2").blur(function () {
        var txtPic = $("#txtImgUrl2").val();
        if (txtPic == "" || txtPic == null) {
            $(".thumb_ImgUrl_show2").hide();
        }
        else {
            $(".thumb_ImgUrl_show2").html("<ul><li><div class='img-box1'><img src='/images/" + txtPic + "' bigsrc='" + txtPic + "' /></div></li></ul>");
            $(".thumb_ImgUrl_show2").show();
        }
    });  
    	  //(刷新图3)
    $("#txtImgUrl3").blur(function () {
        var txtPic = $("#txtImgUrl3").val();
        if (txtPic == "" || txtPic == null) {
            $(".thumb_ImgUrl_show3").hide();
        }
        else {
            $(".thumb_ImgUrl_show3").html("<ul><li><div class='img-box1'><img src='/images/" + txtPic + "' bigsrc='" + txtPic + "' /></div></li></ul>");
            $(".thumb_ImgUrl_show3").show();
        }
    });  
    	  //(刷新图4)
    $("#txtImgUrl4").blur(function () {
        var txtPic = $("#txtImgUrl4").val();
        if (txtPic == "" || txtPic == null) {
            $(".thumb_ImgUrl_show4").hide();
        }
        else {
            $(".thumb_ImgUrl_show4").html("<ul><li><div class='img-box1'><img src='/images/" + txtPic + "' bigsrc='" + txtPic + "' /></div></li></ul>");
            $(".thumb_ImgUrl_show4").show();
        }
    });  
    	  //(刷新图5)
    $("#txtImgUrl5").blur(function () {
        var txtPic = $("#txtImgUrl5").val();
        if (txtPic == "" || txtPic == null) {
            $(".thumb_ImgUrl_show5").hide();
        }
        else {
            $(".thumb_ImgUrl_show5").html("<ul><li><div class='img-box1'><img src='/images/" + txtPic + "' bigsrc='" + txtPic + "' /></div></li></ul>");
            $(".thumb_ImgUrl_show5").show();
        }
    });  
    
    //设置封面图片的样式
    $(".photo-list ul li .img-box img").each(function () {
        if ($(this).attr("src") == $("#hidFocusPhoto").val()) {
            $(this).parent().addClass("selected");
        }
    }); 
});   

</script>
</head>

<body class="mainbody">

<!--导航栏-->
<div class="location" style="position: static; top: 0px;">
  <a href="/Verwalter/user/list?roleId=${roleId!""}"><i></i><span>返回列表页</span></a>
  <a href="/Verwalter/center" class="home"><i></i><span>首页</span></a>
  <i class="arrow"></i>
  <span>企业管理</span>
  <i class="arrow"></i>
  <#if enterprise??>
  	<span>编辑企业信息</span>
  <#else>
  	<span>创建企业账号</span>
  </#if>
</div>
<div class="line10"></div>
<!--/导航栏-->

<!--内容-->
<div class="content-tab-wrap">
  <div id="floatHead" class="content-tab" style="position: static; top: 52px;">
    <div class="content-tab-ul-wrap">
      <ul>
	      <#if enterprise??>  
	      	<li><a href="javascript:;" onclick="tabs(this);" class="selected menu">基本资料</a></li>
	      	<li><a href="javascript:;" onclick="tabs(this);" class="menu">文件管理</a></li>
	      </#if>
          <li><a href="javascript:;" onclick="tabs(this);" class="menu">安全设置</a></li>
        <#--
        <li><a href="javascript:;" onclick="tabs(this);">账户信息</a></li>
        -->
      </ul>
    </div>
  </div>
</div>
<#if enterprise??>
<!--基本资料-->
<div class="tab-content">

<!-- 企业资料 -->

<!--right-->
<div class="content">
    <div class="right_content">
    <div class="right_box">

    <div class="change_inform">
      <dl>
  		<dt>
        <#if enterprise??&&enterprise.statusId??&&enterprise.statusId == 0>
        审核状态： 
        <#elseif enterprise??&&enterprise.statusId??&&enterprise.statusId == 1>
        审核状态：
        <#elseif enterprise??&&enterprise.statusId??&&enterprise.statusId == 2>
       审核状态： 
         <#elseif enterprise??&&enterprise.statusId??&&enterprise.statusId == 3>
       审核状态：
         <#else>
         用户未完善资料                    
        </#if>
        </dt>
   		<dd>
	      <div class="rule-single-select">
	            <select id="setStatusId" onchange="javascript:setStatusId(${enterprise.id!''},this.value);">
	                <option value="" <#if !enterprise?? || enterprise??&& !enterprise.statusId??>selected="selected"</#if>>请选择...</option>
	                <option value="0" <#if enterprise?? && enterprise.statusId?? &&enterprise.statusId==0>selected="selected"</#if>>待审核</option> 
	                <option value="1" <#if enterprise?? && enterprise.statusId?? &&enterprise.statusId==1>selected="selected"</#if>>已通过</option>
	                <option value="3" <#if enterprise?? && enterprise.statusId?? &&enterprise.statusId==3>selected="selected"</#if>>未通过</option>     
	                <option value="4" <#if enterprise?? && enterprise.statusId?? &&enterprise.statusId==4>selected="selected"</#if>>重新审核</option>            
	            </select>
	        </div>    
	    </dd>
	</dl>        
    </div>
        <div style="float:left;	">
			<dl class="apply_step2" style="margin-top:20px ; ">
				<dd><input  style="background:#529c15;float:left;"  class="area_save_btn" type="button" value="导出报名表" onclick="location.href='/enterprise/export/enter?id='<#if enterprise??>+${enterprise.id?c}</#if>;"/></dd>
			</dl>		
        </div>  
        
    </div>  
        <dl class="apply_content">
             <form action="/Verwalter/user/info/submit" id="step1" method="post">
             <#if enterprise??>
              	<input type="hidden" name="statusId" value="${enterprise.statusId!''}" />
                <input type="hidden" name="id" value="${enterprise.id?c!''}" />
			    <input type="hidden" name="username" value="${enterprise.username!''}"/>
			    <input type="hidden" name="usermobile" value="${enterprise.usermobile!''}"/>
			    <input type="hidden" name="useremail" value="${enterprise.useremail!''}"/>
		        <input type="hidden" name="fileUrl" value="${enterprise.fileUrl!''}"/>
			    <input type="hidden" name="pptUrl" value="${enterprise.pptUrl!''}"/>
	     	    <input type="hidden" name="dataBusiness" value="${enterprise.dataBusiness!''}"/>
	     	    <input type="hidden" name="dataPossible" value="${enterprise.dataPossible!''}"/>
	     	    <input type="hidden" name="dataOther" value="${enterprise.dataOther!''}"/>			    
			</#if>    
        	<div style="margin: 20px 0 20px 50px ;width:100%;">
        		<input type="radio" <#if enterprise??&&enterprise.formType??&&enterprise.formType==0 ||!enterprise.formType??>checked="checked"</#if> name="formType" value="0" onClick="javascript:showEnter();"/><span>企业组表格</span>
        		<input type="radio" <#if  enterprise??&&enterprise.formType??&&enterprise.formType==1>checked="checked"</#if> name="formType" value="1" onClick="javascript:showPro();"/><span>项目团队表格</span>
        	</div>
    	<dt class="dt01"  style="width: 1000px; text-align: left; height: 30px;"><span>一、基本信息</span><br/><p>此信息将自动生成到报名表中</p></dt>
    	<dd style="margin-left: 62px;">

    			<div>
    			     <span class="enter <#if  enterprise??&&enterprise.formType??&&enterprise.formType==1>hide</#if>">企业名称：</span>
    			     <span class="pro <#if  enterprise??&&enterprise.formType??&&enterprise.formType==0 ||!enterprise.formType??>hide</#if>">项目名称：</span>
    			     <input type="text" name="title" value="<#if enterprise.formType??>${enterprise.title!''}</#if>" datatype="*2-25" errormsg="请填写2到25位字符"/>
    		    </div>
    			<div>
    			<span class="enter <#if  enterprise??&&enterprise.formType??&&enterprise.formType==1>hide</#if>">成立时间：</span>
    			<span class="pro <#if  enterprise??&&enterprise.formType??&&enterprise.formType==0 ||!enterprise.formType??>hide</#if>">（拟）成立时间：</span>
    			  <input  type="text" id="date" value="<#if  enterprise??&&enterprise.establish??>${enterprise.establish?string("yyyy年MM月dd日")!''}</#if>" class="input date" onfocus="WdatePicker({dateFmt:'yyyy年MM月dd日',vel:'date_2',lang:'zh-cn'})" datatype="*" / >
    			  <input id="date_2" name="establish" value="<#if  enterprise??&&enterprise.establish??>${enterprise.establish?string("yyyy-MM-dd")!''}</#if>" type="hidden" datatype="/^\s*$|^\d{4}\-\d{1,2}\-\d{1,2}$/" errormsg="填写正确格式" sucmsg=" " />
    			<#--<input type="text" name="establish" value="<#if enterprise.formType??>${enterprise.establish!''}</#if>" datatype="*" ignore="ignore" /> -->
    			</div>
    			<div>
	    			<span class="enter <#if  enterprise??&&enterprise.formType??&&enterprise.formType==1>hide</#if>">注册资本<b style="color:#999;font-size:0.6em;">(万元)</b>：</span>
	    			<span class="pro <#if  enterprise??&&enterprise.formType??&&enterprise.formType==0 || enterprise??&&!enterprise.formType??>hide</#if>">（拟）注册资本<b style="color:#999;font-size:0.6em;">(万)</b>：</span>
	    			<input type="text" name="capital" value="<#if  enterprise??&&enterprise.capital??>${enterprise.capital?c!''}</#if>" datatype="/^(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/"  errormsg="请填写数字！" />
    			</div>
    			<div>
	    			<span class="enter <#if  enterprise??&&enterprise.formType??&&enterprise.formType==1>hide</#if>">法定代表人：</span>
	    			<span  class="pro <#if  enterprise??&&enterprise.formType??&&enterprise.formType==0 || enterprise??&&!enterprise.formType??>hide</#if>">（拟）法定代表人：</span>
	    			<input type="text" name="representative" datatype="*"value="<#if  enterprise??&&enterprise.formType??>${enterprise.representative!''}</#if>" />
    			</div>
    			<div>
	    			<span class="enter <#if  enterprise??&&enterprise.formType??&&enterprise.formType==1>hide</#if>">股东结构：</span>
	    			<span class="pro <#if  enterprise??&&enterprise.formType??&&enterprise.formType==0 ||!enterprise.formType??>hide</#if>">（拟）股东结构：</span>
	    			<textarea name="shareholder" datatype="*5-100"  errormsg="输入5到100字"><#if  enterprise??&&enterprise.formType??>${enterprise.shareholder!''}</#if></textarea>
    			</div>
    			<div><span>所在地区：</span>
    				<select name="area" datatype="*">
    					<#if region_list??>
    						<#list region_list as item>
    							<option value="${item.title!''}" <#if  enterprise??&&enterprise.formType??&&enterprise.area==item.title>selected="selected"</#if>>${item.title!''}</option>
    						</#list>
    					</#if>	
    				</select>
    			</div>
    			<div><span>地址：</span><input type="text" name="address" datatype="*"value="<#if  enterprise??&&enterprise.address??>${enterprise.address!''}</#if>" /></div>
    			<div><span  class="enter <#if  enterprise??&&enterprise.formType??&&enterprise.formType==1>hide</#if>">职工人数：</span>
    			        <span  class="pro  <#if  enterprise??&&enterprise.formType??&&enterprise.formType==0 ||  enterprise??&&!enterprise.formType??>hide</#if>">团队人数：</span>
    				<input type="text" name="staffNumber" datatype="n"  value="<#if  enterprise??&&enterprise.staffNumber??>${enterprise.staffNumber?c!''}</#if>" /></div>
    			<div><span>行业归属：</span>
    				<#if enterpriseType_list??>
    					<#list enterpriseType_list as item>
    						<input style="margin-top: 3px; width:15px;" name="type" type="checkbox" datatype="*" value="${item.title!''}" 
    							<#if enterprise.formType?? && enterpriseType??>
	    			     			<#list enterpriseType as type>
	    			     				<#if type == item.title>
	    			     					checked="checked"
	    			     				</#if>
	    			     			</#list>
	    			     		</#if>			
    							/><p>${item.title!''}</p>
    					</#list>
    				</#if>		
				</div>
				<div class="pro <#if  enterprise??&&enterprise.formType??&&enterprise.formType==0 ||  enterprise??&&!enterprise.formType??>hide</#if>"><span>主要负责人：</span><input type="text"  name="inCharge" datatype="*" ignore="ignore" value="<#if enterprise.formType??>${enterprise.inCharge!''}</#if>" /></div>
				<div><span>邮箱（选填）：</span><input type="text" name="email" datatype="e"  ignore="ignore" value="<#if  enterprise??&&enterprise.formType??>${enterprise.email!''}</#if>" errormsg="请填写邮箱"/></div>
    			<div><span>联系人：</span><input type="text"  name="contact" datatype="*"  value="<#if  enterprise??&&enterprise.formType??>${enterprise.contact!''}</#if>" /></div>
    			<div><span>网站（选填）：</span><input type="text" name="website" datatype="url"  ignore="ignore" value="<#if  enterprise??&&enterprise.formType??>${enterprise.website!''}</#if>" /></div>
    			<div><span>联系电话：</span><input type="text"  name="telephone" datatype="*" value="<#if  enterprise??&&enterprise.formType??>${enterprise.telephone!''}</#if>" /></div>
    			<div><span>传真（选填）：</span><input type="text"  name="fax" datatype="*"  ignore="ignore" value="<#if  enterprise??&&enterprise.formType??>${enterprise.fax!''}</#if>" /></div>
    			<div><span>QQ/MSN：</span><input type="text" name="chat" datatype="*" ignore="ignore"  value="<#if  enterprise??&&enterprise.formType??>${enterprise.chat!''}</#if>" /></div>
    			<div><span>手机：</span><input type="text"  name="mobile" datatype="m|/^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/"  value="<#if  enterprise??&&enterprise.formType??>${enterprise.mobile!''}</#if>" errormsg="请填写手机！"/></div>
    			<div>
	    			<span class="enter <#if  enterprise??&&enterprise.formType??&&enterprise.formType==1>hide</#if>">企业简介：</span>
	    			<span class="pro <#if  enterprise??&&enterprise.formType??&&enterprise.formType==0 || enterprise??&&!enterprise.formType??>hide</#if>">团队简介：</span><textarea name="profile" datatype="*5-199"  errormsg="输入5到200字" tip="200字以内"><#if enterprise.formType??>${enterprise.profile!''}</#if></textarea>
    			</div>
    			<div  class="enter <#if  enterprise??&&enterprise.formType??&&enterprise.formType==1>hide</#if>"><span>公司团队：</span><textarea name="teamIntroduction" datatype="*5-199" ignore="ignore" errormsg="输入5到200字"  tip="200字以内"><#if enterprise.formType??>${enterprise.teamIntroduction!''}</#if></textarea></div>
    			<div><span>技术特点及优势：</span><textarea name="advantage" datatype="*5-200" errormsg="输入5到200字"  tip="200字以内"><#if  enterprise??&&enterprise.formType??>${enterprise.advantage!''}</#if></textarea></div>
    			<div>
    			<span class="enter <#if  enterprise??&&enterprise.formType??&&enterprise.formType==1>hide</#if>">市场规模行业地位：</span>
    			<span class="pro <#if  enterprise??&&enterprise.formType??&&enterprise.formType==0 ||  enterprise??&&!enterprise.formType??>hide</#if>">预期市场前景：</span>
    			<textarea name="size" datatype="*5-200" errormsg="输入5到200字" tip="200字以内"><#if  enterprise??&&enterprise.formType??>${enterprise.size!''}</#if></textarea>
    			</div>

    	</dd>
    	<dt class="dt02 enter <#if  enterprise??&&enterprise.formType??&&enterprise.formType==1>hide</#if>"  style="width: 1000px; text-align: left; height: 30px;"><span>二、近三年财务状况（单位：万元）</span><br/><p>此信息将自动生成到报名表中</p></dt>
    	<dd style="margin-left: 62px;" class="enter <#if  enterprise??&&enterprise.formType??&&enterprise.formType==1>hide</#if>">
			<div class="enter <#if  enterprise??&&enterprise.formType??&&enterprise.formType==1>hide</#if>">
    			<div>
    				<span>年限</span>
    				<span style=" display: block; width:170px; text-align: center; margin-left: 20px;">总资产</span>
    				<span style=" display: block; width:170px; text-align: center; margin-left: 20px;">净资产</span>
    				<span style=" display: block; width:170px; text-align: center; margin-left: 20px;">销售收入</span>
    				<span style=" display: block; width:170px; text-align: center; margin-left: 20px;">毛利润</span>
    			</div>
    			<div>
    				<span><#if lastyear3??>${lastyear3?string("yyyy")}</#if></span>

    				<input type="text" name="lastAssets3" ignore="ignore" datatype="/^(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/" value="<#if  enterprise??&&enterprise.lastAssets3??>${enterprise.lastAssets3?c!''}</#if>" ignore="ignore"  errormsg="请填写数字！"/>
    				<input type="text" name="lastNetAssets3"  ignored="ignored" datatype="/^(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/"  value="<#if  enterprise??&&enterprise.lastNetAssets3??>${enterprise.lastNetAssets3?c!''}</#if>"  ignore="ignore"   errormsg="请填写数字！"/>
    				<input type="text" name="lastSale3" ignored="ignored" datatype="/^(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/"  value="<#if  enterprise??&&enterprise.lastSale3??>${enterprise.lastSale3?c!''}</#if>"  ignore="ignore"  errormsg="请填写数字！" />
    				<input type="text" name="lastProfit3" ignored="ignored" datatype="/^(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/"  value="<#if enterprise??&& enterprise.lastProfit3??>${enterprise.lastProfit3?c!''}</#if>"  ignore="ignore"   errormsg="请填写数字！"/>
    			</div>
    			<div>
    				<span><#if lastyear2??>${lastyear2?string("yyyy")}</#if></span>

    				<input type="text" name="lastAssets2" ignored="ignored" datatype="/^(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/"  value="<#if  enterprise??&&enterprise.lastAssets2??>${enterprise.lastAssets2?c!''}</#if>"  ignore="ignore"   errormsg="请填写数字！"/>
    				<input type="text" name="lastNetAssets2" ignored="ignored" datatype="/^(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/"  value="<#if enterprise??&& enterprise.lastNetAssets2??>${enterprise.lastNetAssets2?c!''}</#if>"   ignore="ignore"  errormsg="请填写数字！"/>
    				<input type="text" name="lastSale2" ignored="ignored" datatype="/^(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/"  value="<#if  enterprise??&&enterprise.lastSale2??>${enterprise.lastSale2?c!''}</#if>"  ignore="ignore"  errormsg="请填写数字！" />
    				<input type="text" name="lastProfit2" ignored="ignored" datatype="/^(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/"  value="<#if  enterprise??&&enterprise.lastProfit2??>${enterprise.lastProfit2?c!''}</#if>"   ignore="ignore"  errormsg="请填写数字！"/>
    			</div>
    			<div>
    				<span><#if lastyear1??>${lastyear1?string("yyyy")}</#if></span>
    				<input type="text" name="lastAssets1" ignored="ignored" datatype="/^(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/"  value="<#if enterprise??&& enterprise.lastAssets1??>${enterprise.lastAssets1?c!''}</#if>"  ignore="ignore"   errormsg="请填写数字！" />
    				<input type="text" name="lastNetAssets1" ignored="ignored" datatype="/^(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/"  value="<#if  enterprise??&&enterprise.lastNetAssets1??>${enterprise.lastNetAssets1?c!''}</#if>"  ignore="ignore"    errormsg="请填写数字！"/>
    				<input type="text" name="lastSale1" ignored="ignored" datatype="/^(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/"  value="<#if enterprise??&& enterprise.lastSale1??>${enterprise.lastSale1?c!''}</#if>"  ignore="ignore"   errormsg="请填写数字！" />
    				<input type="text" name="lastProfit1" ignored="ignored" datatype="/^(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/"  value="<#if  enterprise??&&enterprise.lastProfit1??>${enterprise.lastProfit1?c!''}</#if>"   ignore="ignore"  errormsg="请填写数字！" />
    			</div>
    		</div>	
    	</dd>
    	<dt class="dt03  enter <#if  enterprise??&&enterprise.formType??&&enterprise.formType==1>hide</#if>"  style="width: 1000px; text-align: left; height: 30px;">
    		<span>三、知识产权基本情况</span>
    		<br/><p>此信息将自动生成到报名表中</p>
    	</dt>
    	<dt class="dt03 pro <#if enterprise??&& enterprise.formType??&&enterprise.formType==0 || enterprise??&&!enterprise.formType??>hide</#if>"  style="width: 1000px; text-align: left; height: 30px;">
    		<span>二、知识产权基本情况</span>
    		<br/><p>此信息将自动生成到报名表中</p>
    	</dt>   	
    	<dd style="margin-left: 62px;">
    			<div><span>发明专利数量</span><input type="text" name="inventiPatent" datatype="n" style="width:40px;" value="<#if  enterprise??&&enterprise.formType??>${enterprise.inventiPatent!'0'}<#else>0</#if>"  /></div>
    			<div><span>实用新型专利数量</span><input type="text"name="newPatent" datatype="n"  style="width:40px;" value="<#if  enterprise??&&enterprise.formType??>${enterprise.newPatent!'0'}<#else>0</#if>"  /></div>
    			<div><span>外观设计专利数量</span><input type="text" name="designPatent" datatype="n" style="width:40px;" value="<#if  enterprise??&&enterprise.formType??>${enterprise.designPatent!'0'}<#else>0</#if>"  /></div>
    	</dd>
    	<dt class="dt04 enter <#if  enterprise??&&enterprise.formType??&&enterprise.formType==1>hide</#if>"  style="width: 1000px; text-align: left; height: 30px;"><span>四、融资信息（单位：万元）</span>
    								<br/><p>此信息将自动生成到报名表中</p>
    	</dt>
    	<dt class="dt04 pro <#if  enterprise??&&enterprise.formType??&&enterprise.formType==0 ||!enterprise.formType??>hide</#if>"  style="width: 1000px; text-align: left; height: 30px;"><span>三、融资信息（单位：万元）</span>
    								<br/><p>此信息将自动生成到报名表中</p>
    	</dt>    	
    	<dd style="margin-left: 62px;">
    			<div>
    				<span>期望融资方式</span>
    				<span style=" display: block; width:170px; text-align: center; margin-left: 20px;">期望获得资金的时间</span>
    				<span style=" display: block; width:170px; text-align: center; margin-left: 20px;">期望融资金额</span>
    				<span style=" display: block; width:170px; text-align: center; margin-left: 20px;">融资用途</span>
    			</div>
    			<div>
    				<span>（一）股权融资</span>
    				<input  type="text" id="equi" value="<#if enterprise.expectEquityDate??>${enterprise.expectEquityDate?string("yyyy年MM月dd日")!''}</#if>" class="input date" onfocus="WdatePicker({dateFmt:'yyyy年MM月dd日',vel:'expectEquityDate',lang:'zh-cn'})" datatype="*" ignore="ignore"/ >
    			  	<input id="expectEquityDate" name="expectEquityDate" value="<#if enterprise.expectEquityDate??>${enterprise.expectEquityDate?string("yyyy-MM-dd")!''}</#if>" type="hidden" datatype="/^\s*$|^\d{4}\-\d{1,2}\-\d{1,2}$/" errormsg="填写正确格式" sucmsg=" " />
    				
    				<input type="text" name="expectEquityAmount" value="<#if enterprise.formType??><#if enterprise.expectEquityAmount??>${enterprise.expectEquityAmount?c}</#if></#if>"  ignore="ignore" datatype="/^(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/"  errormsg="请填写数字！"/>
    				<input type="text" style="width:400px;" name="expectEquityUse" datatype="*2-100"  errormsg="输入2到100字"  ignore="ignore" value="<#if enterprise.formType??>${enterprise.expectEquityUse!''}</#if>"  />
    			</div>
    			<div>
    				<span>（二）债权融资</span>
    				<input  type="text" id="bond" value="<#if enterprise.expectBondDate??>${enterprise.expectBondDate?string("yyyy年MM月dd日")!''}</#if>" class="input date" onfocus="WdatePicker({dateFmt:'yyyy年MM月dd日',vel:'expectBondDate',lang:'zh-cn'})" datatype="*" ignore="ignore"/ >
    			  	<input id="expectBondDate" name="expectBondDate" value="<#if enterprise.expectBondDate??>${enterprise.expectBondDate?string("yyyy-MM-dd")!''}</#if>" type="hidden" datatype="/^\s*$|^\d{4}\-\d{1,2}\-\d{1,2}$/" errormsg="填写正确格式" sucmsg=" " />
    				
    				<input type="text" name="expectBondAmount" value="<#if enterprise.formType??><#if enterprise.expectBondAmount??>${enterprise.expectBondAmount?c}</#if></#if>" ignore="ignore" datatype="/^(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/"  errormsg="请填写数字！" />
    				<input type="text" style="width:400px;" name="expectBondUse" datatype="*2-100" errormsg="输入2到100字" ignore="ignore" value="<#if enterprise.formType??>${enterprise.expectBondUse!''}</#if>"  />
    			</div>
    			
    			<div>
    				<p>项目可供资料</p>
	    			<input style=" width:15px;"  type="checkbox"  name="dataAble" value="商业计划书"
	    				    		<#if enterprise.dataAble?? && dataAble??>
		    			     			<#list dataAble as item>
		    			     				<#if item == "商业计划书">
		    			     					checked="checked"
		    			     				</#if>
		    			     			</#list>
		    			     		</#if>		
	    			 />
	    			<span style=" width:auto; display: block; margin-left: 10px; margin-top: 3px; ">商业计划书</span>
	    			
	    			 <input style=" width:15px;"  type="checkbox"  name="dataAble" value="可行性报告"
	    				    		<#if enterprise.dataAble?? && dataAble??>
		    			     			<#list dataAble as item>
		    			     				<#if item == "可行性报告">
		    			     					checked="checked"
		    			     				</#if>
		    			     			</#list>
		    			     		</#if>		
	    			 />
	    			<span style=" width:auto; display: block; margin-left: 10px; margin-top: 3px; ">可行性报告</span>
	    			
	    		    <input style=" width:15px;"  type="checkbox"  name="dataAble" value="其他说明资料"
	    				    		<#if enterprise.dataAble?? && dataAble??>	
		    			     			<#list dataAble as item>
		    			     				<#if item == "其他说明资料">
		    			     					checked="checked"
		    			     				</#if>
		    			     			</#list>
		    			     		</#if>		
	    			 />
	    			<span style=" width:auto; display: block; margin-left: 10px; margin-top: 3px; ">其他说明资料</span>
    			</div>
    			
    			<div>
    				<p class="enter <#if enterprise.formType??&&enterprise.formType==1>hide</#if>">是否愿意将贵公司所填以上信息向投资金融平台披露</p>
    				<p  class="pro <#if enterprise.formType??&&enterprise.formType==0 ||!enterprise.formType??>hide</#if>">是否愿意将团队所填以上信息向投资金融平台披露</p>
    			<input style=" width:15px;"  type="radio" <#if enterprise.formType??&& enterprise.isShow ||!enterprise.formType??> checked="checked"</#if> name="isShow" value="true" onclick="javascript:allowsubmit();"/>
    			<span  class="enter <#if enterprise.formType??&&enterprise.formType==1>hide</#if>" style=" width:auto; margin-left: 10px; margin-top: 3px; ">是（同意请加盖公司公章）</span>
    			<span  class="pro <#if enterprise.formType??&&enterprise.formType==0 ||!enterprise.formType??>hide</#if>" style=" width:auto; margin-left: 10px; margin-top: 3px; ">是（同意请签字）</span>
        		<input style=" width:15px;" type="radio"  <#if enterprise.formType??&& !enterprise.isShow> checked="checked"</#if> name="isShow" value="false" onclick="javascript:forbidsubmit();"/>
        		<span style=" width:auto; display: block; margin-left: 10px; margin-top:3px;">否</span>
    			</div>
    	</dd>
    	<dt class="dt05">
    		<input id="submitbutton"  type="submit" <#if enterprise.isShow??&& !enterprise.isShow>style="background:#666666" <#else>style="cursor:pointer;"</#if> value="保存" <#if enterprise.isShow??&& !enterprise.isShow> disabled="disabled"</#if> />
    	</dt>
    	</form>
    	</dl>
    </div>
</div><!--content_end-->
</div>
<!--/基本资料-->
<!--文件管理-->
<div class="tab-content"  style="display:none;">  
<div class="right_content">
	<form id="dataUpload"  action="/Verwalter/user/data/submit" method="post">
	<input type="hidden" name="id" value="${enterprise.id?c!''}" />
	  	<dl>
	    	<dt>
	    	资料扫描件：
	    	</dt>
	    	<dd>
			     <input  name="fileUrl" type="text" id="txtImgUrl1" value="<#if enterprise??>${enterprise.fileUrl!''}</#if>" class="input normal upload-path" >
				<div class="upload-box upload-img"></div>
                <div class="photo-list thumb_ImgUrl_show1" style="display: none;">
                    <ul>
                        <li>
                            <div class="img-box1"></div>
                        </li>
                    </ul>
                </div>						   
				<#if enterprise.fileUrl??&&enterprise.fileUrl != "">
			    		<dd>已上传资料：<a href="/download/data?name=${enterprise.fileUrl!''}" title="点击下载">${enterprise.fileUrl!''}</a></dd>
		    	</#if>	
	    	</dd>
	    </dl>		   

	  	<dl>
	    	<dt>项目展示PPT：</dt>
	    	<dd>
			     <input  name="pptUrl" type="text" id="txtImgUrl2" value="<#if enterprise??>${enterprise.pptUrl!''}</#if>" class="input normal upload-path" >
				<div class="upload-box upload-img"></div>
                <div class="photo-list thumb_ImgUrl_show2" style="display: none;">
                    <ul>
                        <li>
                            <div class="img-box1"></div>
                        </li>
                    </ul>
                </div>	
			 <#if enterprise.pptUrl??&&enterprise.pptUrl != "">
		    		<dd>已上传资料：<a href="/download/data?name=${enterprise.pptUrl!''}" title="点击下载">${enterprise.pptUrl!''}</a></dd>
	    	</#if>
	    	</dd>
	    </dl>		   
    
		<dl>
	    	<dt>
	    	<#if enterprise.dataAble??>
				<#list enterprise.dataAble?split(",") as data>
					<#if data == "商业计划书">
						<img src="/client/images/n0.png" title="已选此项" alt="+"  />   
					</#if>
				</#list>
			</#if>
			商业计划书：
	    	</dt>
	    	<dd>
			     <input  name="dataBusiness" type="text" id="txtImgUrl3" value="<#if enterprise??>${enterprise.dataBusiness!''}</#if>" class="input normal upload-path" >
				<div class="upload-box upload-img"></div>
                <div class="photo-list thumb_ImgUrl_show3" style="display: none;">
                    <ul>
                        <li>
                            <div class="img-box1"></div>
                        </li>
                    </ul>
                </div>	
					<#if enterprise.dataBusiness??&&enterprise.dataBusiness != "">
					    	<dd>已上传资料：<a href="/download/data?name=${enterprise.dataBusiness!''}" title="点击下载">${enterprise.dataBusiness!''}</a></dd>
				    </#if>					  
			</dd>
		</dl>										   

		<dl>	  
	    	<dt>
	    	<#if enterprise.dataAble??>
				<#list enterprise.dataAble?split(",") as data>
					<#if data == "可行性报告">
						<img src="/client/images/n0.png" title="已选此项" alt="+"  />   
					</#if>
				</#list>
			</#if>		    	
	    	可行性报告：
	    	</dt>
	    	<dd>
			     <input  name="dataPossible" type="text" id="txtImgUrl4" value="<#if enterprise??>${enterprise.dataPossible!''}</#if>" class="input normal upload-path" >
				<div class="upload-box upload-img"></div>
                <div class="photo-list thumb_ImgUrl_show4" style="display: none;">
                    <ul>
                        <li>
                            <div class="img-box1"></div>
                        </li>
                    </ul>
                </div>	
				    <#if enterprise.dataPossible??&&enterprise.dataPossible != "">
			    		     <dd>已上传资料：<a href="/download/data?name=${enterprise.dataPossible!''}" title="点击下载">${enterprise.dataPossible!''}</a></dd>
				    </#if>	    	
	    	</dd>
	    </dl>			     

		<dl>      	
	    	<dt>
	    	<#if enterprise.dataAble??>
				<#list enterprise.dataAble?split(",") as data>
					<#if data == "其他说明资料">
						<img src="/client/images/n0.png" title="已选此项" alt="+"  />   
					</#if>
				</#list>
			</#if>		    	
	    	其他说明资料：
	    	</dt>
	    	<dd>
			     <input  name="dataOther" type="text" id="txtImgUrl5" value="<#if enterprise??>${enterprise.dataOther!''}</#if>" class="input normal upload-path" >
				<div class="upload-box upload-img"></div>
                <div class="photo-list thumb_ImgUrl_show5" style="display: none;">
                    <ul>
                        <li>
                            <div class="img-box1"></div>
                        </li>
                    </ul>
                </div>	
				    <#if enterprise.dataOther??&&enterprise.dataOther != "">
					    	<dd>已上传资料：<a href="/download/data?name=${enterprise.dataOther!''}" title="点击下载">${enterprise.dataOther!''}</a></dd>
				    </#if>	    	
	    	</dd>
	    </dl>
	<div class="page-footer">
	  <div class="btn-list">
	    <input type="submit" value="提交保存"  class="btn">
	    <input name="btnReturn" type="button" value="返回上一页" class="btn yellow" onclick="javascript:history.back(-1);">
	  </div>
	  <div class="clear"></div>
	</form>	

    </div>

</div><!--content_end-->
</div>
<!--/文件管理-->
</#if>

<!--安全设置-->
<form name="form_user" method="post" action="/Verwalter/user/save" id="form_user">
<div class="tab-content" <#if enterprise??> style="display:none;"</#if>>  

<div>
<input type="hidden" name="__VIEWSTATE" id="__VIEWSTATE" value="${__VIEWSTATE!""}" >
<input type="hidden" name="userId" value="<#if user??>${user.id!""}</#if>" >
<input type="hidden" name="roleId" value="<#if user??>${user.roleId!""}<#else>1</#if>" >
</div>
 <dl>
    <dt>用户状态</dt>
    <dd>
      <div class="rule-multi-radio">
        <span id="rblStatus">
            <#--
            <input type="radio" name="statusId" value="0" datatype="n" <#if user?? && user.statusId?? && user.statusId==0>checked="checked"</#if>>
            <label>待审核</label>
            -->
            <input type="radio" name="statusId" value="1" datatype="n" <#if !user?? || user.statusId?? && user.statusId==1>checked="checked"</#if>>
            <label>正常</label>
            <input type="radio" name="statusId" value="2" datatype="n" <#if user?? && user.statusId?? && user.statusId==2>checked="checked"</#if>>
            <label>禁用</label>
        </span>
      </div>
      <span class="Validform_checktip">*禁用账户无法登录</span>
    </dd>
  </dl>
    <dl>
        <dt>用户名：</dt>
        <dd>
            <#if user??>
                <span>${user.username!""}</span>
            <#else>
                <input name="username" type="text" maxlength="200" class="input normal" datatype="s6-20" ajaxurl="/Verwalter/user/check<#if user??>?id=${user.id}</#if>" sucmsg=" " minlength="2">
            </#if>
            <span class="Validform_checktip">
        </span></dd>
    </dl>
  <dl>
    <dt>登录密码</dt>
    <dd><input name="password" type="password" value="<#if user??>${user.password!''}</#if>" class="input normal" datatype="*6-20" nullmsg="请设置密码" errormsg="密码范围在6-20位之间" sucmsg=" " value=""> <span class="Validform_checktip">*登录的密码，至少6位</span></dd>
  </dl>
  <dl>
    <dt>确认密码</dt>
    <dd><input name="password1" type="password" value="<#if user??>${user.password!''}</#if>" class="input normal" datatype="*" recheck="password" nullmsg="请再输入一次密码" errormsg="两次输入的密码不一致" sucmsg=" " value=""> <span class="Validform_checktip">*再次输入密码</span></dd>
  </dl>
  <dl>
    <dt>邮箱账号</dt>
    <dd><input name="email" type="text" value="<#if user??>${user.email!""}</#if>" id="txtEmail" class="input normal" datatype="e" sucmsg=" " > </dd>
  </dl>
  <dl>
    <dt>手机号码</dt>
    <dd><input name="mobile" type="text" value="<#if user??>${user.mobile!""}</#if>" class="input normal" datatype="m|/^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/" ajaxurl="/Verwalter/user/check/mobile<#if user??>?id=${user.id?c!''}</#if>" sucmsg=" " ><span class="Validform_checktip">*取回密码时用到</span></dd>
  </dl>
  


<!--/安全设置-->


   <!--工具栏-->
<div class="page-footer">
  <div class="btn-list">
    <input type="submit" name="btnSubmit" value="提交保存" id="btnSubmit" class="btn">
    <input name="btnReturn" type="button" value="返回上一页" class="btn yellow" onclick="javascript:history.back(-1);">
  </div>
  <div class="clear"></div>

</div>
<!--/工具栏-->
</div>



 </form>

</body></html>