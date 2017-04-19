<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ include file="../include.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html >
<head>
	<meta charset="UTF-8">
  
	<title>jQuery点击展开收缩树形菜单</title>
	  <link rel="icon" href="favicon.ico" mce_href="favicon.ico" type="image/x-icon">
	<style type="text/css">
	*{margin: 0;padding: 0}
	body{font-size: 12px;font-family: "宋体","微软雅黑";}
	ul,li{list-style: none;}
	a:link,a:visited{text-decoration: none;color: #fff;}
	.list{width: 210px;border-bottom:solid 1px #316a91;margin:0px auto 0 0;}
	.list ul li{background-color:#467ca2; border:solid 1px #316a91; border-bottom:0;}
	.list ul li a{padding-left: 10px;color: #fff; font-size:12px; display: block; font-weight:bold; height:36px;line-height: 36px;position: relative;
	}
	.list ul li .inactive{ background:url(./images/off.png) no-repeat 184px center;}
	.list ul li .inactives{background:url(./images/on.png) no-repeat 184px center;} 
	.list ul li ul{display: none;}
	.list ul li ul li { border-left:0; border-right:0; background-color:#6196bb; border-color:#467ca2;}
	.list ul li ul li ul{display: none;}
	.list ul li ul li a{ padding-left:20px;}
	.list ul li ul li ul li { background-color:#d6e6f1; border-color:#6196bb; }
	.last{ background-color:#d6e6f1; border-color:#6196bb; }
	.list ul li ul li ul li a{ color:#316a91; padding-left:30px;}
	</style>
	
	<script type="text/javascript">
	$(document).ready(function() {
		$('.inactive').click(function(){
			var className=$(this).parents('li').parents().attr('class');
			if($(this).siblings('ul').css('display')=='none'){
				if(className=="yiji"){
					$(this).parents('li').siblings('li').children('ul').parent('li').children('a').removeClass('inactives');
					$(this).parents('li').siblings('li').children('ul').slideUp(100);
				}
				$(this).addClass('inactives');
				$(this).siblings('ul').slideDown(100).children('li');
			}else {
				$(this).removeClass('inactives');
				$(this).siblings('ul').slideUp(100);
			}
		})
	});
	
	
	
	
	
	</script>
</head>
<body>
<div style="width:100%;height:10%;background:#888;font-size:20px;color:#000;" >菜铺子管理后台
</div>
	<div class="list" style="margin-left:0;float:left;width:20%background:#008">
		<ul class="yiji">
			<li><a href="CpzBuyerMsgAction!index" target="rightFrame">买家基本信息表</a></li>
<li><a href="CpzBuyerCollectShopAction!index" target="rightFrame">买家店铺收藏信息表</a></li>
<li><a href="CpzTerminfoLinkAction!index" target="rightFrame">买家或者卖家关联第三方信息表</a></li>
<li><a href="CpzBuyerOrderAction!index" target="rightFrame">买家订单表</a></li>
<li><a href="CpzBuyerShoppingAction!index" target="rightFrame">买家购物车表</a></li>
<li><a href="CpzBuyerRefundAction!index" target="rightFrame">买家退款表</a></li>
<li><a href="CpzSellerProductAction!index" target="rightFrame">卖家商品信息表</a></li>
<li><a href="CpzShopProductNormsAction!index" target="rightFrame">卖家商品规格表</a></li>
<li><a href="CpzSellerAction!index" target="rightFrame">卖家表</a></li>
<li><a href="CpzProductLevelAction!index" target="rightFrame">商品分类表</a></li>
<li><a href="GspProductNormsMapAction!index" target="rightFrame">商品规格关联表</a></li>
<li><a href="CpzMarketAction!index" target="rightFrame">市场表</a></li>
<li><a href="CpzPlatProductAction!index" target="rightFrame">平台商品信息表</a></li>
<li><a href="CpzPlatProductPicAction!index" target="rightFrame">平台商品图片表</a></li>
<li><a href="CpzPlatProductRecommendAction!index" target="rightFrame">平台商品推荐表</a></li>
<li><a href="CpzPlatProductNormsAction!index" target="rightFrame">平台商品规格表</a></li>
<li><a href="CpzShopBusineeRangeAction!index" target="rightFrame">店铺经营范围表</a></li>
<li><a href="CpzMessageAction!index" target="rightFrame">消息表</a></li>
<li><a href="CpzMessageSetAction!index" target="rightFrame">消息设置表</a></li>
<li><a href="GspSettlementAction!index" target="rightFrame">结算表</a></li>
<li><a href="CpzBuyerOrderProductAction!index" target="rightFrame">订单关联商品表</a></li>
<li><a href="CpzBuyerLogAction!index" target="rightFrame">订单或者退款单日志表</a></li>
<li><a href="CpzOrderStatusOpAction!index" target="rightFrame">订单状态操作表</a></li>
<li><a href="GspSalesReportAction!index" target="rightFrame">销量统计表</a></li>
		</ul>
	</div>
	

	<iframe name=rightFrame frameborder=0 style="float:center;padding-top:10px;width:79%;height:90%"></iframe>
	
</body>
</html>