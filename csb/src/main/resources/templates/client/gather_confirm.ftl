<!DOCTYPE html>
<html>
	<head>
		<meta name="keywords" content="">
		<meta name="description" content="">
		<meta name="copyright" content="" />
		<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
		<meta charset="utf-8">
		<title>票据整理</title>
		
		<link rel="stylesheet" type="text/css" href="/client/css/rich_base.css"/>
		<link rel="stylesheet" type="text/css" href="/client/css/rich_other.css"/>
		<script src="/client/js/jquery-1.11.0.js" type="text/javascript"></script>
		<script src="/client/js/Rich_Lee.js" type="text/javascript"></script>
		<script src="/client/js/rich_one.js" type="text/javascript"></script>
		
	</head>
	<script type="text/javascript">
		window.onload = function(){
			html_hi();//	
		};
		
		function remarkSubmit()
		{
			$("#remarkform").submit();
		}
	</script>
	<body style="background: #f2f2f2;height: 100%;">
		<form id="remarkform" action="/gather/confirm" method=POST>
		<header class="header_one">
			<a href="javascript:history.back(-1);"></a>
			<div>票据整理</div>
		</header>
		<article class="clearBox">
			<#if gather??>
			<ul class="clear_box02">
				<li class="title">
					<h3>
						<#if gather.title??&&gather.length gt 0>
							${gather.title}
						<#elseif gather.time??>
							${gather.time?string("MM")}月票据整理汇总如下
						</#if>
					</h3>
				</li>
				<li class="li05">
					<p>收入</p>
				</li>
				<li>
					<#if gather.billgeneralAmount??&&gather.generalIncome??>
						<div class="line">
							<p>本月普票<span>${gather.billgeneralAmount!'0'}</span>份</p>
							<p>不含税收入<span>${gather.generalIncome!'0'}</span>(元)</p>
						</div>
					</#if>
					<#if gather.generalTax??>
						<div class="one">
							<p>销项税<span>${gather.generalTax!'0'}</span>(元)</p>
						</div>
					</#if>
				</li>
				<li class="top10">
					<#if gather.specialAmount??&&gather.specialIncome??>
						<div class="line">
							<p>本月专票<span>${gather.specialAmount!'0'}</span>份</p>
							<p>不含税收入<span>${gather.specialIncome!'0'}</span>(元)</p>
						</div>
					</#if>
					<#if gather.specialTax??>
						<div class="one">
							<p>销项税<span>${gather.specialTax!'0'}</span>(元)</p>
						</div>
					</#if>
				</li>
				<li class="top10">
					<#if gather.noTicketIncome??&&gather.noTicketTax??>
						<div>
							<p>不开票收入<span>${gather.noTicketIncome!'0'}</span>(元)</p>
							<p>销项税<span>${gather.noTicketTax!'0'}</span>(元)</p>
						</div>
					</#if>
				</li>
				<li class="top10">
					<#if gather.TotalIncome??&&gather.TotalTax??>
						<div>
							<p>不含税收入合计<span>${gather.TotalIncome!'0'}</span>(元)</p>
							<p>销项税合计<span>${gather.TotalTax!'0'}</span>(元)</p>
						</div>
					</#if>
				</li>
				<li class="li05">
					<p>进货</p>
				</li>
				<li>
					<#if gather.taxRetention??>
						<div>
							<p>上月留底税金</p>
							<p><span>${gather.taxRetention!'0'}</span>(元)</p>
						</div>
					</#if>
				</li>
				<li class="top10">
					<#if gather.vatAmount??&&gather.vat??>
						<div>
							<p>上月待抵扣税金转入本月进项税额<span>${gather.vatAmount!'0'}</span>份</p>
							<p>税额<span>${gather.vat!'0'}</span>(元)</p>
						</div>
					</#if>
				</li>
				<li class="top10">
					<#if gather.transDeductionAmount??&&gather.transDeduction??>
					<div>
						<p>运费(含机动车)抵扣进项税<span>${gather.transDeductionAmount!'0'}</span>份</p>
						<p>税额<span>${gather.transDeduction!''}</span>(元)</p>
					</div>
					</#if>
				</li>
				<li class="top10">
					<#if gather.taxDeductionAmount??&&gather.taxDeduction??>
						<div>
							<p>本月增值税抵扣发票<span>${gather.taxDeductionAmount!'0'}</span>份</p>
							<p>税额<span>${gather.taxDeduction!'0'}</span>(元)</p>
						</div>
					</#if>
				</li>
				<li class="top10">
					<#if gather.totalVat??>
						<div>
							<p>本月进项税额合计</p>
							<p>税额<span>${gather.totalVat!''}</span>(元)</p>
						</div>
					</#if>
				</li>
				<li class="li05">
					<p>本月应纳税金</p>
				</li>
				<li>
					<#if gather.taxAdd??&&gather.taxBearing??>
						<div>
							<p>增值税<span>${gather.taxAdd!'0'}</span>(元)</p>
							<p>累计税负<span>${gather.taxBearing!''}</span></p>
						</div>
					</#if>
				</li>
				<li class="top10">
					<#if gather.incomeTax??>
						<div class="one">
							<p>所得税<span>${gather.incomeTax!'0'}</span>(元)</p>
						</div>
					</#if>
				</li>

				<li class="top10">
					<#if gather.urbanTax??&&gather.eduAdd??>
						<div class="line">
							<p>城建税<span>${gather.urbanTax!'0'}</span>(元)</p>
							<p>教育费附加<span>${gather.eduAdd!'0'}</span>(元)</p>
						</div>
					</#if>
					<#if gather.eduAddLocal??>
						<div class="line">
							地方教育费附加
							<p><span>${gather.eduAddLocal!'0'}</span>元</p>
						</div>
					</#if>
					<#if gather.landTax??>
						<div>
							地税合计
							<p><span>${gather.landTax!'0'}</span>元</p>
						</div>
					</#if>
				</li>
				<li class="li05">
					<p>待抵扣</p>
				</li>
				<li>
					<#if gather.deTodo??&&gather.deTodoAmount??>
						<div>
							<p>未收抵扣联<span>${gather.deTodoAmount!'0'}</span>份</p>
							<p>税额<span>${gather.deTodo!'0'}</span>(元)</p>
						</div>
					</#if>
				</li>
				<li class="li06">
					<#if gather.deTodoDate??>
						<div>最早日期：${gather.deTodoDate?string("yyyy-MM-dd")}</div>
					</#if>
				</li>
				<li>
					<#if gather.deDone??&&gather.deDoneAmount??>
						<div>
							<p>已收抵扣联<span>${gather.deDoneAmount!'0'}</span>份</p>
							<p>税额<span>${gather.deDone!'0'}</span>(元)</p>
						</div>
					</#if>
				</li>
				<li class="li06">
					<#if gather.deDoneDate??>
						<div>最早日期：${gather.deDoneDate?string("yyyy-MM-dd")}</div>
					</#if>
				</li>
				<li>
					<#if gather.incomeTaxTodo??>
						<div>
							本月应纳所得税
							<p><span>${gather.incomeTaxTodo!'0'}</span>元</p>
						</div>
					</#if>
				</li>
			</ul>

			
			<textarea name="remark" class="clear_tex">请输入补充说明</textarea>
			<a href="javascript:remarkSubmit();" class="clear_back">确认无误</a>
			<div style="height: 30px;width: 100%;"></div>
			<#else>
			<h3>没有票据信息！</h3>
			</#if>
		</article>
		</form>
	</body>
</html>
