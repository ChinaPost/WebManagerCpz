<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ include file="../include.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>买家订单表</title>
	<script>
		function toAdd(){
			window.location.href="CpzBuyerOrderAction!toAdd?orderno=${orderno}";
		}
		
		
		$(document).on('ready', function() {
			var orderno="${orderno}";
			$("#orderno").val(orderno);
			getAll('CpzBuyerOrderAction!list');
		});
		
		
		function search(){
			getAll('CpzBuyerOrderAction!list');
		}
		
		function dodel(morderno){
			var r=confirm("确定要删除吗？");
			if(r){
				$.ajax({
					type:'POST',
					url:'CpzBuyerOrderAction!doDelete',
					data:{
orderno:morderno					
},
					success:function(k){
							alert("删除成功！")
							window.location.href = "CpzBuyerOrderAction!index?orderno=${orderno}";
					},
					error : function() {
						alert("对不起，系统错误，请稍候重试！")
					}
				});
			}
			
			
		}
		function getAll(tzurl){
var searchInput = $("#searchInput").val();
				$.ajax({
					type:'POST',
					dataType:'json',
					url:tzurl,
					data:{
orderno:searchInput					},
					success:function(result){
	
							var divtext = '';
							var data =result.list;
							var pagenational = result.pageString;
						
							for(var i=0;i<data.length;i++){
								divtext += '<tr class="even" style="white-space:nowrap; overflow:hidden; text-align:center">';
	 divtext += '<td>' + data[i].userid + '</td>';
	 divtext += '<td>' + data[i].username + '</td>';
	 divtext += '<td>' + data[i].userphone + '</td>';
	 divtext += '<td>' + data[i].orderstatus + '</td>';
	 divtext += '<td>' + data[i].totalmoney + '</td>';
	 divtext += '<td>' + data[i].paymoney + '</td>';
	 divtext += '<td>' + data[i].paystatus + '</td>';
	 divtext += '<td>' + data[i].shopid + '</td>';
	 divtext += '<td>' + data[i].marketid + '</td>';
	 divtext += '<td>' + data[i].shiptype + '</td>';
	 divtext += '<td>' + data[i].provcode + '</td>';
	 divtext += '<td>' + data[i].citycode + '</td>';
	 divtext += '<td>' + data[i].countycode + '</td>';
	 divtext += '<td>' + data[i].detailaddr + '</td>';
	 divtext += '<td>' + data[i].invoicetype + '</td>';
	 divtext += '<td>' + data[i].invoicetitle + '</td>';
	 divtext += '<td>' + data[i].orderremark + '</td>';
	 divtext += '<td>' + data[i].getproducttime + '</td>';
	 divtext += '<td>' + data[i].booktime + '</td>';
	 divtext += '<td>' + data[i].paytime + '</td>';
	 divtext += '<td>' + data[i].channelno + '</td>';
	 divtext += '<td>' + data[i].remark1 + '</td>';
	 divtext += '<td>' + data[i].remark2 + '</td>';
								divtext += '<td ><a href="CpzBuyerOrderAction!toUpdate?orderno='+data[i].orderno+'"> [修改] </a>'
								divtext +='|<a href="javascript:void(0);" onclick="dodel('+data[i].orderno+')"> [删除] </a></td>';
								divtext += '</tr>';
							}
							//divtext += pagenational;
							$("#newtable tbody").html(divtext);
							$("#pageContent").html(pagenational);
					}
				});
		}
	</script>
</head>
<body style="overflow:auto">
	<div style="padding-left:20px;margin-bottom:10px;" >
	<input type="hidden" id="orderno" name="orderno" value="" />
	买家订单表：<input type="text" id="searchInput" style="margin-left:10px;width:100px;height:20px; "/>
	<input type="button" value="查询" name = "btn_search" onmouseover="this.style.cursor='hand'" style="width:50px;height:20px;font-size:12px;" class="subBtn" onclick="search()">
	<input type="button" value="新增" name = "btn_search" onmouseover="this.style.cursor='hand'" style="width:50px;height:20px;font-size:12px;" class="subBtn" onclick="toAdd()">
	
	</div>
	<div id="signContent">
	  <div class="table-list lr10">
	      <table width="100%">
	      <tr>
	        <td style="vertical-align: top;">
	        <table id="newtable" width="100%">
	          <thead class=trhead id="tblHeader">
	            
				<tr> 				<th  style="text-align:center;">会员号</th>
				<th  style="text-align:center;">会员姓名</th>
				<th  style="text-align:center;">会员联系手机</th>
				<th  style="text-align:center;">订单状态01：待付款02：已付款03：等待发货04：已备货05：已确认收货06：已完成07：已取消08：退款中09：已退款10：退款拒绝</th>
				<th  style="text-align:center;">订单总金额</th>
				<th  style="text-align:center;">订单已支付金额</th>
				<th  style="text-align:center;">订单支付状态0：未支付1：支付中2：已支付</th>
				<th  style="text-align:center;">店铺代号</th>
				<th  style="text-align:center;">所在市场代号</th>
				<th  style="text-align:center;">配送方式预留 01：自提02：寄递</th>
				<th  style="text-align:center;">省份代号预留：目前阶段接口送空值</th>
				<th  style="text-align:center;">市局代号预留：目前阶段接口送空值</th>
				<th  style="text-align:center;">区县代号预留：目前阶段接口送空值</th>
				<th  style="text-align:center;">详细地址代号</th>
				<th  style="text-align:center;">发票类型预留发票模块：目前阶段接口送0：不开发票 1：个人发票 2：单位发票</th>
				<th  style="text-align:center;">发票抬头</th>
				<th  style="text-align:center;">给卖家留言</th>
				<th  style="text-align:center;">提货时间</th>
				<th  style="text-align:center;">订单下单时间格式：yyyymmdd hh24miss</th>
				<th  style="text-align:center;">订单支付时间格式：yyyymmdd hh24miss</th>
				<th  style="text-align:center;">订单受理渠道01：微信02：安卓03：IOS</th>
				<th  style="text-align:center;">备注1</th>
				<th  style="text-align:center;">备注2</th>
				<th  style="text-align:center;">操作</th></tr>
				</thead>
				<tbody id="records">
			    </tbody>
		</table>
	       </td>
	      </tr>
	      </table>
	</div>
	<div id="pageContent"></div>
  </div>
	
</body>
</html>

