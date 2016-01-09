<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/mag/style/idialog.css" rel="stylesheet" id="lhgdialoglink">
<title>票据整理</title>
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

</script>
</head>

<body class="mainbody">

<!--导航栏-->
<div class="location" style="position: static; top: 0px;">
  <a href="/Verwalter/bill/list/${statusId!""}"><i></i><span>返回列表页</span></a>
  <a href="/Verwalter/center" class="home"><i></i><span>首页</span></a>
  <i class="arrow"></i>
  <span>票据管理</span>
  <i class="arrow"></i>
    <span>票据整理</span>
</div>
<div class="line10"></div>
<!--/导航栏-->

<!--内容-->
<div class="content-tab-wrap">
  <div id="floatHead" class="content-tab" style="position: static; top: 52px;">
    <div class="content-tab-ul-wrap">
      <ul>
          <li><a href="javascript:;" onclick="tabs(this);" class="selected menu">填写资料</a></li>
      </ul>
    </div>
  </div>
</div>


<!--安全设置-->
<form name="form_user" method="post" action="/Verwalter/bill/deal/submit" id="form_user">

<input type="hidden" name="__VIEWSTATE" id="__VIEWSTATE" value="${__VIEWSTATE!""}" >
<input type="hidden" name="userId" value="<#if user??>${user.id?c!""}</#if>" >
</div>
        <dl>
         <dt>发布月份</dt>
         <dd>
             <div class="input-date">
                 <input name="桃ime" type="text" id="txtAddTime" value="" class="input date" onfocus="WdatePicker({dateFmt:'yyyy-MM',lang:'zh-cn'})" datatype="/^\s*$|^\d{4}\-\d{1,2}\-\d{1,2}\$/" errormsg="请选择正确的日期" sucmsg=" ">
                 <i>日期</i>
             </div>
             <span class="Validform_checktip">不选择默认当前时间</span>
         </dd>
        </dl>
    <dl>
       <dt>用户名：</dt>
       <dd><#if user??>${user.username!''}</#if></dd>
    </dl>
  <dl>
    <dt>票据下载</dt>
    <dd><a href="/download/data?name=${bill.imgUrl!''}">${bill.imgUrl!''}</a></dd>
  </dl>
  <!-- 收入 -->
 <div class="content-tab-wrap">
  <div id="floatHead" class="content-tab">
    <div class="content-tab-ul-wrap">
      <ul>
            <li><a href="javascript:;" onclick="tabs(this);" class="selected">收入</a></li>
      </ul>
    </div>
  </div>
</div>
  <dl>
    <dt>销项税</dt>
    <dd><input name="realName" type="text" value="<#if user??>${user.realName!""}</#if>" class="input normal"  sucmsg=" " ><span class="Validform_checktip">*联系人姓名</span></dd>
  </dl>
  <dl>
    <dt>联系人电话</dt>
    <dd><input name="mobile" type="text" value="<#if user??>${user.mobile!""}</#if>" class="input normal"  sucmsg=" " ><span class="Validform_checktip">*联系人电话</span></dd>
  </dl>
  <dl class="enter" <#if !user?? || user?? && user.roleId?? && user.roleId == 1 >style= "display:none;"</#if>>
    <dt>公司名称</dt>
    <dd><input name="enterName" type="text" value="<#if user??>${user.enterName!""}</#if>" class="input normal"  sucmsg=" " ><span class="Validform_checktip">*公司名称</span></dd>
  </dl> 
  <dl class="enter" <#if !user?? || user?? && user.roleId?? && user.roleId == 1 >style= "display:none;"</#if>>
    <dt>公司类型</dt>
    <dd><input name="enterType" type="text" value="<#if user??>${user.enterName!""}</#if>" class="input normal"  sucmsg=" " ><span class="Validform_checktip">*公司名称</span></dd>
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