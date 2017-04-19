<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ include file="../include.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>平台商品信息表</title>
	<script>
		function toAdd(){
			window.location.href="CpzPlatProductAction!toAdd?productid=${productid}";
		}
		
		
		$(document).on('ready', function() {
			var productid="${productid}";
			$("#productid").val(productid);
			getAll('CpzPlatProductAction!list');
		});
		
		
		function search(){
			getAll('CpzPlatProductAction!list');
		}
		
		function dodel(mproductid){
			var r=confirm("确定要删除吗？");
			if(r){
				$.ajax({
					type:'POST',
					url:'CpzPlatProductAction!doDelete',
					data:{
productid:mproductid					
},
					success:function(k){
							alert("删除成功！")
							window.location.href = "CpzPlatProductAction!index?productid=${productid}";
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
productid:searchInput					},
					success:function(result){
	
							var divtext = '';
							var data =result.list;
							var pagenational = result.pageString;
						
							for(var i=0;i<data.length;i++){
								divtext += '<tr class="even" style="white-space:nowrap; overflow:hidden; text-align:center">';
	 divtext += '<td>' + data[i].productname + '</td>';
	 divtext += '<td>' + data[i].productcategoryid + '</td>';
	 divtext += '<td>' + data[i].producttype + '</td>';
	 divtext += '<td>' + data[i].productstatus + '</td>';
	 divtext += '<td>' + data[i].tradingunit + '</td>';
	 divtext += '<td>' + data[i].productcolor + '</td>';
	 divtext += '<td>' + data[i].isfresh + '</td>';
	 divtext += '<td>' + data[i].sourcearea + '</td>';
	 divtext += '<td>' + data[i].iscanrefund + '</td>';
	 divtext += '<td>' + data[i].isneedspot + '</td>';
	 divtext += '<td>' + data[i].productdetail + '</td>';
	 divtext += '<td>' + data[i].supportday + '</td>';
	 divtext += '<td>' + data[i].channelno + '</td>';
	 divtext += '<td>' + data[i].remakr1 + '</td>';
	 divtext += '<td>' + data[i].remakr2 + '</td>';
								divtext += '<td ><a href="CpzPlatProductAction!toUpdate?productid='+data[i].productid+'"> [修改] </a>'
								divtext +='|<a href="javascript:void(0);" onclick="dodel('+data[i].productid+')"> [删除] </a></td>';
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
	<input type="hidden" id="productid" name="productid" value="" />
	平台商品信息表：<input type="text" id="searchInput" style="margin-left:10px;width:100px;height:20px; "/>
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
				<th  style="text-align:center;">商品类型0：单个商品1：套餐</th>
				<th  style="text-align:center;">商品状态0:上架1：下架</th>
				<th  style="text-align:center;">交易单位0：按斤:1：按块2 : 按只3：按支4：按/瓶</th>
				<th  style="text-align:center;">颜色</th>
				<th  style="text-align:center;">是否鲜活0：否1：是 （此属性是否可以不要，归类于商品状态？）</th>
				<th  style="text-align:center;">原产地名称</th>
				<th  style="text-align:center;">是否支持退货0：否 1：是</th>
				<th  style="text-align:center;">是否需要当场处理0：否1：是（是否支持当场处理？）</th>
				<th  style="text-align:center;">详情URL地址</th>
				<th  style="text-align:center;">配菜支持天数</th>
				<th  style="text-align:center;">商品发布渠道 多个渠道以“,”好隔开01：微信02：安卓03：IOS（应该没有渠道）</th>
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

