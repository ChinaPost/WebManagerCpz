<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ include file="../include.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>买家购物车表</title>
	<script>
		function toAdd(){
			window.location.href="CpzBuyerShoppingAction!toAdd?shopcarid=${shopcarid}";
		}
		
		
		$(document).on('ready', function() {
			var shopcarid="${shopcarid}";
			$("#shopcarid").val(shopcarid);
			getAll('CpzBuyerShoppingAction!list');
		});
		
		
		function search(){
			getAll('CpzBuyerShoppingAction!list');
		}
		
		function dodel(mshopcarid){
			var r=confirm("确定要删除吗？");
			if(r){
				$.ajax({
					type:'POST',
					url:'CpzBuyerShoppingAction!doDelete',
					data:{
shopcarid:mshopcarid					
},
					success:function(k){
							alert("删除成功！")
							window.location.href = "CpzBuyerShoppingAction!index?shopcarid=${shopcarid}";
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
shopcarid:searchInput					},
					success:function(result){
	
							var divtext = '';
							var data =result.list;
							var pagenational = result.pageString;
						
							for(var i=0;i<data.length;i++){
								divtext += '<tr class="even" style="white-space:nowrap; overflow:hidden; text-align:center">';
	 divtext += '<td>' + data[i].userid + '</td>';
	 divtext += '<td>' + data[i].shopproductid + '</td>';
	 divtext += '<td>' + data[i].shopid + '</td>';
	 divtext += '<td>' + data[i].marketid + '</td>';
	 divtext += '<td>' + data[i].normsid + '</td>';
	 divtext += '<td>' + data[i].buynum + '</td>';
	 divtext += '<td>' + data[i].normsprice + '</td>';
	 divtext += '<td>' + data[i].createtime + '</td>';
	 divtext += '<td>' + data[i].remark + '</td>';
								divtext += '<td ><a href="CpzBuyerShoppingAction!toUpdate?shopcarid='+data[i].shopcarid+'"> [修改] </a>'
								divtext +='|<a href="javascript:void(0);" onclick="dodel('+data[i].shopcarid+')"> [删除] </a></td>';
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
	<input type="hidden" id="shopcarid" name="shopcarid" value="" />
	买家购物车表：<input type="text" id="searchInput" style="margin-left:10px;width:100px;height:20px; "/>
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
				<th  style="text-align:center;">卖家商品代号</th>
				<th  style="text-align:center;">卖家店铺代号</th>
				<th  style="text-align:center;">卖家市场代号</th>
				<th  style="text-align:center;">购买规格</th>
				<th  style="text-align:center;">购买数量</th>
				<th  style="text-align:center;">购买价格</th>
				<th  style="text-align:center;">加入购物车时间格式：yyyymmdd hh24miss</th>
				<th  style="text-align:center;">备注</th>
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

