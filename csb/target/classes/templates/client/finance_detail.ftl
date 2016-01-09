<!DOCTYPE html>
<html>
	<head>
		<meta name="keywords" content="">
		<meta name="description" content="">
		<meta name="copyright" content="" />
		<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
		<meta charset="utf-8">
		<title>财务状况</title>
		
		<link rel="stylesheet" type="text/css" href="/client/css/rich_base.css"/>
		<link rel="stylesheet" type="text/css" href="/client/css/rich_other.css"/>
		<script src="/client/js/jquery-1.11.0.js" type="text/javascript"></script>
		<script src="/client/js/Rich_Lee.js" type="text/javascript"></script>
		<script src="/client/js/rich_one.js" type="text/javascript"></script>
		
	</head>
	<body style="background: #f2f2f2;">
		<header class="header_one">
			<a href="javascript:history.back(-1);"></a>
			<div>财务状况</div>
		</header>
		<#if finance??>
		<article class="money">
			<ul class="mon_box01">
				<li>
					<#if finance.time??&&finance.noTax??>
						<div>${finance.time?string("MM")}月不含税收入</div>
						<p><span>${finance.noTax!'0'}</span>(元)</p>
					</#if>
				</li>
				<li>
					<div>累计收入</div>
					<p><span>${finance.totalIncome!'0'}</span>(元)</p>
				</li>
				<li>
					<div>本月利润</div>
					<p><span>${finance.profit!'0'}</span>(元)</p>
				</li>
				<li>
					<div>累计利润</div>
					<p><span><#if user??&&user.totalProfit??>${user.totalProfit!''}<#else>${finance.totalProfit!'0'}</#if></span>(元)</p>
				</li>
				<li>
					<div>累计毛利率</div>
					<p><span><#if user??&&user.totalGross??>${user.totalGross!''}<#else>${finance.totalGross!'0'}</#if></span></p>
				</li>
			</ul>
			<#if user?? && user.enterTypeId?? && user.enterTypeId==2>
			<ul class="mon_box02">
				<li>
					<div>
						本月留抵税金
						<p><span>${finance.taxRetention!'0'}</span>元</p>
					</div>
					<div>
						折合为价税合计
						<p><span>${finance.valorem!'0'}</span>元</p>
					</div>
				</li>
				<li>
					<div>
						本年累计上缴增值税
						<p><span><#if user??&&user.totalDeduction??>${user.totalDeduction!''}<#else>${finance.totalDeduction!'0'}</#if></span>元</p>
					</div>
					<div>
						税负
						<span><#if user??&&user.taxBearing??>${user.taxBearing!''}<#else>${finance.taxBearing!'0'}</#if></span>%
					</div>
				</li>
				<li>
					<div>
						本年累计上缴所得税
						<p><span><#if user??&&user.totalIncomeTax??>${user.totalIncomeTax!''}<#else>${finance.totalIncomeTax!'0'}</#if></span>元</p>
					</div>
				</li>
				<li>
					<div>
						本月待抵扣税金
						<p><span>${finance.todo!'0'}</span>元</p>
					</div>
					<div>
						折合为价税合计
						<p><span>${finance.todoValorem!'0'}</span>元</p>
					</div>
				</li>
				<li>
					<div>
						已收抵扣联<span>${finance.doneAmount!'0'}</span>份
						<p>最早日期<span><#if finance.doneEarlyDate??>${finance.doneEarlyDate?string("yyyy-MM-dd")}</#if></span></p>
					</div>					
				</li>
				<li style="background: none;">
					<a style="display: block; text-align: left;">待抵扣</a>
				</li>
				<li>
					<div>
						未收抵扣联<span>${finance.todoAmount!'0'}</span>份
						<p>最早日期<span><#if finance.todoEarlyDate??>${finance.todoEarlyDate?string("yyyy-MM-dd")}</#if></span></p>
					</div>
				</li>
				<li>
					<div>
						已收抵扣联<span>${finance.doneAmount!'0'}</span>份
						<p>最早日期<span><#if finance.doneEarlyDate??>${finance.doneEarlyDate?string("yyyy-MM-dd")}</#if></span></p>
					</div>
				</li>
			</ul>
			<#else>
			<ul class="mon_box02">
				<li>
					<div>
						<#if finance.time??>${finance.time?string("MM")}月</#if>最高可开票金额（含税价）
						<p><span>${finance.maxTicket!'0'}</span>元</p>
					</div>
				</li>
				<li class="mon02_li01">
					<div>
						<#if finance.tip??>${finance.tip}<#else>连续滚动12个月商贸企业超过80万元（工业企业超过50万元）将自动转为一般纳税人</#if>
					</div>
				</li>
				<li>
					<div>
						本年累计上缴增值税/营业税
						<p><span><#if user??&&user.totalDeduction??>${user.totalDeduction!''}<#else>${finance.totalDeduction!'0'}</#if></span>元</p>
					</div>					
				</li>
				<li>
					<div>
						本年累计上缴所得税
						<p><span><#if user??&&user.totalIncomeTax??>${user.totalIncomeTax!''}<#else>${finance.totalIncomeTax!'0'}</#if></span>元</p>
					</div>
				</li>				
			</ul>
			</#if>
			<div style="height: 20px;float: left;width: 100%;"></div>
			<#if stock??>
				<ul class="mon_box03">
					<li class="li01">
						账面存货(不含税)
					</li>
					<li class="li02">
						<span>名称</span>
						<span>数量</span>
						<span>单价</span>
						<span>金额</span>
					</li>
					<#assign totalStockAmount = 0>
					<#assign totalStockPrice = 0>
					<#assign totalStockMoney = 0>
					<#list stock as item>
						<li class="li02">
							<span>${item.title!''}</span>
							<span>${item.amount!''}</span>
							<span>${item.price!''}元</span>
							<span>${item.money!''}元</span>
							<#assign totalStockAmount = totalStockAmount+item.amount>
							<#assign totalStockPrice = totalStockPrice+item.price>
							<#assign totalStockMoney = totalStockMoney+item.money>
						</li>
					</#list>	
					<li class="li02">
						<span>合计</span>
						<span>${totalStockAmount}</span>
						<span>${totalStockPrice}元</span>
						<span>${totalStockMoney}元</span>
					</li>
				</ul>
			</#if>
			<textarea name="remark" class="mon_tex">请输入补充说明</textarea>
			<a class="mon_see" href="/download/data?name=${finance.fileUrl!''}">资金往来见附表</a>
			<a href="javascript:history.back(-1);" class="mon_back">返回</a>
			<div style="height: 30px;width: 100%;"></div>
		</article>
		<#else>
		<h3>没有财务记录！</h3>
		</#if>
	</body>
</html>
