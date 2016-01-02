////////////////计算heml 高
function html_hi(){
	var oHtml = document.getElementsByTagName('html')[0];
	var win_hi = window.screen.height;
	var doc_hi =document.documentElement.offsetHeight;
	if(doc_hi>=win_hi){
		oHtml.style.height = doc_hi + 'px';
	}else{
		oHtml.style.height = win_hi + 'px';
	};
	
};
////////////////////////////////////////
/////////////////////////footer
function choice_food(obj,colo){
	var oCh = rich('.'+obj);
	oCh[0].children[1].style.display = 'block';
	oCh[0].children[0].style.display = 'none';
	oCh[0].children[2].style.color = colo;
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
////////////////////////////////////////
////////////////////////////////////////tel_out电话弹出
function tel_out(obj){
	var oTel = rich(obj)[0];
	var tBox = oTel.children[0];
	oTel.style.height = window.screen.height + 'px';
	var my_top = (window.screen.height - tBox.offsetHeight)/2;
	tBox.style.marginTop = my_top +'px';
};	
function phone_show(obj){
	rich(obj)[0].style.display = 'block';
	tel_out(obj);
};
function phone_hide(obj){
	rich(obj)[0].style.display = 'none';
};
//////////////////////////
function teaty_show(obj){
	rich(obj)[0].style.display = 'block';
	var oTel = rich(obj)[0];
	var tBox = oTel.children[0];
	oTel.style.height = window.screen.height + 'px';
};
////////////////////////////////////////////////////////
////////////////////////////////////////////////////////poto
function img_big(){
	var oBox = rich('.poto_box')[0];
	var aLi = oBox.children;
	var len = aLi.length;
	var aAffi = rich('.affirm')[0];
	var aFdet =rich('.affirm')[0].children[0];
	var aFback =rich('.affirm')[0].children[1];
	for(var i=0;i<len-1;i++){
		aLi[i].onclick =function(){
			var oA = this.children[1];
			var wi_fa =aLi[0].parentNode;
			var wi_fater = richStyle(wi_fa,'width');
			
			if(this.style.width !=='100%'){
				for(var i=0;i<len;i++){
					aLi[i].style.display = 'none';
				};
				this.style.display = 'block';
				this.style.margin = '0px';
				//this.style.overflow = 'hidden';
				this.style.width = '100%';
				this.style.height = '100%';
				oA.style.display = 'block';
			}else{
				//this.style.overflow = 'auto';
				this.style.width = '19%';
				this.style.height = '40%';
				this.style.marginLeft = '1%';
				oA.style.display = 'none';
				var timer = setTimeout(function(){				
					for(var i=0;i<len;i++){
						aLi[i].style.display = 'block';
					};
				},1000);
			};
				
			oA.onclick =function(){
				event.stopPropagation();///阻止 冒泡
				var oPare = oA.parentNode;
				aAffi.style.display = 'block';
				aFdet.onclick =function(){
					oBox.removeChild(oPare);
					len--;
					aAffi.style.display = 'none';
					for(var i=0;i<len;i++){
						aLi[i].style.display = 'block';
					};
				};
				aFback.onclick = function(){
					aAffi.style.display = 'none';
				};	
			};
		};
	};
	var aChoice = rich('#choice_poto');
	var aDd = rich('.add_poto')[0];
	//var aDd_add = rich('.add_poto')[0].children[0];
	var aDd_back= rich('.add_poto')[0].children[2];	
	aChoice.onclick = function(){
		aDd.style.display = 'block';
		aDd_back.onclick = function(){
			aDd.style.display = 'none';
		};
	};
	
};


/*--------------------------zhangji/ ---------------------*/
function regFormSubmit()
{
	<!--
	form = document.forms["regForm"];
	form.changeRole.value=document.getElementById("roleId").value;
	form.submit();
	-->
	var roleId = document.getElementById("roleId");
	var enterType = document.getElementById("enterType");
	  $.ajax({
	      type:"post",
	      url:"/reg",
	      data:{"id":id,"activityId":activityId,"statusId":statusId,"reason":reason},
	      success:function(data){
			if (data.code == 1)
			{
	            Showbo.Msg.alert(data.msg);
			}
			else{
				location.reload();
			}
	      }
	  });
	}
}

function selectType(id)
{
	$(".input02").removeClass("inputSelected");
	$(".input02").removeAttr("selected");
	
	$("#enterType"+id).attr("selected","selected");
	$("#enterTypeId"+id).attr("selected","selected");
	$("#type"+id).addClass("inputSelected");
}	










