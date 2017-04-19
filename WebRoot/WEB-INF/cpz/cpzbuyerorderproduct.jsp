<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ include file="../include.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>订单关联商品表</title>
	<script>
		function toAdd(){
			window.location.href="CpzBuyerOrderProductAction!toAdd?orderno=${orderno}%26shopproductid=${shopproductid}";
		}
		
		
		$(document).on('ready', function() {
			var orderno="${orderno}";
			$("#orderno").val(orderno);
			var shopproductid="${shopproductid}";
			$("#shopproductid").val(shopproductid);
			getAll('CpzBuyerOrderProductAction!list');
		});
		
		
		function search(){
			getAll('CpzBuyerOrderProductAction!list');
		}
		
		function dodel(morderno,mshopproductid){
			var r=confirm("确定要删除吗？");
			if(r){
				$.ajax({
					type:'POST',
					url:'CpzBuyerOrderProductAction!doDelete',
					data:{
orderno:morderno,
shopproductid:mshopproductid					
},
					success:function(k){
							alert("删除成功！")
							window.location.href = "CpzBuyerOrderProductAction!index?orderno=${orderno}%26shopproductid=${shopproductid}";
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
orderno:searchInput,
shopproductid:searchInput					},
					success:function(result){
	
							var divtext = '';
							var data =result.list;
							var pagenational = result.pageString;
						
							for(var i=0;i<data.length;i++){
								divtext += '<tr class="even" style="white-space:nowrap; overflow:hidden; text-align:center">';
	 divtext += '<td>' + data[i].shopproductname + '</td>';
	 divtext += '<td>' + data[i].levelid + '</td>';
	 divtext += '<td>' + data[i].producttype + '</td>';
	 divtext += '<td>' + data[i].shopnormsid + '</td>';
	 divtext += '<td>' + data[i].shopnormsnum + '</td>';
	 divtext += '<td>' + data[i].shopnormsprice + '</td>';
								divtext += '<td ><a href="CpzBuyerOrderProductAction!toUpdate?orderno='+data[i].orderno+'%26shopproductid='+data[i].shopproductid+'"> [修改] </a>'
								divtext +='|<a href="javascript:void(0);" onclick="dodel('+data[i].orderno+','+data[i].shopproductid+')"> [删除] </a></td>';
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
	<input type="hidden" id="shopproductid" name="shopproductid" value="" />
	订单关联商品表：<input type="text" id="searchInput" style="margin-left:10px;width:100px;height:20px; "/>
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
	            
				<tr> 				<th  style="text-align:center;">商品名称</th>
				<th  style="text-align:center;">商品所属分类代号</th>
				<th  style="text-align:center;">商品类型0：单个商品:1：套餐</th>
				<th  style="text-align:center;">购买规格</th>
				<th  style="text-align:center;">购买数量</th>
				<th  style="text-align:center;">购买价格</th>
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

