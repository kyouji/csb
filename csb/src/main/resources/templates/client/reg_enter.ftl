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
		<title>注册</title>
		
		<link rel="stylesheet" type="text/css" href="/client/css/rich_base.css"/>
		<link rel="stylesheet" type="text/css" href="/client/css/rich_other.css"/>
		<script src="/client/js/jquery-1.11.0.js" type="text/javascript"></script>
		<script src="/client/js/Rich_Lee.js" type="text/javascript"></script>
		<script src="/client/js/TweenMax1.18.0.min.js" type="text/javascript"></script>
		<script src="/client/js/rich_one.js" type="text/javascript"></script>
		
	</head>

	<body style="background: #f2f2f2;">
	<form name="regForm" id="regForm" action="/reg/changeRole" method="post">
		<section class="teaty_out">
			<div>
				<span>财税宝1688用户服务协议</span>
				<p>本《用户服务协议》（以下称“协
议”）是用户（以下称“您”）与重庆贵
恒财税咨询有限公司旗下微信公众号（以
下称“财税宝1688”）之间关于用户使
用财税宝1688相关服务所订立的协议。
为使用财税宝1688的服务，您应当阅读
并遵守本协议。请您务必审慎阅读、充分
理解各条款内容，特别是免除或者限制责
任的条款，以及开通或使用某项服务的单
独协议。限制、免责条款可能以黑体加粗
形式提示您注意。 除非您已阅读并接受
本协议所有条款，否则您无权使用财税宝
1688提供的服务。您使用财税宝1688的
服务即视为您已阅读并同意上述协议的约
束。</p>
<p>定义
•  服务：本协议项下的服务是财税宝168
8向您提供的包括但不限于即时通讯、协
同办公、网络媒体、互动娱乐、在线企业
业务处理、电子商务和广告等产品及服
务。
•  财税宝1688账户：指您在使用财税宝1
688服务时需注册的帐号。•  手机认证：
一旦您忘记帐号密码，可以帮助您重新获</p>
			</div>
			<a  onclick="phone_hide('.teaty_out')">关闭</a>
		</section>
		<header class="header_one">
			<a href="javascript:history.back(-1);"></a>
			<div>注册</div>
		</header>
		<article class="reg">
			<div>公司注册</div>
			<section class="choice_regis">
				<select id="changeRole" name="changeRole" onchange="javascript:regChangeRole();">
					<option value="">请选择注册类型</option>
					<option value="0">公司注册</option>
					<option value="1">会计注册</option>
				</select>
			</section>	
			<input type="hidden"  id="roleId" name="roleId"  value="0"> 
			<input class="input01" type="text" placeholder="请输入公司名称" autocomplete="on" name="enterName" id="enterName"  value="${enterName!''}"> 
			<#--<p class="choice_com">请选择公司类型</p>-->
			<#if enterType_list??>
				<#list enterType_list as item>
					<input class="type_radio" type="radio" style="display:none;"  id="enterType${item_index}"  name="enterType" value="${item.title!''}" <#if item_index == 0> selected="selected" </#if>>
					<input class="type_radio" type="radio" style="display:none;"  id="enterTypeId${item_index}"  name="enterTypeId"  value="${item.id?c!''}" <#if item_index == 0> selected="selected" </#if>>
					<input class="input02 <#if item_index == 0> inputSelected </#if>" type="button" id="type${item_index}" onclick="javascript:selectType(${item_index});" <#if item_index == 0> name="type" </#if>  value="${item.title!''}">
					<#--<input type="hidden" id="typeId${item_index}"  <#if item_index == 0> name="enterTypeId" </#if> value="<#if item??&&item.id??>${item.id?c}</#if>">-->
				</#list>
			</#if>	
			<input class="input01" type="text" placeholder="请输入联系人姓名" id="realName" name="realName"  value="${realName!''}">
			<input class="input01" type="text" placeholder="请输入联系人电话" id="mobile" name="mobile"  value="${mobile!''}">
			<input class="input01" type="text" placeholder="请输入用户名" id="username" name="username"  value="${username!''}">
			<input class="input01" type="password" placeholder="请输入密码" id="password" name="password"  value="">
			<input class="input01" type="password" placeholder="请再次输入密码" id="password2" name="password2"  value="">
			<span>上传营业执照</span>
			<section class="poto">	
				<ul class="poto_box">
					<li>
						<img src="/client/images/touxiang01.png"/>
						<a></a>
					</li>
					<li>
						<img src="/client/images/touxiang01.png"/>
						<a></a>
					</li>
					<li>
						<img src="/client/images/touxiang01.png"/>
						<a></a>
					</li>
					<li>
						<img src="/client/images/touxiang01.png"/>
						<a></a>
					</li>
					<li>
						<img src="/client/images/touxiang01.png"/>
						<a></a>
					</li>
					<li>
						<img src="/client/images/touxiang01.png"/>
						<a></a>
					</li>
					<li>
						<div id="choice_poto"></div>
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
			<input type="button" onclick="javascript:regEnterSubmit();"  value="同意协议并注册" style="margin-top: 20px; color:#ffffff; background: #00b8e1;">
			<p class="teaty" onclick="teaty_show('.teaty_out')">财税宝1688用户服务协议</p>
		</article>
	</form>	
	</body>
</html>
