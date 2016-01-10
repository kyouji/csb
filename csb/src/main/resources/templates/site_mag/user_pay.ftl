<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/mag/style/idialog.css" rel="stylesheet" id="lhgdialoglink">
<title>缴费管理</title>
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


<style>
.apply_content dd div .hide{display:none;}
.apply_content dd .hide{display:none;}
.apply_content dt .hide{display:none;}
.apply_content .hide{display:none;}

.tab-content dl dd span{color: #333;font-weight:normal;}
</style>
<script>
$(document).ready(function(){

    $("#bill_deal").Validform({
            tiptype:4,
            ajaxPost:true,
            callback: function (data) { 
                if (data.code == 0)
                {
                	var dialog = $.dialog.confirm('操作成功！继续进行下一步【上传财务状况表】，确认吗？', 
                            function(){location.href='/Verwalter/bill/finance/upload<#if bill??>?billId=${bill.id?c}</#if>';},
                            function(){location.href='/Verwalter/bill/user/list';});
                    
                }
                else 
                {
                    $.dialog.alert(data.msg);
                    if (data.check == 0)
                    {
                        location.href='/Verwalter/login';
                    }
                }
             }
    });
    
    // 添加赠品
    $("#addItem").click(function(){
        showDialogItem();
    });
       
    //创建促销赠品窗口
    function showDialogItem(obj) {
        var objNum = arguments.length;
        
        var giftDialog = $.dialog({
            id: 'giftDialogId',
            lock: true,
            max: false,
            min: false,
            title: "账面存货",
            content: 'url:/Verwalter/bill/list/dialog/stock?total=' + $("#var_box_gift").children("tr").length,
            width: 600,
            height: 250
        });
        
        //如果是修改状态，将对象传进去
        if (objNum == 1) {
            giftDialog.data = obj;
        }
    }
    
    //删除促销赠品节点
    function delGiftNode(obj) {
        $(obj).parent().parent().remove();
    }
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

function selectDate(time)
{
	location.href='/Verwalter/bill/finance/edit/<#if bill??>?billId=${bill.id?c}<#elseif user??>?userId=${user.id?c} </#if>&time='+time;
}



//账面库存
  


//创建促销赠品窗口
function show_goods_gift_dialog(obj) {
    var objNum = arguments.length;
    var zengpinDialog = $.dialog({
        id: 'zengpinhDialogId',
        lock: true,
        max: false,
        min: false,
        title: "促销赠品",
        content: 'url:/Verwalter/goods/list/dialog/price'<#if pricelist??>+ '?priceId='+${pricelist.id?c}</#if>,
        width: 900,
        height: 600
    });
    //如果是修改状态，将对象传进去
    if (objNum == 1) {
        zengpinDialog.data = obj;
    }
}
    
//删除促销商品节点
function del_goods_gift(obj) {
    $(obj).parent().parent().remove();
    $("#totalGift").val(parseInt($("#totalGift").val())-1);
}

</script>
</head>

<body class="mainbody">

<!--导航栏-->
<div class="location" style="position: static; top: 0px;">
  <a href="/Verwalter/bill/user/list"><i></i><span>返回列表页</span></a>
  <a href="/Verwalter/center" class="home"><i></i><span>首页</span></a>
  <i class="arrow"></i>
  <span>会员管理</span>
  <i class="arrow"></i>
    <span>财务管理</span>
</div>
<div class="line10"></div>
<!--/导航栏-->

<!--内容-->
<div class="content-tab-wrap">
  <div id="floatHead" class="content-tab" style="position: static; top: 52px;">
    <div class="content-tab-ul-wrap">
      <ul>
          <li><a href="javascript:;" onclick="tabs(this);" class="selected menu">基本资料</a></li>
          <li><a href="javascript:;" onclick="tabs(this);" class="">财务状况</a></li>
          <li><a href="javascript:;" onclick="tabs(this);" class="">税金状况</a></li>
          <li><a href="javascript:;" onclick="tabs(this);" class="">账面存货</a></li>
          <li><a href="javascript:;" onclick="tabs(this);" class="">备注</a></li>
      </ul>
    </div>
  </div>
</div>


<!--基本资料-->
<form name="bill_deal" method="post" action="/Verwalter/bill/finance/save" id="bill_deal">

<input type="hidden" name="__VIEWSTATE" id="__VIEWSTATE" value="${__VIEWSTATE!""}" >
<input type="hidden" name="userId" value="<#if user??>${user.id?c!""}</#if>" >
<input type="hidden" name="id" value="<#if tdFinance??>${tdFinance.id?c!""}</#if>" >
<input type="hidden" name="billId" value="<#if bill??>${bill.id?c!""}</#if>" >
<div class="tab-content" style="display: block;">
        <dl>
         <dt>发布月份</dt>
         <dd>
             <div class="input-date"  style="width: 240px;">
                 <input name="time" type="text" id="time" value="<#if gather??&&gather.time??>${gather.time?string("yyyy-MM")}<#elseif date??>${date?string("yyyy-MM")}</#if>" class="input date"  onfocus="WdatePicker({dateFmt:'yyyy-MM',lang:'zh-cn'})" datatype="/^\s*$|^\d{4}\-\d{1,2}$/" errormsg="请选择正确的日期" sucmsg=" "  onchange="javascript:selectDate(this.value);">
                 <i  style="right: 70px;">日期</i>
             </div>
             <#--<span class="Validform_checktip">不选择默认当前时间</span>-->
         </dd>
        </dl>
    <dl>
       <dt>用户名：</dt>
       <dd><#if user??>${user.username!''}</#if></dd>
    </dl>
    <dl>
       <dt>用户编号：</dt>
       <dd><#if user??>${user.number!''}</#if></dd>
    </dl>
    <dl>
       <dt>公司名称：</dt>
       <dd><#if user??>${user.enterName!''}</#if></dd>
    </dl>   
    <#if bill??> 
    <dl>
    <dt>票据</dt>
    <dd>
        <img src="<#if bill.imgUrl??&&bill.imgUrl != "">/images/${bill.imgUrl!""} <#else>/client/images/foote22.png</#if>"  />
    </dd>
  </dl>
  <dl>
    <dt>票据下载</dt>
    <dd><a href="/download/data?name=${bill.imgUrl!''}">${bill.imgUrl!''}</a></dd>
  </dl>
  </#if>
	<dl>
	    <dt>排序数字</dt>
	    <dd>
	        <input name="sortId" type="text" value="<#if tdFinance??>${tdFinance.sortId!""}<#else>99</#if>" id="txtSortId" class="input txt100" datatype="n" ignore="ignore" sucmsg=" ">
	        <span class="Validform_checktip">*数字，越小越向前</span>
	    </dd>
	</dl>  
 </div> 
 
  <!-- 财务状况 -->
<div class="tab-content" style="display: none;">
  <dl>
  	<dt>本月不含税收入</dt>
    <dd>
	    <input name="noTax" id="noTax" type="text" value="<#if tdFinance??&&tdFinance.noTax??>${tdFinance.noTax?string("0.00")}</#if>" class="input normal" style="width:64px;"  datatype="/^(([1-9]\d*)|0)((\.\d{2})|(\.\d{1}))?$/" ignore="ignore" errormsg="请输入最多2位小数" sucmsg=" ">&nbsp;元
    </dd>
  </dl>
  <dl>
  	<dt>累计收入</dt>
    <dd>
	    <input name="totalIncome" id="totalIncome" type="text" value="<#if tdFinance??&&tdFinance.totalIncome??>${tdFinance.totalIncome?string("0.00")}</#if>" class="input normal" style="width:64px;"  datatype="/^(([1-9]\d*)|0)((\.\d{2})|(\.\d{1}))?$/" ignore="ignore" errormsg="请输入最多2位小数" sucmsg=" ">&nbsp;元
    </dd>
  </dl> 
  <dl>
  	<dt>本月利润</dt>
    <dd>
	    <input name="profit" id="profit" type="text" value="<#if tdFinance??&&tdFinance.profit??>${tdFinance.profit?string("0.00")}</#if>" class="input normal" style="width:64px;"  datatype="/^(([1-9]\d*)|0)((\.\d{2})|(\.\d{1}))?$/" ignore="ignore" errormsg="请输入最多2位小数" sucmsg=" ">&nbsp;元
    </dd>
  </dl> 
  <dl>
  	<dt>累计利润</dt>
    <dd>
	    <input name="totalProfit" id="totalProfit" type="text" value="<#if tdFinance??&&tdFinance.totalProfit??>${tdFinance.totalProfit?string("0.00")}</#if>" class="input normal" style="width:64px;"  datatype="/^(([1-9]\d*)|0)((\.\d{2})|(\.\d{1}))?$/" ignore="ignore" errormsg="请输入最多2位小数" sucmsg=" ">&nbsp;元
    </dd>
  </dl> 
  <dl>
  	<dt>累计毛利率</dt>
    <dd>
	    <input name="totalGross" id="totalGross" type="text" value="<#if tdFinance??&&tdFinance.totalGross??>${tdFinance.totalGross?string("0.00")}</#if>" class="input normal" style="width:64px;"  datatype="/^(([1-9]\d*)|0)((\.\d{2})|(\.\d{1}))?$/" ignore="ignore" errormsg="请输入最多2位小数" sucmsg=" ">&nbsp;元
    </dd>
  </dl> 
</div>  

    <!-- 税金状况 -->
	<div class="tab-content" style="display: none;">
	  <dl>
	    <dt>本月留抵税金</dt>
	    <dd>
	    	<input name="taxRetention" id="taxRetention" type="text" value="<#if tdFinance??&&tdFinance.taxRetention??>${tdFinance.taxRetention?string("0.00")}</#if>" class="input normal" style="width:64px;"  datatype="/^(([1-9]\d*)|0)((\.\d{2})|(\.\d{1}))?$/" ignore="ignore" sucmsg=" " errormsg="请输入最多2位小数">&nbsp;元
	    </dd>
	  </dl>
	  <dl>
	    <dt>折合为价税合计</dt>
	    <dd>
	    	<input name="valorem" id="valorem" type="text" value="<#if tdFinance??&&tdFinance.valorem??>${tdFinance.valorem?string("0.00")}</#if>" class="input normal" style="width:64px;"  datatype="/^(([1-9]\d*)|0)((\.\d{2})|(\.\d{1}))?$/" ignore="ignore" sucmsg=" " errormsg="请输入最多2位小数">&nbsp;元
	    </dd>
	  </dl>
	  <dl>
	    <dt>本年累计上缴增值税</dt>
	    <dd>
	    	<input name="totalDeduction" id="totalDeduction" type="text" value="<#if tdFinance??&&tdFinance.totalDeduction??>${tdFinance.totalDeduction?string("0.00")}</#if>" class="input normal" style="width:64px;"  datatype="/^(([1-9]\d*)|0)((\.\d{2})|(\.\d{1}))?$/" ignore="ignore" sucmsg=" " errormsg="请输入最多2位小数">&nbsp;元，
	    	<span>税负</span>
	    	<input name="taxBear" id="taxBear" type="text" value="<#if tdFinance??>${tdFinance.taxBear!''}</#if>" class="input normal" style="width:64px;"   ignore="ignore" sucmsg=" " >
	    </dd>
	  </dl>
	  <dl>
	    <dt>本年累计上缴所得税</dt>
	    <dd>
	    	<input name="totalIncomeTax" id="totalIncomeTax" type="text" value="<#if tdFinance??&&tdFinance.totalIncomeTax??>${tdFinance.totalIncomeTax?string("0.00")}</#if>" class="input normal" style="width:64px;"  datatype="/^(([1-9]\d*)|0)((\.\d{2})|(\.\d{1}))?$/" ignore="ignore" sucmsg=" " errormsg="请输入最多2位小数">&nbsp;元
	    </dd>
	  </dl>
	  <dl>
	    <dt>本月待抵扣税金</dt>
	    <dd>
	    	<input name="todo" id="todo" type="text" value="<#if tdFinance??&&tdFinance.todo??>${tdFinance.todo?string("0.00")}</#if>" class="input normal" style="width:64px;"  datatype="/^(([1-9]\d*)|0)((\.\d{2})|(\.\d{1}))?$/" ignore="ignore" sucmsg=" " errormsg="请输入最多2位小数">&nbsp;元
	    </dd>
	  </dl>
	  <dl>
	    <dt>折合为价税合计</dt>
	    <dd>
	    	<input name="todoValorem" id="todoValorem" type="text" value="<#if tdFinance??&&tdFinance.todoValorem??>${tdFinance.todoValorem?string("0.00")}</#if>" class="input normal" style="width:64px;"  datatype="/^(([1-9]\d*)|0)((\.\d{2})|(\.\d{1}))?$/" ignore="ignore" sucmsg=" " errormsg="请输入最多2位小数">&nbsp;元
	    </dd>
	  </dl>
	  
  <dl>
    <dt>已收抵扣联</dt>
    <dd>
	    <input name="doneAmount" id="doneAmount" type="text" value="<#if tdFinance??>${tdFinance.doneAmount!''}</#if>" class="input normal" style="width:24px;"  datatype="n" ignore="ignore" sucmsg=" " >&nbsp;份，
	    <#--
	    <span>税额</span>
	    <input name="deTodo" id="deTodo" type="text" value="<#if tdFinance??&&tdFinance.deTodo??>${tdFinance.deTodo?string("0.00")}</#if>" class="input normal" style="width:64px;"  datatype="/^(([1-9]\d*)|0)((\.\d{2})|(\.\d{1}))?$/" ignore="ignore" sucmsg=" " errormsg="请输入最多2位小数">&nbsp;元，
	    -->
	    <span>最早日期</span>
	     <div class="input-date"  style="width: 240px;">
	         <input name="doneEarlyDate" type="text" id="doneEarlyDate" value="<#if tdFinance??&&tdFinance.doneEarlyDate??>${tdFinance.doneEarlyDate?string("yyyy-MM-dd")}</#if>" class="input date" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',lang:'zh-cn'})" datatype="/^\s*$|^\d{4}\-\d{1,2}\-\d{1,2}$/" errormsg="请选择正确的日期" sucmsg=" ">
	         <i style="right: 70px;">日期</i>
	     </div>
    </dd>
  </dl>
  
  <dl>
    <dt>未收抵扣联</dt>
    <dd>
	    <input name="todoAmount" id="todoAmount" type="text" value="<#if tdFinance??>${tdFinance.todoAmount!''}</#if>" class="input normal" style="width:24px;"  datatype="n" ignore="ignore" sucmsg=" " >&nbsp;份，
	    <#--
	    <span>税额</span>
	    <input name="deTodo" id="deTodo" type="text" value="<#if tdFinance??&&tdFinance.deTodo??>${tdFinance.deTodo?string("0.00")}</#if>" class="input normal" style="width:64px;"  datatype="/^(([1-9]\d*)|0)((\.\d{2})|(\.\d{1}))?$/" ignore="ignore" sucmsg=" " errormsg="请输入最多2位小数">&nbsp;元，
	    -->
	    <span>最早日期</span>
	     <div class="input-date"  style="width: 240px;">
	         <input name="todoEarlyDate" type="text" id="todoEarlyDate" value="<#if tdFinance??&&tdFinance.todoEarlyDate??>${tdFinance.todoEarlyDate?string("yyyy-MM-dd")}</#if>" class="input date" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',lang:'zh-cn'})" datatype="/^\s*$|^\d{4}\-\d{1,2}\-\d{1,2}$/" errormsg="请选择正确的日期" sucmsg=" ">
	         <i style="right: 70px;">日期</i>
	     </div>
    </dd>
  </dl>
	  
	</div>  




   <!-- 账面存货 -->
<div class="tab-content" style="display: none;">

        <dl>
            <dt>选择商品</dt>
            <dd>
                <a id="addItem" class="icon-btn add"><i></i><span>添加商品</span></a>
                <span class="Validform_checktip"></span>
            </dd>
        </dl>
        <dl>
            <dt></dt>
            <dd>
                <table border="0" cellspacing="0" cellpadding="0" class="border-table" width="98%">
                    <thead>
                        <tr>
                            <th width="6%">
                                排序
                            </th>
                            <th width="38%">
                                名称
                            </th>
                            <th width="10%">
                                数量
                            </th>
                            <th width="10%">
                                单价
                            </th>
                            <th width="10%">
                                金额
                            </th>
                            <th width="6%">
                                操作
                            </th>
                        </tr>
                    </thead>
                    <tbody id="var_box_gift">
                    <#if tdFinance??>
                        <#if tdFinance.stockList??>
                            <#list tdFinance.stockList as gift>
                                <tr class="td_c">
                                    <td>
                                        <input type="hidden" name="stockList[${gift_index}].id"  value="${gift.id?c!''}">
                                        <input type="text" name="stockList[${gift_index}].sortId" class="td-input" value="${gift.sortId!''}" style="width:90%;">
                                    </td>
                                    <td>
                                        <input type="text" id="title" name="stockList[${gift_index}].title" class="td-input" value="${gift.title!''}" style="width:90%;">
                                    </td>
                                    <td>
                                        <input type="text" id="amount" name="stockList[${gift_index}].amount" class="td-input" value="<#if gift.amount??>${gift.amount}<#else>0</#if>" style="width:90%;">
                                    </td>
                                    <td>
                                        <input type="text" id="price" name="stockList[${gift_index}].price" class="td-input" value="<#if gift.price??>${gift.price?string("0.00")}<#else>0.00</#if>" style="width:90%;">
                                    </td>
                                    <td>
                                        <input type="text" id="money" name="stockList[${gift_index}].money" class="td-input" value="<#if gift.money??>${gift.money?string("0.00")}<#else>0.00</#if>" style="width:90%;">
                                    </td>
                                    <td>
                                        <i class="icon"></i>
                                        <a title="编辑" class="img-btn edit operator" onclick="show_goods_gift_dialog(this);">编辑</a>
                                        <a title="删除" class="img-btn del operator" onclick="del_goods_gift(this);">删除</a>
                                    </td>
                                </tr>
                            </#list>
                        </#if>
                       </#if> 
                    </tbody>
                </table>
            </dd>
        </dl>
    
  
  
  
  
</div>  











<!-- 补充说明-->
<div class="tab-content" style="display: none;">   
    <dl>
        <dt>提示消息</dt>
        <dd>
            <textarea name="tip" rows="2" cols="20" id="tip" class="input" style="height:60px;width:350px;" datatype="*0-255" sucmsg=" "><#if tdFinance??>${tdFinance.tip!""}</#if></textarea>
            <span class="Validform_checktip">一条给用户的提示信息</span>
        </dd>
    </dl>
    <dl>
        <dt>补充说明</dt>
        <dd>
            <textarea name="content" rows="2" cols="20" id="content" class="input" style="height:100px;width:350px;" datatype="*0-255" sucmsg=" "><#if tdFinance??>${tdFinance.content!""}</#if></textarea>
            <span class="Validform_checktip">显示给用户查看</span>
        </dd>
    </dl>

    <dl>
        <dt>备注</dt>
        <dd>
            <textarea name="remark" rows="2" cols="20" id="remark" class="input" style="height:200px;width:600px;" datatype="*0-1024" sucmsg=" "><#if tdFinance??>${tdFinance.remark!""}</#if></textarea>
            <span class="Validform_checktip">不显示给用户查看</span>
        </dd>
    </dl>    
</div>  

    

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