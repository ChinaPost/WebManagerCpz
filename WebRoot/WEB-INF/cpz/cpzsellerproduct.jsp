<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ include file="../include.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>卖家商品信息表</title>
	<script>
		function toAdd(){
			window.location.href="CpzSellerProductAction!toAdd?shopproductid=${shopproductid}";
		}
		
		
		$(document).on('ready', function() {
			var shopproductid="${shopproductid}";
			$("#shopproductid").val(shopproductid);
			getAll('CpzSellerProductAction!list');
		});
		
		
		function search(){
			getAll('CpzSellerProductAction!list');
		}
		
		function dodel(mshopproductid){
			var r=confirm("确定要删除吗？");
			if(r){
				$.ajax({
					type:'POST',
					url:'CpzSellerProductAction!doDelete',
					data:{
shopproductid:mshopproductid					
},
					success:function(k){
							alert("删除成功！")
							window.location.href = "CpzSellerProductAction!index?shopproductid=${shopproductid}";
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
shopproductid:searchInput					},
					success:function(result){
	
							var divtext = '';
							var data =result.list;
							var pagenational = result.pageString;
						
							for(var i=0;i<data.length;i++){
								divtext += '<tr class="even" style="white-space:nowrap; overflow:hidden; text-align:center">';
	 divtext += '<td>' + data[i].productname + '</td>';
	 divtext += '<td>' + data[i].productpic + '</td>';
	 divtext += '<td>' + data[i].merchintro + '</td>';
	 divtext += '<td>' + data[i].description + '</td>';
	 divtext += '<td>' + data[i].productstatus + '</td>';
	 divtext += '<td>' + data[i].productid + '</td>';
	 divtext += '<td>' + data[i].shopid + '</td>';
	 divtext += '<td>' + data[i].orderno + '</td>';
	 divtext += '<td>' + data[i].starttime + '</td>';
	 divtext += '<td>' + data[i].endtime + '</td>';
	 divtext += '<td>' + data[i].remakr1 + '</td>';
	 divtext += '<td>' + data[i].levelid + '</td>';
								divtext += '<td ><a href="CpzSellerProductAction!toUpdate?shopproductid='+data[i].shopproductid+'"> [修改] </a>'
								divtext +='|<a href="javascript:void(0);" onclick="dodel('+data[i].shopproductid+')"> [删除] </a></td>';
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
	<input type="hidden" id="shopproductid" name="shopproductid" value="" />
	卖家商品信息表：<input type="text" id="searchInput" style="margin-left:10px;width:100px;height:20px; "/>
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
	            
				<tr> 				<th  style="text-align:center;">商品自定义名称</th>
				<th  style="text-align:center;">商品自定义图片</th>
				<th  style="text-align:center;">商品简介</th>
				<th  style="text-align:center;">商品描述</th>
				<th  style="text-align:center;">商品状态</th>
				<th  style="text-align:center;">关联的平台商品</th>
				<th  style="text-align:center;">关联店铺代号</th>
				<th  style="text-align:center;">订单号格式：日期+10位流水号</th>
				<th  style="text-align:center;">商品在线开始时间格式：yyyymmdd hh24miss</th>
				<th  style="text-align:center;">商品在线结束时间格式：yyyymmdd hh24miss</th>
				<th  style="text-align:center;">备注</th>
				<th  style="text-align:center;">关联平台分类</th>
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

