<!DOCTYPE html>
<html>
	<head>
		<style type="text/css">
			body{width: 100%;height: 100%;}
		</style>
		<#if site??>
		<meta name="keywords" content="${site.seoKeywords!''}">
		<meta name="description" content="${site.seoDescription!''}">
		<meta name="copyright" content="${site.copyright!''}" />
		</#if>
		<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
		<meta charset="utf-8">
		<title>我要</title>
		
		<link rel="stylesheet" type="text/css" href="/client/css/rich_base.css"/>
		<link rel="stylesheet" type="text/css" href="/client/css/rich_other.css"/>
		<script src="/client/js/Rich_Lee.js" type="text/javascript"></script>
		<script src="/client/js/TweenMax1.18.0.min.js" type="text/javascript"></script>
		<script src="/client/js/rich_one.js" type="text/javascript"></script>
	</head>
	<script type="text/javascript">
		window.onload = function(){
				html_hi();//body宽高	
				choice_food('footer dd a','#00b8e1');///footer
		};
		
		////////////////////////////////////////
		/////////////////////////footer
		function choice_food(obj,colo){
			var oCh = rich('.'+obj);
			oCh[1].children[1].style.display = 'block';
			oCh[1].children[0].style.display = 'none';
			oCh[1].children[2].style.color = colo;
			for(var i=0;i<oCh.length;i++){
				oCh[i].onclick = function(){
					for(var i=0;i<oCh.length;i++){
						oCh[i].children[0].style.display = 'block';
						oCh[i].children[1].style.display = 'none';
						oCh[i].children[2].style.color = '';
					};		
					this.children[1].style.display = 'block';
					this.children[0].style.display = 'none';
					this.children[2].style.color = colo;
				};
			};
		};
	</script>
	<body style="background: #f2f2f2;">
		<header class="header_one">
			<!--
            	作者：345642459@qq.com
            	时间：2015-12-21
            	描述：
            -->
            <p class="test" href="javascript:history.back(-1);"></p>
			<div>我要</div>
		</header>
		<ul class="want">
			<#if applyType_list??>
				<#list applyType_list as item>
					<li>
						<a href="/apply/edit/${item.id?c}">
							<#switch item_index%4 >
								<#case 0>
									<img src="/client/images/want_icon01.png"/>
								<#break>
								<#case 1>
									<img src="/client/images/want_icon02.png"/>
								<#break>
								<#case 2>
									<img src="/client/images/want_icon03.png"/>
								<#break>
								<#case 3>
									<img src="/client/images/want_icon04.png"/>
								<#break>	
							</#switch>			
							<p>${item.title!''}</p>
						</a>
					</li>
				</#list>
			</#if>		
		</ul>
		
		
		
		
		<div style="height: 80px;width: 100%; float: left;"></div>
		<dl class="footer">
				<dd>
					<a href="/">
						<img src="/client/images/foote01.png"/>
						<img src="/client/images/foote11.png"/>					
						<span>我的</span>
					</a>
				</dd>
				<dd>
					<a href="/apply">
						<img src="/client/images/foote02.png"/>
						<img src="/client/images/foote22.png"/>					
						<span>我要</span>
					</a>
				</dd>
				<dd>
					<a href="/user/center">
						<img src="/client/images/foote03.png"/>
						<img src="/client/images/foote33.png"/>					
						<span>财税宝</span>
					</a>
				</dd>
			</dl>
	
	</body>
</html>
